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
import view.Sender;
import util.Convert;

public class CamadaFisicaTransmissora {
  private static int wait = 100 / CommunicationLayer.jSlider.getValue();

  /* ***************************************************************
  * Metodo: camadaFisicaTransmissora
  * Funcao: simulacao da camada fisica transmissora, transforma o array de ascii
    em um array de bits de acordo com a codificacao
  * Parametros: recebe os ascii que devem ser convertidos para bits
  * Retorno: void
  *************************************************************** */
  public static void camadaFisicaTransmissora(int[] frames) {
    Sender.senderPhysTxt.setText( "" ); // limpa o texto
    int codType = CommunicationLayer.jDrop.getSelectedIndex();

    new Thread( new Runnable(){
      int[] bruteBitFlux;
      public void run(){
        switch( codType ){
          case 0:
            bruteBitFlux = camadaFisicaTransmissoraCodificacaoBinaria( frames );
            break;
          case 1:
            bruteBitFlux = camadaFisicaTransmissoraCodificacaoManchester( frames );
            break;
          default: // evitar erro de sintaxe e manter o mesmo funcionamento
            bruteBitFlux = camadaFisicaTransmissoraCodificacaoManchesterDiferencial( frames );
            break;
        }

        MeioDeComunicacao.meioDeComunicacao( bruteBitFlux );

      }
      
    } ).start();

  }

  /* ***************************************************************
  * Metodo: camadaFisicaTransmissoraCodificacaoBinaria
  * Funcao: codifica o array de ascii em binario
  * Parametros: recebe o array de ascii que deve ser convertido
  * Retorno: retorna um array de bits
  *************************************************************** */
  private static int[] camadaFisicaTransmissoraCodificacaoBinaria( int[] frames ){
    int[] bitFlux = new int[ frames.length * 8 ]; // cada char ascii tem 8 bits

    int pos = 0;
    for( int i = 0 ; i < frames.length ; i++ ){
      int[] bits = Convert.asciiToBinary( frames[i] );

      appendNow( frames[ i ] + " -> " );

      for( int j = 0 ; j < 8 ; j++ ){ // transforma de string para 0/1
        bitFlux[ pos ] = bits[ j ]; // armazena o valor, ao inves da referencia
        
        try {
          appendNow( bitFlux[ pos ] + "" );
          Thread.sleep( wait );
          wait = 100 / CommunicationLayer.jSlider.getValue();
        } catch (Exception e) { }
        pos++;

      }

      appendNow( "\n" );

    }

    return bitFlux;
    
  }

  /* ***************************************************************
  * Metodo: camadaFisicaTransmissoraCodificacaoManchester
  * Funcao: codifica o array de ascii em manchester
  * Parametros: recebe o array de ascii que deve ser convertido
  * Retorno: retorna um array de bits
  *************************************************************** */
  private static int[] camadaFisicaTransmissoraCodificacaoManchester( int[] frames ){
    int[] bitFlux = new int[ frames.length * 8 * 2 ]; // cada char ascii tem 8 bits e a manchester
                                                      // precisa de 2 bits para representar cada bit

    int pos = 0;
    for( int i = 0 ; i < frames.length ; i++ ){
      int[] bits = Convert.asciiToBinary( frames[i] );

      appendNow( frames[ i ] + " -> " );

      for( int j = 0 ; j < 8 ; j++ )
        appendNow( bits[ j ] + "" );

      appendNow( " -> " );

      for( int j = 0 ; j < 8 ; j++ ){ // transforma de string para 0/1 de acordo com a cod. manchester
        if( bits[ j ] == 0 ){ // se for 0, desce ( IEEE 802.3 standard )
          bitFlux[ pos ] = 1;
          bitFlux[ pos+1 ] = 0;

        }else{ // se for 1, sobe ( IEEE 802.3 standard )
          bitFlux[ pos ] = 0;
          bitFlux[ pos+1 ] = 1;

        }

        try {
          appendNow( bitFlux[ pos ] + "" + bitFlux[ pos+1 ] );
          Thread.sleep( wait );
          wait = 100 / CommunicationLayer.jSlider.getValue();
        } catch (Exception e) { }

        pos += 2;

      }

      appendNow( "\n" );

    }

    return bitFlux;
    
  }

  /* ***************************************************************
  * Metodo: camadaFisicaTransmissoraCodificacaoManchesterDiferencial
  * Funcao: codifica o array de ascii em manchester diferencial
  * Parametros: recebe o array de ascii que deve ser convertido
  * Retorno: retorna um array de bits
  *************************************************************** */
  private static int[] camadaFisicaTransmissoraCodificacaoManchesterDiferencial( int[] frames ){
    int[] bitFlux = new int[ frames.length * 8 * 2 ]; // cada char ascii tem 8 bits e a manchester
                                                      // precisa de 2 bits para representar cada bit

    int pos = 0;
    for( int i = 0 ; i < frames.length ; i++ ){
      int[] bits = Convert.asciiToBinary( frames[i] );

      appendNow( frames[ i ] + " -> " );

      for( int j = 0 ; j < 8 ; j++ )
        appendNow( bits[ j ] + "" );

      appendNow( " -> " );

      for( int j = 0 ; j < 8 ; j++ ){ // transforma de string para 0/1 de acordo com a cod. manchester
        if( pos == 0 ){ // se for o primeiro bit, segue a cod. manchester padrao ( IEEE 802.3 standard )
          if( bits[ j ] == 0 ){ // se for 0, desce ( IEEE 802.3 standard )
            bitFlux[ pos ] = 1;
            bitFlux[ pos+1 ] = 0;
  
          }else{ // se for 1, sobe ( IEEE 802.3 standard )
            bitFlux[ pos ] = 0;
            bitFlux[ pos+1 ] = 1;
  
          }

        }else{ // se nao for o primeiro bit da transmissao, segue a cod. manchester diferencial
          if( bits[ j ] == 0){ // se for 0, tem transicao
            if( bitFlux[ pos-1 ] == 0 ){
              bitFlux[ pos ] = 1;
              bitFlux[ pos+1 ] = 0;

            }else{
              bitFlux[ pos ] = 0;
              bitFlux[ pos+1 ] = 1;

            }

          }else{ // se for 1, nao tem transicao ( mantem )
            if( bitFlux[ pos-1 ] == 0 ){
              bitFlux[ pos ] = 0;
              bitFlux[ pos+1 ] = 1;

            }else{
              bitFlux[ pos ] = 1;
              bitFlux[ pos+1 ] = 0;

            }

          }

        }

        try {
          appendNow( bitFlux[ pos ] + "" + bitFlux[ pos+1 ] );
          Thread.sleep( wait );
          wait = 100 / CommunicationLayer.jSlider.getValue();
        } catch (Exception e) { }

        pos += 2;

      }

      appendNow( "\n" );

    }

    return bitFlux;
    
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
          Sender.senderPhysTxt.append( str );

        }catch( Exception e ){ }

      }
    });
  }

}