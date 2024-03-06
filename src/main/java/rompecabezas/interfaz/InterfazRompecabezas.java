/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_rompecabezas
 * Autor: Manuel Muñoz - 02-Oct-2006
 * Autor: Milena Vela - 01-Dic-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package rompecabezas.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rompecabezas.mundo.Ficha;
import rompecabezas.mundo.Figura;
import rompecabezas.mundo.JuegoRompecabezas;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazRompecabezas extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Prefijo del nombre del archivo de cada figura
     */
    public static final String ARCHIVO_FIGURA = "./data/figura";

    /**
     * Sufijo que indica la extensión del archivo de propiedades donde se encuentra la descripción de cada figura
     */
    public static final String PROPIEDAD_EXTENSION_ARCHIVO = ".properties";

    /**
     * Prefijo para indicar el nombre de la ficha
     */
    public static final String FICHA_NOMBRE = "ficha";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Referencia a la clase principal del mundo
     */
    private JuegoRompecabezas juego;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel donde se muestran las figuras que tiene el rompecabezas
     */
    private PanelFiguras panelFiguras;

    /**
     * Panel para ordenar las fichas de la figura
     */
    private PanelOrdenamientoFichas panelOrdenamiento;

    /**
     * Panel con la figura en juego
     */
    private PanelFigura panelFigura;

    /**
     * Panel con las fichas de la figura para armar
     */
    private PanelFichas panelFichas;

    /**
     * Panel para el manejo de extensiones
     */
    private PanelExtension panelExtension;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la interfaz e inicializa todos sus componentes.
     */
    public InterfazRompecabezas( )
    {

        try
        {
            // inicializa el mundo
            juego = new JuegoRompecabezas( );
            cargarFiguras( );

            // Construye la forma
            setTitle( "Rompecabezas" );
            setLayout( new BorderLayout( ) );
            Dimension dimension = Toolkit.getDefaultToolkit( ).getScreenSize( );
            setSize( dimension );
            setResizable( false );
            setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

            // Creación de los paneles aquí

            // Panel izquierdo
            panelFiguras = new PanelFiguras( this );
            add( panelFiguras, BorderLayout.WEST );
            actualizarLista( );

            // Panel central
            JPanel panelJuego = new JPanel( );
            panelJuego.setLayout( new BorderLayout( ) );

            panelFichas = new PanelFichas( );
            panelJuego.add( panelFichas, BorderLayout.NORTH );

            panelFigura = new PanelFigura( );
            panelJuego.add( panelFigura, BorderLayout.CENTER );

            panelOrdenamiento = new PanelOrdenamientoFichas( this );
            panelJuego.add( panelOrdenamiento, BorderLayout.SOUTH );

            add( panelJuego, BorderLayout.CENTER );

            // Panel inferior
            panelExtension = new PanelExtension( this );
            add( panelExtension, BorderLayout.SOUTH );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
            JOptionPane.showMessageDialog( this, "Error al cargar las figuras " + e.getMessage( ), "Error de Carga", JOptionPane.ERROR_MESSAGE );

        }
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que lee de los archivos de propiedades y carga todas las figuras que allí se encuentren registradas, junto con sus fichas
     * @throws Exception Si ocurre algún problema al cargar las figuras
     */
    private void cargarFiguras( ) throws Exception
    {

        Properties propiedadesFigura = new Properties( );
        propiedadesFigura.load( new FileInputStream( new File( "./data/figuras.properties" ) ) );
        String strNum = propiedadesFigura.getProperty( "total.figuras" );

        int numFiguras = Integer.parseInt( strNum );

        for( int i = 0; i < numFiguras; i++ )
        {
            Properties propiedadesFichas = new Properties( );
            String nombreArchivo = ARCHIVO_FIGURA + ( i + 1 ) + PROPIEDAD_EXTENSION_ARCHIVO;
            propiedadesFichas.load( new FileInputStream( new File( nombreArchivo ) ) );

            String nombre = propiedadesFichas.getProperty( "figura.nombre" );
            String dificultad = propiedadesFichas.getProperty( "figura.dificultad" );
            String strFilas = propiedadesFichas.getProperty( "figura.filas" );
            int filas = Integer.parseInt( strFilas );
            String strColumnas = propiedadesFichas.getProperty( "figura.columnas" );
            int columnas = Integer.parseInt( strColumnas );
            String categoria = propiedadesFichas.getProperty( "figura.categoria" );
            String ruta = propiedadesFichas.getProperty( "figura.ruta" );

            Ficha tablero[][] = new Ficha[filas][columnas];

            int numFichas = filas * columnas;
            for( int j = 0; j < numFichas; j++ )
            {
                tablero = cargarFichas( propiedadesFichas, filas, columnas );
            }

            Figura figura = new Figura( nombre, dificultad, filas, columnas, categoria, ruta, tablero );
            juego.agregarFigura( figura );

        }

    }

    /**
     * Carga las fichas a una figura especificada
     * @param propiedadesFichas Properties con los datos de las fichas de una figura. Diferente de null
     * @param numFilas Número de fichas de alto de la figura
     * @param numColumnas Número de fichas de ancho de la figura
     * @return Matriz de fichas de la figura ordenadas según la posición relativa a la figura.
     * @throws Exception si ocurre algún problema al cargar las fichas de la figura
     */
    private Ficha[][] cargarFichas( Properties propiedadesFichas, int numFilas, int numColumnas ) throws Exception
    {
        Ficha figura[][] = new Ficha[numFilas][numColumnas];

        int cantidad = numFilas * numColumnas;
        for( int i = 0; i < cantidad; i++ )
        {
            String ruta = propiedadesFichas.getProperty( FICHA_NOMBRE + ( i + 1 ) + ".ruta" );
            String strCol = propiedadesFichas.getProperty( FICHA_NOMBRE + ( i + 1 ) + ".col" );
            int posCol = Integer.parseInt( strCol );
            String strFil = propiedadesFichas.getProperty( FICHA_NOMBRE + ( i + 1 ) + ".fil" );
            int posFil = Integer.parseInt( strFil );
            String strRegion = propiedadesFichas.getProperty( FICHA_NOMBRE + ( i + 1 ) + ".region" );
            int region = Integer.parseInt( strRegion );

            Ficha ficha = new Ficha( ruta, i + 1, posFil, posCol, region );
            figura[ ficha.obtenerFila( ) ][ ficha.obtenerColumna( ) ] = ficha;

        }

        return figura;
    }

    /**
     * Método que se ejecuta cuando el usuario quiere comenzar a jugar.<br>
     * @param nombreFigura Nombre de la figura seleccionada por el usuario. Diferente de null
     */
    public void iniciarJuego( String nombreFigura )
    {
        try
        {
            // Se busca la figura selecciona
            int posicion = juego.buscarFigura( nombreFigura );
            Figura actual = ( Figura )juego.obtenerFiguras( ).get( posicion );

            // Se desordena la figura
            actual.desordenar( );

            // se establece la figura seleccionada como la actual del juego
            juego.iniciarJuego( actual );

            // Se refresca la información referente al juego
            pintarFigura( actual );
            panelFiguras.actualizar( juego.obtenerArmados( ), juego.obtenerIntentos( ), juego.darTiempoTotal( ) + 0, actual.obtenerRutaFigura( ) );
            panelFiguras.desactivarIniciar( );

        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Método que se ejecuta cuando el usuario quiere terminar de jugar el rompecabezas actual
     */
    public void terminarJuego( )
    {
        int[] fichas = panelFigura.obtenerFichasColocadas( );
        if( juego.terminarJuego( fichas ) )
        {
            JOptionPane.showMessageDialog( this, "Figura armada correctamente", "Juego Terminado", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Figura incorrecta", "Juego Terminado", JOptionPane.INFORMATION_MESSAGE );
        }
        panelFiguras.actualizar( juego.obtenerArmados( ), juego.obtenerIntentos( ), juego.darTiempoTotal( ), PanelFiguras.IMAGEN_TITULO );
        panelOrdenamiento.desactivarBotones( );
        panelFiguras.activarIniciar( );
        panelFichas.quitarFichas( );
        panelFigura.quitarFigura( );

    }

    /**
     * Método que se ejecuta cuando el usuario quiere que se arme la figura automáticamente
     * @param figura Figura a ser armada. Diferente de null
     */
    public void armarFigura( Figura figura )
    {
        juego.incrementarIntentos();
        panelFichas.quitarFichas( );
        panelFigura.armarFigura( figura );
        panelOrdenamiento.desactivarBotones( );
        panelFiguras.actualizar( juego.obtenerArmados( ), juego.obtenerIntentos( ), juego.darTiempoTotal( ), PanelFiguras.IMAGEN_TITULO );
        panelFiguras.activarIniciar( );
    }

    /**
     * Devuelve la posición de una figura dentro de la lista de figuras disponibles.
     * @return Retorna un entero que corresponde a una figura del rompecabezas
     */
    public int seleccionarAleatorio( )
    {
        return juego.seleccionarAleatorio( );

    }

    /**
     * Busca una figura usando el nombre y cuando la encuentra la selecciona en la lista y muestra sus datos
     */
    public void buscarFigura( )
    {
        String nombreFigura = JOptionPane.showInputDialog( this, "Ingrese el nombre de la figura que quiere buscar" );
        if( nombreFigura != null && nombreFigura.length( ) != 0 )
        {
            int posicion = juego.buscarFigura( nombreFigura );
            actualizarLista( );
            if( posicion != -1 )
            {
                panelFiguras.seleccionar( posicion );
                Figura figura = ( Figura )juego.obtenerFiguras( ).get( posicion );
                juego.establecerFiguraActual( figura );
                String respuesta = "La figura " + nombreFigura + ":\n";
                respuesta += "Categoría: " + figura.obtenerCategoria( ) + "\n";
                respuesta += "Dificultad: " + figura.obtenerDificultad( ) + "\n";
                respuesta += "Número de fichas: " + figura.obtenerFichas( ).size( );
                JOptionPane.showMessageDialog( this, respuesta, "Información figura", JOptionPane.INFORMATION_MESSAGE );
                panelFiguras.activarIniciar( );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "No se encontró la figura buscada" );
            }
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Debe ingresar un nombre válido", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Ordena la lista de figuras por categoría.
     */
    public void ordenarFigurasPorCategoria( )
    {
        juego.ordenarFigurasPorCategoria( );
        panelFiguras.actualizarListaFiguras( juego.obtenerFiguras( ) );

    }

    /**
     * Ordena la lista de figuras por dificultad
     */
    public void ordenarFigurasPorDificultad( )
    {
        juego.ordenarFigurasPorDificultad( );
        panelFiguras.actualizarListaFiguras( juego.obtenerFiguras( ) );
    }

    /**
     * Indica a los paneles que se quiere pintar una nueva figura
     * @param figura Figura que se quiere pintar. Diferente de null
     */
    public void pintarFigura( Figura figura )
    {
        panelOrdenamiento.activarBotones( );
        panelFigura.pintarFigura( figura );
        panelFichas.setVisible( true );
        panelFichas.pintarFichas( figura.obtenerFichas( ) );
        panelOrdenamiento.actualizarListaFichas( figura.obtenerFichas( ) );
    }

    /**
     * Actualiza la lista de figuras
     */
    private void actualizarLista( )
    {
        panelFiguras.actualizarListaFiguras( juego.obtenerFiguras( ) );
    }

    /**
     * Indica al panel que contiene la información de la figura que tiene que actualizar la lista de las fichas
     * @param listaFichas Lista que contiene las fichas ordenadas como el usuario indica. Diferente de null
     */
    public void actualizarListaFichas( ArrayList<Ficha> listaFichas )
    {
        panelOrdenamiento.actualizarListaFichas( listaFichas );
    }

    /**
     * Indica a los paneles que se quiere resaltar una ficha
     * @param ficha Ficha que se quiere resaltar. Diferente de null
     */
    public void resaltarFicha( Ficha ficha )
    {
        if( !panelFigura.resaltarFicha( ficha.obtenerPosicion( ) ) )
            panelFichas.resaltarFicha( ficha.obtenerPosicion( ) );

    }

    /**
     * Retorna la figura actual que se esta armando
     * @return Figura que se está armando
     */
    public Figura obtenerFiguraActual( )
    {
        return juego.obtenerFiguraActual( );
    }

    /**
     * Ordena las fichas de la figura actual por columnas
     */
    public void ordenarFichasPorColumnas( )
    {
        juego.ordenarFichasPorColumnas( );
        panelOrdenamiento.actualizarListaFichas( juego.obtenerFiguraActual( ).obtenerFichas( ) );
    }

    /**
     * Ordena las fichas de la figura actual por filas
     */
    public void ordenarFichasPorFilas( )
    {
        juego.ordenarFichasPorFilas( );
        panelOrdenamiento.actualizarListaFichas( juego.obtenerFiguraActual( ).obtenerFichas( ) );
    }

    /**
     * Ordena las fichas de la figura actual por regiones
     */
    public void ordenarFichasPorRegiones( )
    {
        juego.ordenarFichasPorRegiones( );
        panelOrdenamiento.actualizarListaFichas( juego.obtenerFiguraActual( ).obtenerFichas( ) );
    }

    /**
     * Muestra la imagen de la figura en el panel correspondiente
     * @param figura La figura a mostrar - figura != null
     */
    public void mostrarFigura( Figura figura )
    {
        panelFiguras.actualizar( juego.obtenerArmados( ), juego.obtenerIntentos( ), 0, figura.obtenerRutaFigura( ) );

    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = juego.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = juego.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {
        InterfazRompecabezas interfaz = new InterfazRompecabezas( );
        interfaz.setVisible( true );
    }
}