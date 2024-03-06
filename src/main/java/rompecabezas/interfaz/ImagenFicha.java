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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.accessibility.Accessible;
import javax.swing.JComponent;

/**
 * Clase que representa gráficamente una ficha del rompecabezas
 */
public class ImagenFicha extends JComponent implements MouseListener, FocusListener, Accessible
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Imagen que quiere ser mostrada
     */
    public Imagen imagen;

    /**
     * Antigua posición en x
     */
    private int oldX;

    /**
     * Antigua posición en Y
     */
    private int oldY;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Método constructor por parámetros de la clase ImagenFicha
     * @param imagen2 Imagen a mostrar
     * @param alto Alto que va a tener la casilla donde puede o no ir la figura
     * @param ancho Ancho que va a tener la casilla donde puede ir o no la figura
     */
    public ImagenFicha( Imagen imagen2, int ancho, int alto )
    {
        this.imagen = imagen2;
        setFocusable( true );
        addMouseListener( this );
        addFocusListener( this );
        oldX = ancho;
        oldY = alto;
    }

    /**
     * Método que es llamado cuando se envía un evento de click (presionar y soltar) con el mouse
     */
    public void mouseClicked( MouseEvent e )
    {
        requestFocusInWindow( );
    }

    /**
     * Método que es llamado cuando se presiona un botón del mouse en este componente
     */
    public void mousePressed( MouseEvent e )
    {
    }

    /**
     * Método que es llamando cuando se suelta el botón del mouse en este componente
     */
    public void mouseReleased( MouseEvent e )
    {
    }

    /**
     * Método que es llamado cuando el mouse entra a este componente
     */
    public void mouseEntered( MouseEvent e )
    {
    }

    /**
     * Método que es llamando cuando el mouse sale de este componente
     */
    public void mouseExited( MouseEvent e )
    {
    }

    /**
     * Método que se llama cuando el componente tiene el foco del teclado
     */
    public void focusGained( FocusEvent e )
    {
        // Indica que el componente tiene focus con un borde rojo
        this.repaint( );
    }

    /**
     * Método que se llama cuando el componente pierde el foco del teclado
     */
    public void focusLost( FocusEvent e )
    {
        // Indica que el componente perdió el foco con un borde rojo
        this.repaint( );
    }

    /**
     * Método que es llamado para pintar el componente
     */
    protected void paintComponent( Graphics graphics )
    {
        Graphics g = graphics.create( );

        Image laImagen = null;
        int width = oldX;
        int height = oldY;

        if( imagen != null )
        {
            laImagen = imagen.obtenerImagen( );
            width = laImagen.getWidth( this );
            height = laImagen.getHeight( this );
            oldX = laImagen.getWidth( this );
            oldY = laImagen.getHeight( this );
        }

        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, width, height );

        if( imagen != null )
        {
            g.drawImage( laImagen, 0, 0, width, height, this );
        }

        // Dibuja el borde
        if( isFocusOwner( ) )
        {
            g.setColor( Color.RED );
        }
        else
        {
            g.setColor( Color.BLACK );
        }
        g.drawRect( 0, 0, width, height );
        g.dispose( );
    }

    /**
     * Revisa si el componente tiene actualmente una imagen asociada
     * @return True si esta mostrando una imagen, false de lo contrario
     */
    public boolean tieneImagen( )
    {
        if( imagen == null )
            return false;
        return true;
    }

    /**
     * Método que resalta la imagen
     */
    public void resaltar( )
    {
        this.requestFocus( );
        this.repaint( );
    }

    /**
     * Método que retorna la posición de la ficha
     * @return Entero mayor o igual a cero
     */
    public int obtenerPosicion( )
    {
        if( imagen == null )
            return -1;
        return imagen.obtenerPosicion( );
    }

    /**
     * Cambia la imagen que se quiere mostrar
     * @param imagen2 Nueva imagen a mostrar. Puede ser null
     */
    public void cambiarImagen( Imagen imagen2 )
    {
        imagen = imagen2;
    }

}
