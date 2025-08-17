package Commands;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;
import dev.tjxjnoobie.uhcgames.managers.SpectatorManager;
import dev.tjxjnoobie.uhcgames.other.Storage;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Kill implements CommandExecutor{
	
	  public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args){
		  Player player = (Player)sender;
		  Storage storage = new Storage();
		  World w = player.getWorld();
		  SpectatorManager specm = new SpectatorManager();
		  if(Main.getInstance().arenas.get(0).watching.contains(player) || Arena.pregame == true){
			  sender.sendMessage(Main.prefix + ChatColor.DARK_RED+"You must be alive to perform this command.");
			  return true;
		  }
		  if(Main.getInstance().arenas.get(0).ingame.contains(player) && Arena.joinable == false && Arena.timeremaining >1800) {
			  Main.getInstance().arenas.get(0).ingame.remove(player);
			  w.strikeLightningEffect(player.getLocation().add(0, 5, 0));
			  specm.makeSpec(player);
			  storage.addLosses(player);
			  player.setVelocity(player.getLocation().getDirection().multiply(0.5));
			  player.setVelocity(new Vector(player.getVelocity().getX(), 0.7D, player.getVelocity().getZ()));
		  }


		  return false;
	  }


}
