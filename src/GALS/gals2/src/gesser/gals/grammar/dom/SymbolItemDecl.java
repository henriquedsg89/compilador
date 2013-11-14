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
import gesser.gals.grammar.dom.tokens.SymbolToken;

/**
 * @author Carlos Gesser
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SymbolItemDecl extends ItemDecl
{
	private final SymbolToken token;
	
	public SymbolItemDecl(SymbolToken symbol)
	{
		this.token = symbol;
	}
	
	public int getStart()
	{
		return token.getPosition();
	}

	public int getEnd()
    {
        return token.getPosition() + token.getLexeme().length();
    }
	
	public Symbol getSymbol()
	{
	    if (getCardinality() == Cardinality.NONE)
	        return token.getSymbol();
	    else
	        return Symbol.symbol(this);
	}

	public ItemDecl copy()
	{
		SymbolItemDecl i = new SymbolItemDecl(token);
		i.setCardinality(getCardinality());
		return i;
	}
	
	public String toString()
	{
		return token.getLexeme() + getCardinality();
	}

}
