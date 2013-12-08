package semantico

import org.scalatest._
import controller.Controller
import gals.{SemanticError, LexicalError, Constants}

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
    try {
      sin.parse(lex, sem)
    } catch {
      case e: Exception => fail("Não deveria dar excecao: " + e)
    }

  }

  "Semantico" should "gerar error em caso de variavel com mesmo nome do programa" in {
    lex.setInput("programa asdf; var asdf: inteiro; {}.")
    a [SemanticError] should be thrownBy {
      sin.parse(lex, sem)
    }
  }
}
