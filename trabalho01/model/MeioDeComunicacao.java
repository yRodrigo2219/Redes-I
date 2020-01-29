package model;

import view.Canvas;

public class MeioDeComunicacao {
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
          Canvas.sync.acquire();

        } catch ( Exception e ) { }

        CamadaFisicaReceptora.camadaFisicaReceptora( bruteBitFluxSpotB );

      }
    }).start();

  }

}