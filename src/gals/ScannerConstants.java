package gals;

public interface ScannerConstants
{

    int[] TOKEN_STATE = { 0,  0,  0, -1, -1, -1, 10, 11,  8,  6, 16,  7, 14,  9,  3, 13, 12, 23, 15, 24, -1,  2, 19, 20, 17, 18,  4, -1,  5,  4, -1,  5,  4, 21, -1,  0,  4,  3,  4, 22, 26, 25, 27,  2,  2, -1,  2, -1, -1, -1, -1, -1, -1, -1,  3, -1,  2,  2,  2,  2,  2, -1,  4,  5, -1,  4, -1,  4,  0,  3,  2,  2 };

    int[] SPECIAL_CASES_INDEXES =
        { 0, 0, 0, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26 };

    String[] SPECIAL_CASES_KEYS =
        {  "booleano", "cadeia", "caracter", "const", "de", "e", "enquanto", "entao", "escreva", "faca", "falso", "funcao", "inteiro", "leia", "nao", "ou", "proc", "programa", "real", "ref", "se", "senao", "val", "var", "verdadeiro", "vetor" };

    int[] SPECIAL_CASES_VALUES =
        {  39, 35, 40, 29, 41, 50, 44, 43, 47, 45, 52, 32, 37, 46, 51, 49, 31, 28, 38, 33, 42, 48, 34, 30, 53, 36 };

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
        "Erro identificando id",
        "",
        "Erro identificando num_real",
        "Erro identificando literal",
        "Erro identificando num_real ou literal",
        "Erro identificando literal",
        "Erro identificando num_real ou literal",
        "Erro identificando <ignorar>",
        "Erro identificando num_int",
        "",
        "Erro identificando id",
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
