/**
 * Authors: Henrique & Octávio
 * Date: Nov 2013
 */
package controller

import gals._
import java.util.logging.Logger

class Controller {

  val log = Logger.getLogger("Controller")

  val lexico = new Lexico
  val semantico = new Semantico
  val sintatico = new Sintatico


  def validateLexical(codigo: String): (String, Int) = {
    lexico.setInput(codigo)

    var token: Token = null
    try {
      do {
        token = lexico.nextToken()
        log.info("Token lido = " + token)
      } while (token != null)

      ("O código está correto lexicamente", 0)
    } catch {
      case e: LexicalError => {
        log.warning(e.getMessage)
        (e.getMessage, e.getPosition)
      }
    }
  }

  def validateSyntatic(codigo: String): (String, Int) = {
    lexico.setInput(codigo)

    try {
      sintatico.parse(lexico, semantico)

      ("O código está correto sintaticamente", 0)
    } catch {
      case e: LexicalError => {
        log.warning(e.getMessage)
        (e.getMessage, e.getPosition)
      }
      case e: SyntaticError => {
        log.warning(e.getMessage)
        (e.getMessage, e.getPosition)
      }
      case e: SemanticError => {
        log.warning(e.getMessage)
        (e.getMessage, e.getPosition)
      }
    }
  }
}
