package dev.tjxjnoobie.uhcgames.other;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import net.md_5.bungee.api.ChatColor;
import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.MySQL;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Inventories {
	public static class getServers {
		Main plugin;
		public getServers(Main p){
			plugin = p;
		}	
	public static boolean ServerOnline(String ServerIP, int ServerPort){
		if(ServerIP == null){
			ServerIP = "sonder.network";
		}
		return false;
		}
	public static List<String> GetOnlineServerLists(int ServerTypeID) {
		
		List<String> ServerList = new LinkedList<String>(); 

		MySQL.connect();
		
		try {
			Statement statement  = MySQL.connection.createStatement();
			ResultSet ServerListSQLRAW = statement.executeQuery("SELECT * FROM Servers ;");
			while (ServerListSQLRAW.next()) {
				  String ServerName = ServerListSQLRAW.getString("ServerName");
				  int ServerPort = ServerListSQLRAW.getInt("ServerPort");
				  
				  if(ServerOnline("sonder.network", ServerPort)){
					  if(ServerTypeID == 0){
					  if(ServerName.contains("SG")){
						  if(ServerName.contains("CSG")){
							  //Do nothing because it thinks is CSG.
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

	public final static Material CurrentGameStateMaterial(String ServerName){
		MySQL.connect();
		try {

			Statement statement = MySQL.connection.createStatement();
			ResultSet GameStateSQLRAW = statement.executeQuery("SELECT * FROM `gamestate` WHERE `SERVERID`='"+ServerName+"';");
			GameStateSQLRAW.next();
		 	Short CurrentGameState = GameStateSQLRAW.getShort("GAMESTATE");
		 	if(CurrentGameState == 0){
		 		return Material.EMERALD_BLOCK;
		 	}
		 	if(CurrentGameState == 1){
		 		return Material.GOLD_BLOCK;
		 	}
		 	if(CurrentGameState == 2){
		 		return Material.REDSTONE_BLOCK;
		 	}
		 	
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey DEVS - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
					Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED!");
				}
		}
		return Material.REDSTONE_TORCH_ON;
	}


	public final static String CurrentGameState(String ServerName){
		MySQL.connect();
		
		try {

			Statement statement = MySQL.connection.createStatement();
			ResultSet GameStateSQLRAW = statement.executeQuery("SELECT * FROM `gamestate` WHERE `SERVERID`='"+ServerName+"';");
			GameStateSQLRAW.next();
		 	Short CurrentGameState = GameStateSQLRAW.getShort("GAMESTATE");
		 	if(CurrentGameState == 0){
		 		return ChatColor.GREEN + "WAITING FOR PLAYERS";
		 	}
		 	if(CurrentGameState == 1){
		 		return ChatColor.YELLOW + "GAME IN PROGRESS";
		 	}
		 	if(CurrentGameState == 2){
		 		return ChatColor.RED + "RESTARTING GAME";
		 	}
		 	
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey DEVS - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
					Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED!");
				
				}
		}
		return ChatColor.RED + "" + ChatColor.BOLD + "GAMESTATE UNKNOWN";
	}
	public static int ServerPlayerCount(String ServerName){
		int ServerNametoPort = ServerNameToServerPort(ServerName);
		return SelectedServerPlayerCount(ServerNametoPort);
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

	public static ItemStack OfflineServerItem(){
		ItemStack OfflineServerItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0, (byte) 11);
		ItemMeta OfflineServerItemMeta = OfflineServerItem.getItemMeta();
		OfflineServerItemMeta.setDisplayName(ChatColor.MAGIC + "00000000");
		OfflineServerItemMeta.setLore(null);
		OfflineServerItem.setItemMeta(OfflineServerItemMeta);
		
		return OfflineServerItem;
	}
	
public static void OpenSGGUI(Player user){
		
		Inventory SGINV = Bukkit.createInventory(null , 27 , ChatColor.RED + "" + ChatColor.BOLD + "UHC Games (UHCSG)");
		 for (int i = 0; i < 27; i++)
	        {  
			 SGINV.setItem(i, OfflineServerItem());
	        }
		 Short CurrentServerOn = 0; //What block do you wanna start at?
		 for(String SelectedServer: getServers.GetOnlineServerLists(0)){
			 	
			 
				ItemStack Server = new ItemStack(CurrentGameStateMaterial(SelectedServer), getServers.ServerPlayerCount(SelectedServer));
				ItemMeta ServerMeta = Server.getItemMeta();
				ServerMeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + SelectedServer);
				ServerMeta.setLore(Arrays.asList(CurrentGameState(SelectedServer) , "Players: " + getServers.ServerPlayerCount(SelectedServer)));
				Server.setItemMeta(ServerMeta);
				
				SGINV.setItem(CurrentServerOn, Server);
				CurrentServerOn++;
		 }
		user.openInventory(SGINV);
	}
	}
}



