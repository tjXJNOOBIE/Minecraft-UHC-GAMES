package Events;

import dev.tjxjnoobie.uhcgames.Main;

import dev.tjxjnoobie.uhcgames.managers.Arena;
import dev.tjxjnoobie.uhcgames.other.Storage;
import dev.tjxjnoobie.uhcgames.other.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.logging.Level;

public class DamageEvents implements Listener {

	
	
	@EventHandler
	public void damageRegister(EntityDamageByEntityEvent event) {
		if(Arena.joinable){
			event.setCancelled(true);
			return;
		}
		Storage storage = new Storage();
		if ((event.getDamager() instanceof Player)) {
			if ((event.getEntity() instanceof Player)) {
				Main.fire.put((Player) event.getEntity(), (Player) event.getDamager());
			}
		} else if (((event.getDamager() instanceof Arrow)) && ((event.getEntity() instanceof Player))) {
			Arrow a = (Arrow) event.getDamager();
			Main.fire.put((Player) event.getEntity(), (Player) a.getShooter());
		}
		if ((event.getDamager() instanceof Arrow)) {
			Arrow a = (Arrow) event.getDamager();
			if ((a.getShooter() instanceof Player)) {
				a.getShooter();
				Player p = (Player) a.getShooter();

				Damageable dp = (Damageable) event.getEntity();
				if ((dp instanceof Player)) {
					Player v = (Player) dp;
					double distance = a.getLocation().distance(p.getLocation());
					double ptviev = dp.getHealth();
					Integer damage = Integer.valueOf((int) event.getFinalDamage());
					if ((!dp.isDead())) {
						if (Arena.joinable == true) {
							Bukkit.getLogger().log(Level.INFO,"Hit logged");
							return;
						} else {
							storage.addBowHit(p);
							p.sendMessage( v.getDisplayName() + " §7has §c§l " + Utils.getHealth(v) / 2 + " ❤§7's remaining");
						}
						if(distance >= 35&&v.isDead()){
							p.sendMessage(Main.prefix +"§c§lLongshot§f(§a"+distance+" §7Blocks§f)§8: §7You've received §a10§7 coins");
							p.playSound(p.getLocation(), Sound.EXPLODE,1.0f,1.0f);
						}

					}
				}
			}
		}

	}


	
	@EventHandler
	public void onCanDamage(EntityDamageByEntityEvent e) {
		if(Main.canDamage == true) {
			e.setCancelled(false);
			return;
		}
		if(Main.canDamage == false) {
			e.setCancelled(true);
			return;
		}
		if(e.getEntity().getWorld().getName().equals("MCSGLobby")) {
			e.setCancelled(true);
			return;

		}

	}
	   @EventHandler
       public void onEntityDamage(EntityDamageByEntityEvent e) {
               if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
                       Player hit = (Player) e.getEntity();
                       Player damager = (Player) e.getDamager();
                      
                       if (Main.tag.get(hit) != null) {
                               if (Main.tag.get(hit) != damager) ;
                               return;
                       }
                       else {
                    	   Main.tag.put(hit, damager);
                       }
               }
       }
	   @EventHandler
       public void onEntityDamage(EntityDamageEvent e) {
		   	Entity player = e.getEntity();
		   if(Main.getInstance().arenas.get(0).watching.contains(player)){
			   e.setCancelled(true);
			   return;
		   }
		   	if(!Main.canDamage){
		   		e.setCancelled(true);
		   		return;
			}
		   if(Arena.joinable){
			   e.setCancelled(true);
			   return;
		   }
                       if (Main.tag.get(e.getEntity()) != null) {
                               if (Main.tag.get(e.getEntity()) != e.getEntity()) ;
                               return;
                       }
                       else {
                    	   Main.tag.put(player, player);
                       }
		   if(e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
			   Main.tag.remove(player,player);
			   Main.tag.put(player,player);
		   }
		   if(e.getCause() == EntityDamageEvent.DamageCause.DROWNING) {

			   Main.tag.remove(player,player);
			   Main.tag.put(player,player);

		   }
		   if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {
			   Main.tag.remove(player,player);
			   Main.tag.put(player,player);

		   }
		   if(e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
			   Main.tag.remove(player,player);
			   Main.tag.put(player,player);
		   }
		   if(e.getCause() == EntityDamageEvent.DamageCause.FIRE) {
			   Main.tag.remove(player,player);
			   Main.tag.put(player,player);
		   }
		   if(e.getCause() == EntityDamageEvent.DamageCause.LAVA) {
			   Main.tag.remove(player,player);
			   Main.tag.put(player,player);
		   }
		   if(e.getCause() == EntityDamageEvent.DamageCause.VOID) {
			   Main.tag.remove(player,player);
			   Main.tag.put(player,player);
		   }
		   if(e.getCause() == EntityDamageEvent.DamageCause.SUICIDE) {
			   Main.tag.remove(player,player);
			   Main.tag.put(player,player);
		   }
		   if(e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) {
			   Main.tag.remove(player,player);
			   Main.tag.put(player,player);
		   }


               }

}
