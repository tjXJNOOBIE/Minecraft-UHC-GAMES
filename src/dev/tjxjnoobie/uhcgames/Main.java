package dev.tjxjnoobie.uhcgames;

import Commands.*;
import Events.*;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import dev.tjxjnoobie.uhcgames.other.*;
import info.techwizmatt.ServerCore.API.CoinAPI;
import info.techwizmatt.ServerCore.Rank.Rank;
import dev.tjxjnoobie.uhcgames.commands.Commands;
import dev.tjxjnoobie.uhcgames.commands.Debug;
import dev.tjxjnoobie.uhcgames.commands.Start;
import dev.tjxjnoobie.uhcgames.managers.Arena;
import dev.tjxjnoobie.uhcgames.managers.SpectatorManager;
import dev.tjxjnoobie.uhcgames.managers.Sponsor;
import dev.tjxjnoobie.uhcgames.managers.Voting;
import dev.tjxjnoobie.uhcgames.managers.player.PlayerManager;
import nl.savagecoder.sg.other.*;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.sql.Connection;
import java.util.*;
import java.util.logging.Level;

public class Main extends JavaPlugin implements Listener, PluginMessageListener {
	private static Main instance;
	static Plugin plugin;
	public static String prefix = ChatColor.translateAlternateColorCodes('&', "&dUHCGames&8 » &7");
	public ArrayList<Arena> arenas = new ArrayList();
	public int latest = 0;
	public static int maplist = 0;
	public static ArrayList<Player> maintance = new ArrayList();
	public static HashMap<Entity, Entity> tag = new HashMap<Entity, Entity>();
	public static HashMap<Player, Player> fire = new HashMap();
	HashMap<String, Integer> players = new HashMap();
	public static Voting voting;
	public static Connection c = null;
	public static boolean canDamage = false;
	public HashMap<Player, Integer> exh = new HashMap();
	public static BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	public static GameState gamestate;
	private static PlayerManager playerManager;
	public static List<UUID> OnlinePlayerListUUID = new LinkedList<UUID>();
	public static List<String> OnlinePlayerListNAME = new LinkedList<String>();
	public static List<String> OnlinePlayerListReportSender = new LinkedList<String>();


	public static PlayerManager getPlayerManager() {
		return playerManager;
	}

