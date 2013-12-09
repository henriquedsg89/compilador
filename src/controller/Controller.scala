/**
 * Authors: Henrique & Octávio
 * Date: Nov 2013
 */
package controller

import gals._
import _root_.java.util.logging.{Level, Logger}

class Controller {

  val LEX_OK_MSG = "O código está correto lexicamente"
  val SIN_OK_MSG = "O código está correto sintaticamente e semanticamente"

  val lexico = new Lexico
  val sintatico = new Sintatico
  val semantico = new Semantico


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
      } while (token != null)//enquanto houverem tokens

      //retorna a msg e a posicão
      (LEX_OK_MSG, 0)
    } catch {
      /* se lancar a excecao léxica, retorna
       a mensagem de erro e a posicao do erro */
      case e: LexicalError => {
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

      (SIN_OK_MSG, 0)
    } catch {
      case e: LexicalError => {
        (e.getMessage, e.getPosition)
      }
      case e: SyntaticError => {
        (e.getMessage, e.getPosition)
      }
      case e: SemanticError => {
        (e.getMessage, e.getPosition)
      }
    }
  }

}
