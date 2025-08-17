package dev.tjxjnoobie.uhcgames;

import info.techwizmatt.ServerCore.Rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class Disguise {
	public static String dnames;

	public static Integer getDisguised(String uuid) {
		Integer i = Integer.valueOf(0);
		try {

			ResultSet rs = MySQL.getResult("SELECT * FROM players WHERE UUID='" + uuid + "'");
			if ((rs.next()) && (Integer.valueOf(rs.getInt("DISGUISED")) != null)) {}
			i = Integer.valueOf(rs.getInt("DISGUISED"));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return i;
	}
	public static String getDisguiseName(String uuid) {
		try {
			ResultSet rs = MySQL.getResult("SELECT * FROM players WHERE UUID = '" + uuid + "';");
			if (rs.next()) {
				return rs.getString("DISGUISEDNAME").replaceAll("c:", "");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return "Error whlist updating";
	}

	public static void setDisguised(int i, String uuid) {
		MySQL.update("UPDATE players SET DISGUISED='" + i+ "' WHERE UUID='" + uuid + "'");
	}

	public static boolean isDisguised(Player player) {
		String playeruuid = player.getUniqueId().toString();
		if(getDisguised(playeruuid) == 1) {
			return true;
		}
		if(getDisguised(playeruuid) == 0) {
			return false;
		}
		return false;
	}


	public static void setDisguiseName(Player player, String uuid) {
		List<String> d = getDisguiseFileConfiguration().getStringList("d-names");

		Random random = new Random();
		int index = random.nextInt(d.size());
		d.set(100, "d-names");
		MySQL.update("UPDATE players SET DISGUISEDNAME='" + ChatColor.BLUE +d.get(index) + "' WHERE UUID='" + uuid + "'");
		player.sendMessage(Main.getInstance().prefix + "Your disguise name is now " + getDisguiseName(uuid));
		player.sendMessage(Main.getInstance().prefix + "To undisguise use " + ChatColor.YELLOW + "/ud");
		player.setDisplayName(getDisguiseName(uuid));
		player.setPlayerListName(getDisguiseName(uuid));
		player.setCustomName(getDisguiseName(uuid));
		player.setCustomNameVisible(true);
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
				"odis " + player.getName() + " player " + getDisguiseName(uuid).replaceAll("§3", ""));
		return;

	}

	public static void removeDisguiseName(Player player, String uuid) {
		MySQL.update("UPDATE players SET DISGUISEDNAME='" + player.getName() + "' WHERE UUID='" + uuid + "'");
		player.sendMessage(Main.getInstance().prefix + "You have been undisguised");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
				"odis " + player.getName() + " player " + player.getName());

		if(Rank.getRankLevel(player)==7) {
			player.setDisplayName(Rank.getDisplayColor(7)+player.getName());


			return;
		}
		if(Rank.getRankLevel(player)==12) {
			player.setDisplayName(Rank.getDisplayColor(12)+player.getName());

			return;
		}
		if(Rank.getRankLevel(player)==10) {
			player.setDisplayName(Rank.getDisplayColor(10)+player.getName());

			return;
		}
		if(Rank.getRankLevel(player)==9) {
			player.setDisplayName(Rank.getDisplayColor(9)+player.getName());

			return;
		}
		if(Rank.getRankLevel(player)==8) {
			player.setDisplayName(Rank.getDisplayColor(8)+player.getName());

			return;
		}
		if(Rank.getRankLevel(player)==4) {
			player.setDisplayName(Rank.getDisplayColor(4)+player.getName());

			return;
		}
		if(Rank.getRankLevel(player)==6) {
			player.setDisplayName(Rank.getDisplayColor(6)+player.getName());

			return;
		}
		if(Rank.getRankLevel(player)==5) {
			player.setDisplayName(Rank.getDisplayColor(5)+player.getName());

			return;
		}
		if(Rank.getRankLevel(player)==3) {
			player.setDisplayName(Rank.getDisplayColor(3)+player.getName());

			return;
		}
		if(Rank.getRankLevel(player)==1) {
			player.setDisplayName(Rank.getDisplayColor(1)+player.getName());

			return;
		}
		if(Rank.getRankLevel(player)==2) {
			player.setDisplayName(Rank.getDisplayColor(2)+player.getName());

			return;
		}
		if(Rank.getRankLevel(player)==11) {
			player.setDisplayName(Rank.getDisplayColor(11)+player.getName());
			return;


		}
	}
	// ** START FILE CONFIGURATION  ** \\
	public static File getDisguiseFile() {
		return new File("plugins/BlimpCore", "dnames.yml");
	}

	public static FileConfiguration getDisguiseFileConfiguration() {
		return YamlConfiguration.loadConfiguration(getDisguiseFile());
	}

	public static void setStandardDisguise() {
		FileConfiguration cfg = getDisguiseFileConfiguration();
		cfg.options().copyDefaults(true);
		cfg.addDefault("d-names", "f");
	}
	public static void readDisguise() {
		FileConfiguration cfg = getDisguiseFileConfiguration();
		dnames = cfg.getString("d-names");
	}

	// ** END FILE CONFIGURATION ** \\
	public static boolean playerExists(String uuid) {
		try {

			ResultSet rs = MySQL.getSecondResult("SELECT * FROM players WHERE UUID='" + uuid + "'");
			if (rs.next()) {
				return rs.getString("UUID") != null;
			}
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean playerDoesntExists(String uuid) {
		try {

			ResultSet rs = MySQL.getSecondResult("SELECT * FROM players WHERE UUID='" + uuid + "'");
			if (rs.next()) {
				return rs.getString("UUID") == null;
			}
			return true;
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static void createPlayer(String username, String uuid) {
		Player p = Bukkit.getPlayer(username);
		String playeruuid = p.getUniqueId().toString();
		if (playerDoesntExists(playeruuid) == true){
			MySQL.updateSG("INSERT INTO players (UUID, DISGUISED, DISGUISEDNAME, SILENTJOIN) VALUES ('" + uuid + "', 0, 'none', 0);");
		} else{
			if(playerExists(playeruuid) == true) {
				return;
			}
		}


	}

}

