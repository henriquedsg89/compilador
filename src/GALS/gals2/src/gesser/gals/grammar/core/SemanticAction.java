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
import java.util.Set;

/**
 * @author Carlos Gesser
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SemanticAction extends Symbol
{
	private static final Set<Terminal> FIRST = Collections.emptySet();//Collections.singleton(Symbol.EPSILON);
	/**
	 * @param label
	 */
	public SemanticAction(String label)
	{
		super(label);
	}

	public Set<Terminal> getFirst() 
	{
		return FIRST;
	}

	public boolean derivesEpsilon()
	{
		return true;
	}
}
