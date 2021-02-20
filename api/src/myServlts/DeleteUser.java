package myServlts;
import com.wsy.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wsy.User;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		User user=new User();
		request.setCharacterEncoding("utf-8");
		
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		String id=request.getParameter("id");
		Function func = new Function();
		boolean bool = func.CheckLogin(request);
		if(bool) {
		
			try {
				JSONObject obj = user.deleteUser(id);
				response.getWriter().append(obj.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		
		
		}else {
			JSONObject obj = func.ReturnObj(bool);
			response.getWriter().append(obj.toString());
		}
	}

}
