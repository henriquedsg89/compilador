package semantico

import org.scalatest._
import controller._
import gals.{Semantico, SemanticError, LexicalError, Constants}
import controller.ID_Variavel
import controller.ID_Procedimento

/**
 * Authors: Henrique & Octávio
 * Date: Nov 2013
 */
class RvarTest extends FlatSpec with Matchers {

  val lex = new Controller().lexico
  val sin = new Controller().sintatico
  val sem = new Controller().semantico

  val semScala = Semantico.semScala

  "Resto de var se tiver parenteses" should "ser funcao" in {
    lex.setInput("programa asdf; var a: inteiro; { a(5) }.")
    a [SemanticError] should be thrownBy {
      sin.parse(lex, sem)
    }
  }

  "Resto de var " should "aceitar nao ter resto, apenas id" in {
    lex.setInput("programa asdf; var a, b: inteiro; { a := b; }.")
    try {
      sin.parse(lex, sem)
    } catch {
      case e: SemanticError => fail("Não deveria dar excecao: " + e)
    }
  }
}
