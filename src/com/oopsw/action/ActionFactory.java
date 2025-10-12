package com.oopsw.action;

public class ActionFactory {	
	private ActionFactory(){}

	public static Action getAction(String cmd){
		Action a=null;
		//±¸ÇöºÎ
		switch(cmd){
		case "deleteSchedule":
			a=new DeleteScheduleAction();
			break;
		case "addSchedule":
			a=new AddScheduleAction();
			break;
		case "dotSchedule":
			a=new DotScheduleAction();
			break;
		case "scheduleUI":
			a=new ScheduleUIAction();
			break;
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
