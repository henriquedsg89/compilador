package lexico

import controller.Controller
import gals.Constants
import org.scalatest._

/**
 * Authors: Henrique & Octávio
 * Date: Nov 2013
 */
class NumIntTest extends FlatSpec with Matchers {

  val lexico = new Controller().lexico

  "Numeros inteiros não" should "aceitar sinal na frente" in {
    lexico.setInput("+10")
    val token = lexico.nextToken()
    token.getId() should not be (Constants.t_num_int)

    val token2 = lexico.nextToken()
    token2.getId() should be (Constants.t_num_int)
  }

  "Numeros inteiros" should "não ter sinal" in {
    lexico.setInput("10")
    val token = lexico.nextToken()
    token.getId should be (Constants.t_num_int)
  }

  "Numeros inteiros" should "aceitar parte exponencial" in {
    val ints = Array("10e7", "10E-1", "10E+712")
    ints map { i =>
      lexico.setInput(i)
      val token = lexico.nextToken()
      token.getId should be (Constants.t_num_int)
    }
  }

  "Numeros inteiros não" should "ter exponencial sem valores" in {
    val ints = Array("10e", "10E", "0E")
    ints map { i =>
      lexico.setInput(i)
      val token = lexico.nextToken()
      token.getId should be (Constants.t_num_int)
      val token2 = lexico.nextToken()
      token2.getId should not be (Nil)
    }
  }

  "Reais não" should "ser considerados inteiros" in {
    val doubles = Array("10.7", "14.74", "444.4")
    doubles map { d =>
      lexico.setInput(d)
      lexico.nextToken().getId should not be (Constants.t_num_int)
    }
  }

  "Ids não" should "deveriam ser considerados inteiros" in {
    val ids = Array("asdfasdf", "@sdfa", "@sadf1123")
    ids map { id =>
      lexico.setInput(id)
      lexico.nextToken().getId should not be (Constants.t_num_int)
    }
  }

}
