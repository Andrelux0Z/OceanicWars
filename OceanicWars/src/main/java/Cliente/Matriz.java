/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;
import Hero.Hero;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author kokoju
 */
public class Matriz extends JPanel {  // Hereda de JPanel para la creación de componentes visuales
    // Atributos
    // Cantidades (tanto de filas como columnas)
    private int cantidadFilas;
    private int cantidadColumnas;
    
    // Medidas (tanto de filas como columnas)
    private int sizeAlto;
    private int sizeLargo;
    
    // Valor aleatorio utilizado en las funciones
    private final Random rand = new Random();
    
    // Matriz que almacena casillas
    private Casilla[][] matriz;
    
    private ArrayList<Casilla> casillasActivas;  // Arreglo para el guardado de casillas vivas (útil para dirigir los ataques a casillas que realmente siguen funcionando)
    private ArrayList<Casilla> casillasInactivas;  // Arreglo para guardar casillas asesinadas (puede no ser tan útil)
    
    
    private FrameClient refCliente;  // Referencia al Cliente para acceder a sus espacios
    
    // Constructor
    public Matriz (int cantidadFilas, int cantidadColumnas, FrameClient refCliente) {
        // Se establece el tamaño del componente 'matriz' para que sea el mismo que el de 'pnlMatriz'
        this.setPreferredSize(refCliente.getPnlMatriz().getPreferredSize()); 
        this.setSize(refCliente.getPnlMatriz().getPreferredSize());
        this.refCliente = refCliente;
        this.cantidadFilas = cantidadFilas;
        this.cantidadColumnas = cantidadColumnas;
        this.sizeAlto = this.refCliente.getPnlMatriz().getHeight() / this.cantidadFilas;  // Conseguimos la altura del componente pnlMatriz para crear casillas con la medida correcta
        this.sizeLargo = this.refCliente.getPnlMatriz().getWidth() / this.cantidadColumnas;  // Conseguimos el largo del componente pnlMatriz para crear casillas con la medida correta
        this.matriz = new Casilla[this.cantidadFilas][this.cantidadColumnas];  // Creamos una matriz del tamaño desado
        this.casillasActivas = new ArrayList<Casilla>();
        this.casillasInactivas = new ArrayList<Casilla>();
        
        this.crearMatriz();  // Se crea la matriz
    }
    
    // Métodos
    // TODO: CAMBIAR COLOR EN FUNCIÓN DEL LUCHADOR
    @Override
    protected void paintComponent(Graphics g) {  // Función encarga de cambiar el visual para cada casilla
        super.paintComponent(g);  // Limpia el fondo (necesario para que no dibuje un elemento sobre otro anterior
        // Se recorre toda la matriz
        for (int i = 0; i < this.cantidadFilas; i++) {
            for (int j = 0; j < this.cantidadColumnas; j++) {
                // Para cada casilla, se consigue su color, sus medidas y se dibuja
                Casilla c = matriz[i][j];  // Se consigue la casilla
                g.setColor(c.getColor());  // Se consigue su color
                g.fillRect(j * sizeLargo + 1 , i * sizeAlto + 1, sizeLargo - 2, sizeAlto - 2);  // Se hace un rectángulo relleno con ese color (se suma 1 en los size para ajustar el color, además de restar 2 para que no se sobrepase)
                g.setColor(Color.BLACK);  // Se pone en color negro
                g.drawRect(j * sizeLargo, i * sizeAlto, sizeLargo - 1 , sizeAlto - 1);  // Se hace un rectángulo sin relleno de borde negro para hacer la ilusión de división  (se resta 1 para que no sobrepase los límites de la casilla
            }
        }
    }
    
    public void crearMatriz() {
        for (int i = 0; i < this.cantidadFilas; i++) {
            for (int j = 0; j < this.cantidadColumnas; j++) {
                matriz[i][j] = new Casilla(i, j, " ", Color.GREEN);
            }
        }
    }

