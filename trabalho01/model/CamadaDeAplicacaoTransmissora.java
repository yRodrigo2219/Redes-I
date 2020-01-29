package model;

import javax.swing.SwingUtilities;
import view.CommunicationLayer;
import view.MainWindow;
import view.Sender;

public class CamadaDeAplicacaoTransmissora {
  public static void camadaDeAplicacaoTransmissora( String message ){
    Sender.senderLayerTxt.setText( "" ); // limpa o text
    byte [] temp = message.getBytes(); // armazena os bytes
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