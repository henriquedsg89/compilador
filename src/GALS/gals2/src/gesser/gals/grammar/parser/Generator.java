package gesser.gals.grammar.parser;

import gesser.gals.grammar.core.Symbol;
import gesser.gals.grammar.dom.Cardinality;
import gesser.gals.grammar.dom.ComplexItemDecl;
import gesser.gals.grammar.dom.GrammarDecl;
import gesser.gals.grammar.dom.ItemDecl;
import gesser.gals.grammar.dom.ItemList;
import gesser.gals.grammar.dom.ItemListDecl;
import gesser.gals.grammar.dom.ProductionDecl;
import gesser.gals.grammar.dom.SymbolItemDecl;
import gesser.gals.grammar.dom.tokens.NonTerminalToken;
import gesser.gals.grammar.dom.tokens.SemanticActionToken;
import gesser.gals.grammar.dom.tokens.TerminalToken;

import java.util.List;
import java.util.Stack;

public class Generator implements Constants
{
    private GrammarDecl grammar = new GrammarDecl();
    
	//private NonTerminalToken lhs = null;
	private Stack<ItemList> itemListStack = new Stack<ItemList>();
	private Stack<ComplexItemDecl> complexStack = new Stack<ComplexItemDecl>();
	private ItemDecl lastItem;
	
	public GrammarDecl getGrammar()
	{		
		return grammar;
	}
	
    public void executeAction(int action, Token token)
    {
    	switch (action)
		{
    		case 1:
    			action01(token);
    			break;
    		case 2:
    			action02(token);
    			break;
    		case 3:
    			action03(token);
    			break;
    		case 4:
    			action04(token);
    			break;
    		case 5:
    			action05(token);
    			break;
    		case 6:
    			action06(token);
    			break;
    		case 7:
    			action07(token);
    			break;
    		case 8:
    			action08(token);
    			break;
    		case 9:
    			action09(token);
    			break;
    		default:
    			System.out.println("Ação #"+action+", Token: "+token);
		}
    }
    
    private void action01(Token token)
    {
        NonTerminalToken lhs = new NonTerminalToken(Symbol.nonTerminal(token.getLexeme()), token.getPosition());
    	ProductionDecl p = new ProductionDecl(lhs);
    	
    	itemListStack.push(p);
    	
    	grammar.getProductions().add(p);
    }
    
    private void action02(Token token)
    {
        ComplexItemDecl c = new ComplexItemDecl(token.getPosition());
    	complexStack.push(c);
    	
    	itemListStack.push(c); 
    }
    
    private void action03(Token token)
    {
        ComplexItemDecl ci = complexStack.pop();
    	ci.setEnd(token.getPosition()+1);
    	
    	itemListStack.pop();
    	
    	createItem(ci);
    }
    
    private void action04(Token token)
    {
    	switch (token.getLexeme().charAt(0))
		{
    		case '?':
    			lastItem.setCardinality(Cardinality.ZERO_OR_ONE);
    			break;
    		case '+':
    			lastItem.setCardinality(Cardinality.ONE_OR_MORE);
    			break;
    		case '*':
    			lastItem.setCardinality(Cardinality.ZERO_OR_MORE);
    			break;
		}
    	
    }
    
    private void action05(Token token)
    {
    	itemListStack.peek().getItems().add(new ItemListDecl());
    }
        
    private void action06(Token token)
    {
    	SymbolItemDecl item = new SymbolItemDecl(new TerminalToken(Symbol.terminal(token.getLexeme()), token.getPosition()));
    	
    	createItem(item);
    }
    
    private void action07(Token token)
    {	    	
    	SymbolItemDecl item = new SymbolItemDecl(new NonTerminalToken(Symbol.nonTerminal(token.getLexeme()), token.getPosition()));
    	
    	createItem(item);
    }
    
    private void action08(Token token)
    {	    	
    	SymbolItemDecl item = new SymbolItemDecl(new SemanticActionToken(Symbol.semanticAction(token.getLexeme()), token.getPosition()));
    	
    	createItem(item);
    }
    
    private void action09(Token token)
    {
    	
    }
    
    private void createItem(ItemDecl item)
    {
    	ItemList list = itemListStack.peek();
    	List<ItemListDecl> items = list.getItems();
    	items.get(items.size()-1).getItems().add(item);
    	
    	lastItem = item;
    }
}
