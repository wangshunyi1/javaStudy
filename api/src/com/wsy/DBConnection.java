package com.wsy;			//鎸囧畾绫绘墍鍦ㄧ殑鍖�

import java.sql.*;						//瀵煎叆鏁版嵁搴撴搷浣滅殑绫�
import java.util.*;
import java.io.*;



public class DBConnection					
{

    private String FileName;			//閰嶇疆鏂囦欢鍚�
    private int DBType;					//鏁版嵁搴撶被鍨�
    private Connection conn;			//杩炴帴瀵硅薄
    private String MySqlDriver;			//MYSQL Server椹卞姩绋嬪簭
    private String MySqlURL; 			//MYSQL Server杩炴帴瀛楃涓�
        
   
    public DBConnection()
    {
    	conn = null;
    }

	public  Connection getConn()
	{

		DBType= new Function().StrToInt(getPara("DBType"));
		switch(DBType)
		{
			case 1:return(getConnToMySql());
			default:return null;
		}	
	}
	
	
	public String getPara(String ParaName) 
	{
		FileName="../DBConfig.property";
		Properties prop= new Properties();
		try
		{
			InputStream is=getClass().getResourceAsStream(FileName);
			prop.load(is);
			if(is!=null) is.close();
		}
		catch(Exception e) {
			return "Error!";
		}
		return prop.getProperty(ParaName);
	}
	
	
    public Connection getConnToMySql()
    {
		try{
	 		MySqlDriver = getPara("MySQLDriver");	
	    	MySqlURL = getPara("MySQLURL");
	    	System.out.println(MySqlDriver);
	    	Class.forName(MySqlDriver).newInstance();
	    	conn = DriverManager.getConnection(MySqlURL);
	    	}catch(Exception e){
	    		e.printStackTrace();
		    	//return "鎿嶄綔鏁版嵁搴撳嚭閿欙紝璇蜂粩缁嗘鏌�" ;
		    	System.err.println(e.getMessage());
	    	}
	    return conn;
    }       
    
}
