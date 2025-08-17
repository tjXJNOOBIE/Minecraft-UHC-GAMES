package dev.tjxjnoobie.uhcgames;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class MySQL {
	 public static String username;
	  public static String password;
	  public static String database;
	  public static String host;
	  public static String port;
	  public static String sgusername;
	  public static String sgpassword;
	  public static String sgdatabase;
	  public static String sghost;
	  public static String sgport;
	  public static Connection connection;
	  public static Connection connection2;

	  
	  public MySQL(String user, String pass, String host2, String dB) {}
	  
	  public static void connect() {
	    if (!isConnected()) {
	      try
	      {
	    	  connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, 
	          password);

	        Bukkit.getConsoleSender().sendMessage("�aConnected to sg stats database");
	      }
	      catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	    }
		  public static void connectSG() {
			    if (!isConnectedSG()) {
			      try
			      {

			    	  connection2 = DriverManager.getConnection("jdbc:mysql://" + sghost + ":" + sgport + "/" + sgdatabase, sgusername, 
			    	          sgpassword);
			        Bukkit.getConsoleSender().sendMessage("�aConnected to SG Gamestate database");
			      }
			      catch (SQLException e) {
			        e.printStackTrace();
			      }
			    }
	  }
	  
	  public static void close() {
		  
	    if (isConnected()) {
	      try
	      {
	    	  connection.close();
	        Bukkit.getConsoleSender().sendMessage("�cSafely dissconnected from sg stats database");
	      }
	      catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	  }
	    public static void closeSG() {
			  
		    if (isConnectedSG()) {
		      try
		      {
		    	  connection2.close();
		        Bukkit.getConsoleSender().sendMessage("�cSafely dissconnected from SG GameState database");
		      }
		      catch (SQLException e) {
		        e.printStackTrace();
		      }
		    }
		  }
	  
	  
	  public static boolean isConnected() {
	    return connection != null;
	  }
	  public static boolean isConnectedSG() {
		return connection2 != null;
		  }
	  public static void createGameIDTable() {
	    if (isConnected()) {
	      try
	      {
	    	  connection.createStatement().executeUpdate(
	          "CREATE TABLE IF NOT EXISTS gamestate (SERVERID VARCHAR(20), GAMESTATE int, GAMESPLAYED int)");
	          Bukkit.getConsoleSender().sendMessage("�aGameState table created");

	      }
	      catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	  }
		  public static void createSGStatsTable() {
			  if (isConnectedSG()) {
				  try {
					  connection2.createStatement().executeUpdate(
							  "CREATE TABLE IF NOT EXISTS uhc_stats (UUID VARCHAR(100), KILLS int, DEATHS int, WINS int, LOSSES int, PLAYED int,KILLSTREAK int, POINTS int,KDR NUMERIC(4,2),USERNAME VARCHAR(20))");
					  Bukkit.getConsoleSender().sendMessage("�aSG tables created");
				  } catch (SQLException e) {
					  e.printStackTrace();
				  }
			  }
		  }
			  public static void createWorldManager() {
				  if (isConnectedSG()) {
					  try
					  {
						  connection2.createStatement().executeUpdate(
								  "CREATE TABLE IF NOT EXISTS worlds (WORLD VARCHAR(100), KILLS int, DEATHS int, WINS int, LOSSES int, PLAYED int,KILLSTREAK int, POINTS int,KDR NUMERIC(4,2),USERNAME VARCHAR(20))");
						  Bukkit.getConsoleSender().sendMessage("�aSG tables created");
					  }
					  catch (SQLException e) {
						  e.printStackTrace();
					  }
				  }
			  }

	  
	  
	  public static void update(String qry) {
	    if (isConnected()) {
	      try
	      {
	    	  connection.createStatement().executeUpdate(qry);
	      }
	      catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	  }
		  public static void updateSG(String qry) {
			    if (isConnected()) {
			      try
			      {
			    	  connection2.createStatement().executeUpdate(qry);
			      }
			      catch (SQLException e) {
			        e.printStackTrace();
			      }
			    }
	  }
	  
	  public static ResultSet getResult(String qry) {
	    ResultSet rs = null;
	    try
	    {
	      Statement st = connection.createStatement();

	      rs = st.executeQuery(qry);

	    }
	    catch (SQLException e) {
	      connect();
	      System.err.println(e);
	    }
	    return rs;
	  }
	  public static ResultSet getSecondResult(String qry) {
		    ResultSet rs = null;
		    try
		    {
		      Statement st = connection2.createStatement();

		      rs = st.executeQuery(qry);

		    }
		    catch (SQLException e) {
		      connectSG();
		      System.err.println(e);
		    }
		    return rs;
		  }
	  
	  public static File getRatingFile() {
	    return new File("plugins/Databases", "gamestate_database.yml");
	  }
	  public static File getSgFile() {
		  return new File("plugins/Databases", "stats_database.yml");
	  }
	  
	  public static FileConfiguration getRatingDatabaseFile() {
	    return YamlConfiguration.loadConfiguration(getRatingFile());
	  }
	  public static FileConfiguration getSGDatabaseFile() {
		    return YamlConfiguration.loadConfiguration(getSgFile());
	  }
	  
	  public static void setDefualtRating() {
	    FileConfiguration cfg = getRatingDatabaseFile();
	    cfg.options().copyDefaults(true);
	    cfg.addDefault("username", "root");
	    cfg.addDefault("password", "password");
	    cfg.addDefault("database", "localhost");
	    cfg.addDefault("host", "localhost");
	    cfg.addDefault("port", "3306");
	    try
	    {
	      cfg.save(getRatingFile());
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
		 public static void setSGFile() {
			    FileConfiguration cfg = getSGDatabaseFile();
			    
	    cfg.options().copyDefaults(true);
		cfg.addDefault("username", "root");
		cfg.addDefault("password", "password");
		cfg.addDefault("database", "localhost");
		cfg.addDefault("host", "localhost");
		cfg.addDefault("port", "3306");
		try
		{
			cfg.save(getSgFile());
		 }
		catch (IOException e) {
		e.printStackTrace();
			    }
			  }
	  
	  
	  public static void readRatingFiles() {
	    FileConfiguration cfg = getRatingDatabaseFile();
	    username = cfg.getString("username");
	    password = cfg.getString("password");
	    database = cfg.getString("database");
	    host = cfg.getString("host");
	    port = cfg.getString("port");
	  }
	  
	  public static void readSGFiles() {
		  FileConfiguration cfg = getSGDatabaseFile();
		  sgusername = cfg.getString("username");
		  sgpassword = cfg.getString("password");
		  sgdatabase = cfg.getString("database");
		  sghost = cfg.getString("host");
		  sgport = cfg.getString("port");
	  }
	}


