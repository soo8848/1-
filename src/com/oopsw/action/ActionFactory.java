package com.oopsw.action;

import com.oopsw.action.fileboxAction.FileBoxAction;
import com.oopsw.action.fileboxAction.FileBoxUIAction;
import com.oopsw.action.fileboxAction.SearchFileBoxAction;
import com.oopsw.action.fileboxAction.filesTaskFileBoxAction;
import com.oopsw.action.fileboxAction.projectFileBoxAction;
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
		case "filesTaskFileBox":
			a=new filesTaskFileBoxAction();
			break;
		case "projectFileBox":
			a=new projectFileBoxAction();
			break;
		case "searchFileBox":
			a=new SearchFileBoxAction();
			break;
		case "fileBox":
			a=new FileBoxAction();
			break;
		case "fileBoxUI":
			a=new FileBoxUIAction();
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
