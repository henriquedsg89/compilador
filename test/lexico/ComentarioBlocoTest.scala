package lexico

import controller.Controller
import gals.{LexicalError, Constants}
import org.scalatest._
/**
 * Authors: Henrique & Octávio
 * Date: Dez 2013
 */
class ComentarioBlocoTest extends FlatSpec with Matchers {

  val lex = new Controller().lexico

  "Cometario de bloco" should "aceitar qualquer coisa" in {
    lex.setInput("/*asdf +10 -15 .5 10e15 14.e asdfasdf \n\\\n\t asdf @@@****asdf +14sa*/")
    lex.nextToken() should be (null)
  }

  "Cometario de bloco nao fechado" should "gerar erro lexico" in {
    lex.setInput("/*asdf +10 -15 .5 10e15 comentario nao fechado.*asd***** /")
    lex.nextToken().getLexeme should be ("/")
    lex.nextToken().getLexeme should be ("*")
  }
}