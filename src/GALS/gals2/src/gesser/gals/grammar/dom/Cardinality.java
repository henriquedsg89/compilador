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

/**
 * @author Carlos Gesser
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public final class Cardinality
{
	private final String str;
	
	private Cardinality(String str)
	{
		this.str = str;
	}
	
	public static final Cardinality NONE = new Cardinality("");
	public static final Cardinality ZERO_OR_ONE = new Cardinality("?");
	public static final Cardinality ONE_OR_MORE = new Cardinality("+");	
	public static final Cardinality ZERO_OR_MORE = new Cardinality("*");
	
	public String toString()
	{
		return str;
	}
}
