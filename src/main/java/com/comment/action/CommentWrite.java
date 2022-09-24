package com.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentWrite implements CommentAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		
		int comment_num = 0, ref = 1, step = 0, depth = 0;
		try{
			if(request.getParameter("num")!=null){
				comment_num = Integer.parseInt(request.getParameter("num"));
				ref = Integer.parseInt(request.getParameter("ref"));
				step = Integer.parseInt(request.getParameter("step"));
				depth = Integer.parseInt(request.getParameter("depth"));
				}
			}catch(Exception e){}
				
				// 해당 뷰에서 사용할 속성
				
				 	request.setAttribute("comment_num",new Integer(comment_num));
				    request.setAttribute("ref", new Integer(ref));
				    request.setAttribute("step", new Integer(step));
				    request.setAttribute("depth", new Integer(depth));
		
		
		
		
		
		
		return "/board/content.jsp";
	}

}
