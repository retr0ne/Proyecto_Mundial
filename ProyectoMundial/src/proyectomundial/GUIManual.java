package proyectomundial;
//Hecho por Dylan Vargas y Javier Hernandez

import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.text.AttributedCharacterIterator;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import proyectomundial.DAO.AuditoriaDAO;
import proyectomundial.DAO.DashboardSelDAO;
import proyectomundial.DAO.ResultadosDAO;
import proyectomundial.DAO.SeleccionDAO;
import proyectomundial.model.Seleccion;

public class GUIManual extends JFrame {

    SeleccionDAO seleccionDAO = new SeleccionDAO();
    AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
    ResultadosDAO resultadosDAO = new ResultadosDAO();
    DashboardSelDAO dashboardsel = new DashboardSelDAO();
    // Matrix que permite almancenar la información de las selecciones futbol cargadas
    public String[][] selecciones = null;

    // Matriz que permite almacenar los resultados de los partidos cargardos
    public String[][] resultados = null;

    //Matriz que permite almacenar los datos de auditoria
    public String[][] auditoria = null;

    //Matriz que almacena los datos del partido con mas goles
    public String[][] PartidoConMasGoles = null;
    public String[][] PartidoConMenosGoles = null;

    //Matriz que almacena los datos del equipo con  mas goles
    public String[][] EquiposConMasGoles = null;

    // Elementos de bara Lateral
    private JPanel jPanelLeft;

    private JPanel jPanelIconFIFA;
    private JLabel iconFIFA;

    // Elementos de opciones de Menú
    private JPanel jPanelMenu;

    private JPanel jPanelMenuHome;
    private JLabel btnHome;

    private JPanel jPanelMenuSelecciones;
    private JLabel btnSelecciones;

    private JPanel jPanelMenuResultados;
    private JLabel btnResultados;

    private JPanel jPanelMenuDashboardSel;
    private JLabel btnDashboardSel;

    private JPanel jPanelMenuDashboardRes;
    private JLabel btnDashboardRes;

    private JPanel jPanelMenuAuditoria;
    private JLabel btnAuditoria;

    // Elementos de panel de contenido
    private JPanel jPanelRight;
    private JPanel jPanelLabelTop;
    private JLabel jLabelTop;

    private JPanel jPanelMain;

    private JPanel JPanelreferencia;

    public GUIManual() {

        // Se inician los componentes gráficos
        initComponents();

        // Se configuran propiedades de nuestra Ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Se llama la función home para que al momento de iniciar la aplicacoón, por defecto se muestre el home
        accionHome();

    }

    private void initComponents() {

        // Inicializamos componentes del Menu Lateral
        jPanelLeft = new JPanel();

        jPanelIconFIFA = new JPanel();
        iconFIFA = new JLabel();
        jPanelMenu = new JPanel();

        jPanelMenuHome = new JPanel();
        btnHome = new JLabel();

        jPanelMenuSelecciones = new JPanel();
        btnSelecciones = new JLabel();

        jPanelMenuResultados = new JPanel();
        btnResultados = new JLabel();

        jPanelMenuDashboardSel = new JPanel();
        btnDashboardSel = new JLabel();

        jPanelMenuDashboardRes = new JPanel();
        btnDashboardRes = new JLabel();

        jPanelMenuAuditoria = new JPanel();
        btnAuditoria = new JLabel();

        JPanelreferencia = new JPanel();

        // Pinta el logo de la aplicación
        pintarLogo();

        // Pinta la opción de menú del Home
        pintarMenuHome();

        // Pinta la opción de Menú de las Selecciones
        pintarMenuSelecciones();

        // Pinta la opción de Menú de los resultados
        pintarMenuResultados();

        // Pinta la opción de Menú del dashboard de equipo
        pintarMenuDashboardSel();

        // Pinta la opción de Menú del dahboard de resultados
        pintarMenuDashboardRes();

        // Pinta la opción del menu de la auditoria
        pintarMenuAuditoria();
        // Pinta y ajuste diseño del contenedor del panel izquierdo
        pintarPanelIzquierdo();

        // Inicializa los componentes del panel derecho de los contenidos
        jPanelRight = new JPanel();
        jPanelLabelTop = new JPanel();
        jPanelMain = new JPanel();

        // Pinta la barra superrior de color azul claro, del panel de contenido
        pintarLabelTop();

        // Pinta y ajusta diseño del contenedor de contenidos
        pintarPanelDerecho();

        setTitle("Mundial");
        pack();
        setVisible(true);
    }

