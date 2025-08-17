package Events;

import info.techwizmatt.ServerCore.API.CoinAPI;
import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;
import dev.tjxjnoobie.uhcgames.managers.Bounties;
import dev.tjxjnoobie.uhcgames.managers.SpectatorManager;
import dev.tjxjnoobie.uhcgames.other.Storage;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class DeathEvent implements Listener{

	public static HashMap<Entity,EntityDamageEvent.DamageCause> dmg = new HashMap<Entity,EntityDamageEvent.DamageCause>();

	@EventHandler
	public void onDeath(final PlayerDeathEvent event) {
		final Player p = event.getEntity();
			event.setDeathMessage("");
			final String playerusername = p.getName();
			final String playeruuid = p.getUniqueId().toString();
			EntityDamageEvent playerdmg = p.getLastDamageCause();
			final Player k = event.getEntity().getKiller();
			int take = 10;
			int StolenTokens = CoinAPI.GetTokens(p) * take / 100;

		SpectatorManager manager = new SpectatorManager();
			Storage storage = new Storage();
			Main.getInstance().arenas.get(0).ingame.remove(p);
			World w = p.getWorld();
		     p.addPotionEffect(
		                new PotionEffect(PotionEffectType.WEAKNESS, 1000000, 1000, true), false);
				Arena.players.remove(p.getName());
			for(Player ap : Bukkit.getServer().getOnlinePlayers()){
				ap.sendMessage(Main.prefix + ChatColor.GRAY + "Only " + ChatColor.DARK_GRAY + "["
						+ ChatColor.LIGHT_PURPLE + (Main.getInstance().arenas.get(0).ingame.size()) + ChatColor.DARK_GRAY + "]"
						+ ChatColor.GRAY + " tributes remain!");
				ap.sendMessage(Main.prefix + ChatColor.GRAY + "There are " + ChatColor.DARK_GRAY + "["
						+ ChatColor.LIGHT_PURPLE + (Main.getInstance().arenas.get(0).watching.size() + 1) + ChatColor.DARK_GRAY + "]"
						+ ChatColor.GRAY + " spectators watching the game.");
			    ap.sendMessage(ChatColor.DARK_PURPLE + "A cannon can be heard in the distance in memorial for "
							+ ChatColor.AQUA + event.getEntity().getDisplayName() + ChatColor.DARK_GRAY + ".");

			}



	 			if(Main.tag.containsKey(p) ){
				manager.makeSpec(p);
				Main.tag.remove(p);
				}

	
		     
	
				if (Bounties.hasBounty(p)) {
					for(Player ap : Bukkit.getServer().getOnlinePlayers()) {
						Bounties.claimBounty(p,k);
						Bounties.bounties.remove(event.getEntity());
					}

				}
				w.strikeLightning(p.getLocation().add(0, 7, 0));
	 			p.setAllowFlight(true);
			    p.setFlying(true);
				p.setVelocity(p.getLocation().getDirection().multiply(0.5));
				p.setVelocity(new Vector(p.getVelocity().getX(), 0.7D, p.getVelocity().getZ()));	
				Location location = p.getLocation();
			    location.getWorld().playEffect(location, Effect.SMOKE, 0);

		p.sendMessage(Main.prefix + "§3You've lost §8(§e"  + StolenTokens +"§8)§3 coins for dying§8." );
		k.sendMessage(Main.prefix + "§3You've gained §8(§e"  + StolenTokens+ "§8)§3 coins for killing " + p.getDisplayName() + "§8!");
		p.setHealth(20);
		p.setFoodLevel(20);
		storage.addLosses(p);
		storage.addKill(k);
		storage.addStreak(k);
		storage.addDeaths(p);
		CoinAPI.AddTokens(k,StolenTokens);
		CoinAPI.subtractTokens(p,StolenTokens);
		ItemStack GoldenHead = new ItemStack(Material.GOLDEN_APPLE, 1);
		ItemMeta GoldenHeadMeta = GoldenHead.getItemMeta();
		GoldenHeadMeta.setDisplayName(ChatColor.MAGIC + "" + ChatColor.DARK_BLUE + "--" + ChatColor.RESET + "" + ChatColor.GOLD + "" + ChatColor.BOLD + "Golden Head" + "" + ChatColor.RESET + "" +ChatColor.MAGIC + "" + ChatColor.DARK_BLUE + "--");
		GoldenHead.setItemMeta(GoldenHeadMeta);
			event.getDrops().add(GoldenHead);
			//Killstreak
		int kills = (int)storage.getKills(k);
		if(kills==3){
			k.sendMessage(Main.prefix+"You received 10 coins for going on a kill streak of §a" + kills);
			k.playSound(k.getLocation(),Sound.EXPLODE,1.0f,1.0f);
			k.playEffect(p.getLocation(),Effect.EXPLOSION,true);
			storage.addCoins(p,10);

		}
		if(kills==5){
			k.sendMessage(Main.prefix+"You received 20 coins for going on a kill streak of §a" + kills);
			k.playSound(k.getLocation(),Sound.EXPLODE,1.0f,1.0f);
			k.playEffect(p.getLocation(),Effect.EXPLOSION,true);
			storage.addCoins(p,20);
		}
		if(kills==7){
			k.sendMessage(Main.prefix+"You received 30 coins for going on a kill streak of §a" + kills);
			k.playSound(k.getLocation(),Sound.EXPLODE,1.0f,1.0f);
			k.playEffect(p.getLocation(),Effect.EXPLOSION,true);
			storage.addCoins(p,30);

		}
		if(kills==10){
			k.sendMessage(Main.prefix+"You received 45 coins for going on a kill streak of §a" + kills);
			k.playSound(k.getLocation(),Sound.EXPLODE,1.0f,1.0f);
			k.playEffect(p.getLocation(),Effect.EXPLOSION,true);
			storage.addCoins(p,45);


		}
				if((Main.getInstance().arenas.get(0).ingame.size() <= 1)) {
					for (Arena arena : Main.getInstance().arenas) {
							if(Bounties.hasBounty(Main.getInstance().arenas.get(0).ingame.get(0))) {
								for(Player ap : Bukkit.getServer().getOnlinePlayers()) {
								ap.sendMessage(Main.prefix + Main.getInstance().arenas.get(0).ingame.get(0).getDisplayName() +" claimed their own bounty of " + Bounties.getBounty(Main.getInstance().arenas.get(0).ingame.get(0)));
								Main.getInstance().arenas.get(0).ingame.get(0).sendMessage(Main.prefix + "You have claimed your own bounty of " + Bounties.getBounty(Main.getInstance().arenas.get(0).ingame.get(0)));
								Bounties.claimBounty(Main.getInstance().arenas.get(0).ingame.get(0),Main.getInstance().arenas.get(0).ingame.get(0));
							}
								arena.stopGame();
							
				
					}
					}
					
					if(Main.getInstance().arenas.get(0).getIngame().size() <= 3) {
						for (Arena arena : Main.getInstance().arenas) {					
							arena.startDM();
						}			
						
					}
				}
				
	}

}
