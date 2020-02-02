/* ***************************************************************
* Autor: Rodrigo Santos do Carmo
* Matricula: 201810821
* Inicio: 23/01/2020
* Ultima alteracao: 02/02/2020
* Nome: Simulacao da Camada Fisica
* Funcao: Simula o funcionamento da camada fisica em uma rede
*************************************************************** */

package view;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import util.Constants;

public class Receiver extends JPanel {
  public static JTextArea receiverTxt = new JTextArea();
  public static JTextArea receiverLayerTxt = new JTextArea();
  public static JTextArea receiverPhysTxt = new JTextArea();

  /* ***************************************************************
  * Metodo: Receiver
  * Funcao: construtor da classe
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public Receiver(){
    super();
    this.setPreferredSize( new Dimension( Constants.WIDTH/3 , Constants.HEIGHT ) );
    init();
    this.setLayout( null );

  }

  /* ***************************************************************
  * Metodo: init
  * Funcao: inicia alguns componentes necessarios
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void init(){
    initReceiverApp();
    initReceiverAppLayer();
    initReceiverPhysicalLayer();

  }

  /* ***************************************************************
  * Metodo: initReceiverApp
  * Funcao: inicia o aplicativo receptor da simulacao
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void initReceiverApp(){
    Image img = new ImageIcon( "./img/whiteChat.png" ).getImage().getScaledInstance( Constants.BLOONS_WIDTH , Constants.BLOONS_HEIGHT , java.awt.Image.SCALE_SMOOTH );
    JLabel receiver = new JLabel( new ImageIcon( img ) );
    receiver.setBounds( 10 , 15 , Constants.BLOONS_WIDTH , Constants.BLOONS_HEIGHT );

    Receiver.receiverTxt.setOpaque(false);
    Receiver.receiverTxt.setBounds( 20 , 20 , 260 , 110 );
    Receiver.receiverTxt.setFont( Constants.FONT );
    Receiver.receiverTxt.setLineWrap( true );
    Receiver.receiverTxt.setEditable(false);

    receiver.add( Receiver.receiverTxt );

    this.add( receiver );
  }

  /* ***************************************************************
  * Metodo: initReceiverAppLayer
  * Funcao: inicia a camada de aplicacao receptora do aplicativo da simulacao
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void initReceiverAppLayer(){
    JScrollPane scrll = new JScrollPane( Receiver.receiverLayerTxt );

    scrll.setBounds( 20 , Constants.BLOONS_HEIGHT + 50 , Constants.BLOONS_WIDTH , 200 );
    Receiver.receiverLayerTxt.setOpaque( true );
    Receiver.receiverLayerTxt.setEditable( false );

    this.add( scrll );

  }

  /* ***************************************************************
  * Metodo: initReceiverPhysicalLayer
  * Funcao: inicia a camada fisica de recebimento simulacao
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void initReceiverPhysicalLayer(){
    JScrollPane scrll = new JScrollPane( Receiver.receiverPhysTxt );

    scrll.setBounds( 20 , Constants.BLOONS_HEIGHT*2 + 100 , Constants.BLOONS_WIDTH , 150 );
    Receiver.receiverPhysTxt.setOpaque( true );
    Receiver.receiverPhysTxt.setEditable( false );

    this.add( scrll );

  }

}