package dev.tjxjnoobie.uhcgames.other;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.MapManager;
import dev.tjxjnoobie.uhcgames.managers.Voting;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapList {

	public static ArrayList<String> loadedmaps = new ArrayList<String>();
	public static ArrayList<String> maps = new ArrayList<String>();
	public static List<MapManager> mapsmanager = new ArrayList<>();
	public static MapManager selected_map;
	public static boolean selcted = false;

	public static void selectRandomMapList() {

		Random rand = new Random();
		int i = rand.nextInt(9);
		maps.add("Par72");
		maps.add("SG4");
		maps.add("TSG2");
		maps.add("AlaskanVillage");
		maps.add("BCMHighschool");
		maps.add("GreenGrass");
		maps.add("SGAdrenaline");
		maps.add("SGHighway");
		maps.add("Breeze");
		maps.add("Avaricia");
		maps.add("Zone85v1");
		maps.add("Coliseum");


		if (i == 1) {
			Main.maplist = 1;
			maps.add("Breeze");
			maps.add("Avaricia");
			maps.add("Zone85v1");
			maps.add("Coliseum");
			maps.remove("GreenGrass");
			return;
		}
		if (i == 2) {
			Main.maplist = 2;
			return;
		}
		if (i == 3) {
			Main.maplist = 3;
			return;
		}
		if (i == 4) {
			Main.maplist = 4;
			return;
		}
		if (i == 5) {
			Main.maplist = 5;
			return;
		}
		if (i == 6) {
			Main.maplist = 6;
			return;
		}
		if (i == 7) {
			Main.maplist = 7;
			return;
		}
		if (i == 8) {
			Main.maplist = 8;
			return;
		}
		if (i == 9) {
			Main.maplist = 9;
			return;
		}
		if (i == 10) {
			Main.maplist = 10;
			return;

		} else {
			Main.maplist = 10;
		}
	}


	public static void loadMaps() {
		final ConsoleCommandSender console = Bukkit.getConsoleSender();

		Bukkit.dispatchCommand(console, "mvload Par72");
		Bukkit.dispatchCommand(console, "mvload SG4");
		Bukkit.dispatchCommand(console, "mvload Turbulence");
		Bukkit.dispatchCommand(console, "mvload AlaskanVillage");
		Bukkit.dispatchCommand(console, "mvload SGAdrenaline");
		Bukkit.dispatchCommand(console, "mvload Coliseum");
		Bukkit.dispatchCommand(console, "mvload SGHighway");
		Bukkit.dispatchCommand(console, "mvload Avaricia");
		Bukkit.dispatchCommand(console, "mvload GreenGrass");
		Bukkit.dispatchCommand(console, "mvload Breeze");
		Bukkit.dispatchCommand(console, "mvload BCMHighschool");
		Bukkit.dispatchCommand(console, "mvload Zone85v1");
		Bukkit.dispatchCommand(console, "mvload TSG2");


	}

	public static List<MapManager> getRawMaps(){
		MapManager map1 = new MapManager("Par72");
		MapManager map3 = new MapManager("SG4");
		MapManager map2 = new MapManager("Turbulence");
		MapManager map4 = new MapManager("ValleysideUniversity");//BRREEZE
		MapManager map5 = new MapManager("ValleysideUniversity");//bcmh/other
		MapManager map6 = new MapManager("TSG2");
		MapManager map7 = new MapManager("Zone85v1");
		MapManager map8 = new MapManager("AlaskanVillage");
		MapManager map9 = new MapManager("SGAdrenaline");
		MapManager map10 = new MapManager("Coliseum");//COLISEUM
		MapManager map11 = new MapManager("SGHighway");//SGHIGHWAY
		MapManager map12 = new MapManager("GreenGrass");//AVARICA
		MapManager map13= new MapManager("GreenGrass");
		mapsmanager.add(map1);
		mapsmanager.add(map2);
		mapsmanager.add(map3);
		mapsmanager.add(map4);
		mapsmanager.add(map5);
		mapsmanager.add(map6);
		mapsmanager.add(map7);
		mapsmanager.add(map8);
		mapsmanager.add(map9);
		mapsmanager.add(map10);
		mapsmanager.add(map11);
		mapsmanager.add(map12);
		mapsmanager.add(map13);
		return mapsmanager;
	}



	public static MapManager getResult() {
		if(!selcted) {
			selcted = true;
			if (Main.maplist == 1) {

				if ((Voting.i1 > Voting.i2) && (Voting.i1 > Voting.i3) && (Voting.i1 > Voting.i4) && (Voting.i1 > Voting.i5)) {
					selected_map = mapsmanager.get(0);
					return mapsmanager.get(0);
				}
				if ((Voting.i2 > Voting.i1) && (Voting.i2 > Voting.i3) && (Voting.i2 > Voting.i4) && (Voting.i2 > Voting.i5)) {
					selected_map = mapsmanager.get(1);
					return mapsmanager.get(1);
				}
				if ((Voting.i3 > Voting.i1) && (Voting.i3 > Voting.i2) && (Voting.i3 > Voting.i4) && (Voting.i3 > Voting.i5)) {
					selected_map = mapsmanager.get(2);
					return mapsmanager.get(2);
				}
				if ((Voting.i4 > Voting.i1) && (Voting.i4 > Voting.i2) && (Voting.i4 > Voting.i3) && (Voting.i4 > Voting.i5)) {
					return selectRandomMap();
				}
				if ((Voting.i5 == 0) && (Voting.i4 == 0) && (Voting.i3 == 0) && (Voting.i2 == 0) && (Voting.i1 == 0)) {
					return selectRandomMap();
				}
			}
			if (Main.maplist == 2) {
				if ((Voting.i1 > Voting.i2) && (Voting.i1 > Voting.i3) && (Voting.i1 > Voting.i4) && (Voting.i1 > Voting.i5)) {
					selected_map = mapsmanager.get(3);
					return mapsmanager.get(3);

				}
				if ((Voting.i2 > Voting.i1) && (Voting.i2 > Voting.i3) && (Voting.i2 > Voting.i4) && (Voting.i2 > Voting.i5)) {
					selected_map = mapsmanager.get(4);
					return mapsmanager.get(12);
				}
				if ((Voting.i3 > Voting.i1) && (Voting.i3 > Voting.i2) && (Voting.i3 > Voting.i4) && (Voting.i3 > Voting.i5)) {
					selected_map = mapsmanager.get(5);
					return mapsmanager.get(5);
				}
				if ((Voting.i4 > Voting.i1) && (Voting.i4 > Voting.i2) && (Voting.i4 > Voting.i3) && (Voting.i4 > Voting.i5)) {
					return selectRandomMap();
				}
				if ((Voting.i5 == 0) && (Voting.i4 == 0) && (Voting.i3 == 0) && (Voting.i2 == 0) && (Voting.i1 == 0)) {
					return selectRandomMap();
				}
			}
			if (Main.maplist == 3) {
				if ((Voting.i1 > Voting.i2) && (Voting.i1 > Voting.i3) && (Voting.i1 > Voting.i4) && (Voting.i1 > Voting.i5)) {
					selected_map = mapsmanager.get(8);
					return mapsmanager.get(8);
				}
				if ((Voting.i2 > Voting.i1) && (Voting.i2 > Voting.i3) && (Voting.i2 > Voting.i4) && (Voting.i2 > Voting.i5)) {
					selected_map = mapsmanager.get(9);
					return mapsmanager.get(9);
				}
				if ((Voting.i3 > Voting.i1) && (Voting.i3 > Voting.i2) && (Voting.i3 > Voting.i4) && (Voting.i3 > Voting.i5)) {
					selected_map = mapsmanager.get(10);
					return mapsmanager.get(10);
				}
				if ((Voting.i4 > Voting.i1) && (Voting.i4 > Voting.i2) && (Voting.i4 > Voting.i3) && (Voting.i4 > Voting.i5)) {
					return selectRandomMap();
				}
				if ((Voting.i5 == 0) && (Voting.i4 == 0) && (Voting.i3 == 0) && (Voting.i2 == 0) && (Voting.i1 == 0)) {
					return selectRandomMap();
				}
			}
			if (Main.maplist == 4) {
				if ((Voting.i1 > Voting.i2) && (Voting.i1 > Voting.i3) && (Voting.i1 > Voting.i4) && (Voting.i1 > Voting.i5)) {
					selected_map = mapsmanager.get(3);
					return mapsmanager.get(3);
				}
				if ((Voting.i2 > Voting.i1) && (Voting.i2 > Voting.i3) && (Voting.i2 > Voting.i4) && (Voting.i2 > Voting.i5)) {
					selected_map = mapsmanager.get(10);
					return mapsmanager.get(10);
				}
				if ((Voting.i3 > Voting.i1) && (Voting.i3 > Voting.i2) && (Voting.i3 > Voting.i4) && (Voting.i3 > Voting.i5)) {
					selected_map = mapsmanager.get(11);
					return mapsmanager.get(11);
				}
				if ((Voting.i4 > Voting.i1) && (Voting.i4 > Voting.i2) && (Voting.i4 > Voting.i3) && (Voting.i4 > Voting.i5)) {
					return selectRandomMap();
				}
				if ((Voting.i5 == 0) && (Voting.i4 == 0) && (Voting.i3 == 0) && (Voting.i2 == 0) && (Voting.i1 == 0)) {
					return selectRandomMap();
				}
			}
			if (Main.maplist == 5) {
				if ((Voting.i1 > Voting.i2) && (Voting.i1 > Voting.i3) && (Voting.i1 > Voting.i4) && (Voting.i1 > Voting.i5)) {
					selected_map = mapsmanager.get(6);
					return mapsmanager.get(6);
				}
				if ((Voting.i2 > Voting.i1) && (Voting.i2 > Voting.i3) && (Voting.i2 > Voting.i4) && (Voting.i2 > Voting.i5)) {
					selected_map = mapsmanager.get(1);
					return mapsmanager.get(1);
				}
				if ((Voting.i3 > Voting.i1) && (Voting.i3 > Voting.i2) && (Voting.i3 > Voting.i4) && (Voting.i3 > Voting.i5)) {
					selected_map = mapsmanager.get(7);
					return mapsmanager.get(7);
				}
				if ((Voting.i4 > Voting.i1) && (Voting.i4 > Voting.i2) && (Voting.i4 > Voting.i3) && (Voting.i4 > Voting.i5)) {
					return selectRandomMap();
				}
				if ((Voting.i5 == 0) && (Voting.i4 == 0) && (Voting.i3 == 0) && (Voting.i2 == 0) && (Voting.i1 == 0)) {
					return selectRandomMap();
				}
			}
			if (Main.maplist == 6) {
				if ((Voting.i1 > Voting.i2) && (Voting.i1 > Voting.i3) && (Voting.i1 > Voting.i4) && (Voting.i1 > Voting.i5)) {
					selected_map = mapsmanager.get(2);
					return mapsmanager.get(2);
				}
				if ((Voting.i2 > Voting.i1) && (Voting.i2 > Voting.i3) && (Voting.i2 > Voting.i4) && (Voting.i2 > Voting.i5)) {
					selected_map = mapsmanager.get(10);
					return mapsmanager.get(10);
				}
				if ((Voting.i3 > Voting.i1) && (Voting.i3 > Voting.i2) && (Voting.i3 > Voting.i4) && (Voting.i3 > Voting.i5)) {
					selected_map = mapsmanager.get(5);
					return mapsmanager.get(5);
				}
				if ((Voting.i4 > Voting.i1) && (Voting.i4 > Voting.i2) && (Voting.i4 > Voting.i3) && (Voting.i4 > Voting.i5)) {
					return selectRandomMap();
				}
				if ((Voting.i5 == 0) && (Voting.i4 == 0) && (Voting.i3 == 0) && (Voting.i2 == 0) && (Voting.i1 == 0)) {
					return selectRandomMap();
				}
			}
			if (Main.maplist == 7) {
				if ((Voting.i1 > Voting.i2) && (Voting.i1 > Voting.i3) && (Voting.i1 > Voting.i4) && (Voting.i1 > Voting.i5)) {
					selected_map = mapsmanager.get(10);
					return mapsmanager.get(10);
				}
				if ((Voting.i2 > Voting.i1) && (Voting.i2 > Voting.i3) && (Voting.i2 > Voting.i4) && (Voting.i2 > Voting.i5)) {
					selected_map = mapsmanager.get(7);
					return mapsmanager.get(7);
				}
				if ((Voting.i3 > Voting.i1) && (Voting.i3 > Voting.i2) && (Voting.i3 > Voting.i4) && (Voting.i3 > Voting.i5)) {
					selected_map = mapsmanager.get(6);
					return mapsmanager.get(6);
				}
				if ((Voting.i4 > Voting.i1) && (Voting.i4 > Voting.i2) && (Voting.i4 > Voting.i3) && (Voting.i4 > Voting.i5)) {
					return selectRandomMap();
				}
				if ((Voting.i5 == 0) && (Voting.i4 == 0) && (Voting.i3 == 0) && (Voting.i2 == 0) && (Voting.i1 == 0)) {
					return selectRandomMap();
				}
			}
			if (Main.maplist == 8) {
				if ((Voting.i1 > Voting.i2) && (Voting.i1 > Voting.i3) && (Voting.i1 > Voting.i4) && (Voting.i1 > Voting.i5)) {
					selected_map = mapsmanager.get(12);
					return mapsmanager.get(12);
				}
				if ((Voting.i2 > Voting.i1) && (Voting.i2 > Voting.i3) && (Voting.i2 > Voting.i4) && (Voting.i2 > Voting.i5)) {
					selected_map = mapsmanager.get(11);
					return mapsmanager.get(3);
				}
				if ((Voting.i3 > Voting.i1) && (Voting.i3 > Voting.i2) && (Voting.i3 > Voting.i4) && (Voting.i3 > Voting.i5)) {
					selected_map = mapsmanager.get(10);
					return mapsmanager.get(10);
				}
				if ((Voting.i4 > Voting.i1) && (Voting.i4 > Voting.i2) && (Voting.i4 > Voting.i3) && (Voting.i4 > Voting.i5)) {
					return selectRandomMap();
				}

			}
			if ((Voting.i5 == 0) && (Voting.i4 == 0) && (Voting.i3 == 0) && (Voting.i2 == 0) && (Voting.i1 == 0)) {
				selected_map = mapsmanager.get(2);
				return mapsmanager.get(2);
			}
			if (Main.maplist == 9) {
				if ((Voting.i1 > Voting.i2) && (Voting.i1 > Voting.i3) && (Voting.i1 > Voting.i4) && (Voting.i1 > Voting.i5)) {
					selected_map = mapsmanager.get(2);
					return mapsmanager.get(2);
				}
				if ((Voting.i2 > Voting.i1) && (Voting.i2 > Voting.i3) && (Voting.i2 > Voting.i4) && (Voting.i2 > Voting.i5)) {
					selected_map = mapsmanager.get(0);
					return mapsmanager.get(0);
				}
				if ((Voting.i3 > Voting.i1) && (Voting.i3 > Voting.i2) && (Voting.i3 > Voting.i4) && (Voting.i3 > Voting.i5)) {
					selected_map = mapsmanager.get(8);
					return mapsmanager.get(8);
				}
				if ((Voting.i4 > Voting.i1) && (Voting.i4 > Voting.i2) && (Voting.i4 > Voting.i3) && (Voting.i4 > Voting.i5)) {
					return selectRandomMap();
				}

				if ((Voting.i5 == 0) && (Voting.i4 == 0) && (Voting.i3 == 0) && (Voting.i2 == 0) && (Voting.i1 == 0)) {
					return selectRandomMap();
				}
			}
			if (Main.maplist == 10) {
				if ((Voting.i1 > Voting.i2) && (Voting.i1 > Voting.i3) && (Voting.i1 > Voting.i4) && (Voting.i1 > Voting.i5)) {
					selected_map = mapsmanager.get(10);
					return mapsmanager.get(10);
				}
				if ((Voting.i2 > Voting.i1) && (Voting.i2 > Voting.i3) && (Voting.i2 > Voting.i4) && (Voting.i2 > Voting.i5)) {
					selected_map = mapsmanager.get(8);
					return mapsmanager.get(8);
				}
				if ((Voting.i3 > Voting.i1) && (Voting.i3 > Voting.i2) && (Voting.i3 > Voting.i4) && (Voting.i3 > Voting.i5)) {
					selected_map = mapsmanager.get(9);
					return mapsmanager.get(9);
				}
				if ((Voting.i4 > Voting.i1) && (Voting.i4 > Voting.i2) && (Voting.i4 > Voting.i3) && (Voting.i4 > Voting.i5)) {
					return selectRandomMap();
				}

				if ((Voting.i5 == 0) && (Voting.i4 == 0) && (Voting.i3 == 0) && (Voting.i2 == 0) && (Voting.i1 == 0)) {
					return selectRandomMap();
				}

			}
		}else{
			return selected_map;
		}
		return selectRandomMap();
	}



public static void getMapList(Player player) {
	if (Main.maplist == 1) {
		player.sendMessage(Main.prefix + "Vote using " + ChatColor.DARK_GRAY + "(" +
				ChatColor.GREEN + "§5/vote §7#" + ChatColor.DARK_GRAY + ")");
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "1 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i1 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(0).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "2 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i2 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ "  + ChatColor.GRAY +
				MapList.getRawMaps().get(1).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "3 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i3 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(2).getMapName());
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "4 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i4 +ChatColor.GRAY + " Votes "+ "§f▏ §7Random");
	}
	if (Main.maplist == 2) {
		player.sendMessage(Main.prefix + "Vote using " + ChatColor.DARK_GRAY + "(" +
				ChatColor.GREEN + "§5/vote §7#" + ChatColor.DARK_GRAY + ")");
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "1 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i1 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(3).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "2 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i2 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ "  + ChatColor.GRAY +
				MapList.getRawMaps().get(12).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "3 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i3 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(5).getMapName());
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "4 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i4 +ChatColor.GRAY + " Votes "+ "§f▏ §7Random");

	}
	if (Main.maplist == 3) {
		player.sendMessage(Main.prefix + "Vote using " + ChatColor.DARK_GRAY + "(" +
				ChatColor.GREEN + "§5/vote §7#" + ChatColor.DARK_GRAY + ")");
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "1 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i1 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(8).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "2 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i2 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ "  + ChatColor.GRAY +
				MapList.getRawMaps().get(9).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "3 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i3 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(10).getMapName());
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "4 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i4 +ChatColor.GRAY + " Votes "+ "§f▏ §7Random");

	}
	if (Main.maplist == 4) {
		player.sendMessage(Main.prefix + "Vote using " + ChatColor.DARK_GRAY + "(" +
				ChatColor.GREEN + "§5/vote §7#" + ChatColor.DARK_GRAY + ")");
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "1 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i1 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(2).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "2 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i2 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ "  + ChatColor.GRAY +
				MapList.getRawMaps().get(0).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "3 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i3 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(1).getMapName());
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "4 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i4 +ChatColor.GRAY + " Votes "+ "§f▏ §7Random");

	}
	if (Main.maplist == 5) {
		player.sendMessage(Main.prefix + "Vote using " + ChatColor.DARK_GRAY + "(" +
				ChatColor.GREEN + "§5/vote §7#" + ChatColor.DARK_GRAY + ")");
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "1 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i1 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(6).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "2 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i2 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ "  + ChatColor.GRAY +
				MapList.getRawMaps().get(1).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "3 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i3 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(7).getMapName());
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "4 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i4 +ChatColor.GRAY + " Votes "+ "§f▏ §7Random");
	}
	if (Main.maplist == 6) {
		player.sendMessage(Main.prefix + "Vote using " + ChatColor.DARK_GRAY + "(" +
				ChatColor.GREEN + "§5/vote §7#" + ChatColor.DARK_GRAY + ")");
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "1 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i1 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(2).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "2 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i2 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ "  + ChatColor.GRAY +
				MapList.getRawMaps().get(10).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "3 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i3 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(5).getMapName());
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "4 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i4 +ChatColor.GRAY + " Votes "+ "§f▏ §7Random");
	}
	if (Main.maplist == 7) {
		player.sendMessage(Main.prefix + "Vote using " + ChatColor.DARK_GRAY + "(" +
				ChatColor.GREEN + "§5/vote §7#" + ChatColor.DARK_GRAY + ")");
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "1 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i1 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(10).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "2 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i2 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ "  + ChatColor.GRAY +
				MapList.getRawMaps().get(7).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "3 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i3 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(6).getMapName());
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "4 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i4 +ChatColor.GRAY + " Votes "+ "§f▏ §7Random");
	}
	if (Main.maplist == 8) {
		player.sendMessage(Main.prefix + "Vote using " + ChatColor.DARK_GRAY + "(" +
				ChatColor.GREEN + "§5/vote §7#" + ChatColor.DARK_GRAY + ")");
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "1 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i1 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(12).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "2 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i2 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ "  + ChatColor.GRAY +
				MapList.getRawMaps().get(3).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "3 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i3 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(10).getMapName());
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "4 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i4 +ChatColor.GRAY + " Votes "+ "§f▏ §7Random");
	}
	if (Main.maplist == 9) {
		player.sendMessage(Main.prefix + "Vote using " + ChatColor.DARK_GRAY + "(" +
				ChatColor.GREEN + "§5/vote §7#" + ChatColor.DARK_GRAY + ")");
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "1 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i1 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(2).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "2 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i2 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ "  + ChatColor.GRAY +
				MapList.getRawMaps().get(0).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "3 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i3 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(8).getMapName());
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "4 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i4 +ChatColor.GRAY + " Votes "+ "§f▏ §7Random");
	}
	if (Main.maplist == 10) {
		player.sendMessage(Main.prefix + "Vote using " + ChatColor.DARK_GRAY + "(" +
				ChatColor.GREEN + "§5/vote §7#" + ChatColor.DARK_GRAY + ")");
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "1 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i1 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(10).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "2 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i2 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ "  + ChatColor.GRAY +
				MapList.getRawMaps().get(8).getMapName());

		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "3 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i3 + ChatColor.GRAY + " Votes " +
				ChatColor.WHITE + "▏ " + ChatColor.GRAY +
				MapList.getRawMaps().get(9).getMapName());
		player.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "4 " + ChatColor.DARK_GRAY + ": " +
				ChatColor.GREEN + Main.voting.i4 +ChatColor.GRAY + " Votes "+ "§f▏ §7Random");
	}


}
public static MapManager selectRandomMap(){
	Random random = new Random();
	int i = random.nextInt(mapsmanager.size());
	selected_map = mapsmanager.get(i);
	return mapsmanager.get(i);

}
}

