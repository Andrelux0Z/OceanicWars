/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 *
 * @author kokoju
 */
public class Typewritter {  // Función encargada de mostrar el texto de manera gradual, en lugar de imprimirlo en su totalidad
    public static void typeText(JTextArea txaLabel, String texto, int delay) {  // Recibe un TextArea, además de el texto y el 'delay' que queremos
        txaLabel.setText("");  // Quita el texto anterior
        Timer timer = new Timer(delay, null);  // Establece un Timer con cierto delay, el cuál da hace un tick cada 'delay' segundos

        timer.addActionListener(new ActionListener() {  // Si el timer hace un tick
            int i = 0;  // Variable 'i' que lleva el índice del texto revisado: no se reinicia cada tick, solo se crea una vez

            @Override
            public void actionPerformed(ActionEvent e) {  // Esta función si se repite cada tick, y imprime una letra más mientras siga habiendo
                if (i < texto.length()) {
                    txaLabel.setText(txaLabel.getText() + texto.charAt(i));  // Escribe en el texto que ya había el nuevo char 
                    i++;
                } else {  // Si no quedan más letras, el timer se detiene y la función termina
                    timer.stop();
                }
            }
        });

        timer.start();  // Inicio del timer
    }
}
