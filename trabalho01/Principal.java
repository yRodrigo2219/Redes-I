import view.Constants;
import view.MainWindow;

public class Principal {
  public static void main( String[] args ) {
    MainWindow window = new MainWindow(); //Cria a janela principal
    window.setVisible( true );

    try{
      while( true ){
        Thread.sleep( Constants.WAIT );
        window.revalidate();
        window.repaint();

      }
    }catch( Exception e ){ }

  }

}