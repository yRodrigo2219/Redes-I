package model;
import view.*;

public class AplicacaoReceptora {
  public static void aplicacaoReceptora( String message ){
    Receiver.receiverTxt.setText(message);

    // re-estabelece a entrada de outras mensagens
    Sender.senderTxt.setEditable( true );
    Sender.senderTxt.setFocusable( true );
    Sender.senderTxt.requestFocusInWindow();

    // re-estabelece a troca de codificacao
    CommunicationLayer.jDrop.setEnabled( true );

  }

}