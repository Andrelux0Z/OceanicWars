/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;
import java.awt.Color;
import java.awt.Graphics;
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
    
    // Matriz que almacena casillas
    private Casilla[][] matriz;
    
    private FrameClient refCliente;  // Referencia al Cliente para acceder a sus espacios
    
    // Constructor
    public Matriz (int cantidadFilas, int cantidadColumnas, FrameClient refCliente) {
        this.refCliente = refCliente;
        this.cantidadFilas = cantidadFilas;
        this.cantidadColumnas = cantidadColumnas;
        this.sizeAlto = this.refCliente.getPnlMatriz().getHeight() / this.cantidadFilas;  // Conseguimos la altura del componente pnlMatriz para crear casillas con la medida correcta
        this.sizeLargo = this.refCliente.getPnlMatriz().getWidth() / this.cantidadColumnas;  // Conseguimos el largo del componente pnlMatriz para crear casillas con la medida correta
        this.matriz = new Casilla[this.cantidadFilas][this.cantidadColumnas];  // Creamos una matriz del tamaño desado
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
                Casilla c = matriz[i][j];
                g.setColor(c.getColor());
                g.fillRect(j * sizeLargo, i * sizeAlto, sizeLargo, sizeAlto);
                g.setColor(Color.BLACK);
                g.drawRect(j * sizeLargo, i * sizeAlto, sizeLargo, sizeAlto);
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
}
