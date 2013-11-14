/*
 * Copyright 2004 Carlos Eduardo Gesser
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, you can get it at http://www.gnu.org/licenses/gpl.txt
 */

package gesser.gals.grammar.core;

import gesser.gals.grammar.dom.Cardinality;
import gesser.gals.grammar.dom.ComplexItemDecl;
import gesser.gals.grammar.dom.GrammarDecl;
import gesser.gals.grammar.dom.ItemDecl;
import gesser.gals.grammar.dom.ItemListDecl;
import gesser.gals.grammar.dom.ProductionDecl;
import gesser.gals.grammar.dom.SymbolItemDecl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Carlos Gesser
 */
public class Grammar
{
	List<Production> productions = new ArrayList<Production>();
	
	private Grammar() {}

	public String toString()
	{
		StringBuilder result = new StringBuilder();
		for (Production p : productions)
		{
			result.append(p).append("\n");
		}
		
		return result.toString().trim();
	}
	
	public static Grammar process(GrammarDecl gd)
	{
		Grammar g = new Grammar();
		
		for (ProductionDecl p : gd.getProductions())
		{
			NonTerminal lhs = (NonTerminal) p.getLhs().getSymbol();
			for (ItemListDecl list : p.getItems())
			{
				g.productions.add(new Production(lhs, simplify(list, g)));
			}
		}
		
		return g;
	}
	
	/**
	 * Converts an ItemDeclList to a List&lt;Symbol&gt;, adding new productions
	 * to g if rhs has any ComplexItemDecl.
	 * 
	 * @param rhs ItemDeclList to be simplified
	 * @param g the Grammar
	 * @return List of Symbols, from rhs
	 */
	private static List<Symbol>	simplify(ItemListDecl rhs, Grammar g)
	{
		List<Symbol> newItems = new ArrayList<Symbol>();
		
		for (ItemDecl item : rhs.getItems())
		{
			Symbol s;
			if (item.getCardinality() == Cardinality.NONE && item instanceof SymbolItemDecl)
				s = item.getSymbol();
			else 
			{
				s = breakComplex( item, g );
			}
			newItems.add(s);			
		}
		
		return newItems;
	}
			
	/** 
	 * Breaks a ComplexItemDecl into a list of productions and adds them to g.
	 * Each component from the ComplexItemDecl is converted to a production like:
	 * <pre>ComplexItemDecl ::= component</pre>
	 * Then the complex item cardinality operator is applied to the new generated
	 * productions.
	 * 
	 * @param ci the ComplexItemDecl
	 * @param g the Grammar
	 * 
	 * @return the Symbol generated from the ComplexItemDecl toString
	 */
	
	private static NonTerminal breakComplex(ItemDecl item, Grammar g)
	{
		NonTerminal lhs = (NonTerminal) item.getSymbol();
		Cardinality cardinality = item.getCardinality();
		item = item.copy();
		
		List<List<Symbol>> generated = new ArrayList<List<Symbol>>(); 
		
		if (item instanceof ComplexItemDecl)
		{
			ComplexItemDecl ci = (ComplexItemDecl) item;
			for (ItemListDecl items : ci.getItems())
			{
				generated.add(simplify(items, g));
			}
		}
		else
		{
		    item.setCardinality(Cardinality.NONE);
		    
			List<Symbol> rhs = new ArrayList<Symbol>();
			rhs.add( item.getSymbol() );
			generated.add(rhs);
		}
	
		if (cardinality == Cardinality.ZERO_OR_ONE)
		{
			generated.add(Collections.<Symbol>emptyList());
		}
		else if (cardinality == Cardinality.ZERO_OR_MORE)
		{
			for (List<Symbol> sl : generated)
			{
				sl.add(lhs);			
			}
			generated.add(Collections.<Symbol>emptyList());
		}
		else if (cardinality == Cardinality.ONE_OR_MORE)
		{
			ItemDecl i = item;
			i.setCardinality(Cardinality.ZERO_OR_MORE);
			
			NonTerminal nt = breakComplex(i, g);
			for (List<Symbol> sl : generated)
			{
				sl.add(nt);			
			}
		}
		
		for (List<Symbol> rhs : generated)
		{
			g.productions.add(new Production(lhs, rhs));
		}
		
		return lhs;
	}
}
