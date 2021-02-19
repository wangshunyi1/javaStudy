package myServlts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wsy.User;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/addUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		

		User user = new User();
		request.setCharacterEncoding("utf-8");
		

		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		
		HttpSession session = request.getSession(false);
		
		if(session!=null) {
		String userName = request.getParameter("userName");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String adress = request.getParameter("adress");
		String tel = request.getParameter("tel");
		long time = System.currentTimeMillis();
	
		
		if(userName!=null&&age!=null&&gender!=null&&adress!=null&&tel!=null) {
			
			try {
				user.addUser(userName, age, gender, adress, tel, time);

				JSONObject res = new JSONObject();
				res.put("status", 200);
				res.put("success", true);
				res.put("desc", "添加成功");
				response.getWriter().append(res.toString());
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		
		
		else{
			
			
			JSONObject res = new JSONObject();
			res.put("status", 200);
			res.put("success", false);
			res.put("desc", "添加失败,请完善用户信息！");
			response.getWriter().append(res.toString());
			
			
		}
		
		
		
		
			
		/*	try {
			
				
				if(userName!=null&&age!=null&&gender!=null&&adress!=null&&tel!=null) {
				user.addUser(userName, age, gender, adress, tel, time);
				
				
				JSONObject res = new JSONObject();
				res.put("status", 200);
				res.put("success", true);
				res.put("desc", "添加成功");
				response.getWriter().append(res.toString());

				}
			
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JSONObject res = new JSONObject();
				res.put("status", 200);
				res.put("success", false);
				res.put("desc", "添加失败");
				response.getWriter().append(res.toString());
			
			}
			
		
		}else{// TODO Auto-generated catch block
			JSONObject res = new JSONObject();
			res.put("status", 200);
			res.put("success", false);
			res.put("desc", "添加失败");
			response.getWriter().append(res.toString());
		

		}

	*/

}
}
	
	
}