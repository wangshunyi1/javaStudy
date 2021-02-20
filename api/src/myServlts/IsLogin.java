package myServlts;
import com.wsy.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xpath.internal.functions.Function;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class IsLogin
 */
@WebServlet(description = "判断是否登录", urlPatterns = { "/IsLogin" })
public class IsLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IsLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        Function func = new Function();
        Boolean bool = func.CheckLogin(request);
        JSONObject obj = func.ReturnObj(bool);
        
		
		response.getWriter().append(obj.toString());
	}

}
