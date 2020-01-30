package view;

public class Convert {
  public static int[] utf8ToBinary( int number ){
    int[] binary = new int[ 8 ]; // utf-8 tem 8 bits

    for( int i = 0 ; number > 0 ; number = number / 2, i++ )
      binary[ i ] = number % 2;

    int[] binaryValue = new int[ 8 ];

    for( int i = 0 ; i < 8 ; i++ )
      binaryValue[ i ] = binary[ 7-i ];

    return binaryValue;

  }

  public static int binaryToUtf8( int[] binary ){
    int value = 0;

    for( int i = 0 ; i < 8 ; i++ ){
      value += binary[ 7-i ] * Math.pow( 2 , i );

    }

    return value;

  }


}