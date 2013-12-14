package controller

import scala.collection.mutable.{ArrayBuffer, HashMap, Stack}
import gals.{SemanticError, Constants, Token}
import scala.collection.mutable
import java.util.logging.Logger
/**
 * Authors: Henrique & Octávio
 * Date: Dez 2013
 */
class SemanticoScala extends Constants {

  val log = Logger.getLogger("SemanticoScala")
  var listTabSim = new ArrayBuffer[HashMap[String, ID_Abstract]]()

  var tipoConst, tipoVar, tipoResultadoFuncao, contextoLID, tipoExpr, valConst,
    tipoExpSimples, tipoTermo, tipoFator, opRel, operador, tipoResultadoOperacao,
    mpp, tipoAtual, tipoLadoEsq, tipoVarIndexada, tipoConstVetorLimSup, tipoConstVetorLimInf, contextoEXPR,
    tipoIndiceDim2, tipoElementos: String = null
  var opNega, opUnario: Boolean = false
  var valVar : Object = null
  var na, desloc, npf, npa, limInfVetor, limSupVetor, numIndices, numDim : Int = 0


  var posid: Stack[ID_Abstract] = new Stack[ID_Abstract]()


  var dimensao1, dimensao2: Dimensao = null
  var vetor_temp: Vetor = null
  var lids = new ArrayBuffer[ID_Abstract]()

  var forcaReal = false
  var faltaReturn = false
  var nomeFunc: String = null

  val tiposPreDefinidos = Array("inteiro", "real", "booleano", "caracter")

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
      case 36 => act36(token)
      case 37 => act37(token)
      case 38 => act38(token)
      case 39 => act39(token)
      case 40 => act40(token)
      case 41 => act41(token)
      case 42 => act42(token)
      case 43 => act43(token)
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
      case 78 => act78(token)
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
    verificaNomePrograma(token)

