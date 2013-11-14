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

package gesser.gals.grammar.dom;

import java.util.ArrayList;
import java.util.List;

import gesser.gals.grammar.dom.tokens.NonTerminalToken;

/**
 * @author Carlos Gesser
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ProductionDecl implements ItemList
{
	private final NonTerminalToken lhs;
	private final List<ItemListDecl> rhs = new ArrayList<ItemListDecl>();
	
	public ProductionDecl(NonTerminalToken lhs)
	{
		this.lhs = lhs;
	}
	
	/**
	 * @return Returns the lhs.
	 */
	public NonTerminalToken getLhs()
	{
		return lhs;
	}
		
	/**
	 * @return Returns the rhs.
	 */
	public List<ItemListDecl> getItems() 
	{
		return rhs;
	}
	
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		
		result.append(lhs).append(" ::= ");
		
		for (ItemListDecl itemList : rhs)
		{
			if (itemList.getItems().isEmpty())
			{
				result.append("î").append(" ");
			}
			else
				for (ItemDecl item : itemList.getItems())
				{
					result.append(item).append(" ");
				}
			result.append("| ");
		}
		result.setLength(result.length()-2);
		result.append(';');
		
		return result.toString();
	}
}
