package lexico

import controller.Controller
import gals.{LexicalError, Constants}
import org.scalatest._
/**
 * User: henrique & octavio
 * Date: 07/12/13
 * Time: 18:26
 */
class ComentarioBlocoTest extends FlatSpec with Matchers {

  val lex = new Controller().lexico

  "Cometario de bloco" should "aceitar qualquer coisa" in {
    lex.setInput("/*asdf +10 -15 .5 10e15 14.e asdfasdf \n\\\n\t asdf @@@****asdf +14sa*/")
    lex.nextToken() should be (null)
  }

  "Cometario de bloco nao fechado" should "gerar erro lexico" in {
    lex.setInput("/*asdf +10 -15 .5 10e15 comentario nao fechado.*asd***** /")
    a [LexicalError] should be thrownBy {
      lex.nextToken()
    }
  }
}