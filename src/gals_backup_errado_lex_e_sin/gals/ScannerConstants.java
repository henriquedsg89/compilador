package gals_backup_errado_lex_e_sin.gals;

public interface ScannerConstants
{

    int[] TOKEN_STATE = { 0,  0,  0, -1, -1, -1, 39, 40, 37, 35, 45, 36, 43, 38,  3, 42, 41, 52, 44, 53, -1,  2, 48, 49, 46, 47,  4, -1,  5,  4, -1,  5,  4, 50, -1,  0,  4,  3,  4, 51, 55, 54, 56,  2,  2,  2,  2, -1, -1, -1, -1, -1, -1, -1,  3,  2,  2,  2,  2, -1,  4,  5, -1,  4, -1,  4,  0,  3,  2,  2 };

    int[] SPECIAL_CASES_INDEXES =
        { 0, 0, 0, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29 };

    String[] SPECIAL_CASES_KEYS =
        {  "booleano", "cadeia", "caracter", "const", "de", "e", "enquanto", "entao", "escreva", "faca", "falso", "fim", "funcao", "inicio", "inteiro", "leia", "nao", "ou", "proc", "procedimento", "programa", "real", "ref", "se", "senao", "val", "var", "verdadeiro", "vetor" };

    int[] SPECIAL_CASES_VALUES =
        {  14, 9, 8, 31, 26, 22, 30, 17, 20, 27, 24, 12, 15, 11, 13, 19, 23, 21, 32, 10, 6, 28, 33, 16, 18, 34, 7, 25, 29 };

    String[] SCANNER_ERROR =
    {
        "Caractere não esperado",
        "",
        "",
        "Erro identificando num_real",
        "Erro identificando num_real ou literal",
        "Erro identificando num_real ou literal",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "Erro identificando id ou num_real",
        "",
        "",
        "",
        "",
        "",
        "",
        "Erro identificando literal",
        "",
        "",
        "Erro identificando literal",
        "",
        "",
        "",
        "Erro identificando <ignorar>",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "Erro identificando num_real",
        "Erro identificando literal",
        "Erro identificando num_real ou literal",
        "Erro identificando literal",
        "Erro identificando num_real ou literal",
        "Erro identificando <ignorar>",
        "Erro identificando num_int",
        "",
        "",
        "",
        "",
        "",
        "Erro identificando num_real",
        "",
        "",
        "Erro identificando num_real ou literal",
        "",
        "Erro identificando num_real ou literal",
        "",
        "",
        "",
        "",
        ""
    };

}
