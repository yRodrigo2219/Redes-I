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
import util.Convert;
import view.Receiver;

public class CamadaFisicaReceptora {
  private static int wait = 100 / CommunicationLayer.jSlider.getValue();

  /* ***************************************************************
  * Metodo: camadaFisicaReceptora
  * Funcao: simulacao da camada fisica receptora, transforma o array de bits
    em um array de ascii(256) de acordo com a codificacao
  * Parametros: recebe os bits que devem ser convertidos
  * Retorno: void
  *************************************************************** */
  public static void camadaFisicaReceptora( int[] bruteBitFlux ){
    Receiver.receiverPhysTxt.setText( "" ); // limpa o texto
    int codType = CommunicationLayer.jDrop.getSelectedIndex();
    
    new Thread( new Runnable(){
      int[] frames;
      public void run(){
        switch( codType ){
          case 0:
            frames = camadaFisicaReceptoraCodificacaoBinaria( bruteBitFlux );
            break;
          case 1:
            frames = camadaFisicaReceptoraCodificacaoManchester( bruteBitFlux );
            break;
          default: // evitar erro de sintaxe e manter o mesmo funcionamento
            frames = camadaFisicaReceptoraCodificacaoManchesterDiferencial( bruteBitFlux );
            break;
        }
      
        CamadaDeAplicacaoReceptora.camadaDeAplicacaoReceptora( frames );

      }

    } ).start();

  }

  /* ***************************************************************
  * Metodo: camadaFisicaReceptoraCodificacaoBinaria
  * Funcao: realiza a interpretacao da codificacao binaria
  * Parametros: recebe os bits que devem ser interpretados
  * Retorno: retorna um array de ascii
  *************************************************************** */
  private static int[] camadaFisicaReceptoraCodificacaoBinaria( int[] bruteBitFlux ){
    int[] frames = new int[ bruteBitFlux.length / 8 ]; // cada quadro contem 8 bits ( ascii )

    int pos = 0;
    int[] bits = new int[ 8 ];
    for( int i = 0 ; i < bruteBitFlux.length ; i++ ){
      bits[ i%8 ] = bruteBitFlux[ i ];

      try {
        appendNow( bruteBitFlux[ i ] + "" );
        Thread.sleep( wait );
        wait = 100 / CommunicationLayer.jSlider.getValue();
      } catch (Exception e) { }

      if( i % 8 == 7 ){ // a cada 8 iteracoes
        frames[ pos ] = Convert.binaryToAscii( bits ); // converte para inteiro e armazena
        try {
          appendNow( " -> " + frames[ pos ] + "\n" );
          Thread.sleep( wait );
          wait = 100 / CommunicationLayer.jSlider.getValue();
        } catch (Exception e) { }
        // se prepara para o proximo
        pos++;

      }

    }

    return frames;

  }

  /* ***************************************************************
  * Metodo: camadaFisicaReceptoraCodificacaoManchester
  * Funcao: realiza a interpretacao da codificacao manchester
  * Parametros: recebe os bits que devem ser interpretados
  * Retorno: retorna um array de ascii
  *************************************************************** */
  private static int[] camadaFisicaReceptoraCodificacaoManchester( int[] bruteBitFlux ){
    int[] frames = new int[ bruteBitFlux.length / 16 ]; // cada quadro contem 8 bits ( ascii ) porem por se tratar da cod. 
                                                          // manchester, se utilizam 2 bits para representar um unico bit

    int pos = 0;
    int[] bits = new int[ 8 ];
    int[] manc = new int[ 16 ];
    for( int i = 0 ; i < bruteBitFlux.length ; i++ ){
      manc[ i%2 ] = bruteBitFlux[ i ];

      if( i % 2 == 1 ){ // a cada 2 iteracoes
        if( manc[ 0 ] == 0 && manc[ 1 ] == 1 ) // se estiver subindo
          bits[ (i/2)%8 ] = 1; // o bit representado eh 1
        else // se estiver descendo
          bits[ (i/2)%8 ] = 0; // o bit representado eh 0

        try {
          appendNow( manc[ 0 ] + "" + manc[ 1 ] );
          Thread.sleep( wait );
          wait = 100 / CommunicationLayer.jSlider.getValue();
        } catch (Exception e) { }

      }

      if( i % 16 == 15 ){ // a cada 16 iteracoes
        frames[ pos ] = Convert.binaryToAscii( bits ); // converte para inteiro e armazena
        try {
          appendNow( " -> " );
          for( int j = 0 ; j < 8 ; j++ )
            appendNow( bits[ j ] + "" );
          appendNow( " -> " + frames[ pos ] + "\n" );
          Thread.sleep( wait );
          wait = 100 / CommunicationLayer.jSlider.getValue();
        } catch (Exception e) { }
        // se prepara para o proximo
        pos++;

      }

    }

    return frames;

  }

  /* ***************************************************************
  * Metodo: camadaFisicaReceptoraCodificacaoManchesterDiferencial
  * Funcao: realiza a interpretacao da codificacao manchester diferencial
  * Parametros: recebe os bits que devem ser interpretados
  * Retorno: retorna um array de ascii
  *************************************************************** */
  private static int[] camadaFisicaReceptoraCodificacaoManchesterDiferencial( int[] bruteBitFlux ){
    int[] frames = new int[ bruteBitFlux.length / 16 ]; // cada quadro contem 8 bits ( ascii ) porem por se tratar da cod. 
                                                          // manchester, se utilizam 2 bits para representar um unico bit

    int pos = 0;
    int[] bits = new int[ 8 ];
    int[] manc = new int[ 16 ];
    for( int i = 0 ; i < bruteBitFlux.length ; i++ ){
      manc[ i%2 ] = bruteBitFlux[ i ];

      if( i % 2 == 1 ){ // a cada 2 iteracoes
        if( i == 1 ){ // se for o primeiro bit da transmissao, segue cod. manchester
          if( manc[ 0 ] == 0 && manc[ 1 ] == 1 ) // se estiver subindo
            bits[ (i/2)%8 ] = 1; // o bit representado eh 1
          else // se estiver descendo
            bits[ (i/2)%8 ] = 0; // o bit representado eh 0

        }else{ // se nao for o primeiro bit, segue a cod. manchester diferencial
          if( ( manc[ 0 ] == 0 && manc[ 1 ] == 1 && bruteBitFlux[ i-2 ] == 0 )  // se nao houve transicao
          || ( manc[ 0 ] == 1 && manc[ 1 ] == 0 && bruteBitFlux[ i-2 ] == 1 ) )
            bits[ (i/2)%8 ] = 1; // o bit representado eh 1
          else // se houve transicao
            bits[ (i/2)%8 ] = 0; // o bit representado eh 0

        }

        try {
          appendNow( manc[ 0 ] + "" + manc[ 1 ] );
          Thread.sleep( wait );
          wait = 100 / CommunicationLayer.jSlider.getValue();
        } catch (Exception e) { }

      }

      if( i % 16 == 15 ){ // a cada 16 iteracoes
        frames[ pos ] = Convert.binaryToAscii( bits ); // converte para inteiro e armazena
        try {
          appendNow( " -> " );
          for( int j = 0 ; j < 8 ; j++ )
            appendNow( bits[ j ] + "" );
          appendNow( " -> " + frames[ pos ] + "\n" );
          Thread.sleep( wait );
          wait = 100 / CommunicationLayer.jSlider.getValue();
        } catch (Exception e) { }
        // se prepara para o proximo
        pos++;

      }

    }

    return frames;

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
          Receiver.receiverPhysTxt.append( str );

        }catch( Exception e ){ }

      }
    });
  }
  
}