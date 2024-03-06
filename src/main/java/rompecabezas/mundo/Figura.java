 /**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Figura.java 850 2007-01-16 14:36:31Z f-vela $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_rompecabezas
 * Autor: Manuel Muñoz - Oct 2, 2006
 * Autor: Milena Vela - 01-Dic-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package rompecabezas.mundo;

import java.util.ArrayList;

/**
 * Clase que representa una figura del juego de rompecabezas. <br>
 * <b>inv: </b> La dificultad solo puede tomar los valores DIFICULTAD_BAJA, DIFICULTAD_MEDIA o DIFICULTAD_SUPERIOR <br>
 * El nombre de la figura tiene que ser diferente de null <br>
 * El número de fichas de ancho de la figura tiene que ser mayor a 0 <br>
 * El número de fichas de alto de la figura tiene que ser mayor a 0 <br>
 * La ruta de la imagen completa debe ser diferente de null <br>
 * La categoría de la figura tiene que ser diferente de null <br>
 * El vector de fichas de la figura ha sido inicializado <br>
 * La matriz de fichas ha sido inicializada <br>
 * Toda posición en la matriz ha sido inicializada <br>
 * No existen dos fichas con la misma posición en la matriz de fichas <br>
 * No existen dos fichas con la misma posición en el vector de fichas <br>
 * La posición de la ficha debe ser válida dentro de la figura. 0 < posicion <= numFichasAncho*numFichasAlto
 */
