package com.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	
	//이렇게 하면 외부에서 편집을 하질 못함	
	private static BoardDAO instance =null;
	
	//생성자를 private로 
	private BoardDAO() {   }
	
	
	public static BoardDAO getInstance() {
		if(instance ==null) {
		synchronized (BoardDAO.class) {
			instance = new BoardDAO();
		}
	}
		return instance;
	}
	//이곳에서 부터 게시판 작업 기능을 하나씩 메소드를 추가해서 작성 하면 됨
	//insertArticle 글을 데이터 베이스에 추가하는 메소드
	public void insertArticle(BoardVO article) {
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //rs가 필요한 이유가 답변 글
		
		int num = article.getNum();
		
		
		int number = 0;
		String strQuery ="";
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select max(num) from p_board"); //보드에서 어느게 젤 큰지
			rs = pstmt.executeQuery();
			
			if(rs.next()) number = rs.getInt(1) +1 ;  //rs에 있다면 1을 추가 
			else number = 1;  //없으면 1
			
			
			strQuery = "insert into p_board(num, writer, subject, readcount, regdate, content) " 
					+ "values(boardone_seq.nextval, ?, ?, ?, ?, ?)";
				//시퀀스 테이블에 자동으로 하나씩 증가 할 수 있도록 이미 설정해놨음
			
			pstmt = conn.prepareStatement(strQuery);
			//num은 시퀀스가 이미 처리했기 때문에 writer
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setInt(3, article.getReadcount());
			pstmt.setTimestamp(4, article.getRegdate());
			pstmt.setString(5, article.getContent());
			
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException s1) {
			s1.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException s1){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
			if(conn != null) try{conn.close();}catch(SQLException s3){}		
		}
		
		
	} //end of insertArticle 
	
	/*
	 *  글 작성을 수행하고 나면 list.jsp로 redirect 하다록 되어 있다.
	 *  따라서 결과를 보려면 list.jsp를 작성해야함
	 *  글 전체 글의 개수를 가져와서 목록을 보여줘야 된다.
	 */
	
	//전체 글이 몇개인지 인트형으로 메소드
	public int getArticleCount() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x =0;
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from p_board"); //전체 개수를 얻어 온다
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1); //첫번째 컬럼을 가져온다 첫번째 컬럼이 num?
			}
			//list를 arraylist에 저장한 다음에 그걸로 뿌려준다.
			
			
		}catch (SQLException s1) {
			s1.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException s1){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
			if(conn != null) try{conn.close();}catch(SQLException s3){}		
		}
		
		return x;
	}// end of  getArticleCount
	
	/*
	 * board 테이블에서 데이터를 가져와서 보여줄 메소드를 추가
	 * 이때 쓰는것이 List<BoardVO>
	 */
	
	
	//여기다 글 게시판 페이지 수를 해줘야됨
	//start : 시작번호 , end 끝페이지
	public List<BoardVO> getArticles(int start, int end){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVO> articleList = null; //리턴할 객체 생성
		
		try {
			conn = ConnUtil.getConnection();                 //내림차순으로
			/* pstmt = conn.prepareStatement("select * from board order by num desc"); */
			//안쪽 쿼리가 서브 바깥쪽 쿼리가 메인
			pstmt = conn.prepareStatement("select * from (select rownum rnum, num, writer, subject, regdate, readcount, content from (select * from p_board order by num desc)) where rnum >= ? and rnum <= ?"); //rnum이 시작번호 
			
			//위에 쿼리문에서 바인딩 함수가 2개임
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			
			rs = pstmt.executeQuery();
 			
 			if(rs.next()) {
 				articleList = new ArrayList<>(end - start +1);
 				do {
 					
 					BoardVO article = new BoardVO();
 					
 					article.setNum(rs.getInt("num"));
 					article.setWriter(rs.getString("writer"));
 					article.setSubject(rs.getString("subject"));
 					article.setRegdate(rs.getTimestamp("regdate"));
 					article.setReadcount(rs.getInt("readcount"));
 					article.setContent(rs.getString("content"));
 				
 				
 					articleList.add(article);
 				}while(rs.next()); //안에 있으면 계속 반복해서 꺼내라
 				
 			}
			
		}catch (SQLException s1) {
			s1.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException s1){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
			if(conn != null) try{conn.close();}catch(SQLException s3){}		
		}
		
		return articleList;
	} //end of getArticles
	
	/* 
	 * list.jsp 페이지에서 글 제목을 누르면 글 내용을 볼 수 있도록 구현함
	 * 글을 num을 매개변수로 해서 하나의 글을 가져와서 보여줌
	 * 상세정보 데이터베이스에서 가져와야된다.
	 * 
	 * 글 하나의 정보를 가져오는 메소드를 구현 해야 한다.
	 */
	
	 public BoardVO getArticle(int num) {
		 Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BoardVO article = null;
		 
		try {
			
		conn = ConnUtil.getConnection();
		pstmt = conn.prepareStatement("update p_board set readcount=readcount+1 where num=?"); //글을 누르면 조회수가 올라가야됨 갱신 되야된다는뜻이다.
		pstmt.setInt(1, num);
		
		pstmt.executeUpdate();	
		
		pstmt = conn.prepareStatement("select * from p_board where num=? "); //조회
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
				article = new BoardVO(); //객체 만듬
			
				//db에서 가져와야됨
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));;
				article.setContent(rs.getString("content"));
		}
			
		}catch (SQLException s1) {
			s1.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException s1){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
			if(conn != null) try{conn.close();}catch(SQLException s3){}		
		}
		
		return article;
		
	 } // end getArticle
	
	 /*
	  * 글 수정 버튼을  누를 경우 updateForm.jsp 페이지로 이동하도록 링크를 걸었다.
	  * 글 수정 시  글 목록 보기와 다르게 조회수를 증가시킬 필요는 없다.
	  * 
	  * 조회수 증가를 제외하고 num에 해당하는 글만 가져오는 메소드를 구현한당
	  * 
	  */
	
	 
	 public BoardVO updateGetArticle(int num) {
		 	
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BoardVO article = null;
		 
		try {
			
		conn = ConnUtil.getConnection();
		/*
		 * pstmt =
		 * conn.prepareStatement("update board set readcount=readcount+1 where num=?");
		 * //글을 누르면 조회수가 올라가야됨 갱신 되야된다는뜻이다. pstmt.setInt(1, num);
		 * 
		 * pstmt.executeUpdate(); 얘는 조회수 이기 떄문에 필요가 없음 나머지는 다 똑같음
		 */
		
		pstmt = conn.prepareStatement("select * from p_board where num=? "); //조회
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
				article = new BoardVO(); //객체 만듬
			
				//db에서 가져와야됨
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setContent(rs.getString("content"));
		}
			
		}catch (SQLException s1) {
			s1.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException s1){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
			if(conn != null) try{conn.close();}catch(SQLException s3){}		
		}
		
		return article;
			
			
	 }// end of updateGetArticle
	
	 public int updateArticle(BoardVO article) {
		 
		 	Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			
			String strQuery ="";
			int result = -1; //게시글 없을때 
		
			try {
			
				conn = ConnUtil.getConnection();
				pstmt = conn.prepareStatement("select * from p_board where num =?");
				pstmt.setInt(1, article.getNum()); //조건이 번호임 번호는 article이 가지고 있음
				rs = pstmt.executeQuery();
				
				
						strQuery = "update p_board set writer=?, subject=?, content=? where num=?";
						pstmt = conn.prepareStatement(strQuery); //쿼리문 실행
						
						pstmt.setString(1, article.getWriter());
						pstmt.setString(2, article.getSubject());
						pstmt.setString(3, article.getContent());
						pstmt.setInt(4, article.getNum());
						
						pstmt.executeUpdate();
						result =1; // 수정 성공
						
					
				
				
				
			}catch (SQLException s1) {
				s1.printStackTrace();
			} finally {
				if(rs != null) try{rs.close();}catch(SQLException s1){}
				if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
				if(conn != null) try{conn.close();}catch(SQLException s3){}		
			}
			
			return result;
			
	 } // end of updateArticle
	 
	 /*
	  * 글 삭제 처리
	  * 글삭제 폼에서 비밀번호를 입력하고 삭제를 수행하면 데이터베이스에서 비밀번호를 검색해서
	  * 비밀 번호가 일치하면 삭제 처리를 수행하고 그렇지 않으면 비밀번호 오류 설정
	  */
	 
	 public int deleteArticle(int num) {
			
		 	Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
			
			int result = -1; 
		
			try {
			
				conn = ConnUtil.getConnection();
				
				pstmt = conn.prepareStatement("select pass from boardone where num =?"); //패스워드를 가져와야된다
				// num을 가지고 온다
//				pstmt.setInt(1, num); 
//				rs = pstmt.executeQuery();
				
			
						
						pstmt = conn.prepareStatement("delete from p_board where num=?");
						
	
						pstmt.setInt(1, num);
						
						pstmt.executeUpdate();
						result =1; // 글 삭제 성공
						
		
				
				
			}catch (SQLException s1) {
				s1.printStackTrace();
			} finally {
				if(rs != null) try{rs.close();}catch(SQLException s1){}
				if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
				if(conn != null) try{conn.close();}catch(SQLException s3){}		
			}
			return result;
	 }
	 
	 //오버로딩 매개변수가 달름 위에꺼랑 조심! 
	 //검색한 내용이 몇개 있는지를 반환하는 기능 (what : 검색조건,  content : 검색내용 )
	 public int getArticleCount(String what, String content) {

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int x =0;
			
			try {
				conn = ConnUtil.getConnection();								// 
				pstmt = conn.prepareStatement("select count(*) from p_board where "+what+" like '%"+content+"%' "); 
				rs = pstmt.executeQuery();
				if(rs.next()) {
					x = rs.getInt(1); 
				}
			
			}catch (SQLException s1) {
				s1.printStackTrace();
			} finally {
				if(rs != null) try{rs.close();}catch(SQLException s1){}
				if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
				if(conn != null) try{conn.close();}catch(SQLException s3){}		
			}
			
			return x;
		}// end of  getArticleCount
	 
	 //오버로딩 매개변수 4
	 //검색한 내용을 리스트로 받아옴 (what:검색조건 content:검색내용, 시작번호, 끝번호 ) 시작번호와 끝번호는 페이지 처리용 
	 	public List<BoardVO> getArticles(String what, String content, int start, int end){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			List<BoardVO> articleList = null; //리턴할 객체 생성
			
			try {
				conn = ConnUtil.getConnection();                
				
				
				
				
				
				//안쪽 쿼리가 서브 바깥쪽 쿼리가 메인
				pstmt = conn.prepareStatement("select * from ( " 
				+ "select rownum rnum, num, writer, subject, "
				+ "regdate, readcount, content from ( "
				+ "select * from p_board where num "+what+" like '%" +content+"%' order by asc)) "
				+ "where rnum >=? and rnum <= ? "); //rnum이시작번호 
				
				//위에 쿼리문에서 바인딩 함수가 2개임
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				
				
				rs = pstmt.executeQuery();
	 			
	 			if(rs.next()) {
	 				articleList = new ArrayList<>(end - start +1);
	 				do {
	 					
	 					BoardVO article = new BoardVO();
	 					
	 					article.setNum(rs.getInt("num"));
	 					article.setWriter(rs.getString("writer"));
	 					
	 					article.setSubject(rs.getString("subject"));
	 					
	 					article.setRegdate(rs.getTimestamp("regdate"));
	 					article.setReadcount(rs.getInt("readcount"));
	 					
	 					article.setContent(rs.getString("content"));
	 			
	 				
	 				
	 					articleList.add(article);
	 				}while(rs.next()); //안에 있으면 계속 반복해서 꺼내라
	 				
	 			}
				
			}catch (SQLException s1) {
				s1.printStackTrace();
			} finally {
				if(rs != null) try{rs.close();}catch(SQLException s1){}
				if(pstmt != null) try{pstmt.close();}catch(SQLException s2){}
				if(conn != null) try{conn.close();}catch(SQLException s3){}		
			}
			
			return articleList;
		} //end of getArticles
}