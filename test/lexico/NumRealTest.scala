package lexico

import controller.Controller
import gals.Constants
import org.scalatest._
/**
 * Authors: Henrique & Octávio
 * Date: Dez 2013
 */
class NumRealTest extends FlatSpec with Matchers {

  val lexico = new Controller().lexico

  "Numeros reais" should "possuir ponto decimal, no meio, inicio ou fim" in {
    val reals = Array(".0", "0.0", "0.")
    reals map { r =>
      lexico.setInput(r)
      val token = lexico.nextToken()
      token.getId should be (Constants.t_num_real)
    }
  }

  "Numeros reais não" should "aceitar sinal na frente" in {
    lexico.setInput("+10.0")
    val token = lexico.nextToken()
    token.getId() should not be (Constants.t_num_real)

    val token2 = lexico.nextToken()
    token2.getId() should be (Constants.t_num_real)
  }

  "Numeros reais" should "aceitar parte exponencial" in {
    val reals = Array("10.4e7", ".4E-1", "10.E+712")
    reals map { r =>
      lexico.setInput(r)
      val token = lexico.nextToken()
      token.getId should be (Constants.t_num_real)
    }
  }

  "Numeros reais não" should "ter exponencial sem valores" in {
    val ints = Array("10.0e", "10.0E", "10.0E+")
    ints map { i =>
      lexico.setInput(i)
      val token = lexico.nextToken()
      token.getId should be (Constants.t_num_real)
      val token2 = lexico.nextToken()
      token2.getId should not be (Nil)
    }
  }

  "Inteiros não" should "ser considerados reais" in {
    val doubles = Array("10", "4")
    doubles map { d =>
      lexico.setInput(d)
      lexico.nextToken().getId should not be (Constants.t_num_real)
    }
  }

  "Ids não" should "ser considerados reais" in {
    val ids = Array("asdfasdf", "@sdfa", "@sadf1123")
    ids map { id =>
      lexico.setInput(id)
      lexico.nextToken().getId should not be (Constants.t_num_real)
    }
  }

  "Dado exemplo 1..2" should "nao gerar dois tokens real" in {
    lexico.setInput("1..2")
    lexico.nextToken().getId should not be (Constants.t_num_real)
  }
}
