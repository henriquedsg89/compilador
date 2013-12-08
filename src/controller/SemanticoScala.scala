package controller

import scala.collection.mutable.{ArrayBuffer, HashMap}
import gals.{SemanticError, Constants, Token}
import scala.collection.mutable
import controller.ID_Constante
import java.util.logging.Logger


/**
 * User: henrique & octavio
 * Date: 07/12/13
 * Time: 21:28
 */
class SemanticoScala extends Constants {

  val log = Logger.getLogger("SemanticoScala")

  val listTabSim = new ArrayBuffer[HashMap[String, ID_Abstract]]()

  var na = 0
  var desloc = 0
  var tipoConst : String = null
  var valConst : Object = null
  var contextLID : String = null
  var npf : Int = 0;
  var posid: ID_Abstract = null

  def executeAction(action: Int, token: Token) {
    action match {
      case 1 => act01(token)
      case 2 => act02(token)
      case 3 => act03(token)
      case 4 => act04(token)
      case 75 => act75(token)
      case 79 => act79(token)
      case 80 => act80(token)
      case 81 => act81(token)
      case 82 => act82(token)
      case 83 => act83(token)
      case 84 => act84(token)
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

  def act04(token: Token) {
    contextLID = "decl"
    //TODO marca pos do primeiro id da lista(relativa a TS)
  }

  def act05(token: Token) {
    //TODO marca a pos do ultimo id da lista (relativa a TS)
  }

  def act06(token: Token) {
    //TODO
  }

  def act07(token: Token) {
    val tabSim = listTabSim(na)
    if (tabSim.contains(token.getLexeme)) {
      throw new SemanticError("Id já declarado: " + token.getLexeme)
    } else {
      val proc = new ID_Procedimento(token.getLexeme, na, 0, 0, 0, 0)
      tabSim.put(proc.nome, proc)
      npf = 0
      incNa()
    }
  }

  def act08(token: Token) {
    val tabSim = listTabSim(na)
    if (tabSim.contains(token.getLexeme)) {
      throw new SemanticError("Id já declarado: " + token.getLexeme)
    } else {
      val func = new ID_Funcao(token.getLexeme, na, 0, 0, 0, 0, null)
      tabSim.put(func.nome, func)
      npf = 0
      incNa()
    }
  }

  def act09(token: Token) {
    val tabSim = listTabSim(na)
    val funcOrProc = tabSim.get(token.getLexeme)
    try {
      val func = funcOrProc.asInstanceOf[ID_Funcao]
      val newFunc = new ID_Funcao(func.nome, func.nivel, func.desloc, func.end_prim_instr, npf,
        0, null)
    }
  }


  def act75(token: Token) {
    try{
      val id_func = posid.asInstanceOf[ID_Funcao]
    }
    catch {
      case ex: ClassCastException => log.warning(posid.name + " deveria ser uma funcao")
    }
  }

  def act79(token: Token) {
    //TODO
  }

  def act80(token: Token) {
    tipoConst = "inteiro"
    valConst = token.getLexeme
  }

  def act81(token: Token) {
    tipoConst = "real"
    valConst = token.getLexeme
  }

  def act82(token: Token) {
    tipoConst = "booleano"
    valConst = token.getLexeme
  }

  def act83(token: Token) {
    tipoConst = "booleano"
    valConst = token.getLexeme
  }

  def act84(token: Token) {
    tipoConst = "literal"
    valConst = token.getLexeme
  }
}


