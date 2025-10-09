package test.com.oopsw.model;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.Test;

import com.oopsw.action.ScheduleDAO;
import com.oopsw.model.ScheduleVO;

public class ScheduleDAOTest {

	//일정관리 전체 조회
    @Test
    public void testGetDotSchedule() {
        ScheduleDAO dao = new ScheduleDAO();
        String employeeId = "1004014";
        List<ScheduleVO> list = dao.getDotSchedule(employeeId);
        assertNotNull("리스트가 null이면 안됨", list);
        System.out.println("조회된 일정 개수: " + list.size());
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        
        if (!list.isEmpty()) {
            for (ScheduleVO vo : list) {
            	String dateStr = sdf.format(vo.getScheduleDate());
                System.out.println("날짜: " + dateStr);
                System.out.println("타입: " + vo.getScheduleType());
                System.out.println("제목: " + vo.getTitle());
                String startTimeStr = sdf2.format(vo.getStartTime());
                System.out.println("시작시간: " + startTimeStr);
                String endTimeStr = sdf2.format(vo.getEndTime());                
                System.out.println("종료시간: " + endTimeStr);
            }
        }
    }
}
