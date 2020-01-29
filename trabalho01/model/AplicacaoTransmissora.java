package model;

import view.CommunicationLayer;
import view.Sender;

public class AplicacaoTransmissora {
  public static void aplicacaoTransmissora( String message ){
    if( message.length() == 0 ) // Evitar o envio de mensagens vazias
      return;
      
    // impossibilita a entrada de outras mensagens
    Sender.senderTxt.setEditable( false );
    Sender.senderTxt.setFocusable( false );

    // impossibilita a troca de codificacao
    CommunicationLayer.jDrop.setEnabled( false );

    CamadaDeAplicacaoTransmissora.camadaDeAplicacaoTransmissora( message );

  }
}