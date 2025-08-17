package Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by TJ on 9/27/2017.
 */
public class ConsumeEvent implements Listener {


    @EventHandler
    public void onEat(PlayerItemConsumeEvent event){
        Player user = event.getPlayer();
        ItemStack item = event.getItem();
        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta.getDisplayName().contains(ChatColor.stripColor("Golden Head"))){
            PotionEffect pe = new PotionEffect(PotionEffectType.ABSORPTION, 2400, 0);
            PotionEffect re = new PotionEffect(PotionEffectType.REGENERATION, 200, 1);
            pe.apply(user);
            re.apply(user);
        }
    }
}
