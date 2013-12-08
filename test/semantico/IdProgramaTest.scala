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
class IdProgramaTest extends FlatSpec with Matchers {

  val lex = new Controller().lexico
  val sin = new Controller().sintatico
  val sem = new Controller().semantico

  val semScala = Semantico.semScala

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

  "Declarando variavel valida do tipo inteiro" should "possuir tipo inteiro" in {
    lex.setInput("programa asdf; var A: inteiro; {}.")
    try {
      sin.parse(lex, sem)
      semScala.listTabSim(1).get("A").get.asInstanceOf[ID_Variavel].tipo should be ("inteiro")
    } catch {
      case e: Exception => fail("Não deveria dar excecao: " + e)
    }
  }

  "Declarando variaveis validas do tipo inteiro" should "possuir tipo inteiro" in {
    lex.setInput("programa asdf; var A, B: inteiro; {}.")
    try {
      sin.parse(lex, sem)
      semScala.listTabSim(1).get("A").get.asInstanceOf[ID_Variavel].tipo should be ("inteiro")
      semScala.listTabSim(1).get("B").get.asInstanceOf[ID_Variavel].tipo should be ("inteiro")
    } catch {
      case e: Exception => fail("Não deveria dar excecao: " + e)
    }
  }

  "Declarando variavel valida do tipo cadeia" should "possuir tipo cadeia" in {
    lex.setInput("programa asdf; var A: cadeia[5]; {}.")
    try {
      sin.parse(lex, sem)
      semScala.listTabSim(1).get("A").get.asInstanceOf[ID_Variavel].tipo should be ("cadeia")
    } catch {
      case e: Exception => fail("Não deveria dar excecao: " + e)
    }
  }

  "Declarando procedimentos sem parametros formais" should "conter num parametros formais = 0" in {
    lex.setInput("programa asdf; proc id;{}; {}.")
    try {
      sin.parse(lex, sem)
      semScala.listTabSim(1).get("id").get.asInstanceOf[ID_Procedimento].num_parms should be (0)
    } catch {
      case e: Exception => fail("Não deveria dar excecao: " + e)
    }
  }

  "Declarando procedimentos com valores parametros formais" should "conter num parametros formais = 1" in {
    lex.setInput("programa asdf; proc id(val p1: inteiro);{}; {}.")
    try {
      sin.parse(lex, sem)
      semScala.listTabSim(1).get("id").get.asInstanceOf[ID_Procedimento].num_parms should be (1)
      semScala.listTabSim(1).get("id").get.asInstanceOf[ID_Procedimento].list_params(0).nome should be ("p1")
      semScala.listTabSim(1).get("id").get.asInstanceOf[ID_Procedimento].list_params(0).tipo should be ("inteiro")
      semScala.contextoLID should be ("par-formal")
      semScala.mpp should be ("valor")
    } catch {
      case e: Exception => fail("Não deveria dar excecao: " + e)
    }
  }

  "Declarando procedimentos com 3 parametros formais" should "conter num parametros formais = 3" in {
    lex.setInput("programa asdf; proc id(ref p1, p2: inteiro; val p3: real);{}; {}.")
    try {
      sin.parse(lex, sem)
      val proced = semScala.listTabSim(1).get("id").get.asInstanceOf[ID_Procedimento]
      proced.num_parms should be (3)
      proced.list_params(0).nome should be ("p1")
      proced.list_params(0).tipo should be ("inteiro")
      proced.list_params(0).mecanismo_passagem should be ("referencia")
      proced.list_params(1).nome should be ("p2")
      proced.list_params(1).tipo should be ("inteiro")
      proced.list_params(1).mecanismo_passagem should be ("referencia")
      proced.list_params(2).nome should be ("p3")
      proced.list_params(2).tipo should be ("real")
      proced.list_params(2).mecanismo_passagem should be ("val")
    } catch {
      case e: Exception => fail("Não deveria dar excecao: " + e)
    }

  }

