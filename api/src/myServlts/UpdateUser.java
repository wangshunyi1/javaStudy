package myServlts;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wsy.Function;
import com.wsy.User;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		//String str = new String(names.getBytes("ISO-8859-1"),"utf-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		
		Function func = new Function();
		boolean bool = func.CheckLogin(request);
		
		if(bool) {
				String id=request.getParameter("id");
				String userName = request.getParameter("userName");
				String age = request.getParameter("age");
				String gender = request.getParameter("gender");
				String adress = request.getParameter("adress");
				String tel = request.getParameter("tel");
				System.out.println(userName);
				
				
				
				try {
					JSONObject res = user.updateUser(id, userName, age, gender, adress, tel);
					response.getWriter().append(res.toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			
				
		
		}else {
			JSONObject obj = func.ReturnObj(bool);
			response.getWriter().append(obj.toString());
		}
		
	}
	
}
