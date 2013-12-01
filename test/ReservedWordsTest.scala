import controller.Controller
import org.scalatest._

import _root_.java.util

/**
 * Created with IntelliJ IDEA.
 * User: henrique
 * Date: 30/11/13
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
class ReservedWordsTest extends FlatSpec with Matchers {

  val con = new Controller()

  "Lexico" should "work, reserved words" in {
    val ids = Array(
      "programa",
      "const",
      "var",
      "proc",
      "funcao",
      "ref",
      "val",
      "cadeia",
      "vetor",
      "inteiro",
      "real",
      "booleano",
      "caracter",
      "de",
      "se",
      "entao",
      "enquanto",
      "faca",
      "leia",
      "escreva",
      "senao",
      "ou",
      "e",
      "nao",
      "falso",
      "verdadeiro")

    ids map { id =>
      con.validateLexical(id.trim) should be (con.LEX_OK_MSG, 0)
    }
  }
}
