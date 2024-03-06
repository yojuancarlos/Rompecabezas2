 /**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: JuegoRompecabezas.java 853 2007-01-16 16:00:29Z f-vela $
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

package rompecabezas.mundo;

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que representa el Juego de Rompecabezas. <br>
 * <b>inv: </b> El vector de figuras ha sido inicializado. figuras != null <br>
 * No hay dos figuras con el mismo nombre en el juego <br>
 * El número intentos no puede se menor a cero <br>
 * El número de veces que se ha jugado no puede ser menor a cero <br>
 * El tiempo de juego no puede ser negativo<br>
 */

public class JuegoRompecabezas
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Número de rompecabezas que han sido tratados de armar.
     */
    private int intentos;

    /**
     * Número de figuras que han sido armadas
     */
    private int armadas;

    /**
     * Tiempo(milisegundos) en el que empezó el juego.
     */
    private long tiempoInicial;

    /**
     * Tiempo(milisegundos) en el que terminó el juego.
     */
    private long tiempoFinal;

    /**
     * Figuras que existen para armar
     */
    private ArrayList<Figura> figuras;

    /**
     * Figura actual que está siendo armada
     */
    private Figura figuraActual;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa el juego de rompecabezas. <br>
     * El tiempo se inicializa en cero. <br>
     * El número de intentos se inicializa en cero. <br>
     * El número de figuras armadas se inicializa en cero. <br>
     * Se inicializa el vector de figuras vacío. <br>
     */
    public JuegoRompecabezas( )
    {
        intentos = 0;
        armadas = 0;
        tiempoInicial = 0;
        tiempoFinal = 0;
        figuras = new ArrayList<>( );
        //verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega la figura al vector de figuras <br>
     * <b>post: </b> Se agregó la figura al vector de figuras si no existe otra figura con el mismo nombre.
     * @param f Figura que se va a adicionar al vector. f != null
     * @return true si la figura se puedo agregar, false en caso contrario.
     */
    public boolean agregarFigura( Figura f )
    {
        int figuraBuscada = buscarFigura( f.obtenerNombre( ) );
        boolean agregado = false;

        if( figuraBuscada == -1 )
        {
            figuras.add( f );
            agregado = true;
        }
        verificarInvariante( );

        return agregado;
    }

    /**
     * Método que es llamado cuando se quiere iniciar un juego <br>
     * <b>post: </b> Se inicializa el tiempo y se establece la figura actual
     * @param figura Figura que se va a armar. figura != null
     */
    public void iniciarJuego( Figura figura )
    {
        tiempoInicial = darTiempoActual( );
        tiempoFinal = darTiempoActual( );
        figuraActual = figura;
        verificarInvariante( );
    }

    /**
     * Indica si la figura se ha armado correctamente o no y actualiza los datos del juego. <br>
     * <b>post: </b> Si la figura esta armada se incrementa el número de figuras armadas del juego, de lo contrario se incrementa el número de intentos.
     * @param fichasJugadas Arreglo de enteros con los identificadores de las fichas que están colocadas en la figura de juego. Diferente de null
     */
    public boolean terminarJuego( int[] fichasJugadas )
    {
        boolean armado = figuraActual.estaArmada( fichasJugadas );
        if( armado )
        {
            armadas++;
        }
        else
        {
            intentos++;
        }
        verificarInvariante( );
        return armado;
    }

    /**
     * Método que ordena las figuras del rompecabezas por categoría (lexicográficamente) de manera ascendente. <br>
     * El método de ordenamiento utilizado es selección. <br>
     * <b>post: </b>El vector de figuras está ordenado por categoría de manera ascendente.
     */
    public void ordenarFigurasPorCategoria( )
    {
        for( int i = 0; i < figuras.size( ) - 1; i++ )
        {
            Figura menor = ( Figura )figuras.get( i );
            int cual = i;
            for( int j = i + 1; j < figuras.size( ); j++ )
            {
                Figura posibleMenor = ( Figura )figuras.get( j );
                if( posibleMenor.compararPorCategoria( menor ) < 0 )
                {
                    menor = posibleMenor;
                    cual = j;
                }
            }
            Figura temp = ( Figura )figuras.get( i );
            figuras.set( i, menor );
            figuras.set( cual, temp );
        }
        verificarInvariante( );
    }

    /**
     * Método que ordena las figuras del rompecabezas por dificultad (lexicográficamente) de manera ascendente. <br>
     * El método de ordenamiento utilizado es burbuja. <br>
     * <b>post: </b>El vector de figuras está ordenado por dificultad de manera ascendente.
     */
    public void ordenarFigurasPorDificultad( )
    {
        for( int i = figuras.size( ); i > 0; i-- )
        {
            for( int j = 0; j < i - 1; j++ )
            {
                Figura figura1 = ( Figura )figuras.get( j );
                Figura figura2 = ( Figura )figuras.get( j + 1 );
                if( figura1.compararPorDificultad( figura2 ) > 0 )
                {
                    figuras.set( j, figura2 );
                    figuras.set( j + 1, figura1 );
                }
            }
        }
        verificarInvariante( );
    }

    /**
     * Método que ordena las figuras del rompecabezas por nombre (lexicográficamente) de manera ascendente. <br>
     * El método de ordenamiento utilizado es inserción. <br>
     * <b>post: </b>El vector de figuras está ordenado por nombre de manera ascendente.
     */
    public void ordenarFigurasPorNombre( )
    {
        for( int i = 1; i < figuras.size( ); i++ )
        {
            Figura porInsertar = ( Figura )figuras.get( i );
            boolean termino = false;

            for( int j = i; j > 0 && !termino; j-- )
            {
                Figura actual = ( Figura )figuras.get( j - 1 );

                if( actual.compararPorNombre( porInsertar ) > 0 )
                {
                    figuras.set( j, actual );
                    figuras.set( j - 1, porInsertar );
                }
                else
                    termino = true;
            }
        }
        verificarInvariante( );
    }

    /**
     * Busca una figura por su nombre y retorna la posición en la que se encuentra en el vector de figuras del juego. <br>
     * @param nombre El nombre de la figura buscada - nombre!=null
     * @return Se retornó la posición donde se encuentra una figura con el nombre dado. Si no se encuentra ninguna figura con ese nombre se retornó -1.
     */
    public int buscarFigura( String nombre )
    {
        int posicion = -1;
        boolean termine = false;

        for( int i = 0; i < figuras.size( ) && !termine; i++ )
        {
            Figura figuraPosicion = ( Figura )figuras.get( i );
            String nombreFigura = figuraPosicion.obtenerNombre( );

            // Los nombres son iguales
            if( nombreFigura.equalsIgnoreCase( nombre ) )
            {
                posicion = i;
                termine = true;
            }
        }

        return posicion;
    }

    /**
     * Busca una figura utilizando una búsqueda binaria. <br>
     * <b>pre: </b> La lista de figuras se encuentra ordenada por nombre. <br>
     * @param nombre es el nombre de la figura que se va a buscar - nombre!=null
     * @return Se retornó la posición de la figura con el nombre dado. Si la figura no existe se retornó -1.
     */
    public int buscarBinarioPorNombre( String nombre )
    {
        int posicion = -1;
        int inicio = 0;
        int fin = figuras.size( ) - 1;

        // Figura a buscar
        Ficha fichas[][] = new Ficha[1][1];
        fichas[ 0 ][ 0 ] = new Ficha( "f1imagen1", 1, 0, 0, Ficha.ESQUINA );
        Figura aBuscar = new Figura( nombre, "", 1, 1, "", "", fichas );

        while( inicio <= fin && posicion == -1 )
        {
            int medio = ( inicio + fin ) / 2;
            Figura mitad = ( Figura )figuras.get( medio );
            if( mitad.compararPorNombre( aBuscar ) == 0 )
            {
                posicion = medio;
            }
            else if( mitad.compararPorNombre( aBuscar ) > 0 )
            {
                fin = medio - 1;
            }
            else
            {
                inicio = medio + 1;
            }
        }
        return posicion;
    }

    /**
     * Retorna una posición aleatoriamente en el vector de figuras
     * @return pos Posición en el vector de figuras. pos < figuras.size()
     */
    public int seleccionarAleatorio( )
    {
        int pos = ( int ) ( ( Math.random( ) + .1 ) * figuras.size( ) );
        return pos;
    }

    /**
     * Retorna la cantidad de rompecabezas que fueron armados
     * @return Número de rompecabezas armados
     */
    public int obtenerArmados( )
    {
        return armadas;
    }

    /**
     * Retorna la cantidad de rompecabezas que han sido tratados de armar
     * @return Número de intentos
     */
    public int obtenerIntentos( )
    {
        return intentos;
    }

    /**
     * Incrementa en uno la cantidad de rompecabezas que han sido tratados de armar
     */
    public void incrementarIntentos( )
    {
        intentos++;
    }

    /**
     * Retorna un vector con las figuras del rompecabezas
     * @return Vector con las figuras.
     */
    public ArrayList<Figura> obtenerFiguras( )
    {
        return figuras;
    }

    /**
     * Retorna la figura que se está armando
     * @return Figura actual
     */
    public Figura obtenerFiguraActual( )
    {
        return figuraActual;
    }

    /**
     * Establece la figura actual con la que viene por parámetro
     * @param figura diferente de null
     */
    public void establecerFiguraActual( Figura figura )
    {
        figuraActual = figura;
    }

    /**
     * Método que indica a la figura actual que ordene sus fichas por columnas <br>
     * <b>post: </b> Las fichas de la figura están ordenadas por columna.
     */
    public void ordenarFichasPorColumnas( )
    {
        figuraActual.ordenarPorColumnas( );
    }

    /**
     * Método que indica a la figura actual que ordene sus fichas por filas <br>
     * <b>post: </b> Las fichas de la figura están ordenadas por fila.
     */
    public void ordenarFichasPorFilas( )
    {
        figuraActual.ordenarPorFilas( );
    }

    /**
     * Método que indica a la figura actual que ordene sus fichas por regiones <br>
     * <b>post: </b> Las fichas de la figura están ordenadas por regiones.
     */
    public void ordenarFichasPorRegiones( )
    {
        figuraActual.ordenarPorRegion( );
    }

    /**
     * Devuelve un número que representa los milisegundos desde el 1 de enero de 1970 hasta el tiempo actual.
     * @return milisegundos desde el 1 de enero de 1970 hasta el tiempo actual.
     */
    private long darTiempoActual( )
    {
        return new Date( ).getTime( );
    }

    /**
     * Retorna el tiempo total en segundos que fue necesario para resolver el juego
     * @return tiempo
     */
    public int darTiempoTotal( )
    {
        if( tiempoInicial == 0 )
            return 0;
        else
            tiempoFinal = darTiempoActual( );
        return ( int ) ( ( tiempoFinal - tiempoInicial ) / 1000 );
    }

    /**
     * Verifica el invariante de la clase <br>
     * <b>inv: </b> El vector de figuras ha sido inicializado. figuras != null <br>
     * No hay dos figuras con el mismo nombre en el juego <br>
     * El número intentos no puede se menor a cero <br>
     * El número de veces que se ha jugado no puede ser menor a cero <br>
     * El tiempo de juego no puede ser negativo<br>
     */
    private void verificarInvariante( )
    {
        assert figuras != null : "La lista de figuras no puede ser null";
        assert ( !buscarFigurasConNombresRepetidos( ) ) : "Hay dos figuras con el mismo nombre";
        assert intentos >= 0 : "La cantidad de intentos de juego del usuario no puede ser negativa";
        assert armadas >= 0 : "La cantidad de figuras armados del usuario no puede ser negativa";
        assert tiempoInicial <= tiempoFinal : "El tiempo de juego no puede ser negativo";
    }

    /**
     * Verifica si hay dos figuras con el mismo nombre.
     * @return Retorna true si hay dos figuras con el mismo nombre, retorna false en caso contrario
     */
    private boolean buscarFigurasConNombresRepetidos( )
    {
        for( int i = 0; i < figuras.size( ); i++ )
        {
            Figura figuraI = ( Figura )figuras.get( i );
            for( int j = 0; j < figuras.size( ); j++ )
            {
                if( i != j )
                {
                    Figura figuraJ = ( Figura )figuras.get( j );
                    if( figuraJ.compararPorNombre( figuraI ) == 0 )
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}