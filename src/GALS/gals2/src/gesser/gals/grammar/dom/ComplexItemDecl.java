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

import gesser.gals.grammar.core.Symbol;

import java.util.ArrayList;
import java.util.List;

public class ComplexItemDecl extends ItemDecl implements ItemList
{
	private final List<ItemListDecl> items = new ArrayList<ItemListDecl>();
	private int start, end;

	public ComplexItemDecl(int start, int end)
	{
		this.start = start;
		this.end = end;
	}
	
	public ComplexItemDecl(int start)
	{
		this.start = start;
	}
	
	public List<ItemListDecl> getItems() 
	{
		return items;
	}

	/**
	 * @return Returns the start.
	 */
	public int getStart()
	{
		return start;
	}
	
	/**
	 * @return Returns the end.
	 */
	public int getEnd()
	{
		return end;
	}
	
	public Symbol getSymbol()
    {
        return Symbol.symbol(this);
    }

	public ItemDecl copy()
	{
		ComplexItemDecl c = new ComplexItemDecl(start, end);
		c.getItems().addAll(getItems());
		c.setCardinality(getCardinality());
		return c;
	}
	
	/**
     * @param end The end to set.
     */
    public void setEnd(int end)
    {
        this.end = end;
    }
	
    public String toString()
	{
		StringBuilder result = new StringBuilder();
		result.append("( ");
		for (ItemListDecl itemList : items)
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
		result.append(')').append(getCardinality());
		return result.toString();
	}

    
}