	public void onEnable() {
		plugin = this;
		setGameState(GameState.LOBBY);
		instance = this;
		info.techwizmatt.ServerCore.Main.CustomChatDisplay = true;
		Arena.livegame = false;
		Arena.dm = false;
		Arena.joinable = true;
		Arena.minPlayers = 6;
		voting = new Voting();
		playerManager = new PlayerManager(this);
		MySQL.setDefualtRating();
		MySQL.setSGFile();
		MySQL.getRatingFile();
		MySQL.getSgFile();
		MySQL.readRatingFiles();
		MySQL.readSGFiles();
		MySQL.connect();
		MySQL.connectSG();
		MySQL.createGameIDTable();
		MySQL.createSGStatsTable();
		new BukkitRunnable(){
			@Override
			public void run() {
				MapList.selectRandomMapList();

				MapList.getRawMaps();

			}
		}.runTaskLater(this,20*15);
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		if ((GameIDSQLChecker.gameExist(Bukkit.getServer().getServerId()))) {
			GameIDSQLChecker.setGameState(0, Bukkit.getServerId());
		}
		if (!(GameIDSQLChecker.gameExist(Bukkit.getServer().getServerId()))) {
			GameIDSQLChecker.createServerID(Bukkit.getServer().getServerId());
			GameIDSQLChecker.setGameState(0, Bukkit.getServerId());
		}
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		Bukkit.dispatchCommand(console, "minecraft:kill @e[type=Item]");
		Bukkit.dispatchCommand(console, "gamerule doFireTick false");
		Bukkit.dispatchCommand(console, "gamerule keepInventory false");
		Bukkit.getServer().createWorld(new WorldCreator("MCSGLobby"));
		Bukkit.dispatchCommand(console, "mvload MCSGLobby");
		Bukkit.getPluginManager().registerEvents(new Arena(null, null, null), this);
		Bukkit.getPluginManager().registerEvents(new Sponsor(), this);
		Bukkit.getPluginManager().registerEvents(new DamageEvents(), this);
		Bukkit.getPluginManager().registerEvents(new DeathEvent(), this);
		Bukkit.getPluginManager().registerEvents(new DropEvent(), this);
		Bukkit.getPluginManager().registerEvents(new InteractEvent(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryDrag(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryInteract(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryOpen(), this);
		Bukkit.getPluginManager().registerEvents(new MiscEvents(), this);
		Bukkit.getPluginManager().registerEvents(new MoveEvent(), this);
		Bukkit.getPluginManager().registerEvents(new BlockIgnite(), this);
		Bukkit.getPluginManager().registerEvents(new PickUpEvent(), this);
		Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);
		Bukkit.getPluginManager().registerEvents(new BlockBreak(), this);
		Bukkit.getPluginManager().registerEvents(new BlockPlace(), this);
		Bukkit.getPluginManager().registerEvents(new StatsEvents(), this);
		Bukkit.getPluginManager().registerEvents(new CmdPreProcess(), this);
		Bukkit.getPluginManager().registerEvents(new ConsumeEvent(), this);
		Bukkit.getPluginManager().registerEvents(new LaunchEvent(), this);



		getCommand("bounty").setExecutor(new Bounty());
		getCommand("start").setExecutor(new Start());
		getCommand("debug").setExecutor(new Debug());
		getCommand("maprotation").setExecutor(new MapRotation());
		Bukkit.getPluginCommand("crazytaco").setExecutor(new SG());
		Bukkit.getPluginCommand("crazy").setExecutor(new SG());
		Bukkit.getPluginCommand("taco").setExecutor(new SG());
		Bukkit.getPluginCommand("ct").setExecutor(new SG());
		Bukkit.getPluginCommand("spec").setExecutor(new Commands());
		Bukkit.getPluginCommand("l").setExecutor(new PlayerList());
		Bukkit.getPluginCommand("bounty").setExecutor(new Bounty());
		Bukkit.getPluginCommand("confirmbounty").setExecutor(new Commands());
		Bukkit.getPluginCommand("setkills").setExecutor(new Commands());
		Bukkit.getPluginCommand("settotal").setExecutor(new Commands());
		Bukkit.getPluginCommand("setwins").setExecutor(new Commands());
		Bukkit.getPluginCommand("stats").setExecutor(new StatsCMD());
		Bukkit.getPluginCommand("sponsor").setExecutor(new SponsorCMD());
		Bukkit.getPluginCommand("join").setExecutor(new Commands());
		Bukkit.getPluginCommand("v").setExecutor(new Commands());
		Bukkit.getPluginCommand("setwins").setExecutor(new Commands());
		Bukkit.getPluginCommand("vote").setExecutor(new Commands());
		Bukkit.getPluginCommand("who").setExecutor(new PlayerList());
		Bukkit.getPluginCommand("list").setExecutor(new PlayerList());
		Bukkit.getPluginCommand("leaderboard").setExecutor(new LeaderBoards());
		Bukkit.getPluginCommand("leaderboards").setExecutor(new LeaderBoards());
		Bukkit.getPluginCommand("lb").setExecutor(new LeaderBoards());
		Bukkit.getPluginCommand("lbs").setExecutor(new LeaderBoards());
		Bukkit.getPluginCommand("kt").setExecutor(new KillTop());
		Bukkit.getPluginCommand("killtop").setExecutor(new KillTop());
		Bukkit.getPluginCommand("maintenance").setExecutor(new maintenance());
		Bukkit.getPluginCommand("speed").setExecutor(new Speed());
		Bukkit.getPluginCommand("kill").setExecutor(new Kill());
		Bukkit.getPluginCommand("wager").setExecutor(new Wager());
		Bukkit.getPluginCommand("bid").setExecutor(new Wager());
		Arena.s = Bukkit.getScoreboardManager().getMainScoreboard();
		Bukkit.getPluginCommand("spoof").setExecutor(new Spoof());

		saveConfig();
		Arena arena = new Arena((Location) getInstance().getConfig().get("arena." + getInstance().getConfig().getStringList("maps") + ".pos1"),
				(Location) getInstance().getConfig().get("arena." + getInstance().getConfig().getStringList("maps") + ".pos2"), getInstance().getConfig().getStringList("maps"));
		Bukkit.getLogger().log(Level.INFO, ChatColor.GREEN+""+arena + " is loading" + " with the following maps " + getInstance().getConfig().getStringList("maps"));
		arena.setID(this.latest);
		this.latest += 1;
		this.arenas.add(arena);
		SGUtils.AddGoldenHead();
		SGUtils.AddGoldenApple();
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				for (Player player : Bukkit.getServer().getOnlinePlayers()) {
					if (Arena.joinable == true) {
						MapList.getMapList(player);
					}

				}
			}
		}, 0L, 1500L);


