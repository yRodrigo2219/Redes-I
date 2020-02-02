/* ***************************************************************
* Autor: Rodrigo Santos do Carmo
* Matricula: 201810821
* Inicio: 23/01/2020
* Ultima alteracao: 02/02/2020
* Nome: Simulacao da Camada Fisica
* Funcao: Simula o funcionamento da camada fisica em uma rede
*************************************************************** */

package util;

import java.awt.Font;

public class Constants{
  public static final int FPS = 12; // Meta de Frame por segundo
  public static final int WAIT = 1000 / FPS; // Tempo que se deve esperar entre os frames
  public static final int WIDTH = 1024; // Largura da janela
  public static final int HEIGHT = 720; // Altura da janela
  public static final String NAME = "Simulacao camada fisica"; // Titulo da janela
  public static final int BLOONS_WIDTH = 300; // Largura do balao
  public static final int BLOONS_HEIGHT = 200; // Altura do balao
  public static final Font FONT = new Font("Arial", Font.BOLD, 16); // Fonte utilizada
  public static final int CHAR_LIMIT = 130; // Quantidade limite de caracteres do balao

}