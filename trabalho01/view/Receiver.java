package view;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;

public class Receiver extends JPanel {
  public static JTextArea receiverTxt = new JTextArea();
  public static JTextArea receiverLayerTxt = new JTextArea();
  public static JTextArea receiverPhysTxt = new JTextArea();

  public Receiver(){
    super();
    this.setPreferredSize( new Dimension( Constants.WIDTH/3 , Constants.HEIGHT ) );
    init();
    this.setLayout( null );

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

    Receiver.receiverTxt.setOpaque(false);
    Receiver.receiverTxt.setBounds( 20 , 20 , 260 , 110 );
    Receiver.receiverTxt.setFont( Constants.FONT );
    Receiver.receiverTxt.setLineWrap( true );
    Receiver.receiverTxt.setEditable(false);

    receiver.add( Receiver.receiverTxt );

    this.add( receiver );
  }

  private void initReceiverAppLayer(){
    JScrollPane scrll = new JScrollPane( Receiver.receiverLayerTxt );

    scrll.setBounds( 20 , Constants.BLOONS_HEIGHT + 50 , Constants.BLOONS_WIDTH , 200 );
    Receiver.receiverLayerTxt.setOpaque( true );
    Receiver.receiverLayerTxt.setEditable( false );

    this.add( scrll );

  }

  private void initReceiverPhysicalLayer(){
    JScrollPane scrll = new JScrollPane( Receiver.receiverPhysTxt );

    scrll.setBounds( 20 , Constants.BLOONS_HEIGHT*2 + 100 , Constants.BLOONS_WIDTH , 150 );
    Receiver.receiverPhysTxt.setOpaque( true );
    Receiver.receiverPhysTxt.setEditable( false );

    this.add( scrll );

  }

}