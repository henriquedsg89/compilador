package semantico

import org.scalatest.{Matchers, FlatSpec}
import controller._
import gals.{Semantico, SemanticError}
/**
 * Authors: Henrique & Octávio
 * Date: Dez 2013
 */
class ComandosTest extends FlatSpec with Matchers {
  val con = new Controller
  val lex = con.lexico
  val sin = con.sintatico
  val sem = con.semantico
  val semScala = Semantico.semScala

  "Dado identificador declarado" should "poder acessa-lo sem erro semantico e salvo em posid" in {
    lex.setInput("programa p; var A: inteiro; {A := 1}.")
    sin.parse(lex, sem)
    val variavel = semScala.listTabSim(1).get("A").get.asInstanceOf[ID_Variavel]
    variavel should not be (null)
    semScala.posid.pop
    semScala.posid.head should be (variavel)
  }

  "Dado expressao 1>2" should "validar expressao com falso sem gerar erro semantico" in {
    lex.setInput("programa p; var A: inteiro; {se 1>2 entao A := 1}.")
//    val valA = semScala.listTabSim(1).get("A").get.asInstanceOf[ID_Variavel].subCategoria.asInstanceOf[Predefinido].valor
//    valA should be (1)
  }

  "Dado fator 'nao verdadeiro'" should "negar sem erro lexico" in {
    lex.setInput("programa p; {escreva(10,10)}.")
    try {
      sin.parse(lex, sem)
    } catch {
      case e: SemanticError => fail("Não deveria dar excecao: " + e)
    }
  }

  "Usando comando escreva com parametro variavel" should "nao gerar erro semantico" in {
    lex.setInput("programa p; var a: inteiro; {a:= 10; leia(a);}.")
    try {
      sin.parse(lex, sem)
    } catch {
      case e: SemanticError => fail("Nao deveria dar exceccao: " + e)
    }
  }

  "Usando enquanto faca iterando uma variavel" should " nao gerar erro semantico" in {
    lex.setInput("programa p; var a: inteiro; { enquanto a>2 faca {a:= a +3;}; }.")
    try {
      sin.parse(lex, sem)
    } catch {
      case e: SemanticError => fail("Nao deveria dar exceccao: " + e)
    }
  }
}
