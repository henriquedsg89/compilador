package controller

/**
 * User: henrique & octavio
 * Date: 08/12/13
 * Time: 14:57
 */
trait SUB_Categoria
case class Cadeia(tamanho: Int, valor: String) extends SUB_Categoria
case class Predefinido(valor: Object) extends SUB_Categoria
case class Vetor(numDim: Int, tipoElem: String, dim1: Dimensao, dim2: Dimensao) extends SUB_Categoria
case class Dimensao(tipoIndice: String, limInf: Int, limSup: Int)