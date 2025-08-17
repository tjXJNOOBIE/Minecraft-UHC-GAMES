package dev.tjxjnoobie.uhcgames.other;



import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.md_5.bungee.api.ChatColor;
import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.MySQL;

import org.bukkit.Bukkit;

public class getServers {
	Main plugin;
	public getServers(Main p){
		plugin = p;

	}	

//This gets all the Servers connected to the Panel and finds there port to find which ones are Online.	
	public static List<String> GetOnlineServerLists(int ServerTypeID) {
		
		List<String> ServerList = new LinkedList<String>(); 

		MySQL.connect();
		
		try {
			Statement statement = MySQL.connection.createStatement();
			ResultSet ServerListSQLRAW = statement.executeQuery("SELECT * FROM Servers ;");
			while (ServerListSQLRAW.next()) {
				  String ServerName = ServerListSQLRAW.getString("ServerName");
				  int ServerPort = ServerListSQLRAW.getInt("ServerPort");
				  
				  if(ServerOnline("us.blimpsg.net", ServerPort)){
					  if(ServerTypeID == 0){
					  if(ServerName.contains("SG")){
						  if(ServerName.contains("CSG")){
							  //Do nothing because it thinks is CSG.wwwwwwwwwwwwwww
						  }else{
							  ServerList.add(ServerName);
						  }
					  }
					  }
					  if(ServerTypeID == 1)
					  if(ServerName.contains("FFA")){
						  ServerList.add(ServerName);
					  }
					  if(ServerTypeID == 2){
					  if(ServerName.contains("CSG")){
						  ServerList.add(ServerName);
					  }
					  }
					  if(ServerTypeID == 3){
					  if(ServerName.contains("UHC")){
						 ServerList.add(ServerName);
					  }
					  }
					  if(ServerTypeID == 4){
						  if(ServerName.contains("UHC")){
							 
						  }else if(ServerName.contains("UHCM")){
							  ServerList.add(ServerName);
						  }
						  }
					  if(ServerTypeID == 20){
						ServerList.add(ServerName);
					  }
					  
					  
				  }
					  
					  
				  
				}
				
			return ServerList;
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug - " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				ServerList.add("DB ERROR - getServers.GetOnlineServerLists() - DEV DATA:" + ConnectionRefusedError);

				
				Bukkit.getLogger().warning("Hey Matt - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
					
				}
		}
		return ServerList;
		
		
			
		
	}
	
	public static int ServerNameToServerPort(String ServerName) {
		MySQL.connect();
		
		try {

			Statement statement = MySQL.connection.createStatement();
			ResultSet ServerListSQLRAW = statement.executeQuery("SELECT * FROM `Servers` WHERE `ServerName`='"+ServerName+"';");
			ServerListSQLRAW.next();
			 return ServerListSQLRAW.getInt("ServerPort");
			 

					  
				  
				
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey Matt - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){

				}
		}
		
		return 25565;
			
		
	}
	
	
	public static int SelectedServerPlayerCount(int ServerPort){
		
		MySQL.connect();
		
		try {

			Statement statement = MySQL.connection.createStatement();
			ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `players_data` WHERE `currentServer`= '"+ServerPort+"';");
			int ServerPlayerPlayer = 0;
			while(ServerSQLRAW.next()){
				ServerPlayerPlayer++;
			}
			if(ServerPlayerPlayer == 0){
				return 1;
			}
			return ServerPlayerPlayer;
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey DEVS - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
					Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED!");
				}
		}
		return 1;
	}
	public static int ServerPlayerCount(String ServerName){
		int ServerNametoPort = ServerNameToServerPort(ServerName);
		return SelectedServerPlayerCount(ServerNametoPort);
	}
	
	public static  List<String> PlayerStatsOnServer(String user , int ServerTypeID){
		
		MySQL.connect();
		
		try {

		  	if(ServerTypeID == 0){
			  //sg
		  		Statement statement = MySQL.connection.createStatement();
				ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `stats` WHERE `USERNAME`= '"+user+"';");
				ServerSQLRAW.next();
				return Arrays.asList(ChatColor.RED + "" + ChatColor.BOLD + "Kills: " + ServerSQLRAW.getString("KILLS") ,ChatColor.RED + "" + ChatColor.BOLD + "Deaths: " + ServerSQLRAW.getString("DEATHS") ,ChatColor.RED + "" + ChatColor.BOLD + "KDR: " + ServerSQLRAW.getString("KDR"));
			  	}
			if(ServerTypeID == 1){	
			//faa	
				Statement statement = MySQL.connection.createStatement();
				ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `ffa_stats` WHERE `USERNAME`= '"+user+"';");
				ServerSQLRAW.next();
				return Arrays.asList(ChatColor.RED + "" + ChatColor.BOLD + "Kills: " + ServerSQLRAW.getString("KILLS") ,ChatColor.RED + "" + ChatColor.BOLD + "Deaths: " + ServerSQLRAW.getString("DEATHS") ,ChatColor.RED + "" + ChatColor.BOLD + "KDR: " + ServerSQLRAW.getString("KDR"));
			  	
				}
			
			if(ServerTypeID == 2){
//				Statement statement = Main.sqlConnection.createStatement();
//				ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `players_data` WHERE `currentServer`= '"+user+"';");
//			//csg
			  	}
			
			
			
			int ServerPlayerPlayer = 0;
			
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey DEVS - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
					Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED!");
				}
		}
		return null;
	}
		
	public static String PlayerTime(String user){
		
		MySQL.connect();
		
		try {

			Statement statement = MySQL.connection.createStatement();
			ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `players_data` WHERE `last_username`= '"+user+"';");
			ServerSQLRAW.next();
			 int seconds = ServerSQLRAW.getInt("playtime");
			 int day = (int)TimeUnit.SECONDS.toDays(seconds);        
			 long hours = TimeUnit.SECONDS.toHours(seconds) - (day *24);
			 long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
			 long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);

			 return day + " : " + hours + " : " + minute + " : " + second;
			 
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey DEVS - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
					Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED!");
				}
		}

		
		return "";
	}
	
	
	public static int PlayerTokens(String user){
		
		MySQL.connect();
		
		try {

			Statement statement = MySQL.connection.createStatement();
			ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `players_data` WHERE `last_username`= '"+user+"';");
			ServerSQLRAW.next();
			int seconds = ServerSQLRAW.getInt("tokens");
			 
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey DEVS - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
					Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED!");
				}
		}

		
		return 0;
	}
		
	
	
	
	public static boolean ServerOnline(String ServerIP, int ServerPort){
		if(ServerIP == null){
			ServerIP = "blimpsg.net";
		}
	SocketAddress sockaddr = new InetSocketAddress(ServerIP, ServerPort);
	// Create your socket
	Socket socket = new Socket();
	boolean online = true;
	// Connect with 10 s timeout
	try {
	    socket.connect(sockaddr, 500);
	} catch (SocketTimeoutException stex) {
	    // treating timeout errors separately from other io exceptions
	    // may make sense
	    online=false;
	} catch (IOException iOException) {
	    online = false;    
	} finally {
	    // As the close() operation can also throw an IOException
	    // it must caught here
	    try {
	        socket.close();
	    } catch (IOException ex) {
	        // feel free to do something moderately useful here, eg log the event
	    }

	}
	// Now, in your initial version all kinds of exceptions were swallowed by
	// that "catch (Exception e)".  You also need to handle the IOException
	// exec() could throw:
	if(!online){
		return false;
	}else{      
		Bukkit.getLogger().info("Server " + ServerPort + " is " + online + " to being online.");
	}
	return true;
	}
	
}