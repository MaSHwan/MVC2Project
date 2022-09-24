package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		// 매개변수들을 db객체들에 넘겨준다?
		
			BoardDAO dbPro = BoardDAO.getInstance();
			BoardVO article = dbPro.getArticle(num);

			request.setAttribute("num", num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("article", article);

		

		return "/board/content.jsp";

	}
}
