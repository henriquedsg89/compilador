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
    val rws = "programa, var, caracter, cadeia, proc, inicio, fim, inteiro, booleano, " +
      "funcao, se, entao, senao, leia, escreva, ou, e, nao, falso, verdadeiro, " +
      "de, faca, real, vetor, enquanto"

    val ids = rws.split(",")
    ids map { id =>
      con.validateLexical(id.trim) should be (con.LEX_OK_MSG, 0)
    }
  }
}