  "Declarando procedimentos com 3 parametros formais" should "conveter tipo de passagem por valor e referencia" in {
    lex.setInput("programa asdf; proc id(val p1, p2: inteiro; ref p3: real);{}; {}.")
    try {
      sin.parse(lex, sem)
      val proced = semScala.listTabSim(1).get("id").get.asInstanceOf[ID_Procedimento]
      proced.num_parms should be (3)
      proced.list_params(0).nome should be ("p1")
      proced.list_params(0).tipo should be ("inteiro")
      proced.list_params(0).mecanismo_passagem should be ("valor")
      proced.list_params(1).nome should be ("p2")
      proced.list_params(1).tipo should be ("inteiro")
      proced.list_params(1).mecanismo_passagem should be ("valor")
      proced.list_params(2).nome should be ("p3")
      proced.list_params(2).tipo should be ("real")
      proced.list_params(2).mecanismo_passagem should be ("referencia")
    } catch {
      case e: Exception => fail("Não deveria dar excecao: " + e)
    }
  }

  "Declarando func com valor param formal" should "conter num parametros formais = 1" in {
    lex.setInput("programa asdf; funcao id(val p1: inteiro):inteiro;{}; {}.")
    try {
      sin.parse(lex, sem)
      val func = semScala.listTabSim(1).get("id").get.asInstanceOf[ID_Funcao]
      func.num_parms should be (1)
      func.tipo_resultado should be ("inteiro")
      func.list_params(0).nome should be ("p1")
      func.list_params(0).tipo should be ("inteiro")
      semScala.contextoLID should be ("par-formal")
      semScala.mpp should be ("valor")
    } catch {
      case e: Exception => fail("Não deveria dar excecao: " + e)
    }
  }

  "Declarando variaveis validas do tipo cadeia" should "possuir tipo cadeia" in {
    lex.setInput("programa asdf; var A, B: cadeia[5]; {}.")
    try {
      sin.parse(lex, sem)
      semScala.listTabSim(1).get("A").get.asInstanceOf[ID_Variavel].tipo should be ("cadeia")
      semScala.listTabSim(1).get("B").get.asInstanceOf[ID_Variavel].tipo should be ("cadeia")
    } catch {
      case e: Exception => fail("Não deveria dar excecao: " + e)
    }
  }

  "Declarando procedimento com nome do programa" should "gerar erro semantico" in {
    lex.setInput("programa asdf; proc asdf; {}.")
    a [SemanticError] should be thrownBy {
      sin.parse(lex, sem)
    }
  }

  "Declarando variavel valida do tipo vetor indexada por numero" should "salvar o tipo de elementos, tipo de indices e limInf/limSup sem erro semantico" in {
    lex.setInput("programa asdf; var A: vetor[1 .. 2] de inteiro; {}.")//TODO: arrumar lexico para suportar 1..2
    sin.parse(lex, sem)
    semScala.listTabSim(1).get("A").get.asInstanceOf[ID_Variavel].tipo should be ("vetor")
  }

  "Declarando variavel valida do tipo vetor indexada por letra" should "salvar o tipo de elementos, tipo de indices e limInf/limSup sem erro semantico" in {
    lex.setInput("programa asdf; var A: vetor['a' .. 'f'] de inteiro; {}.")
    sin.parse(lex, sem)
    semScala.listTabSim(1).get("A").get.asInstanceOf[ID_Variavel].tipo should be ("vetor")
  }

  "Declarando variaveis validas do tipo vetor" should "nao gerar erro semantico" in {
    lex.setInput("programa asdf; var A, B, C: vetor[1 .. 2] de inteiro; {}.")
    sin.parse(lex, sem)
    semScala.listTabSim(1).get("A").get.asInstanceOf[ID_Variavel].tipo should be ("vetor")
    semScala.listTabSim(1).get("B").get.asInstanceOf[ID_Variavel].tipo should be ("vetor")
    semScala.listTabSim(1).get("C").get.asInstanceOf[ID_Variavel].tipo should be ("vetor")
  }

  "Declarando variavel valida do tipo vetor bidimensional de inteiros" should "salvar tipo das duas dimensoes sem erro semantico" in {
    lex.setInput("programa asdf; var A: vetor[1 .. 2, 'a' .. 'b'] de inteiro; {}.")
    sin.parse(lex, sem)
    semScala.listTabSim(1).get("A").get.asInstanceOf[ID_Variavel].tipo should be ("vetor")
//    semScala.listTabSim(1).get("A").get.asInstanceOf[ID_Variavel].subCategoria.asInstanceOf[Vetor].numDim should be (2)
//    semScala.listTabSim(1).get("A").get.asInstanceOf[ID_Variavel].subCategoria.asInstanceOf[Vetor].tipoElem should be ("inteiro")
  }
}
