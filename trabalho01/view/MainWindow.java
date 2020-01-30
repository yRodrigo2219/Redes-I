package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;

public class MainWindow extends JFrame{
  public static Sender sender = new Sender();
  public static CommunicationLayer comm = new CommunicationLayer();
  public static Receiver rec = new Receiver();

  public MainWindow(){
    super();
    this.setTitle( Constants.NAME );
    this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    this.setSize( Constants.WIDTH , Constants.HEIGHT );
    this.setResizable( false );
    this.setLocationRelativeTo( null );
    this.setLayout( new FlowLayout( 0 , 0 , 0 ) );
    this.init();

  }

  private void init(){
    this.add( sender );
    this.add( comm );
    this.add( rec );

  }

  public static void error( String msg ){
    JOptionPane.showMessageDialog( null , "Error: " + msg , "Error" , JOptionPane.ERROR_MESSAGE );

    // re-estabelece a entrada de outras mensagens
    Sender.senderTxt.setEditable( true );
    Sender.senderTxt.setFocusable( true );
    Sender.senderTxt.requestFocusInWindow();

    // re-estabelece a troca de codificacao
    CommunicationLayer.jDrop.setEnabled( true );

  }

}