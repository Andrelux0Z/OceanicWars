/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import com.mycompany.oceanicwars.Typewritter;
import Hero.Hero;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;

/**
 *
 * @author kokoju
 */

public class Matriz extends JPanel implements Serializable, MouseListener { // Hereda de JPanel para la creación de componentes visuales
    // Atributos
    // Cantidades (tanto de filas como columnas)
    private final int cantidadFilas;
    private final int cantidadColumnas;

    private int cantidadCasillas;

    // Medidas (tanto de filas como columnas)
    private int sizeAlto;
    private int sizeLargo;

    // Matriz que almacena casillas
    private Casilla[][] matriz;

    // Arreglo para almacenar los héroes
    private ArrayList<Hero> heroes;

    private ArrayList<Casilla> casillasActivas; // Arreglo para el guardado de casillas vivas (útil para dirigir los
                                                // ataques a casillas que realmente siguen funcionando)
    private ArrayList<Casilla> casillasTotales; // Arreglo para guardar el total de casillas

    private FrameClient refCliente; // Referencia al Cliente para acceder a sus espacios

    // Atributos para mostrar información de una casilla
    private Casilla casillaInfoMostrada;  // Casilla que es ha sido recientemente mostrada en la bitácora
    private javax.swing.Timer timerInfoCasilla;  // Timer que almacenará el tiempo que fue seleccionado la casilla
    
    // Constructor
    public Matriz(int cantidadFilas, int cantidadColumnas, FrameClient refCliente) {
        // Se establece el tamaño del componente 'matriz' para que sea el mismo que el
        // de 'pnlMatriz'
        this.setPreferredSize(refCliente.getPnlMatriz().getPreferredSize());
        this.setSize(refCliente.getPnlMatriz().getPreferredSize());
        this.refCliente = refCliente;
        this.cantidadFilas = cantidadFilas;
        this.cantidadColumnas = cantidadColumnas;
        this.cantidadCasillas = this.cantidadFilas * this.cantidadColumnas;
        this.sizeAlto = this.refCliente.getPnlMatriz().getHeight() / this.cantidadFilas; // Conseguimos la altura del
                                                                                         // componente pnlMatriz para
                                                                                         // crear casillas con la medida
                                                                                         // correcta
        this.sizeLargo = this.refCliente.getPnlMatriz().getWidth() / this.cantidadColumnas; // Conseguimos el largo del
                                                                                            // componente pnlMatriz para
                                                                                            // crear casillas con la
                                                                                            // medida correta
        this.matriz = new Casilla[this.cantidadFilas][this.cantidadColumnas]; // Creamos una matriz del tamaño desado

        this.heroes = new ArrayList<Hero>(); // Una matriz está conformada por casillas pertenecientes a 3 héroes: suena
                                             // lógico hacer un arreglo de estos

        this.casillasActivas = new ArrayList<Casilla>();
        this.casillasTotales = new ArrayList<Casilla>();
        this.casillaInfoMostrada = null;  // Inicialmente, la casilla mostrada es nula
        
        // La matriz misma se suma a la lista de los Listeners del Mouse. Esto es importante para acceder a las bitácoras de las casillas
        this.addMouseListener(this);
        
        this.crearMatriz(); // Se crea la matriz
    }

    // Métodos
    // TODO: CAMBIAR COLOR EN FUNCIÓN DEL LUCHADOR
    @Override
    protected void paintComponent(Graphics g) { // Función encarga de cambiar el visual para cada casilla
        super.paintComponent(g); // Limpia el fondo (necesario para que no dibuje un elemento sobre otro anterior
        // Se recorre toda la matriz
        for (int i = 0; i < this.cantidadFilas; i++) {
            for (int j = 0; j < this.cantidadColumnas; j++) {
                // Para cada casilla, se consigue su color, sus medidas y se dibuja
                Casilla c = matriz[i][j]; // Se consigue la casilla
                g.setColor(c.getColor()); // Se consigue su color
                g.fillRect(j * sizeLargo + 1, i * sizeAlto + 1, sizeLargo - 2, sizeAlto - 2); // Se hace un rectángulo
                                                                                              // relleno con ese color
                                                                                              // (se suma 1 en los size
                                                                                              // para ajustar el color,
                                                                                              // además de restar 2 para
                                                                                              // que no se sobrepase)
                g.setColor(Color.BLACK); // Se pone en color negro
                g.drawRect(j * sizeLargo, i * sizeAlto, sizeLargo - 1, sizeAlto - 1); // Se hace un rectángulo sin
                                                                                      // relleno de borde negro para
                                                                                      // hacer la ilusión de división
                                                                                      // (se resta 1 para que no
                                                                                      // sobrepase los límites de la
                                                                                      // casilla
                if (!c.getEstado()) {  // Si la casilla revisada está muerta, se hace una X encima
                    g.drawLine(j * sizeLargo, i * sizeAlto, (j + 1) * sizeLargo, (i + 1) * sizeAlto);  // Línea diagonal desde la esquina superior izquierda a la esquina inferior derecha
                    g.drawLine(j * sizeLargo, (i + 1) * sizeAlto, (j + 1) * sizeLargo , i * sizeAlto);  // Línea diagonal desde la esquina inferior izquierda a la esquina superior derecha
                }
            }
        }
    }

