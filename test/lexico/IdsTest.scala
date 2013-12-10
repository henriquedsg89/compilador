package lexico

import _root_.java.util
import org.scalatest._
import controller._
import gals.Constants
/**
 * Authors: Henrique & Octávio
 * Date: Dez 2013
 */
class IdsTest extends FlatSpec with Matchers {

  val con = new Controller()
  val lexico = con.lexico

  "Dado identificadores validos" should "gerar token com id de identificador sem nenhum erro lexico" in {
    val ids = Array("letras@a_b#c", "@comeca_arroba", "a@s#h_lfh", "a1", "a2", "@1231", "@6_54a#s")
    ids map { id =>
      lexico.setInput(id)
      val token = lexico.nextToken()
      token.getId should be (Constants.t_id)
    }
  }

  "Dado intificadores com comeco invalido" should "gerar erro lexico" in {
    val ids = Array("_a", "#a", "%a", "$a", "&a", "!a", "~a")
    ids map { id =>
      con.validateLexical(id) should not be (con.LEX_OK_MSG, 0)
    }

    con.validateLexical("1a") should be (con.LEX_OK_MSG, 0)
  }

  "Dado indentificadores com final invalido" should "gerar erro lexico" in {
    val ids = Array("a_", "a@", "a#", "#", "@")
    ids map { id =>
      con.validateLexical(id) should not be (con.LEX_OK_MSG, 0)
    }
  }

  "Dado identificadores com caracter especial repetido" should "gerar erro lexico" in {
    val ids = Array("a_@#a", "@@sdfg", "a#fas_@ad", "f_#@_fa")
    ids map { id =>
      con.validateLexical(id) should not be (con.LEX_OK_MSG, 0)
    }
  }
}
