/**********************************************************************
Copyright (c) 2000, 2002 IBM Corp. and others.
All rights reserved. This program and the accompanying materials
are made available under the terms of the Common Public License v1.0
which accompanies this distribution, and is available at
http://www.eclipse.org/legal/cpl-v10.html

Contributors:
    IBM Corporation - Initial implementation
**********************************************************************/
package org.eclipse.ui.internal.editors.text;

import java.util.Iterator;
import org.eclipse.swt.widgets.Display;
import java.util.Map;
import java.util.HashMap;
import org.eclipse.swt.graphics.Color;
import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.swt.graphics.RGB;

class SharedTextColors implements ISharedTextColors {

	private Map fDisplayTable;

	public SharedTextColors() {
		super();
	}

	public Color getColor(RGB rgb) {
		if (rgb == null)
			return null;
			
		if (fDisplayTable == null)
			fDisplayTable= new HashMap(2);
		
		Display display= Display.getCurrent();
		
		Map colorTable= (Map) fDisplayTable.get(display);
		if (colorTable == null) {
			colorTable= new HashMap(10);
			fDisplayTable.put(display, colorTable);
		}
			
		Color color= (Color) colorTable.get(rgb);
		if (color == null) {
			color= new Color(display, rgb);
			colorTable.put(rgb, color);
		}
			
		return color;
	}

	public void dispose() {
		if (fDisplayTable != null) {
			Iterator j= fDisplayTable.values().iterator();
			while (j.hasNext()) {
				Iterator i= ((Map) j.next()).values().iterator();
				while (i.hasNext())
					((Color) i.next()).dispose();
			}
		}
	}
	
}