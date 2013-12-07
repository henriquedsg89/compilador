import controller.Controller
import org.scalatest._

import _root_.java.util

/**
 * Authors: Henrique & Octávio
 * Date: Nov 2013
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

  "Lexico" should "work, reserved words" in {
    reserved_words.values map { value =>
      lexico.setInput(value)
      val token = lexico.nextToken()
      reserved_words.get(token.getId()).get should be (value)
    }
  }
}
