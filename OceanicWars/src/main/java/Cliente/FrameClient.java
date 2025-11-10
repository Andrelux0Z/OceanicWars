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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

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
        // Arreglo con los héroes disponibles (2 por cada arquetipo)
        String[] tiposHeroes = {
                "Goku",
                "Zanka",
                "Alien",
                "Frisk",
                "Omniman",
                "James",
                "Tentaculo",
                "Papaleta",
                "Luffy",
                "Popeye",
                "Forky",
                "Penitente"
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

            // Obtener el color e imagen por defecto según el tipo de héroe seleccionado
            java.awt.Color colorHeroe;
            String imagePath;
            String arquetipoHeroe;
            Hero heroeCreado;
            javax.swing.JLabel labelHeroe;
            javax.swing.JTextPane textoHeroe;

            // Determinar qué label y texto usar según el índice
            if (i == 0) {
                labelHeroe = IconHeroe1;
                textoHeroe = TextoHeroe1;
            } else if (i == 1) {
                labelHeroe = IconHeroe2;
                textoHeroe = TextoHeroe2;
            } else {
                labelHeroe = IconHeroe3;
                textoHeroe = TextoHeroe3;
            }

            switch (tipoSeleccionado) {
                case "Goku":
                    colorHeroe = Goku.COLOR;
                    imagePath = Goku.IMAGE_PATH;
                    heroeCreado = new Goku(colorHeroe, porcentaje, 10, 10, 10);
                    break;
                case "Zanka":
                    colorHeroe = Zanka.COLOR;
                    imagePath = Zanka.IMAGE_PATH;
                    heroeCreado = new Zanka(colorHeroe, porcentaje, 10, 10, 10);
                    break;
                case "Alien":
                    colorHeroe = Alien.COLOR;
                    imagePath = Alien.IMAGE_PATH;
                    heroeCreado = new Alien(colorHeroe, porcentaje, 10, 10, 10);
                    break;
                case "Frisk":
                    colorHeroe = Frisk.COLOR;
                    imagePath = Frisk.IMAGE_PATH;
                    heroeCreado = new Frisk(colorHeroe, porcentaje, 10, 10, 10);
                    break;
                case "Omniman":
                    colorHeroe = Omniman.COLOR;
                    imagePath = Omniman.IMAGE_PATH;
                    heroeCreado = new Omniman(colorHeroe, porcentaje, 10, 10, 10);
                    break;
                case "James":
                    colorHeroe = James.COLOR;
                    imagePath = James.IMAGE_PATH;
                    heroeCreado = new James(colorHeroe, porcentaje, 10, 10, 10);
                    break;
                case "Tentaculo":
                    colorHeroe = Tentaculo.COLOR;
                    imagePath = Tentaculo.IMAGE_PATH;
                    heroeCreado = new Tentaculo(colorHeroe, porcentaje, 10, 10, 10);
                    break;
                case "Papaleta":
                    colorHeroe = Papaleta.COLOR;
                    imagePath = Papaleta.IMAGE_PATH;
                    heroeCreado = new Papaleta(colorHeroe, porcentaje, 10, 10, 10);
                    break;
                case "Luffy":
                    colorHeroe = Luffy.COLOR;
                    imagePath = Luffy.IMAGE_PATH;
                    heroeCreado = new Luffy(colorHeroe, porcentaje, 10, 10, 10);
                    break;
                case "Popeye":
                    colorHeroe = Popeye.COLOR;
                    imagePath = Popeye.IMAGE_PATH;
                    heroeCreado = new Popeye(colorHeroe, porcentaje, 10, 10, 10);
                    break;
                case "Forky":
                    colorHeroe = Forky.COLOR;
                    imagePath = Forky.IMAGE_PATH;
                    heroeCreado = new Forky(colorHeroe, porcentaje, 10, 10, 10);
                    break;
                case "Penitente":
                    colorHeroe = Penitente.COLOR;
                    imagePath = Penitente.IMAGE_PATH;
                    heroeCreado = new Penitente(colorHeroe, porcentaje, 10, 10, 10);
                    break;
                default:
                    colorHeroe = java.awt.Color.BLACK;
                    imagePath = "";
                    heroeCreado = null;
            }

            // Agregar el héroe a la matriz si se creó correctamente
            if (heroeCreado != null) {
                matriz.getHeroes().add(heroeCreado);
                arquetipoHeroe = heroeCreado.getArquetipo();
            } else {
                arquetipoHeroe = "Desconocido";
            }

            // Cargar y asignar la imagen al label
            try {
                java.net.URL imageUrl = getClass().getResource(imagePath);
                if (imageUrl != null) {
                    ImageIcon icon = new ImageIcon(imageUrl);
                    // Escalar la imagen para que se ajuste al label
                    int ancho = 100;
                    int alto = 100;

                    if (labelHeroe.getWidth() > 0) {
                        ancho = labelHeroe.getWidth();
                    }

                    if (labelHeroe.getHeight() > 0) {
                        alto = labelHeroe.getHeight();
                    }

                    java.awt.Image scaledImage = icon.getImage().getScaledInstance(
                            ancho,
                            alto,
                            java.awt.Image.SCALE_SMOOTH);
                    labelHeroe.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("No se encontró la imagen: " + imagePath);
                }
            } catch (Exception e) {
                System.err.println("Error al cargar imagen para héroe #" + (i + 1) + ": " + e.getMessage());
            }

            // Llenar el TextPane con la información del héroe usando el método con colores
            llenarInfoHeroe(textoHeroe, porcentaje, tipoSeleccionado, arquetipoHeroe, colorHeroe);
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

    private void llenarInfoHeroe(javax.swing.JTextPane textPane, int porcentaje, String nombreHeroe,
            String arquetipoHeroe, java.awt.Color colorHeroe) {
        StyledDocument doc = textPane.getStyledDocument();

        try {
            // Estilo normal (negro)
            SimpleAttributeSet estiloNormal = new SimpleAttributeSet();
            StyleConstants.setForeground(estiloNormal, java.awt.Color.BLACK);

            // Estilo para el color del héroe
            SimpleAttributeSet estiloHeroe = new SimpleAttributeSet();
            StyleConstants.setForeground(estiloHeroe, colorHeroe);
            StyleConstants.setBold(estiloHeroe, true);

            // Estilo para el arquetipo (gris, cursiva)
            SimpleAttributeSet estiloArquetipo = new SimpleAttributeSet();
            StyleConstants.setForeground(estiloArquetipo, java.awt.Color.GRAY);
            StyleConstants.setItalic(estiloArquetipo, true);

            // Insertar el porcentaje (negro)
            doc.insertString(doc.getLength(), porcentaje + "%\n", estiloNormal);

            // Insertar el nombre del héroe (color)
            doc.insertString(doc.getLength(), nombreHeroe + "\n", estiloHeroe);

            // Insertar el arquetipo (gris, cursiva)
            doc.insertString(doc.getLength(), arquetipoHeroe + "\n", estiloArquetipo);

            // Insertar el resto de la información (negro)
            doc.insertString(doc.getLength(), "Fuerza: 10\n", estiloNormal);
            doc.insertString(doc.getLength(), "Resistencia: 10\n", estiloNormal);
            doc.insertString(doc.getLength(), "Sanidad: 10", estiloNormal);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txaMessages = new javax.swing.JTextArea();
        txfCommand = new javax.swing.JTextField();
        pnlLuchadores = new javax.swing.JPanel();
        IconHeroe3 = new javax.swing.JLabel();
        IconHeroe1 = new javax.swing.JLabel();
        IconHeroe2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TextoHeroe1 = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        TextoHeroe2 = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        TextoHeroe3 = new javax.swing.JTextPane();
        pnlMatriz = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaLastMove = new javax.swing.JTextArea();
        btnSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txaMessages.setColumns(20);
        txaMessages.setRows(5);
        txaMessages.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txaMessages.setFocusable(false);
        txaMessages.setOpaque(false);
        jScrollPane1.setViewportView(txaMessages);

        pnlLuchadores.setBackground(new java.awt.Color(153, 255, 255));
        pnlLuchadores.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlLuchadores.setForeground(new java.awt.Color(102, 102, 102));
        pnlLuchadores.setOpaque(false);

        IconHeroe3.setBackground(new java.awt.Color(51, 255, 153));
        IconHeroe3.setForeground(new java.awt.Color(255, 51, 51));

        IconHeroe1.setBackground(new java.awt.Color(51, 255, 153));
        IconHeroe1.setForeground(new java.awt.Color(255, 51, 51));

        IconHeroe2.setBackground(new java.awt.Color(51, 255, 153));
        IconHeroe2.setForeground(new java.awt.Color(255, 51, 51));

        TextoHeroe1.setCaretColor(new java.awt.Color(204, 255, 204));
        TextoHeroe1.setDisabledTextColor(new java.awt.Color(255, 51, 0));
        TextoHeroe1.setFocusable(false);
        TextoHeroe1.setOpaque(false);
        TextoHeroe1.setSelectedTextColor(new java.awt.Color(0, 102, 102));
        TextoHeroe1.setSelectionColor(new java.awt.Color(102, 102, 255));
        jScrollPane3.setViewportView(TextoHeroe1);

        TextoHeroe2.setFocusable(false);
        TextoHeroe2.setOpaque(false);
        jScrollPane4.setViewportView(TextoHeroe2);

        TextoHeroe3.setDisabledTextColor(new java.awt.Color(153, 255, 153));
        TextoHeroe3.setFocusable(false);
        TextoHeroe3.setOpaque(false);
        jScrollPane5.setViewportView(TextoHeroe3);

        javax.swing.GroupLayout pnlLuchadoresLayout = new javax.swing.GroupLayout(pnlLuchadores);
        pnlLuchadores.setLayout(pnlLuchadoresLayout);
        pnlLuchadoresLayout.setHorizontalGroup(
                pnlLuchadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlLuchadoresLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlLuchadoresLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlLuchadoresLayout.createSequentialGroup()
                                                .addComponent(IconHeroe1, javax.swing.GroupLayout.PREFERRED_SIZE, 131,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 126,
                                                        Short.MAX_VALUE))
                                        .addGroup(pnlLuchadoresLayout.createSequentialGroup()
                                                .addComponent(IconHeroe3, javax.swing.GroupLayout.PREFERRED_SIZE, 131,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane5))
                                        .addGroup(pnlLuchadoresLayout.createSequentialGroup()
                                                .addComponent(IconHeroe2, javax.swing.GroupLayout.PREFERRED_SIZE, 131,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane4)))
                                .addContainerGap()));
        pnlLuchadoresLayout.setVerticalGroup(
                pnlLuchadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                pnlLuchadoresLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(pnlLuchadoresLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jScrollPane3)
                                                .addComponent(IconHeroe1, javax.swing.GroupLayout.DEFAULT_SIZE, 131,
                                                        Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlLuchadoresLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jScrollPane4)
                                                .addComponent(IconHeroe2, javax.swing.GroupLayout.DEFAULT_SIZE, 131,
                                                        Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlLuchadoresLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(IconHeroe3, javax.swing.GroupLayout.DEFAULT_SIZE, 131,
                                                        Short.MAX_VALUE)
                                                .addComponent(jScrollPane5))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

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
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31,
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
    private javax.swing.JLabel IconHeroe1;
    private javax.swing.JLabel IconHeroe2;
    private javax.swing.JLabel IconHeroe3;
    private javax.swing.JTextPane TextoHeroe1;
    private javax.swing.JTextPane TextoHeroe2;
    private javax.swing.JTextPane TextoHeroe3;
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
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