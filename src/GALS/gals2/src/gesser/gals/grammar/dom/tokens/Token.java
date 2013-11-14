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

/**
 * @author Carlos Gesser
 *
 * A token inside a Grammar especification
 */
public class Token
{
	private final int position;
	private final String lexeme;
	
	public Token(int position, String lexeme)
	{
		this.position = position;
		this.lexeme = lexeme;
	}
	
	/**
	 * @return Returns the position.
	 */
	public int getPosition()
	{
		return position;
	}
	
	/**
	 * @return Returns the lexeme.
	 */
	public String getLexeme()
	{
		return lexeme;
	}
}