    public void crearMatriz() {
        // Variables iniciales para asignar héroes
        ArrayList<Casilla> casillasPorPintar = new ArrayList<Casilla>();

        for (int i = 0; i < this.cantidadFilas; i++) {
            for (int j = 0; j < this.cantidadColumnas; j++) {
                Casilla c = new Casilla(i, j, this);
                matriz[i][j] = c;
                casillasTotales.add(c);
                casillasPorPintar.add(c);
                casillasActivas.add(c);
            }
        }

        Collections.shuffle(casillasPorPintar); // Hace un suffle, es decir, que reparte las casillas de manera
                                                // aleatoria
        for (Hero hero : heroes) { // Se revisa la lista de héroes
            int cantidadCasillasPorHeroe = (hero.getOcupacion() * this.cantidadCasillas) / 100;
            for (int k = 0; k < cantidadCasillasPorHeroe && !casillasPorPintar.isEmpty(); k++) { // Mientras que sigan
                                                                                                 // habíendo casillas y
                                                                                                 // no se haya llegado a
                                                                                                 // la cantidad de
                                                                                                 // casillas por
                                                                                                 // peleador
                Casilla c = casillasPorPintar.remove(0);
                c.setHero(hero); // Se toma una casilla cualquiera de las que quedan por pintar y se le asigna el héroe
                c.getHero().getCasillasEnPosesion().add(c);  // Se añade la casilla a las que tiene el héroe
            }
        }
    }

    public boolean IsCasillaEnRadio(int centro_x, int centro_y, int x, int y, int radio) { // Algunas funciones
                                                                                           // requieren saber que
                                                                                           // casillas hay adyacentes,
                                                                                           // entonces lo hacemos aquí
        if (centro_x == x && centro_y == y) { // Si la casilla que revisamos es la misma del centro
            return false; // Estableceremos que es false, porque no va a recibir 2 veces el ataque
        }
        int dx = x - centro_x; // Se calcula la distancia x entre ambas casilla
        int dy = y - centro_y; // Se calcula la distancia y entre ambas casilla
        return dx * dx + dy * dy <= radio * radio; // Pitágoras (a**2 + b**2 = c**2)
    }

    public boolean IsCasillaEnActivas(int x, int y) {
        for (Casilla c : this.casillasActivas)
            if (c.getX() == x && c.getY() == y) {
                return true;
            }
        return false;
    }
    
    public Casilla buscarCasillaEnClick(MouseEvent e) {  // Función que, en función de un evento del mouse, consigue sus coords y revisa la casilla pisada
        int x = e.getX();  // Se consigue el x pisado
        int y = e.getY();  // Se consigue el y pisado
        
        int filaCasilla = y / this.sizeAlto;
        int columnaCasilla = x / this.sizeLargo;

        return matriz[filaCasilla][columnaCasilla];
    }
    
    public void iniciarActualizacionInfo() {  // Función que toma una Casilla y muestra su info en el panel del Cliente (txaLastMove
        String texto = this.casillaInfoMostrada.mostrarInfoCasilla();  // Método encargado de sacar TODA la info de una casilla
        Typewritter.typeText(this.refCliente.getTxaLastMove(), texto, this.refCliente.getDELAY());  // Escribe el texto de manera gradual con Typewritter

        // Crear un nuevo Timer que actualice cada segundo
        timerInfoCasilla = new javax.swing.Timer(1000, e -> {
            casillaInfoMostrada.mostrarInfoCasilla();
        });

        // Iniciar el Timer
        timerInfoCasilla.start();
    }

    
    @Override
    public void mouseClicked(MouseEvent e) {
        Casilla casillaElegida = buscarCasillaEnClick(e);  // Se busca la casilla que tocó el jugador
        this.casillaInfoMostrada = casillaElegida;  // Esta casilla se establece como la casilla de la cual se muestra su info
        iniciarActualizacionInfo();  // Se llama a la función iniciarActualizacionInfo() para mostrar la información
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("MOUSE - PRESIONADO");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("MOUSE - SOLTADO");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("MOUSE EN COMPONENTE");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("MOUSE SALIÓ DEL COMPONENTE");
    }

    // Getters
    public int getCantidadFilas() {
        return cantidadFilas;
    }

    public int getCantidadColumnas() {
        return cantidadColumnas;
    }

    public int getSizeAlto() {
        return sizeAlto;
    }

    public int getSizeLargo() {
        return sizeLargo;
    }

    public Casilla[][] getMatriz() {
        return matriz;
    }

    public ArrayList<Casilla> getCasillasActivas() {
        return casillasActivas;
    }

    public ArrayList<Casilla> getCasillasTotales() {
        return casillasTotales;
    }

    public FrameClient getRefCliente() {
        return refCliente;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }
}