    private void pintarLogo() {
        jPanelIconFIFA.add(iconFIFA);
        jPanelIconFIFA.setOpaque(false);
        jPanelIconFIFA.setPreferredSize((new java.awt.Dimension(220, 80)));
        jPanelIconFIFA.setMaximumSize(jPanelIconFIFA.getPreferredSize());
        iconFIFA.setIcon(new ImageIcon(getClass().getResource("/resources/Easports_fifa_logo.svg.png")));
        jPanelLeft.add(jPanelIconFIFA, BorderLayout.LINE_START);

    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación del HOME Define estilos, etiquetas, iconos que
     * decoran la opción del Menú. Esta opción de Menu permite mostrar la página
     * de bienvenida de la aplicación
     */
    private void pintarMenuHome() {
        btnHome.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioHome = new JLabel();
        jPanelMenuHome.setBackground(new java.awt.Color(183, 16, 22));
        jPanelMenuHome.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuHome.setLayout(new BorderLayout(15, 0));
        jPanelMenuHome.add(vacioHome, BorderLayout.WEST);
        jPanelMenuHome.add(btnHome, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuHome);

        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Home");
                boolean actu = auditoriaDAO.update("Home");
                accionHome();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hacer click sobre la opción de
     * navegación Home Permite modificar la etiqueta de Navegación en Home,
     * remover los elementos que hay en el panel de contenidos y agregar la
     * imagen de inicio de la aplicación
     */
    private void accionHome() {
        jLabelTop.setText("Home");
        jLabelTop.setForeground(new Color(255, 255, 255));

        //jLabelTopDescription.setText("Bievenido al sistema de gestión de mundiales de fútbol");
        jPanelMain.removeAll();
        JPanel homePanel = new JPanel();
        JLabel imageHome = new JLabel();

        imageHome.setIcon(new ImageIcon(getClass().getResource("/resources/home.png"))); // NOI18N
        //imageHome.setPreferredSize(new java.awt.Dimension(810, 465));
        homePanel.add(imageHome);

        jPanelMain.add(homePanel, BorderLayout.CENTER);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de SELECCIONES Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar las
     * selecciones de futbol cargadas en la aplicación
     */
    private void pintarMenuSelecciones() {
        btnSelecciones.setIcon(new ImageIcon(getClass().getResource("/resources/icons/selecciones.png"))); // NOI18N
        btnSelecciones.setText("Selecciones");
        btnSelecciones.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioSelecciones = new JLabel();
        jPanelMenuSelecciones.setBackground(new java.awt.Color(183, 16, 22));
        jPanelMenuSelecciones.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuSelecciones.setLayout(new BorderLayout(15, 0));
        jPanelMenuSelecciones.add(vacioSelecciones, BorderLayout.WEST);
        jPanelMenuSelecciones.add(btnSelecciones, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuSelecciones);

        btnSelecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Selecciones");
                boolean actuliazcion = auditoriaDAO.update("Selecciones");
                accionSelecciones();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Selecciones Permite ver la lista de selecciones que se
     * encuentran cargadas en la aplicación. Si la lista de selecciones en
     * vacía, muestra un botón que permite cargar un archivo CSV con la
     * información de las selelecciones
     */
    private void accionSelecciones() { ////Todo lo que carga el boton de selecciones
        jLabelTop.setText("Selecciones");
        selecciones = seleccionDAO.getSeleccionesMatriz();

        // Si no hay selecciones cargadas, muestra el botón de carga de selecciones
        if (selecciones == null) {
            jPanelMain.removeAll();
            JPanel seleccionesPanel = new JPanel();

            JLabel notSelecciones = new JLabel();
            notSelecciones.setText("No hay selecciones cargadas, por favor cargue selecciones \n\n");
            seleccionesPanel.add(notSelecciones);

            JButton cargarFile = new JButton();
            cargarFile.setText("Seleccione el archivo");
            seleccionesPanel.add(cargarFile);
            cargarFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    cargarFileSelecciones();
                }
            });

            jPanelMain.add(seleccionesPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay selecciones cargadas, llama el método que permite pintar la tabla de selecciones
        else {
            pintarTablaSelecciones();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de RESULTADOS Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar los
     * diferentes resultados de los partidos de la fase de grupos de un mundial
     */
    private void pintarMenuResultados() {
        btnResultados.setIcon(new ImageIcon(getClass().getResource("/resources/icons/resultados.png"))); // NOI18N
        btnResultados.setText("Resultados");
        btnResultados.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioResultados = new JLabel();
        jPanelMenuResultados.setBackground(new java.awt.Color(183, 16, 22));
        jPanelMenuResultados.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuResultados.setLayout(new BorderLayout(15, 0));
        jPanelMenuResultados.add(vacioResultados, BorderLayout.WEST);
        jPanelMenuResultados.add(btnResultados, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuResultados);

        btnResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boolean actu = auditoriaDAO.update("Resultados");
                accionResultados();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Resultados Permite ver la lista de resultados que se
     * encuentran cargadas en la aplicación. Si la lista de resultados en vacía,
     * muestra un botón que permite cargar un archivo CSV con la información de
     * los resultados
     */
    private void accionResultados() {
        jLabelTop.setText("Resultados");
        resultados = resultadosDAO.getResultadosMatriz();
        // Si no hay resultados cargados, muestra el botón de carga de resultados
        if (resultados == null) {
            jPanelMain.removeAll();
            JPanel resultadosPanel = new JPanel();

            if (resultados == null) {

                JLabel notResultados = new JLabel();
                notResultados.setText("No hay resultados, por favor cargue resultados \n\n");
                resultadosPanel.add(notResultados);

                JButton cargarFile = new JButton();
                cargarFile.setText("Seleccione el archivo");
                resultadosPanel.add(cargarFile);
                cargarFile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cargarFileResultados();
                    }
                });
            }

            jPanelMain.add(resultadosPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay ressultados cargados, llama el método que permite pintar la tabla de resultados
        else {
            pintarTablaResultados();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Selecciones Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de las selecciones de futbol que fueron cargadas
     */
    private void pintarMenuDashboardSel() {
        btnDashboardSel.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnDashboardSel.setText("Dash Selecciones");
        btnDashboardSel.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuDashboardSel.setBackground(new java.awt.Color(183, 16, 22));
        jPanelMenuDashboardSel.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardSel.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardSel.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuDashboardSel.add(btnDashboardSel, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardSel);
        btnDashboardSel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Selecciones");
                boolean actuliazcion = auditoriaDAO.update("Dash Selecciones");
                accionDashboardSel();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionDashboardSel() {
        jPanelMain.removeAll();
        JPanelreferencia.removeAll();
        JPanelreferencia.setMaximumSize((new java.awt.Dimension(620, 415)));
        jLabelTop.setText("DashBoard Selecciones");
        dashboardsel.getPaisesporContinente();
        jPanelMain.setBackground(Color.white);
        JPanelreferencia.setLayout(new BoxLayout(JPanelreferencia, BoxLayout.Y_AXIS));
        JPanelreferencia.setBackground(Color.white);
        pintar_cuadrosel();
        showPieChart(); // pinta la gráfica de tarta
        pintar_cuadronac();
        showBarChart(); // pinta la gráfica de barras
        JScrollPane scrollPane = new JScrollPane(JPanelreferencia);
        scrollPane.setPreferredSize(JPanelreferencia.getPreferredSize());
        scrollPane.setMaximumSize((new java.awt.Dimension(620, 415)));
        JPanel sc = new JPanel();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sc.add(scrollPane, BorderLayout.CENTER);
        sc.setLayout(new BoxLayout(sc, BoxLayout.Y_AXIS));
        jPanelMain.add(sc, BorderLayout.CENTER);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Resultados Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de los resultados de los partidos que fueron cargados
     */
    private void pintarMenuDashboardRes() {
        btnDashboardRes.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_resultados.png")));
        btnDashboardRes.setText("Dash Resultados");
        btnDashboardRes.setForeground(new java.awt.Color(255, 255, 255));
        JLabel vacioDashboardResultados = new JLabel();
        jPanelMenuDashboardRes.setBackground(new java.awt.Color(183, 16, 22));
        jPanelMenuDashboardRes.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardRes.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardRes.add(vacioDashboardResultados, BorderLayout.WEST);
        jPanelMenuDashboardRes.add(btnDashboardRes, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardRes);
        btnDashboardRes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Resultados");
                boolean actuliazcion = auditoriaDAO.update("Dash Resultados");
                accionDashboardRes();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionDashboardRes() {
        jLabelTop.setText("Dashboard Resultados");
        PartidoConMasGoles = resultadosDAO.PartidosConMasGolesMatriz();
        jPanelMain.removeAll();
        JPanelreferencia.removeAll();
        jPanelMain.setBackground(Color.white);
        JPanelreferencia.setLayout(new BoxLayout(JPanelreferencia, BoxLayout.Y_AXIS));
        JPanelreferencia.setBackground(Color.white);
        pintar_numpartidos();
        pintar_promgol();
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));
        JLabel titulo1 = new JLabel();
        titulo1.setText("Partido con Más y Menos Goles");
        panelBotones.add(titulo1);
//
        JButton botonmaxgol = new JButton("Partido con mas goles");
        botonmaxgol.setSize(500, 500);
        botonmaxgol.setOpaque(true);
        botonmaxgol.setForeground(new Color(209, 26, 31));
        LineBorder border = new LineBorder(new Color(209, 26, 31), 2);
        botonmaxgol.setBorder(border);
        botonmaxgol.addActionListener(e -> {
            PintarTablaPartido_con_mas_goles(1);
        });
//        
        // Agregar el botón a la ventana
        panelBotones.add(botonmaxgol);
        //
        JButton botonmingol = new JButton("Partido con menos goles");
        botonmingol.setSize(500, 500);
        botonmingol.setOpaque(true);
        botonmingol.setForeground(new Color(209, 26, 31));
        border = new LineBorder(new Color(209, 26, 31), 2);
        botonmingol.setBorder(border);
        botonmingol.addActionListener(e -> {
            PintarTablaPartido_con_mas_goles(0);
        });
        panelBotones.add(botonmingol);
        panelBotones.setBackground(Color.white);
        //
        jPanelMain.add(panelBotones, BorderLayout.CENTER);
        pintar_partidosGanadosEmpatados();
        pintar_partidosGanadosEmpatados_Num();
        //
        JPanel panelBotones2 = new JPanel();
        panelBotones2.setLayout(new GridLayout(1, 2, 30, 0));
        JLabel titulo2 = new JLabel();
        titulo2.setText("Equipos con Más y Menos Goles");
        
        JButton consultar = new JButton("Equipo con mas goles");
        consultar.setSize(500, 500);
        consultar.setOpaque(true);
        consultar.addActionListener(e -> {

            String sql3 = "SELECT equipo, SUM(total_goles) AS totalgoles\n"
                    + "FROM (\n"
                    + "    SELECT local AS equipo, goles_local AS total_goles\n"
                    + "    FROM j_hernandez34.partidos\n"
                    + "    UNION ALL\n"
                    + "    SELECT visitante AS equipo, goles_visitante AS totalgoles\n"
                    + "    FROM j_hernandez34.partidos\n"
                    + ") AS subconsulta\n"
                    + "GROUP BY equipo\n"
                    + "ORDER BY totalgoles DESC\n"
                    + "LIMIT 1;";

            resultadosDAO.EquipoConMasYMenosGoles(sql3);
            EquiposConMasGoles = resultadosDAO.MatrizEquipoConMasYMenosGoles(sql3);
            PintarTablaEquipoMasYMenosGoles();

        });
        panelBotones2.add(titulo2);
        consultar.setBorder(border);
        consultar.setForeground(new Color(209, 26, 31));
        panelBotones2.add(consultar);

        JButton consultar2 = new JButton("Equipo con menos goles");
        consultar2.setSize(500, 500);
        consultar2.setOpaque(true);

        consultar2.addActionListener(e -> {
            String sql3 = "SELECT equipo, MIN(total_goles) AS totalgoles\n"
                    + "FROM (\n"
                    + "    SELECT local AS equipo, SUM(goles_local) AS total_goles\n"
                    + "    FROM j_hernandez34.partidos\n"
                    + "    GROUP BY local\n"
                    + "    UNION ALL\n"
                    + "    SELECT visitante AS equipo, SUM(goles_visitante) AS total_goles\n"
                    + "    FROM j_hernandez34.partidos\n"
                    + "    GROUP BY visitante\n"
                    + ") AS subconsulta\n"
                    + "GROUP BY equipo\n"
                    + "HAVING MIN(total_goles) = (\n"
                    + "    SELECT MIN(total_goles) AS totalgoles\n"
                    + "    FROM (\n"
                    + "        SELECT local AS equipo, SUM(goles_local) AS total_goles\n"
                    + "        FROM j_hernandez34.partidos\n"
                    + "        GROUP BY local\n"
                    + "        UNION ALL\n"
                    + "        SELECT visitante AS equipo, SUM(goles_visitante) AS total_goles\n"
                    + "        FROM j_hernandez34.partidos\n"
                    + "        GROUP BY visitante\n"
                    + "    ) AS subconsulta\n"
                    + ");";

            resultadosDAO.EquipoConMasYMenosGoles(sql3);
            EquiposConMasGoles = resultadosDAO.MatrizEquipoConMasYMenosGoles(sql3);
            PintarTablaEquipoMasYMenosGoles();

        });
        consultar2.setBorder(border);
        consultar2.setForeground(new Color(209, 26, 31));
        panelBotones2.add(consultar2);
        panelBotones2.setBackground(Color.white);
        
        jPanelMain.add(panelBotones2);
        
        jPanelMain.repaint();
        jPanelMain.revalidate();

    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte izquierda de la interfaz, dónde se visulaiza el
     * menú de navegaación
     */
    private void pintarPanelIzquierdo() {
        // Se elimina el color de fondo del panel del menú
        jPanelMenu.setOpaque(false);

        // Se agrega un border izquierdo, de color blanco para diferenciar el panel de menú del panel de contenido
        jPanelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));

        // Se define un BoxLayot de manera vertical para los elementos del panel izquierdo
        jPanelLeft.setLayout(new BoxLayout(jPanelLeft, BoxLayout.Y_AXIS));
        jPanelLeft.setBackground(new java.awt.Color(159, 14, 20));
        getContentPane().add(jPanelLeft, java.awt.BorderLayout.LINE_START);
        jPanelLeft.add(jPanelMenu);
        jPanelLeft.setPreferredSize((new java.awt.Dimension(220, 540)));
        jPanelLeft.setMaximumSize(jPanelLeft.getPreferredSize());
    }

    /**
     * Función que permite leer un archivo y procesar el contenido que tiene en
     * cada una de sus líneas El contenido del archivo es procesado y cargado en
     * la matriz de selecciones. Una vez la información se carga en la atriz, se
     * hace un llamado a la función pintarTablaSelecciones() que se encarga de
     * pintar en la interfaz una tabla con la información almacenada en la
     * matriz de selecciones
     */
    public void cargarFileSelecciones() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {

            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Permite que el sistema se salte la léctura de los encabzados del archivo CSV
            entrada.nextLine();

            // Se leen cada unas de las líneas del archivo
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                Seleccion seleccion = new Seleccion(columns[1], columns[2], columns[3], columns[4]);
                if (seleccionDAO.registrarSeleccion(seleccion)) {
                    System.out.println("Seleccion " + seleccion.getNombre() + " registrada");
                } else {
                    System.out.println("Error " + seleccion.getNombre());
                }
            }

            selecciones = seleccionDAO.getSeleccionesMatriz();
            pintarTablaSelecciones();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pinta la tabla con la información de las
     * selelceciones que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"ID","Selección", "Continente",
     * "DT", "Nacionalidad DT"} Columnas que se corresponden son la información
     * que fue leida desde el archivo csv
     */
    public void pintarTablaSelecciones() {

        String[] columnNames = {"Selección", "Continente", "DT", "Nacionalidad DT"};
        JTable table = new JTable(selecciones, columnNames);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Equipos");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();

////////////////////////////////////////////////////////////        
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción a realizar cuando se presione el botón
                String textobusqueda = field.getText();
                selecciones = seleccionDAO.getSeleccionesMatrizBuscar(textobusqueda);
                //Validar por si no enceuntra nada
                if (selecciones != null) {
                    pintarTablaSelecciones();
                } else {
                    ///Quiero cambiar el metodo para validacion :(
                    selecciones = new String[1][5];
                    selecciones[0][0] = "------";
                    selecciones[0][1] = "Busqueda";
                    selecciones[0][2] = "No";
                    selecciones[0][3] = "Encontrada";
                    selecciones[0][4] = "-----";
                    pintarTablaSelecciones();
                }
            }
        });
/////////////////////////////////////////////////////////////////
        buscar.setText("Buscar");
        panelBotones.add(buscar);

        JButton limpiar = new JButton();
        limpiar.setText("Regresar");
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selecciones = seleccionDAO.getSeleccionesMatriz();
                pintarTablaSelecciones();
            }
        });
        panelBotones.add(limpiar);
        form.add(panelBotones);

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que tiene la lógica que permite leer un archivo CSV de resultados
     * y cargarlo sobre la matriz resultados que se tiene definida cómo variable
     * global. Luego de cargar los datos en la matriz, se llama la función
     * pintarTablaResultados() que se encarga de visulizar el contenido de la
     * matriz en un componente gráfico de tabla
     */
    public void cargarFileResultados() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {
            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Se define las dimensiones de la matriz de selecciones
            resultados = new String[48][7];
            entrada.nextLine();

            int i = 0;
            // Se iteran cada una de las líneas del archivo
            while (entrada.hasNext()) {
                System.out.println(i);
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                for (int j = 0; j < columns.length; j++) {
                    resultados[i][j] = columns[j];
                }
                i++;
            }

            pintarTablaResultados();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pintar la tabla con la información de los
     * resultados que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"Grupo","Local", "Visitante",
     * "Continente L", "Continente V", "Goles L", "Goles V"} Columnas que se
     * corresponden son la información que fue leida desde el archivo csv
     */
    public void pintarTablaResultados() {

        String[] columnNames = {"Grupo", "Local", "Visitante", "Continente L", "Continente V", "Goles L", "Goles V"};
        JTable table = new JTable(resultados, columnNames);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Resultados");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción a realizar cuando se presione el botón
                String textobusqueda = field.getText();
                resultados = resultadosDAO.getResultadoMatrizBuscar(textobusqueda);
                //Validar por si no enceuntra nada
                if (resultados != null) {
                    pintarTablaResultados();
                } else {
                    ///Quiero cambiar el metodo para validacion :(
                    resultados = new String[1][5];
                    resultados[0][0] = "------";
                    resultados[0][1] = "Busqueda";
                    resultados[0][2] = "No";
                    resultados[0][3] = "Encontrada";
                    resultados[0][4] = "-----";
                    pintarTablaResultados();
                }
            }
        });

        buscar.setText("Buscar");
        panelBotones.add(buscar);

        JButton limpiar = new JButton();
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultados = resultadosDAO.getResultadosMatriz();
                pintarTablaResultados();
            }
        });
        limpiar.setText("Regresar");
        panelBotones.add(limpiar);
        form.add(panelBotones);

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte derecha de la interfaz, dónde se visulaiza de
     * manera dinámica el contenido de cada una de las funciones que puede
     * realizar el usuario sobre la aplicación.
     */
    private void pintarPanelDerecho() {

        // Define las dimensiones del panel
        jPanelMain.setPreferredSize((new java.awt.Dimension(620, 420)));
        jPanelMain.setMaximumSize(jPanelLabelTop.getPreferredSize());

        getContentPane().add(jPanelRight, java.awt.BorderLayout.CENTER);
        jPanelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        jPanelRight.add(jPanelLabelTop, BorderLayout.LINE_START);
        jPanelRight.add(jPanelMain);
        jPanelRight.setPreferredSize((new java.awt.Dimension(620, 540)));
        jPanelRight.setMaximumSize(jPanelRight.getPreferredSize());
    }

    /**
     * Función que permite pinta la barra azul del contenedor de contenidos.
     * Barra azul que permite indicar en que sección que se encuentra navegando
     * el usuario.
     */
    private void pintarLabelTop() {
        jLabelTop = new JLabel();
        jLabelTop.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabelTop.setForeground(new java.awt.Color(241, 241, 241));
        jLabelTop.setText("Home");

        JLabel vacioTopLabel = new JLabel();
        jPanelLabelTop.setLayout(new BorderLayout(15, 0));
        jPanelLabelTop.add(vacioTopLabel, BorderLayout.WEST);
        jPanelLabelTop.setBackground(new java.awt.Color(209, 26, 31));
        jPanelLabelTop.add(jLabelTop, BorderLayout.CENTER);
        jPanelLabelTop.setPreferredSize((new java.awt.Dimension(620, 120)));
        jPanelLabelTop.setMaximumSize(jPanelLabelTop.getPreferredSize());
    }

    private void pintarMenuAuditoria() {
        btnAuditoria.setIcon(new ImageIcon(getClass().getResource("/resources/icons/auditoria.png")));
        btnAuditoria.setText("Auditoria");
        btnAuditoria.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuAuditoria.setBackground(new java.awt.Color(183, 16, 22));
        jPanelMenuAuditoria.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuAuditoria.setLayout(new BorderLayout(15, 0));
        jPanelMenuAuditoria.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuAuditoria.add(btnAuditoria, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuAuditoria);

        btnAuditoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Auditoria");
                accionAuditoria();
            }
        });
    }

    private void pintarTablaAuditoria() {
        String[] columnNames = {"Categorias", "Contador"};
        JTable table = new JTable(auditoria, columnNames);
        table.setRowHeight(30);

        JPanel tablaAuditoriaPanel = new JPanel();
        tablaAuditoriaPanel.setLayout(new BoxLayout(tablaAuditoriaPanel, BoxLayout.Y_AXIS));
        tablaAuditoriaPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        tablaAuditoriaPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        tablaAuditoriaPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(tablaAuditoriaPanel, BorderLayout.CENTER);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    private void accionAuditoria() {
        jLabelTop.setText("Auditoria");
        jLabelTop.setForeground(new java.awt.Color(238, 230, 196));
        auditoria = auditoriaDAO.getMatrizAuditoria();
        pintarTablaAuditoria();

    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIManual().setVisible(true);
            }
        });
    }

    public void showPieChart() {
        //create dataset América Central", "América del Norte", "Asia", "Europa", "Oceanía"};
        DefaultPieDataset barDataset = new DefaultPieDataset();
        barDataset.setValue("África", new Double(dashboardsel.num_continentes[0]));
        barDataset.setValue("América del Sur", new Double(dashboardsel.num_continentes[1]));
        barDataset.setValue("América Central", new Double(dashboardsel.num_continentes[2]));
        barDataset.setValue("América del Norte", new Double(dashboardsel.num_continentes[3]));
        barDataset.setValue("Asia", new Double(dashboardsel.num_continentes[4]));
        barDataset.setValue("Europa", new Double(dashboardsel.num_continentes[5]));
        barDataset.setValue("Oceanía", new Double(dashboardsel.num_continentes[6]));

        //create chart
        JFreeChart piechart = ChartFactory.createPieChart("Numero de Paises por Continente", barDataset, false, true, false);//explain

        PiePlot piePlot = (PiePlot) piechart.getPlot();

        //changing pie chart blocks colors
        piePlot.setSectionPaint("África", new Color(159, 14, 20));
        piePlot.setSectionPaint("América del Sur", new Color(197, 17, 24));
        piePlot.setSectionPaint("América Central", new Color(146, 12, 17));
        piePlot.setSectionPaint("América del Norte", new Color(69, 5, 8));
        piePlot.setSectionPaint("Asia", new Color(159, 14, 20));
        piePlot.setSectionPaint("Europa", new Color(197, 17, 24));
        piePlot.setSectionPaint("Oceanía", new Color(146, 12, 17));
        piePlot.setBackgroundPaint(Color.white);

        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        barChartPanel.removeAll();
        barChartPanel.setMaximumSize(new Dimension(820, 520));
        //barChartPanel.add(barChartPanel, BorderLayout.CENTER);
        barChartPanel.validate();
        JPanelreferencia.add(barChartPanel, BorderLayout.CENTER);
    }

    /*=============================================================================*/
    public void showLineChart() {
        //create dataset for the graph

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(200, "Amount", "january");
        dataset.setValue(150, "Amount", "february");
        dataset.setValue(18, "Amount", "march");

        //create chart
        JFreeChart linechart = ChartFactory.createLineChart("contribution", "monthly", "amount",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        //create plot object
        CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
        // lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);

        //create render object to change the moficy the line properties like color
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);

        //create chartPanel to display chart(graph)
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        lineChartPanel.removeAll();
        lineChartPanel.add(lineChartPanel, BorderLayout.CENTER);
        lineChartPanel.validate();
    }

    /*========================================================================================*/
    public void showHistogram() {

        double[] values = {95, 49, 14, 59, 50, 66, 47, 40, 1, 67,
            12, 58, 28, 63, 14, 9, 31, 17, 94, 71,
            49, 64, 73, 97, 15, 63, 10, 12, 31, 62,
            93, 49, 74, 90, 59, 14, 15, 88, 26, 57,
            77, 44, 58, 91, 10, 67, 57, 19, 88, 84
        };

        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", values, 20);

        JFreeChart chart = ChartFactory.createHistogram("JFreeChart Histogram", "Data", "Frequency", dataset, PlotOrientation.VERTICAL, false, true, false);
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);

        ChartPanel barpChartPanel2 = new ChartPanel(chart);
        JPanelreferencia.removeAll();
        JPanelreferencia.add(barpChartPanel2, BorderLayout.CENTER);
        JPanelreferencia.validate();
    }

    /*========================================================================================*/
    public void showBarChart() {
        dashboardsel.getnacionalidad_ranking();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(dashboardsel.ranking[0], "Nacionaliad", dashboardsel.nacionalidades[0]);
        dataset.setValue(dashboardsel.ranking[1], "Nacionaliad", dashboardsel.nacionalidades[1]);
        dataset.setValue(dashboardsel.ranking[2], "Nacionaliad", dashboardsel.nacionalidades[2]);
        dataset.setValue(dashboardsel.ranking[3], "Nacionaliad", dashboardsel.nacionalidades[3]);
        dataset.setValue(dashboardsel.ranking[4], "Nacionaliad", dashboardsel.nacionalidades[4]);
        JFreeChart chart = ChartFactory.createBarChart("Ranking de Paises con mas entrenadores", "Nacionalidades", "Numero de entrenadores",
                dataset, PlotOrientation.VERTICAL, false, true, true);
        Color clr3 = new Color(159, 14, 20);
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeGridlinePaint(clr3);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();

        renderer.setSeriesPaint(0, clr3);

        ChartPanel barpChartPanel = new ChartPanel(chart);
        barpChartPanel.removeAll();
        barpChartPanel.setMaximumSize(new Dimension(820, 520));
        JPanelreferencia.add(barpChartPanel, BorderLayout.CENTER);
        JPanelreferencia.validate();

    }
