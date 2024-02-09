package org.eclipse.e4.ui.widgets;

import org.eclipse.swt.widgets.Widget;

public class ImageBasedFrameDPIUpdater {
	public void visit(Widget widget, int newZoom, float scalingFactor) {
		if (!(widget instanceof ImageBasedFrame)) {
			return;
		}
		ImageBasedFrame control = (ImageBasedFrame) widget;
		if (control.getImageCache() != null) {
			control.getImageCache().handleDPIChange(newZoom);
			control.setImages(control.getImageCache(), null, control.getHandleImage());
		}
		control.getFramedControl().pack(true);
	}
}