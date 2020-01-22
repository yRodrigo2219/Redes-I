package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

public class CommunicationLayer extends JPanel {
  public CommunicationLayer(){
    super();
    this.setPreferredSize( new Dimension( Constants.WIDTH/3-1 , Constants.HEIGHT ) );
    init();
    this.setBackground( Color.BLUE );

  }

  private void init(){
    initSendButton();
    initCod();
    initCommLayer();

  }

  private void initSendButton(){
    JButton btn = new JButton( "Enviar" );

    this.add( btn );
  }

  private void initCod(){

  }

  private void initCommLayer(){

  }

}