package lexico

import org.scalatest.{Matchers, FlatSpec}
import controller.Controller
import gals.Constants

/**
 * Authors: Henrique & Octávio
 * Date: Dez 2013
 */
class ComentarioLinhaTest extends FlatSpec with Matchers {
  val con = new Controller
  val lexico = con.lexico

  "Dado apenas comentario de linha valido" should "retornar nenhum token" in {
    val comentarios = Array("//comentario simples", "//caracteres especiais *?//aa // */")
    comentarios map { coment =>
      lexico.setInput(coment)
      lexico.nextToken() should be (null)
    }
  }

  "Dado comentario de linha com quebra de linha seguido de identificador" should "ler proximo token sem erro lexico" in {
    lexico.setInput("//comentario antes de quebra de linha \n @identificador")
    lexico.nextToken().getId should be (Constants.t_id)
  }
}
