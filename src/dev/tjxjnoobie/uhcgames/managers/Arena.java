package dev.tjxjnoobie.uhcgames.managers;

import dev.tjxjnoobie.uhcgames.other.*;
import info.techwizmatt.ServerCore.API.Chat;
import info.techwizmatt.ServerCore.Rank.Rank;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import dev.tjxjnoobie.uhcgames.GameIDSQLChecker;
import dev.tjxjnoobie.uhcgames.Main;
import nl.savagecoder.sg.other.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;

public class Arena
  implements Listener {
    public static Location pos1;
    public static Location pos2;
    public ArrayList<Player> ingame = new ArrayList();
    public static List<String> players = new ArrayList<String>();
    public static int minPlayers = 6;
    public static int maxPlayers = 24;
    public static int countDown = 30;
    public static int refillTime = 780;
    public static boolean canMove = true;
    public static int msg;
    public static boolean wr = true;
    public static boolean ended = false;
    public static int timeremaining = 1800;
    public ArrayList<Inventory> used = new ArrayList();
    public ArrayList<Player> watching = new ArrayList();
    public static Map<Player, Player> specchat;
    public static int prelobby = 60;
    public static boolean livegame = false;
    public static boolean dm = false;
    public static boolean remove;
    public static boolean addTime;
    public static boolean cleanup;
    public static boolean pregame;
    public static boolean voting = true;
    public static boolean joinable = true;
    public static boolean canPickup = true;
    public static boolean canDrop = true;
    public static HashMap<Player, Player> voted;
    public static HashMap<Player, Integer> killstreak;
    public static int cleanuptime = 30;
    public static int Deathmatch = 240;
    public static boolean cancelDeathmatch = false;
    public static Scoreboard s;

    public static HashMap<Player, String> selected = new HashMap<>();

    public MapManager getMap() {
        return this.map;
    }

    public void setMap(MapManager map) {
        this.map = map;
    }

    public int ID = 0;
    public MapManager map;

    public Location getPos1() {
        return this.pos1;
    }

    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }

    public Location getPos2() {
        return this.pos2;
    }

    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }

    public ArrayList<Player> getIngame() {
        return this.ingame;
    }

    public void setIngame(ArrayList<Player> ingame) {
        this.ingame = ingame;
    }

    public int getMinPlayers() {
        return this.minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getCountDown() {
        return this.countDown;
    }

    public void setCountDown(int countDown) {
        this.countDown = countDown;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int iD) {
        this.ID = iD;
    }

    ArrayList<Chest> blocklist = new ArrayList();

    public ArrayList<Chest> getChests(Location pos1, Location pos2) {
        this.blocklist.clear();
        Cuboid cube = new Cuboid(pos1, pos2);
        for (Block blocks : cube.getBlocks()) {
            if (blocks.getType().equals(Material.CHEST)) {
                this.blocklist.add((Chest) blocks.getState());
            }
        }
        return this.blocklist;
    }

    public Arena(Location pos1, Location pos2, List<String> map_name) {
        this.pos1 = pos1;
        this.pos2 = pos2;

        this.maxPlayers = 24;
        new BukkitRunnable() {
            public void run() {
                if (Arena.this.getIngame().size() >= Arena.this.minPlayers) {
                    Arena.this.addTime = true;
                    if (Arena.this.prelobby > 0) {
                        Arena.this.prelobby -= 1;
                    }
                }
                if ((Arena.this.ingame.size() >= Arena.this.minPlayers) && (Arena.this.ingame.size() <= Arena.this.maxPlayers) && (Arena.this.prelobby <= 0)) {
                    cancel();
                    new BukkitRunnable() {
                        @SuppressWarnings("static-access")
                        public void run() {
                            if (cleanup) {
                                cancel();
                            }
                            if ((Arena.this.ingame.size() <= Arena.this.maxPlayers) && (Arena.this.prelobby <= 0))
                                Arena.this.canMove = false;
                            voting = false;
                            for (Player player : ingame) {
                                World w = player.getWorld();
                                w.setGameRuleValue("keepInventroy", "false");
                                w.setGameRuleValue("keepInventroy", "false");
                                w.setGameRuleValue("doFireTick", "false");
                                w.setGameRuleValue("naturalRegeneration", "false");
                                w.setGameRuleValue("doTileDrops", "true");
                                w.setGameRuleValue("logAdminCommands", "false");
                            }
                            {


                                Arena.this.joinable = true;
                                if (Arena.countDown >= 1) {
                                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {

                                        player.setFoodLevel(20);
                                        player.setHealth(20);
                                    }
                                    ConsoleCommandSender console = Bukkit.getConsoleSender();
                                    World w = ingame.get(0).getWorld();
                                    Bukkit.dispatchCommand(console, "minecraft:kill @e[type=Item]");
                                    w.setGameRuleValue("keepInventroy", "false");
                                    w.setGameRuleValue("doFireTick", "false");
                                    w.setGameRuleValue("naturalRegeneration", "false");
                                    w.setGameRuleValue("doTileDrops", "true");
                                    w.setGameRuleValue("logAdminCommands", "false");
                                    joinable = false;
                                    canMove = false;
                                    voting = false;
                                    dm = false;
                                    pregame = true;
                                    Main.canDamage = false;

                                }


                                if (Arena.this.countDown == 30) {
                                    GameIDSQLChecker.setGameState(1, Bukkit.getServer().getServerId());
                                    ConsoleCommandSender console = Bukkit.getConsoleSender();
                                    Arena.this.map = MapList.getResult();
                                    Bukkit.broadcastMessage(Main.prefix+"Your are now playing on§f:§a " + MapList.getResult().getMapName());
                                    Arena.this.map.loadSpawns();
                                    Arena.this.map.getSpawn(0).getWorld().setTime(0L);

                                    joinable = false;
                                    canMove = false;
                                    voting = false;
                                    dm = false;
                                    teleportAllToSpawn();
                                    Main.canDamage = false;
                                    pregame = true;
                                    Arena.this.broadcast(ChatColor.translateAlternateColorCodes('&',
                                            "&e" + Arena.this.countDown + " &cseconds until the games begin!"));
                                    if (MapList.getResult().getMapName() == "SG4") {

                                        Bukkit.dispatchCommand(console, "mv unload Par72");
                                        Bukkit.dispatchCommand(console, "mv unload Turbulence");

                                        Bukkit.dispatchCommand(console, "mv unload AlaskanVillage");
                                        Bukkit.dispatchCommand(console, "mv unload SGAdrenaline");
                                        Bukkit.dispatchCommand(console, "mv unload Coliseum");

                                        Bukkit.dispatchCommand(console, "mv unload SGHighway");
                                        Bukkit.dispatchCommand(console, "mv unload Avaricia");
                                        Bukkit.dispatchCommand(console, "mv unload GreenGrass");

                                        Bukkit.dispatchCommand(console, "mv unload Breeze");
                                        Bukkit.dispatchCommand(console, "mv unload BCMHighschool");
                                        Bukkit.dispatchCommand(console, "mv unload Zone85v1");
                                        Bukkit.dispatchCommand(console, "mv unload TSG2");


                                    }
                                    if (MapList.getResult().getMapName() == "Par72") {

                                        Bukkit.dispatchCommand(console, "mv unload SG4");
                                        Bukkit.dispatchCommand(console, "mv unload Turbulence");

                                        Bukkit.dispatchCommand(console, "mv unload AlaskanVillage");
                                        Bukkit.dispatchCommand(console, "mv unload SGAdrenaline");
                                        Bukkit.dispatchCommand(console, "mv unload Coliseum");

                                        Bukkit.dispatchCommand(console, "mv unload SGHighway");
                                        Bukkit.dispatchCommand(console, "mv unload Avaricia");
                                        Bukkit.dispatchCommand(console, "mv unload GreenGrass");

                                        Bukkit.dispatchCommand(console, "mv unload Breeze");
                                        Bukkit.dispatchCommand(console, "mv unload BCMHighschool");
                                        Bukkit.dispatchCommand(console, "mv unload Zone85v1");
                                        Bukkit.dispatchCommand(console, "mv unload TSG2");


                                    }
                                    if (MapList.getResult().getMapName() == "Turbulence") {

                                        Bukkit.dispatchCommand(console, "mv unload SG4");
                                        Bukkit.dispatchCommand(console, "mv unload Par72");

                                        Bukkit.dispatchCommand(console, "mv unload AlaskanVillage");
                                        Bukkit.dispatchCommand(console, "mv unload SGAdrenaline");
                                        Bukkit.dispatchCommand(console, "mv unload Coliseum");

                                        Bukkit.dispatchCommand(console, "mv unload SGHighway");
                                        Bukkit.dispatchCommand(console, "mv unload Avaricia");
                                        Bukkit.dispatchCommand(console, "mv unload GreenGrass");

                                        Bukkit.dispatchCommand(console, "mv unload Breeze");
                                        Bukkit.dispatchCommand(console, "mv unload BCMHighschool");
                                        Bukkit.dispatchCommand(console, "mv unload Zone85v1");
                                        Bukkit.dispatchCommand(console, "mv unload TSG2");


                                    }
                                    if (MapList.getResult().getMapName() == "AlaskanVillage") {

                                        Bukkit.dispatchCommand(console, "mv unload SG4");
                                        Bukkit.dispatchCommand(console, "mv unload Turbulence");

                                        Bukkit.dispatchCommand(console, "mv unload Turbulence");
                                        Bukkit.dispatchCommand(console, "mv unload SGAdrenaline");
                                        Bukkit.dispatchCommand(console, "mv unload Coliseum");

                                        Bukkit.dispatchCommand(console, "mv unload SGHighway");
                                        Bukkit.dispatchCommand(console, "mv unload Avaricia");
                                        Bukkit.dispatchCommand(console, "mv unload GreenGrass");

                                        Bukkit.dispatchCommand(console, "mv unload Breeze");
                                        Bukkit.dispatchCommand(console, "mv unload BCMHighschool");
                                        Bukkit.dispatchCommand(console, "mv unload Zone85v1");
                                        Bukkit.dispatchCommand(console, "mv unload TSG2");


                                    }
                                    if (MapList.getResult().getMapName() == "SGAdrenaline") {

                                        Bukkit.dispatchCommand(console, "mv unload SG4");
                                        Bukkit.dispatchCommand(console, "mv unload Turbulence");

                                        Bukkit.dispatchCommand(console, "mv unload AlaskanVillage");
                                        Bukkit.dispatchCommand(console, "mv unload Par72");
                                        Bukkit.dispatchCommand(console, "mv unload Coliseum");

                                        Bukkit.dispatchCommand(console, "mv unload SGHighway");
                                        Bukkit.dispatchCommand(console, "mv unload Avaricia");
                                        Bukkit.dispatchCommand(console, "mv unload GreenGrass");

                                        Bukkit.dispatchCommand(console, "mv unload Breeze");
                                        Bukkit.dispatchCommand(console, "mv unload BCMHighschool");
                                        Bukkit.dispatchCommand(console, "mv unload Zone85v1");
                                        Bukkit.dispatchCommand(console, "mv unload TSG2");
                                    }
                                    if (MapList.getResult().getMapName() == "Coliseum") {

                                        Bukkit.dispatchCommand(console, "mv unload SG4");
                                        Bukkit.dispatchCommand(console, "mv unload Turbulence");
                                        Bukkit.dispatchCommand(console, "mv unload AlaskanVillage");
                                        Bukkit.dispatchCommand(console, "mv unload Par72");
                                        Bukkit.dispatchCommand(console, "mv unload SGAdrenaline");
                                        Bukkit.dispatchCommand(console, "mv unload SGHighway");
                                        Bukkit.dispatchCommand(console, "mv unload Avaricia");
                                        Bukkit.dispatchCommand(console, "mv unload GreenGrass");
                                        Bukkit.dispatchCommand(console, "mv unload Breeze");
                                        Bukkit.dispatchCommand(console, "mv unload BCMHighschool");
                                        Bukkit.dispatchCommand(console, "mv unload Zone85v1");
                                        Bukkit.dispatchCommand(console, "mv unload TSG2");
                                    }
                                    if (MapList.getResult().getMapName() == "Avaricia") {

                                        Bukkit.dispatchCommand(console, "mv unload SG4");
                                        Bukkit.dispatchCommand(console, "mv unload Turbulence");
                                        Bukkit.dispatchCommand(console, "mv unload AlaskanVillage");
                                        Bukkit.dispatchCommand(console, "mv unload Par72");
                                        Bukkit.dispatchCommand(console, "mv unload SGAdrenaline");
                                        Bukkit.dispatchCommand(console, "mv unload SGHighway");
                                        Bukkit.dispatchCommand(console, "mv unload Coliseum");
                                        Bukkit.dispatchCommand(console, "mv unload GreenGrass");
                                        Bukkit.dispatchCommand(console, "mv unload Breeze");
                                        Bukkit.dispatchCommand(console, "mv unload BCMHighschool");
                                        Bukkit.dispatchCommand(console, "mv unload Zone85v1");
                                        Bukkit.dispatchCommand(console, "mv unload TSG2");
                                    }
                                    if (MapList.getResult().getMapName() == "GreenGrass") {

                                        Bukkit.dispatchCommand(console, "mv unload SG4");
                                        Bukkit.dispatchCommand(console, "mv unload Turbulence");
                                        Bukkit.dispatchCommand(console, "mv unload AlaskanVillage");
                                        Bukkit.dispatchCommand(console, "mv unload Par72");
                                        Bukkit.dispatchCommand(console, "mv unload SGAdrenaline");
                                        Bukkit.dispatchCommand(console, "mv unload SGHighway");
                                        Bukkit.dispatchCommand(console, "mv unload Coliseum");
                                        Bukkit.dispatchCommand(console, "mv unload Avaricia");
                                        Bukkit.dispatchCommand(console, "mv unload Breeze");
                                        Bukkit.dispatchCommand(console, "mv unload BCMHighschool");
                                        Bukkit.dispatchCommand(console, "mv unload Zone85v1");
                                        Bukkit.dispatchCommand(console, "mv unload TSG2");
                                        Bukkit.dispatchCommand(console, "gamerule doFireTick false");
                                        Bukkit.dispatchCommand(console, "gamerule keepInventory false");
                                    }
                                    if (MapList.getResult().getMapName() == "BCMHighschool") {

                                        Bukkit.dispatchCommand(console, "mv unload SG4");
                                        Bukkit.dispatchCommand(console, "mv unload Turbulence");
                                        Bukkit.dispatchCommand(console, "mv unload AlaskanVillage");
                                        Bukkit.dispatchCommand(console, "mv unload Par72");
                                        Bukkit.dispatchCommand(console, "mv unload SGAdrenaline");
                                        Bukkit.dispatchCommand(console, "mv unload SGHighway");
                                        Bukkit.dispatchCommand(console, "mv unload Coliseum");
                                        Bukkit.dispatchCommand(console, "mv unload Avaricia");
                                        Bukkit.dispatchCommand(console, "mv unload Breeze");
                                        Bukkit.dispatchCommand(console, "mv unload GreenGrass");
                                        Bukkit.dispatchCommand(console, "mv unload Zone85v1");
                                        Bukkit.dispatchCommand(console, "mv unload TSG2");
                                        Bukkit.dispatchCommand(console, "gamerule doFireTick false");
                                        Bukkit.dispatchCommand(console, "gamerule keepInventory false");
                                    }
                                    if (MapList.getResult().getMapName() == "Breeze") {

                                        Bukkit.dispatchCommand(console, "mv unload SG4");
                                        Bukkit.dispatchCommand(console, "mv unload Turbulence");
                                        Bukkit.dispatchCommand(console, "mv unload AlaskanVillage");
                                        Bukkit.dispatchCommand(console, "mv unload Par72");
                                        Bukkit.dispatchCommand(console, "mv unload SGAdrenaline");
                                        Bukkit.dispatchCommand(console, "mv unload SGHighway");
                                        Bukkit.dispatchCommand(console, "mv unload Coliseum");
                                        Bukkit.dispatchCommand(console, "mv unload Avaricia");
                                        Bukkit.dispatchCommand(console, "mv unload BCMHighschool");
                                        Bukkit.dispatchCommand(console, "mv unload GreenGrass");
                                        Bukkit.dispatchCommand(console, "mv unload Zone85v1");
                                        Bukkit.dispatchCommand(console, "mv unload TSG2");
                                    }
                                    if (MapList.getResult().getMapName() == "Zone85v1") {

                                        Bukkit.dispatchCommand(console, "mv unload SG4");
                                        Bukkit.dispatchCommand(console, "mv unload Turbulence");
                                        Bukkit.dispatchCommand(console, "mv unload AlaskanVillage");
                                        Bukkit.dispatchCommand(console, "mv unload Par72");
                                        Bukkit.dispatchCommand(console, "mv unload SGAdrenaline");
                                        Bukkit.dispatchCommand(console, "mv unload SGHighway");
                                        Bukkit.dispatchCommand(console, "mv unload Coliseum");
                                        Bukkit.dispatchCommand(console, "mv unload Avaricia");
                                        Bukkit.dispatchCommand(console, "mv unload BCMHighschool");
                                        Bukkit.dispatchCommand(console, "mv unload GreenGrass");
                                        Bukkit.dispatchCommand(console, "mv unload Breeze");
                                        Bukkit.dispatchCommand(console, "mv unload TSG2");
                                    }
                                    if (MapList.getResult().getMapName() == "TSG2") {

                                        Bukkit.dispatchCommand(console, "mv unload SG4");
                                        Bukkit.dispatchCommand(console, "mv unload Turbulence");
                                        Bukkit.dispatchCommand(console, "mv unload AlaskanVillage");
                                        Bukkit.dispatchCommand(console, "mv unload Par72");
                                        Bukkit.dispatchCommand(console, "mv unload SGAdrenaline");
                                        Bukkit.dispatchCommand(console, "mv unload SGHighway");
                                        Bukkit.dispatchCommand(console, "mv unload Coliseum");
                                        Bukkit.dispatchCommand(console, "mv unload Avaricia");
                                        Bukkit.dispatchCommand(console, "mv unload BCMHighschool");
                                        Bukkit.dispatchCommand(console, "mv unload GreenGrass");
                                        Bukkit.dispatchCommand(console, "mv unload Breeze");
                                        Bukkit.dispatchCommand(console, "mv unload Zone85v1");
                                    }
                                    if (MapList.getResult().getMapName() == "SGHighway") {

                                        Bukkit.dispatchCommand(console, "mv unload SG4");
                                        Bukkit.dispatchCommand(console, "mv unload Turbulence");
                                        Bukkit.dispatchCommand(console, "mv unload AlaskanVillage");
                                        Bukkit.dispatchCommand(console, "mv unload Par72");
                                        Bukkit.dispatchCommand(console, "mv unload SGAdrenaline");
                                        Bukkit.dispatchCommand(console, "mv unload TSG2");
                                        Bukkit.dispatchCommand(console, "mv unload Coliseum");
                                        Bukkit.dispatchCommand(console, "mv unload Avaricia");
                                        Bukkit.dispatchCommand(console, "mv unload BCMHighschool");
                                        Bukkit.dispatchCommand(console, "mv unload GreenGrass");
                                        Bukkit.dispatchCommand(console, "mv unload Breeze");
                                        Bukkit.dispatchCommand(console, "mv unload Zone85v1");
                                    }
                                    for (Player p : ingame) {
                                        p.getInventory().clear();
                                    }
                                }
                                Arena.this.countDown -= 1;
                                Storage storage = new Storage();
                                if (Arena.this.countDown == 20) {
                                    Arena.this.broadcast(ChatColor.translateAlternateColorCodes('&',
                                            "&e" + Arena.this.countDown + " &cseconds until the games begin!"));

                                }
                                if (Arena.this.getCountDown() == 15) {
                                    for (Player p : ingame) {
                                        p.playSound(p.getLocation(), Sound.HORSE_SADDLE, 1.0f, 1.0f);
                                        p.sendMessage(Main.prefix + "Given §aTier " + storage.getArmourTier(p) + " §7Armour");
                                        SGUtils.giveArmour(p, (int) storage.getArmourTier(p));


                                    }
                                }


                                if (Arena.this.countDown == 10) {
                                    Arena.this.broadcast(ChatColor.translateAlternateColorCodes('&',
                                            "&e" + Arena.this.countDown + " &cseconds until the games begin!"));
                                    for (Player p : ingame) {
                                        if (selected.get(p) == null) {
                                            p.sendMessage(Main.prefix + "No kit loaded!");
                                            p.playSound(p.getLocation(), Sound.NOTE_BASS, 0.5f, 0.5f);
                                        } else {
                                            p.sendMessage(Main.prefix + "Loaded kit:§a " + selected.get(p));
                                            p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1.0f, 1.0f);
                                            if (selected.get(p).equals("ARCHER")){
                                                SGUtils.giveArcher(p, (int) storage.getArcherTier(p));
                                        }
                                            if (selected.get(p).equals("BRUTE")){
                                                SGUtils.giveBrute(p,(int)storage.getBruteTier(p));
                                            }       if (selected.get(p).equals("RUSHER")){
                                                SGUtils.giveRusher(p,(int)storage.getRusherTier(p));
                                            }       if (selected.get(p).equals("HEALER")){
                                                SGUtils.giveHealer(p,(int)storage.getHealerTier(p));
                                            }
                                        }



                                    }
                                }

                                if ((Arena.this.countDown <= 5) && (Arena.this.countDown > 1)) {
                                    Arena.this.broadcast(ChatColor.translateAlternateColorCodes('&',
                                            "&e" + Arena.this.countDown + " &cseconds until the games begin!"));

                                }

                                if (Arena.this.countDown == 1) {
                                    Arena.this.broadcast(ChatColor.translateAlternateColorCodes('&',
                                            "&e" + Arena.this.countDown + " &cseconds until the games begin!"));

                                }

                                if (Arena.this.countDown <= 0)

                                {
                                    for (Player player : ingame) {
                                        player.setWalkSpeed(0.2F);
                                        storage.addPlayed(player);
                                    }
                                    Main.canDamage = true;
                                    Arena.this.remove = true;
                                    pregame = false;
                                    GameIDSQLChecker.setGameState(1, Bukkit.getServer().getServerId());


                                    Arena.this.broadcast(
                                            ChatColor.translateAlternateColorCodes('&', "&3The games have begun!"));
                                    Arena.this.canMove = true;
                                    Arena.this.broadcast(ChatColor.translateAlternateColorCodes('&',
                                            "&e30&8 &cminutes until deathmatch!"));

                                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                                        for (PotionEffect effects : player.getActivePotionEffects()) {
                                            player.removePotionEffect(PotionEffectType.JUMP);
                                            player.setFoodLevel(20);
                                            player.getInventory().clear();
                                            player.getInventory().setArmorContents(null);
                                            player.setHealth(20);
                                            player.setAllowFlight(false);
                                            player.setFlying(false);
                                        }
                                    }


                                    new BukkitRunnable() {
                                        public void run() {
                                            if ((Arena.this.ingame.size() <= 3) || (Arena.this.timeremaining == 0)) {
                                                Arena.this.startDM();
                                                cancel();
                                            }
                                        }
                                    }.runTaskTimer(Main.getInstance(), 20L, 20L);


                                    Arena.this.canMove = true;

                                    cancel();

                                    new BukkitRunnable() {
                                        public void run() {
                                            if (Arena.this.ingame.size() <= 1) {
                                                Arena.this.stopGame();
                                                cancel();
                                            }
                                        }
                                    }.runTaskTimer(Main.getInstance(), 20L, 20L);


                                    new BukkitRunnable() {
                                        public void run() {
                                            if (cleanup) {
                                                cancel();
                                            }
                                            if (Arena.this.ended) {
                                                cancel();
                                            }
                                            if (Arena.this.timeremaining > 0) {
                                                Arena.this.timeremaining -= 1;
                                            }

                                        }
                                    }.runTaskTimer(Main.getInstance(), 20L, 20L);


                                    new BukkitRunnable() {
                                        public void run() {
                                            if (cleanup) {
                                                cancel();
                                            }
                                            if (Arena.this.ended) {
                                                cancel();
                                                return;
                                            }
                                            if (Arena.this.refillTime == 0) {
                                                Arena.this.used.clear();
                                                Arena.this.broadcast("The chests are " + ChatColor.RED + "refilled" +
                                                        ChatColor.GOLD + "!");
                                                cancel();
                                            } else {
                                                if (cleanup) {
                                                    cancel();
                                                }
                                                Arena.this.refillTime -= 1;
                                            }
                                        }
                                    }.runTaskTimer(Main.getInstance(), 20L, 20L);


                                }


                            }

                        }


                    }.runTaskTimer(Main.getInstance(), 20L, 20L);

                }
            }
        }.runTaskTimer(Main.getPlugin(), 20L, 20L);
    }






    public void broadcast(String MSG) {
        for (Player player : this.ingame) {
            player.sendMessage(Main.getInstance().prefix + MSG);
        }
    }

    public void teleportAllToSpawn() {
        int i = 0;
        this.map.loadSpawns();
        for (Player player : this.ingame) {
            player.teleport(this.map.getSpawn(i));
            i++;
        }
    }

    public void teleportAllToSpawnDM() {
        this.map.loadSpawns();
        int i = 24;
        this.map.loadSpawns();
        for (Player player : this.ingame) {
            player.teleport(this.map.getSpawn(i));
            i++;

        }
    }
    public void teleportAllToSpawnForceDM() {
        this.map.loadSpawns();
        int i = 24;
        this.map.loadSpawns();
        for (Player player : this.ingame) {
            player.teleport(this.map.getSpawn(i));
            i++;
            if(i==27){
                player.teleport(this.map.getSpawn(i).add(8,0,8));
            }

        }
    }

    public void startDM() {
        new BukkitRunnable() {
            int i = 61;

            public void run() {
                if (Arena.this.getIngame().size() <= 3) {
                    this.i -= 1;
                    if (this.i == 60) {
                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                            player.sendMessage(Main.getInstance().prefix + ChatColor.DARK_GRAY + "" + ChatColor.YELLOW +
                                    "60" + ChatColor.DARK_GRAY + " " + ChatColor.RED + "seconds until deathmatch!");
                        }
                    }
                    if (this.i == 30) {
                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                            player.sendMessage(Main.getInstance().prefix + ChatColor.DARK_GRAY + "" + ChatColor.YELLOW +
                                    "30" + ChatColor.DARK_GRAY + " " + ChatColor.RED + "seconds until deathmatch!");
                        }
                    }
                    if (this.i == 20) {
                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                            player.sendMessage(Main.getInstance().prefix + ChatColor.DARK_GRAY + "" + ChatColor.YELLOW +
                                    "20" + ChatColor.DARK_GRAY + " " + ChatColor.RED + "seconds until deathmatch!");
                        }
                    }
                    if (this.i == 10) {
                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                            player.sendMessage(Main.getInstance().prefix + ChatColor.DARK_GRAY + "" + ChatColor.YELLOW +
                                    "10" + ChatColor.DARK_GRAY + " " + ChatColor.RED + "seconds until deathmatch!");
                        }
                    }
                    if (this.i == 5) {
                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                            player.sendMessage(Main.getInstance().prefix + ChatColor.DARK_GRAY + "" + ChatColor.YELLOW +
                                    "5" + ChatColor.DARK_GRAY + " " + ChatColor.RED + "seconds until deathmatch!");
                        }
                        this.i -= 1;
                    }
                    if (this.i == 4) {
                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                            player.sendMessage(Main.getInstance().prefix + ChatColor.DARK_GRAY + "" + ChatColor.YELLOW +
                                    "4" + ChatColor.DARK_GRAY + " " + ChatColor.RED + "seconds until deathmatch!");
                        }
                    }
                    if (this.i == 3) {
                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                            player.sendMessage(Main.getInstance().prefix + ChatColor.DARK_GRAY + "" + ChatColor.YELLOW +
                                    "3" + ChatColor.DARK_GRAY + " " + ChatColor.RED + "seconds until deathmatch!");
                        }
                    }
                    if (this.i == 2) {
                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                            player.sendMessage(Main.getInstance().prefix + ChatColor.DARK_GRAY + "" + ChatColor.YELLOW +
                                    "2" + ChatColor.DARK_GRAY + " " + ChatColor.RED + "seconds until deathmatch!");
                        }
                    }
                    if (this.i == 1) {
                        for (Player player :Bukkit.getServer().getOnlinePlayers()) {
                            player.sendMessage(Main.getInstance().prefix + ChatColor.DARK_GRAY + "" + ChatColor.YELLOW +
                                    "1" + ChatColor.DARK_GRAY + " " + ChatColor.RED + "second until deathmatch!");
                            if(Arena.this.getIngame().size() <= 3) {
                                teleportAllToSpawnDM();
                            }
                            if(Arena.this.getIngame().size() < 3) {
                                teleportAllToSpawnForceDM();
                            }
                            Main.canDamage = false;
                            canMove = false;

                        }
                    }
                    if (this.i == 0) {
                        for (Player player : Arena.this.ingame) {
                            Arena.this.deathmatchBoard(player);
                        }
                        for (Player player : Arena.this.watching) {
                            Arena.this.deathmatchBoard(player);
                            player.teleport(Main.getInstance().arenas.get(0).ingame.get(0).getLocation());

                        }

                        dm = true;
                        new BukkitRunnable() {
                            int i1 = 11;

                            public void run() {

                                Main.setGameState(GameState.DEATHMATCH);
                                canMove = false;
                                Main.canDamage = false;
                                joinable = false;

                                this.i1 -= 1;
                                if (this.i1 == 10) {
                                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                                        player.sendMessage(
                                                Main.getInstance().prefix + ChatColor.translateAlternateColorCodes('&',
                                                        "&4Please allow &e10 &4seconds for all players to load the map."));
                                    }
                                }
                                if (this.i1 == 5) {
                                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                                        player.sendMessage(Main.getInstance().prefix + ChatColor.DARK_GRAY + "" +
                                                ChatColor.YELLOW + "5" + ChatColor.DARK_GRAY + " " + ChatColor.RED +
                                                "seconds until deathmatch!");
                                        player.playSound(player.getLocation(),Sound.ORB_PICKUP,1.0f,1.0f);
                                    }
                                }
                                if (this.i1 == 4) {
                                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                                        player.sendMessage(Main.getInstance().prefix + ChatColor.DARK_GRAY + "" +
                                                ChatColor.YELLOW + "4" + ChatColor.DARK_GRAY + " " + ChatColor.RED +
                                                "seconds until deathmatch!");
                                        player.playSound(player.getLocation(),Sound.ORB_PICKUP,1.0f,1.0f);

                                    }
                                }
                                if (this.i1 == 3) {
                                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                                        player.sendMessage(Main.getInstance().prefix + ChatColor.DARK_GRAY + "" +
                                                ChatColor.YELLOW + "3" + ChatColor.DARK_GRAY + " " + ChatColor.RED +
                                                "seconds until deathmatch!");
                                        player.playSound(player.getLocation(),Sound.ORB_PICKUP,1.0f,1.0f);

                                    }
                                }
                                if (this.i1 == 2) {
                                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                                        player.sendMessage(Main.getInstance().prefix + ChatColor.DARK_GRAY + "" +
                                                ChatColor.YELLOW + "2" + ChatColor.DARK_GRAY + " " + ChatColor.RED +
                                                "seconds until deathmatch!");
                                        player.playSound(player.getLocation(),Sound.ORB_PICKUP,1.0f,1.0f);

                                    }
                                }
                                if (this.i1 == 1) {
                                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                                        player.sendMessage(Main.getInstance().prefix + ChatColor.DARK_GRAY + "" +
                                                ChatColor.YELLOW + "1" + ChatColor.DARK_GRAY + " " + ChatColor.RED +
                                                "second until deathmatch!");
                                        player.playSound(player.getLocation(),Sound.ORB_PICKUP,1.0f,1.0f);

                                    }
                                }
                                if (this.i1 <= 0) {
                                    for (Player player : ingame) {
                                        player.setWalkSpeed(0.2F);
                                    }


                                    Arena.this.canMove = true;
                                    Main.canDamage = true;
                                    for (Player player : Arena.this.ingame) {
                                        player.sendMessage(
                                                Main.getInstance().prefix + ChatColor.RED + "Fight to the death!");
                                        player.playSound(player.getLocation(),Sound.WOLF_HOWL,1.0f,1.0f);

                                    }
                                    cancel();
                                    new BukkitRunnable() {
                                        int i;

                                        public void run() {
                                            for (Player player : Arena.this.ingame) {
                                                this.i += 1;
                                                if (this.i == 3) {
                                                    this.i = 0;
                                                    int radius = 0;
                                                    World world = player.getWorld();
                                                    Location spawn = world.getSpawnLocation();
                                                    int x = spawn.getBlockX();
                                                    int y = spawn.getBlockY();
                                                    int z = spawn.getBlockZ();
                                                    int playerx = player.getLocation().getBlockX();
                                                    int playery = player.getLocation().getBlockY();
                                                    int playerz = player.getLocation().getBlockZ();

                                                    if (playerx >= x+60 && Deathmatch <= 240||
                                                            playery >= y+60 && Deathmatch <= 240 ||
                                                            playerz >= z+110) {
                                                        player.getWorld().strikeLightning(player.getLocation());
                                                        player.sendMessage(Main.prefix + ChatColor.DARK_RED + "Please return to spawn" + ChatColor.DARK_GRAY + "!");
                                                        Bukkit.getLogger().log(Level.INFO,"SPAWN FOR " + player.getWorld() + " is " + Arena.this.getMap().getSpawn(0).getWorld().getSpawnLocation());
                                                    }
                                                    if (player.getWorld().getSpawnLocation().distance(player.getLocation())>= 55D && Deathmatch <= 200) {
                                                        player.getWorld().strikeLightning(player.getLocation());
                                                        player.sendMessage(Main.prefix + ChatColor.DARK_RED + "Please return to spawn" + ChatColor.DARK_GRAY + "!");
                                                    }
                                                    if (player.getWorld().getSpawnLocation().distance(player.getLocation())>= 50D && Deathmatch <= 180) {
                                                        player.getWorld().strikeLightning(player.getLocation());
                                                        player.sendMessage(Main.prefix + ChatColor.DARK_RED + "Please return to spawn" + ChatColor.DARK_GRAY + "!");
                                                    }
                                                    if (player.getWorld().getSpawnLocation().distance(player.getLocation())>= 45D && Deathmatch <= 140) {
                                                        player.getWorld().strikeLightning(player.getLocation());
                                                        player.sendMessage(Main.prefix + ChatColor.DARK_RED + "Please return to spawn" + ChatColor.DARK_GRAY + "!");
                                                    }
                                                    if (player.getWorld().getSpawnLocation().distance(player.getLocation())>= 35D && Deathmatch <= 100) {
                                                        player.getWorld().strikeLightning(player.getLocation());
                                                        player.sendMessage(Main.prefix + ChatColor.DARK_RED + "Please return to spawn" + ChatColor.DARK_GRAY + "!");
                                                    }
                                                    if (player.getWorld().getSpawnLocation().distance(player.getLocation())>= 30D && Deathmatch <= 80) {
                                                        player.getWorld().strikeLightning(player.getLocation());
                                                        player.sendMessage(Main.prefix + ChatColor.DARK_RED + "Please return to spawn" + ChatColor.DARK_GRAY + "!");
                                                    }
                                                    if (player.getWorld().getSpawnLocation().distance(player.getLocation())>= 25D && Deathmatch <= 60) {
                                                        player.getWorld().strikeLightning(player.getLocation());
                                                        player.sendMessage(Main.prefix + ChatColor.DARK_RED + "Please return to spawn" + ChatColor.DARK_GRAY + "!");
                                                    }
                                                    if (player.getWorld().getSpawnLocation().distance(player.getLocation())>=20D && Deathmatch <= 45) {
                                                        player.getWorld().strikeLightning(player.getLocation());
                                                        player.sendMessage(Main.prefix + ChatColor.DARK_RED + "Please return to spawn" + ChatColor.DARK_GRAY + "!");
                                                    }
                                                    if (player.getWorld().getSpawnLocation().distance(player.getLocation())>= 15D && Deathmatch <= 30) {
                                                        player.getWorld().strikeLightning(player.getLocation());
                                                        player.sendMessage(Main.prefix + ChatColor.DARK_RED + "Please return to spawn" + ChatColor.DARK_GRAY + "!");
                                                    }
                                                    if (player.getWorld().getSpawnLocation().distance(player.getLocation())>= 10D && Deathmatch <= 10) {
                                                        player.getWorld().strikeLightning(player.getLocation());
                                                        player.sendMessage(Main.prefix + ChatColor.DARK_RED + "Please return to spawn" + ChatColor.DARK_GRAY + "!");
                                                    }
                                                    if (player.getWorld().getSpawnLocation().distance(player.getLocation()) >= 5D && Deathmatch <= 5) {
                                                        player.getWorld().strikeLightning(player.getLocation());
                                                        player.sendMessage(Main.prefix + ChatColor.DARK_RED + "Please return to spawn" + ChatColor.DARK_GRAY + "!");
                                                    }
                                                    if (player.getWorld().getSpawnLocation().distance(player.getLocation())>= 3D && Deathmatch <= 3) {
                                                        player.getWorld().strikeLightning(player.getLocation());
                                                        player.sendMessage(Main.prefix + ChatColor.DARK_RED + "Please return to spawn" + ChatColor.DARK_GRAY + "!");
                                                    }
                                                    if (player.getWorld().getSpawnLocation().distance(player.getLocation())>= 2D && Deathmatch <= 2) {
                                                        player.getWorld().strikeLightning(player.getLocation());
                                                        player.sendMessage(Main.prefix + ChatColor.DARK_RED + "Please return to spawn" + ChatColor.DARK_GRAY + "!");
                                                    }
                                                    if (player.getWorld().getSpawnLocation().distance(player.getLocation())>= 1D && Deathmatch <= 1) {
                                                        player.getWorld().strikeLightning(player.getLocation());
                                                        player.sendMessage(Main.prefix + ChatColor.DARK_RED + "Please return to spawn" + ChatColor.DARK_GRAY + "!");
                                                    }
                                                }
                                            }
                                            if (Arena.this.Deathmatch > 0) {
                                                Arena.this.Deathmatch -= 1;
                                            } else {
                                                Arena.this.stopGame();
                                                cancel();
                                            }
                                        }
                                    }.runTaskTimer(Main.getInstance(), 20L, 20L);
                                }
                            }
                        }.runTaskTimer(Main.getInstance(), 20L, 20L);
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 20L, 20L);
    }

    public int getRand(Inventory inv) {
        Random rand = new Random();
        int i = rand.nextInt(26);
        if (inv.getItem(i) != null) {
            return getRand(inv);
        }
        return i;
    }


    public void stopGame() {
        PointManager pm = new PointManager();
        this.cleanup = true;
        canPickup = false;
        for (Player watch : this.watching) {
            cleanupboard(watch);
        }

        if (this.ingame.size() > 0) {
            for (Player ingame : getIngame()) {
                Storage storage = new Storage();
                storage.addWins(ingame);
                TextComponent message = new TextComponent( "§b§lClick here to view your legacy for this match" );
                message.setHoverEvent( new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7View legacy for "+ ingame.getDisplayName()).create() ) );
                message.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND ,"/legacy" ));
                ingame.spigot().sendMessage(message);
            }
            Wagers.giveWager(ingame.get(0),Wagers.getCurrentpot());
            if(Bounties.hasBounty(ingame.get(0))){
                Bounties.claimWinnerBounty(ingame.get(0));
            }
            if(Grade.getPlacementMatches(ingame.get(0).getUniqueId().toString(),"UUID") >0){
                Grade.setPlacements(ingame.get(0).getUniqueId().toString(),Grade.getPlacementMatches(ingame.get(0).getUniqueId().toString(),"UUID") -1);

            }
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                p.sendMessage(Main.getInstance().prefix + ChatColor.GREEN + "The games have ended!");
                p.sendMessage(Main.getInstance().prefix + (this.ingame.get(0)).getDisplayName() +
                        ChatColor.GREEN + " has won Sonder UHC Games!");
                Main.setGameState(GameState.SHUTTING_DOWN);
                joinable = false;
                Main.canDamage = false;
            }
            int i = 0;
            for(Player allplayers : Bukkit.getOnlinePlayers()){
                i++;
                Utils.shootfireWork(ingame.get(0).getLocation(),allplayers, FireworkEffect.Type.CREEPER,Color.PURPLE, Color.GRAY,2,true,true);

            }
            for (final Player player : Bukkit.getServer().getOnlinePlayers()) {
                Bounties bounty = new Bounties();
                player.getInventory().setArmorContents(null);
                player.setGameMode(GameMode.CREATIVE);
                player.getInventory().clear();
                cleanupboard(player);
                new BukkitRunnable() {
                    public void run() {

                        if (Arena.this.cleanuptime > 0) {
                            Arena.this.cleanuptime -= 1;
                            if (Arena.this.cleanuptime <= 10) {
                                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                                    Main.SendToServer(player.getName(), "lobby", player);

                                }
                            }
                        } else {
                            Main.SendToServer(player.getName(), "lobby", player);
                            ConsoleCommandSender console = Bukkit.getConsoleSender();
                            Bukkit.dispatchCommand(console, "minecraft:kill @e[type=Item]");
                            Bukkit.dispatchCommand(console, "restart");
                        }

                    }
                }.runTaskTimer(Main.getInstance(), 20L, 20L);
                this.ended = true;
                new BukkitRunnable() {
                    public void run() {
                        for (Entity ent : Arena.this.getMap().getSpawn(0).getWorld().getEntities()) {
                            if ((ent instanceof Item)) {
                                ent.remove();
                            }
                        }
                        Arena.this.ingame.remove(player);
                        Main.SendToServer(player.getName(), "lobby", player);


                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "restart");
                        Bukkit.shutdown();
                        Main.getInstance().arenas.remove(Arena.this);
                        ShutDown.run();
                    }
                }.runTaskLater(Main.getInstance(), 20*30);
            }
        }
    }

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



    public void setBy(String name) {
        Main.getInstance().getConfig().set("arena." + this.map.MAP_NAME + ".made", name);
        Main.getInstance().saveConfig();
    }

    public String getBy() {
        return Main.getInstance().getConfig().getString("arena." + this.map.MAP_NAME + ".made");
    }

    public static void registerNameTags(Player player){

    Team t = s.registerNewTeam(player.getName());

    }

    public static void unregisterNames(Player player){
        Team t = s.getTeam(player.getName());
        t.unregister();
    }
    public void newBoardPreLobby1(final Player player) {

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("main", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.LIGHT_PURPLE + "Lobby");
        Team t = board.registerNewTeam("grade");
        Team dev = board.registerNewTeam("dev");
        dev.setPrefix("SPECIAL TEST");
        t.setPrefix(ChatColor.DARK_RED+"");
      //  if(Rank.getRankLevel(player) ==Rank.ConvertRankNameToInt("Developer")){
        //     dev.addEntry(player.getName());
        //  }
        // if(Rank.getRankLevel(player) ==Rank.ConvertRankNameToInt("Admin") ||
        //         Rank.getRankLevel(player) ==Rank.ConvertRankNameToInt("Owner")||
        //         Rank.getRankLevel(player) ==Rank.ConvertRankNameToInt("SrMod")){
        //     t.addEntry(player.getName());
        //  }
        // for(Player ap : Bukkit.getServer().getOnlinePlayers()){
        //     if(ap.isOp()){
        //         t.addEntry(ap.getName());
        //     }
        //    if(Rank.getRankLevel(ap) ==Rank.ConvertRankNameToInt("Developer")){
        //        dev.addEntry(ap.getName());
        //    }
        //    if(Rank.getRankLevel(ap) ==Rank.ConvertRankNameToInt("Admin") ||
                    //            Rank.getRankLevel(ap) ==Rank.ConvertRankNameToInt("Owner")||
                //            Rank.getRankLevel(ap) ==Rank.ConvertRankNameToInt("SrMod")){
        //        t.addEntry(ap.getName());
        //    }
        // }
        final Objective objective = board.registerNewObjective("health", "health");
        objective.setDisplayName("§4 ❤");
            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);

        Score you = obj.getScore(
                Bukkit.getOfflinePlayer("§7» §d§lYou §7«"));
        you.setScore(16);

        Score spacer1 = obj.getScore(
                Bukkit.getOfflinePlayer(ChatColor.GRAY+" "));
        spacer1.setScore(14);











        Score p = obj.getScore(Bukkit.getOfflinePlayer( "§7» §d§lRank §7«"));
        p.setScore(13);





        Score info = obj.getScore(Bukkit.getOfflinePlayer( "§7» §d§lInfo§7 «"));
        info.setScore(10);

        Score blank2 = obj.getScore(Bukkit.getOfflinePlayer(org.bukkit.ChatColor.GRAY + "    "));
        blank2.setScore(4);


        Score server = obj.getScore(
                Bukkit.getOfflinePlayer("§7» §d§lGame §7«"));
        server.setScore(6);




        Score blank6 = obj.getScore(Bukkit.getOfflinePlayer(org.bukkit.ChatColor.GRAY + "              "));
        blank6.setScore(7);





        final Team Watching = board.registerNewTeam("watching");
        Watching.addEntry(ChatColor.WHITE.toString() +"§dWatching§8:" );
        obj.getScore(ChatColor.WHITE.toString() +"§dWatching§8:" ).setScore(2);

        final Team Playing = board.registerNewTeam("playing");
        Playing.addEntry(ChatColor.WHITE.toString() +"§dPlaying§8:" + ChatColor.WHITE);
        obj.getScore(ChatColor.WHITE.toString() +"§dPlaying§8:" + ChatColor.WHITE).setScore(3);
        int left = Grade.getPlacementMatches(player.getUniqueId().toString(),"UUID");
        if(left >0){
            final Team POINTS = board.registerNewTeam("points");
            POINTS.addEntry("§c"+left +" Placements");
            obj.getScore("§c"+left +" Placements ").setScore(11);
            final Team Rank = board.registerNewTeam("rank");
            Rank.addEntry( "        ");
            obj.getScore("        ").setScore(12);


        }else {
            final Team POINTS = board.registerNewTeam("points2");
            POINTS.addEntry("§c" + Grade.getGradePoints(player.getUniqueId().toString(), "UUID") + "§8/§a1600");
            obj.getScore("§c" + Grade.getGradePoints(player.getUniqueId().toString(), "UUID") + "§8/§a1600").setScore(11);
            final Team Rank = board.registerNewTeam("rank2");
            Rank.addEntry(Grade.getGrade(player.getUniqueId().toString(),"UUID")+ " " + org.bukkit.ChatColor.WHITE);
            obj.getScore(Grade.getGrade(player.getUniqueId().toString(),"UUID")+ " " + org.bukkit.ChatColor.WHITE).setScore(12);

        }


        final Team date = board.registerNewTeam("date");
        date.addEntry(org.bukkit.ChatColor.WHITE.toString());
        date.setSuffix(ChatColor.GRAY+getDate());
        obj.getScore(org.bukkit.ChatColor.WHITE.toString()).setScore(9);


        final Team advtime = board.registerNewTeam("advtime");
        advtime.addEntry(org.bukkit.ChatColor.GREEN.toString() + ""+org.bukkit.ChatColor.WHITE);
        obj.getScore(org.bukkit.ChatColor.GREEN.toString() + org.bukkit.ChatColor.WHITE).setScore(8);

        final Team Server = board.registerNewTeam("server");
        Server.addEntry(org.bukkit.ChatColor.DARK_AQUA.toString() + "§5US§8:" + " " + org.bukkit.ChatColor.WHITE);
        Server.setSuffix(ChatColor.GREEN + Bukkit.getServer().getServerId());
        obj.getScore(org.bukkit.ChatColor.DARK_AQUA.toString() + "§5US§8:" + " " + org.bukkit.ChatColor.WHITE).setScore(5);


        final Team You = board.registerNewTeam("you");
        You.addEntry(ChatColor.GRAY.toString() + player.getName() + " " + org.bukkit.ChatColor.WHITE);
        obj.getScore(org.bukkit.ChatColor.GRAY.toString() + player.getName() + " " + org.bukkit.ChatColor.WHITE).setScore(15);

        player.setScoreboard(board);
        new BukkitRunnable() {
            public void run() {

                advtime.setSuffix(ChatColor.DARK_GRAY + getAdvancedTime());
                board.getTeam("playing").setSuffix("§a " + ingame.size());
                board.getTeam("watching").setSuffix("§a " + watching.size());



                if (Arena.this.addTime) {
                    int minutes = Arena.this.prelobby / 60;
                    int seconds = Arena.this.prelobby % 60;
                    String disMinu = (minutes < 10 ? "0" : "") + minutes;
                    String disSec = (seconds < 10 ? "0" : "") + seconds;
                    String formattedTime = disMinu + ":" + disSec;

                    int minutes1 = timeremaining/ 60;
                    int seconds1 = timeremaining% 60;
                    String disMinu1 = (minutes1 < 10 ? "0" : "") + minutes1;
                    String disSec1 = (seconds1 < 10 ? "0" : "") + seconds1;
                    String formattedTime1 = disMinu1 + ":" + disSec1;
                    obj.setDisplayName(
                            ChatColor.translateAlternateColorCodes('&', "&dLobby " + ChatColor.GRAY + formattedTime));
                    if (Arena.joinable == true) {
                        obj.setDisplayName(
                                ChatColor.translateAlternateColorCodes('&', "&dLobby " + ChatColor.GRAY + formattedTime));

                    }
                    if (Arena.joinable == false) {
                        obj.setDisplayName(
                                ChatColor.translateAlternateColorCodes('&', "&dLiveGame " + ChatColor.GRAY + formattedTime1));
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 20L, 20L);
    }

    public void cleanupboard(final Player player) {

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("main", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.AQUA + "Lobby");

        final Objective objective = board.registerNewObjective("health", "health");
        objective.setDisplayName("§4 ❤");

        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        Score you = obj.getScore(
                Bukkit.getOfflinePlayer("§7» §d§lYou §7«"));
        you.setScore(16);

        Score spacer1 = obj.getScore(
                Bukkit.getOfflinePlayer(ChatColor.GRAY+" "));
        spacer1.setScore(14);











        Score p = obj.getScore(Bukkit.getOfflinePlayer( "§7» §d§lRank §7«"));
        p.setScore(13);






        Score info = obj.getScore(Bukkit.getOfflinePlayer( "§7» §d§lInfo§7 «"));
        info.setScore(10);

        Score blank2 = obj.getScore(Bukkit.getOfflinePlayer(org.bukkit.ChatColor.GRAY + "    "));
        blank2.setScore(4);


        Score server = obj.getScore(
                Bukkit.getOfflinePlayer("§7» §d§lGame §7«"));
        server.setScore(6);




        Score blank6 = obj.getScore(Bukkit.getOfflinePlayer(org.bukkit.ChatColor.GRAY + "              "));
        blank6.setScore(7);





        final Team Watching = board.registerNewTeam("watching");
        Watching.addEntry(ChatColor.WHITE.toString() +"§dWatching§8:" );
        obj.getScore(ChatColor.WHITE.toString() +"§dWatching§8:" ).setScore(2);

        final Team Playing = board.registerNewTeam("playing");
        Playing.addEntry(ChatColor.WHITE.toString() +"§dPlaying§8:" + ChatColor.WHITE);
        obj.getScore(ChatColor.WHITE.toString() +"§dPlaying§8:" + ChatColor.WHITE).setScore(3);

        int left = Grade.getPlacementMatches(player.getUniqueId().toString(),"UUID");
        if(left >0){
            final Team POINTS = board.registerNewTeam("cpointss");
            POINTS.addEntry("§c"+left +" Placements");
            obj.getScore("§c"+left +" Placements ").setScore(11);
            final Team Rank = board.registerNewTeam("crankk");
            Rank.addEntry( "        ");
            obj.getScore("        ").setScore(12);


        }else {
            final Team POINTS = board.registerNewTeam("cpointsw");
            POINTS.addEntry("§c" + Grade.getGradePoints(player.getUniqueId().toString(), "UUID") + "§8/§a1600");
            obj.getScore("§c" + Grade.getGradePoints(player.getUniqueId().toString(), "UUID") + "§8/§a1600").setScore(11);
            final Team Rank = board.registerNewTeam("crankw");
            Rank.addEntry(Grade.getGrade(player.getUniqueId().toString(),"UUID")+ " " + org.bukkit.ChatColor.WHITE);
            obj.getScore(Grade.getGrade(player.getUniqueId().toString(),"UUID")+ " " + org.bukkit.ChatColor.WHITE).setScore(12);

        }
        final Team date = board.registerNewTeam("date");
        date.addEntry(org.bukkit.ChatColor.WHITE.toString());
        date.setSuffix(ChatColor.GRAY+getDate());
        obj.getScore(org.bukkit.ChatColor.WHITE.toString()).setScore(9);


        final Team advtime = board.registerNewTeam("advtime");
        advtime.addEntry(org.bukkit.ChatColor.GREEN.toString() + ""+org.bukkit.ChatColor.WHITE);
        obj.getScore(org.bukkit.ChatColor.GREEN.toString() + org.bukkit.ChatColor.WHITE).setScore(8);

        final Team Server = board.registerNewTeam("server");
        Server.addEntry(org.bukkit.ChatColor.DARK_AQUA.toString() + "§dUS§8:" + " " + org.bukkit.ChatColor.WHITE);
        Server.setSuffix(ChatColor.GREEN + Bukkit.getServer().getServerId());
        obj.getScore(org.bukkit.ChatColor.DARK_AQUA.toString() + "§dUS§8:" + " " + org.bukkit.ChatColor.WHITE).setScore(5);



        final Team You = board.registerNewTeam("you");
        You.addEntry(org.bukkit.ChatColor.GRAY.toString() + player.getName() + " " + org.bukkit.ChatColor.WHITE);
        obj.getScore(org.bukkit.ChatColor.GRAY.toString() + player.getName() + " " + org.bukkit.ChatColor.WHITE).setScore(15);

        player.setScoreboard(board);
        new BukkitRunnable() {
            public void run() {

                advtime.setSuffix(ChatColor.DARK_GRAY + getAdvancedTime());
                board.getTeam("playing").setSuffix("§a " + ingame.size());
                board.getTeam("watching").setSuffix("§a " + watching.size());




                int minutes = cleanuptime / 60;
                int seconds = cleanuptime % 60;
                String disMinu = (minutes < 10 ? "0" : "") + minutes;
                String disSec = (seconds < 10 ? "0" : "") + seconds;
                String formattedTime = disMinu + ":" + disSec;
                obj.setDisplayName(
                        ChatColor.translateAlternateColorCodes('&', "§dClean Up " + ChatColor.GRAY + formattedTime));

            }
        }.runTaskTimer(Main.getInstance(), 20L, 20L);
    }

    public void deathmatchBoard(final Player player) {

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("main", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.AQUA + "Lobby");

        final Objective objective = board.registerNewObjective("health", "health");
        objective.setDisplayName("§4 ❤");

        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        Score you = obj.getScore(
                Bukkit.getOfflinePlayer("§7» §d§lYou §7«"));
        you.setScore(16);

        Score spacer1 = obj.getScore(
                Bukkit.getOfflinePlayer(ChatColor.GRAY+" "));
        spacer1.setScore(14);











        Score p = obj.getScore(Bukkit.getOfflinePlayer( "§7» §d§lRank §7«"));
        p.setScore(13);






        Score info = obj.getScore(Bukkit.getOfflinePlayer( "§7» §d§lInfo§7 «"));
        info.setScore(10);

        Score blank2 = obj.getScore(Bukkit.getOfflinePlayer(org.bukkit.ChatColor.GRAY + "    "));
        blank2.setScore(4);


        Score server = obj.getScore(
                Bukkit.getOfflinePlayer("§7» §d§lGame§7 «"));
        server.setScore(6);




        Score blank6 = obj.getScore(Bukkit.getOfflinePlayer(org.bukkit.ChatColor.GRAY + "              "));
        blank6.setScore(7);





        final Team Watching = board.registerNewTeam("watching");
        Watching.addEntry(ChatColor.WHITE.toString() +"§dWatching§8:" );
        obj.getScore(ChatColor.WHITE.toString() +"§dWatching§8:" ).setScore(2);

        final Team Playing = board.registerNewTeam("playing");
        Playing.addEntry(ChatColor.WHITE.toString() +"§dPlaying§8:" + ChatColor.WHITE);
        obj.getScore(ChatColor.WHITE.toString() +"§dPlaying§8:" + ChatColor.WHITE).setScore(3);
        int left = Grade.getPlacementMatches(player.getUniqueId().toString(),"UUID");

        if(left >0){
            final Team POINTS = board.registerNewTeam("dpoints");
            POINTS.addEntry("§c"+left +" Placements");
            obj.getScore("§c"+left +" Placements ").setScore(11);
            final Team Rank = board.registerNewTeam("dranker");
            Rank.addEntry( "        ");
            obj.getScore("        ").setScore(12);


        }else {
            final Team POINTS = board.registerNewTeam("pts");
            POINTS.addEntry("§c" + Grade.getGradePoints(player.getUniqueId().toString(), "UUID") + "§8/§a1600");
            obj.getScore("§c" + Grade.getGradePoints(player.getUniqueId().toString(), "UUID") + "§8/§a1600").setScore(11);
            final Team Rank = board.registerNewTeam("randid");
            Rank.addEntry(Grade.getGrade(player.getUniqueId().toString(),"UUID")+ " " + org.bukkit.ChatColor.WHITE);
            obj.getScore(Grade.getGrade(player.getUniqueId().toString(),"UUID")+ " " + org.bukkit.ChatColor.WHITE).setScore(12);

        }

        final Team date = board.registerNewTeam("date");
        date.addEntry(org.bukkit.ChatColor.WHITE.toString());
        date.setSuffix(ChatColor.GRAY+getDate());
        obj.getScore(org.bukkit.ChatColor.WHITE.toString()).setScore(9);


        final Team advtime = board.registerNewTeam("advtime");
        advtime.addEntry(org.bukkit.ChatColor.GREEN.toString() + ""+org.bukkit.ChatColor.WHITE);
        obj.getScore(org.bukkit.ChatColor.GREEN.toString() + org.bukkit.ChatColor.WHITE).setScore(8);

        final Team Server = board.registerNewTeam("server");
        Server.addEntry(org.bukkit.ChatColor.DARK_AQUA.toString() + "§5US§8:" + " " + org.bukkit.ChatColor.WHITE);
        Server.setSuffix(ChatColor.GREEN + Bukkit.getServer().getServerId());
        obj.getScore(org.bukkit.ChatColor.DARK_AQUA.toString() + "§5US§8:" + " " + org.bukkit.ChatColor.WHITE).setScore(5);



        final Team You = board.registerNewTeam("you");
        You.addEntry(org.bukkit.ChatColor.GRAY.toString() + player.getName() + " " + org.bukkit.ChatColor.WHITE);
        obj.getScore(org.bukkit.ChatColor.GRAY.toString() + player.getName() + " " + org.bukkit.ChatColor.WHITE).setScore(15);

        player.setScoreboard(board);
        new BukkitRunnable() {
            public void run() {

                advtime.setSuffix(ChatColor.DARK_GRAY + getAdvancedTime());
                board.getTeam("playing").setSuffix("§a " + ingame.size());
                board.getTeam("watching").setSuffix("§a " + watching.size());



                int minutes = Arena.this.Deathmatch / 60;
                    int seconds = Arena.this.Deathmatch % 60;
                    String disMinu = (minutes < 10 ? "0" : "") + minutes;
                    String disSec = (seconds < 10 ? "0" : "") + seconds;
                    String formattedTime = disMinu + ":" + disSec;
                    obj.setDisplayName(
                            ChatColor.translateAlternateColorCodes('&', "§dDeathmatch " + ChatColor.GRAY + formattedTime));

            }
        }.runTaskTimer(Main.getInstance(), 20L, 20L);
    }
}


