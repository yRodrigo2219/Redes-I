/* ***************************************************************
* Autor: Rodrigo Santos do Carmo
* Matricula: 201810821
* Inicio: 23/01/2020
* Ultima alteracao: 02/02/2020
* Nome: Simulacao da Camada Fisica
* Funcao: Simula o funcionamento da camada fisica em uma rede
*************************************************************** */

package model;

import view.CommunicationLayer;
import view.Sender;

public class AplicacaoTransmissora {
  /* ***************************************************************
  * Metodo: aplicacaoTransmissora
  * Funcao: simulacao da aplicacao transmissora, envia a mensagem
  * Parametros: recebe a mensagem que foi enviada
  * Retorno: void
  *************************************************************** */
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