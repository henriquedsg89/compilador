package lexico

import controller.Controller
import gals.Constants
import org.scalatest._
/**
 * Authors: Henrique & Octávio
 * Date: Dez 2013
 */
class SimbolTest extends FlatSpec with Matchers {
  val lexico = new Controller().lexico
  val simbolos = Map(
    6 -> "+",
    7 -> "-",
    8 -> "*",
    9 -> "/",
    10 -> "(",
    11 -> ")",
    12 -> ";",
    13 -> ":",
    14 -> ".",
    15 -> "=",
    16 -> ",",
    17 -> "{",
    18 -> "}",
    19 -> "[",
    20 -> "]",
    21 -> "..",
    22 -> ":=",
    23 -> "<",
    24 -> ">",
    25 -> "<>",
    26 -> "<=",
    27 -> ">=")

  "Dado todos os simbolos simples e complexos validos" should "validar cada simbolo com seu token id" in {
    simbolos.values map { value =>
      lexico.setInput(value)
      val token = lexico.nextToken()
      simbolos.get(token.getId).get should be (value)
    }
  }
}
