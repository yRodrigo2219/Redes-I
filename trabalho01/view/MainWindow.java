package view;

import javax.swing.JFrame;
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

}