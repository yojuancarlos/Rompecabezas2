/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelFigura.java 849 2007-01-15 21:51:38Z f-vela $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_rompecabezas
 * Autor: Manuel Muñoz - Oct 26, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package rompecabezas.interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import rompecabezas.mundo.Ficha;
import rompecabezas.mundo.Figura;

/**
 * Panel donde aparecen los espacios para colocar las fichas del rompecabezas
 */
public class PanelFigura extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * JScrollPane donde se coloca la figura con los espacios para jugar
     */
    private JScrollPane scroll;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor de la clase
     */
    public PanelFigura( )
    {
        setLayout( new BorderLayout( ) );
        scroll = new JScrollPane( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que es llamado cuando el usuario inicia un juego. Solo coloca los espacios para que se pueda jugar.
     * @param figura Figura que contiene las fichas. Diferente de null
     */
    public void pintarFigura( Figura figura )
    {
        JPanel panel = new JPanel( );
        setVisible( true );
        FichaTransferHandler picHandler = new FichaTransferHandler( );
        panel.setLayout( new GridBagLayout( ) );
        ArrayList<Ficha> fichas = figura.obtenerFichas( );
        int preferedHeigth = 0;
        int preferedWith = 0;
        for( int i = 0; i < figura.obtenerFichasAlto( ); i++ )
        {
            for( int j = 0; j < figura.obtenerFichasAncho( ); j++ )
            {
                Ficha ficha = ( Ficha )fichas.get( i * j );
                ImageIcon icono = new ImageIcon( ficha.obtenerRuta( ) );
                preferedHeigth = icono.getIconHeight( );
                preferedWith = icono.getIconWidth( );
                GridBagConstraints constraint = new GridBagConstraints( );
                constraint.gridy = i;
                constraint.gridx = j;
                constraint.fill = GridBagConstraints.BOTH;
                DTImagenFicha temp = new DTImagenFicha( null, icono.getIconWidth( ), icono.getIconHeight( ) );
                temp.setPreferredSize( new Dimension( icono.getIconWidth( ), icono.getIconHeight( ) ) );
                temp.setTransferHandler( picHandler );
                panel.add( temp, constraint );
            }
        }
        scroll.getViewport( ).add( panel );
        scroll.setPreferredSize( new Dimension( preferedWith * figura.obtenerFichasAncho( ) - 1, preferedHeigth * figura.obtenerFichasAlto( ) - 1 ) );
        add( scroll, BorderLayout.CENTER );

    }

    /**
     * Método que es llamado cuando el usuario quiere que se arme automáticamente la figura
     * @param figura Figura de juego. Diferente de null
     */
    public void armarFigura( Figura figura )
    {
        figura.ordenarPorPosicion( );
        ArrayList<Ficha> fichas = figura.obtenerFichas( );

        Component[] componentes = obtenerImagenesFicha( );
        for( int i = 0; i < componentes.length; i++ )
        {
            Ficha ficha = ( Ficha )fichas.get( i );
            ImageIcon icono = new ImageIcon( ficha.obtenerRuta( ) );
            Image image = icono.getImage( );
            Imagen imagen = new Imagen( image, ficha.obtenerPosicion( ) );
            DTImagenFicha imagenFicha = ( DTImagenFicha )componentes[ i ];
            imagenFicha.cambiarImagen( imagen );
        }
    }

    /**
     * Método que es llamado cuando el usuario selecciona una ficha de la lista de fichas en el panel de información
     * @param numeroFicha posición de la ficha que se quiere resaltar. Diferente de null
     * @return Retorna true si encontró la ficha en el panel, false de lo contrario
     */
    public boolean resaltarFicha( int numeroFicha )
    {
        Component[] componentes = obtenerImagenesFicha( );
        boolean encontrada = false;
        for( int i = 0; i < componentes.length && !encontrada; i++ )
        {
            DTImagenFicha imagen = ( DTImagenFicha )componentes[ i ];
            if( numeroFicha == imagen.obtenerPosicion( ) )
            {
                imagen.resaltar( );
                encontrada = true;
            }
        }
        return encontrada;
    }

    /**
     * Retorna un arreglo con las imágenes que están colocadas en la figura
     * @return Arreglo con objetos de tipo DTImagenFicha.
     */
    private Component[] obtenerImagenesFicha( )
    {
        // Se obtienen los componentes del panel
        Component[] componentes = getComponents( );
        // El único componente que tiene es el JScrollPane
        JScrollPane temp = ( JScrollPane )componentes[ 0 ];
        // A ese JScrollPane se le pide el viewport
        componentes = temp.getViewport( ).getComponents( );
        // Ese viewport solo tiene el panel y este contiene las imágenes
        JPanel panel = ( JPanel )componentes[ 0 ];
        componentes = panel.getComponents( );

        return componentes;
    }

    /**
     * Método que retorna las fichas que están colocadas en la figura
     * @return Arreglo de enteros con las posiciones de las fichas que están en la figura. Diferente de null.
     */
    public int[] obtenerFichasColocadas( )
    {
        Component[] fichas = obtenerImagenesFicha( );
        int[] posiciones = new int[fichas.length];
        for( int i = 0; i < fichas.length; i++ )
        {
            DTImagenFicha temp = ( DTImagenFicha )fichas[ i ];
            posiciones[ i ] = temp.obtenerPosicion( );
        }
        return posiciones;
    }

    /**
     * Método que quita el panel de la figura
     */
    public void quitarFigura( )
    {
        setVisible( false );
    }

}
