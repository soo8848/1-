package test.com.oopsw.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import com.oopsw.model.ScheduleDAO;
import com.oopsw.model.ScheduleVO;

public class ScheduleDAOTest {

    @Test
    public void getDotScheduleTest() {
        ScheduleDAO dao = new ScheduleDAO();
        String employeeId = "1004014";
        List<ScheduleVO> list = dao.getDotSchedule(employeeId);
        assertNotNull("����Ʈ�� null�̸� �ȵ�", list);
        System.out.println("��ȸ�� ���� ����: " + list.size());
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        
        if (!list.isEmpty()) {
            for (ScheduleVO vo : list) {
            	String dateStr = sdf.format(vo.getScheduleDate());
                System.out.println("��¥: " + dateStr);
                System.out.println("Ÿ��: " + vo.getScheduleType());
                System.out.println("����: " + vo.getTitle());
                String startTimeStr = sdf2.format(vo.getStartTime());
                System.out.println("���۽ð�: " + startTimeStr);
                String endTimeStr = sdf2.format(vo.getEndTime());                
                System.out.println("����ð�: " + endTimeStr);
            }
        }
    }
    
	@Test
	public void addScheduleTest(){
		Date scheduleDate = Date.valueOf("2025-10-11");
		Timestamp startTime = Timestamp.valueOf("2025-10-11 09:00:00");
		Timestamp endTime = Timestamp.valueOf("2025-10-11 18:00:00");
		new ScheduleDAO().addSchedule(
//String employeeId, Date scheduleDate, String scheduleType, String title, Date startTime, Date endTime
				new ScheduleVO("1004014", scheduleDate, "green", "����", startTime, endTime)
				);
	}
	
	@Test
	public void deleteScheduleTest() {
			assertTrue(new ScheduleDAO().deleteSchedule(17));

	}
	
	@Test
	public void updateScheduleTest() {
	    ScheduleVO vo = new ScheduleVO(
	        17,                    // scheduleNo
	        "1004014",          // employeeId
	        Date.valueOf("2025-10-12"), // scheduleDate
	        "blue",                // scheduleType
	        "�׽�Ʈ ����",           // title
	        Timestamp.valueOf("2025-10-12 09:00:00"), // startTime
	        Timestamp.valueOf("2025-10-12 18:00:00")  // endTime
	    );

	    new ScheduleDAO().updateSchedule(vo);
	}

}
