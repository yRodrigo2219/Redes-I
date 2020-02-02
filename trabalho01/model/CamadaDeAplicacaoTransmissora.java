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
import view.MainWindow;
import view.Sender;

public class CamadaDeAplicacaoTransmissora {
  /* ***************************************************************
  * Metodo: camadaDeAplicacaoTransmissora
  * Funcao: simulacao da camada de aplicacao transmissora, transforma a mensagem
    em um array de ascii(256)
  * Parametros: recebe a mensagem que deve ser convertida
  * Retorno: void
  *************************************************************** */
  public static void camadaDeAplicacaoTransmissora( String message ){
    Sender.senderLayerTxt.setText( "" ); // limpa o text
    char [] temp = message.toCharArray();
    int [] frames = new int[ temp.length ];

    new Thread( new Runnable(){
      @Override
      public void run() {
        int wait = 500 / CommunicationLayer.jSlider.getValue();

        try{
          for( int i = 0 ; i < temp.length ; i++ ){ // converte para um array de quadros (ascii only)
            frames[ i ] = ( int ) temp[ i ];
            if( frames[ i ] < 0 || frames[ i ] >= 256 ){
              MainWindow.error( "\"String contem caractere invalido!\"" );
              return;
      
            }

            appendNow( " " + (char) frames[ i ] + " -> " + frames[ i ] + "\n" );
            Thread.sleep( wait );
            wait = 500 / CommunicationLayer.jSlider.getValue();
            
          }

          CamadaFisicaTransmissora.camadaFisicaTransmissora( frames );

        }catch( Exception e ){ }

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
          Sender.senderLayerTxt.append( str );

        }catch( Exception e ){ }

      }
    });
  }

}