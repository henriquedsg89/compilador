package lexico

import controller.Controller
import org.scalatest._

import _root_.java.util
/**
 * Authors: Henrique & Octávio
 * Date: Dez 2013
 */
class ReservedWordsTest extends FlatSpec with Matchers {

  val lexico = new Controller().lexico
  val reserved_words = Map(
    28 -> "programa",
    29 -> "const",
    30 -> "var",
    31 -> "proc",
    32 -> "funcao",
    33 -> "ref",
    34 -> "val",
    35 -> "cadeia",
    36 -> "vetor",
    37 -> "inteiro",
    38 -> "real",
    39 -> "booleano",
    40 -> "caracter",
    41 -> "de",
    42 -> "se",
    43 -> "entao",
    44 -> "enquanto",
    45 -> "faca",
    46 -> "leia",
    47 -> "escreva",
    48 -> "senao",
    49 -> "ou",
    50 -> "e",
    51 -> "nao",
    52 -> "falso",
    53 -> "verdadeiro"
  )

  "Dado todos os tokens de palavras reservadas validos" should "validar com apenas ids de palavras reservadas" in {
    reserved_words.values map { value =>
      lexico.setInput(value)
      val token = lexico.nextToken()
      reserved_words.get(token.getId).get should be (value)
    }
  }

  "Dado tokens de identificadores" should "nao conter nenhum token id de palavra reservada nem gerar erro lexico" in {
    val ids = Array("x", "@aux", "id_de_Teste")
    ids map { id =>
      lexico.setInput(id)
      val token = lexico.nextToken()
      val token_id = token.getId
      reserved_words.contains(token_id) should be (false)
    }
  }

  "Dado tokens de num_real ou num_int" should "nao conter nenhum token id de palavra reservada nem gerar erro lexico" in {
    val ids = Array("20", "20e10", "3.5")
    ids map { id =>
      lexico.setInput(id)
      val token = lexico.nextToken()
      val token_id = token.getId
      reserved_words.contains(token_id) should be (false)
    }
  }
}
