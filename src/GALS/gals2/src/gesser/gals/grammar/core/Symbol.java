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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gesser.gals.grammar.dom.ItemDecl;

/**
 * @author Carlos Gesser
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class Symbol
{
    private static Map<String, Symbol> symbols = new LinkedHashMap<String, Symbol>();
    private static Set<Terminal> terminals = new HashSet<Terminal>();
    private static Set<NonTerminal> nonTerminals = new HashSet<NonTerminal>();
    private static Set<SemanticAction> semanticActions = new HashSet<SemanticAction>();
    private static NonTerminal startSymbol = null;
    
	private String label;
	
	public static final Terminal EPSILON = new Terminal("î");
	public static final Terminal DOLLAR = new Terminal("$");
	
	protected Symbol(String label)
	{
		this.label = label;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public boolean isArtificial()
	{
	    return false;
	}
	
	public String toString()
	{
		return label;
	}
	
	public boolean equals(Object obj)
	{
		if (this.getClass() != obj.getClass())
			return false;
		
		return label.equals( ((Symbol)obj).label );
	}
	
	public abstract Set<Terminal> getFirst();
	public abstract boolean derivesEpsilon();
	
	public int hashCode()
	{
		return label.hashCode();
	}
	
	public static NonTerminal nonTerminal(String label)
	{   
	    NonTerminal nt = (NonTerminal) symbols.get(label);
	    if (nt == null)
	    {
	        nt = new NonTerminal(label);
	        symbols.put(label, nt);
	        nonTerminals.add(nt);
	    }
	    
	    if (startSymbol == null)
	    	startSymbol = nt;
	    
		return nt;
	}
	
	public static Terminal terminal(String label)
	{
	    Terminal t = (Terminal) symbols.get(label);
	    if (t == null)
	    {
	        t = new Terminal(label);
	        symbols.put(label, t);
	        terminals.add(t);
	    }
		return t;
	}
	
	public static SemanticAction semanticAction(String label)
	{
	    SemanticAction t = (SemanticAction) symbols.get(label);
	    if (t == null)
	    {
	        t = new SemanticAction(label);
	        symbols.put(label, t);
	        semanticActions.add(t);
	    }
		return t;
	}
	
	public static NonTerminal symbol(ItemDecl cid)
	{
	    String label = "<"+cid.toString()+">";
	    return nonTerminal(label);
	}

	public static void clearSymbols()
	{
		symbols.clear();
		terminals.clear();
		nonTerminals.clear();
		semanticActions.clear();
		
		startSymbol = null;
		
		NonTerminal.derivesEpsilonComputed = false;
	}
	
	public static Collection<Symbol> allSymbols()
	{
	    return Collections.unmodifiableCollection(symbols.values());
	}
	
	public static Collection<Terminal> terminals()
	{
	    return Collections.unmodifiableCollection(terminals);
	}
	
	public static Collection<NonTerminal> nonTerminals()
	{
	    return Collections.unmodifiableCollection(nonTerminals);
	}
	
	public static Collection<SemanticAction> semanticActions()
	{
	    return Collections.unmodifiableCollection(semanticActions);
	}

	public static Set<Terminal> first(List<Symbol> rhs)
	{
		if (rhs.isEmpty())
			return Collections.singleton(EPSILON);
		else
		{
			Set<Terminal> result = new HashSet<Terminal>();
			for (Symbol s : rhs)
			{
				result.addAll(s.getFirst());
				if (! s.derivesEpsilon())
				{
					result.remove(EPSILON);
					break;
				}
			}
			return result;
		}
	}
}
