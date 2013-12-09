package semantico

import org.scalatest.{Matchers, FlatSpec}
import controller._
import gals.{Semantico, SemanticError}
/**
 * Authors: Henrique & Octávio
 * Date: Nov 2013
 */
class OpLogicoTest extends FlatSpec with Matchers {
  val con = new Controller
  val lex = con.lexico
  val sin = con.sintatico
  val sem = con.semantico
  val semScala = Semantico.semScala

  "Variavel booleana" should "aceitar verdadeiro, falto ou expressao de comparacao" in {
    lex.setInput("programa p; var a : booleano; { a:= verdadeiro; a:= falso; a:= (a > 5); a:= (a = 5); a:= (a <> 5); }.")
    try {
      sin.parse(lex, sem)
    } catch {
      case e: SemanticError => fail("Não deveria dar excecao: " + e)
    }
  }

  "Variavel booleana deveria" should "aceitar 'não'" in {
    lex.setInput("programa p; var a : booleano; { a:= nao verdadeiro; a:= nao falso; a:= nao(a > 5); a:= nao(a = 5); a:= nao(a <> 5); }.")
    try {
      sin.parse(lex, sem)
    } catch {
      case e: SemanticError => fail("Não deveria dar excecao: " + e)
    }
  }

  "Variavel booleana nao deveria" should "aceitar 'não' repetidos" in {
    lex.setInput("programa p; var a : booleano; { a:= nao nao verdadeiro;}.")
    a [SemanticError] should be thrownBy {
      sin.parse(lex, sem)
    }
  }
}