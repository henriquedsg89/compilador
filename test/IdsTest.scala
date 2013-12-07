import _root_.java.util
import org.scalatest._
import controller._
/**
 * Authors: Henrique & Octávio
 * Date: Nov 2013
 */
class IdsTest extends FlatSpec with Matchers {

  val con = new Controller()

  "Lexico" should "work, ids" in {
    val ids = Array("letras@a_b#c", "@comeca_arrobaasdfsd", "a@s#h_lfh", "a1", "a2", "@1231", "@654s")
    ids map { id =>
      con.validateLexical(id) should be (con.LEX_OK_MSG, 0)
    }
  }

  "Lexico" should "fail, ids should begin with letter or @" in {
    val ids = Array("_a", "#a", "%a", "$a", "&a", "!a", "~a")
    //TODO Está considerando 1a como real, depois verificar e adicionar de volta
    ids map { id =>
      con.validateLexical(id) should not be (con.LEX_OK_MSG, 0)
    }
  }

  "Lexico" should "fail, ids should not end with special char" in {
    val ids = Array("a_", "a@", "a#")
    ids map { id =>
      con.validateLexical(id) should not be (con.LEX_OK_MSG, 0)
    }
  }

  "Lexico" should "fail, ids should not contains special char together" in {
    val ids = Array("a_@#a", "@@sdfg", "a#fas_@ad", "f_#@_fa")
    ids map { id =>
      con.validateLexical(id) should not be (con.LEX_OK_MSG, 0)
    }
  }
}
