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
    mpp, tipoAtual, tipoLadoEsq, tipoVarIndexada, tipoConstVetorLimSup, tipoConstVetorLimInf, contextoEXPR, tipoIndiceDim1,
    tipoIndiceDim2, tipoElementos: String = null
  var opNega, opUnario: Boolean = false
  var valConst, valVar : Object = null
  var na, desloc, npf, npa, limInfVetor, limSupVetor, numIndices, numDim : Int = 0
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
      case 10 => act10(token)
      case 11 => act11(token)
      case 12 => act12(token)
      case 13 => act13(token)
      case 14 => act14(token)
      case 15 => act15(token)
      case 16 => act16(token)
      case 17 => act17(token)
      case 18 => act18(token)
      case 19 => act19(token)
      case 20 => act20(token)
      case 21 => act21(token)
      case 22 => act22(token)
      case 23 => act23(token)
      case 24 => act24(token)
      case 25 => act25(token)
      case 26 => act26(token)
      case 27 => act27(token)
      case 28 => act28(token)
      case 29 => act29(token)
      case 30 => act30(token)
      case 31 => act31(token)
      case 32 => act32(token)
      case 33 => act33(token)
      case 34 => act34(token)
      case 35 => act35(token)
      case 36 => //TODO: act36(token)
      case 37 => //TODO: act37(token)
      case 38 => act38(token)
      case 39 => //TODO: act39(token)
      case 40 => act40(token)
      case 41 => act41(token)
      case 42 => //TODO: act42(token)
      case 43 => //TODO: act43(token)
      case 44 => act44(token)
      case 45 => act45(token)
      case 46 => act46(token)
      case 47 => act46(token)
      case 48 => act46(token)
      case 49 => act46(token)
      case 50 => act46(token)
      case 51 => act46(token)
      case 52 => act52(token)
      case 53 => act53(token)
      case 54 => act54(token)
      case 55 => act55(token)
      case 56 => act55(token)
      case 57 => act55(token)
      case 58 => act58(token)
      case 59 => act59(token)
      case 60 => act60(token)
      case 64 => act64(token)
      case 65 => act64(token)
      case 66 => act64(token)
      case 67 => act67(token)
      case 68 => act68(token)
      case 69 => act69(token)
      case 70 => act70(token)
      case 71 => act71(token)
      case 72 => act72(token)
      case 73 => act73(token)
      case 74 => act74(token)
      case 75 => act75(token)
      case 76 => act76(token)
      case 77 => act77(token)
      case 78 => //TODO: act78(token)
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

  def jaDeclarado(token: Token): Boolean = {
    var i = na
    var ret = false
    while(i >= 0 && ret == false) {
      ret = listTabSim(i).contains(token.getLexeme)
      i = i - 1
    }
    ret
  }

  def pegaTabSim(id: String) = {
    var tabSim: HashMap[String, ID_Abstract] = null
    var i = na

    while(tabSim == null && i >= 1) {
      if (listTabSim(i).contains(id)) {
        tabSim = listTabSim(i)
      }
      i = i - 1
    }
    tabSim
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
    if (jaDeclarado(token)) {
      throw new SemanticError("Id já declarado: " + token.getLexeme)
    } else {
      posid = new ID_Constante(token.getLexeme,na,0, null, null)
      val tabSim = listTabSim(na)
      tabSim.put(token.getLexeme, posid)
    }
  }

  def act03(token: Token) {
    val tabSim = pegaTabSim(posid.absNome)
    val id = tabSim.get(posid.absNome).get.asInstanceOf[ID_Constante]
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
    if (jaDeclarado(token)) {
      throw new SemanticError("Id já declarado: " + token.getLexeme)
    } else {
      val tabSim = pegaTabSim(token.getLexeme)
      val proc = new ID_Procedimento(token.getLexeme, na, 0, 0, 0, 0)
      tabSim.put(proc.nome, proc)
      posid = proc
      npf = 0
      incNa()
    }
  }

  def act08(token: Token) {
    if (jaDeclarado(token)) {
      throw new SemanticError("Id já declarado: " + token.getLexeme)
    } else {
      val tabSim = pegaTabSim(token.getLexeme)
      val func = new ID_Funcao(token.getLexeme, na, 0, 0, 0, 0, null)
      tabSim.put(func.nome, func)
      posid = func
      npf = 0
      incNa()
    }
  }

  def act09(token: Token) {
    val tabSim = pegaTabSim(posid.absNome)
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
      if (jaDeclarado(token)) {
        throw new SemanticError("Id já declarado: " + token.getLexeme)
      } else {
        val tabSim = pegaTabSim(token.getLexeme)
        tabSim.put(token.getLexeme, new ID_Variavel(token.getLexeme, null, null))
      }
    } else if (contextoLID == "par-formal") {
      if (jaDeclarado(token)) {
        throw new SemanticError("Id já declarado: " + token.getLexeme)
      } else {
        npf = npf + 1
        val tabSim = pegaTabSim(token.getLexeme)
        tabSim.put(token.getLexeme, new ID_Variavel(token.getLexeme, null, null))
      }
    } else if (contextoLID == "leitura") {
      if (!jaDeclarado(token)) {
        throw new SemanticError("Id não declarado: " + token.getLexeme)
      } else {
        val tabSim = pegaTabSim(token.getLexeme)
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

  def act18(token: Token) {
    if (tipoConst != "inteiro") {
      throw new SemanticError("Esperava-se uma constante inteira")
    } else if (valConst.isInstanceOf[String] && valConst.asInstanceOf[String].length > 256) {
      throw new SemanticError("Tamanho da cadeia maior que o permitido! Até 256 caracteres")
    } else {
      tipoAtual = "cadeia"
    }
  }

  def act19(token: Token) {
    if (tipoConst != "inteiro" && tipoConst != "caracter") {
      throw new SemanticError("Tipo do índice inválido")
    } else {
      tipoConstVetorLimInf = tipoConst
      limInfVetor = token.getLexeme.asInstanceOf[Int]
    }
  }

  def act20(token: Token) {
    if (tipoConst != tipoConstVetorLimInf && tipoConst != tipoConstVetorLimInf) {
      throw new SemanticError("Ctes do interv devem ser do mesmo tipo")
    } else if (valConst.asInstanceOf[Int] <= limInfVetor) {
      throw new SemanticError("Lim Sup Deve ser >  que L. Inf.")
    } else {
      tipoConstVetorLimSup = tipoConst
      limSupVetor = token.getLexeme.asInstanceOf[Int]
    }
  }

  def act21(token: Token) {
    tipoElementos = tipoAtual
    tipoAtual = "vetor"
    if (numDim == 2)
      tipoIndiceDim2 = tipoConst//FIXME:verificar se esta ok
  }

  def act22(token: Token) {
    //FIXME:registra info
    val subCat = new Vetor(numDim, tipoIndiceDim1, tipoElementos, limInfVetor, limSupVetor)

    val tabSim = pegaTabSim(token.getLexeme)

    numDim = 2
  }


  def act23(token: Token) {
    //TODO: registra info
    numDim = 1
  }

  def act24(token: Token) {
    if (!jaDeclarado(token)) {
      throw new SemanticError("Id não declarado: " + token.getLexeme)
    } else if (!pegaTabSim(token.getLexeme).get(token.getLexeme).isInstanceOf[ID_Constante]){
      throw new SemanticError("Esperava-se um id de constante")
    } else {
      valConst = token.getLexeme
    }
  }

  def act25(token: Token) {
    tipoAtual = "inteiro"
  }

  def act26(token: Token) {
    tipoAtual = "real"
  }

  def act27(token: Token) {
    tipoAtual = "booleano"
  }

  def act28(token: Token) {
    tipoAtual = "caracter"
  }

  def act29(token: Token) {
    val tabSim = pegaTabSim(token.getLexeme)
    if (!tabSim.contains(token.getLexeme)) {
      throw new SemanticError("Identificador não declarado")
    } else {
      posid = tabSim.get(token.getLexeme).get
    }
  }

  def act30(token: Token) {
    if (tipoExpr != "booleano" && tipoExpr != "inteiro")
       throw new SemanticError("Tipo inválido da expressão")
    else
      "Gera código"//TODO
  }

  def act31(token: Token) {
    contextoLID = "leitura"
  }

  def act32(token: Token) {
    contextoEXPR = "impressao"
    val tiposParaImp = Array("inteiro", "real", "caracter", "cadeia")
    if (!tiposParaImp.contains(tipoExpr))
      throw new SemanticError("Tipo inválido para impressão")
    else
      "G. Código"//TODO
  }

  def act33(token: Token){//TODO: revisar que a porra eh foda e eu to com sono
    if(posid.isInstanceOf[ID_Variavel]) {
      if(posid.asInstanceOf[ID_Variavel].tipo == "vetor") {
        throw new SemanticError(posid.absNome + " deveria ser indexado")
      } else {
        tipoLadoEsq = posid.asInstanceOf[ID_Variavel].tipo
      }
    } else if (posid.isInstanceOf[ID_Parametro]) {
      if(posid.asInstanceOf[ID_Parametro].tipo == "vetor") {
        throw new SemanticError(posid.absNome + " deveria ser indexado")
      } else {
        tipoLadoEsq = posid.asInstanceOf[ID_Parametro].tipo
      }
    } else if(posid.isInstanceOf[ID_Funcao]) {
      if(false) { //TODO:se fora do escopo da funcao id
        throw new SemanticError("fora do escopo da funcao")
      } else {
        tipoLadoEsq = posid.asInstanceOf[ID_Funcao].tipo_resultado
      }
    } else {
      throw new SemanticError(posid.absNome + " deveria ser var/par/funcao")
    }
  }

  def act34(token: Token) {
    if(tipoExpr != tipoLadoEsq) //TODO: verificar compatibilidade, podem ser diferentes
      throw new SemanticError("esperava-se uma variavel")
  }

  def act35(token: Token) {
    if(!posid.isInstanceOf[ID_Variavel])
      throw new SemanticError("esperava-se uma variavel")
    else if(posid.asInstanceOf[ID_Variavel].tipo == "vetor")
      tipoVarIndexada = "vetor"
    else if (posid.asInstanceOf[ID_Variavel].tipo == "cadeia")
      tipoVarIndexada = "cadeia"
    else
      throw new SemanticError("apenas vetores e cadeias podem ser indexados")
  }

  def act36(token: Token) {
    numIndices = 1
    if (tipoVarIndexada == "vetor") {
      if (tipoExpr != tipoIndiceDim1)
        throw new SemanticError("Tipo indice inválido")
      else
          tipoLadoEsq = tipoElementos
    } else {//cadeia
      if (tipoExpr != "inteiro")
        throw new SemanticError("Indice deveria ser inteiro")
      else
        tipoLadoEsq = "caracter"
    }
  }

  def act38(token: Token) {
    if(!posid.isInstanceOf[ID_Procedimento])
      throw new SemanticError(posid.absNome + " deveria ser uma procedure")
  }

  def act40(token: Token) {
    if(npa==npf)
      "Gerar código cham"//TODO: gerar codigo p/ chamada de proc
    else
      throw new SemanticError("Erro na quantidade de parametros")
  }

  def act41(token: Token) {
    if(!posid.isInstanceOf[ID_Procedimento])
      throw new SemanticError(posid.absNome + " deveria ser uma procedure")
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

  def act54(token: Token) {
    //TODO: tipo termo incompativil com tipo expsimples -> ERRO
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


