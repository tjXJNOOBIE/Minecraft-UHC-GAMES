package dev.tjxjnoobie.uhcgames.other;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.UUID;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.MySQL;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;



//This is used for updating alot of Information into the Database.
public class stats implements Listener {

	Main plugin;
	public stats(Main p){
		plugin= p;
		p.getServer().getPluginManager().registerEvents(this, Main.getInstance());
	}	
	
	
	public static boolean PlayerJoinedBefore(Player user){
		
		try {
			ResultSet res = MySQL.getResult("SELECT * FROM players_data WHERE UUID = '" + user.getUniqueId() + "';");
			if(res.next()){
				return true;
			}else{ 
				return false;
			}
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey Server - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
					Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED. CANT CALL METHOD AGAIN. - kicking player.");
					user.kickPlayer("UNIXITY ERROR: CONNECTION REFUSED. (rejoin to try again)");
				}
		}
		return false;
	}


public static void CreatePlayerData(Player user){
	
	UUID PlayerUUID = user.getUniqueId();
	String Username = user.getName();
	int ServerPort = Bukkit.getServer().getPort();
	MySQL.update("INSERT INTO `players_data`(`UUID`,`last_username` ,`currentServer`, `last_ip` ) VALUES ('" + PlayerUUID + "','"+ Username +"','"+ ServerPort +"','"+  GetPlayerIpAddress(user)  +  "');");
	
}
	
public static void PlayerJoinUpdate(Player user){
	
	UUID PlayerUUID = user.getUniqueId();
	String Username = user.getName();
	int ServerPort = Bukkit.getServer().getPort();
	Bukkit.getLogger().info("UUID OF PLAYER: " + PlayerUUID + " Server PORT: " + ServerPort);
	MySQL.update("UPDATE `players_data` SET `online`=1,`currentServer`="+ServerPort+",`last_username`='"+Username+"',`last_ip`='"+ GetPlayerIpAddress(user) +"' WHERE `UUID`='" + PlayerUUID +"';");
	
}


public static void PlayerQuitUpdate(Player user){
	
	Bukkit.getLogger().info(user.getName() + " Has left.");
	UUID PlayerUUID = user.getUniqueId();
	MySQL.update("UPDATE `players_data` SET `online`=0,`currentServer`=0 WHERE `UUID`='" + PlayerUUID +"';");
	
}

public static String GetPlayerIpAddress(Player user){
	String UserAddress = "" + user.getAddress();
	UserAddress = UserAddress.split(":")[0];
	UserAddress = UserAddress.split("/")[1];
	return UserAddress;
}

public static void GetPlayerList() {
	
	
	try {

		ResultSet PlayerListSQLRAW = MySQL.getResult("SELECT `UUID`, `online`, `last_username`, `rankLevel` FROM players_data ;");
		while (PlayerListSQLRAW.next()) {
			  int OnlineCheck = PlayerListSQLRAW.getInt("online");
			  if(OnlineCheck == 1){
				  String UUIDQ = PlayerListSQLRAW.getString("UUID");
				  String LastPlayerName = PlayerListSQLRAW.getString("last_username");
				  Main.OnlinePlayerListUUID.add(UUID.fromString(UUIDQ));
				  Main.OnlinePlayerListNAME.add(LastPlayerName);
				  
//				  if(PlayerListSQLRAW.getInt("rankLevel") >= 7 ){
//					  Main.OnlinePlayerListReportSender.add(LastPlayerName);
//				  }
			
//			Convert to how BlimpRuns ranking.	  
				  
				  
				  
			  }
			}
		
	} catch (SQLException error) {
		Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
		String ConnectionRefusedError = "" + error;
			
			ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
			
			
			Bukkit.getLogger().warning("Hey Devs: - " + ConnectionRefusedError);
			
			if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
				Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED. TRYING METHOD AGAIN.");
				GetPlayerList();
			}
	}
	
	
		
	
}

public static void ResetPlayerList(){
	Main.OnlinePlayerListUUID = new LinkedList<UUID>();
	Main.OnlinePlayerListNAME = new LinkedList<String>();
	Main.OnlinePlayerListReportSender = new LinkedList<String>();
	Bukkit.getLogger().info("Playerlist emptied.");
}

public static void SetRankInt(Player user , int RankLevel){
	
	if(RankLevel > 12){
		RankLevel = 12;
	}
	
	String PlayerUUID = user.getUniqueId().toString();
	MySQL.update("UPDATE `players_data` SET `rank`="+RankLevel+" WHERE `UUID`='" + PlayerUUID +"';");
	
}


public static String UUIDfromPlayerName(String PlayerName){
	
	
	try {
		
		ResultSet res = MySQL.getResult("SELECT * FROM players_data WHERE last_username = '" + PlayerName + "';");
		if(res.next()){
			return res.getString("UUID");
		}else{ 
			return "";
		}
		
		
		
	} catch (SQLException error) {
		Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
		String ConnectionRefusedError = "" + error;
			
			ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
			
			
			Bukkit.getLogger().warning("Hey Matt - " + ConnectionRefusedError);
			
			if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
				Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED. CANT CALL METHOD AGAIN. - kicking player.");
			}
	}
			
			return "";
	
				
			}



}