//=======================================    
//Pintar Labels

    private void pintar_cuadrosel() {
        JLabel sel = new JLabel();
        Font font = sel.getFont();
        Font biggerFont = font.deriveFont(font.getSize() + 20f); // Aumenta el tamaño de fuente en 5 puntos
        sel.setFont(biggerFont);
        String texto = String.valueOf(dashboardsel.getselecciones());
        sel.setText(" " + texto + " \nSelecciones han sido cargadas");
        sel.setForeground(new Color(255, 49, 49));
        LineBorder border = new LineBorder(new Color(255, 49, 49), 2);
        sel.setHorizontalAlignment(SwingConstants.CENTER);
        sel.setBorder(border);
        sel.setPreferredSize(new Dimension(605, 100));
        JPanelreferencia.add(sel, BorderLayout.CENTER);
    }

    private void pintar_cuadronac() {
        JLabel sel1 = new JLabel();
        sel1.setBackground(new Color(255, 49, 49));
        sel1.setOpaque(true);
        Font font = sel1.getFont();
        Font biggerFont = font.deriveFont(font.getSize() + 10f); // Aumenta el tamaño de fuente en 5 puntos
        sel1.setFont(biggerFont);
        String texto = String.valueOf(dashboardsel.getnacionalidades());
        sel1.setText("                                      Hay " + texto + "                                      ");
        sel1.setForeground(new Color(255, 255, 255));
        LineBorder border = new LineBorder(new Color(255, 49, 49), 2);
        sel1.setHorizontalAlignment(SwingConstants.CENTER);
        sel1.setBorder(border);
        sel1.setSize(new Dimension(605, 100));
        JPanelreferencia.add(sel1);
//
        JLabel sel = new JLabel();
        //Font font = sel.getFont();
        //Font biggerFont = font.deriveFont(font.getSize() + 10f); // Aumenta el tamaño de fuente en 5 puntos
        sel.setFont(biggerFont);
        texto = String.valueOf(dashboardsel.getnacionalidades());
        sel.setText("diferentes nacionalidades de los directores técnicos");
        sel.setForeground(new Color(255, 49, 49));
        //LineBorder border = new LineBorder(new Color(255, 49, 49), 2);
        sel.setHorizontalAlignment(SwingConstants.CENTER);
        sel.setBorder(border);
        sel.setSize(new Dimension(605, 100));
        JPanelreferencia.add(sel1, BorderLayout.CENTER);
        JPanelreferencia.add(sel, BorderLayout.CENTER);
    }

    private void pintar_numpartidos() {
        JLabel sel = new JLabel();
        Font font = sel.getFont();
        Font biggerFont = font.deriveFont(font.getSize() + 5f); // Aumenta el tamaño de fuente en 5 puntos
        sel.setFont(biggerFont);
        String texto = String.valueOf(resultadosDAO.NumeroDePartidos());
        sel.setText("" + texto + " Partidos cargados");
        sel.setForeground(new Color(226, 209, 171)); //226, 209, 171
        sel.setBackground(new Color(209, 26, 31)); //209, 26, 31
        sel.setOpaque(true);
        LineBorder border = new LineBorder(new Color(209, 26, 31), 2);
        sel.setHorizontalAlignment(SwingConstants.CENTER);
        sel.setBorder(border);
        sel.setPreferredSize(new Dimension(300, 60));
        jPanelMain.add(sel, BorderLayout.EAST);
    }

    private void pintar_promgol() {
        JLabel sel = new JLabel();
        Font font = sel.getFont();
        Font biggerFont = font.deriveFont(font.getSize() + 5f); // Aumenta el tamaño de fuente en 5 puntos
        sel.setFont(biggerFont);
        String texto = String.valueOf(resultadosDAO.PromedioDeGoles());
        sel.setText("Promedio de " + texto + " \n goles por partido");
        sel.setForeground(new Color(226, 209, 171));
        sel.setBackground(new Color(209, 26, 31));
        sel.setOpaque(true);
        LineBorder border = new LineBorder(new Color(209, 26, 31), 2);
        sel.setHorizontalAlignment(SwingConstants.CENTER);
        sel.setBorder(border);
        sel.setPreferredSize(new Dimension(300, 60));
        jPanelMain.add(sel, BorderLayout.WEST);
    }

    private void pintar_partidosGanadosEmpatados() {
        //Label partidos ganados como local
        JLabel sel = new JLabel();
        Font font = sel.getFont();
        Font biggerFont = font.deriveFont(font.getSize() + 2f); // Aumenta el tamaño de fuente en 5 puntos
        sel.setFont(biggerFont);
        String texto = String.valueOf(resultadosDAO.PromedioDeGoles());
        sel.setText("Partidos ganados local");
        sel.setForeground(new Color(226, 209, 171));
        sel.setBackground(new Color(209, 26, 31));
        sel.setOpaque(true);
        LineBorder border = new LineBorder(new Color(209, 26, 31), 2);
        sel.setHorizontalAlignment(SwingConstants.CENTER);
        sel.setBorder(border);
        sel.setPreferredSize(new Dimension(200, 60));
        jPanelMain.add(sel, BorderLayout.EAST);

        //Label partidos ganados como visitante
        JLabel sel2 = new JLabel();
        font = sel2.getFont();
        biggerFont = font.deriveFont(font.getSize() + 2f); // Aumenta el tamaño de fuente en 5 puntos
        sel2.setFont(biggerFont);
        texto = String.valueOf(resultadosDAO.PromedioDeGoles());
        sel2.setText("Partidos ganados visitante");
        sel2.setForeground(new Color(226, 209, 171));
        sel2.setBackground(new Color(209, 26, 31));
        sel2.setOpaque(true);
        border = new LineBorder(new Color(209, 26, 31), 2);
        sel2.setHorizontalAlignment(SwingConstants.CENTER);
        sel2.setBorder(border);
        sel2.setPreferredSize(new Dimension(200, 60));
        jPanelMain.add(sel2, BorderLayout.CENTER);

        //Label partidos empatados
        JLabel sel3 = new JLabel();
        font = sel3.getFont();
        biggerFont = font.deriveFont(font.getSize() + 2f); // Aumenta el tamaño de fuente en 5 puntos
        sel3.setFont(biggerFont);
        texto = String.valueOf(resultadosDAO.PromedioDeGoles());
        sel3.setText("Partidos empatados");
        sel3.setForeground(new Color(226, 209, 171));
        sel3.setBackground(new Color(209, 26, 31));
        sel3.setOpaque(true);
        border = new LineBorder(new Color(209, 26, 31), 2);
        sel3.setHorizontalAlignment(SwingConstants.CENTER);
        sel3.setBorder(border);
        sel3.setPreferredSize(new Dimension(200, 60));
        jPanelMain.add(sel3, BorderLayout.WEST);
    }

    private void pintar_partidosGanadosEmpatados_Num() {
        //Label partidos ganados como local
        JLabel sel = new JLabel();
        Font font = sel.getFont();
        Font biggerFont = font.deriveFont(font.getSize() + 10f); // Aumenta el tamaño de fuente en 5 puntos
        sel.setFont(biggerFont);
        sel.setText(resultadosDAO.getPartidosDondeGanaLocal());
        sel.setForeground(new Color(209, 26, 31));
        sel.setBackground(new Color(255, 255, 255));
        sel.setOpaque(true);
        LineBorder border = new LineBorder(new Color(209, 26, 31), 2);
        sel.setHorizontalAlignment(SwingConstants.CENTER);
        sel.setBorder(border);
        sel.setPreferredSize(new Dimension(200, 60));
        jPanelMain.add(sel, BorderLayout.EAST);

        //Label partidos ganados como visitante
        JLabel sel2 = new JLabel();
        font = sel2.getFont();
        biggerFont = font.deriveFont(font.getSize() + 10f); // Aumenta el tamaño de fuente en 5 puntos
        sel2.setFont(biggerFont);
        sel2.setText(resultadosDAO.getPartidosDondeGanaVisitante());
        sel2.setForeground(new Color(209, 26, 31));
        sel2.setBackground(new Color(255, 255, 255));
        sel2.setOpaque(true);
        border = new LineBorder(new Color(209, 26, 31), 2);
        sel2.setHorizontalAlignment(SwingConstants.CENTER);
        sel2.setBorder(border);
        sel2.setPreferredSize(new Dimension(200, 60));
        jPanelMain.add(sel2, BorderLayout.CENTER);

        //Label partidos empatados
        JLabel sel3 = new JLabel();
        font = sel3.getFont();
        biggerFont = font.deriveFont(font.getSize() + 10f); // Aumenta el tamaño de fuente en 5 puntos
        sel3.setFont(biggerFont);
        sel3.setText(resultadosDAO.getPartidosDondeEmpata());
        sel3.setForeground(new Color(209, 26, 31));
        sel3.setBackground(new Color(255, 255, 255));
        sel3.setOpaque(true);
        border = new LineBorder(new Color(209, 26, 31), 2);
        sel3.setHorizontalAlignment(SwingConstants.CENTER);
        sel3.setBorder(border);
        sel3.setPreferredSize(new Dimension(200, 60));
        jPanelMain.add(sel3, BorderLayout.WEST);
    }

    private void PintarTablaPartido_con_mas_goles(int bandera) {

        String[] columnNames = {"Grupo", "Local", "Visitante", "Continente L", "Continente V", "Goles L", "Goles V"};
        PartidoConMasGoles = resultadosDAO.PartidosConMasGolesMatriz();
        PartidoConMenosGoles = resultadosDAO.PartidosConMenosGolesMatriz();
        JTable table = null;
        JLabel label = new JLabel();
        
        if (bandera == 1) {
            table = new JTable(PartidoConMasGoles, columnNames);
            table.setRowHeight(30);
            label.setText("Partido con mas goles");
        }
        if (bandera == 0) {
            table = new JTable(PartidoConMenosGoles, columnNames);
            table.setRowHeight(30);
            label.setText("Partido con menos goles");

        }

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        form.add(label);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 20));

        JButton Regresar = new JButton();
        Regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionDashboardRes();
            }
        });
        Regresar.setText("Regresar");
        panelBotones.add(Regresar);
        form.add(panelBotones);
        form.setBackground(Color.white);

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 180)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());
        seleccionesPanel.setBackground(Color.white);
        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);
        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    private void PintarTablaEquipoMasYMenosGoles() {

        String[] columnNames = {"Equipo", "Goles"};
        JTable table = new JTable(EquiposConMasGoles, columnNames);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Equipo con mas y menos goles");
        form.add(label);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 20));

        JButton Regresar = new JButton();
        Regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionDashboardRes();
            }
        });
        Regresar.setText("Regresar");
        panelBotones.add(Regresar);
        form.add(panelBotones);

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();

    }
}
