package dev.tjxjnoobie.uhcgames.other;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import dev.tjxjnoobie.uhcgames.Main;

import dev.tjxjnoobie.uhcgames.managers.Arena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class SGUtils {

	
	public String getTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM HH:mm");
		return sdf.format(cal.getTime());
	}

	public String getDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
		return sdf.format(cal.getTime());
	}

	public String getAdvancedTime() {
		TimeZone timeZone = Calendar.getInstance().getTimeZone();

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(cal.getTime()) + " " + timeZone.getDisplayName(false, 0);
	}



	public void newBoard(Player player) {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		final Objective obj = board.registerNewObjective("main", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLiveGame &c30:00"));
		Score time = obj
				.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD+ "" + ChatColor.BOLD + "§" + ChatColor.WHITE + " Time"));
		time.setScore(9);

		Score blank2 = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY + "         "));
		blank2.setScore(6);

		Score server = obj
				.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "" + ChatColor.BOLD + "§" + ChatColor.WHITE + " Server"));
		server.setScore(5);

		Score blank1 = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY + "            "));
		blank1.setScore(3);

		Score playerstag = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "" +  ChatColor.BOLD + "§ Players"));
		playerstag.setScore(2);

	    Team Playing = board.registerNewTeam("playing");
	    Playing.addEntry(ChatColor.WHITE.toString() + "Playing: " + ChatColor.WHITE);
	    obj.getScore(ChatColor.WHITE.toString() + "Playing: " + ChatColor.WHITE).setScore(1);

		final Team date = board.registerNewTeam("date");
		date.addEntry(ChatColor.WHITE.toString());
		obj.getScore(ChatColor.WHITE.toString()).setScore(8);

		final Team advtime = board.registerNewTeam("advtime");
		advtime.addEntry(ChatColor.GREEN.toString() + ChatColor.WHITE);
		obj.getScore(ChatColor.GREEN.toString() + ChatColor.WHITE).setScore(7);

		Team GlobalOnline = board.registerNewTeam("globalonline");
		GlobalOnline.addEntry(ChatColor.DARK_AQUA.toString() + "US" + " " + ChatColor.WHITE + "1");
		obj.getScore(ChatColor.DARK_AQUA.toString() + "US" + " " + ChatColor.WHITE).setScore(4);

		player.setScoreboard(board);

		new BukkitRunnable() {
			public void run() {
				date.setSuffix(getDate());
				advtime.setSuffix(getAdvancedTime());

				obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLobby"));
			}
		}.runTaskTimer(Main.getInstance(), 5L, 5L);
	}

	public static String splitChar = ":";

	public static String serialize(Location loc) {
		return loc.getWorld().getName()
				+splitChar+loc.getX()+splitChar+loc.getY()+splitChar+loc.getZ();
	}

	public static String serializeSimple(Location loc) {
		return loc.getWorld().getName()+splitChar+
				loc.getBlockX()+splitChar+loc.getBlockY()+splitChar+loc.getBlockZ();
	}

	public static String serializeFully(Location loc) {
		return serialize(loc)+splitChar+loc.getYaw()+splitChar+loc.getPitch();
	}


	public static Location deserialize(String str) {
		Location loc = new Location(null, 0, 0, 0);
		String[] split = str.split(splitChar);
		loc.setWorld(Bukkit.getWorld(split[0]));
		loc.setX(Double.parseDouble(split[1]));
		loc.setY(Double.parseDouble(split[2]));
		loc.setZ(Double.parseDouble(split[3]));
		return loc;
	}

	public static Location deserializeSimple(String str) {
		Location loc = new Location(null, 0, 0, 0);
		String[] split = str.split(splitChar);
		loc.setWorld(Bukkit.getWorld(split[0]));
		loc.setX((int) Double.parseDouble(split[1]));
		loc.setY((int) Double.parseDouble(split[2]));
		loc.setZ((int) Double.parseDouble(split[3]));
		return loc;
	}

	public static Location deserializeFully(String str) {
		Location loc = deserialize(str);
		String[] split = str.split(splitChar);
		loc.setYaw(Float.parseFloat(split[4]));
		loc.setPitch(Float.parseFloat(split[5]));
		return loc;
	}
	public static void AddGoldenHead(){
		ItemStack GoldenHead = new ItemStack(Material.GOLDEN_APPLE, 1);
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		ItemMeta GoldenHeadMeta = GoldenHead.getItemMeta();
		GoldenHeadMeta.setDisplayName(ChatColor.MAGIC + "" + ChatColor.DARK_BLUE + "--" + ChatColor.RESET + "" + ChatColor.GOLD + "" + ChatColor.BOLD + "Golden Head" + "" + ChatColor.RESET + "" +ChatColor.MAGIC + "" + ChatColor.DARK_BLUE + "--");
		GoldenHead.setItemMeta(GoldenHeadMeta);

		MaterialData data = skull.getData();
		ShapedRecipe GoldHead = new ShapedRecipe(GoldenHead);

		GoldHead.shape("ggg","gsg","ggg");

		GoldHead.setIngredient('s', data);
		GoldHead.setIngredient('g', Material.GOLD_INGOT);




		Bukkit.getServer().addRecipe(GoldHead);
	}
	public static void AddGoldenApple(){
		ItemStack GoldenHead = new ItemStack(Material.GOLDEN_APPLE, 1);
		ItemMeta GoldenHeadMeta = GoldenHead.getItemMeta();
		GoldenHeadMeta.setDisplayName(ChatColor.AQUA + "Golden Apple");
		GoldenHead.setItemMeta(GoldenHeadMeta);

		ShapedRecipe GoldApple = new ShapedRecipe(GoldenHead);

		GoldApple.shape("ggg","gsg","ggg");

		GoldApple.setIngredient('s', Material.APPLE);
		GoldApple.setIngredient('g', Material.GOLD_INGOT);




		Bukkit.getServer().addRecipe(GoldApple);
	}
	public static void giveArmour(Player player, int tier) {
		ItemStack Stone = new ItemStack(Material.STONE_SWORD, 1);
		ItemStack Wood = new ItemStack(Material.WOOD_SWORD, 1);

		ItemStack Gold1 = new ItemStack(Material.GOLD_INGOT, 1);
		ItemStack Gold2 = new ItemStack(Material.GOLD_INGOT, 2);
		ItemStack Gold3 = new ItemStack(Material.GOLD_INGOT, 3);
		ItemStack Gold4 = new ItemStack(Material.GOLD_INGOT, 4);
		ItemStack IronHelm = new ItemStack(Material.IRON_HELMET, 1);
		ItemStack IronChest = new ItemStack(Material.IRON_CHESTPLATE, 1);
		ItemStack IronLegs = new ItemStack(Material.IRON_LEGGINGS, 1);
		ItemStack IronBoots = new ItemStack(Material.IRON_BOOTS, 1);
		ItemStack LeatherHelm = new ItemStack(Material.LEATHER_HELMET, 1);
		ItemStack LeatherChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		ItemStack LeatherLegs = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		ItemStack LeatherBoots = new ItemStack(Material.LEATHER_BOOTS, 1);



			if (tier == 0) {
				player.getInventory().setItem(0, Wood);
				player.getInventory().setHelmet(LeatherHelm);
				player.getInventory().setChestplate(LeatherChest);
				player.getInventory().setLeggings(LeatherLegs);
				player.getInventory().setBoots(LeatherBoots);
			}
			if (tier == 1) {
				player.getInventory().setItem(0, Wood);
				player.getInventory().setHelmet(IronHelm);
				player.getInventory().setChestplate(LeatherChest);
				player.getInventory().setLeggings(LeatherLegs);
				player.getInventory().setBoots(LeatherBoots);
				player.getInventory().setItem(8, Gold1);
			}
			if (tier == 2) {
				player.getInventory().setItem(0, Wood);
				player.getInventory().setHelmet(IronHelm);
				player.getInventory().setChestplate(LeatherChest);
				player.getInventory().setLeggings(LeatherLegs);
				player.getInventory().setBoots(IronBoots);
				player.getInventory().setItem(8, Gold2);

			}
			if (tier == 3) {
				player.getInventory().setItem(0, Wood);
				player.getInventory().setHelmet(IronHelm);
				player.getInventory().setChestplate(LeatherChest);
				player.getInventory().setLeggings(IronLegs);
				player.getInventory().setBoots(IronBoots);
				player.getInventory().setItem(8, Gold3);

			}
			if (tier == 4) {

				player.getInventory().setItem(0, Stone);
				player.getInventory().setHelmet(IronHelm);
				player.getInventory().setChestplate(IronChest);
				player.getInventory().setLeggings(IronLegs);
				player.getInventory().setBoots(IronBoots);
				player.getInventory().setItem(8, Gold4);

			}
		}


		public static void giveArcher(Player player, int tier){
		Storage storage = new Storage();
		ItemStack String1 = new ItemStack(Material.STRING, 1);
		ItemStack String2 = new ItemStack(Material.STRING, 2);
		ItemStack String3 = new ItemStack(Material.STRING, 3);
		ItemStack Arrows8 = new ItemStack(Material.ARROW, 8);
		ItemStack Arrows12 = new ItemStack(Material.ARROW, 12);
		ItemStack Arrows16 = new ItemStack(Material.ARROW, 16);
		ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
		ItemStack Bow = new ItemStack(Material.BOW, 1);
	if(tier ==1 &&  Arena.selected.containsKey(player)==Arena.selected.containsValue("ARCHER") ){
		player.getInventory().addItem(String2);
		player.getInventory().addItem(Arrows8);


	}
	if(tier==2&& Arena.selected.containsKey(player)==Arena.selected.containsValue("ARCHER") ){
		player.getInventory().addItem(String3);
		player.getInventory().addItem(Arrows12);
	}
	if(tier==3&& Arena.selected.containsKey(player)==Arena.selected.containsValue("ARCHER") ){
		player.getInventory().addItem(Bow);
		player.getInventory().addItem(Arrows12);
	}
	if(tier==4&& Arena.selected.containsKey(player)==Arena.selected.containsValue("ARCHER") ){
		meta.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		book.setItemMeta(meta);
		player.getInventory().addItem(Bow);
		player.getInventory().addItem(Arrows16);
		player.getInventory().addItem(book);
		player.getInventory().addItem(String2);



}


	}

	public static void giveRusher(Player player, int tier){
		ItemStack Wood = new ItemStack(Material.IRON_HELMET, 1);
		ItemStack Stone = new ItemStack(Material.STONE_SWORD, 1);
		ItemStack Iron = new ItemStack(Material.IRON_SWORD, 1);
		ItemStack Diamond = new ItemStack(Material.DIAMOND_SWORD, 1);
		Storage storage = new Storage();

			if (tier == 1&& Arena.selected.containsKey(player)==Arena.selected.containsValue("RUSHER") ) {
				player.getInventory().addItem(Stone);
			}
			if (tier == 2&&Arena.selected.containsKey(player)==Arena.selected.containsValue("RUSHER") ) {
				player.getInventory().addItem(Iron);

			}
			if (tier == 3&& Arena.selected.containsKey(player)==Arena.selected.containsValue("RUSHER") ) {
			player.getInventory().addItem(Diamond);
			}
			if (tier == 4&& Arena.selected.containsKey(player)==Arena.selected.containsValue("RUSHER") ) {
				player.getInventory().addItem(Diamond);
				player.getInventory().getLeggings().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				player.getInventory().getLeggings().addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);


		}
	}
	public static void giveHealer(Player player, int tier){
		Storage storage = new Storage();
		ItemStack Apple1 = new ItemStack(Material.GOLDEN_APPLE, 1);
		ItemStack Apple2 = new ItemStack(Material.GOLDEN_APPLE, 2);
		ItemStack Gold = new ItemStack(Material.GOLD_INGOT, 4);

		ItemStack Iron = new ItemStack(Material.IRON_HELMET, 1);

		ItemStack GoldenHead = new ItemStack(Material.GOLDEN_APPLE, 1);
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		ItemMeta GoldenHeadMeta = GoldenHead.getItemMeta();
		GoldenHeadMeta.setDisplayName(ChatColor.MAGIC + "" + ChatColor.DARK_BLUE + "--" + ChatColor.RESET + "" + ChatColor.GOLD + "" + ChatColor.BOLD + "Golden Head" + "" + ChatColor.RESET + "" +ChatColor.MAGIC + "" + ChatColor.DARK_BLUE + "--");
		GoldenHead.setItemMeta(GoldenHeadMeta);
		MaterialData data = skull.getData();
		GoldenHead.setData(data);
			if (tier == 1&& Arena.selected.containsKey(player)==Arena.selected.containsValue("HEALER")) {
				player.getInventory().addItem(Apple1);
			}
			if (tier == 2&& Arena.selected.containsKey(player)==Arena.selected.containsValue("HEALER")) {
				player.getInventory().addItem(Apple2);
			}
			if (tier == 3&& Arena.selected.containsKey(player)==Arena.selected.containsValue("HEALER")) {
				player.getInventory().addItem(Apple1);
				player.getInventory().addItem(GoldenHead);
			}
			if (tier == 4&& Arena.selected.containsKey(player)==Arena.selected.containsValue("HEALER")) {
				player.getInventory().addItem(Apple2);
				player.getInventory().addItem(GoldenHead);
				player.getInventory().addItem(Gold);
			}

	}
	public static void giveBrute(Player player, int tier){
		Storage storage = new Storage();
			if (tier == 1&& Arena.selected.containsKey(player)==Arena.selected.containsValue("BRUTE")) {
				player.getInventory().getLeggings().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			}
			if (tier == 2&&  Arena.selected.containsKey(player)==Arena.selected.containsValue("BRUTE")) {
				player.getInventory().getLeggings().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				player.getInventory().getChestplate().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			}
			if (tier == 3&& Arena.selected.containsKey(player)==Arena.selected.containsValue("BRUTE")) {
				player.getInventory().getLeggings().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
				player.getInventory().getChestplate().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			}
			if (tier == 4 &&  Arena.selected.containsKey(player)==Arena.selected.containsValue("BRUTE")) {
				player.getInventory().getLeggings().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
				player.getInventory().getChestplate().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
			}
		}

}