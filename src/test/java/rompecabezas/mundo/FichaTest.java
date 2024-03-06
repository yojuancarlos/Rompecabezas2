package rompecabezas.mundo;

import static org.junit.Assert.*;
import org.junit.Test;

public class FichaTest {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ficha para realizar las pruebas
     */
    private Ficha ficha1;

    /**
     * Ficha para realizar las pruebas
     */
    private Ficha ficha2;

    /**
     * Ficha para realizar las pruebas
     */
    private Ficha ficha3;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye tres fichas con valores diferentes
     */
    private void setupEscenario1( )
    {
        ficha1 = new Ficha( "ruta1", 1, 0, 0, Ficha.ESQUINA );
        ficha2 = new Ficha( "ruta2", 2, 0, 1, Ficha.BORDE );
        ficha3 = new Ficha( "ruta3", 3, 1, 0, Ficha.INTERNA );
    }

    /**
     * Verifica el constructor. <br>
     * <b> Métodos a probar: </b> <br>
     * Ficha (constructor), obtenerRuta, obtenerColumna, obtenerFila, obtenerPosicion, obtenerRegion<br>
     * <b> Objetivo: </b> Probar que el constructor crea una ficha de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear una ficha los atributos del objeto deben quedar inicializados con el valor correcto.
     */
    @Test
    public void testFicha( )
    {
        setupEscenario1( );

        assertEquals( "La ruta de la ficha está equivocada", "ruta1", ficha1.obtenerRuta( ) );
        assertEquals( "La columna de la ficha está equivocada", 0, ficha1.obtenerColumna( ) );
        assertEquals( "La fila de la ficha está equivocada", 0, ficha1.obtenerFila( ) );
        assertEquals( "La posición de la ficha en la figura está equivocada", 1, ficha1.obtenerPosicion( ) );
        assertEquals( "La región de la ficha está equivocada", Ficha.ESQUINA, ficha1.obtenerRegion( ) );

        assertEquals( "La ruta de la ficha está equivocada", "ruta2", ficha2.obtenerRuta( ) );
        assertEquals( "La columna de la ficha está equivocada", 1, ficha2.obtenerColumna( ) );
        assertEquals( "La fila de la ficha está equivocada", 0, ficha2.obtenerFila( ) );
        assertEquals( "La posición de la ficha en la figura está equivocada", 2, ficha2.obtenerPosicion( ) );
        assertEquals( "La región de la ficha está equivocada", Ficha.BORDE, ficha2.obtenerRegion( ) );

        assertEquals( "La ruta de la ficha está equivocada", "ruta3", ficha3.obtenerRuta( ) );
        assertEquals( "La columna de la ficha está equivocada", 0, ficha3.obtenerColumna( ) );
        assertEquals( "La fila de la ficha está equivocada", 1, ficha3.obtenerFila( ) );
        assertEquals( "La posición de la ficha en la figura está equivocada", 3, ficha3.obtenerPosicion( ) );
        assertEquals( "La región de la ficha está equivocada", Ficha.INTERNA, ficha3.obtenerRegion( ) );

    }

    /**
     * Verifica el método compararPorColumna. <br>
     * <b> Métodos a probar: </b> <br>
     * compararPorColumna. <br>
     * <b> Objetivo: </b> Probar que el método compararPorColumna realiza la comparación de dos fichas de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al comparar una ficha cuya columna es menor a la de otra el resultado debe ser -1. <br>
     * 2. Al comparar una ficha cuya columna es igual a la de otra el resultado debe ser 0. <br>
     * 3. Al comparar una ficha cuya columna es mayor a la de otra el resultado debe ser 1.
     */
    @Test
    public void testCompararPorColumna( )
    {
        setupEscenario1( );

        assertEquals( "La ficha 1 debería ser menor", -1, ficha1.compararPorColumna( ficha2 ) );
        assertEquals( "Las columnas de las fichas 1 y 3 deberían ser iguales", 0, ficha1.compararPorColumna( ficha3 ) );
        assertEquals( "La ficha 2 debería ser mayor", 1, ficha2.compararPorColumna( ficha3 ) );
    }

    /**
     * Verifica el método compararPorFila. <br>
     * <b> Métodos a probar: </b> <br>
     * compararPorFila. <br>
     * <b> Objetivo: </b> Probar que el método compararPorFila realiza la comparación de dos fichas de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al comparar una ficha cuya fila es menor a la de otra el resultado debe ser -1. <br>
     * 2. Al comparar una ficha cuya fila es igual a la de otra el resultado debe ser 0. <br>
     * 3. Al comparar una ficha cuya fila es mayor a la de otra el resultado debe ser 1.
     */
    @Test
    public void testCompararPorFila( )
    {
        setupEscenario1( );

        assertEquals( "La ficha 2 debería ser menor", -1, ficha2.compararPorFila( ficha3 ) );
        assertEquals( "Las filas de las fichas 1 y 2 deberían ser iguales", 0, ficha1.compararPorFila( ficha2 ) );
        assertEquals( "La ficha 3 debería ser mayor", 1, ficha3.compararPorFila( ficha2 ) );
    }

    /**
     * Verifica el método compararPorRegion. <br>
     * <b> Métodos a probar: </b> <br>
     * compararPorRegion. <br>
     * <b> Objetivo: </b> Probar que el método compararPorRegion realiza la comparación de dos fichas de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al comparar una ficha cuya región es menor a la de otra el resultado debe ser -1. <br>
     * 2. Al comparar una ficha cuya región es igual a la de otra el resultado debe ser 0. <br>
     * 3. Al comparar una ficha cuya región es mayor a la de otra el resultado debe ser 1.
     */
    @Test
    public void testCompararPorRegion( )
    {
        setupEscenario1( );

        assertEquals( "La ficha 1 debería ser menor", -1, ficha1.compararPorRegion( ficha2 ) );
        assertEquals( "La ficha 2 debería ser mayor", 1, ficha2.compararPorRegion( ficha1 ) );
        assertEquals( "La ficha 2 debería ser menor", -1, ficha2.compararPorRegion( ficha3 ) );
        assertEquals( "La ficha 3 debería ser mayor", 1, ficha3.compararPorRegion( ficha2 ) );
        assertEquals( "Las regiones de las fichas 1 y 1 deberían ser iguales", 0, ficha1.compararPorFila( ficha1 ) );

    }

    /**
     * Verifica el método compararPorPosicion. <br>
     * <b> Métodos a probar: </b> <br>
     * compararPorPosicion. <br>
     * <b> Objetivo: </b> Probar que el método compararPorPosicion realiza la comparación de dos fichas de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al comparar una ficha cuya posición es menor a la de otra el resultado debe ser -1. <br>
     * 2. Al comparar una ficha cuya posición es igual a la de otra el resultado debe ser 0. <br>
     * 3. Al comparar una ficha cuya posición es mayor a la de otra el resultado debe ser 1.
     */
    @Test
    public void testCompararPorPosicion( )
    {
        setupEscenario1( );

        assertEquals( "La ficha 1 debería ser menor", -1, ficha1.compararPorPosicion( ficha2 ) );
        assertEquals( "Las posiciones de las fichas deberían ser iguales", 0, ficha1.compararPorPosicion( ficha1 ) );
        assertEquals( "La ficha 3 debería ser mayor", 1, ficha3.compararPorPosicion( ficha2 ) );
    }

}
