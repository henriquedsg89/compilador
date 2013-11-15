package controller

import gals.{LexicalError, Token, Lexico}
import java.util.logging.Logger
/**
 * User: henrique
 * Date: 11/15/13
 * Time: 3:52 PM
 *
 */
class Controller {

  val log = Logger.getLogger("Controller")

  def validate(codigo: String): String = {
    val lexico = new Lexico
    lexico.setInput(codigo)

    var token: Token = null
    try {
      do {
        token = lexico.nextToken()
        log.info("Read token = " + token)
      } while (token != null)

      "O código está correto"
    } catch {
      case e: LexicalError => {

        log.warning(e.getMessage)
        MsgUtil.fmtToUser(e.getPosition, e.getMessage)
      }
    }
  }
}
