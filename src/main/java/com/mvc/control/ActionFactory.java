package com.mvc.control;

import com.mvc.action.Action;
import com.mvc.action.IndexAction;

public class ActionFactory {

	private static ActionFactory factory;
	
	private ActionFactory() {}
	
	public static synchronized ActionFactory getInstance() {
		if(factory == null) {
			factory = new ActionFactory();
		}
		return factory;
	}
	
	public Action getAction(String cmd) { // Mvc2Project/mvc/test.do?cmd=index
		Action action = null;
		
		if(cmd.equals("index")) {
			action = new IndexAction();
		}
		
		return action;
	}
}
