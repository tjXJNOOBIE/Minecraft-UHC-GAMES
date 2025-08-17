package dev.tjxjnoobie.uhcgames.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dev.tjxjnoobie.uhcgames.Main;

import org.bukkit.entity.Player;

public class PointManager
{
  public static void firstUpdate(Player player)
  {
    try
    {
      PreparedStatement ps = Main.c.prepareStatement("INSERT INTO `player_stats`(player_name, wins, total, kills) VALUES (?, ?, ?, ?);");
      ps.setString(1, player.getName());
      ps.setInt(2, 0);
      ps.setInt(3, 0);
      ps.setInt(4, 0);
      ps.executeUpdate();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  
  public void firstUpdate3(Player player)
  {
    try
    {
      PreparedStatement ps = Main.c.prepareStatement("INSERT INTO `player_info`(player_uuid, player_points) VALUES (?, ?);");
      ps.setString(1, player.getName());
      ps.setInt(2, 0);
      ps.executeUpdate();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  
  
  
  public int getPoints(Player player)
  {
    firstUpdate3(player);
    firstUpdate(player);
    
    int points = 0;
    try
    {
      Statement statement = Main.c.createStatement();
      
      ResultSet res = statement.executeQuery(
        "SELECT * FROM player_info WHERE player_uuid = '" + player.getName().toString() + "';");
      res.next();
      points = res.getInt("player_points");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return points;
  }
  
  public void setWins(Player player, int amount)
  {
    firstUpdate(player);
    try
    {
      Statement statement = Main.c.createStatement();
      statement.executeUpdate("UPDATE `player_stats` SET `wins`='" + amount + "' WHERE `player_name`='" + player.getName() + "';");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  


  public int getWins(Player player)
  {
    firstUpdate(player);
    
    int points = 0;
    try
    {
      Statement statement = Main.c.createStatement();
      
      ResultSet res = statement.executeQuery("SELECT * FROM player_stats WHERE player_name = '" + player.getName() + "';");
      res.next();
      points = res.getInt("wins");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return points;
  }
  
  public void setTotal(Player player, int amount)
  {
    firstUpdate(player);
    try
    {
      Statement statement = Main.c.createStatement();
      statement.executeUpdate("UPDATE `player_stats` SET `total`='" + amount + "' WHERE `player_name`='" + player.getName() + "';");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  
  public int getTotal(Player player)
  {
    firstUpdate(player);
    
    int points = 0;
    try
    {
      Statement statement = Main.c.createStatement();
      
      ResultSet res = statement.executeQuery("SELECT * FROM player_stats WHERE player_name = '" + player.getName() + "';");
      res.next();
      points = res.getInt("total");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return points;
  }
  
  public void setKills(Player player, int amount)
  {
    firstUpdate(player);
    try
    {
      Statement statement = Main.c.createStatement();
      statement.executeUpdate("UPDATE `player_stats` SET `kills`='" + amount + "' WHERE `player_name`='" + player.getName() + "';");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  
  public int getKills(Player player)
  {
    firstUpdate(player);
    
    int points = 0;
    try
    {
      Statement statement = Main.c.createStatement();
      
      ResultSet res = statement.executeQuery("SELECT * FROM player_stats WHERE player_name = '" + player.getName() + "';");
      res.next();
      points = res.getInt("kills");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return points;
  }
  
  public void setPoints(Player player, int amount)
  {
    firstUpdate3(player);
    firstUpdate(player);
    try
    {
      Statement statement = Main.c.createStatement();
      
      statement.executeUpdate("UPDATE `player_info` SET `player_points`='" + amount + "' WHERE `player_uuid`='" + player.getName() + "';");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  
  public void addPoints(Player player, int amount)
  {
    firstUpdate3(player);
    int newamount = getPoints(player) + amount;
    setPoints(player, newamount);
    Main.getInstance().saveConfig();
  }
  
  public void takePoints(Player player, int amount)
  {
    firstUpdate3(player);
    int newamount = getPoints(player) - amount;
    setPoints(player, newamount);
    Main.getInstance().saveConfig();
  }
  
  public void updatePoints(Player killer, Player died)
  {
    if (getPoints(died) < 100)
    {
      addPoints(killer, 5);
      return;
    }
    if (getPoints(died) >= 100)
    {
      int a = Math.round(getPoints(died) / 20);
      addPoints(killer, a);
      takePoints(died, a);
      return;
    }
  }
  
  public void updatePoints2(Player died)
  {
    if (getPoints(died) < 100)
    {
      takePoints(died, Math.round(getPoints(died) / 20));
      return;
    }
    if (getPoints(died) >= 100)
    {
      int a = Math.round(getPoints(died) / 20);
      takePoints(died, a);
    }
  }
}
