package com.eagerlogic.cubee.client.events;

import com.google.gwt.dom.client.DataTransfer;

public class DragStartEventArgs extends EventArgs {

	private final com.google.gwt.user.client.Event event;

	public DragStartEventArgs(Object sender, com.google.gwt.user.client.Event event) {
		super(sender);
		this.event = event;
	}
	
	public void stopPropagation() {
		event.stopPropagation();
	}
	
	public void preventDefault() {
		event.preventDefault();
	}
	
	public DataTransfer getDataTransfer() {
		return event.getDataTransfer();
	}

}