public class Figura
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    /**
     * Constante que identifica la dificultad baja
     */
    public static final String DIFICULTAD_BAJA = "Baja";

    /**
     * Constante que identifica la dificultad media
     */
    public static final String DIFICULTAD_MEDIA = "Media";

    /**
     * Constante que identifica la dificultad alta
     */
    public static final String DIFICULTAD_SUPERIOR = "Superior";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Dificultad para armar el rompecabezas.
     */
    private String dificultad;

    /**
     * Nombre de la figura
     */
    private String nombre;

    /**
     * Número de fichas que tiene la figura de ancho
     */
    private int numFichasAncho;

    /**
     * Número de fichas que tiene la figura de alto
     */
    private int numFichasAlto;

    /**
     * Ruta relativa de la imagen del rompecabezas armado
     */
    private String rutaImagenCompleta;

    /**
     * Categoría de la figura
     */
    private String categoria;

    /**
     * Vector que contiene las fichas de la figura
     */
    private ArrayList<Ficha> fichas;

    /**
     * Matriz con las fichas de la figura
     */
    private Ficha[][] fichasFigura;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor con parámetros
     * @param pNombre Nombre que deberá tener la figura. Diferente de null
     * @param pDificultad Dificultad que tendrá la figura. Diferente de null
     * @param pFilas Cantidad de filas de la figura. Entero mayor que cero
     * @param pColumnas Cantidad de columnas de la figura. Entero mayor que cero
     * @param pCategoria Categoría a la que pertenece la figura. Diferente de null
     * @param pRuta Ruta de la imagen que representa. Diferente de null
     * @param pFichas Matriz con todas las fichas de la figura. pFichas != null y cada posición en la matriz esta inicializada
     */
    public Figura( String pNombre, String pDificultad, int pFilas, int pColumnas, String pCategoria, String pRuta, Ficha[][] pFichas )
    {
        nombre = pNombre;
        dificultad = pDificultad;
        numFichasAlto = pFilas;
        numFichasAncho = pColumnas;
        categoria = pCategoria;
        rutaImagenCompleta = pRuta;
        fichasFigura = pFichas;
        fichas = obtenerFichasOrdenadasPorPosicion( );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que ordena las fichas de la figura de acuerdo a su columna de manera ascendente. <br>
     * El método de ordenamiento utilizado es inserción. <br>
     * <b>post: </b>El vector de fichas está ordenado por número de columna de manera ascendente.
     */
    public void ordenarPorColumnas( )
    {
        for( int i = 1; i < fichas.size( ); i++ )
        {
            Ficha porInsertar = ( Ficha )fichas.get( i );
            boolean termino = false;

            for( int j = i; j > 0 && !termino; j-- )
            {
                Ficha actual = ( Ficha )fichas.get( j - 1 );

                if( actual.compararPorColumna( porInsertar ) > 0 )
                {
                    fichas.set( j, actual );
                    fichas.set( j - 1, porInsertar );
                }
                else
                    termino = true;
            }
        }
        verificarInvariante( );
    }

    /**
     * Método que ordena las fichas de la figura por filas de manera ascendente. <br>
     * El método de ordenamiento utilizado es selección. <br>
     * <b>post: </b>El vector de fichas está ordenado por número de fila de manera ascendente.
     */
    public void ordenarPorFilas( )
    {
        for( int inicial = 0; inicial < fichas.size( ); inicial++ )
        {
            int posicionMenor = inicial;
            Ficha fichaMenor = ( Ficha )fichas.get( inicial );

            // Buscar la ficha de menor fila entre inicial y final
            for( int i = inicial + 1; i < fichas.size( ); i++ )
            {
                Ficha fichaPosicion = ( Ficha )fichas.get( i );

                // La ficha de la posición actual es menor que la menor encontrada hasta el momento
                if( fichaPosicion.compararPorFila( fichaMenor ) < 0 )
                {
                    fichaMenor = fichaPosicion;
                    posicionMenor = i;
                }
            }

            if( posicionMenor != inicial )
            {
                Ficha temp = ( Ficha )fichas.get( inicial );
                fichas.set( inicial, fichaMenor );
                fichas.set( posicionMenor, temp );
            }

        }
        verificarInvariante( );
    }

    /**
     * Método que organiza por regiones las fichas usando el algoritmo de burbuja <br>
     * <b>post: </b>El vector de fichas está ordenado por región de manera ascendente así: ESQUINA, BORDE e INTERNA.
     */
    public void ordenarPorRegion( )
    {
        for( int i = fichas.size( ); i > 0; i-- )
        {
            for( int j = 0; j < i - 1; j++ )
            {
                Ficha f1 = ( Ficha )fichas.get( j );
                Ficha f2 = ( Ficha )fichas.get( j + 1 );

                // Si es necesario se deben intercambiar f1 y f2
                if( f1.compararPorRegion( f2 ) > 0 )
                {
                    fichas.set( j, f2 );
                    fichas.set( j + 1, f1 );
                }
            }
        }
        verificarInvariante( );
    }

    /**
     * Método que organiza por posición las fichas usando el algoritmo de burbuja <br>
     * <b>post: </b>El vector de fichas está ordenado por posición relativa a la figura de manera ascendente.
     */
    public void ordenarPorPosicion( )
    {
        for( int i = fichas.size( ); i > 0; i-- )
        {
            for( int j = 0; j < i - 1; j++ )
            {
                Ficha f1 = ( Ficha )fichas.get( j );
                Ficha f2 = ( Ficha )fichas.get( j + 1 );

                // Si es necesario se deben intercambiar f1 y f2
                if( f1.compararPorPosicion( f2 ) > 0 )
                {
                    fichas.set( j, f2 );
                    fichas.set( j + 1, f1 );
                }
            }
        }
        verificarInvariante( );
    }

    /**
     * Compara dos figuras por dificultad (lexicográficamente).
     * @param f La figura con la que se está comparando - f != null
     * @return Retorna 0 si las figuras son iguales en dificultad. <br>
     *         Retorna -1 si la dificultad de la figura f es mayor . <br>
     *         Retorna 1 si la dificultad de la figura f es menor. <br>
     */
    public int compararPorDificultad( Figura f )
    {
        if( dificultad.compareTo( f.obtenerDificultad( ) ) == 0 )
            return 0;
        else if( dificultad.compareTo( f.obtenerDificultad( ) ) > 0 )
            return 1;
        else
            return -1;

    }

    /**
     * Compara dos figuras por categoría (lexicográficamente).
     * @param f La figura con la que se está comparando - f != null
     * @return Retorna 0 si las figuras son iguales en categoría. <br>
     *         Retorna -1 si la categoría de la figura f es mayor . <br>
     *         Retorna 1 si la categoría de la figura f es menor. <br>
     */
    public int compararPorCategoria( Figura f )
    {
        if( categoria.compareTo( f.obtenerCategoria( ) ) == 0 )
            return 0;
        else if( categoria.compareTo( f.obtenerCategoria( ) ) > 0 )
            return 1;
        else
            return -1;

    }

    /**
     * Compara dos figuras por nombre (lexicográficamente).
     * @param f La figura con la que se está comparando - f != null
     * @return Retorna 0 si las figuras son iguales según el nombre. <br>
     *         Retorna -1 si l de la figura f es mayor . <br>
     *         Retorna 1 si la dificultad de la figura f es menor. <br>
     */
    public int compararPorNombre( Figura f )
    {
        if( nombre.compareTo( f.obtenerNombre( ) ) == 0 )
            return 0;
        else if( nombre.compareTo( f.obtenerNombre( ) ) > 0 )
            return 1;
        else
            return -1;

    }

    /**
     * Retorna la matriz de fichas.
     * @return Matriz con las fichas de la figura
     */
    public Ficha[][] obtenerMatrizFichas( )
    {
        return fichasFigura;
    }

    /**
     * Desordena las fichas de la figura para que el jugador comience a jugar con unas fichas en desorden
     */
    public void desordenar( )
    {
        for( int i = 0; i < fichas.size( ); i++ )
        {
            int indice1 = ( int ) ( Math.random( ) * fichas.size( ) );
            int indice2 = ( int ) ( Math.random( ) * fichas.size( ) );
            Ficha ficha1 = ( Ficha )fichas.get( indice1 );
            fichas.set( indice1, fichas.get( indice2 ) );
            fichas.set( indice2, ficha1 );
        }
        verificarInvariante( );
    }

    /**
     * Informa si la figura se encuentra armada comparando el vector de fichas ordenadas por posición con el arreglo de fichas jugadas
     * @param fichasJugadas Arreglo con las posiciones de las fichas que colocó el usuario. Diferente de null
     * @return true si la figura esta armada, false de lo contrario
     */
    public boolean estaArmada( int[] fichasJugadas )
    {
        ordenarPorPosicion( );
        if( fichasJugadas.length == 0 )
            return false;
        for( int i = 0; i < fichasJugadas.length; i++ )
        {
            int fichaJugada = fichasJugadas[ i ];

            int fichaReal = ( ( Ficha )fichas.get( i ) ).obtenerPosicion( );
            if( fichaReal != fichaJugada )
                return false;
        }
        return true;
    }

    /**
     * Retorna la dificultad de la figura
     * @return Cadena que corresponde al grado de dificultad de la figura
     */
    public String obtenerDificultad( )
    {
        return dificultad;
    }

    /**
     * Retorna la categoría de la figura
     * @return Cadena con la categoría a la que pertenece la figura
     */
    public String obtenerCategoria( )
    {
        return categoria;
    }

    /**
     * Retorna el número de fichas de alto que tiene la figura
     * @return Número de fichas de alto de la figura
     */
    public int obtenerFichasAlto( )
    {
        return numFichasAlto;
    }

    /**
     * Retorna el número de fichas de ancho que tiene la figura
     * @return Número de fichas de ancho de la figura
     */
    public int obtenerFichasAncho( )
    {
        return numFichasAncho;
    }

    /**
     * Retorna el nombre de la figura
     * @return Cadena de caracteres con el nombre de la figura.
     */
    public String obtenerNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la ruta a la imagen de la figura
     * @return Cadena de caracteres con la ruta de la figura.
     */
    public String obtenerRutaFigura( )
    {
        return rutaImagenCompleta;
    }

    /**
     * Retorna la lista de las fichas de la figura
     * @return Vector con las fichas de la figura. Las fichas no tienen ningún orden específico.
     */
    public ArrayList<Ficha> obtenerFichas( )
    {
        return fichas;
    }

    /**
     * Retorna la lista de fichas ordenadas por posición de manera ascendente.
     * @return Vector con las fichas ordenadas por la posición relativa a la figura.
     */
    private ArrayList<Ficha> obtenerFichasOrdenadasPorPosicion( )
    {
        ArrayList<Ficha> fichasArmadas = new ArrayList<>( );

        for( int i = 0; i < numFichasAlto; i++ )
        {
            for( int j = 0; j < numFichasAncho; j++ )
            {
                Ficha ficha = fichasFigura[ i ][ j ];
                fichasArmadas.add( ficha );
            }
        }

        return fichasArmadas;
    }

    /**
     * Retorna una cadena con el nombre de la figura y su categoría
     * @return Cadena nombre - categoría de la figura
     */
    public String toString( )
    {
        String string = new String( );
        string += nombre + " - ";
        string += categoria;
        return string;
    }

    /**
     * Método que verifica el invariante de la clase <br>
     * <b>inv: </b> La dificultad solo puede tomar los valores DIFICULTAD_BAJA, DIFICULTAD_MEDIA o DIFICULTAD_SUPERIOR <br>
     * El nombre de la figura tiene que ser diferente de null <br>
     * El número de fichas de ancho de la figura tiene que ser mayor a 0 <br>
     * El número de fichas de alto de la figura tiene que ser mayor a 0 <br>
     * La ruta de la imagen completa debe ser diferente de null <br>
     * La categoría de la figura tiene que ser diferente de null <br>
     * El vector de fichas de la figura ha sido inicializado <br>
     * La matriz de fichas ha sido inicializada <br>
     * Toda posición en la matriz ha sido inicializada <br>
     * No existen dos fichas con la misma posición en la matriz de fichas <br>
     * No existen dos fichas con la misma posición en el vector de fichas <br>
     * La posición de la ficha debe ser válida dentro de la figura. 0 < posicion <= numFichasAncho*numFichasAlto
     */
    private void verificarInvariante( )
    {

        assert ( dificultad.equals( DIFICULTAD_BAJA ) || dificultad.equals( DIFICULTAD_MEDIA ) || dificultad.equals( DIFICULTAD_SUPERIOR ) ) : "La  dificultad no se encuentra en los rangos especificados";
        assert nombre != null : "El nombre de la figura no puede ser null";
        assert numFichasAncho > 0 : "La cantidad de columnas no puede ser negativa o cero";
        assert numFichasAlto > 0 : "La cantidad de filas no puede ser negativa o cero";
        assert rutaImagenCompleta != null : "La ruta de la imagen completa no puede ser null";
        assert categoria != null : "La categoría de la figura no puede ser null";
        assert fichas != null : "La lista de fichas no puede ser null";
        assert fichasFigura != null : "La matriz de fichas de la figura no puede ser null";
        assert matrizInicializada( ) : "Toda posición en la matriz debe estar inicializada";
        assert !hayFichasEnIgualPosicion( ) : "No pueden existir dos fichas con la misma posición";
        assert !hayFichasConIgualColumnaYFila( ) : "No pueden existir dos fichas con la misma columna y fila";
        assert !hayFichasPosicionInvalida( ) : "La posición de las fichas debe estar en el rango válido";

    }

    /**
     * Verifica que la matriz de fichas contiene una ficha en cada posición.
     * @return true si cada posición en la matriz está inicializada, false en caso contrario.
     */
    private boolean matrizInicializada( )
    {
        for( int i = 0; i < numFichasAlto; i++ )
        {
            for( int j = 0; j < numFichasAncho; j++ )
            {
                if( fichasFigura[ i ][ j ] == null )
                    return false;
            }
        }

        return true;
    }

    /**
     * Verifica si hay dos fichas con la misma posición en la figura
     * @return Retorna true si hay dos fichas con la misma posición dentro de la figura; retorna false en caso contrario.
     */
    private boolean hayFichasEnIgualPosicion( )
    {
        ArrayList<Ficha> fichasArmadas = obtenerFichasOrdenadasPorPosicion( );
        for( int i = 0; i < fichasArmadas.size( ); i++ )
        {
            Ficha ficha1 = ( Ficha )fichasArmadas.get( i );

            for( int j = 0; j < fichasArmadas.size( ); j++ )
            {
                if( i != j )
                {
                    Ficha ficha2 = ( Ficha )fichasArmadas.get( j );
                    if( ficha1.obtenerPosicion( ) == ficha2.obtenerPosicion( ) )
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Verifica si hay dos fichas con la misma columna y la misma fila en la figura
     * @return Retorna true si hay dos fichas con la misma fila y columna dentro de la figura; retorna false en caso contrario.
     */

    private boolean hayFichasConIgualColumnaYFila( )
    {
        ArrayList<Ficha> fichasArmadas = obtenerFichasOrdenadasPorPosicion( );
        for( int i = 0; i < fichasArmadas.size( ); i++ )
        {
            Ficha ficha1 = ( Ficha )fichasArmadas.get( i );

            for( int j = 0; j < fichasArmadas.size( ); j++ )
            {
                if( i != j )
                {
                    Ficha ficha2 = ( Ficha )fichasArmadas.get( j );
                    if( ficha1.obtenerColumna( ) == ficha2.obtenerColumna( ) && ficha1.obtenerFila( ) == ficha2.obtenerFila( ) )
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Verifica que la posición de las fichas relativa a la figura sea una posición entre 1 y numFichasAncho*numFichasAlto
     * @return Retorna true si hay una ficha con posición relativa a la figura fuera del rango permitido; retorna false en caso contrario.
     */
    private boolean hayFichasPosicionInvalida( )
    {
        ArrayList<Ficha> fichasArmadas = obtenerFichasOrdenadasPorPosicion( );
        for( int i = 0; i < fichasArmadas.size( ); i++ )
        {
            Ficha ficha1 = ( Ficha )fichasArmadas.get( i );
            int posicion = ficha1.obtenerPosicion( );
            int numFichas = numFichasAncho * numFichasAlto;

            if( posicion <= 0 || posicion > numFichas )
            {
                return true;
            }
        }

        return false;
    }

}