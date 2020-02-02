/* ***************************************************************
* Autor: Rodrigo Santos do Carmo
* Matricula: 201810821
* Inicio: 23/01/2020
* Ultima alteracao: 02/02/2020
* Nome: Simulacao da Camada Fisica
* Funcao: Simula o funcionamento da camada fisica em uma rede
*************************************************************** */

package view;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import util.Constants;

public class CommunicationLayer extends JPanel {
  private static final String[] types = { "Cod. Binaria" , "Cod. Manchester" , "Cod. Manchester Dif." };
  public static final JComboBox<String> jDrop = new JComboBox<>( types );
  public static JSlider jSlider = new JSlider( 1 , 25 , 1 );
  public static Canvas canvas = new Canvas();

  /* ***************************************************************
  * Metodo: CommunicationLayer
  * Funcao: construtor da classe
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public CommunicationLayer(){
    super();
    this.setPreferredSize( new Dimension( Constants.WIDTH/3-1 , Constants.HEIGHT ) );
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
    initControlPanel();
    initCommLayer();

  }

  /* ***************************************************************
  * Metodo: initControlPanel
  * Funcao: inicia o painel com as funcoes de controle
  * Parametros: void
  * Retorno: void
  *************************************************************** */
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

  /* ***************************************************************
  * Metodo: initCommLayer
  * Funcao: inicia o canvas, onde vai ser utilizado para representar a passagem dos bits 
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void initCommLayer(){
    canvas.setBounds( 0 , Constants.BLOONS_HEIGHT * 2 + 100 , Constants.WIDTH/3-1 , 150 );

    this.add( canvas );

  }

}