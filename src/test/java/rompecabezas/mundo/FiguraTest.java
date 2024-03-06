package rompecabezas.mundo;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;


public class FiguraTest {
	

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Figura donde se harán las pruebas
     */
    private Figura figura1;

    /**
     * Figura donde se harán las pruebas
     */
    private Figura figura2;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye dos figuras de prueba. figura1 de 3x4 figura2 de 4x3
     */
    private void setupEscenario1( )
    {
        Ficha fichas1[][] = new Ficha[3][4];
        fichas1[ 0 ][ 0 ] = new Ficha( "f1imagen1", 1, 0, 0, Ficha.ESQUINA );
        fichas1[ 0 ][ 1 ] = new Ficha( "f1imagen2", 2, 0, 1, Ficha.BORDE );
        fichas1[ 0 ][ 2 ] = new Ficha( "f1imagen3", 3, 0, 2, Ficha.BORDE );
        fichas1[ 0 ][ 3 ] = new Ficha( "f1imagen4", 4, 0, 3, Ficha.ESQUINA );
        fichas1[ 1 ][ 0 ] = new Ficha( "f1imagen5", 5, 1, 0, Ficha.BORDE );
        fichas1[ 1 ][ 1 ] = new Ficha( "f1imagen6", 6, 1, 1, Ficha.INTERNA );
        fichas1[ 1 ][ 2 ] = new Ficha( "f1imagen7", 7, 1, 2, Ficha.INTERNA );
        fichas1[ 1 ][ 3 ] = new Ficha( "f1imagen8", 8, 1, 3, Ficha.BORDE );
        fichas1[ 2 ][ 0 ] = new Ficha( "f1imagen9", 9, 2, 0, Ficha.ESQUINA );
        fichas1[ 2 ][ 1 ] = new Ficha( "f1imagen10", 10, 2, 1, Ficha.BORDE );
        fichas1[ 2 ][ 2 ] = new Ficha( "f1imagen11", 11, 2, 2, Ficha.BORDE );
        fichas1[ 2 ][ 3 ] = new Ficha( "f1imagen12", 12, 2, 3, Ficha.ESQUINA );

        figura1 = new Figura( "Figura1 de prueba", Figura.DIFICULTAD_MEDIA, 3, 4, "Categoría de Prueba1", "Ruta de Prueba1", fichas1 );

        Ficha fichas2[][] = new Ficha[4][3];
        fichas2[ 0 ][ 0 ] = new Ficha( "f2imagen1", 1, 0, 0, Ficha.ESQUINA );
        fichas2[ 0 ][ 1 ] = new Ficha( "f2imagen2", 2, 0, 1, Ficha.BORDE );
        fichas2[ 0 ][ 2 ] = new Ficha( "f2imagen3", 3, 0, 2, Ficha.ESQUINA );
        fichas2[ 1 ][ 0 ] = new Ficha( "f2imagen4", 4, 1, 0, Ficha.BORDE );
        fichas2[ 1 ][ 1 ] = new Ficha( "f2imagen5", 5, 1, 1, Ficha.INTERNA );
        fichas2[ 1 ][ 2 ] = new Ficha( "f2imagen6", 6, 1, 2, Ficha.BORDE );
        fichas2[ 2 ][ 0 ] = new Ficha( "f2imagen7", 7, 2, 0, Ficha.BORDE );
        fichas2[ 2 ][ 1 ] = new Ficha( "f2imagen8", 8, 2, 1, Ficha.INTERNA );
        fichas2[ 2 ][ 2 ] = new Ficha( "f2imagen9", 9, 2, 2, Ficha.BORDE );
        fichas2[ 3 ][ 0 ] = new Ficha( "f2imagen10", 10, 3, 0, Ficha.ESQUINA );
        fichas2[ 3 ][ 1 ] = new Ficha( "f2imagen11", 11, 3, 1, Ficha.BORDE );
        fichas2[ 3 ][ 2 ] = new Ficha( "f2imagen12", 12, 3, 2, Ficha.ESQUINA );

        figura2 = new Figura( "Figura2 de prueba", Figura.DIFICULTAD_SUPERIOR, 4, 3, "Categoría de Prueba2", "Ruta de Prueba1", fichas2 );
    }

