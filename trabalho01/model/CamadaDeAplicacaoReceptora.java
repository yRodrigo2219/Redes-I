package model;

import javax.swing.SwingUtilities;

import view.CommunicationLayer;
import view.Receiver;

public class CamadaDeAplicacaoReceptora {
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