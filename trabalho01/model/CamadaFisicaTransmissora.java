package model;

import javax.swing.SwingUtilities;

import view.CommunicationLayer;
import view.Sender;
import view.Convert;

public class CamadaFisicaTransmissora {
  private static int wait = 100 / CommunicationLayer.jSlider.getValue();

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

  private static int[] camadaFisicaTransmissoraCodificacaoBinaria( int[] frames ){
    int[] bitFlux = new int[ frames.length * 8 ]; // cada char ascii tem 8 bits

    int pos = 0;
    for( int i = 0 ; i < frames.length ; i++ ){
      int[] bits = Convert.utf8ToBinary( frames[i] );

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

  private static int[] camadaFisicaTransmissoraCodificacaoManchester( int[] frames ){
    int[] bitFlux = new int[ frames.length * 8 * 2 ]; // cada char ascii tem 8 bits e a manchester
                                                      // precisa de 2 bits para representar cada bit

    int pos = 0;
    for( int i = 0 ; i < frames.length ; i++ ){
      int[] bits = Convert.utf8ToBinary( frames[i] );

      appendNow( frames[ i ] + " -> " );

      for( int j = 0 ; j < 8 ; j++ ){ // transforma de string para 0/1 de acordo com a cod. manchester
        int val = bits[ j ]; // pega o valor, ao inves da referencia
  
        if( val == 0 ){ // se for 0, desce ( IEEE 802.3 standard )
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

  private static int[] camadaFisicaTransmissoraCodificacaoManchesterDiferencial( int[] frames ){
    int[] bitFlux = new int[ frames.length * 8 * 2 ]; // cada char ascii tem 8 bits e a manchester
                                                      // precisa de 2 bits para representar cada bit

    int pos = 0;
    for( int i = 0 ; i < frames.length ; i++ ){
      int[] bits = Convert.utf8ToBinary( frames[i] );

      appendNow( frames[ i ] + " -> " );

      for( int j = 0 ; j < 8 ; j++ ){ // transforma de string para 0/1 de acordo com a cod. manchester
        int val = bits[ j ]; // pega o valor, ao inves da referencia
  
        if( pos == 0 ){ // se for o primeiro bit, segue a cod. manchester padrao ( IEEE 802.3 standard )
          if( val == 0 ){ // se for 0, desce ( IEEE 802.3 standard )
            bitFlux[ pos ] = 1;
            bitFlux[ pos+1 ] = 0;
  
          }else{ // se for 1, sobe ( IEEE 802.3 standard )
            bitFlux[ pos ] = 0;
            bitFlux[ pos+1 ] = 1;
  
          }

        }else{ // se nao for o primeiro bit da transmissao, segue a cod. manchester diferencial
          if( val == 0){ // se for 0, tem transicao
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