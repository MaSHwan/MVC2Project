package mvc;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;




public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 명령어와 명령어 처리 클래스를 쌍으로 불러다가 저장함
	private Map<String, Object> commandMap = new HashMap<String, Object>();
	
	// 명령어와 처리 클래스가 맵핑되어있는 Command.properties 파일을 읽어와서
	// Map 객체인 commandMap에 저장한다.

	public void init(ServletConfig config) throws ServletException {
		// web.xml 파일에서 propertyConfig에 해당하는 init-param의 값을 읽어옴
		String props = config.getInitParameter("propertyConfig");
		
		// 명령어와 처리클래스의 맵핑정보를 저장할 Properties 객체를 생성
		Properties pr = new Properties();
		
		String path = config.getServletContext().getRealPath("/WEB-INF");
		FileInputStream f = null;
		
		try {
			// Command.properties 내용을 읽어옴
			f = new FileInputStream(new File(path, props));
			// Command.properties 파일의 정보를 Properties 객체에 저장함
			pr.load(f);
		}catch(IOException e) {
			throw new ServletException(e);
		}finally {
			if(f != null) try {f.close();}catch(IOException ex) {}
		}
		// Iterator 객체를 이용해서 파일 내용의 키값을 읽어옴
		// Iterator 객체를 생성
		Iterator<Object> keyIter = pr.keySet().iterator(); // 순서가 없고 중복허용을 안함
		
		while(keyIter.hasNext()){
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			
			try {
				Class commandClass = Class.forName(className);
				Object commandInstance = commandClass.newInstance();
				// 해당 클래스의 객체를 생성함
				
				// Map 객체인 commandMap 에 객체를 저장함
				commandMap.put(command, commandInstance);
			}catch(ClassNotFoundException e) {
				throw new ServletException(e);
			}catch(InstantiationException e) {
				throw new ServletException(e);
			}catch(IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}


	// get 방식
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
		
	}


	// post 방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
		
	}
	
	// 사용자의 요청을 분석해서 해당작업을 처리하는 기능을 구현
	protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = null;
		CommandProcess com = null;
		try {
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath())==0) {
				command = command.substring(request.getContextPath().length());
			}
			com = (CommandProcess)commandMap.get(command);
			view = com.requestPro(request, response);
		}catch(Throwable e) {
			throw new ServletException(e);
		}
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
		
	}

}
