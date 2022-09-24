package com.board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;

// 글 목록을 처리하는 클래스
public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		 int pageSize = 10;

		    //페이지 num을 가져온다
		    String pageNum = request.getParameter("pageNum");
		    
		    if(pageNum == null) {
		    	pageNum = "1";
		    }

		    //현재 페이지 
		    int currentPage =Integer.parseInt(pageNum);
		    
		    int startRow = (currentPage-1) * pageSize + 1; 
		    int endRow = currentPage * pageSize;    
		    int count = 0;
		      int number =0;
		      List<BoardVO> articleList = null;
		      BoardDAO dbPro = BoardDAO.getInstance(); // db연결
		      count = dbPro.getArticleCount();
		   
		       if(count > 0){  // 현재 페이지에 해당하는 글목록
		       //하나라도 존재 하면 리스트를 출력해라
		       articleList = dbPro.getArticles(startRow, endRow);
		    }	       
		 else { // 검색 일 경우		 
		      //하나라도 존재 하면 리스트를 출력해라
		      articleList = Collections.emptyList();  
		}	   
		  // 글 목록에 표시할 글 번호
		    number = count - (currentPage - 1) * pageSize;
		
		    // 해당 뷰에서 사용할 속성 저장
		    request.setAttribute("currentPage",currentPage);
		    request.setAttribute("startRow", startRow);
		    request.setAttribute("endRow", endRow);
		    request.setAttribute("count", count);
		    request.setAttribute("pageSize", pageSize);
		    request.setAttribute("number", number);
		    request.setAttribute("articleList", articleList);
		    
		    // 해당 뷰로 반환해줌
		
		return "/board/list.jsp";
	}
	
	
	
	

}
