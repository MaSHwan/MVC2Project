package com.board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;

public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		// 单捞磐 后 贸府
		
		// 按眉 积己 ( jsp 颇老狼 property)
		BoardVO article = new BoardVO();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));	
		article.setSubject(request.getParameter("subject"));
		article.setRegdate(new Timestamp(System.currentTimeMillis()));
		article.setContent(request.getParameter("content"));
		
		BoardDAO dbPro = BoardDAO.getInstance();
		dbPro.insertArticle(article);
		
		return "/board/writeProc.jsp";
	}

}
