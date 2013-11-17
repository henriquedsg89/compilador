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


  /** Valida lexicamente retornando uma tupla com
    * a mensagem de erro e a posicao do erro
    */
  def validateLexical(codigo: String): (String, Int) = {
    lexico.setInput(codigo)

    var token: Token = null
    try {
      do {
        /* lê o token, se não lancar excessão
         * o token lido está correto.
        */
        token = lexico.nextToken()
        log.info("Token lido = " + token)
      } while (token != null)//enquanto houverem tokens

      //retorna a msg e a posicão
      ("O código está correto lexicamente", 0)
    } catch {
      /* se lancar a excecao léxica, retorna
       a mensagem de erro e a posicao do erro */
      case e: LexicalError => {
        log.warning(e.getMessage)
        (e.getMessage, e.getPosition)
      }
    }
  }

  /** Valida sintaticamente retornando uma tupla com
    * a mensagem de erro e a posicao do erro
    */
  def validateSyntatic(codigo: String): (String, Int) = {
    lexico.setInput(codigo)

    try {
      /* mesmo código que o léxico,
        só que chama o analis. sintatico, que
        chama o lexico para obter os tokens
      */
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
