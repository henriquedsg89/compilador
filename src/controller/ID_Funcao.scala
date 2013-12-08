package controller

/**
 * User: henrique & octavio
 * Date: 07/12/13
 * Time: 21:36
 */
case class ID_Funcao(nome: String, nivel: Int, desloc: Int, end_prim_instr: Int, num_parms: Int, list_params: Array[ID_Parametro], tipo_resultado: String)
  extends ID_Abstract(nome, 5, nivel, desloc) {
}
