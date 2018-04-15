package com.example.common.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.codehaus.xfire.MessageContext;
import org.codehaus.xfire.fault.XFireFault;
import org.codehaus.xfire.handler.Handler;

public abstract class AbstractHandler implements Handler {
	
	private List before = new ArrayList();
	private List after = new ArrayList();
	private String phase;

	public AbstractHandler() {
		this.phase = "user";
	}

	public QName[] getUnderstoodHeaders() {
		return null;
	}

	public String[] getRoles() {
		return null;
	}

	public final String getPhase() {
		return this.phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public void handleFault(XFireFault fault, MessageContext context) {
	}

	public void after(String handler) {
		this.after.add(handler);
	}

	public void before(String handler) {
		this.before.add(handler);
	}

	public List getAfter() {
		return this.after;
	}

	public List getBefore() {
		return this.before;
	}

	public void setAfter(List after) {
		if (this.after == null) {
			this.after = after;
		} else {
			this.after.addAll(after);
		}
	}

	public void setBefore(List before) {
		if (this.before == null) {
			this.before = before;
		} else {
			this.before.addAll(before);
		}
	}
}
