package model;

import javax.swing.SwingUtilities;

import view.CommunicationLayer;
import view.Receiver;

public class CamadaFisicaReceptora {
  private static int wait = 100 / CommunicationLayer.jSlider.getValue();

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

  private static int[] camadaFisicaReceptoraCodificacaoBinaria( int[] bruteBitFlux ){
    int[] frames = new int[ bruteBitFlux.length / 8 ]; // cada quadro contem 8 bits ( ascii )

    int pos = 0;
    String bits = "";
    for( int i = 0 ; i < bruteBitFlux.length ; i++ ){
      bits += bruteBitFlux[ i ];

      try {
        appendNow( bruteBitFlux[ i ] + "" );
        Thread.sleep( wait );
        wait = 100 / CommunicationLayer.jSlider.getValue();
      } catch (Exception e) { }

      if( i % 8 == 7 ){ // a cada 8 iteracoes
        frames[ pos ] = Integer.parseInt( bits , 2); // converte para inteiro e armazena
        try {
          appendNow( " -> " + frames[ pos ] + "\n" );
          Thread.sleep( wait );
          wait = 100 / CommunicationLayer.jSlider.getValue();
        } catch (Exception e) { }
        // se prepara para o proximo
        pos++;
        bits = "";

      }

    }

    return frames;

  }

  private static int[] camadaFisicaReceptoraCodificacaoManchester( int[] bruteBitFlux ){
    int[] frames = new int[ bruteBitFlux.length / 16 ]; // cada quadro contem 8 bits ( ascii ) porem por se tratar da cod. 
                                                          // manchester, se utilizam 2 bits para representar um unico bit

    int pos = 0;
    String bits = "";
    String manc = "";
    for( int i = 0 ; i < bruteBitFlux.length ; i++ ){
      manc += bruteBitFlux[ i ];

      if( i % 2 == 1 ){ // a cada 2 iteracoes
        if( manc.equals( "01" ) ) // se estiver subindo
          bits += "1"; // o bit representado eh 1
        else // se estiver descendo
          bits += "0"; // o bit representado eh 0

        try {
          appendNow( manc );
          Thread.sleep( wait );
          wait = 100 / CommunicationLayer.jSlider.getValue();
        } catch (Exception e) { }
        manc = ""; // reinicia a espera pela cod.

      }

      if( i % 16 == 15 ){ // a cada 16 iteracoes
        frames[ pos ] = Integer.parseInt( bits , 2); // converte para inteiro e armazena
        try {
          appendNow( " -> " + frames[ pos ] + "\n" );
          Thread.sleep( wait );
          wait = 100 / CommunicationLayer.jSlider.getValue();
        } catch (Exception e) { }
        // se prepara para o proximo
        pos++;
        bits = "";

      }

    }

    return frames;

  }

  private static int[] camadaFisicaReceptoraCodificacaoManchesterDiferencial( int[] bruteBitFlux ){
    int[] frames = new int[ bruteBitFlux.length / 16 ]; // cada quadro contem 8 bits ( ascii ) porem por se tratar da cod. 
                                                          // manchester, se utilizam 2 bits para representar um unico bit

    int pos = 0;
    String bits = "";
    String manc = "";
    for( int i = 0 ; i < bruteBitFlux.length ; i++ ){
      manc += bruteBitFlux[ i ];

      if( i % 2 == 1 ){ // a cada 2 iteracoes
        if( i == 1 ){ // se for o primeiro bit da transmissao, segue cod. manchester
          if( manc.equals( "01" ) ) // se estiver subindo
            bits += "1"; // o bit representado eh 1
          else // se estiver descendo
            bits += "0"; // o bit representado eh 0

        }else{ // se nao for o primeiro bit, segue a cod. manchester diferencial
          if( ( manc.equals( "01" ) && bruteBitFlux[ i-2 ] == 0 )  // se nao houve transicao
          || ( manc.equals( "10" ) && bruteBitFlux[ i-2 ] == 1 ) )
            bits += "1"; // o bit representado eh 1
          else // se houve transicao
            bits += "0"; // o bit representado eh 0

        }

        try {
          appendNow( manc );
          Thread.sleep( wait );
          wait = 100 / CommunicationLayer.jSlider.getValue();
        } catch (Exception e) { }

        manc = ""; // reinicia a espera pela cod.

      }

      if( i % 16 == 15 ){ // a cada 16 iteracoes
        frames[ pos ] = Integer.parseInt( bits , 2); // converte para inteiro e armazena
        try {
          appendNow( " -> " + frames[ pos ] + "\n" );
          Thread.sleep( wait );
          wait = 100 / CommunicationLayer.jSlider.getValue();
        } catch (Exception e) { }
        // se prepara para o proximo
        pos++;
        bits = "";

      }

    }

    return frames;

  }

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