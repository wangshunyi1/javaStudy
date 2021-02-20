package com.wsy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

public class Function {
	DBConnection DBConn = new DBConnection();
	
	public boolean CheckLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null) {
			return true;
		}else {
			
			return false;
		}
	}
	
	public JSONObject ReturnObj (Boolean flag) {
		JSONObject obj = new JSONObject();
		if (flag) {
			obj.put("status", 200);
			obj.put("desc", "已登录！");
			obj.put("data", null);
			obj.put("success", true);
		}else {
			obj.put("status", 401);
			obj.put("desc", "登录失效，请重新登录！");
			obj.put("data", null);
			obj.put("success", false);
		}
		return obj;
	}

	
}