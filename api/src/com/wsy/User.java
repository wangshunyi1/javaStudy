package com.wsy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

public class User {

	DBConnection DBConn = new DBConnection(); // 寮曞叆杩炴帴鏁版嵁搴撴柟娉曠被
	Function Fun = new Function(); // 寮曞叆鏁版嵁澶勭悊鏂规硶绫�
	// 鏂伴椈鍒楄〃鏌ヨ鏂规硶

	public String getUserList() {

		try { // 鐢ㄤ簬鑾峰彇绯荤粺杩愯寮傚父淇℃伅

			Connection Conn = DBConn.getConn(); // 寤虹珛鏁版嵁搴撹繛鎺�
			Statement stmt = Conn.createStatement(); // 鍒涘缓鏁版嵁鏌ヨ
			ResultSet rs = null; // 瀹氫箟缁撴灉闆�

			// 鍒涘缓sql璇彞鏌ヨNews琛ㄥ叏閮ㄤ俊鎭�
			String sSql = "select * from userList order by id DESC";
			// 閫氳繃鎵цsql璇彞寰楀埌鏌ヨ缁撴灉
			rs = stmt.executeQuery(sSql);

			JSONObject res = new JSONObject();
			Boolean flag = rs.next();

			if (!flag) { // 鍒ゅ畾褰撴煡璇㈢粨鏋滀负绌�
				res.put("status", 200);
				res.put("desc", "请求成功！");
				res.put("data", null);
				res.put("success", false);
			} else { // 鍒ゅ畾褰撴煡璇㈢粨鏋滄槸鍚︿负绌�

				res.put("status", 200);
				res.put("desc", "请求成功！");

				res.put("success", true);
				ArrayList arr = new ArrayList();
				while (flag) {
					JSONObject obj = new JSONObject();
					obj.put("id", rs.getInt("id"));
					obj.put("userName", rs.getString("userName"));
					obj.put("gender", rs.getString("gender"));
					obj.put("age", rs.getString("age"));
					obj.put("adress", rs.getString("adress"));
					obj.put("tel", rs.getString("tel"));
					obj.put("time", rs.getString("time"));
					arr.add(obj); // 鍒ゅ畾鏄惁瀛樺湪涓嬩竴鏉′俊鎭�
					flag = rs.next();
				}
				res.put("data", arr);
			}
			rs.close(); // 鍏抽棴缁撴灉闆�
			stmt.close(); // 鍏抽棴鏌ヨ
			Conn.close(); // 鍏抽棴鏁版嵁杩炴帴
			return res.toString(); // 杩斿洖瀛楃涓叉暟鎹�
		} catch (Exception e) { // 寰楀埌绯荤粺杩愯寮傚父

			return "No"; // 濡傛灉绯荤粺寮傚父鏂规硶杩斿洖瀛楃鈥淣o鈥�
		}
	}

	public void addUser(String userName, String age, String gender, String adress, String tel, long time)
			throws SQLException {
		Connection Conn = DBConn.getConn();
		Statement stmt = Conn.createStatement();

		String sSql = "INSERT INTO userList (userName, gender, age, adress, tel, time) values ('" + userName + "','"
				+ gender + "'," + age + ",'" + adress + "'," + tel + "," + time + ")";
		int rs = stmt.executeUpdate(sSql);

		System.out.print(rs);

	}

	public JSONObject deleteUser(String id) throws SQLException {

		Connection Conn = DBConn.getConn();

		Statement stmt = Conn.createStatement();

		String sSql = "delete from userList where id =" + id;
		JSONObject res = new JSONObject();
		try {
			stmt.execute(sSql);
			res.put("status", 200);
			res.put("success", true);
			res.put("desc", "删除成功！");
			
		}catch(Exception ex){
			res.put("status", 400);
			res.put("success", false);
			res.put("desc", "删除失败！");
		}
		stmt.close(); // 鍏抽棴鏌ヨ
		Conn.close(); // 鍏抽棴鏁版嵁杩炴帴
		return res;

	}

	public JSONObject updateUser(String id, String userName, String age, String gender, String adress, String tel)
			throws SQLException {

		Connection Conn = DBConn.getConn();

		Statement stmt = Conn.createStatement();
		// String s = null;
		// String sSql = "update userList set "+s+"where id = "+id;
		// 判断userName用户名是否更新
		
		String sSql = "update userList set userName = '" + userName + "',gender='" +gender + "',age="+age+ ",adress='"+adress+"',tel='"+ tel+ "' where id = " + id ;
		System.out.println(sSql);
		JSONObject res = new JSONObject();
		try {
			stmt.execute(sSql);
			res.put("status", 200);
			res.put("desc", "更新成功！");
			res.put("success", true);
		}catch(Exception ex) {
			res.put("status", 200);
			res.put("desc", "更新失败！");
			res.put("success", false);
		}
		stmt.close(); // 鍏抽棴鏌ヨ
		Conn.close(); // 鍏抽棴鏁版嵁杩炴帴	
		return res;
		


	}

	public String signin(String iden, String password) throws SQLException {
		Connection Conn = DBConn.getConn();

		Statement stmt = Conn.createStatement();
		String sSql = "select iden from login ";
		ResultSet rs = stmt.executeQuery(sSql);
		JSONObject res = new JSONObject();
		boolean b = true;
		while (rs.next()) {

			if (rs.getString("iden").equals(iden)) {

				/*
				 * res.put("status", 200); res.put("desc", "请求成功！"); res.put("data", null);
				 * res.put("success", "账户已存在");
				 */
				b = false;
				break;

			}

		}
		System.out.println(b);

		if (b) {
			String sSql1 = "INSERT INTO login (iden, password) values ('" + iden + "','" + password + "')";

			stmt.executeUpdate(sSql1);
			res.put("status", 200);
			res.put("desc", "请求成功！");
			res.put("iden", iden);
			res.put("password", password);
			res.put("success", "账户添加成功");

		} else {
			res.put("status", 200);
			res.put("desc", "请求成功！");
			res.put("data", null);
			res.put("success", "账户已存在");
			
		}

		rs.close();
		stmt.close();
		Conn.close();
		return res.toString();

	}

	public String login(String iden, String password, HttpServletRequest request) throws SQLException {

		Connection Conn = DBConn.getConn();
		JSONObject res = new JSONObject();
		Statement stmt = Conn.createStatement();
		String sSql = "select * from login where iden='" + iden + "' and password=" + password;
		ResultSet rs = stmt.executeQuery(sSql);
		if (rs.next()) {
			res.put("status", 200);
			res.put("desc", "请求成功！");
			res.put("iden",iden);
			res.put("password",password);
			res.put("success", true);
			
			HttpSession session = request.getSession();
			int userId = rs.getInt("userId");
			System.out.print(userId);
			session.setAttribute("personId", userId);
		}else {
			res.put("status", 400);
			res.put("desc", "账号或密码错误！");
			res.put("data",null);
			res.put("success", false);
		}

		
		
		return res.toString();
	}

}