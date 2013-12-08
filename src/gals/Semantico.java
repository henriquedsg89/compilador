package gals;

import controller.SemanticoScala;

public class Semantico implements Constants
{
    private SemanticoScala semScala = new SemanticoScala();

    public void executeAction(int action, Token token)	throws SemanticError
    {
        semScala.executeAction(action, token);
    }	
}
