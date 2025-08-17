package dev.tjxjnoobie.uhcgames;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;



public class GameIDSQLChecker {
	
	
	
	
	  public static boolean gameExist(String uuid) {
		    try {
		    	
		      ResultSet rs = MySQL.getSecondResult("SELECT * FROM gamestate WHERE SERVERID='" + uuid + "'");
		      if (rs.next()) {
		        return rs.getString("SERVERID") != null;
		      }
		      return false;
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return false;
		  }
	  public static Integer getState(String serverid) {
		    Integer i = Integer.valueOf(0);
		      try {
		    	  
		        ResultSet rs = MySQL.getSecondResult("SELECT * FROM gamestate WHERE SERVERID='" + serverid + "'");
		        if ((rs.next()) && (Integer.valueOf(rs.getInt("GAMESTATE")) == null)) {}
		        i = Integer.valueOf(rs.getInt("GAMESTATE"));
		      }
		      catch (SQLException e) {
		        e.printStackTrace();
		      }
		    

		    
		    return i;
		  }

	  public static String getServerGameID(){
		    try {
		    	String id = Bukkit.getServer().getServerId();
		      ResultSet rs = MySQL.getResult("SELECT * FROM gamestate WHERE SERVERID='" + id + "'");
		      if (rs.next()) {
		        return rs.getString(id);
		      }
		      return id;
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
			return "Error returning game state ID (Is it set in server.properties?)";
		  }
	
	  
	  public static void createServerID(String gameID) {
		      MySQL.update("INSERT INTO gamestate (SERVERID, GAMESTATE, GAMESPLAYED) VALUES ('" + gameID + "',0,0);");
	  }

	
		
			   
			   public static String setServerID(String string) {
				   string = Bukkit.getServer().getServerId();
				   MySQL.update("UPDATE gamestate SET SERVERID='" + string + "'" );
				return string;
			   }
			   public static int setGameState(int state, String gameid) {
			   MySQL.updateSG("UPDATE gamestate SET GAMESTATE='" + state + "' WHERE SERVERID='" + gameid + "'");
			   return state;
			   }

	
			  public static int isIngame(String id) {
			      ResultSet rs = MySQL.getResult("SELECT * FROM gamestate WHERE SERVERID='" + id + "'");
			        int i = 0;
					try {
						i = rs.getInt("INGAME");
					      if (rs.next()) {
					    	  
						        return rs.getInt(i);
						      }
					} catch (SQLException e) {
						e.printStackTrace();
					}
		
			      return i;
			    }

			  
}
			   
			