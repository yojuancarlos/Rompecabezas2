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

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 * Una subclase de ImagenFicha que soporta la transferencia de datos
 */

public class DTImagenFicha extends ImagenFicha implements MouseMotionListener
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Primer evento que le llega del mouse. Se tiene que guardar el primero, ya que cada vez que se mueva el componente, se recibirán eventos nuevos.
     */
    private MouseEvent primerEventoMouse = null;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método constructor por parámetros
     * @param imagen
     * @param alto Alto que va a tener la casilla donde puede o no ir la figura
     * @param ancho Ancho que va a tener la casilla donde puede ir o no la figura
     */
    public DTImagenFicha( Imagen imagen, int ancho, int alto )
    {
        super( imagen, ancho, alto );
        addMouseMotionListener( this );
    }

    /**
     * Cambia la imagen que se esta mostrando en el componente
     * @param imagen Nueva imagen que se quiere mostrar. Puede ser null
     */
    public void setImage( Imagen imagen )
    {
        this.imagen = imagen;
        this.repaint( );
    }

    /**
     * Método que se llama cuando se hace click sobre un componente
     */
    public void mousePressed( MouseEvent e )
    {
        if( imagen == null )
            return;
        primerEventoMouse = e;
        e.consume( );
    }

    /**
     * Método que se llama cuando se arrastra un componente
     */
    public void mouseDragged( MouseEvent evento )
    {
        if( imagen != null && primerEventoMouse != null )
        {
            evento.consume( );

            int action = TransferHandler.MOVE;

            int dx = Math.abs( evento.getX( ) - primerEventoMouse.getX( ) );
            int dy = Math.abs( evento.getY( ) - primerEventoMouse.getY( ) );

            if( dx > 5 || dy > 5 )
            {

                JComponent c = ( JComponent )evento.getSource( );
                TransferHandler handler = c.getTransferHandler( );

                handler.exportAsDrag( c, primerEventoMouse, action );
                primerEventoMouse = null;
            }
        }

    }

    /**
     * Método que se ejecuta cuando se mueve el mouse sobre este componente
     */
    public void mouseMoved( MouseEvent e )
    {
        primerEventoMouse = null;
    }

    /**
     * Retorna la imagen que contiene
     * @return La imagen que contiene. Puede ser null
     */
    public Imagen obtenerImagen( )
    {
        return imagen;
    }

    /**
     * Método que se llama cuando se quiere resaltar la ficha
     */
    public void resaltar( )
    {
        super.resaltar( );
    }

    /**
     * Retorna la posición de la ficha actual
     */
    public int obtenerPosicion( )
    {
        return super.obtenerPosicion( );
    }

    /**
     * Cambia la imagen que se esta mostrando
     */
    public void cambiarImagen( Imagen imagen )
    {
        super.cambiarImagen( imagen );
    }
}
