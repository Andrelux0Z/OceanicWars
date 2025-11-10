/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Cliente;

import Hero.*;
import Models.Command;
import Models.CommandAttack;
import Models.CommandFactory;
import Models.CommandMessage;
import Models.CommandUtil;
import java.awt.BorderLayout;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author diego
 */
public class FrameClient extends javax.swing.JFrame {

    private Client client;
    private Matriz matriz;

    /**
     * Creates new form FrameClient
     */
    public FrameClient() {
        initComponents();

        // Solicitar el nombre hasta que sea válido
        String name = null;
        while (name == null || name.trim().isEmpty()) {
            name = JOptionPane.showInputDialog(this, "Ingrese su nombre");

            // Si el usuario cierra la ventana, cerrar la aplicación
            if (name == null) {
                System.exit(0);
            }

            // Si el nombre está vacío, mostrar advertencia y volver a preguntar
            if (name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "El nombre no puede estar vacío.\nPor favor, ingresa un nombre válido.",
                        "Nombre Requerido",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        this.setTitle("Cliente de " + name); // Se pone el nombre introducido como título de la ventana
        client = new Client(this, name); // Creamos un nuevo cliente c
        matriz = new Matriz(20, 30, this); // Creación de la matriz (20 filas, 30 columnas y la referencia al frame del
                                           // cliente)

        // Si inicializarHeroes retorna false, significa que el usuario canceló
        if (!inicializarHeroes()) {
            System.exit(0);
        }

        actualizarPnlMatriz(); // Se actualiza el pnl
        pnlMatriz.add(matriz);

    }

    private boolean inicializarHeroes() {
        // Arreglo con los tipos de héroes disponibles
        String[] tiposHeroes = {
                "Thunders Under The Sea",
                "Fish Telepathy",
                "Poseidon Trident",
                "Release The Kraken",
                "Waves Control",
                "Undersea Fire"
        };

        int porcentajeTotal = 0;

        // Pedir datos para cada uno de los 3 héroes
        for (int i = 0; i != 3; i++) {
            // Seleccionar tipo de héroe
            String tipoSeleccionado = (String) JOptionPane.showInputDialog(
                    this,
                    "Selecciona el tipo de héroe #" + (i + 1) + ":",
                    "Selección de Héroe",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tiposHeroes,
                    tiposHeroes[i % tiposHeroes.length]);

            // Si el usuario cierra la ventana, cancelar todo
            if (tipoSeleccionado == null) {
                return false;
            }

            int porcentaje = 0;
            boolean porcentajeValido = false;

            // Si es el último héroe, asignar automáticamente el porcentaje restante
            if (i == 2) {
                porcentaje = 100 - porcentajeTotal;
                JOptionPane.showMessageDialog(this,
                        "Al héroe #" + (i + 1) + " se le asigna automáticamente el porcentaje restante: " + porcentaje
                                + "%");
                porcentajeValido = true;
            }

            // Para los primeros héroes, solicitar el porcentaje con validación
            while (!porcentajeValido) {
                int porcentajeRestante = 100 - porcentajeTotal;
                int porcentajeMaximo = porcentajeRestante - (3 - i - 1);

                String mensajePorcentaje = "¿Qué porcentaje del tablero controlará este héroe?\n";
                mensajePorcentaje += "Rango válido: " + 1 + "% - " + porcentajeMaximo + "%\n";
                mensajePorcentaje += "Porcentaje usado: " + porcentajeTotal + "% | Restante: " + porcentajeRestante
                        + "%";

                String porcentajeStr = JOptionPane.showInputDialog(this, mensajePorcentaje);

                try {
                    porcentaje = Integer.parseInt(porcentajeStr.trim());

                    // Validar que esté en el rango permitido
                    if (porcentaje < 1 || porcentaje > porcentajeMaximo) {
                        JOptionPane.showMessageDialog(this,
                                "Error: El porcentaje debe estar entre " + 1 + "% y " + porcentajeMaximo + "%.\n" +
                                        "Por favor, intenta de nuevo.",
                                "Porcentaje Inválido",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        porcentajeValido = true;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this,
                            "Error: Debes ingresar un número entero válido.\n" +
                                    "Por favor, intenta de nuevo.",
                            "Entrada Inválida",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            porcentajeTotal += porcentaje;

            // Obtener el color por defecto según el tipo de héroe seleccionado
            java.awt.Color colorHeroe;
            switch (tipoSeleccionado) {
                case "Thunders Under The Sea":
                    colorHeroe = ThundersUnderTheSea.COLOR_DEFAULT;
                    matriz.getHeroes().add(new ThundersUnderTheSea("", colorHeroe, porcentaje, 10, 10, 10));
                    break;
                case "Fish Telepathy":
                    colorHeroe = FishTelepathy.COLOR_DEFAULT;
                    matriz.getHeroes().add(new FishTelepathy("", colorHeroe, porcentaje, 10, 10, 10));
                    break;
                case "Poseidon Trident":
                    colorHeroe = PoseidonTrident.COLOR_DEFAULT;
                    matriz.getHeroes().add(new PoseidonTrident("", colorHeroe, porcentaje, 10, 10, 10));
                    break;
                case "Release The Kraken":
                    colorHeroe = ReleaseTheKrakenHero.COLOR_DEFAULT;
                    matriz.getHeroes().add(new ReleaseTheKrakenHero("", colorHeroe, porcentaje, 10, 10, 10));
                    break;
                case "Waves Control":
                    colorHeroe = WavesControl.COLOR_DEFAULT;
                    matriz.getHeroes().add(new WavesControl("", colorHeroe, porcentaje, 10, 10, 10));
                    break;
                case "Undersea Fire":
                    colorHeroe = UnderseaFire.COLOR_DEFAULT;
                    matriz.getHeroes().add(new UnderseaFire("", colorHeroe, porcentaje, 10, 10, 10));
                    break;
            }
        }

        // Crear la matriz con los héroes configurados
        matriz.crearMatriz();
        matriz.repaint();

        // Retornar true indicando que la configuración se completó exitosamente
        return true;
    }

    public void writeMessage(String msg) {
        txaMessages.append(msg + "\n");
    }

    public void actualizarPnlMatriz() {
        System.out.println("Actualizando panel matriz...");
        System.out.println("Matriz visible: " + matriz.isVisible());
        System.out.println("Matriz tamaño: " + matriz.getWidth() + "x" + matriz.getHeight());
        System.out.println("Matriz preferred size: " + matriz.getPreferredSize());

        pnlMatriz.revalidate();
        pnlMatriz.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txaMessages = new javax.swing.JTextArea();
        txfCommand = new javax.swing.JTextField();
        pnlLuchadores = new javax.swing.JPanel();
        pnlMatriz = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaLastMove = new javax.swing.JTextArea();
        btnSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txaMessages.setColumns(20);
        txaMessages.setRows(5);
        txaMessages.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txaMessages.setFocusable(false);
        jScrollPane1.setViewportView(txaMessages);

        javax.swing.GroupLayout pnlLuchadoresLayout = new javax.swing.GroupLayout(pnlLuchadores);
        pnlLuchadores.setLayout(pnlLuchadoresLayout);
        pnlLuchadoresLayout.setHorizontalGroup(
                pnlLuchadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 275, Short.MAX_VALUE));
        pnlLuchadoresLayout.setVerticalGroup(
                pnlLuchadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE));

        pnlMatriz.setForeground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout pnlMatrizLayout = new javax.swing.GroupLayout(pnlMatriz);
        pnlMatriz.setLayout(pnlMatrizLayout);
        pnlMatrizLayout.setHorizontalGroup(
                pnlMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 780, Short.MAX_VALUE));
        pnlMatrizLayout.setVerticalGroup(
                pnlMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 520, Short.MAX_VALUE));

        txaLastMove.setColumns(20);
        txaLastMove.setRows(5);
        txaLastMove.setFocusable(false);
        jScrollPane2.setViewportView(txaLastMove);

        btnSend.setBackground(new java.awt.Color(51, 255, 51));
        btnSend.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnSend.setForeground(new java.awt.Color(255, 255, 255));
        btnSend.setText("SEND!");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(jScrollPane1,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 250,
                                                                Short.MAX_VALUE)
                                                        .addComponent(jScrollPane2))
                                                .addGap(31, 31, 31)
                                                .addComponent(pnlMatriz, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35,
                                                        Short.MAX_VALUE)
                                                .addComponent(pnlLuchadores, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(13, 13, 13))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(txfCommand)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnSend)
                                                .addGap(33, 33, 33)))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(pnlLuchadores,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jScrollPane1,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 380,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane2,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 214,
                                                                        Short.MAX_VALUE)))
                                                .addGap(18, 18, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                layout.createSequentialGroup()
                                                        .addContainerGap(61, Short.MAX_VALUE)
                                                        .addComponent(pnlMatriz, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(48, 48, 48)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txfCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSendActionPerformed
        // obnter string del txf y quitar espacio
        String msg = txfCommand.getText().trim();
        if (msg.length() > 0) {
            String args[] = CommandUtil.tokenizerArgs(msg);
            if (args.length > 0) {
                Command comando = CommandFactory.getCommand(args);
                if (comando != null) {
                    try {   
                        client.objectSender.writeObject(comando);
                    } catch (IOException ex) {

                    }
                } else {
                    this.writeMessage("Error: comando desconocido");
                }

            }
        }
    }// GEN-LAST:event_btnSendActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlLuchadores;
    private javax.swing.JPanel pnlMatriz;
    private javax.swing.JTextArea txaLastMove;
    private javax.swing.JTextArea txaMessages;
    private javax.swing.JTextField txfCommand;
    // End of variables declaration//GEN-END:variables

    // Getters
    public Client getClient() {
        return client;
    }

    public JButton getBtnSend() {
        return btnSend;
    }

    public JPanel getPnlLuchadores() {
        return pnlLuchadores;
    }

    public JPanel getPnlMatriz() {
        return pnlMatriz;
    }

    public JTextArea getTxaLastMove() {
        return txaLastMove;
    }

    public JTextArea getTxaMessages() {
        return txaMessages;
    }

    public JTextField getTxfCommand() {
        return txfCommand;
    }

    public Matriz getMatriz() {
        return matriz;
    }
    

}