/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Cliente;

import Hero.*;
import Models.Command;
import Models.CommandFactory;
import Models.CommandUtil;
import java.io.IOException;
import java.util.ArrayList;
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

    static final int DELAY = 5; // Delay para mostrar el texto en milisegundos

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
        // Definir los arquetipos disponibles
        String[] arquetipos = {
                "Thunders Under The Sea",
                "Fish Telepathy",
                "Release The Kraken",
                "Waves Control",
                "The Trident",
                "Undersea Volcanoes"
        };

        // Obtener todas las apariencias disponibles de HeroList
        String[] aparienciasDisponibles = HeroList.getAllHeroNames();

        // Lista para rastrear las apariencias ya seleccionadas
        ArrayList<String> aparienciasSeleccionadas = new ArrayList<>();

        int porcentajeTotal = 0;

        // Estadísticas disponibles
        int disponibles100 = 3;
        int disponibles75 = 3;
        int disponibles50 = 3;

        // Guardar las estadísticas de cada héroe
        ArrayList<int[]> estadisticasHeroes = new ArrayList<>();

        // Pedir datos para cada uno de los 3 héroes
        for (int i = 0; i != 3; i++) {
            // Primero, seleccionar el arquetipo
            String arquetipoSeleccionado = (String) JOptionPane.showInputDialog(
                    this,
                    "Selecciona el arquetipo del héroe #" + (i + 1) + ":",
                    "Selección de Arquetipo",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    arquetipos,
                    arquetipos[i % arquetipos.length]);

            // Si el usuario cierra la ventana, cancelar todo
            if (arquetipoSeleccionado == null) {
                return false;
            }

            // Filtrar las apariencias que aún no han sido seleccionadas
            ArrayList<String> aparienciasRestantes = new ArrayList<>();
            for (String apariencia : aparienciasDisponibles) {
                if (!aparienciasSeleccionadas.contains(apariencia)) {
                    aparienciasRestantes.add(apariencia);
                }
            }

            // Ahora, seleccionar la apariencia del héroe
            String aparienciaSeleccionada = (String) JOptionPane.showInputDialog(
                    this,
                    "Selecciona la apariencia del héroe #" + (i + 1) + "\nArquetipo: " + arquetipoSeleccionado,
                    "Selección de Apariencia",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    aparienciasRestantes.toArray(new String[0]),
                    aparienciasRestantes.get(0));

            // Si el usuario cierra la ventana, cancelar todo
            if (aparienciaSeleccionada == null) {
                return false;
            }

            // Agregar la apariencia seleccionada a la lista para evitar repeticiones
            aparienciasSeleccionadas.add(aparienciaSeleccionada);

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

            // Seleccionar las estadísticas del héroe (Sanidad, Fuerza, Resistencia)
            String[] nombresEstadisticas = { "Sanidad", "Fuerza", "Resistencia" };
            int[] estadisticasHeroe = new int[3];

            for (int j = 0; j < 3; j++) {
                // Crear botones personalizados para cada opción disponible
                Object[] opciones;
                ArrayList<Integer> valoresDisponibles = new ArrayList<>();

                if (disponibles100 > 0)
                    valoresDisponibles.add(100);
                if (disponibles75 > 0)
                    valoresDisponibles.add(75);
                if (disponibles50 > 0)
                    valoresDisponibles.add(50);

                // Crear las opciones de botones con el conteo
                opciones = new Object[valoresDisponibles.size()];
                for (int k = 0; k < valoresDisponibles.size(); k++) {
                    int valor = valoresDisponibles.get(k);
                    int count;
                    String textoDisponible;

                    if (valor == 100) {
                        count = disponibles100;
                        if (disponibles100 > 1) {
                            textoDisponible = "s";
                        } else {
                            textoDisponible = "";
                        }
                    } else if (valor == 75) {
                        count = disponibles75;
                        if (disponibles75 > 1) {
                            textoDisponible = "s";
                        } else {
                            textoDisponible = "";
                        }
                    } else {
                        count = disponibles50;
                        if (disponibles50 > 1) {
                            textoDisponible = "s";
                        } else {
                            textoDisponible = "";
                        }
                    }

                    opciones[k] = valor + " (" + count + " disponible" + textoDisponible + ")";
                }

                int seleccion = JOptionPane.showOptionDialog(
                        this,
                        "Héroe #" + (i + 1) + " - " + aparienciaSeleccionada + " (" + arquetipoSeleccionado + ")\n" +
                                "Selecciona el valor para " + nombresEstadisticas[j] + ":\n\n" +
                                "Estadísticas disponibles:\n" +
                                "  100: " + disponibles100 + " disponible(s)\n" +
                                "  75: " + disponibles75 + " disponible(s)\n" +
                                "  50: " + disponibles50 + " disponible(s)",
                        "Selección de Estadísticas",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]);

                // Si el usuario cierra la ventana
                if (seleccion == JOptionPane.CLOSED_OPTION) {
                    return false;
                }

                int valorEstadistica = valoresDisponibles.get(seleccion);
                estadisticasHeroe[j] = valorEstadistica;

                // Decrementar el contador correspondiente
                if (valorEstadistica == 100) {
                    disponibles100--;
                } else if (valorEstadistica == 75) {
                    disponibles75--;
                } else if (valorEstadistica == 50) {
                    disponibles50--;
                }
            }

            // Guardar las estadísticas del héroe
            estadisticasHeroes.add(estadisticasHeroe);

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

            // Obtener la apariencia del héroe desde HeroList
            HeroList.AparienciaHeroe apariencia = HeroList.getApariencia(aparienciaSeleccionada);

            if (apariencia != null) {
                colorHeroe = apariencia.getColor();
                imagePath = apariencia.getImagePath();

                // Crear el héroe usando HeroList con el arquetipo seleccionado
                heroeCreado = HeroList.createHero(
                        arquetipoSeleccionado,
                        aparienciaSeleccionada,
                        imagePath,
                        colorHeroe,
                        porcentaje,
                        estadisticasHeroe[0],
                        estadisticasHeroe[1],
                        estadisticasHeroe[2]);
            } else {
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
            llenarInfoHeroe(textoHeroe, porcentaje, aparienciaSeleccionada, arquetipoHeroe, colorHeroe,
                    estadisticasHeroe[0], estadisticasHeroe[1], estadisticasHeroe[2]);
        }

        // Crear la matriz con los héroes configurados y el jugador respectivo
        this.client.setJugador(new Jugador(this.client.name, matriz, matriz.getHeroes(), new ArrayList<String>()));
        matriz.crearMatriz();
        matriz.repaint();

        // Retornar true indicando que la configuración se completó exitosamente
        return true;
    }

    public void writeMessage(String msg) {
        txaMessages.append(msg + "\n");
    }

    private void llenarInfoHeroe(javax.swing.JTextPane textPane, int porcentaje, String nombreHeroe,
            String arquetipoHeroe, java.awt.Color colorHeroe, int sanidad, int fuerza, int resistencia) {
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

            // Insertar las estadísticas reales del héroe (negro)
            doc.insertString(doc.getLength(), "Sanidad: " + sanidad + "\n", estiloNormal);
            doc.insertString(doc.getLength(), "Fuerza: " + fuerza + "\n", estiloNormal);
            doc.insertString(doc.getLength(), "Resistencia: " + resistencia, estiloNormal);
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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txaMessages = new javax.swing.JTextArea();
        txfCommand = new javax.swing.JTextField();
        txfCommand.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txfCommandKeyPressed(evt);
            }
        });
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
        pnlResumen = new javax.swing.JPanel();
        destruidas = new javax.swing.JLabel();
        vida = new javax.swing.JLabel();
        heroe2 = new javax.swing.JLabel();
        heroe1 = new javax.swing.JLabel();
        heroe3 = new javax.swing.JLabel();
        labelfondo = new javax.swing.JLabel();

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
        pnlMatriz.setOpaque(false);

        javax.swing.GroupLayout pnlMatrizLayout = new javax.swing.GroupLayout(pnlMatriz);
        pnlMatriz.setLayout(pnlMatrizLayout);
        pnlMatrizLayout.setHorizontalGroup(
                pnlMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 818, Short.MAX_VALUE));
        pnlMatrizLayout.setVerticalGroup(
                pnlMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 470, Short.MAX_VALUE));

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

        pnlResumen.setBackground(new java.awt.Color(153, 255, 255));
        pnlResumen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlResumen.setForeground(new java.awt.Color(102, 102, 102));
        pnlResumen.setOpaque(false);

        destruidas.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        destruidas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        destruidas.setText("Casillas destruídas");

        vida.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        vida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vida.setText("Vida");

        heroe2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        heroe2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        heroe2.setText("hola");

        heroe1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        heroe1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        heroe1.setText("hola");

        heroe3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        heroe3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        heroe3.setText("hola");

        javax.swing.GroupLayout pnlResumenLayout = new javax.swing.GroupLayout(pnlResumen);
        pnlResumen.setLayout(pnlResumenLayout);
        pnlResumenLayout.setHorizontalGroup(
                pnlResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlResumenLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlResumenLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                pnlResumenLayout.createSequentialGroup()
                                                        .addComponent(heroe1, javax.swing.GroupLayout.DEFAULT_SIZE, 183,
                                                                Short.MAX_VALUE)
                                                        .addGap(126, 126, 126)
                                                        .addComponent(heroe2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(100, 100, 100)
                                                        .addComponent(heroe3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(25, 25, 25))
                                        .addGroup(pnlResumenLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(vida, javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(60, 60, 60)
                                                .addComponent(destruidas, javax.swing.GroupLayout.PREFERRED_SIZE, 327,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(85, 85, 85)))));
        pnlResumenLayout.setVerticalGroup(
                pnlResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlResumenLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        pnlResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(vida)
                                                .addComponent(destruidas))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(
                                        pnlResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(heroe2, javax.swing.GroupLayout.Alignment.LEADING,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                                                .addComponent(heroe1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(heroe3, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap()));

        labelfondo.setText("jLabel1");

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
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(pnlMatriz, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(pnlResumen, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(pnlLuchadores, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(13, 13, 13))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(txfCommand)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnSend)
                                                .addGap(33, 33, 33))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelfondo, javax.swing.GroupLayout.Alignment.TRAILING,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, 1390, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(pnlMatriz, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(pnlResumen, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(pnlLuchadores, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane2)))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txfCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelfondo, javax.swing.GroupLayout.Alignment.TRAILING,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)));

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
                        txfCommand.setText(""); // Limpiar campo de texto después de enviar
                    } catch (IOException ex) {

                    }
                } else {
                    this.writeMessage("Error: comando desconocido");
                }

            }
        }
    }// GEN-LAST:event_btnSendActionPerformed

    private void txfCommandKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            btnSend.doClick(); // Simular clic en botón SEND
        }
    }

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
    private javax.swing.JLabel destruidas;
    private javax.swing.JLabel heroe1;
    private javax.swing.JLabel heroe2;
    private javax.swing.JLabel heroe3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelfondo;
    private javax.swing.JPanel pnlLuchadores;
    private javax.swing.JPanel pnlMatriz;
    private javax.swing.JPanel pnlResumen;
    private javax.swing.JTextArea txaLastMove;
    private javax.swing.JTextArea txaMessages;
    private javax.swing.JTextField txfCommand;
    private javax.swing.JLabel vida;
    // End of variables declaration//GEN-END:variables

    // Getters
    public int getDELAY() {
        return DELAY;
    }

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

    // Actualiza los labels del panel de resumen con las estadísticas actuales
    public void actualizarPanelResumen() {
        if (matriz == null)
            return;

        // Actualizar porcentaje de vida
        double porcentajeVida = matriz.calcularPorcentajeVida();
        vida.setText(String.format("Vida: %.1f%%", porcentajeVida));

        // Actualizar casillas destruidas
        int destruidas = matriz.calcularCasillasDestruidas();
        this.destruidas.setText("Casillas destruídas: " + destruidas);

        // Actualizar información de cada héroe (si existen)
        ArrayList<Hero> heroes = matriz.getHeroes();
        if (heroes.size() > 0) {
            Hero heroe = heroes.get(0);
            int casillasVivas = matriz.calcularCasillasVivasHeroe(heroe);
            int casillasOriginales = heroe.getCasillasEnPosesion().size();
            String colorHex = String.format("#%02x%02x%02x",
                    heroe.getColor().getRed(),
                    heroe.getColor().getGreen(),
                    heroe.getColor().getBlue());
            heroe1.setText("<html><div style='text-align:center'>" +
                    "<span style='color:" + colorHex + "'>" + heroe.getNombre() + "</span><br>" +
                    "<span style='font-size:11px'>" + casillasVivas + " de " + casillasOriginales
                    + " casillas activas</span>" +
                    "</div></html>");
        }

        if (heroes.size() > 1) {
            Hero heroe = heroes.get(1);
            int casillasVivas = matriz.calcularCasillasVivasHeroe(heroe);
            int casillasOriginales = heroe.getCasillasEnPosesion().size();
            String colorHex = String.format("#%02x%02x%02x",
                    heroe.getColor().getRed(),
                    heroe.getColor().getGreen(),
                    heroe.getColor().getBlue());
            heroe2.setText("<html><div style='text-align:center'>" +
                    "<span style='color:" + colorHex + "'>" + heroe.getNombre() + "</span><br>" +
                    "<span style='font-size:11px'>" + casillasVivas + " de " + casillasOriginales
                    + " casillas activas</span>" +
                    "</div></html>");
        }

        if (heroes.size() > 2) {
            Hero heroe = heroes.get(2);
            int casillasVivas = matriz.calcularCasillasVivasHeroe(heroe);
            int casillasOriginales = heroe.getCasillasEnPosesion().size();
            String colorHex = String.format("#%02x%02x%02x",
                    heroe.getColor().getRed(),
                    heroe.getColor().getGreen(),
                    heroe.getColor().getBlue());
            heroe3.setText("<html><div style='text-align:center'>" +
                    "<span style='color:" + colorHex + "'>" + heroe.getNombre() + "</span><br>" +
                    "<span style='font-size:11px'>" + casillasVivas + " de " + casillasOriginales
                    + " casillas activas</span>" +
                    "</div></html>");
        }
    }

}