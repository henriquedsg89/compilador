package semantico

import org.scalatest._
import controller.Controller
import gals.{LexicalError, Constants}

/**
 * Authors: Henrique & Octávio
 * Date: Nov 2013
 */
class IdProgramaTest extends FlatSpec with Matchers {

  val lex = new Controller().lexico
  val sin = new Controller().sintatico
  val sem = new Controller().semantico

  "Semantico" should "não gerar error" in {
    lex.setInput("programa asdf; {}.")
    sin.parse(lex, sem)
  }
}
