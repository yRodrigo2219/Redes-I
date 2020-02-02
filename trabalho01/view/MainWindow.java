/* ***************************************************************
* Autor: Rodrigo Santos do Carmo
* Matricula: 201810821
* Inicio: 23/01/2020
* Ultima alteracao: 02/02/2020
* Nome: Simulacao da Camada Fisica
* Funcao: Simula o funcionamento da camada fisica em uma rede
*************************************************************** */

package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import util.Constants;
import java.awt.FlowLayout;

public class MainWindow extends JFrame{
  public static Sender sender = new Sender();
  public static CommunicationLayer comm = new CommunicationLayer();
  public static Receiver rec = new Receiver();

  /* ***************************************************************
  * Metodo: MainWindow
  * Funcao: construtor da classe
  * Parametros: void
  * Retorno: void
  *************************************************************** */
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

  /* ***************************************************************
  * Metodo: init
  * Funcao: inicia alguns componentes necessarios
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void init(){
    this.add( sender );
    this.add( comm );
    this.add( rec );

  }

  /* ***************************************************************
  * Metodo: error
  * Funcao: exibe uma mensagem de erro e re-estabelece a entrada de mensagens
  * Parametros: mensagem que vai ser exibida quando ocorrer um erro
  * Retorno: void
  *************************************************************** */
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