		for (World w : Bukkit.getWorlds()) {
			for (Entity e : w.getEntities()) {
				if (e instanceof Item) {
					e.remove();
				}
			}
		}

		new BukkitRunnable() {
			public void run() {
				for (Player player : Bukkit.getServer().getOnlinePlayers()) {
					player.setExhaustion(0.0F);
				}
			}
		}.runTaskTimer(this, 30L, 30L);
		new BukkitRunnable() {
			public void run() {
				for (Player ingame : arenas.get(0).ingame) {
					ingame.setFoodLevel((int) (ingame.getFoodLevel() - 0.5));
				}
				for (Player watching : arenas.get(0).watching) {
					watching.setFoodLevel(20);
				}
			}
		}.runTaskTimer(this, 20 * 48, 20 * 48);
		new BukkitRunnable() {
			public void run() {
				int online = arenas.get(0).ingame.size();
				if (online == 0 && Arena.joinable == true) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
				}
			}

		}.runTaskTimer(this, 20 * 450, 20 * 450);
		new BukkitRunnable() {
			public void run() {
				for (Player ap : Bukkit.getServer().getOnlinePlayers()) {
					if (arenas.get(0).ingame.size() < Arena.minPlayers && ap.getWorld().getName().equals("MCSGLobby") && Arena.joinable == true) {
						ap.sendMessage(prefix + "§cThere must be at least §a" + Arena.minPlayers + " §cplayers for the game to start§8.");
					}
				}

			}
		}.runTaskTimer(this, 1250L, 1250L);

		new BukkitRunnable() {
			public void run() {
				for (Player watching : arenas.get(0).watching) {
					watching.getInventory().setArmorContents(null);
					watching.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 100, true),
							false);
					watching.addPotionEffect(
							new PotionEffect(PotionEffectType.WEAKNESS, 1000000, 1000, true), false);
					watching.setAllowFlight(true);
				}

			}
		}.runTaskTimer(this, 20L, 20L);

		new BukkitRunnable() {
			public void run() {
				for (World w : Bukkit.getWorlds()) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather clear");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule keepInventory false");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doFireTick false");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule naturalRegeneration false");

					w.setThundering(false);
					w.setStorm(false);
					w.setAutoSave(false);
					w.setGameRuleValue("keepInventroy", "false");
					w.setGameRuleValue("doFireTick", "false");
					w.setGameRuleValue("naturalRegeneration","false");
				}
			}

		}.runTaskTimer(this, 20*45, 20*45);

		new BukkitRunnable() {
			public void run() {

				MapList.loadMaps();


			}

		}.runTaskLater(this, 10L);


		saveConfig();

		new BukkitRunnable() {
			public void run() {
				for (Player player : arenas.get(0).ingame) {
					if (player.getWorld().getName().equals("MCSGLobby")) {
						player.setFoodLevel(20);
						player.setHealth(20.0D);
					}
				}


			}

		}.runTaskTimer(this, 20L, 20L);

		Bukkit.getPluginManager().registerEvents(this, this);
		new BukkitRunnable() {
			public void run() {
				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					p.getWorld().setDifficulty(Difficulty.EASY);
				}
			}
		}.runTaskTimer(this, 200L, 200L);
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Sponsor.items.clear();
		Sponsor.items.put(Sponsor.cake,100);
		Sponsor.items.put(new ItemStack(Material.ENDER_PEARL),150);
		Sponsor.items.put(new ItemStack(Material.BOW),100);
		Sponsor.items.put(new ItemStack(Material.ARROW, 5),100);
		ItemStack fs = new ItemStack(Material.FLINT_AND_STEEL);
		Sponsor.items.put(fs,80 );
		Sponsor.items.put(new ItemStack(Material.EXP_BOTTLE, 5),100);
		Sponsor.items.put(new ItemStack(Material.PORK),75);
		LeaderBoards.wins.put(0,Stats.getFirst("WINS"));
		LeaderBoards.wins.put(1,Stats.getSecond("WINS"));
		LeaderBoards.wins.put(2,Stats.getThird("WINS"));
		LeaderBoards.wins.put(3,Stats.getForuth("WINS"));
		LeaderBoards.wins.put(4,Stats.getFifit("WINS"));
		LeaderBoards.wins.put(5,Stats.getSixth("WINS"));
		LeaderBoards.wins.put(6,Stats.getSeven("WINS"));
		LeaderBoards.wins.put(7,Stats.getEight("WINS"));
		LeaderBoards.wins.put(8,Stats.getNine("WINS"));
		LeaderBoards.wins.put(9,Stats.getTen("WINS"));
		LeaderBoards.winrate.put(0,Stats.getFirst("WINRATE"));
		LeaderBoards.winrate.put(1,Stats.getSecond("WINRATE"));
		LeaderBoards.winrate.put(2,Stats.getThird("WINRATE"));
		LeaderBoards.winrate.put(3,Stats.getForuth("WINRATE"));
		LeaderBoards.winrate.put(4,Stats.getFifit("WINRATE"));
		LeaderBoards.winrate.put(5,Stats.getSixth("WINRATE"));
		LeaderBoards.winrate.put(6,Stats.getSeven("WINRATE"));
		LeaderBoards.winrate.put(7,Stats.getEight("WINRATE"));
		LeaderBoards.winrate.put(8,Stats.getNine("WINRATE"));
		LeaderBoards.winrate.put(9,Stats.getTen("WINRATE"));
		LeaderBoards.kills.put(0,Stats.getFirst("KILLS"));
		LeaderBoards.kills.put(1,Stats.getSecond("KILLS"));
		LeaderBoards.kills.put(2,Stats.getThird("KILLS"));
		LeaderBoards.kills.put(3,Stats.getForuth("KILLS"));
		LeaderBoards.kills.put(4,Stats.getFifit("KILLS"));
		LeaderBoards.kills.put(5,Stats.getSixth("KILLS"));
		LeaderBoards.kills.put(6,Stats.getSeven("KILLS"));
		LeaderBoards.kills.put(7,Stats.getEight("KILLS"));
		LeaderBoards.kills.put(8,Stats.getNine("KILLS"));
		LeaderBoards.kills.put(9,Stats.getTen("KILLS"));
		info.techwizmatt.ServerCore.Main.antilag=false;

	}

	public void onDisable() {
		Stats.setRanks("uhc_stats","WINS");
		MySQL.close();
		GameIDSQLChecker.setGameState(2, Bukkit.getServer().getServerId());

	}

	public static Plugin getPlugin() {
		return plugin;
	}


	public String aoran(String s) {
		if ((s.startsWith("a")) || (s.startsWith("e")) || (s.startsWith("i")) || (s.startsWith("u"))
				|| (s.startsWith("o"))) {
			return "an";
		}
		return "a";
	}

	@EventHandler
	public void onLight(EntityDamageEvent event) {
		if (event.getCause() == DamageCause.LIGHTNING && Arena.dm == false) {
			event.setCancelled(true);
		}
	}


	public static GameState setGameState(GameState gamestate) {
		return gamestate;
	}

	public static GameState getGameState() {
		return getGameState();
	}

	public static Main getInstance() {
		return instance;
	}

	@EventHandler
	public void onJoin2(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		final String playerusername = player.getName();
		final String playeruuid = player.getUniqueId().toString();
		Storage storage = new Storage();

		player.teleport(new Location(Bukkit.getWorld("MCSGLobby"), -36.55, 7, -155.7));
		Stats.updateName(playerusername, playeruuid);
		SpectatorManager specm = new SpectatorManager();
		Arena.registerNameTags(player);
		if(!SilentJoin.canSilentJoin(playeruuid)) {
			event.setJoinMessage(player.getDisplayName() + " §ahas joined");
		}else{
			event.setJoinMessage(null);
		}
		for (PotionEffect effect : player.getActivePotionEffects()) {
			player.removePotionEffect(effect.getType());
		}
		if (Arena.joinable == false) {
			storage.add(player);
			player.teleport(Main.getInstance().arenas.get(0).ingame.get(0).getLocation());
			arenas.get(0).newBoardPreLobby1(player);
			specm.makeSpec(player);
			player.setGameMode(GameMode.CREATIVE);
			return;
		}

		if (Arena.joinable) {
			arenas.get(0).ingame.add(player);
			arenas.get(0).newBoardPreLobby1(player);
			Arena.players.add(player.getName());
			player.getInventory().clear();
			player.setAllowFlight(false);
			player.setFlying(false);
			player.getInventory().setArmorContents(null);
			player.getInventory().clear();
			player.setGameMode(GameMode.SURVIVAL);
			player.setWalkSpeed(0.2F);
			player.setLevel(0);
			player.setMaximumNoDamageTicks(19);
			Stats.createPlayer(playerusername, playeruuid);
			storage.add(player);
			storage.updateTiers(player);
			Utils.giveJoinItems(player);
			Wagers.runPotIncrease(player);
			MapList.getMapList(player);



			if ((Stats.getDeaths(playerusername, playeruuid,"UUID") > 0)) {
				Stats.setKDR(playerusername, playeruuid, (Stats.getKills(playerusername, playeruuid,"UUID") / Stats.getDeaths(playerusername, playeruuid,"UUID")));
			}
			if (Stats.getWins(playerusername, playeruuid,"UUID") < 0) {
				int gamesplayed = Stats.getGamesPlayed(playerusername, playeruuid,"UUID");
				HashMap<Player, Integer> losses = new HashMap<Player, Integer>();
				losses.put(player, Stats.getLosses(playerusername, playeruuid,"UUID"));
				Stats.setLosses(playerusername, playeruuid, gamesplayed);
				Stats.setGamesPlayed(playerusername, playeruuid, losses.get(player));
			}

			Stats.setWins(playerusername, playeruuid, Stats.getGamesPlayed(playerusername, playeruuid,"UUID") - Stats.getLosses(playerusername, playeruuid,"UUID"));
		if(Stats.getSelectedKit(playeruuid) != null){
			Arena.selected.put(player,Stats.getSelectedKit(playeruuid));
			player.sendMessage(Main.prefix+"You selected kit §a" + Arena.selected.get(player));
		}else{
			player.sendMessage(Main.prefix+"§cDon't forget to select a kit!");

			Bukkit.getLogger().log(Level.INFO,"No kit selected for " + playerusername);

		}
		}
	}

		@SuppressWarnings("deprecation")
		@EventHandler(priority = EventPriority.HIGHEST)
		public void onChat (AsyncPlayerChatEvent event){
			Player p = event.getPlayer();
			Storage storage = new Storage();
			if(Punish.isMuted(p.getUniqueId().toString())){
				event.setCancelled(true);
				p.sendMessage(Main.prefix+"§cYou are muted!");
			}
				for (Player player : Bukkit.getServer().getOnlinePlayers()) {
					if (((arenas.get(0).ingame.contains(p)))) {
						String playeruuid = p.getUniqueId().toString();
						String playerusername = p.getName();
						event.setFormat("§e" + storage.getCoins(p)+ " §8§l▏ §a" + p.getDisplayName()+ " §f» "+ Rank.getChatColor(Rank.getRankLevel(p))+event.getMessage());
					}
				}
			if (((arenas.get(0).watching.contains(p)))) {
			for (Player watching : arenas.get(0).watching) {
					watching.sendMessage("§e" + storage.getCoins(p) + " §8§l▏ §a" + "§4SPEC§7: " + Utils.getNameColour(p) + p.getDisplayName() + " §8» §7" + Rank.getChatColor(Rank.getRankLevel(p)) + event.getMessage());
					event.setCancelled(true);
				}
			}



		}
	ArrayList<Player> cooldown = new ArrayList<>();

	@EventHandler
	public void onthrow(ProjectileLaunchEvent e) {
		Player p = (Player) e.getEntity().getShooter();
		if(!(e.getEntity().getType() == EntityType.ENDER_PEARL)){
			return;
		}
		if (Main.getInstance().arenas.get(0).canMove == false) {
			e.setCancelled(true);
			return;
		}
		if (e.getEntity().getType() == EntityType.ENDER_PEARL && cooldown.contains(p)) {
			e.setCancelled(true);
			p.sendMessage(prefix+"§cYou must wait 15 seconds in between pearls");
			p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL,1));
			return;
		}

		if(e.getEntity().getType() == EntityType.ENDER_PEARL && !cooldown.contains(p)) {
			cooldown.add(p);
			p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*3, 1));
		}


			new BukkitRunnable(){
				@Override
				public void run() {
					cooldown.remove(p);
					p.sendMessage(prefix+"§aPearl Ready");
					p.playSound(p.getLocation(),Sound.NOTE_STICKS,1.0f,1.0f);
					cancel();
				}



			}.runTaskLater(this,20*15);

		}






	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player p2 = event.getPlayer();
		World w = p2.getWorld();
		Wagers.removeWager(Wagers.getWager(p2),p2);
		Wagers.runPotDecrease(p2);
		int winrate = (int)(((int)Stats.getWins(p2.getName(), p2.getUniqueId().toString(),"UUID") * 100.0f) / (int)Stats.getGamesPlayed(p2.getName(), p2.getUniqueId().toString(),"UUID"));
		String playeruuid = p2.getUniqueId().toString();
		String playername = p2.getName();
		Storage storage = new Storage();
		Arena.unregisterNames(p2);
		if(!SilentJoin.canSilentJoin(playeruuid)){
			event.setQuitMessage(p2.getDisplayName() + " §chas left");

		}else{
			event.setQuitMessage(null);
		}
		Stats.setWinRate(playername,playeruuid, winrate);
		CoinAPI.AddTokens(p2,(int)storage.getCoinsEarned(p2));

		Stats.setWins(playername, playeruuid, Stats.getGamesPlayed(playername, playeruuid,"UUID") - Stats.getLosses(playername, playeruuid,"UUID"));
		// NEED AN EXTRA CHECK FOR STATS

		if((int)storage.getWins(p2) == 0){
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"has left with 0 wins");
		}
		if((int)storage.getSwings(p2) == 0){
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"has left with 0 swings");
		}
		if((int)storage.getSwingHits(p2) == 0){
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"has left with 0 swing hits");
		}
		if((int)storage.getKills(p2) == 0){
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"has left with 0 kills");
		}
		if((int)storage.getLosses(p2) == 0){
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"has left with 0 losses");
		}
		if((int)storage.getDeaths(p2) == 0){
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"has left with 0 deaths");
		}
		if((int)storage.getPlayed(p2) == 0){
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"has left with 0 played");
		}
		if((int)storage.getBowMisses(p2) == 0 || (int)storage.getBowHits(p2) == 0){
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"has left with 0 bow misses");
		}
		if((int)storage.getSwings(p2) == 0 || (int)storage.getSwingHits(p2) == 0){
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"has left with 0 swings");
		}







		if((int)storage.getWins(p2)>1) {
			Stats.addWins(playername, playeruuid, (int) storage.getWins(p2));
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"updated with " + storage.getWins(p2));

		}
		if((int)storage.getBowMisses(p2)>0 ||(int)storage.getBowHits(p2)>0) {
			int bowrate = (int)(((int)Stats.getBowMisses(p2.getName(), p2.getUniqueId().toString(),"UUID") * 100.0f) / (int)Stats.getBowHits(p2.getName(), p2.getUniqueId().toString(),"UUID"));
			Stats.setBowAccuracy(playername,playeruuid,bowrate);

			Bukkit.getLogger().log(Level.INFO,p2.getName()+"updated with " + storage.getWins(p2));

		}
		if((int)storage.getSwings(p2)>0 ||(int)storage.getSwingHits(p2)>0) {
			int swingrate = (int)(((int)Stats.getSwingHits(p2.getName(), p2.getUniqueId().toString(),"UUID") * 100.0f) / (int)Stats.getSwingsMisses(p2.getName(), p2.getUniqueId().toString(),"UUID"));
			Stats.setSwingAccuracy(playername,playeruuid,swingrate);

			Bukkit.getLogger().log(Level.INFO,p2.getName()+"updated with " + storage.getWins(p2));

		}
		if((int)storage.getSwingHits(p2)>0) {
			Stats.setSwingHits(playername,playeruuid,Stats.getSwingHits(playername,playeruuid,"UUID") +(int)storage.getSwingHits(p2));
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"updated with " + storage.getSwingHits(p2));
		}
		if((int)storage.getSwings(p2)>0) {
			Stats.setSwingMisses(playername,playeruuid,Stats.getSwingsMisses(playername,playeruuid,"UUID") +(int)storage.getSwings(p2));
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"updated with " + storage.getSwings(p2));
		}


		if((int)storage.getBowMisses(p2)>0) {
			Stats.setBowMisses(playername,playeruuid,Stats.getBowHits(playername,playeruuid,"UUID") + (int) storage.getBowMisses(p2));
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"updated with " + storage.getSwingHits(p2));
		}
		if((int)storage.getBowHits(p2)>0) {
			Stats.setBowHits(playername,playeruuid,Stats.getBowHits(playername,playeruuid,"UUID") + (int) storage.getBowHits(p2));
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"updated with " + storage.getSwings(p2));
		}


		if((int)storage.getLosses(p2)>0) {
			Stats.addLosses(playername,playeruuid,(int)storage.getLosses(p2));
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"updated with " + storage.getLosses(p2));

		}
		if((int)storage.getKills(p2)>0) {
			Stats.addKills(playername,playeruuid,(int)storage.getKills(p2));
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"updated with " + storage.getKills(p2));

		}
		if((int)storage.getDeaths(p2)>0) {
			Stats.addDeaths(playername,playeruuid,(int) storage.getDeaths(p2));
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"updated with " + storage.getDeaths(p2));

		}
		if((int)storage.getPlayed(p2)>0) {
			Stats.addGamesPlayed(playername,playeruuid,(int) storage.getPlayed(p2));
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"updated with " + storage.getPlayed(p2));

		}
		if((int)storage.getStreak(p2) > Stats.getKillstreak(playername,playeruuid,"UUID")){
			Stats.setKillstreak(playername,playeruuid,(int) storage.getStreak(p2));
			Bukkit.getLogger().log(Level.INFO,p2.getName()+"updated with " + storage.getStreak(p2));


		}
		if(Arena.joinable==true){
			Main.getInstance().arenas.get(0).ingame.remove(p2);

		}
		if(Main.getInstance().arenas.get(0).watching.contains(p2)) {
			event.getPlayer().getInventory().clear();
			event.getPlayer().getInventory().setArmorContents(null);
			Main.getInstance().arenas.get(0).watching.remove(p2);
		}
		if(Main.getInstance().arenas.get(0).ingame.contains(p2) && Arena.joinable == true) {
			Main.getInstance().arenas.get(0).ingame.remove(p2);
			Arena.players.remove(p2.getName());
		}
		if(Main.getInstance().arenas.get(0).ingame.size() >=1 && Arena.joinable == false && Arena.dm == true && Arena.cleanup == true) {
			Main.getInstance().arenas.get(0).ingame.remove(p2);
			Arena.players.remove(p2.getName());
		}
		if(Main.getInstance().arenas.get(0).ingame.contains(p2) && Arena.joinable == false) {

			w.strikeLightning(p2.getLocation().add(0, 5, 0));
			Main.getInstance().arenas.get(0).ingame.remove(p2);
			Stats.addLosses(playername,playeruuid,1);
			Arena.players.remove(p2.getName());
			for(Player ap : Bukkit.getServer().getOnlinePlayers()) {
				ap.sendMessage(Main.prefix + ChatColor.GRAY + "Only " + ChatColor.DARK_GRAY + "["
						+ ChatColor.LIGHT_PURPLE + (Main.getInstance().arenas.get(0).ingame.size()) + ChatColor.DARK_GRAY + "]"
						+ ChatColor.GRAY + " tributes remain!");
				ap.sendMessage(Main.prefix + ChatColor.GRAY + "There are " + ChatColor.DARK_GRAY + "["
						+ ChatColor.LIGHT_PURPLE + (Main.getInstance().arenas.get(0).watching.size() + 1) + ChatColor.DARK_GRAY + "]"
						+ ChatColor.GRAY + " spectators watching the game.");
				ap.sendMessage(ChatColor.DARK_PURPLE + "A cannon can be heard in the distance in memorial for "
						+ ChatColor.AQUA + p2.getDisplayName() + ChatColor.DARK_GRAY + ".");

			}

				ItemStack[] arrayOfItemStack;
			int j = (arrayOfItemStack = p2.getInventory().getContents()).length;
			for (int i = 0; i < j; i++) {
				ItemStack i1 = arrayOfItemStack[i];
				if (i1 != null) {
					p2.getWorld().dropItemNaturally(p2.getLocation(), i1);
					p2.getInventory().remove(i1);
				}
			}
			if (p2.getInventory().getHelmet() != null) {
				p2.getWorld().dropItemNaturally(p2.getLocation(), p2.getInventory().getHelmet());
			}
			if (p2.getInventory().getBoots() != null) {
				p2.getWorld().dropItemNaturally(p2.getLocation(), p2.getInventory().getBoots());
			}
			if (p2.getInventory().getLeggings() != null) {
				p2.getWorld().dropItemNaturally(p2.getLocation(), p2.getInventory().getLeggings());
			}
			if (p2.getInventory().getChestplate() != null) {
				p2.getWorld().dropItemNaturally(p2.getLocation(), p2.getInventory().getChestplate());
			}
			p2.getInventory().setHelmet(null);
			p2.getInventory().setBoots(null);
			p2.getInventory().setLeggings(null);
			p2.getInventory().setChestplate(null);

			if (Main.tag.containsKey(p2)) {
				Main.tag.remove(p2);
			}

		}
		Main.getInstance().arenas.get(0).ingame.remove(p2);

		storage.remove(p2);
	}







	public static int getRand(Inventory inv) {
		Random rand = new Random();
		int i = rand.nextInt(26);
		if (inv.getItem(i) != null) {
			return getRand(inv);
		}
		return i;
	}


	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("BungeeCord")) {
			return;
		}
		try {
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
			String command = in.readUTF();
			if (command.equals("PlayerCount")) {
				String server = in.readUTF();
				int playercount = in.readInt();
				this.players.put(server, Integer.valueOf(playercount));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}





	static public void SendToServer(final String PlayerName, final String ServerName, final Player SendingUser) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(getPlugin(), new Runnable() {
			public void run() {
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("ConnectOther");
				out.writeUTF(PlayerName);
				out.writeUTF(ServerName);
				SendingUser.sendPluginMessage(getPlugin(), "BungeeCord", out.toByteArray());
				Bukkit.getLogger().info(ChatColor.GREEN + PlayerName + "CONNECTING TO " + ServerName);
			}
		}, 5L);
	}

}
