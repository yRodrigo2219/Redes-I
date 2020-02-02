/* ***************************************************************
* Autor: Rodrigo Santos do Carmo
* Matricula: 201810821
* Inicio: 23/01/2020
* Ultima alteracao: 02/02/2020
* Nome: Simulacao da Camada Fisica
* Funcao: Simula o funcionamento da camada fisica em uma rede
*************************************************************** */

package model;
import view.*;

public class AplicacaoReceptora {
  /* ***************************************************************
  * Metodo: aplicacaoReceptora
  * Funcao: simulacao da aplicacao receptora, exibe a mensagem recebida
  * Parametros: recebe a mensagem que foi recebida
  * Retorno: void
  *************************************************************** */
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