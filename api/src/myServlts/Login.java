package myServlts;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wsy.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		User user = new User();
		request.setCharacterEncoding("utf-8");
	
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		
		String iden = request.getParameter("iden");
		String password = request.getParameter("password");
		
		
		if(iden!=null&&password!=null) {
		try {
			String s=user.login(iden, password);
			response.getWriter().append(s);
			HttpSession session = request.getSession();
			//session.setMaxInactiveInterval(60*60);	//设置生效时间，单位秒
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			
			response.getWriter().append("账号密码不能为空");
			
		}
		
	
	}
		
		
		
		
	}


