package controller

/**
 * Authors: Henrique & Octávio
 * Date: Dez 2013
 */
abstract class ID_Abstract(nome: String, categoria: Int, nivel: Int, desloc: Int, posicao: Int) {

  def absNome = nome
  def pos = posicao
}
