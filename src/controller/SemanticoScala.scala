package controller

import scala.collection.mutable.{ArrayBuffer, ListBuffer, HashMap, LinkedList}
import gals.{Constants, Token}
import scala.collection.mutable
import com.sun.xml.internal.bind.v2.model.core.ID


/**
 * User: henrique & octavio
 * Date: 07/12/13
 * Time: 21:28
 */
class SemanticoScala extends Constants {

  val listTabSim = new ArrayBuffer[HashMap[String, ID_Abstract]]()


  var na = 0
  var desloc = 0

  def executeAction(action: Int, token: Token) {
    action match {
      case 1 => act01(token)
    }
  }

  def act01(token: Token) {
    val prog = new ID_Programa(token.getLexeme)

    if (listTabSim.size <= na) {
      listTabSim += new mutable.HashMap[String, ID_Abstract]()
    }
    listTabSim(na).put(prog.nome, prog)

    na = 1
    desloc = 0
  }

}


