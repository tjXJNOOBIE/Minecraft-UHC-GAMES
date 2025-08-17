package Events;

import info.techwizmatt.ServerCore.Rank.Rank;
import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;


import java.util.ArrayList;
import java.util.Random;

public class LoginEvent implements Listener{

	public static ArrayList<Player> nondonors = new ArrayList<>();

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		Player player = e.getPlayer();
		int full = Main.getInstance().arenas.get(0).ingame.size();
		int online = Main.getInstance().arenas.get(0).ingame.size();
		int random = new Random().nextInt(online);
			if(Rank.getRankLevel(player)==1){
				this.nondonors.add(player);
			}

		Player randomplayer = (Player) nondonors.toArray()[random];
		if(full == Bukkit.getServer().getMaxPlayers() && Rank.getRankLevel(player)==1 && Arena.joinable) {
			e.disallow(Result.KICK_OTHER,"§d§lThe match is full§8! §dVisit §5§lsonder.rip §dto reserve a slot§8!");
			return;
		}
		if(full == Bukkit.getServer().getMaxPlayers() && Rank.getRankLevel(player)==12 && Arena.joinable) {
			randomplayer.sendMessage(Main.prefix + "You were kicked to make room for a staff member.");
			Main.SendToServer(randomplayer.getName(), "lobby", randomplayer);
			e.allow();
			return;
		}
		if(full == Bukkit.getServer().getMaxPlayers() && Rank.getRankLevel(player)==11 && Arena.joinable) {
			randomplayer.sendMessage(Main.prefix + "You were kicked to make room for a staff member.");
			Main.SendToServer(randomplayer.getName(), "lobby", randomplayer);
			e.allow();
			return;
		}

		if(full == Bukkit.getServer().getMaxPlayers()  && Rank.getRankLevel(player)==7 &&  Arena.joinable) {
			randomplayer.sendMessage(Main.prefix + "You were kicked to make room for a donor. Purchase a rank @ blimpsg.buycraft.net");
			Main.SendToServer(randomplayer.getName(), "lobby", randomplayer);
			e.allow();
			return;
		}
		if(full == Bukkit.getServer().getMaxPlayers()  && Rank.getRankLevel(player)==6 &&  Arena.joinable) {
			randomplayer.sendMessage(Main.prefix + "You were kicked to make room for a donor. Purchase a rank @ blimpsg.buycraft.net");
			Main.SendToServer(randomplayer.getName(), "lobby", randomplayer);
			e.allow();
			return;
		}
		if(full == Bukkit.getServer().getMaxPlayers()  && Rank.getRankLevel(player)==5 && Arena.joinable) {
			randomplayer.sendMessage(Main.prefix + "You were kicked to make room for a Iron. Purchase a rank @ blimpsg.buycraft.net");
			Main.SendToServer(randomplayer.getName(), "lobby", randomplayer);
			e.allow();
			return;
		}
		if(full == Bukkit.getServer().getMaxPlayers()  && Rank.getRankLevel(player)==8 &&  Arena.joinable) {
			randomplayer.sendMessage(Main.prefix + "You were kicked to make room for a Mod. Purchase a rank @ blimpsg.buycraft.net");
			Main.SendToServer(randomplayer.getName(), "lobby", randomplayer);
			e.allow();
			return;
		}
		if(full == Bukkit.getServer().getMaxPlayers()  && Rank.getRankLevel(player)==3 &&  Arena.joinable) {
			randomplayer.sendMessage(Main.prefix + "You were kicked to make room for a VIP. Purchase a rank @ blimpsg.buycraft.net");
			Main.SendToServer(randomplayer.getName(), "lobby", randomplayer);
			e.allow();
			return;
		}
		if(full == Bukkit.getServer().getMaxPlayers()  && Rank.getRankLevel(player)==9 &&  Arena.joinable) {
			randomplayer.sendMessage(Main.prefix + "You were kicked to make room for a SrMod. Purchase a rank @ blimpsg.buycraft.net");
			Main.SendToServer(randomplayer.getName(), "lobby", randomplayer);
			e.allow();
			return;
		}
		if(full == Bukkit.getServer().getMaxPlayers()  && Rank.getRankLevel(player)==10 &&  Arena.joinable) {
			randomplayer.sendMessage(Main.prefix + "You were kicked to make room for a Admin. Purchase a rank @ blimpsg.buycraft.net");
			Main.SendToServer(randomplayer.getName(), "lobby", randomplayer);
			e.allow();
			return;
		}


		if(Rank.getRankLevel(player) >= 7&& Arena.joinable && full == Bukkit.getServer().getMaxPlayers()) {
			e.disallow(Result.KICK_OTHER, Main.prefix + "You may not kick " + randomplayer.getDisplayName());
			return;
		}
		if(Rank.getRankLevel(player) == 3&& Arena.joinable && full == Bukkit.getServer().getMaxPlayers()) {
			e.disallow(Result.KICK_OTHER, Main.prefix + "You may not kick " + randomplayer.getDisplayName());
			return;
		}
		if(Rank.getRankLevel(player) >= 4&& Arena.joinable && full == Bukkit.getServer().getMaxPlayers()) {
			e.disallow(Result.KICK_OTHER, Main.prefix + "You may not kick " + randomplayer.getDisplayName());
			return;
		}
		if(Arena.joinable == false && full < 24) {
			e.allow();
			return;
		}
		if(full < Bukkit.getServer().getMaxPlayers()) {
			e.allow();
			return;
		}
		if(full == Bukkit.getServer().getMaxPlayers() && Arena.joinable == false) {
			e.disallow(Result.KICK_OTHER, Main.prefix + "The server is full!");
			return;
		}
	}

}