    public boolean IsCasillaEnRadio(int centro_x, int centro_y, int x, int y, int radio) {  // Algunas funciones requieren saber que casillas hay adyacentes, entonces lo hacemos aquí
        if (centro_x == x && centro_y == y) {  // Si la casilla que revisamos es la misma del centro
            return false;  // Estableceremos que es false, porque no va a recibir 2 veces el ataque
        }
        int dx = x - centro_x;  // Se calcula la distancia x entre ambas casilla
        int dy = y - centro_y; // Se calcula la distancia y entre ambas casilla
        return dx * dx + dy * dy <= radio * radio;  // Pitágoras (a**2 + b**2 = c**2)
    }
    
    // Los ataques son ejecutados en las matrices de los jugadores, así que deberían ser programados aquí
    public void thunder_rain(Hero hero) {  // Reciben un héroe para ver sus estadísticas
        for (int i = 0; i < 100 ; i++) {  // For para hacer 100 rayos
            
            // Elección de potencia
            int potenciaGolpe = (10 + rand.nextInt(11)) * (1 + hero.getFuerzaAtaque());  // Calcula la potencia del golpe de cada rayo (cada uno hace entre 10 y 20 (porque nextInt va hasta n-1) * fuerza del héroe
            
            // Elección de casilla
            Casilla casilla = this.casillasActivas.get(rand.nextInt(casillasActivas.size()));  // Toma una casilla aleatoria de las que están presentes en el arreglo
            casilla.recibirGolpe(potenciaGolpe);  // Golpea a la casilla
            casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por un rayo, recibiendo " + potenciaGolpe + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
        }
    }
    
    public void poseidon_thunders(Hero hero) {
        // Cantidad de rayos generada
        // Al ser un ataque "Instakill", el elemento que aumenta la fuerza será la cantidad de rayos, no su alcance
        int cantidadRayos = (5 + rand.nextInt(6)) * (1 + hero.getFuerzaAtaque()); // Crea de 5 a 10 rayos (porque nextInt va hasta n-1) * fuerza del héroe
        for (int i = 0; i < cantidadRayos; i++) {
            // Elección de radio 
            int radioObtenido = (2 + rand.nextInt(9));  // Radio de 2 hasta 10
            
            // Elección de casilla
            Casilla casilla = this.casillasActivas.get(rand.nextInt(casillasActivas.size()));  // Toma una casilla aleatoria de las que están presentes en el arreglo
            casilla.recibirGolpe(casilla.getVida());  // Recibe el mismo daño de su vida ('instakill')
            casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue seleccionada como epicentro de una onda de Poseidon Thunders, recibiendo " + casilla.getVida() + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
            
            for (Casilla c : casillasActivas) {  // Revisamos en las casillas activas
                if (IsCasillaEnRadio(casilla.getX(), casilla.getY(), c.getX(), c.getY(), radioObtenido)) {
                    c.recibirGolpe(c.getVida());  // Recibe el mismo daño de su vida ('instakill')
                    c.getBitacora().add("La casilla (" + c.getX() + ", " + c.getY() + ") fue golpeada por una onda expansiva, recibiendo " + c.getVida() + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
                }
            }
        }
    }
    
    public void eel_attack (Hero hero) {
        // Cantidad de anguilas generadas
        int cantidadAnguilas = (25 + rand.nextInt(76)) * (1 + hero.getFuerzaAtaque());  // Se generan entre 25 y 100 anguilas * fuerza del héroe
        for (int i = 0; i < cantidadAnguilas; i++) {
            // Cantidad de descargas de una anguila
            int cantidadDescargas = 1 + rand.nextInt(10);  // Entre 1 y 10 descargas
       
            // Elección de casilla
            Casilla casilla = this.casillasActivas.get(rand.nextInt(casillasActivas.size()));  // Toma una casilla aleatoria de las que están presentes en el arreglo
            casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue seleccionada por una anguila de Eel Attack");
             
            for (int j = 0; j < cantidadDescargas; j++) {  // For para dar las descargas
                int golpeDescarga = 10;
                casilla.recibirGolpe(golpeDescarga);  // Golpea a la casilla
                casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por una descarga de anguila, recibiendo " + golpeDescarga + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
            }

        }
    }
}