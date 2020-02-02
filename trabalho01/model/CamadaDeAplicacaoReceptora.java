/* ***************************************************************
* Autor: Rodrigo Santos do Carmo
* Matricula: 201810821
* Inicio: 23/01/2020
* Ultima alteracao: 02/02/2020
* Nome: Simulacao da Camada Fisica
* Funcao: Simula o funcionamento da camada fisica em uma rede
*************************************************************** */

package model;

import javax.swing.SwingUtilities;

import view.CommunicationLayer;
import view.Receiver;

public class CamadaDeAplicacaoReceptora {
  /* ***************************************************************
  * Metodo: camadaDeAplicacaoReceptora
  * Funcao: simulacao da camada de aplicacao receptora, transforma os ascii
    recebidos em mensagem
  * Parametros: array de ascii que formam a mensagem
  * Retorno: void
  *************************************************************** */
  public static void camadaDeAplicacaoReceptora( int[] frames ){
    Receiver.receiverLayerTxt.setText( "" ); // limpa o texto
    new Thread( new Runnable(){
      String message = "";
      int wait = 500 / CommunicationLayer.jSlider.getValue();
      public void run(){
        for( int c : frames ){
          message += (char) c;

          try {
            appendNow( " " + c + " -> " + (char) c + "\n" );
            Thread.sleep( wait );
            wait = 500 / CommunicationLayer.jSlider.getValue();
          } catch ( Exception e ) { }
          
        } 
    
        AplicacaoReceptora.aplicacaoReceptora( message );

      }

    } ).start();

  }

  /* ***************************************************************
  * Metodo: appendNow
  * Funcao: concatena a mensagem desejada na proxima atualizacao do swing
  * Parametros: recebe a string que deve ser concatenada
  * Retorno: void
  *************************************************************** */
  private static void appendNow( String str ){
    SwingUtilities.invokeLater( new Runnable(){
      public void run(){
        try{
          Receiver.receiverLayerTxt.append( str );

        }catch( Exception e ){ }

      }
    });
  }

}