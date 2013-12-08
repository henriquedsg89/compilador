package controller

/**
 * User: henrique & octavio
 * Date: 07/12/13
 * Time: 21:36
 */
case class ID_Procedimento(nome: String, nivel: Int, desloc: Int, end_prim_instr: Int, num_parms: Int, list_marams: Int)
  extends ID_Abstract(nome, 4, nivel, desloc) {
}