    var id: ID_Abstract = null
    var i = na
    var ret = false
    while(i >= 0 && ret == false) {
      ret = listTabSim(i).contains(token.getLexeme)
      i = i - 1
    }
    ret
  }

  def pegaTabSim(id: String) = {
    verificaNomePrograma(id)

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

  def getLiteralAsInt(l: String) :Int = {
    val li : Char = l.charAt(1)
    Character.getNumericValue(li)
  }

  def verificaNomePrograma(token: Token) {
    if (listTabSim(0).contains(token.getLexeme)) {
      throw new SemanticError("Id do programa não pode ser utilizado", token.getPosition)
    }
  }

  def verificaNomePrograma(id: String) {
    if (listTabSim(0).contains(id)) {
      throw new SemanticError("Id do programa não pode ser utilizado", 0)
    }
  }

  def act01(token: Token) {
    posid = new Stack[ID_Abstract]()
    listTabSim = new ArrayBuffer[HashMap[String, ID_Abstract]]()
    na = 0;

    val prog = new ID_Programa(token.getLexeme, token.getPosition)
    if (listTabSim.size <= na) {
      listTabSim += new mutable.HashMap[String, ID_Abstract]()
    }
    listTabSim(na).put(prog.nome, prog)

    incNa()
    desloc = 0
  }

  def act02(token: Token) {
    if (jaDeclarado(token)) {
      throw new SemanticError("Id já declarado: " + token.getLexeme, token.getPosition)
    } else {
      posid.push(new ID_Constante(token.getLexeme,na,0, null, null, token.getPosition))
      val tabSim = listTabSim(na)
      tabSim.put(token.getLexeme, posid.head)
    }
  }

  def act03(token: Token) {
    val tabSim = pegaTabSim(posid.head.absNome)
    val id = tabSim.get(posid.head.absNome).get.asInstanceOf[ID_Constante]
    val newId = new ID_Constante(id.nome, id.nivel, id.desloc, tipoConst, valConst, token.getPosition)
    tabSim.put(newId.nome, newId)

    posid.pop
  }

  def act04(token: Token) {
    contextoLID = "decl"
    lids = new ArrayBuffer[ID_Abstract]()
  }

  def act05(token: Token) {
    //FIXME: marca a pos do ultimo id da lista (relativa a TS) ->nao usado
  }

  def act06(token: Token) {
    lids map { id =>
      val tabSim = pegaTabSim(id.absNome)
      val oldId = tabSim.get(id.absNome).get.asInstanceOf[ID_Variavel]
      var newId = new ID_Variavel(null,0,null,null, token.getPosition)
      if(tipoAtual == "vetor")
        newId = ID_Variavel(oldId.nome, na, tipoAtual, vetor_temp, token.getPosition)
      else
        newId = new ID_Variavel(oldId.nome, na, tipoAtual, oldId.subCategoria, token.getPosition)
      tabSim.put(id.absNome, newId)
    }
  }

  def act07(token: Token) {
    if (jaDeclarado(token)) {
      throw new SemanticError("Id já declarado: " + token.getLexeme)
    } else {
      val tabSim = listTabSim(na)

      desloc += 1

      val proc = new ID_Procedimento(token.getLexeme, na, desloc, 0, null, token.getPosition)
      tabSim.put(proc.nome, proc)
      posid.push(proc)
      npf = 0
      incNa()
    }
    faltaReturn = false
  }

  def act08(token: Token) {
    if (jaDeclarado(token)) {
      throw new SemanticError("Id já declarado: " + token.getLexeme, token.getPosition)
    } else {
      val tabSim = listTabSim(na)

      desloc += 1

      val func = new ID_Funcao(token.getLexeme, na, desloc, 0, null, null, token.getPosition)
      tabSim.put(func.nome, func)
      posid.push(func)
      npf = 0
      incNa()

      faltaReturn = true
      nomeFunc = func.nome
    }
  }

  def act09(token: Token) {
    val id = posid.head
    val tabSim = pegaTabSim(id.absNome)
    val funcOrProc = tabSim.get(id.absNome)
    try {
      val func = funcOrProc.get.asInstanceOf[ID_Funcao]
      val newFunc = new ID_Funcao(func.nome, func.nivel, func.end_prim_instr, npf,
        func.list_params, null, token.getPosition)
      tabSim.put(newFunc.nome, newFunc)
    } catch {
      case ex: ClassCastException => {
        val proc = funcOrProc.get.asInstanceOf[ID_Procedimento]
        val newProc = new ID_Procedimento(proc.nome, proc.nivel, proc.end_prim_instr, npf, proc.list_params, token.getPosition)
        tabSim.put(newProc.nome, newProc)
      }
    }

    if (id.isInstanceOf[ID_Procedimento])
      posid.pop
  }

  def act10(token: Token) {
    val id =  posid.pop
    val tabSim = pegaTabSim(id.absNome)
    val func = tabSim.get(id.absNome).get.asInstanceOf[ID_Funcao]
    val newFunc = new ID_Funcao(func.nome, func.nivel, func.end_prim_instr,
      func.num_parms, func.list_params, tipoAtual, token.getPosition)
    tabSim.put(newFunc.nome, newFunc)
  }

  def act11(token: Token) {
    if (faltaReturn) {
      throw new SemanticError("Funcao deve ser atribuida em seu escopo, ao menos uma vez.", token.getPosition)
    }

    listTabSim remove na
    na = na - 1
    contextoEXPR = null
  }

  def act12(token: Token) {
    contextoLID = "par-formal"
    lids = new ArrayBuffer[ID_Abstract]()
  }

  def act13(token: Token) {
    //marca pos fim
  }

  def act14(token: Token) {
    val lidsPar = new ArrayBuffer[ID_Parametro]()
    lids map { id =>
      val tabSim = pegaTabSim(id.absNome)
      val oldId = tabSim.get(id.absNome).get.asInstanceOf[ID_Parametro]
      val newId = new ID_Parametro(oldId.nome, oldId.nivel, 0, mpp, tipoAtual, token.getPosition)
      tabSim.put(newId.nome, newId)
      lidsPar += newId
    }

    val id = posid.head
    val tabSim = pegaTabSim(id.absNome)

    val procOrFunc = tabSim.get(id.absNome).get
      if (procOrFunc.isInstanceOf[ID_Procedimento]) {
      val oldProc = procOrFunc.asInstanceOf[ID_Procedimento]

      val oldParams = oldProc.list_params
      val newParams = lidsPar.toArray

      if (oldParams == null) {
        val newProc = new ID_Procedimento(oldProc.nome, oldProc.nivel, oldProc.end_prim_instr, oldProc.num_parms, newParams, token.getPosition)
        tabSim.put(newProc.nome, newProc)
      } else {
        val newProc = new ID_Procedimento(oldProc.nome, oldProc.nivel, oldProc.end_prim_instr, oldProc.num_parms, (oldParams ++ newParams), token.getPosition)
        tabSim.put(newProc.nome, newProc)
      }
    } else {
        val oldFunc = procOrFunc.asInstanceOf[ID_Funcao]

        val oldParams = oldFunc.list_params
        val newParams = lidsPar.toArray

        if (oldParams == null) {
          val newFunc = new ID_Funcao(oldFunc.nome, oldFunc.nivel, oldFunc.end_prim_instr, oldFunc.num_parms,
            newParams, oldFunc.tipo_resultado, token.getPosition)
          tabSim.put(newFunc.nome, newFunc)
        } else {
          val newFunc = new ID_Funcao(oldFunc.nome, oldFunc.nivel, oldFunc.end_prim_instr, oldFunc.num_parms,
            (oldParams ++ newParams), oldFunc.tipo_resultado, token.getPosition)
          tabSim.put(newFunc.nome, newFunc)
        }
    }
  }

  def act15(token: Token) {
    mpp = "referencia"
  }

  def act16(token: Token) {
    mpp = "valor"
  }

  def act17(token: Token) {
    verificaNomePrograma(token)

    if (contextoLID == "decl") {
      if (listTabSim(na).contains(token.getLexeme)) {
        throw new SemanticError("Id já declarado: " + token.getLexeme, token.getPosition)
      } else {
        val tabSim = listTabSim(na)
        tabSim.put(token.getLexeme, new ID_Variavel(token.getLexeme, na, null, null, token.getPosition))
        lids += new ID_Variavel(token.getLexeme, na, null, null, token.getPosition)
      }
    } else if (contextoLID == "par-formal") {
      if (listTabSim(na).contains(token.getLexeme)) {
        throw new SemanticError("Id já declarado: " + token.getLexeme, token.getPosition)
      } else {
        npf = npf + 1
        val id = posid.head
        val tabSim = pegaTabSim(id.absNome)
        val funcOrProc = tabSim.get(id.absNome).get
        if (funcOrProc.isInstanceOf[ID_Procedimento]) {
          val proc = funcOrProc.asInstanceOf[ID_Procedimento];
          val newProc = new ID_Procedimento(proc.nome, proc.nivel, proc.end_prim_instr, npf, proc.list_params, proc.posicao)
          tabSim.put(newProc.nome, newProc)
        } else if (funcOrProc.isInstanceOf[ID_Funcao]) {
          val func = funcOrProc.asInstanceOf[ID_Funcao];
          val newFunc = new ID_Funcao(func.nome, func.nivel, func.end_prim_instr, npf, func.list_params, func.tipo_resultado, func.posicao)
          tabSim.put(newFunc.nome, newFunc)
        }

        val tabSim2 = listTabSim(na)
        tabSim2.put(token.getLexeme, new ID_Parametro(token.getLexeme, na, 0, mpp, null, token.getPosition))
          lids += new ID_Parametro(token.getLexeme, na, 0, mpp, null, token.getPosition)
      }
    } else if (contextoLID == "leitura") {
      if (!listTabSim(na).contains(token.getLexeme)) {
        throw new SemanticError("Id não declarado: " + token.getLexeme, token.getPosition)
      } else {
        val tabSim = listTabSim(na)
        val id = tabSim.get(token.getLexeme)
        if (id.get.isInstanceOf[ID_Variavel]) {
          if (!tiposPreDefinidos.contains(id.get.asInstanceOf[ID_Variavel].tipo)) {
            throw new SemanticError("Tipo de Id Inválido, deveria ser pre-definido", token.getPosition)
          }
          //TODO gera código leitura
        } else if (id.get.isInstanceOf[ID_Parametro]) {
          if (!tiposPreDefinidos.contains(id.get.asInstanceOf[ID_Parametro].tipo)) {
            throw new SemanticError("Tipo de Id Inválido, deveria ser pre-definido", token.getPosition)
          }
          //TODO gera código leitura
        } else {
          throw new SemanticError("Apenas var podem ser lidas", token.getPosition)
        }
      }
    }
  }

  def act18(token: Token) {
    if (tipoConst != "inteiro") {
      throw new SemanticError("Esperava-se uma constante inteira", token.getPosition)
    } else if (valConst.toInt < 256) {
      tipoAtual = "cadeia"
    } else if (valConst.toInt >= 256) {
      throw new SemanticError("Tamanho da cadeia maior que o permitido! Até 256 caracteres", token.getPosition)
    }
  }

  def act19(token: Token) {
    if (tipoConst != "inteiro" && tipoConst != "caracter") {
      throw new SemanticError("Tipo do índice inválido", token.getPosition)
    } else {
      tipoConstVetorLimInf = tipoConst
      if(tipoConstVetorLimInf == "caracter")
        limInfVetor = getLiteralAsInt(token.getLexeme)
      else
        limInfVetor = Integer.parseInt(token.getLexeme)
    }
  }

  def act20(token: Token) {
    var intValConst :Int = 0
    if(tipoConst=="caracter")
      intValConst = getLiteralAsInt(token.getLexeme)
    else
      intValConst = Integer.parseInt(token.getLexeme)
    if (tipoConst != tipoConstVetorLimInf) {
      throw new SemanticError("Ctes do interv devem ser do mesmo tipo", token.getPosition)
    } else if (intValConst <= limInfVetor) {
      throw new SemanticError("Lim Sup Deve ser >  que L. Inf.", token.getPosition)
    } else {
      tipoConstVetorLimSup = tipoConst
      limSupVetor = intValConst
    }
    dimensao2 = new Dimensao(tipoIndiceDim2, limInfVetor, limSupVetor)//TODO:tipoindiceDim2 esta null
  }

  def act21(token: Token) {
    tipoElementos = tipoAtual//FIXME:salvar o tipoAtual no tipo do tabsim
    tipoAtual = "vetor"
    if (numDim == 2)
      tipoIndiceDim2 = tipoConst//FIXME:verificar se esta ok, dimensao 2 com tipoIndice
    vetor_temp = new Vetor(numDim, tipoElementos, dimensao1, dimensao2)
  }

  def act22(token: Token) {
    dimensao1 = new Dimensao(tipoConstVetorLimSup, limInfVetor, limSupVetor)
    numDim = 2
  }


  def act23(token: Token) {
    dimensao1 = new Dimensao(tipoConstVetorLimSup, limInfVetor, limSupVetor)
    numDim = 1
  }

  def act24(token: Token) {
    val id = pegaTabSim(token.getLexeme).get(token.getLexeme)
    if (!jaDeclarado(token)) {
      throw new SemanticError("Id não declarado: " + token.getLexeme, token.getPosition)
    } else if (!id.get.isInstanceOf[ID_Constante]){
      throw new SemanticError("Esperava-se um id de constante", token.getPosition)
    } else {
      valConst = id.get.asInstanceOf[ID_Constante].valor.asInstanceOf[String]
      tipoConst = id.get.asInstanceOf[ID_Constante].tipo
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
    verificaNomePrograma(token)

    if (!jaDeclarado(token)) {
      throw new SemanticError(token.getLexeme + " nao declarado", token.getPosition)
    }

    posid.push(pegaTabSim(token.getLexeme).get(token.getLexeme).get)

    val id = posid.head
    if (id.isInstanceOf[ID_Funcao]) {
      npf = id.asInstanceOf[ID_Funcao].num_parms
      if (nomeFunc == token.getLexeme) {
        nomeFunc = null
        faltaReturn = false
      }

    } else if (id.isInstanceOf[ID_Procedimento]) {
      npf = id.asInstanceOf[ID_Procedimento].num_parms
    }
    mpp = "referencia"
  }

  def act30(token: Token) {
    if (tipoExpr != "booleano" && tipoExpr != "inteiro")
       throw new SemanticError("Tipo inválido da expressão", token.getPosition)
  }

  def act31(token: Token) {
    contextoLID = "leitura"
  }

  def act32(token: Token) {
    contextoEXPR = "impressao"
    val tiposParaImp = Array("inteiro", "real", "caracter", "cadeia", "literal")
    if (!tiposParaImp.contains(tipoExpr))
      throw new SemanticError("Tipo inválido para impressão", token.getPosition)
  }

  def act33(token: Token){
    val id = pegaTabSim(token.getLexeme).get(token.getLexeme).get
    if(id.isInstanceOf[ID_Variavel]) {
      if(id.asInstanceOf[ID_Variavel].tipo == "vetor") {
        throw new SemanticError(id.absNome + " deveria ser indexado", token.getPosition)
      } else {
        tipoLadoEsq = id.asInstanceOf[ID_Variavel].tipo
      }
    } else if (id.isInstanceOf[ID_Parametro]) {
      if(id.asInstanceOf[ID_Parametro].tipo == "vetor") {
        throw new SemanticError(id.absNome + " deveria ser indexado", token.getPosition)
      } else {
        tipoLadoEsq = id.asInstanceOf[ID_Parametro].tipo
      }
    } else if(id.isInstanceOf[ID_Funcao]) { //TODO:fragilidade, possivel declarar nome da funcao fora da funcao
      if(na >= id.asInstanceOf[ID_Funcao].nivel + 1) {
        tipoLadoEsq = id.asInstanceOf[ID_Funcao].tipo_resultado
      } else {
        throw new SemanticError("fora do escopo da funcao", token.getPosition)
      }
    } else {
      throw new SemanticError(id.absNome + " deveria ser var/par/funcao", token.getPosition)
    }
  }

  def act34(token: Token) {
    if(tipoLadoEsq=="real") {
      if(tipoExpr!="real" && tipoExpr!="inteiro") {
        throw new SemanticError("Tipos Incomp. Tipo Lado Esq = real e TipoExpr = " + tipoExpr, token.getPosition)
      }
    } else if(tipoLadoEsq=="inteiro") {
      if(tipoExpr!="inteiro") {
        throw new SemanticError("Tipos Incomp. Tipo Lado Esq = inteiro e TipoExpr = " + tipoExpr, token.getPosition)
      }
    } else if(tipoLadoEsq=="cadeia") {
      if(tipoExpr!="cadeia" && tipoExpr!="literal" && tipoExpr != "caracter") {
        throw new SemanticError("Tipos Incomp. Tipo Lado Esq = cadeia e TipoExpr = " + tipoExpr, token.getPosition)
      }
    } else if(tipoLadoEsq=="booleano") {
      if(tipoExpr!="booleano") {
        throw new SemanticError("Tipos Incomp. Tipo Lado Esq = booleano e TipoExpr = " + tipoExpr, token.getPosition)
      }
    } else if(tipoLadoEsq == "vetor") {
      if(tipoExpr!="vetor") {
        throw new SemanticError("Tipos Incomp. Tipo Lado Esq = vetor e TipoExpr = " + tipoExpr, token.getPosition)
      }
    }
  }

  def act35(token: Token) {
    val id = posid.head
    if(!id.isInstanceOf[ID_Variavel])
      throw new SemanticError("esperava-se uma variavel", token.getPosition)
    else if(id.asInstanceOf[ID_Variavel].tipo == "vetor")
      tipoVarIndexada = "vetor"
    else if (id.asInstanceOf[ID_Variavel].tipo == "cadeia")
      tipoVarIndexada = "cadeia"
    else
      throw new SemanticError("apenas vetores e cadeias podem ser indexados", token.getPosition)
  }

  def act36(token: Token) {
    numIndices = 1
    if (tipoVarIndexada == "vetor") {
      val id = posid.head
      if (id.isInstanceOf[ID_Variavel]) {
        val tipoIndiceDim1 = id.asInstanceOf[ID_Variavel].subCategoria.asInstanceOf[Vetor].dim1.tipoIndice// TODO: quando acessa V[i] dah pau
        if (tipoExpr != tipoIndiceDim1)//FIXME: declarando vetores com indices de tipo diferente dah pau
          throw new SemanticError("Tipo indice inválido", token.getPosition)
        else {
          tipoElementos = id.asInstanceOf[ID_Variavel].subCategoria.asInstanceOf[Vetor].tipoElem
          tipoLadoEsq = tipoElementos

        }
        posid.pop
      }
    } else {//cadeia
      if (tipoExpr != "inteiro")
        throw new SemanticError("Indice deveria ser inteiro", token.getPosition)
      else
        tipoLadoEsq = "caracter"
    }
  }

  def act37(token: Token) {
    if(numIndices == 2) {
      if(tipoVarIndexada == "cadeia") {
        throw new SemanticError("Cadeia so pode ter 1 indice", token.getPosition)
      } else if(numDim != 2) {
        throw new SemanticError("Vetor eh unidimensional", token.getPosition)
      } else if(tipoExpr != dimensao2.tipoIndice) {
        throw new SemanticError("Tipo de indice invalido", token.getPosition)
      } else {
        tipoLadoEsq = tipoElementos
      }
    } else if(numDim == 2) {
      throw new SemanticError("Vetor eh bi-dimensional", token.getPosition)
    }
  }

  def act38(token: Token) {
    val id = posid.head
    if(!(id.isInstanceOf[ID_Procedimento] || id.isInstanceOf[ID_Funcao]))
      throw new SemanticError(posid.head.absNome + " deveria ser uma procedure", token.getPosition)
    contextoEXPR = "par-atual"
  }

  def act39(token: Token) {
    npa = 1
    contextoEXPR = "par-atual"
    val id = posid.pop
    if (!id.isInstanceOf[ID_Valor]) {//se nao for valor fixo
      val funcOrProc = pegaTabSim(id.absNome).get(id.absNome).get
      if(funcOrProc.isInstanceOf[ID_Funcao]) {
        val par = funcOrProc.asInstanceOf[ID_Funcao].list_params(npa-1)
        if(par == null)
          throw new SemanticError("Parametro nao encontrado", token.getPosition)
        else {
          if(par.mecanismo_passagem != mpp) {
            throw new SemanticError("Tipo de passagem de parametro incompativel", token.getPosition)
          }
          if (par.tipo == "real") {
            if (!(tipoExpr == "real" ||  tipoExpr == "inteiro"))
              throw new SemanticError("Tipo do parametro incompativel com tipo do argumento", token.getPosition)
          } else if (par.tipo == "inteiro") {
            if (tipoExpr != "inteiro")
              throw new SemanticError("Tipo do parametro incompativel com tipo do argumento", token.getPosition)
          } else if (par.tipo == "cadeia") {
            if (!(tipoExpr == "cadeia" ||  tipoExpr == "caracter"))
              throw new SemanticError("Tipo do parametro incompativel com tipo do argumento", token.getPosition)
          } else if (par.tipo == "caracter") {
            if (tipoExpr != "caracter")
              throw new SemanticError("Tipo do parametro incompativel com tipo do argumento", token.getPosition)
          }

        }
      } else if (funcOrProc.isInstanceOf[ID_Procedimento]) {
        if(funcOrProc.asInstanceOf[ID_Procedimento].list_params == null)
          throw new SemanticError("Parametro nao encontrado", token.getPosition)
        else {
          val par = funcOrProc.asInstanceOf[ID_Procedimento].list_params(npa-1)
          if(mpp != par.mecanismo_passagem)
            throw new SemanticError("Tipo de passagem de parametro incompativel", token.getPosition)
          if (par.tipo == "real") {
            if (!(tipoExpr == "real" ||  tipoExpr == "inteiro"))
              throw new SemanticError("Tipo do parametro incompativel com tipo do argumento", token.getPosition)
          } else if (par.tipo == "inteiro") {
            if (tipoExpr != "inteiro")
              throw new SemanticError("Tipo do parametro incompativel com tipo do argumento", token.getPosition)
          } else if (par.tipo == "cadeia") {
            if (!(tipoExpr == "cadeia" ||  tipoExpr == "caracter"))
              throw new SemanticError("Tipo do parametro incompativel com tipo do argumento", token.getPosition)
          } else if (par.tipo == "caracter") {
            if (tipoExpr != "caracter")
              throw new SemanticError("Tipo do parametro incompativel com tipo do argumento", token.getPosition)
          }
        }
      }
    }
  }

  def act40(token: Token) {
    if(npa!=npf)
      throw new SemanticError("Erro na quantidade de parametros", token.getPosition)
    contextoEXPR = null
    posid.pop
  }

  def act41(token: Token) {
    val id = posid.pop
    if(!id.isInstanceOf[ID_Procedimento])
      throw new SemanticError(id.absNome + " deveria ser uma procedure", token.getPosition)
    else {
      if(npf!=0) {
        throw new SemanticError("Erro na quantidade de parametros", token.getPosition)
      }
    }
  }

  def act42(token: Token) {
    numIndices = 2
  }

  def act43(token: Token) {
    if(contextoEXPR == "par-atual") {
      npa += 1
      val id = posid.pop
      if (!id.isInstanceOf[ID_Valor]) {
        val funcOrProc = pegaTabSim(id.absNome).get(id.absNome).get
        if(funcOrProc.isInstanceOf[ID_Funcao]) {
          if (npa != funcOrProc.asInstanceOf[ID_Funcao].num_parms) {
            throw new SemanticError("Num de param diferentes, num param atuais = " + npa +
              " - num params da func/proc = " + funcOrProc.asInstanceOf[ID_Funcao].num_parms, token.getPosition)
          }

          val par = funcOrProc.asInstanceOf[ID_Funcao].list_params(npa-1)
          if(par == null)
            throw new SemanticError("Parametro nao encontrado", token.getPosition)
          else {
           if(mpp != par.mecanismo_passagem)
             throw new SemanticError("Tipo de passagem de parametro incompativel", token.getPosition)
            if (par.tipo == "real") {
              if (!(tipoExpr == "real" ||  tipoExpr == "inteiro"))
                throw new SemanticError("Tipo do parametro incompativel com tipo do argumento", token.getPosition)
            } else if (par.tipo == "inteiro") {
              if (tipoExpr != "inteiro")
                throw new SemanticError("Tipo do parametro incompativel com tipo do argumento", token.getPosition)
            } else if (par.tipo == "cadeia") {
              if (!(tipoExpr == "cadeia" ||  tipoExpr == "caracter"))
                throw new SemanticError("Tipo do parametro incompativel com tipo do argumento", token.getPosition)
            } else if (par.tipo == "caracter") {
              if (tipoExpr != "caracter")
                throw new SemanticError("Tipo do parametro incompativel com tipo do argumento", token.getPosition)
            }
          }
        } else if(funcOrProc.isInstanceOf[ID_Procedimento]) {
          if (npa != funcOrProc.asInstanceOf[ID_Procedimento].num_parms) {
            throw new SemanticError("Num de param diferentes, num param atuais = " + npa +
              " - num params da func/proc = " + funcOrProc.asInstanceOf[ID_Procedimento].num_parms, token.getPosition)
          }

          val par = funcOrProc.asInstanceOf[ID_Procedimento].list_params(npa-1)
          if(par == null)
            throw new SemanticError("Parametro nao encontrado", token.getPosition)
          else {
            if(mpp != par.mecanismo_passagem)
              throw new SemanticError("Tipo de passagem de parametro incompativel", token.getPosition)
            if (par.tipo == "real") {
              if (!(tipoExpr == "real" ||  tipoExpr == "inteiro"))
                throw new SemanticError("Tipo do parametro incompativel com tipo do argumento", token.getPosition)
            } else if (par.tipo == "inteiro") {
              if (tipoExpr != "inteiro")
                throw new SemanticError("Tipo do parametro incompativel com tipo do argumento", token.getPosition)
            } else if (par.tipo == "cadeia") {
              if (!(tipoExpr == "cadeia" ||  tipoExpr == "caracter"))
                throw new SemanticError("Tipo do parametro incompativel com tipo do argumento", token.getPosition)
            } else if (par.tipo == "caracter") {
              if (tipoExpr != "caracter")
                throw new SemanticError("Tipo do parametro incompativel com tipo do argumento", token.getPosition)
            }
          }
        }
      }
    }
    if(contextoEXPR == "impressao") {
      if(tipoExpr != "inteiro" && tipoExpr != "real" && tipoExpr != "caracter" && tipoExpr != "cadeia")
        throw new SemanticError("Tipo invalido para impressao", token.getPosition)
      else
        System.out.println("token: "+token.getLexeme+"\n cteExplicita :"+valConst)//FIXME: printa expressao
    }
  }

  def act44(token: Token) {
    tipoExpr = tipoExpSimples
  }

  def act45(token: Token) {
    if(tipoExpr == "real" || tipoExpr == "inteiro") {
      if(tipoExpSimples != "real" && tipoExpSimples != "inteiro")
        throw new SemanticError("Operandos incompativeis", token.getPosition)
      else {
        tipoExpr = "booleano"
      }
    }
    else if(opRel == "<>" || opRel == "=") {
      if(tipoExpr == "cadeia" || tipoExpr == "literal") {
        if(tipoExpSimples != "cadeia" && tipoExpSimples != "literal")
          throw new SemanticError("Operandos incompativeis", token.getPosition)
      } else {
        tipoExpr = "booleano"
      }
    }
  }

  def act46(token: Token) {//ate act51 a implementacao eh a mesma!
    opRel = token.getLexeme
  }

  def act52(token: Token) {
    tipoExpSimples = tipoTermo
  }

  def act53(token: Token) {
    if(operador=="+" || operador == "-") {
      if(!(tipoExpSimples=="inteiro" || tipoExpSimples=="real"))
        throw new SemanticError("Operador e operando incompativeis", token.getPosition)
    } else {
      if(operador == "ou") {
        if(tipoExpSimples != "booleano")
          throw new SemanticError("Operador e operando incomaptiveis", token.getPosition)
      }
    }
  }

  def act54(token: Token) {
    if(tipoTermo == "real" || tipoTermo == "inteiro") {
      if(tipoExpSimples != "real" && tipoExpSimples != "inteiro")
        throw new SemanticError("Operandos incomp., o Termo = real e TipoExpSimple = " + tipoExpSimples, token.getPosition)
      else {
        if (operador == "/")
          tipoResultadoOperacao = "real"
        else if(tipoTermo == "real" || tipoExpSimples == "real") {
          tipoResultadoOperacao = "real"
        }
        else {
          tipoResultadoOperacao = "inteiro"
        }

        tipoExpSimples = tipoResultadoOperacao

        val id = posid.pop
        if (id.isInstanceOf[ID_Variavel]) {
          if (id.asInstanceOf[ID_Variavel].tipo == "inteiro") {
            if (tipoTermo != "inteiro" || tipoFator != "inteiro" || forcaReal) {
              forcaReal = false
              throw new SemanticError("Operandos incomp., o Termo = " + tipoTermo + " e TipoExpSimple = " + tipoExpSimples +
                ", Tipo POSID = " + id.asInstanceOf[ID_Variavel].tipo, token.getPosition)
            }
          }
        } else if (id.isInstanceOf[ID_Funcao]) {
          if (id.asInstanceOf[ID_Funcao].tipo_resultado == "inteiro") {
            if (tipoTermo != "inteiro" || tipoFator != "inteiro" || forcaReal) {
              forcaReal = false
              throw new SemanticError("Operandos incomp., o Termo = " + tipoTermo + " e TipoExpSimple = " + tipoExpSimples +
                ", Tipo POSID = " + id.asInstanceOf[ID_Funcao].tipo_resultado, token.getPosition)
            }
          }
        }
      }
    } else if(tipoTermo == "booleano") {
      if(tipoExpSimples != "booleano")
        throw new SemanticError("Operandos incomp., o Termo = booleano e TipoExpSimple = " + tipoExpSimples, token.getPosition)
      else {
        tipoResultadoOperacao = "booleano"
        tipoExpSimples = tipoResultadoOperacao
      }
    }
  }

  def act55(token: Token) {
    operador = token.getLexeme
  }

  def act58(token: Token) {
    tipoTermo = tipoFator
  }

  def act59(token: Token) {
    if (operador == "/" || operador == "*") {
      if (tipoTermo != "real" && tipoTermo != "inteiro") {
        throw new SemanticError("Operador e operando incompativeis, '*' ou '/' verifique", token.getPosition)
      }
    } else if (operador == "e") {
      if (tipoTermo != "booleano")
        throw new SemanticError("Operador e operando incompativeis, 'e' aceita booleano", token.getPosition)
    }

    if (operador == "/")
      forcaReal = true
  }

  def act60(token: Token) {
    if(tipoTermo == "real" || tipoTermo == "inteiro") {
      if(tipoFator != "real" && tipoFator != "inteiro")
        throw new SemanticError("Operandos incompativeis, tipo fator e termo", token.getPosition)
      else {
        if (operador == "/")
          tipoResultadoOperacao = "real"
        else if(tipoTermo == "real" || tipoExpSimples == "real") {
          tipoResultadoOperacao = "real"
        }
        else {
          tipoResultadoOperacao = "inteiro"
        }


        val id = posid.pop
        if (id.isInstanceOf[ID_Variavel]) {
          if (id.asInstanceOf[ID_Variavel].tipo == "inteiro") {
            if (tipoTermo != "inteiro" || tipoFator != "inteiro" || forcaReal) {
              forcaReal = false
              throw new SemanticError("Variavel " + id.asInstanceOf[ID_Variavel].nome + " é inteiro e não pode " +
                "receber resultado real", token.getPosition)
            }
          }
        } else if (id.isInstanceOf[ID_Funcao]) {
          if (id.asInstanceOf[ID_Funcao].tipo_resultado == "inteiro") {
            if (tipoTermo != "inteiro" || tipoFator != "inteiro" || forcaReal) {
              forcaReal = false
              throw new SemanticError("Funcao " + id.asInstanceOf[ID_Funcao].nome + " é inteiro e não pode " +
                "receber resultado real", token.getPosition)
            }
          }
        }
      }
    } else if(tipoTermo == "booleano") {
      if(tipoFator != "booleano")
        throw new SemanticError("Operandos incompativeis, tipo fator e termo", token.getPosition)
      else {
        val id = posid.pop
        if (id.isInstanceOf[ID_Variavel]) {
          if (id.asInstanceOf[ID_Variavel].tipo != "booleano")
            throw new SemanticError("Operador e operando incompativeis, '*' ou '/' verifique1", token.getPosition)
        } else if (id.isInstanceOf[ID_Funcao]) {
          if (id.asInstanceOf[ID_Funcao].tipo_resultado == "booleano") {
            throw new SemanticError("Operador e operando incompativeis, '*' ou '/' verifique2", token.getPosition)
          }
        }
      }
    }
  }

  def act64(token: Token) {
    operador = token.getLexeme
  }

  def act67(token: Token) {
    if(opNega)
      throw new SemanticError("Operadores 'não' consecutivos não é permitido", token.getPosition)
    else
      opNega = true
  }

  def act68(token: Token) {
    if(!tipoFator.equalsIgnoreCase("booleano"))
      throw new SemanticError("Op. 'nao' exige operando booleano", token.getPosition)
  }

  def act69(token: Token) {
    if(opUnario)
      throw new SemanticError("Ops. 'unario' consecutivos", token.getPosition)
    else
      opUnario = true
  }

  def act70(token: Token) {
    if(tipoFator != "inteiro" && tipoFator != "real")
      throw new SemanticError("Op. unario '+/-' exige operando numerico", token.getPosition)
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
    mpp = "valor"
  }

  def act75(token: Token) {
    contextoEXPR = "par-atual"
    val id = pegaTabSim(posid.head.absNome).get(posid.head.absNome).get
    if (!id.isInstanceOf[ID_Funcao] && !id.isInstanceOf[ID_Procedimento])
      throw new SemanticError("Id deveria ser de uma função ou procedimento: " + id.absNome, posid.head.pos)
  }

  def act76(token: Token) {
    val id = posid.pop
    if (id.isInstanceOf[ID_Funcao]) {
      if(npa == npf)
        tipoVar = id.asInstanceOf[ID_Funcao].tipo_resultado
      else
        throw new SemanticError("Erro na quantidade de parametros, num param atuais = " + npa +
          " num parm form = " + npf, token.getPosition)
    }
    contextoEXPR = null
  }

  def act77(token: Token) {
    numIndices = 1
    if(tipoVarIndexada == "vetor") {
      val id = posid.pop
      if (id.isInstanceOf[ID_Variavel]){
        val tipoIndiceDim1 = id.asInstanceOf[ID_Variavel].subCategoria.asInstanceOf[Vetor].dim1.tipoIndice
        if(tipoExpr != tipoIndiceDim1) {//TODO:abrir tipo dimensao1 da tabsim
          throw new SemanticError("Tipo do indice invalido", token.getPosition)
        } else {
          tipoElementos = id.asInstanceOf[ID_Variavel].subCategoria.asInstanceOf[Vetor].tipoElem
          tipoVar = tipoElementos
        }
      }
    } else if(tipoExpr != "inteiro") {
      throw new SemanticError("Indice deveria ser inteiro", token.getPosition)
    } else {
      tipoVar = "caracter"
    }
  }

  def act78(token: Token) {
    if(numIndices==2) {
      if(tipoVarIndexada == "cadeia") {
        throw new SemanticError("Cadeia so pode ter 1 indice", token.getPosition)
      } else if(numDim!=2) {
        throw new SemanticError("Vetor eh uni-dimensional", token.getPosition)
      } else if(tipoExpr!=dimensao2.tipoIndice) {
        throw new SemanticError("Tipo do indice invalido", token.getPosition)
      } else {
        tipoVar = tipoElementos
      }
    } else if(numDim == 2) {
      throw new SemanticError("Vetor eh bi-dimensional", token.getPosition)
    }
  }

  def act79(token: Token) {
    val fator = pegaTabSim(token.getLexeme).get(token.getLexeme).get
    if (fator.isInstanceOf[ID_Variavel]) {
      if (fator.asInstanceOf[ID_Variavel].tipo == "vetor")
        throw new SemanticError("Vetor deve ser indexado", token.getPosition)
      else
        tipoVar = fator.asInstanceOf[ID_Variavel].tipo
    } else if (fator.isInstanceOf[ID_Parametro]) {
      if (fator.asInstanceOf[ID_Parametro].tipo == "vetor")
        throw new SemanticError("Vetor deve ser indexado", token.getPosition)
      else
        tipoVar = fator.asInstanceOf[ID_Parametro].tipo
    } else if (fator.isInstanceOf[ID_Funcao]) {
      if (npf != 0)
        throw new SemanticError("Erro na quantidade de parametros", token.getPosition)
      else
        tipoVar = fator.asInstanceOf[ID_Funcao].tipo_resultado//TODO Gera codigo
    } else if (fator.isInstanceOf[ID_Constante]) {
      tipoVar = fator.asInstanceOf[ID_Constante].tipo
    } else {
      throw new SemanticError("Esperava-se var, id-funcao ou constante", token.getPosition)
    }
    posid.pop
  }

  def act80(token: Token) {
    tipoConst = "inteiro"
    valConst = token.getLexeme
    posid.push(new ID_Valor(valConst))
  }

  def act81(token: Token) {
    tipoConst = "real"
    valConst = token.getLexeme
    posid.push(new ID_Valor(valConst))
  }

  def act82(token: Token) {
    tipoConst = "booleano"
    valConst = token.getLexeme
    posid.push(new ID_Valor(valConst))
  }

  def act83(token: Token) {
    tipoConst = "booleano"
    valConst = token.getLexeme
    posid.push(new ID_Valor(valConst))
  }

  def act84(token: Token) {
    valConst = token.getLexeme
    if (valConst.length > 3)//'a'
      tipoConst = "literal"
    else
      tipoConst = "caracter"
  }
  posid.push(new ID_Valor(valConst))
}


