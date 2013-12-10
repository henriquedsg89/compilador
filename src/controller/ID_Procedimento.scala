package controller

/**
 * Authors: Henrique & Octávio
 * Date: Dez 2013
 */
case class ID_Procedimento(nome: String, nivel: Int, end_prim_instr: Int, num_parms: Int, list_params: Array[ID_Parametro])
  extends ID_Abstract(nome, 4, nivel, 0) {

  def temPar(id: String) : ID_Parametro = {
    list_params map { p =>
      if (p.nome == id)
        p
    }
    null
  }
}
