package controller

/**
 * User: henrique
 * Date: 11/15/13
 * Time: 4:29 PM
 *
 */
object MsgUtil {

  def fmtToUser(pos: Int, galsMsg: String): String = {
    val msgPos = "Valor inválido na posicão "+ pos + ", "
    galsMsg match {
      case "Erro identificando literal" =>  msgPos + "Esperado ' ', \" \", /* */ ou //"
    }
  }
}
