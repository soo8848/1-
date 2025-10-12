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

	public void addSchedule(ScheduleVO scheduleVO) {
		SqlSession conn=DBCP.getSqlSessionFactory().openSession();
		conn.insert("scheduleMapper.addSchedule",scheduleVO);
		conn.commit();
		conn.close();
	}

	public void updateSchedule(ScheduleVO scheduleVO) {
		SqlSession conn=DBCP.getSqlSessionFactory().openSession();
		conn.update("scheduleMapper.updateSchedule",scheduleVO);
		conn.commit();
		conn.close();
	}

	public void deleteSchedule(ScheduleVO scheduleVO) {
		SqlSession conn=DBCP.getSqlSessionFactory().openSession();
		conn.delete("scheduleMapper.deleteSchedule",scheduleVO);
		conn.commit();
		conn.close();
	}



}
