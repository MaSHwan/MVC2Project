package com.comment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.comment.model.*;


public class CommentDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	// ��� �߰�
	public void insertArticle(CommentVO article) {
		
		
		
		int num = article.getNum();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		
		int number = 0;
		String strQuery ="";
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select max(num) from p_comment"); //���忡�� ����� �� ū��
			rs = pstmt.executeQuery();
			
			if(rs.next()) number = rs.getInt(1) +1 ;  //rs�� �ִٸ� 1�� �߰� 
			else number = 1;  //������ 1
			
			if(num != 0) { // �亯 �� �� ���
											//�亯 �� ��� �� ȭ��ǥ�� 1���� �߰� �Ǿߵ�
				strQuery = "update p_comment set step=step+1 where ref=? and step > ?";
				
				pstmt = conn.prepareStatement(strQuery);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				
				//������Ʈ�ϱ�
				pstmt.executeUpdate();
				
				step = step + 1;
				depth = depth + 1;
				
			}else { //�׷��� ������ ����
				ref = number;
				step =0;
				depth =0;
			}			//�����̱⿡ �ٵ��ߵ�
			strQuery = "insert into p_comment(COMMENT_NUM, COMMENT_WRITER, REGDATE, COMMENT_CONTENT, COMMENT_PNUM, REF, STEP, DEPTH) " 
					+ "values(p_comment_seq.nextval, ?,?,?,?,?,?,?)";
				//������ ���̺� �ڵ����� �ϳ��� ���� �� �� �ֵ��� �̹� �����س���
			
			pstmt = conn.prepareStatement(strQuery);
			//num�� �������� �̹� ó���߱� ������ writer
			pstmt.setString(1, article.getWriter());
			pstmt.setTimestamp(2, article.getRegdate());
			pstmt.setString(3, article.getContent());
			pstmt.setInt(4, ref); // ��� �Ʊ� ��Ʈ������ ��ȯ ������
			pstmt.setInt(5, step); // ��� �Ʊ� ��Ʈ������ ��ȯ ������
			pstmt.setInt(6, depth); // ��� �Ʊ� ��Ʈ������ ��ȯ ������
			
			
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException s1) {
			s1.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException s1){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
			if(conn != null) try{conn.close();}catch(SQLException s3){}		
		}
		
		
	}
	
	
	
	// ��� ���
	public List<CommentVO> getList(int num){
			
		List<CommentVO> commentList = null; //������ ��ü ����
		
		try {
			conn = ConnUtil.getConnection();                 //������������
			/* pstmt = conn.prepareStatement("select * from board order by num desc"); */
			//���� ������ ���� �ٱ��� ������ ����
			pstmt = conn.prepareStatement("select * from p_comment where comment_pnum = ?"); //rnum�� ���۹�ȣ 
			
			//���� ���������� ���ε� �Լ��� 2����
			pstmt.setInt(1, num);
			
			
			rs = pstmt.executeQuery();
 			
 			if(rs.next()) {
 				commentList = new ArrayList<>();
 				do {
 					
 					CommentVO comment = new CommentVO();
 					
 					comment.setNum(rs.getInt("num"));
 					comment.setWriter(rs.getString("writer"));
 					
 					comment.setRegdate(rs.getTimestamp("regdate"));
 					
 					comment.setContent(rs.getString("content"));
 					comment.setPnum(rs.getInt("pnum"));
 					comment.setRef(rs.getInt("ref"));
 					comment.setStep(rs.getInt("step"));
 					comment.setDepth(rs.getInt("depth"));
 				
 				
 					commentList.add(comment);
 				}while(rs.next()); //�ȿ� ������ ��� �ݺ��ؼ� ������
 				
 			}
			
		}catch (SQLException s1) {
			s1.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException s1){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
			if(conn != null) try{conn.close();}catch(SQLException s3){}		
		}
		
		return commentList;
	}
	
}
