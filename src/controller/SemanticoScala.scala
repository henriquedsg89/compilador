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

  var tipoConst, tipoVar, tipoResultadoFuncao, contextoLID, tipoExpr,
    tipoExpSimples, tipoTermo, tipoFator, opRel, operador, tipoResultadoOperacao,
    mpp: String = null
  var opNega, opUnario: Boolean = false
  var valConst, valVar : Object = null
  var na, desloc, npf, npa : Int = 0
  var posid: ID_Abstract = null

  def executeAction(action: Int, token: Token) {
    action match {
      case 1 => act01(token)
      case 2 => act02(token)
      case 3 => act03(token)
      case 4 => act04(token)
      case 5 => act05(token)
      case 6 => act06(token)
      case 7 => act07(token)
      case 8 => act08(token)
      case 9 => act09(token)
      //TODO: verificar se nao da merda usar range dentro do case
      case 46 until 51 => act46(token) //tem mesma implementacao
      case 55 until 57 => act55(token)
      case 64 until 66 => act64(token)
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
    contextoLID = "decl"
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
      posid = proc
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
      posid = func
      npf = 0
      incNa()
    }
  }

  def act09(token: Token) {
    val tabSim = listTabSim(na)
    val funcOrProc = tabSim.get(posid.absNome)
    try {
      val func = funcOrProc.asInstanceOf[ID_Funcao]
      val newFunc = new ID_Funcao(func.nome, func.nivel, func.desloc, func.end_prim_instr, npf,
        0, null)
      tabSim.put(newFunc.nome, newFunc)
    } catch {
      case ex: ClassCastException => {
        val proc = funcOrProc.asInstanceOf[ID_Procedimento]
        val newProc = new ID_Procedimento(proc.nome, proc.nivel, proc.desloc, proc.end_prim_instr, npf, proc.list_marams)
        tabSim.put(newProc.nome, newProc)
      }
    }
  }

  def act10(token: Token) {
    //TODO
  }

  def act11(token: Token) {
    //FIXME verificar -> retirar as variavies declaradas localmente
    listTabSim remove na
    na = na - 1
  }

  def act12(token: Token) {
    contextoLID = "par-formal"
    //TODO marca pos do primero id da lista (relativa a TS)
  }

  def act13(token: Token) {
    //TODO marca pos do ultimo elemento da lista (relativa a TS)
  }

  def act14(token: Token) {
    //TODO
  }

  def act15(token: Token) {
    mpp = "referencia"
  }

  def act16(token: Token) {
    mpp = "valor"
  }

  def act17(token: Token) {
    if (contextoLID == "decl") {
      val tabSim = listTabSim(na)
      if (tabSim.contains(token.getLexeme)) {
        throw new SemanticError("Id já declarado: " + token.getLexeme)
      } else {
        tabSim.put(token.getLexeme, new ID_Variavel(token.getLexeme, null, null))
      }
    } else if (contextoLID == "par-formal") {
      val tabSim = listTabSim(na)
      if (tabSim.contains(token.getLexeme)) {
        throw new SemanticError("Id já declarado: " + token.getLexeme)
      } else {
        npf = npf + 1
        tabSim.put(token.getLexeme, new ID_Variavel(token.getLexeme, null, null))
      }
    } else if (contextoLID == "leitura") {
      val tabSim = listTabSim(na)
      if (!tabSim.contains(token.getLexeme)) {
        throw new SemanticError("Id não declarado: " + token.getLexeme)
      } else {
        val id = tabSim.get(token.getLexeme)
        if (id.isInstanceOf[ID_Variavel]) {
          if (id.asInstanceOf[ID_Variavel].tipo != "pre-definido") {
            throw new SemanticError("Tipo de Id Inválido")
          }
          //TODO gera código leitura
        } else if (id.isInstanceOf[ID_Parametro]) {
          if (id.asInstanceOf[ID_Parametro].tipo != "pre-definido") {
            throw new SemanticError("Tipo de Id Inválido")
          }
          //TODO gera código leitura
        } else {
          throw new SemanticError("Apenas var podem ser lidas")
        }
      }
    }
  }









  def act44(token: Token) {
    tipoExpr = tipoExpSimples
  }

  def act45(token: Token) {
    if(!tipoExpSimples.equalsIgnoreCase(tipoExpr))
      log.warning("Operandos incompativeis")
    else
      tipoExpr = "booleano"
  }

  def act46(token: Token) {//ate act51 a implementacao eh a mesma!
    opRel = token.getLexeme
  }

  def act52(token: Token) {
    tipoExpSimples = tipoTermo
  }

  def act53(token: Token) {
    //TODO: verificar se operador se aplica a tipoExpSimples
  }

  def act55(token: Token) {
    operador = token.getLexeme
  }

  def act58(token: Token) {
    tipoTermo = tipoFator
  }

  def act59(token: Token) {
    //TODO: se operador nao se aplica a tipoTermo
    log.warning("Operador e operando incompativeis")
  }

  def act60(token: Token) {
    if(!tipoFator.equalsIgnoreCase(tipoTermo))//TODO: podem ser diferentes mas compativeis
      log.warning("Operandos incompativeis")
    else
      tipoTermo = tipoResultadoOperacao
  }

  def act64(token: Token) {
    operador = token.getLexeme
  }

  def act67(token: Token) {
    if(opNega)
      log.warning("Operadores nao consecutivos")
    else
      opNega = true
  }

  def act68(token: Token) {
    if(!tipoFator.equalsIgnoreCase("booleano"))
      log.warning("Op. 'nao' exige operando booleano")
  }

  def act69(token: Token) {
    if(opUnario)
      log.warning("Ops. 'unario' consecutivos")
    else
      opUnario = true
  }

  def act70(token: Token) {
    if(!tipoFator.equalsIgnoreCase("inteiro")|| !tipoFator.equalsIgnoreCase("real"))
      log.warning("Op. '+/-' exige operando numerico")
  }

  def act71(token: Token) {
    opNega = false
    opUnario = false
  }

  def act72(token: Token) {
    tipoFator = tipoExpr
  }

  def act73(token: Token) {
    tipoFator = tipoVar
  }

  def act74(token: Token) {
    tipoFator = tipoConst
  }

  def act75(token: Token) {
    try{
      posid.asInstanceOf[ID_Funcao] //TODO: testar se realmente verifica posid ser uma funcao
    }
    catch {
      case ex: ClassCastException => log.warning(posid.absNome + " deveria ser uma funcao")
    }
  }

  def act76(token: Token) {
    if(npa == npf)
      tipoVar = tipoResultadoFuncao
    else
      log.warning("Erro na quantidade de parametros")
  }

  def act77(token: Token) {
    //TODO
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


