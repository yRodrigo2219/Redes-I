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
import javax.swing.KeyStroke;
import javax.swing.text.*;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import util.Constants;
import java.awt.event.ActionEvent;
import model.AplicacaoTransmissora;

public class Sender extends JPanel {
  public static JTextArea senderLayerTxt = new JTextArea();
  public static JTextArea senderPhysTxt = new JTextArea();
  public static JTextArea senderTxt = new JTextArea();

  /* ***************************************************************
  * Metodo: Sender
  * Funcao: construtor da classe
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public Sender(){
    super();
    this.setPreferredSize( new Dimension( Constants.WIDTH/3 , Constants.HEIGHT ) );
    init();
    this.setLayout( null );

  }

  private void init(){
    initSenderApp();
    initSenderAppLayer();
    initSenderPhysicalLayer();

  }

  /* ***************************************************************
  * Metodo: initSenderApp
  * Funcao: inicia o aplicativo que envia as mensagens na simulacao
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void initSenderApp(){
    Image img = new ImageIcon( "./img/greenChat.png" ).getImage().getScaledInstance( Constants.BLOONS_WIDTH , Constants.BLOONS_HEIGHT , java.awt.Image.SCALE_SMOOTH );
    JLabel sender = new JLabel( new ImageIcon( img ) );
    sender.setBounds( 10 , 15 , Constants.BLOONS_WIDTH , Constants.BLOONS_HEIGHT );

    senderTxt.setOpaque( false );
    senderTxt.setBounds( 20 , 20 , 260 , 110 );
    senderTxt.setFont( Constants.FONT );
    senderTxt.setLineWrap( true );

    KeyStroke enterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
    senderTxt.getInputMap( WHEN_FOCUSED ).put( enterStroke , enterStroke.toString() );
    senderTxt.getActionMap().put( enterStroke.toString() , new AbstractAction() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        sendMsg( senderTxt.getText() );

      }
    });
    senderTxt.setDocument( new PlainDocument() {
      @Override
      public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null || senderTxt.getText().length() >= Constants.CHAR_LIMIT) {
            return;
        }
 
        super.insertString(offs, str, a);
      }
    });

    sender.add( senderTxt );

    this.add( sender );

  }

  /* ***************************************************************
  * Metodo: initSenderAppLayer
  * Funcao: inicia a camada de envio do aplicativo
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void initSenderAppLayer(){
    JScrollPane scrll = new JScrollPane( Sender.senderLayerTxt );

    scrll.setBounds( 20 , Constants.BLOONS_HEIGHT + 50 , Constants.BLOONS_WIDTH , 200 );
    Sender.senderLayerTxt.setOpaque( true );
    Sender.senderLayerTxt.setEditable( false );

    this.add( scrll );

  }

  /* ***************************************************************
  * Metodo: initSenderPhysicalLayer
  * Funcao: inicia a camada fisica de envio
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void initSenderPhysicalLayer(){
    JScrollPane scrll = new JScrollPane( Sender.senderPhysTxt );

    scrll.setBounds( 20 , Constants.BLOONS_HEIGHT*2 + 100 , Constants.BLOONS_WIDTH , 150 );
    Sender.senderPhysTxt.setOpaque( true );
    Sender.senderPhysTxt.setEditable( false );

    this.add( scrll );

  }

  /* ***************************************************************
  * Metodo: sendMsg
  * Funcao: envia uma mensagem pela simulacao
  * Parametros: recebe a mensagem que deve ser enviada
  * Retorno: void
  *************************************************************** */
  private void sendMsg( String msg ){
    AplicacaoTransmissora.aplicacaoTransmissora( msg );

  }

}