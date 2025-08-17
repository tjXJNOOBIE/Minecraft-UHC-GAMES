package dev.tjxjnoobie.uhcgames.managers.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class PlayerManager implements Listener{
	
	private final Plugin plugin;
	private final Map<PlayerState,List<UUID>> players;
	
	public PlayerManager(Plugin plugin){
		this.players = new HashMap<PlayerState,List<UUID>>();
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		for (PlayerState state : PlayerState.values()){
			this.players.put(state, new ArrayList<UUID>());
		}
	}
	
	@EventHandler
	private void onJoin(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		
		setPlayerState(player.getUniqueId(), PlayerState.SPECTATOR);
	}
	
	@EventHandler
	private void onPlayerDamage(EntityDamageByEntityEvent e)
	{
		if (e.getEntity() instanceof Player){
			Player player = (Player)e.getEntity();
			if (e.getDamager() instanceof Player){
				
				if (this.players.get(PlayerState.SPECTATOR).contains(player.getUniqueId())){
					e.setCancelled(true);
				}else{
					e.setCancelled(false);//bc idk what the fuck java version we on.
				}
			}
		}
	}
	
	@EventHandler
	private void onQuit(PlayerQuitEvent e)
	{
		PlayerState state = getState(e.getPlayer().getUniqueId());
		
		if (players.get(state).contains(e.getPlayer().getUniqueId())){
			players.get(state).remove(e.getPlayer().getUniqueId());
		}
	}
	
	public void setPlayerState(UUID uuid, PlayerState to)
	{
		
		PlayerState from = getState(uuid);
		if (players.get(from).contains(uuid)){
			players.remove(uuid);
		}
		
		players.get(to).add(uuid);

		
	}
	
	public PlayerState getState(UUID uuid)
	{
		if (this.players.get(PlayerState.PARTICIPANT).contains(uuid)){
			return PlayerState.PARTICIPANT;
		}else{
			return PlayerState.SPECTATOR;
		}
	}
	
	
	

}
