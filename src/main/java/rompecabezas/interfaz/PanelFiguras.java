/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_rompecabezas
 * Autor: Manuel Muñoz - Oct 4, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package rompecabezas.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import rompecabezas.mundo.Figura;

/**
 * Panel que contiene la lista de figuras disponibles
 */
public class PanelFiguras extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Acción que se ejecuta cuando se presiona el botón de iniciar un nuevo juego
     */
    public static final String ACCION_INICIAR_JUEGO = "ACCION_INICIAR_JUEGO";

    /**
     * Acción que se ejecuta cuando se presiona el botón de terminar el juego
     */
    private static final String TERMINAR = "TERMINAR";

    /**
     * Acción que se ejecuta cuando se presiona el botón de armar la figura
     */
    private static final String ACCION_ARMAR = "ACCION_ARMAR";

    /**
     * Acción que se ejecuta cuando se presiona el botón de seleccionar un rompecabezas aleatorio
     */
    public static final String ACCION_ALEATORIO = "ACCION_ALEATORIO";

    /**
     * Acción que se ejecuta cuando se presiona el botón de ordenar los rompecabezas por categorías
     */
    public static final String ACCION_ORDENAR_CATEGORIAS = "ACCION_ORDENAR_CATEGORIAS";

    /**
     * Acción que se ejecuta cuando se presiona el botón de ordenar por dificultad
     */
    public static final String ACCION_ORDENAR_DIFICULTAD = "ACCION_ORDENAR_DIFICULTAD";

    /**
     * Acción que se ejecuta cuando se presiona el botón de buscar un rompecabezas
     */
    public static final String ACCION_BUSCAR_ROMPECABEZAS = "ACCION_BUSCAR_ROMPECABEZAS";

    /**
     * Indica la imagen que se muestra cuando una figura no esta cargada
     */
    public static final String IMAGEN_TITULO = "./data/titulo.png";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Lista con la información de las figuras que están cargadas
     */
    private JList listaFiguras;

    /**
     * Botón que indica que el usuario quiere iniciar el juego
     */
    private JButton btnIniciarJuego;

    /**
     * Botón de terminar el juego actual
     */
    private JButton btnTerminar;

    /**
     * Botón par armar automáticamente el rompecabezas
     */
    private JButton btnArmarAutomaticamente;

    /**
     * Botón de seleccionar un rompecabezas aleatorio
     */
    private JButton btnAleatorio;

    /**
     * Botón que indica que se quiere buscar un rompecabezas por su nombre
     */
    private JButton btnBuscarRompecabezas;

    /**
     * Botón que indica que se quiere ordenar por categorías los rompecabezas
     */
    private JButton btnOrdenarCategorias;

    /**
     * Botón que indica que se quiere ordenar por dificultad los rompecabezas
     */
    private JButton btnOrdenarDificultad;

    /**
     * Etiqueta que indica el titulo del la figura objetivo
     */
    private JLabel lblTituloObjetivo;

    /**
     * Etiqueta que indica la vista objetivo
     */
    private JLabel lblVistaObjetivo;

    /**
     * Etiqueta que indica el titulo de los rompecabezas que se han armado
     */
    private JLabel lblArmados;

    /**
     * Etiqueta que indica el titulo de los rompecabezas que se han intentado armar
     */
    private JLabel lblIntentos;

    /**
     * Etiqueta que indica el tiempo que se gasto jugando en el ultimo rompecabezas
     */
    private JLabel lblTiempo;

    /**
     * Ruta de la figura actual
     */
    private String rutaFiguraActual;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal de la interfaz gráfica
     */
    private InterfazRompecabezas principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor por parámetros
     * @param rompecabezas Clase principal de la interfaz gráfica
     */
    public PanelFiguras( InterfazRompecabezas rompecabezas )
    {
        principal = rompecabezas;
        rutaFiguraActual = "./data/titulo.png";

        listaFiguras = new JList( );
        listaFiguras.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

        JScrollPane scroll = new JScrollPane( listaFiguras );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setBorder( new CompoundBorder( new EmptyBorder( 3, 3, 3, 3 ), new LineBorder( Color.BLACK, 1 ) ) );
        scroll.getViewport( ).add( listaFiguras );

        TitledBorder titulo;
        titulo = BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder( EtchedBorder.LOWERED ), "Figuras" );
        titulo.setTitleJustification( TitledBorder.LEFT );
        setBorder( titulo );
        setLayout( new GridBagLayout( ) );
        GridBagConstraints constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets( 0, 10, 10, 10 );
        constraint.fill = GridBagConstraints.BOTH;
        add( scroll, constraint );

        btnIniciarJuego = new JButton( "Iniciar Juego" );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets( 5, 0, 5, 0 );
        btnIniciarJuego.setPreferredSize( new Dimension( 200, 20 ) );
        btnIniciarJuego.setActionCommand( ACCION_INICIAR_JUEGO );
        btnIniciarJuego.addActionListener( this );
        add( btnIniciarJuego, constraint );

        btnTerminar = new JButton( "Terminar Figura Actual" );
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.insets = new Insets( 5, 0, 5, 0 );
        btnTerminar.setPreferredSize( new Dimension( 200, 20 ) );
        btnTerminar.setActionCommand( TERMINAR );
        btnTerminar.addActionListener( this );
        btnTerminar.setEnabled( false );
        add( btnTerminar, constraint );

        btnArmarAutomaticamente = new JButton( "Armar Automáticamente" );
        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.insets = new Insets( 5, 0, 5, 0 );
        btnArmarAutomaticamente.setPreferredSize( new Dimension( 200, 20 ) );
        btnArmarAutomaticamente.addActionListener( this );
        btnArmarAutomaticamente.setActionCommand( ACCION_ARMAR );
        btnArmarAutomaticamente.setEnabled( false );
        add( btnArmarAutomaticamente, constraint );

        btnAleatorio = new JButton( "Seleccionar Aleatorio" );
        constraint.gridx = 0;
        constraint.gridy = 4;
        constraint.insets = new Insets( 5, 0, 5, 0 );
        btnAleatorio.setPreferredSize( new Dimension( 200, 20 ) );
        btnAleatorio.setActionCommand( ACCION_ALEATORIO );
        btnAleatorio.addActionListener( this );
        add( btnAleatorio, constraint );

        btnOrdenarCategorias = new JButton( "Ordenar por Categorías" );
        constraint.gridx = 0;
        constraint.gridy = 5;
        constraint.insets = new Insets( 5, 0, 5, 0 );
        btnOrdenarCategorias.setPreferredSize( new Dimension( 200, 20 ) );
        btnOrdenarCategorias.setActionCommand( ACCION_ORDENAR_CATEGORIAS );
        btnOrdenarCategorias.addActionListener( this );
        add( btnOrdenarCategorias, constraint );

        btnOrdenarDificultad = new JButton( "Ordenar por Dificultad" );
        constraint.gridx = 0;
        constraint.gridy = 6;
        constraint.insets = new Insets( 5, 0, 5, 0 );
        btnOrdenarDificultad.setPreferredSize( new Dimension( 200, 20 ) );
        btnOrdenarDificultad.setActionCommand( ACCION_ORDENAR_DIFICULTAD );
        btnOrdenarDificultad.addActionListener( this );
        add( btnOrdenarDificultad, constraint );

        btnBuscarRompecabezas = new JButton( "Buscar Figura" );
        constraint.gridx = 0;
        constraint.gridy = 7;
        constraint.insets = new Insets( 5, 0, 5, 0 );
        btnBuscarRompecabezas.setPreferredSize( new Dimension( 200, 20 ) );
        btnBuscarRompecabezas.setActionCommand( ACCION_BUSCAR_ROMPECABEZAS );
        btnBuscarRompecabezas.addActionListener( this );
        add( btnBuscarRompecabezas, constraint );

        lblArmados = new JLabel( "Armados: N/A" );
        constraint.gridx = 0;
        constraint.gridy = 8;
        constraint.gridwidth = 25;
        constraint.insets = new Insets( 10, 10, 5, 0 );
        constraint.fill = GridBagConstraints.BOTH;
        add( lblArmados, constraint );

        lblIntentos = new JLabel( "Intentos: N/A" );
        constraint.gridx = 0;
        constraint.gridy = 9;
        constraint.gridwidth = 25;
        constraint.insets = new Insets( 5, 10, 5, 0 );
        constraint.fill = GridBagConstraints.BOTH;
        add( lblIntentos, constraint );

        lblTiempo = new JLabel( "Tiempo: N/A" );
        constraint.gridx = 0;
        constraint.gridy = 10;
        constraint.gridwidth = 25;
        constraint.insets = new Insets( 5, 10, 5, 0 );
        constraint.fill = GridBagConstraints.BOTH;
        add( lblTiempo, constraint );

        lblTituloObjetivo = new JLabel( "Figura Completa" );
        constraint.gridx = 0;
        constraint.gridy = 11;
        constraint.insets = new Insets( 10, 10, 0, 0 );
        constraint.fill = GridBagConstraints.BOTH;
        add( lblTituloObjetivo, constraint );

        lblVistaObjetivo = new JLabel( new ImageIcon( rutaFiguraActual ) );
        constraint.gridx = 0;
        constraint.gridy = 12;
        constraint.gridwidth = 25;
        constraint.insets = new Insets( 5, 10, 10, 0 );
        constraint.fill = GridBagConstraints.BOTH;
        add( lblVistaObjetivo, constraint );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que retorna la figura que fue seleccionada en la lista de figuras
     * @return Cadena de caracteres que contiene el nombre de la figura seleccionada
     */
    public Figura obtenerFiguraSeleccionada( )
    {
        return ( Figura )listaFiguras.getSelectedValue( );
    }

    /**
     * Método que cambia la información que se muestra al usuario
     * @param armados Número de rompecabezas que han sido armados. Entero mayor o igual a 0
     * @param intentados Número de rompecabezas que han sido intentados armar. Entero mayor o igual a 0
     * @param tiempo Tiempo(segundos) que se demoro el usuario en armar la última figura.
     * @param ruta Ruta de la imagen que hay que mostrar en la parte del objetivo. Diferente de null
     */
    public void actualizar( int armados, int intentados, int tiempo, String ruta )
    {
        lblArmados.setText( "Armados: " + armados );
        lblIntentos.setText( "Intentos: " + intentados );
        int minutos = tiempo / 60;
        int segundos = tiempo % 60;
        lblTiempo.setText( "Tiempo: " + minutos + " min. " + segundos + " seg. " );
        ImageIcon icono = new ImageIcon( ruta );
        lblVistaObjetivo.setIcon( icono );

    }

    /**
     * Actualiza la lista de figuras que se está mostrando
     * @param listaActualizada Es una lista con las figuras que deben mostrarse
     */
    public void actualizarListaFiguras( ArrayList<Figura> listaActualizada )
    {
        listaFiguras.setListData( listaActualizada.toArray( ) );
        listaFiguras.setSelectedIndex( 0 );
    }

    /**
     * Selecciona una figura de la lista
     * @param seleccionado La posición de la figura que se debe seleccionar
     */
    public void seleccionar( int seleccionado )
    {
        listaFiguras.setSelectedIndex( seleccionado );
        listaFiguras.ensureIndexIsVisible( seleccionado );
    }

    /**
     * Desactiva el botón de iniciar juego y activa los botones de armado automático y terminar juego
     */
    public void desactivarIniciar( )
    {
        btnIniciarJuego.setEnabled( false );
        btnArmarAutomaticamente.setEnabled( true );
        btnTerminar.setEnabled( true );
    }

    /**
     * Botón que activa el botón de iniciar y desactiva los botones de armado automático y terminar juego
     */
    public void activarIniciar( )
    {
        btnIniciarJuego.setEnabled( true );
        btnArmarAutomaticamente.setEnabled( false );
        btnTerminar.setEnabled( false );
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */

    public void actionPerformed( ActionEvent e )
    {
        if( ACCION_INICIAR_JUEGO.equals( e.getActionCommand( ) ) )
        {
            Figura seleccionada = obtenerFiguraSeleccionada( );
            principal.iniciarJuego( seleccionada.obtenerNombre( ) );
        }
        else if( TERMINAR.equals( e.getActionCommand( ) ) )
        {
            principal.terminarJuego( );
        }
        else if( ACCION_ARMAR.equals( e.getActionCommand( ) ) )
        {
            principal.armarFigura( principal.obtenerFiguraActual( ) );
        }
        else if( ACCION_ALEATORIO.equals( e.getActionCommand( ) ) )
        {
            int pos = principal.seleccionarAleatorio( );
            listaFiguras.setSelectedIndex( pos );
        }
        else if( ACCION_ORDENAR_CATEGORIAS.equals( e.getActionCommand( ) ) )
        {
            principal.ordenarFigurasPorCategoria( );

        }
        else if( ACCION_ORDENAR_DIFICULTAD.equals( e.getActionCommand( ) ) )
        {
            principal.ordenarFigurasPorDificultad( );

        }
        else if( ACCION_BUSCAR_ROMPECABEZAS.equals( e.getActionCommand( ) ) )
        {
            principal.buscarFigura( );
        }

    }

}