    /**
     * Este método se encarga de verificar el método constructor<br>
     * <b> Métodos a probar: </b> <br>
     * Figura (constructor), obtenerNombre, obtenerCategoria, obtenerCodigo, obtenerDificultad, obtenerFichas, obtenerFichasOrdenadas, obtenerFichasAncho, obtenerFichasAlto,
     * obtenerRutaFigura<br>
     * <b> Objetivo: </b> Probar la inicialización de Figura<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear una FIGURA los atributos del objeto deben quedar inicializados con el valor correcto.
     */
    @Test
    public void testFigura( )
    {
        setupEscenario1( );
        assertNotNull( "El nombre de la figura no debería ser null", figura1.obtenerNombre( ) );
        assertEquals( "El nombre de la figura esta mal inicializado", "Figura1 de prueba", figura1.obtenerNombre( ) );
        assertNotNull( "La categoría no debería ser null", figura1.obtenerCategoria( ) );
        assertEquals( "La categoría esta mal inicializada", "Categoría de Prueba1", figura1.obtenerCategoria( ) );
        assertEquals( "La dificultad de la figura esta mal inicializada", Figura.DIFICULTAD_MEDIA, figura1.obtenerDificultad( ) );
        assertNotNull( "La lista de fichas no debería ser null", figura1.obtenerFichas( ) );
        assertNotNull( "La matriz de fichas no debería ser null", figura1.obtenerMatrizFichas( ) );
        assertEquals( "La cantidad de filas de la figura esta mal inicializada", 3, figura1.obtenerFichasAlto( ) );
        assertEquals( "La cantidad de columnas esta mal inicializada", 4, figura1.obtenerFichasAncho( ) );
        assertNotNull( "La ruta de la figura no debería ser null", figura1.obtenerRutaFigura( ) );
        assertEquals( "La ruta de la figura esta mal inicializado", "Ruta de Prueba1", figura1.obtenerRutaFigura( ) );

    }

    /**
     * Verifica el método compararPorCategoria. <br>
     * <b> Métodos a probar: </b> <br>
     * compararPorCategoria. <br>
     * <b> Objetivo: </b> Probar que el método compararPorCategoria realiza la comparación de dos figuras de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al comparar una figura cuya categoría es menor a la de otra el resultado debe ser -1. <br>
     * 2. Al comparar una figura cuya categoría es igual a la de otra el resultado debe ser 0. <br>
     * 3. Al comparar una figura cuya categoría es mayor a la de otra el resultado debe ser 1.
     */
    @Test
    public void testCompararPorCategoria( )
    {
        setupEscenario1( );

        assertEquals( "La figura 1 debería ser menor", -1, figura1.compararPorCategoria( figura2 ) );
        assertEquals( "Las categorías deben ser iguales", 0, figura1.compararPorCategoria( figura1 ) );
        assertEquals( "La figura 2 debería ser mayor", 1, figura2.compararPorCategoria( figura1 ) );
    }

    /**
     * Verifica el método compararPorDificultad. <br>
     * <b> Métodos a probar: </b> <br>
     * compararPorDificultad. <br>
     * <b> Objetivo: </b> Probar que el método compararPorDificultad realiza la comparación de dos figuras de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al comparar una figura cuya dificultad es menor a la de otra el resultado debe ser -1. <br>
     * 2. Al comparar una figura cuya dificultad es igual a la de otra el resultado debe ser 0. <br>
     * 3. Al comparar una figura cuya dificultad es mayor a la de otra el resultado debe ser 1.
     */
    @Test
    public void testCompararPorDificultad( )
    {
        setupEscenario1( );

        assertEquals( "La figura 1 debería ser menor", -1, figura1.compararPorDificultad( figura2 ) );
        assertEquals( "Las dificultades deben ser iguales", 0, figura1.compararPorDificultad( figura1 ) );
        assertEquals( "La figura 2 debería ser mayor", 1, figura2.compararPorDificultad( figura1 ) );
    }

