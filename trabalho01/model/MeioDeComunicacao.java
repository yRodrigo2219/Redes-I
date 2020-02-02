/* ***************************************************************
* Autor: Rodrigo Santos do Carmo
* Matricula: 201810821
* Inicio: 23/01/2020
* Ultima alteracao: 02/02/2020
* Nome: Simulacao da Camada Fisica
* Funcao: Simula o funcionamento da camada fisica em uma rede
*************************************************************** */

package model;

import view.Canvas;

public class MeioDeComunicacao {
  /* ***************************************************************
  * Metodo: meioDeComunicacao
  * Funcao: simulacao do meio de comunicacao, simula a passagem de bits
    de uma fonte fisica a outra
  * Parametros: recebe os bits que devem ser transmitidos
  * Retorno: void
  *************************************************************** */
  public static void meioDeComunicacao( int[] bruteBitFlux ){
    int[] bruteBitFluxSpotA = bruteBitFlux;
    
    new Thread( new Runnable(){
    
      @Override
      public void run() {
        int[] bruteBitFluxSpotB = new int[ bruteBitFluxSpotA.length ];
        Canvas.watcher();

        for( int i = 0 ; i < bruteBitFluxSpotA.length ; i++ ){ // transfere os bits, um por um
          bruteBitFluxSpotB[ i ] = bruteBitFluxSpotA[ i ];
          Canvas.q.add( bruteBitFluxSpotB[ i ] );

        }

        try {
          Canvas.sync.acquire(); // espera a animacao

        } catch ( Exception e ) { }

        CamadaFisicaReceptora.camadaFisicaReceptora( bruteBitFluxSpotB );

      }
    }).start();

  }

}