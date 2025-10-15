package com.oopsw.action;

import com.oopsw.action.sceduleAction.AddScheduleAction;
import com.oopsw.action.sceduleAction.DeleteScheduleAction;
import com.oopsw.action.sceduleAction.DotScheduleAction;
import com.oopsw.action.sceduleAction.ScheduleUIAction;
import com.oopsw.action.sceduleAction.UpdateScheduleAction;

public class ActionFactory {	
	private ActionFactory(){}

	public static Action getAction(String cmd){
		Action a=null;
		//±¸ÇöºÎ
		switch(cmd){
		case "searchFileBox":
			a=new searchFileBoxAction();
			break;
		case "fileBox":
			a=new fileBoxAction();
			break;
		case "fileBoxWithinTaskUI":
			a=new FileBoxWithinTaskUIAction();
			break;
		case "updateSchedule":
			a=new UpdateScheduleAction();
			break;
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
