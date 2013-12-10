package semantico

import org.scalatest.{Matchers, FlatSpec}
import controller._
import gals.{Semantico, SemanticError}
/**
* Authors: Henrique & Octávio
* Date: Dez 2013
*/
class OpMultiTest extends FlatSpec with Matchers {
  val con = new Controller
  val lex = con.lexico
  val sin = con.sintatico
  val sem = con.semantico
  val semScala = Semantico.semScala

  "Variavel inteira" should "aceitar multiplicacao/soma/subtracao de inteiros" in {
    lex.setInput("programa p; var a : inteiro; { a:= 2 * 4; a:= a + 5; a:= a - 5; }.")
    try {
      sin.parse(lex, sem)
    } catch {
      case e: SemanticError => fail("Não deveria dar excecao: " + e)
    }
  }

  "Variavel inteira" should "aceitar operações e unários" in {
    lex.setInput("programa p; var a : inteiro; { a:= 2 * 4; a:= a + -5; a := (2 + 3) * (5 - -2) }.")
    try {
      sin.parse(lex, sem)
    } catch {
      case e: SemanticError => fail("Não deveria dar excecao: " + e)
    }
  }

  "Variavel inteira nao" should "aceitar real" in {
    lex.setInput("programa p; var a : inteiro; { a:= a / -5; }.")
    a [SemanticError] should be thrownBy {
      sin.parse(lex, sem)
    }
  }

  "Variavel inteira nao" should "aceitar real mesmo com parenteses" in {
    lex.setInput("programa p; var a : inteiro; { a:= (a / 5); }.")
    a [SemanticError] should be thrownBy {
      sin.parse(lex, sem)
    }
  }

  "Variavel inteira nao" should "aceitar real constante explicita" in {
    lex.setInput("programa p; var a : inteiro; { a:= a + 5.2; }.")
    a [SemanticError] should be thrownBy {
      sin.parse(lex, sem)
    }
  }

  "Variavel inteira nao" should "aceitar real mesmo com parenteses em duas exp e o div no meio" in {
    lex.setInput("programa p; var a : inteiro; { a:= (1 + -5) / (7 + 4); }.")
    a [SemanticError] should be thrownBy {
      sin.parse(lex, sem)
    }
  }

}