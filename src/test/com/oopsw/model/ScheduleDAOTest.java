package test.com.oopsw.model;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.Test;

import com.oopsw.action.ScheduleDAO;
import com.oopsw.model.ScheduleVO;

public class ScheduleDAOTest {

	//�������� ��ü ��ȸ
    @Test
    public void testGetDotSchedule() {
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
}
