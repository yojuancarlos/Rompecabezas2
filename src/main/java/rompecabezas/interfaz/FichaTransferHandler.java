/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_rompecabezas
 * Autor: Manuel Muñoz - Oct 6, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package rompecabezas.interfaz;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 * Clase que maneja la transferencia de los datos al hacer Drag and Drop
 */
public class FichaTransferHandler extends TransferHandler
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Tipo de dato que se quiere mover
     */
    private DataFlavor imagenFlavor = new DataFlavor( Imagen.class, "Imagen" );

    /**
     * La imagen que se quiere mover
     */
    private DTImagenFicha imagenFuente;

    /**
     * Variable que indica si se debería mover o no los datos
     */
    private boolean deberiaMover;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Causa la transferencia de un componente.
     * @param componente El componente que va a recibir la transferencia
     * @param transferible La información a importar
     * @return true si los datos fueron insertados en el componente, false de lo contrario
     */
    public boolean importData( JComponent componente, Transferable transferible )
    {
        Imagen lblImagen;
        if( canImport( componente, transferible.getTransferDataFlavors( ) ) )
        {
            DTImagenFicha pic = ( DTImagenFicha )componente;
            // Se usa para que no arrastre al mismo sitio
            if( imagenFuente == pic )
            {
                deberiaMover = false;
                return true;
            }
            try
            {
                lblImagen = ( Imagen )transferible.getTransferData( imagenFlavor );
                // Coloca el componente para la nueva imagen
                pic.setImage( lblImagen );
                return true;
            }
            catch( UnsupportedFlavorException ufe )
            {
                System.out.println( "importData: data flavor no soportado" );
            }
            catch( IOException ioe )
            {
                System.out.println( "importData: I/O exception" );
            }
        }
        return false;
    }

    /**
     * Crea un Transferable para utilizar como source para la transferencia de datos. Retorna una representación de los datos ha ser transferidos o null
     * @param componente El componente que tiene los datos ha ser transferidos
     * @return La representación de los datos ha ser transferidos o null
     */
    protected Transferable createTransferable( JComponent componente )
    {
        imagenFuente = ( DTImagenFicha )componente;
        imagenFuente.repaint( );
        deberiaMover = true;
        return new FichaTransferable( imagenFuente );
    }

    /**
     * Devuelve el tipo de acciones de transferencia soportados por la fuente (source)
     * @param componente El componente que tiene los datos ha ser transferidos
     * @return MOVE ya que solo se permite mover los datos y no copiarlos
     */
    public int getSourceActions( JComponent componente )
    {
        return MOVE;
    }

    /**
     * Llamado cuando los datos han sido movidos. Este método quita los datos de la fuente, ya que la acción permitida es mover
     * @param origen El componente que era el origen de los datos
     * @param datos Los datos transferidos
     * @param accion La acción que fue ejecutada
     */
    protected void exportDone( JComponent origen, Transferable datos, int accion )
    {
        if( deberiaMover && ( accion == MOVE ) )
        {
            imagenFuente.setImage( null );
        }
        imagenFuente = null;
    }

    /**
     * Indica si el componente debería aceptar recibir los datos representados por flavors.
     * @param componente El componente que recibiría la transferencia.
     * @param flavors Los tipos de datos disponibles
     * @return true si los datos pueden ser importados en el componente, false de lo contrario
     */
    public boolean canImport( JComponent componente, DataFlavor[] flavors )
    {
        ImagenFicha pic = ( ImagenFicha )componente;
        if( pic.tieneImagen( ) )
        {
            return false;
        }
        for( int i = 0; i < flavors.length; i++ )
        {
            if( imagenFlavor.equals( flavors[ i ] ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Clase que empaqueta la información a ser transferida
     */
    class FichaTransferable implements Transferable
    {

        /**
         * Imagen que muestra
         */
        private Imagen imagen;

        /**
         * Método constructor por parámetros
         */
        FichaTransferable( DTImagenFicha ficha )
        {
            imagen = ficha.obtenerImagen( );
        }

        /**
         * Devuelve un objeto que representa los datos ha ser transferidos.
         * @param flavor El tipo solicitado para los datos
         * @exception IOException Si los datos no están disponibles.
         * @exception UnsupportedFlavorException Si el tipo de datos solicitados no esta soportado
         */
        public Object getTransferData( DataFlavor flavor ) throws UnsupportedFlavorException
        {
            if( !isDataFlavorSupported( flavor ) )
            {
                throw new UnsupportedFlavorException( flavor );
            }
            return imagen;
        }

        /**
         * Devuelve un arreglo de objetos DataFlavors indicando como pueden ser proveídos los datos.
         * @return Un arreglo de DataFlavors indicando como se pueden transferir los datos
         */
        public DataFlavor[] getTransferDataFlavors( )
        {
            return new DataFlavor[]{ imagenFlavor };
        }

        /**
         * Retorna si los tipos de datos son soportados por este objeto
         * @param flavor El tipo de datos solicitado para los datos
         * @return boolean que indica si los tipos de datos son soportados
         */
        public boolean isDataFlavorSupported( DataFlavor flavor )
        {
            return imagenFlavor.equals( flavor );
        }
    }
}
