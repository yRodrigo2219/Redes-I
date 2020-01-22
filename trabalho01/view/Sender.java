package view;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.*;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import model.AplicacaoTransmissora;

public class Sender extends JPanel {
  public JTextArea senderTxt = new JTextArea();

  public Sender(){
    super();
    this.setPreferredSize( new Dimension( Constants.WIDTH/3 , Constants.HEIGHT ) );
    init();
    this.setBackground( Color.RED );

  }

  private void init(){
    initSenderApp();
    initSenderAppLayer();
    initSenderPhysicalLayer();

  }

  private void initSenderApp(){
    Image img = new ImageIcon( "./img/greenChat.png" ).getImage().getScaledInstance( Constants.BLOONS_WIDTH , Constants.BLOONS_HEIGHT , java.awt.Image.SCALE_SMOOTH );
    JLabel sender = new JLabel( new ImageIcon( img ) );
    sender.setBounds( 10 , 15 , Constants.BLOONS_WIDTH , Constants.BLOONS_HEIGHT );

    this.senderTxt.setOpaque( false );
    this.senderTxt.setBounds( 20 , 20 , 260 , 110 );
    this.senderTxt.setFont( Constants.FONT );
    this.senderTxt.setLineWrap( true );

    KeyStroke enterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
    this.senderTxt.getInputMap( WHEN_FOCUSED ).put( enterStroke , enterStroke.toString() );
    this.senderTxt.getActionMap().put( enterStroke.toString() , new AbstractAction() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        sendMsg( senderTxt.getText() );

      }
    });
    this.senderTxt.setDocument( new PlainDocument() {
      @Override
      public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null || senderTxt.getText().length() >= Constants.CHAR_LIMIT) {
            return;
        }
 
        super.insertString(offs, str, a);
      }
    });

    sender.add( this.senderTxt );

    this.add(sender);

  }

  private void initSenderAppLayer(){

  }

  private void initSenderPhysicalLayer(){

  }

  private void sendMsg( String msg ){
    AplicacaoTransmissora.aplicacaoTransmissora( msg );

  }

}