    /**
     * Verifica el método compararPorNombre. <br>
     * <b> Métodos a probar: </b> <br>
     * compararPorNombre. <br>
     * <b> Objetivo: </b> Probar que el método compararPorNombre realiza la comparación de dos figuras de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al comparar una figura cuyo nombre es menor al de la otra el resultado debe ser -1. <br>
     * 2. Al comparar una figura cuyo nombre es igual al de la otra el resultado debe ser 0. <br>
     * 3. Al comparar una figura cuyo nombre es mayor al de la otra el resultado debe ser 1.
     */
    @Test
    public void testCompararPorNombre( )
    {
        setupEscenario1( );

        assertEquals( "La figura 1 debería ser menor", -1, figura1.compararPorNombre( figura2 ) );
        assertEquals( "Los nombres deben ser iguales", 0, figura1.compararPorNombre( figura1 ) );
        assertEquals( "La figura 2 debería ser mayor", 1, figura2.compararPorNombre( figura1 ) );
    }

    /**
     * Este método se encarga de verificar el método desordenar<br>
     * <b> Métodos a probar: </b> <br>
     * desordenar<br>
     * <b> Objetivo: </b> Probar que se desordenan las fichas<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Después de estar ordenadas, las fichas en juego quedan desordenadas
     */
    @Test
    public void testDesordenar( )
    {
        setupEscenario1( );
        ArrayList<Ficha> fichasOrdenadas = figura1.obtenerFichas( );
        figura1.desordenar( );
        ArrayList<Ficha> fichasDesordenadas = figura1.obtenerFichas( );

        int cantidadCeros = 0;

        for( int i = 0; i < fichasOrdenadas.size( ); i++ )
        {
            int posOrdenada = ( ( Ficha )fichasOrdenadas.get( i ) ).obtenerPosicion( );
            int posDesordenada = ( ( Ficha )fichasDesordenadas.get( i ) ).obtenerPosicion( );

            if( posOrdenada - posDesordenada == 0 )
                cantidadCeros++;
        }
        assertTrue( "No se desordenaron las fichas", cantidadCeros == fichasOrdenadas.size( ) );
    }

    /**
     * Este método se encarga de verificar el método estaArmada<br>
     * <b> Métodos a probar: </b> <br>
     * estaArmado<br>
     * <b> Objetivo: </b> Probar que la figura se armó correctamente<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se verifica el vector de fichas armadas con
     */
    @Test
    public void testEstaArmada( )
    {
        setupEscenario1( );
        figura2.ordenarPorPosicion( );
        ArrayList<Ficha> fichas = figura2.obtenerFichas( );
        int[] fichasJugadas = new int[fichas.size( )];
        for( int i = 0; i < fichas.size( ); i++ )
        {
            fichasJugadas[ i ] = ( ( Ficha )fichas.get( i ) ).obtenerPosicion( );
        }
        assertTrue( "La figura no esta verificando correctamente que se encuentre armada", figura1.estaArmada( fichasJugadas ) );
    }

    /**
     * Este método se encarga de verificar el método ordenarPorColumnas<br>
     * <b> Métodos a probar: </b> <br>
     * ordenarPorColumnas<br>
     * <b> Objetivo: </b> Probar que se ordenen las fichas de la figura por sus columnas<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La cantidad de fichas es la misma después de ordenadas<br>
     * 2. Al ordenar las fichas por columnas la ficha con menor número de columna debe quedar de primera y la de mayor número de columna de última.<br>
     */
    @Test
    public void testOrdenarPorColumnas( )
    {
        setupEscenario1( );

        ArrayList<Ficha> fichas1 = figura1.obtenerFichas( );
        figura1.desordenar( );
        figura1.ordenarPorColumnas( );

        ArrayList<Ficha> fichas2 = figura1.obtenerFichas( );
        assertTrue( "La cantidad de fichas antes y después de ordenar debería ser igual", fichas1.size( ) == fichas2.size( ) );

        for( int i = 1; i < fichas2.size( ); i++ )
        {
            Ficha f0 = ( Ficha )fichas2.get( i - 1 );
            Ficha f1 = ( Ficha )fichas2.get( i );

            assertTrue( "No se ordenó bien por número de columna", f0.obtenerColumna( ) <= f1.obtenerColumna( ) );
        }
    }

