package com.oopsw.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class FileBoxDAO {
    // ������Ʈ ��� ��ȸ
    public List<ProjectVO> getProjects() {
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        List<ProjectVO> list = conn.selectList("fileBoxMapper.getProjects");
        conn.close();
        return list;
    }
    
    // ���� �˻� (�κ� �˻� ����)
    public List<SearchFileBoxVO> searchFiles(String keyword) {
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        List<SearchFileBoxVO> list = conn.selectList("fileBoxMapper.searchFiles", keyword);
        conn.close();
        return list;
    }
    
    // ������Ʈ �̸� �ϰ� ������Ʈ �� �������� ���� ���� ��ȸ
    public List<SearchFileBoxVO> getProjectNameTaskFileCount(int projectNo) {
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        List<SearchFileBoxVO> list = conn.selectList("fileBoxMapper.getProjectNameTaskFileCount", projectNo);
        conn.close();
        return list;
    }

    // �� ���� �� ���� ��� ��ȸ
    public List<SearchFileBoxVO> getTaskFiles(int taskNo) {
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        List<SearchFileBoxVO> list = conn.selectList("fileBoxMapper.getTaskFiles", taskNo);
        conn.close();
        return list;
    }
}
