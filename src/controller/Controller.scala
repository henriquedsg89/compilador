package controller

import gals._
import java.util.logging.Logger
/**
 * User: henrique
 * Date: 11/15/13
 * Time: 3:52 PM
 *
 */
class Controller {

  val log = Logger.getLogger("Controller")

  val lexico = new Lexico
  val semantico = new Semantico
  val sintatico = new Sintatico


  def validateLexical(codigo: String): String = {
    lexico.setInput(codigo)

    var token: Token = null
    try {
      do {
        token = lexico.nextToken()
        log.info("Token lido = " + token)
      } while (token != null)

      "O código está correto lexicamente"
    } catch {
      case e: LexicalError => {
        log.warning(e.getMessage)
        e.getMessage
      }
    }
  }

  def validateSyntatic(codigo: String): String = {
    lexico.setInput(codigo)

    try {
      sintatico.parse(lexico, semantico)

      "O código está correto sintaticamente"
    } catch {
      case e: LexicalError => {
        log.warning(e.getMessage)
        e.getMessage
      }
      case e: SyntaticError => {
        log.warning(e.getMessage)
        e.getMessage
      }
      case e: SemanticError => {
        log.warning(e.getMessage)
        e.getMessage
      }
    }
  }
}
