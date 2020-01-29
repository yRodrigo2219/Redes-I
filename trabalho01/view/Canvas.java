package view;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Canvas extends JPanel{
  public static Semaphore sync = new Semaphore( 0 );
  public static Queue<Integer> q = new LinkedList<>();
  private static int[] bits = new int[5];
  private BufferedImage binUp, binDown, binFromUp, binFromDown; // cod. binaria sprites
  private BufferedImage asc, ascFromUp, desc, descFromDown;
  private BufferedImage chgUp, chgDown, holdUp, holdDown;
  private BufferedImage down;
  private String lastBit = "";

  public Canvas() {
    super();
    makeFluid();

    for (int i = 0; i < 5; i++)
      bits[i] = -1;

    try {
      binUp = ImageIO.read(new File("./img/binUp.png"));
      binDown = ImageIO.read(new File("./img/binDown.png"));
      binFromUp = ImageIO.read(new File("./img/binFromUp.png"));
      binFromDown = ImageIO.read(new File("./img/binFromDown.png"));

      asc = ImageIO.read(new File("./img/ascending.png"));
      ascFromUp = ImageIO.read(new File("./img/ascendingFromUp.png"));
      desc = ImageIO.read(new File("./img/descending.png"));
      descFromDown = ImageIO.read(new File("./img/descendingFromDown.png"));

      chgUp = ImageIO.read(new File("./img/changeUp.png"));
      chgDown = ImageIO.read(new File("./img/changeDown.png"));
      holdUp = ImageIO.read(new File("./img/holdUp.png"));
      holdDown = ImageIO.read(new File("./img/holdDown.png"));

      down = ImageIO.read(new File("./img/down.png"));

    } catch (Exception e) {
    }

  }

  private void makeFluid() {
    new Thread(new Runnable() {
      public void run() {
        try {
          while (true) { // forca o swing a re-pintar a tela para atingir a velocidade de animacao
            // desejada
            Thread.sleep(1000 / CommunicationLayer.jSlider.getValue());
            repaint();

          }

        } catch (Exception e) {
        }

      }
    }).start();

  }

  public static void watcher() {
    new Thread(new Runnable() {
      public void run() {
        try {
          while (true) {

            if (q.peek() == null && bits[0] == bits[1] && bits[1] == bits[2] && bits[2] == bits[3] && bits[3] == bits[4]
                && bits[4] == -1) {
              sync.release();
              Thread.currentThread().interrupt();
            }

            Thread.sleep( 100 ); // checka a cada 100ms
          }

        }catch( Exception e ){ }

      }
    } ).start();

  }

  @Override
  public void paintComponent( Graphics g ){
    int codType = CommunicationLayer.jDrop.getSelectedIndex();

    super.paintComponent( g );
    Graphics2D g2 = (Graphics2D) g.create();
    
    g2.setColor( Color.WHITE );
    g2.fillRect(0,0,Constants.WIDTH/3-1,150); // background
  
    g2.setColor( Color.BLACK );
    g2.setFont( new Font( "Arial" , 0 , 25 ) );

    for( int i = 4 ; i > 0 ; i-- ){
      bits[ i ] = bits[ i-1 ];
    }

    switch ( codType ) {
      case 0: // animacao da cod. binaria
        if( q.peek() != null )
          bits[ 0 ] = q.poll();
        else // idle
          bits[ 0 ] = -1;

        g2.drawString( bits[ 0 ] <= 0 ? "0" : "1" , 34 , 25 );
        g2.drawString( bits[ 1 ] <= 0 ? "0" : "1" , 102 , 25 );
        g2.drawString( bits[ 2 ] <= 0 ? "0" : "1" , 170 , 25 );
        g2.drawString( bits[ 3 ] <= 0 ? "0" : "1" , 238 , 25 );
        g2.drawString( bits[ 4 ] <= 0 ? "0" : "1" , 306 , 25 );

        if( bits[ 0 ] <= 0 )
          g2.drawImage( binDown , 0 , 50 , null );
        else
          g2.drawImage( binUp , 0 , 50 , null );

        for( int i = 1 ; i < 5 ; i++ ){
          if( bits[ i ] <= 0 && bits[ i-1 ] <= 0 )
            g2.drawImage( binDown , ( i * 68 ) , 50 , null );
          else if( bits[ i ] == 1 && bits[ i-1 ] == 1 )
            g2.drawImage( binUp , ( i * 68 ) , 50 , null );
          else if( bits[ i ] <= 0 && bits[ i-1 ] == 1 )
            g2.drawImage( binFromUp , ( i * 68 ) , 50 , null );
          else
            g2.drawImage( binFromDown , ( i * 68 ) , 50 , null );
        }
        break;
      case 1: // animacao cod. manchester
        if( q.size() >= 2 ){ // se tiver um bit real( representado por 2 bits na cod. manchester )
          String manc = q.poll() + "" + q.poll();
          if( manc.equals( "01" ) ) // se estiver subindo
            bits[ 0 ] = 1;
          else // se estiver descendo
            bits[ 0 ] = 0;

        }else{ // idle
          bits[ 0 ] = -1;

        }

        g2.drawString( bits[ 0 ] <= 0 ? "0" : "1" , 34 , 25 );
        g2.drawString( bits[ 1 ] <= 0 ? "0" : "1" , 102 , 25 );
        g2.drawString( bits[ 2 ] <= 0 ? "0" : "1" , 170 , 25 );
        g2.drawString( bits[ 3 ] <= 0 ? "0" : "1" , 238 , 25 );
        g2.drawString( bits[ 4 ] <= 0 ? "0" : "1" , 306 , 25 );

        if( bits[ 0 ] == 0 )
          g2.drawImage( desc , 0 , 50 , null );
        else if( bits[ 0 ] == 1 )
          g2.drawImage( asc , 0 , 50 , null );
        else
          g2.drawImage( down , 0 , 50 , null );
        
        for( int i = 1 ; i < 5 ; i++ ){
          if( bits[ i ] == 0 && bits[ i-1 ] <= 0 )
            g2.drawImage( descFromDown , ( i * 68 ) , 50 , null );
          else if( bits[ i ] == 1 && bits[ i-1 ] == 1 )
            g2.drawImage( ascFromUp , ( i * 68 ) , 50 , null );
          else if( bits[ i ] == 0 && bits[ i-1 ] == 1 )
            g2.drawImage( desc , ( i * 68 ) , 50 , null );
          else if( bits[ i ] != -1 )
            g2.drawImage( asc , ( i * 68 ) , 50 , null );
          else
            g2.drawImage( down , ( i * 68 ) , 50 , null );
        }

        break;
      case 2:
        String manc = "";
        if( q.size() >= 2 && lastBit.equals( "" ) ){ // se tiver um bit real( representado por 2 bits na cod. manchester )
                                                    // e for o primeiro bit da transmissao
          manc = q.poll() + "" + q.poll();
          if( manc.equals( "01" ) ) // se estiver subindo
            bits[ 0 ] = 2; // 2 representando '1' da cod. manchester, ja que eh o primeiro bit
          else // se estiver descendo
            bits[ 0 ] = -2; // -2 representando '0' da cod. manchester, ja que eh o primeiro bit

        }else if( q.size() >= 2 ){
          manc = q.poll() + "" + q.poll();
          if( manc.equals( "01" ) && lastBit.equals( "01" ) ){ // se teve transicao
            bits[ 0 ] = 0;

          }else if( manc.equals( "01" ) && lastBit.equals( "10" ) ){ // nao teve transicao
            bits[ 0 ] = 3; // 3 representa o manter em cima

          }else if( manc.equals( "10" ) && lastBit.equals( "10" ) ){ // se teve transicao
            bits[ 0 ] = -3; // -3 representa o trocar em cima

          }else{ // nao teve transicao
            bits[ 0 ] = 1;

          }   

        }else{ // idle
          bits[ 0 ] = -1;

        }

        g2.drawString( bits[ 0 ] <= 0 ? "0" : "1" , 34 , 25 );
        g2.drawString( bits[ 1 ] <= 0 ? "0" : "1" , 102 , 25 );
        g2.drawString( bits[ 2 ] <= 0 ? "0" : "1" , 170 , 25 );
        g2.drawString( bits[ 3 ] <= 0 ? "0" : "1" , 238 , 25 );
        g2.drawString( bits[ 4 ] <= 0 ? "0" : "1" , 306 , 25 );

        if( bits[ 0 ] == -2 ) // -2 representando '0' da cod. manchester, ja que eh o primeiro bit
          g2.drawImage( desc , 0 , 50 , null );
        else if( bits[ 0 ] == 2 ) // 2 representando '1' da cod. manchester, ja que eh o primeiro bit
          g2.drawImage( asc , 0 , 50 , null );
        else if( bits[ 0 ] == 0 )
          g2.drawImage( chgDown , 0 , 50 , null );
        else if( bits[ 0 ] == -3 )
          g2.drawImage( chgUp , 0 , 50 , null );
        else if( bits[ 0 ] == 1 )
          g2.drawImage( holdDown, 0 , 50 , null );
        else if( bits[ 0 ] == 3 )
          g2.drawImage( holdUp , 0 , 50 , null );
        else
          g2.drawImage( down , 0 , 50 , null );

        for( int i = 1 ; i < 5 ; i++ ){
          if( bits[ i ] == -2 && ( bits[ i-1 ] == 1 || bits[ i-1 ] == 3 ) )
            g2.drawImage( desc , ( i * 68 ) , 50 , null );
          else if( bits[ i ] == -2 && ( bits[ i-1 ] == 0 || bits[ i-1 ] == -3 ) )
            g2.drawImage( descFromDown , ( i * 68 ) , 50 , null );
          else if( bits[ i ] == 2 && ( bits[ i-1 ] == 1 || bits[ i-1 ] == 3 ) ) // impossiveis na pratica,  
            g2.drawImage( asc , ( i * 68 ) , 50 , null );                       // ja que ascii tem 7 bits e
          else if( bits[ i ] == 2 && ( bits[ i-1 ] == 0 || bits[ i-1 ] == -3 ) )// esta sendo armazenado
            g2.drawImage( ascFromUp , ( i * 68 ) , 50 , null );                 // como 8 bits
          else if( bits[ i ] == 1 )
            g2.drawImage( holdDown, ( i * 68 ) , 50 , null );
          else if( bits[ i ] == 3 )
            g2.drawImage( holdUp, ( i * 68 ) , 50 , null );
          else if( bits[ i ] == 0 )
            g2.drawImage( chgDown, ( i * 68 ) , 50 , null );
          else if( bits[ i ] == -3 )
            g2.drawImage( chgUp, ( i * 68 ) , 50 , null );
          else
            g2.drawImage( down , ( i * 68 ) , 50 , null );

        }

        lastBit = manc;
        break;
    }

  }

}