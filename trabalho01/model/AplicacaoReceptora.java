package model;
import view.*;

public class AplicacaoReceptora {
  public static void aplicacaoReceptora( String msg ){

    MainWindow.rec.receiverTxt.setText( msg );
  }

}