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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carlos Gesser
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Production
{
	private NonTerminal lhs;
	private List<Symbol> rhs = new ArrayList<Symbol>();
	
	public Production(NonTerminal lhs, List<Symbol> rhs)
	{
		this.lhs = lhs;
		this.rhs.addAll(rhs);
		
		lhs.productions.add(this);
	}
	
	public NonTerminal getLhs()
	{
		return lhs;
	}
	
	public List<Symbol> getRhs() 
	{
		return rhs;
	}

	public boolean equals(Object obj)
	{
		if (obj == this)
		    return true;
		else if (obj.getClass() != getClass())
			return false;
		else
		{
			Production p = (Production) obj;
			return lhs.equals(p.lhs) && rhs.equals(p.rhs);
		}
	}
	
	public int hashCode()
	{
		int hashCode = 31 + lhs.hashCode();
		hashCode = 31*hashCode + rhs.hashCode();
		
		return hashCode;
	}
	
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		
		result.append(lhs).append(" ::= ");
		for (Symbol s : rhs)
		{
			result.append(s).append(" ");
		}
		
		return result.toString().trim();
	}
}
