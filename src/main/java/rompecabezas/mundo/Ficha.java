 /**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
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

/**
 * Clase que representa una ficha de la figura <br>
 * <b>inv: </b> La posición de la ficha relativa a la figura tiene que ser mayor a cero. <br>
 * La fila a la que pertenece la ficha en la figura tiene que ser mayor o igual a cero. <br>
 * La columna a la que pertenece la ficha en la figura tiene que ser mayor o igual a cero. <br>
 * La ruta de la imagen debe ser diferente de null. <br>
 * La región de la ficha solo puede ser ESQUINA, BORDE o INTERNA. <br>
 */
public class Ficha
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para indicar la región esquina.
     */
    public static final int ESQUINA = 1;

    /**
     * Constante para indicar la región borde.
     */
    public static final int BORDE = 2;

    /**
     * Constante para indicar la región interna
     */
    public static final int INTERNA = 3;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Indica la región donde se encuentra la ficha en la figura.
     */
    private int region;

    /**
     * Ruta relativa donde se encuentra la imagen de la ficha
     */
    private String rutaImagen;

    /**
     * Columna a la que pertenece la ficha en la figura de acuerdo al estándar de nombrado de matrices
     */
    private int numColumna;

    /**
     * Fila a la que pertenece la ficha en la figura de acuerdo al estándar de nombrado de matrices
     */
    private int numFila;

    /**
     * Posición de la ficha relativa a la figura. Las fichas se empiezan a enumerar dede la número uno de izquierda a derecha y de arriba hacia abajo.
     */
    private int posicion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una ficha inicializando sus atributos con los valores que vienen por parámetro
     * @param ruta Ruta de la imagen de la ficha
     * @param nPosicion Posición de la ficha relativa a la figura
     * @param fila posición en x de la ficha, de acuerdo a como se nombran las posiciones en las matrices.
     * @param columna posición en y de la ficha, de acuerdo a como se nombran las posiciones en las matrices.
     * @param nRegion Entero que representa la región donde se encuentra la ficha en la figura.
     */
    public Ficha( String ruta, int nPosicion, int fila, int columna, int nRegion )
    {
        rutaImagen = ruta;
        posicion = nPosicion;
        numFila = fila;
        numColumna = columna;
        region = nRegion;

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Da la columna de la ficha en la figura
     * @return número mayor o igual a cero
     */
    public int obtenerColumna( )
    {
        return numColumna;
    }

    /**
     * Da la fila de la ficha en la figura
     * @return número mayor o igual a cero
     */
    public int obtenerFila( )
    {
        return numFila;
    }

    /**
     * Retorna la ruta de la imagen de la ficha
     * @return String diferente de null
     */
    public String obtenerRuta( )
    {
        return rutaImagen;
    }

    /**
     * Retorna la posición de la ficha con respecto a la figura
     * @return Entero mayor a 0
     */
    public int obtenerPosicion( )
    {
        return posicion;
    }

    /**
     * Retorna la región donde se encuentra la ficha
     * @return Entero que puede tomar los valores ESQUINA, BORDE, INTERNA
     */
    public int obtenerRegion( )
    {
        return region;
    }

    /**
     * Representación en String de la ficha
     */
    public String toString( )
    {
        String nombre = "Ficha " + posicion + " - ";
        if( region == ESQUINA )
            nombre += "Está en la esquina";
        if( region == BORDE )
            nombre += "Está en el borde";
        if( region == INTERNA )
            nombre += "Es interna";

        return nombre;
    }

    /**
     * Compara dos fichas por número de columna
     * @param f La ficha con la que se está comparando - f != null
     * @return Retorna 0 si las fichas pertenecen a la misma columna. <br>
     *         Retorna -1 si la columna de la ficha f es mayor . <br>
     *         Retorna 1 si la columna de la ficha f es menor. <br>
     */
    public int compararPorColumna( Ficha f )
    {
        if( numColumna == f.obtenerColumna( ) )
            return 0;
        else if( numColumna > f.obtenerColumna( ) )
            return 1;
        else
            return -1;

    }

    /**
     * Compara dos fichas por número de fila
     * @param f La ficha con la que se está comparando - f != null
     * @return Retorna 0 si las fichas pertenecen a la misma fila. <br>
     *         Retorna -1 si la fila de la ficha f es mayor . <br>
     *         Retorna 1 si la fila de la ficha f es menor. <br>
     */
    public int compararPorFila( Ficha f )
    {
        if( numFila == f.obtenerFila( ) )
            return 0;
        else if( numFila > f.obtenerFila( ) )
            return 1;
        else
            return -1;

    }

    /**
     * Compara dos fichas por región. La región menor es ESQUINA, seguida de BORDE y la región mayor es INTERNA.
     * @param f La ficha con la que se está comparando - f != null
     * @return Retorna 0 si las fichas pertenecen a la misma región. <br>
     *         Retorna -1 si la región de la ficha f es mayor . <br>
     *         Retorna 1 si la región de la ficha f es menor. <br>
     */
    public int compararPorRegion( Ficha f )
    {
        if( region == f.obtenerRegion( ) )
            return 0;
        else if( region > f.obtenerRegion( ) )
            return 1;
        else
            return -1;

    }

    /**
     * Compara dos fichas por posición relativa a la figura.
     * @param f La ficha con la que se está comparando - f != null
     * @return Retorna 0 si las fichas tienen la misma posición. <br>
     *         Retorna -1 si la posición de la ficha f es mayor . <br>
     *         Retorna 1 si la posición de la ficha f es menor. <br>
     */
    public int compararPorPosicion( Ficha f )
    {
        if( posicion == f.obtenerPosicion( ) )
            return 0;
        else if( posicion > f.obtenerPosicion( ) )
            return 1;
        else
            return -1;

    }

}