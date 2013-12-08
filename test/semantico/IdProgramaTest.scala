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

  "Declarando constante com nome do programa" should "gerar erro semantico" in {
    lex.setInput("programa asdf; const asdf = 1; {}.")
    a [SemanticError] should be thrownBy {
      sin.parse(lex, sem)
    }
  }

  "Declarando constante mais de uma vez com identificador valido" should "nao gerar erro semantico" in {
    lex.setInput("programa asdf; const asdf1 = 1; const asdf2 = 2.5; {}.")
    try {
      sin.parse(lex, sem)
    } catch {
      case e: Exception => fail("Não deveria dar excecao: " + e)
    }
  }

  "Declarando variavel com nome do programa" should "gerar erro semantico" in {
    lex.setInput("programa asdf; var asdf: inteiro; {}.")
    a [SemanticError] should be thrownBy {
      sin.parse(lex, sem)
    }
  }

  "Declarando procedimento com nome do programa" should "gerar erro semantico" in {
    lex.setInput("programa asdf; proc asdf(); {}.")
    a [SemanticError] should be thrownBy {
      sin.parse(lex, sem)
    }
  }
}
