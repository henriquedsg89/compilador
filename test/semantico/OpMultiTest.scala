package semantico

import org.scalatest.{Matchers, FlatSpec}
import controller._
import gals.{Semantico, SemanticError}
/**
 * Authors: Henrique & Octávio
 * Date: Nov 2013
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

  "Operador" should "multi" in {
    lex.setInput("programa p; var a : inteiro; { a:= 2 * 4; a:= a + -5; a := (2 + 3) * (5 - -2) }.")
    try {
      sin.parse(lex, sem)
    } catch {
      case e: SemanticError => fail("Não deveria dar excecao: " + e)
    }
  }
}