    /**
     * Este método se encarga de verificar el método ordenarPorFilas<br>
     * <b> Métodos a probar: </b> <br>
     * ordenarPorFilas<br>
     * <b> Objetivo: </b> Probar que se ordenen las fichas de la figura por sus filas<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La cantidad de fichas es la misma después de ordenadas<br>
     * 2. Al ordenar las fichas por filas la ficha con menor número de fila debe quedar de primera y la de mayor número de columna de última.<br>
     */
    @Test
    public void testOrdenarPorFilas( )
    {
        setupEscenario1( );

        ArrayList<Ficha> fichas1 = figura1.obtenerFichas( );
        figura1.desordenar( );
        figura1.ordenarPorFilas( );

        ArrayList<Ficha> fichas2 = figura1.obtenerFichas( );
        assertTrue( "La cantidad de fichas antes y después de ordenar debería ser igual", fichas1.size( ) == fichas2.size( ) );

        for( int i = 1; i < fichas2.size( ); i++ )
        {
            Ficha f0 = ( Ficha )fichas2.get( i - 1 );
            Ficha f1 = ( Ficha )fichas2.get( i );

            assertTrue( "No se ordenó bien por número de fila", f0.obtenerFila( ) <= f1.obtenerFila( ) );
        }
    }

    /**
     * Este método se encarga de verificar el método ordenarPorRegion<br>
     * <b> Métodos a probar: </b> <br>
     * ordenarPorRegion<br>
     * <b> Objetivo: </b> Probar que se ordenen las fichas de la figura por la región a la que pertenece<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La cantidad de fichas es la misma después de ordenadas<br>
     * 2. Al ordenar las fichas por región la ficha con menor región debe quedar de primera y la de mayor región de última.<br>
     */
    @Test
    public void testOrdenarPorRegion( )
    {
        setupEscenario1( );

        ArrayList<Ficha> fichas1 = figura1.obtenerFichas( );
        figura1.desordenar( );
        figura1.ordenarPorRegion( );

        ArrayList<Ficha> fichas2 = figura1.obtenerFichas( );
        assertTrue( "La cantidad de fichas antes y después de ordenar debería ser igual", fichas1.size( ) == fichas2.size( ) );

        for( int i = 1; i < fichas2.size( ); i++ )
        {
            Ficha f0 = ( Ficha )fichas2.get( i - 1 );
            Ficha f1 = ( Ficha )fichas2.get( i );

            assertTrue( "No se ordenó bien por número de fila", f0.compararPorRegion( f1 ) <= 0 );
        }
    }

    /**
     * Este método se encarga de verificar el método ordenarPorPosicion<br>
     * <b> Métodos a probar: </b> <br>
     * ordenarPorPosicion<br>
     * <b> Objetivo: </b> Probar que se ordenen las fichas de la figura por la posición relativa a la figura<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La cantidad de fichas es la misma después de ordenadas<br>
     * 2. Al ordenar las fichas por posición la ficha con menor posición debe quedar de primera y la de mayor posición de última.<br>
     */
    @Test
    public void testOrdenarPorPosicion( )
    {
        setupEscenario1( );

        ArrayList<Ficha> fichas1 = figura1.obtenerFichas( );
        figura1.desordenar( );
        figura1.ordenarPorPosicion( );

        ArrayList<Ficha> fichas2 = figura1.obtenerFichas( );
        assertTrue( "La cantidad de fichas antes y después de ordenar debería ser igual", fichas1.size( ) == fichas2.size( ) );

        for( int i = 1; i < fichas2.size( ); i++ )
        {
            Ficha f0 = ( Ficha )fichas2.get( i - 1 );
            Ficha f1 = ( Ficha )fichas2.get( i );

            assertTrue( "No se ordenó bien por número de fila", f0.compararPorPosicion( f1 ) < 0 );
        }
    }

}
