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
	
	
	// 댓글 추가
	public void insertArticle(CommentVO article) {
		
		
		
		int num = article.getNum();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		
		int number = 0;
		String strQuery ="";
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select max(num) from p_comment"); //보드에서 어느게 젤 큰지
			rs = pstmt.executeQuery();
			
			if(rs.next()) number = rs.getInt(1) +1 ;  //rs에 있다면 1을 추가 
			else number = 1;  //없으면 1
			
			if(num != 0) { // 답변 글 일 경우
											//답변 일 경우 그 화살표가 1개씩 추가 되야됨
				strQuery = "update p_comment set step=step+1 where ref=? and step > ?";
				
				pstmt = conn.prepareStatement(strQuery);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				
				//업데이트니깐
				pstmt.executeUpdate();
				
				step = step + 1;
				depth = depth + 1;
				
			}else { //그렇지 않으면 새글
				ref = number;
				step =0;
				depth =0;
			}			//새글이기에 다들어가야됨
			strQuery = "insert into p_comment(COMMENT_NUM, COMMENT_WRITER, REGDATE, COMMENT_CONTENT, COMMENT_PNUM, REF, STEP, DEPTH) " 
					+ "values(p_comment_seq.nextval, ?,?,?,?,?,?,?)";
				//시퀀스 테이블에 자동으로 하나씩 증가 할 수 있도록 이미 설정해놨음
			
			pstmt = conn.prepareStatement(strQuery);
			//num은 시퀀스가 이미 처리했기 때문에 writer
			pstmt.setString(1, article.getWriter());
			pstmt.setTimestamp(2, article.getRegdate());
			pstmt.setString(3, article.getContent());
			pstmt.setInt(4, ref); // 얘는 아까 인트형으로 변환 시켰음
			pstmt.setInt(5, step); // 얘는 아까 인트형으로 변환 시켰음
			pstmt.setInt(6, depth); // 얘는 아까 인트형으로 변환 시켰음
			
			
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException s1) {
			s1.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException s1){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
			if(conn != null) try{conn.close();}catch(SQLException s3){}		
		}
		
		
	}
	
	
	
	// 댓글 목록
	public List<CommentVO> getList(int num){
			
		List<CommentVO> commentList = null; //리턴할 객체 생성
		
		try {
			conn = ConnUtil.getConnection();                 //내림차순으로
			/* pstmt = conn.prepareStatement("select * from board order by num desc"); */
			//안쪽 쿼리가 서브 바깥쪽 쿼리가 메인
			pstmt = conn.prepareStatement("select * from p_comment where comment_pnum = ?"); //rnum이 시작번호 
			
			//위에 쿼리문에서 바인딩 함수가 2개임
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
 				}while(rs.next()); //안에 있으면 계속 반복해서 꺼내라
 				
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
