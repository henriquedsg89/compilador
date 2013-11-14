package gesser.gals.grammar.parser;

public interface Constants extends ScannerConstants, ParserConstants
{
    int EPSILON  = 0;
    int DOLLAR   = 1;

    int t_TOKEN_2 = 2; //"*"
    int t_TOKEN_3 = 3; //"+"
    int t_TOKEN_4 = 4; //"?"
    int t_TOKEN_5 = 5; //"("
    int t_TOKEN_6 = 6; //")"
    int t_TOKEN_7 = 7; //"|"
    int t_SEMICOLON = 8; //";"
    int t_DERIVES = 9; //"::="
    int t_T = 10;
    int t_NT = 11;
    int t_SA = 12;
    int t_EPSILON = 13;

}
