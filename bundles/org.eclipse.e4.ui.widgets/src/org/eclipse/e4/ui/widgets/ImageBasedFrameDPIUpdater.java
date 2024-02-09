package org.eclipse.e4.ui.widgets;

import org.eclipse.swt.widgets.Widget;

public class ImageBasedFrameDPIUpdater {
	public void visit(Widget widget, int newZoom, float scalingFactor) {
		if (!(widget instanceof ImageBasedFrame control)) {
			return;
		}
		control.getFramedControl().pack(true);
	}
}