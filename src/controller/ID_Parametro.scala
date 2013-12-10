package controller

/**
 * Authors: Henrique & Octávio
 * Date: Dez 2013
 */
case class ID_Parametro(nome: String, nivel: Int, desloc: Int, mecanismo_passagem: String, tipo: String)
  extends ID_Abstract(nome, 7, nivel, desloc) {
}
