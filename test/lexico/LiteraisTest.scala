package lexico

import org.scalatest._
import controller.Controller
import gals.{LexicalError, Constants}

/**
 * Authors: Henrique & Octávio
 * Date: Nov 2013
 */
class LiteraisTest extends FlatSpec with Matchers {

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

  "Literais" should "aceitar continuacao em outra linha" in {
    lex.setInput("'blabla da primeira linha\n blabla da segunda linha\nterceira\tlinha'")
    val token = lex.nextToken()
    token.getId should be (Constants.t_literal)
  }

  "Literal não fechado" should "gerar erro léxico" in {
    lex.setInput("'deve_dar_erro lexico pq nao esta fechado")
    a [LexicalError] should be thrownBy {
      lex.nextToken()
    }
  }

  "Literal" should "aceitar caracteres invalidos para outros fins" in {
    lex.setInput("'+10 +.14 .41 10 7'")
    lex.nextToken().getId should be (Constants.t_literal)
    lex.nextToken() should be (null)
  }

}
