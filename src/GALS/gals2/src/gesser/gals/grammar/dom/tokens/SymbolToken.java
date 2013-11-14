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

package gesser.gals.grammar.dom.tokens;

import gesser.gals.grammar.core.Symbol;

public class SymbolToken extends Token
{
    private final Symbol s;
	/**
	 * @param position
	 * @param lexeme
	 */
	public SymbolToken(Symbol s, int position)
	{
		super(position, s.getLabel());
		this.s = s;
	}

	/**
     * @return Returns the s.
     */
    public Symbol getSymbol()
    {
        return s;
    }
	
	public String toString()
	{
		return getLexeme();
	}
}
