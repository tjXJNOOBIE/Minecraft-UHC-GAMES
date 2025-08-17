package dev.tjxjnoobie.uhcgames;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SilentJoin {
	
	
	
	
	
	
	   public static Integer isSilentJoined(String uuid) {
    	   Integer i = Integer.valueOf(0);
    	      try {
    	    	  
    	        ResultSet rs = MySQL.getResult("SELECT * FROM players WHERE UUID='" + uuid + "'");
    	        if ((rs.next()) && (Integer.valueOf(rs.getInt("SILENTJOIN")) != null)) {}
    	        i = Integer.valueOf(rs.getInt("SILENTJOIN"));
    	      }
    	      catch (SQLException e) {
    	        e.printStackTrace();
    	      }
    	    
    	    return i;
    	  }

	   public static int setSilentJoin(int silentjoin, String uuid) {
	          MySQL.update("UPDATE players SET SILENTJOIN='" + silentjoin + "'" + "WHERE UUID='"+  uuid + "'");
	        return silentjoin;
	        }
	   
	   
	   public static boolean canSilentJoin(String uuid) {
		   if(isSilentJoined(uuid) == 1) {
			   return true;
		   }
		   if(isSilentJoined(uuid) == 0) {
			   return false;
		   }
		return false;
		   
	   }

}
