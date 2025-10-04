package com.oopsw.action;

public class ActionFactory {	
	private ActionFactory(){}

	public static Action getAction(String cmd){
		Action a=null;
		//±¸ÇöºÎ
		switch(cmd){
		case "loginUI":
		case "mainUI":
//			a=new LoginUIAction();
			break;
		default:
//			a=new LoginUIAction();
		}
		return a;
	}
	
}
