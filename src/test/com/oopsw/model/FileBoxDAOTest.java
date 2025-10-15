package test.com.oopsw.model;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.oopsw.model.FileBoxDAO;
import com.oopsw.model.ProjectVO;
import com.oopsw.model.SearchFileBoxVO;

public class FileBoxDAOTest {

    private FileBoxDAO fileBoxDAO = new FileBoxDAO();

//    @Test
    public void testGetProjects() {
        List<ProjectVO> projects = fileBoxDAO.getProjects();
        for (ProjectVO projectVO : projects) {
            System.out.println(projectVO.getProjectNo() + "/" +projectVO.getProjectName() + " / " + projectVO.getClient() + " / " + projectVO.getDescription());
        }
    }
    
    @Test
    public void testSearchFiles() {
        FileBoxDAO dao = new FileBoxDAO();
        String keyword = "JPA";

        List<SearchFileBoxVO> result = dao.searchFiles(keyword);


        SearchFileBoxVO first = result.get(0);
        assertNotNull(first.getProjectName(), "projectName은 null이면 안 됩니다.");
        assertNotNull(first.getTaskName(), "taskName은 null이면 안 됩니다.");

        for (SearchFileBoxVO file : result) {
            System.out.println("Project: " + file.getProjectName() +
                               ", Task: " + file.getTaskName() +", FileName: " + file.getFileName());
        }
        }
    
//    @Test
//    public void testGetTaskFiles() {
//        int taskNo = 15;
//        List<FileBoxVO> files = fileBoxDAO.getTaskFiles(taskNo);
//        for (FileBoxVO f : files) {
//            System.out.println("Comment: " + f.getCommentFile() + ", Reply: " + f.getReplyFile());
//        }
//    }
}