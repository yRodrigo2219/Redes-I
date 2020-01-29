package view;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;

public class CommunicationLayer extends JPanel {
  private static final String[] types = { "Cod. Binaria" , "Cod. Manchester" , "Cod. Manchester Dif." };
  public static final JComboBox<String> jDrop = new JComboBox<>( types );
  public static JSlider jSlider = new JSlider( 1 , 10 , 1 );
  public static Canvas canvas = new Canvas();

  public CommunicationLayer(){
    super();
    this.setPreferredSize( new Dimension( Constants.WIDTH/3-1 , Constants.HEIGHT ) );
    init();
    this.setLayout( null );

  }

  private void init(){
    initControlPanel();
    initCommLayer();

  }

  private void initControlPanel(){
    JLabel txt = new JLabel( "Escolha a codificacao desejada:" );
    txt.setBounds( 20 , 20 , Constants.BLOONS_WIDTH , 40 );
    txt.setFont( new Font( "Arial" , 0 , 20 ) );

    CommunicationLayer.jDrop.setBounds( 20 , 60 , Constants.BLOONS_WIDTH , 50 );
    CommunicationLayer.jDrop.setFont( Constants.FONT );

    JTextArea help =  new JTextArea( "Ajuda:\n"+ 
                                     " - Selecione o balao verde para escrever\n"+
                                     " - 'Enter' para enviar a mensagem\n"+
                                     " - Apenas ASCII suportado" );
    help.setBounds( 20 , 125 , Constants.BLOONS_WIDTH , 85 );
    help.setOpaque( false );
    help.setEditable( false );
    help.setFont( new Font( "Arial" , 0 , 14 ) );

    JLabel txt2 = new JLabel( "Selecione a velocidade:" );
    txt2.setBounds( 20 , 430 , Constants.BLOONS_WIDTH , 40 );
    txt2.setFont( new Font( "Arial" , 0 , 20 ) );

    CommunicationLayer.jSlider.setBounds( 20 , 470 , Constants.BLOONS_WIDTH , 20 );

    this.add( txt );
    this.add( CommunicationLayer.jDrop );
    this.add( help );
    this.add( txt2 );
    this.add( CommunicationLayer.jSlider );
    
  }

  private void initCommLayer(){
    canvas.setBounds( 0 , Constants.BLOONS_HEIGHT * 2 + 100 , Constants.WIDTH/3-1 , 150 );

    this.add( canvas );

  }

}