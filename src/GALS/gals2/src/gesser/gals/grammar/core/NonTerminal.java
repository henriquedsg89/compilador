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


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Carlos Gesser
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class NonTerminal extends Symbol
{
	private Set<Terminal> first = null;
	
	private boolean derivesEpsilon;
	
	/**
	 * Says if the derivesEpsilon was already calculated.
	 * this field is set to FALSE when the symbols are cleaned
	 */
	protected static boolean derivesEpsilonComputed = false;
	
	/**
	 * Stores the ptoductions in which this non-terminal is the RHS.
	 * The production contructor adds it to this set.
	 */
	protected Set<Production> productions = new HashSet<Production>();
	
    private boolean artficial = false;
	/**
	 * @param label
	 */
	protected NonTerminal(String label)
	{
		super(label);
	}
	
	protected NonTerminal(String label, boolean artficial)
	{
		super(label);
		this.artficial = artficial;
	}
	
	public boolean isArtificial()
    {
        return artficial;
    }
	
	public Set<Terminal> getFirst() 
	{
		if (first == null)
		{
			computeFirst();		
		}
		return first;
	}
	
	private static void computeFirst()
	{
		for (NonTerminal nt : Symbol.nonTerminals())
		{
			nt.first = new HashSet<Terminal>();
		}	
		
		boolean change = true;
		
		while (change)
		{
			change = false;
			for (NonTerminal nt : Symbol.nonTerminals())
			{
				for (Production p : nt.getProductions())
				{
					Set<Terminal> f = Symbol.first(p.getRhs());
					change = change || nt.first.addAll(f);
				}
				
				if (change)
					break;
			}
		}			
	}

	public boolean derivesEpsilon()
	{
		if (!derivesEpsilonComputed)
			computeDerivesEpsilon();
		
		return derivesEpsilon;
	}
	
	private static void computeDerivesEpsilon()
	{
		Set<Symbol> chosen = new HashSet<Symbol>();
		boolean change = true;
        while (change)
        {
        	change = false;
			for (Symbol s : Symbol.allSymbols())
			{
				if (chosen.contains(s))
					continue;
				
				if (s instanceof SemanticAction)
				{
					chosen.add(s);
					change = true;
				}
				
				else if (s instanceof NonTerminal)
				{
					NonTerminal nt = (NonTerminal) s;
            		
            		for (Production p : nt.productions)
            		{
            			if (p.getRhs().isEmpty())
            			{
            				chosen.add(s);
            				change = true;
            				break;
            			}
            			else
            			{
            				boolean epsilon = true;
            				for (Symbol s2 : p.getRhs())
	    	                {
	    	                    if (!chosen.contains(s2))
	    	                    {
	    	                    	epsilon = false;
	    	                    	break;
	    	                    }		    	                    
	    	                }
            				if (epsilon)
            				{
            					chosen.add(s);
	            				change = true;
	            				break;
            				}
            			}
            		}
				}
			}
        }
        for (Symbol s : Symbol.allSymbols())
        {
        	if (s instanceof NonTerminal)
        	{
        		((NonTerminal)s).derivesEpsilon = (chosen.contains(s));
        	}
        }
        derivesEpsilonComputed = true;
	}
	
	/**
	 * @return Returns the productions.
	 */
	public Set<Production> getProductions() 
	{
		return Collections.unmodifiableSet(productions);
	}

}
