package controller

import scala.collection.mutable.{ArrayBuffer, HashMap}
import gals.{SemanticError, Constants, Token}
import scala.collection.mutable
import controller.ID_Constante


/**
 * User: henrique & octavio
 * Date: 07/12/13
 * Time: 21:28
 */
class SemanticoScala extends Constants {

  val listTabSim = new ArrayBuffer[HashMap[String, ID_Abstract]]()

  var na = 0
  var desloc = 0
  var tipoConst : String = null
  var valConst : Object = null

  def executeAction(action: Int, token: Token) {
    action match {
      case 1 => act01(token)
      case 2 => act02(token)
      case 3 => act03(token)
      case _ => throw new Exception("Ação não Implementada: Acao = " + action)
    }
  }

  def incNa() {
    na = na + 1
    if (listTabSim.size <= na) {
      listTabSim += new mutable.HashMap[String, ID_Abstract]()
    }
  }

  def act01(token: Token) {
    na = 0;

    val prog = new ID_Programa(token.getLexeme)
    if (listTabSim.size <= na) {
      listTabSim += new mutable.HashMap[String, ID_Abstract]()
    }
    listTabSim(na).put(prog.nome, prog)

    incNa()
    desloc = 0
  }

  def act02(token: Token) {
     val tabSim = listTabSim(na)
    if (tabSim.contains(token.getLexeme)) {
      throw new SemanticError("Id já declarado: " + token.getLexeme)
    } else {
      tabSim.put(token.getLexeme, new ID_Constante(token.getLexeme,na,0, null, null))
    }
  }

  def act03(token: Token) {
    val tabSim = listTabSim(na)
    val id = tabSim.get(token.getLexeme).asInstanceOf[ID_Constante]
    val newId = new ID_Constante(id.nome, id.nivel, id.desloc, tipoConst, valConst)
    tabSim.put(newId.nome, newId)
  }



}


