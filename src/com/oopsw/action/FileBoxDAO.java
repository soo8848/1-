package com.oopsw.action;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.oopsw.model.DBCP;
import com.oopsw.model.ProjectVO;
import com.oopsw.model.FileBoxVO;

public class FileBoxDAO {
    // ������Ʈ ��� ��ȸ
    public List<ProjectVO> getProjects() {
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        List<ProjectVO> list = conn.selectList("fileBoxMapper.getProjects");
        conn.close();
        return list;
    }
    
    // ���� �˻� (�κ� �˻� ����)
    public List<FileBoxVO> searchFiles(String keyword) {
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        List<FileBoxVO> list = conn.selectList("fileBoxMapper.searchFiles", keyword);
        conn.close();
        return list;
    }
    
    // ������Ʈ �̸� �ϰ� ������Ʈ �� �������� ���� ���� ��ȸ
    public List<FileBoxVO> getProjectNameTaskFileCount(int projectNo) {
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        List<FileBoxVO> list = conn.selectList("fileBoxMapper.getProjectNameTaskFileCount", projectNo);
        conn.close();
        return list;
    }

    // �� ���� �� ���� ��� ��ȸ
    public List<FileBoxVO> getTaskFiles(int taskNo) {
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        List<FileBoxVO> list = conn.selectList("fileBoxMapper.getTaskFiles", taskNo);
        conn.close();
        return list;
    }
}
