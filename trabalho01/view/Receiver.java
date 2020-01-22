package view;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

public class Receiver extends JPanel {
  public JTextArea receiverTxt = new JTextArea();

  public Receiver(){
    super();
    this.setPreferredSize( new Dimension( Constants.WIDTH/3 , Constants.HEIGHT ) );
    init();
    this.setBackground( Color.YELLOW );

  }

  private void init(){
    initReceiverApp();
    initReceiverAppLayer();
    initReceiverPhysicalLayer();

  }

  private void initReceiverApp(){
    Image img = new ImageIcon( "./img/whiteChat.png" ).getImage().getScaledInstance( Constants.BLOONS_WIDTH , Constants.BLOONS_HEIGHT , java.awt.Image.SCALE_SMOOTH );
    JLabel receiver = new JLabel( new ImageIcon( img ) );
    receiver.setBounds( 10 , 15 , Constants.BLOONS_WIDTH , Constants.BLOONS_HEIGHT );

    this.receiverTxt.setOpaque( false );
    this.receiverTxt.setBounds( 20 , 20 , 260 , 110 );
    this.receiverTxt.setFont( Constants.FONT );
    this.receiverTxt.setLineWrap( true );
    this.receiverTxt.setEditable(false);

    receiver.add( this.receiverTxt );

    this.add( receiver );
  }

  private void initReceiverAppLayer(){

  }

  private void initReceiverPhysicalLayer(){

  }

}