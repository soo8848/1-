package com.oopsw.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.oopsw.model.DBCP;
import com.oopsw.model.ScheduleVO;

public class ScheduleDAO {
	
	public List<ScheduleVO> getDotSchedule(String employeeId) {
		   List<ScheduleVO> dotSchedulesList = new ArrayList<>();
			SqlSession conn=DBCP.getSqlSessionFactory().openSession();
			dotSchedulesList= conn.selectList("scheduleMapper.getDotSchedule", employeeId);
			conn.close();
		   
		return dotSchedulesList;
	}

}
