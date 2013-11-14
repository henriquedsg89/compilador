package gesser.gals.ebnf.parser;

public interface ParserConstants
{
    public static final int START_SYMBOL = 16;

    public static final int FIRST_NON_TERMINAL    = 16;
    public static final int FIRST_SEMANTIC_ACTION = 27;

    public static final int[][] PARSER_TABLE =
    {
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, -1, -1, -1, -1 },
        {  2, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  3, -1, -1, -1, -1 },
        { -1, -1, -1, -1,  4, -1, -1, -1, -1,  4,  4,  4,  4, -1, -1 },
        { -1, -1, -1, -1, -1,  6,  5,  6, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1,  7, -1, -1, -1, -1,  7,  7,  7,  8, -1, -1 },
        { -1, -1, -1, -1,  9, 10, 10, 10, -1,  9,  9,  9, -1, -1, -1 },
        { -1, -1, -1, -1, 12, -1, -1, -1, -1, 11, 11, 11, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, 17, 18, 19, -1, -1, -1 },
        { -1, 14, 15, 16, 13, 13, 13, 13, -1, 13, 13, 13, -1, -1, -1 },
        { -1, -1, -1, -1, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
    };

    public static final int[][] PRODUCTIONS = 
    {
        { 18, 17 },
        { 16 },
        {  0 },
        { 11, 28,  9, 19,  8, 29 },
        { 30, 21, 31, 20 },
        {  7, 30, 21, 31, 20 },
        {  0 },
        { 23, 22 },
        { 13 },
        { 23, 22 },
        {  0 },
        { 24, 32, 25 },
        { 26, 33, 25 },
        { 34 },
        {  2, 35 },
        {  3, 36 },
        {  4, 37 },
        { 10 },
        { 11 },
        { 12 },
        {  5, 38, 19,  6, 39 }
    };

    public static final String[] PARSER_ERROR =
    {
        "",
        "Era esperado fim de programa",
        "Era esperado \"*\"",
        "Era esperado \"+\"",
        "Era esperado \"?\"",
        "Era esperado \"(\"",
        "Era esperado \")\"",
        "Era esperado \"|\"",
        "Era esperado \";\"",
        "Era esperado \"::=\"",
        "Era esperado T",
        "Era esperado NT",
        "Era esperado SA",
        "Era esperado EPSILON",
        "Era esperado WS",
        "Era esperado COMMENT",
        "A gram�tica deve conter ao menos uma produ��o", //"<G> inv�lido",
        "Gram�tica inv�lida", //"<G_> inv�lido",
        "Produ��o inv�lida",//"<P> inv�lido",
        "Era esperado um s�mbolo, a��o sem�ntica, ou grupo",//"<PLIST> inv�lido",
        "Era esperado ; ou |", //"<PLIST_REP> inv�lido",
        "Era esperado um s�mbolo, a��o sem�ntica, ou grupo", //"<RHS> inv�lido",
        "Era esperado um s�mbolo, a��o sem�ntica, ou grupo",//"<RHS_REP> inv�lido",
        "Item inv�lido",//"<ITEM> inv�lido",
        "S�mbolo/A��o Sem�ntica inv�lido",//"<S> inv�lido",
        "Era esperado o indicador de cardinalidade, ou outro s�mbolo, a��o sem�ntica, ou grupo",//"<CARD> inv�lido",
        "Grupo inv�lido"//"<X> inv�lido"
    };
}
