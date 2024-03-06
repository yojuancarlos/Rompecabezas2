/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelOrdenamientoFichas.java 855 2007-01-16 19:14:13Z f-vela $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_rompecabezas
 * Autor: Manuel Muñoz - Oct 5, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package rompecabezas.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import rompecabezas.mundo.Ficha;

/**
 * Panel que muestra la información de la figura en juego
 */
public class PanelOrdenamientoFichas extends JPanel implements ListSelectionListener, ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Acción que se ejecuta cuando se presiona el botón de ordenar las fichas del rompecabezas por columnas
     */
    private static final String ACCION_ORDENAR_COLUMNAS = "ACCION_ORDENAR_COLUMNAS";

    /**
     * Acción que se ejecuta cuando se presiona el botón de ordenar las fichas del rompecabezas por filas
     */
    private static final String ACCION_ORDENAR_FILAS = "ACCION_ORDENAR_FILAS";

    /**
     * Acción que se ejecuta cuando se presiona el botón de ordenar las fichas del rompecabezas por regiones
     */
    private static final String ACCION_ORDENAR_REGIONES = "ACCION_ORDENAR_REGIONES";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Botón para ordenar las figuras por columnas
     */
    private JButton btnOrdenarColumnas;

    /**
     * Botón para ordenar las figuras por filas
     */
    private JButton btnOrdenarFilas;

    /**
     * Botón para ordenar las figuras por sus regiones
     */
    private JButton btnOrdenarRegiones;

    /**
     * Lista que contiene las fichas del rompecabezas
     */
    private JList listaFichas;

    /**
     * JScrollPane que contiene la lista de las figuras del rompecabezas
     */
    private JScrollPane scroll;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal de la interfaz gráfica
     */
    private InterfazRompecabezas principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor de la clase
     * @param interfaz Clase principal de la interfaz gráfica
     */
    public PanelOrdenamientoFichas( InterfazRompecabezas interfaz )
    {
        principal = interfaz;
        setLayout( new GridBagLayout( ) );
        GridBagConstraints constraint = null;

        TitledBorder titulo;
        titulo = BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder( EtchedBorder.LOWERED ), "Controles de Fichas" );
        titulo.setTitleJustification( TitledBorder.LEFT );
        setBorder( titulo );

        btnOrdenarColumnas = new JButton( "Ordenar por Columnas" );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets( 5, 0, 5, 10 );
        constraint.fill = GridBagConstraints.BOTH;
        btnOrdenarColumnas.setActionCommand( ACCION_ORDENAR_COLUMNAS );
        btnOrdenarColumnas.addActionListener( this );
        btnOrdenarColumnas.setEnabled( false );
        add( btnOrdenarColumnas, constraint );

        btnOrdenarFilas = new JButton( "Ordenar por Filas" );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets( 5, 0, 5, 10 );
        constraint.fill = GridBagConstraints.BOTH;
        btnOrdenarFilas.setActionCommand( ACCION_ORDENAR_FILAS );
        btnOrdenarFilas.addActionListener( this );
        btnOrdenarFilas.setEnabled( false );
        add( btnOrdenarFilas, constraint );

        btnOrdenarRegiones = new JButton( "Ordenar por Regiones" );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.insets = new Insets( 5, 0, 5, 10 );
        constraint.fill = GridBagConstraints.BOTH;
        btnOrdenarRegiones.addActionListener( this );
        btnOrdenarRegiones.setActionCommand( ACCION_ORDENAR_REGIONES );
        btnOrdenarRegiones.setEnabled( false );
        add( btnOrdenarRegiones, constraint );

        listaFichas = new JList( );

        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.gridheight = 5;
        constraint.gridwidth = 10;
        constraint.weighty = 1.0;
        constraint.fill = GridBagConstraints.BOTH;
        scroll = new JScrollPane( listaFichas );
        listaFichas.addListSelectionListener( this );
        add( scroll, constraint );
    }


    /**
     * Método que actualiza la lista de fichas
     * @param fichas Nueva lista de las fichas que se quiere mostrar. Diferente de null
     */
    public void actualizarListaFichas( ArrayList<Ficha> fichas )
    {
        listaFichas.setListData( fichas.toArray( ) );
        scroll.setPreferredSize( new Dimension( 400, 50 ) );
    }

    /**
     * Método que activa los botones de acciones sobre las fichas
     */
    public void activarBotones( )
    {
        btnOrdenarColumnas.setEnabled( true );
        btnOrdenarFilas.setEnabled( true );
        btnOrdenarRegiones.setEnabled( true );
    }

    /**
     * Método que desactiva los botones de acciones sobre las fichas
     */
    public void desactivarBotones( )
    {
        btnOrdenarColumnas.setEnabled( false );
        btnOrdenarFilas.setEnabled( false );
        btnOrdenarRegiones.setEnabled( false );
    }

    /**
     * Método que es llamado cuando se cambia lo que esta seleccionado en la lista. Al seleccionarse una ficha de la lista esta se resalta.
     */
    public void valueChanged( ListSelectionEvent e )
    {
        if( listaFichas.getSelectedValue( ) != null )
        {
            Ficha ficha = ( Ficha )listaFichas.getSelectedValue( );
            principal.resaltarFicha( ficha );

        }
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */

    public void actionPerformed( ActionEvent e )
    {
        if( ACCION_ORDENAR_COLUMNAS.equals( e.getActionCommand( ) ) )
        {
            principal.ordenarFichasPorColumnas( );

        }
        else if( ACCION_ORDENAR_FILAS.equals( e.getActionCommand( ) ) )
        {
            principal.ordenarFichasPorFilas( );

        }
        else if( ACCION_ORDENAR_REGIONES.equals( e.getActionCommand( ) ) )
        {
            principal.ordenarFichasPorRegiones( );

        }
    }

}