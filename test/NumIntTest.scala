import controller.Controller
import gals.Constants
import org.scalatest._

/**
 * Authors: Henrique & Octávio
 * Date: Nov 2013
 */
class NumIntTest extends FlatSpec with Matchers {

  val lexico = new Controller().lexico

  "Numeros inteiros" should "podem aceitar sinal na frente" in {
    lexico.setInput("+10")
    val token = lexico.nextToken()
    token.getId() should be (Constants.t_num_int)
  }

  "Numeros inteiros" should "podem não ter sinal" in {
    lexico.setInput("10")
    val token = lexico.nextToken()
    token.getId should be (Constants.t_num_int)
  }

  "Numeros inteiros" should "podem ter exponencial" in {
    lexico.setInput("10e7 10E-1 10E+712")
    val token = lexico.nextToken()
    token.getId should be (Constants.t_num_int)
  }

  "Reais" should "não deveriam ser considerados inteiros" in {
    val doubles = Array("10.7", "14.74", "444.4")
    doubles map { d =>
      lexico.setInput(d)
      lexico.nextToken().getId should not be (Constants.t_num_int)
    }
  }

  "Ids" should "não deveriam ser considerados inteiros" in {
    val ids = Array("asdfasdf", "@sdfa", "@sadf1123")
    ids map { id =>
      lexico.setInput(id)
      lexico.nextToken().getId should not be (Constants.t_num_int)
    }
  }

}
