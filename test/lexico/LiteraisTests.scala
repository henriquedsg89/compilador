package lexico

import org.scalatest._
import controller.Controller
import gals.Constants

/**
 * Authors: Henrique & Octávio
 * Date: Nov 2013
 */
class LiteraisTests extends FlatSpec with Matchers {

  val lex = new Controller().lexico

  "Literais" should "estar entre apostrofo" in {
    val lits = Array("'wololo'", "'dbz,.,!2as@dbz'", "'10'", "'52.2'", "'74e14'")
    lits map { l =>
      lex.setInput(l)
      val token = lex.nextToken()
      token.getId should be (Constants.t_literal)
    }
  }

  "Literais não" should "estar entre aspas" in {
    val lits = Array("\"wololo\"", "\"dbz,.,!2as@dbz\"", "'10'", "'52.2'", "'74e14'")
    lits map { l =>
      lex.setInput(l)
      val token = lex.nextToken()
      token.getId should not be (Constants.t_literal)
    }
  }

  "Literais" should "aceitar 'pato''dagua'" in {
    lex.setInput("'pato''dagua'")
    val token = lex.nextToken()
    token.getId should be (Constants.t_literal)
  }
}
