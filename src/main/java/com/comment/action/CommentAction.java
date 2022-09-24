package com.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommentAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable;
}
