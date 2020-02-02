/* ***************************************************************
* Autor: Rodrigo Santos do Carmo
* Matricula: 201810821
* Inicio: 23/01/2020
* Ultima alteracao: 02/02/2020
* Nome: Simulacao da Camada Fisica
* Funcao: Simula o funcionamento da camada fisica em uma rede
*************************************************************** */

package util;

public class Convert {
  /* ***************************************************************
  * Metodo: asciiToBinary
  * Funcao: converte um valor numerico em ascii(256) para um array de binarios
  * Parametros: recebe o numero referente ao caractere
  * Retorno: retorna um array de inteiros binarios (0 ou 1)
  *************************************************************** */
  public static int[] asciiToBinary( int number ){
    int[] binary = new int[ 8 ]; // ascii(256) tem 8 bits

    for( int i = 0 ; number > 0 ; number = number / 2, i++ )
      binary[ i ] = number % 2;

    int[] binaryValue = new int[ 8 ];

    for( int i = 0 ; i < 8 ; i++ )
      binaryValue[ i ] = binary[ 7-i ];

    return binaryValue;

  }

  /* ***************************************************************
  * Metodo: binaryToAscii
  * Funcao: converte um array de binarios para um numero ascii(256)
  * Parametros: recebe o array de binarios
  * Retorno: retorna o valor ascii(256)
  *************************************************************** */
  public static int binaryToAscii( int[] binary ){
    int value = 0;

    for( int i = 0 ; i < 8 ; i++ )
      value += binary[ 7-i ] * Math.pow( 2 , i );

    return value;

  }


}