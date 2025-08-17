package dev.tjxjnoobie.uhcgames.other;

import info.techwizmatt.ServerCore.Rank.Rank;
import net.minecraft.server.v1_8_R3.*;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Utils implements CommandExecutor {


	public static String getChatColor(Player player) {
		if (Rank.getRankLevel(player) == 8 || Rank.getRankLevel(player) == 9 || Rank.getRankLevel(player) == 10) {
			return ChatColor.AQUA + "";
		}

		if (Rank.getRankLevel(player) == 5 || Rank.getRankLevel(player) == 6) {
			return ChatColor.GRAY + "";
		}
		if (Rank.getRankLevel(player)==7) {
			return ChatColor.RED + "";
		}
		if (Rank.getRankLevel(player)<=11) {
			return ChatColor.AQUA + "" + ChatColor.BOLD;
		}

		if (Rank.getRankLevel(player)==3) {
			return ChatColor.YELLOW + "";
		}
		if (Rank.getRankLevel(player)==4) {
			return ChatColor.RED + "";
		}
		return ChatColor.GRAY + "";


	}


	public static String getNameColour(Player player) {
		if (Rank.getRankLevel(player) == 8 || Rank.getRankLevel(player) == 9 || Rank.getRankLevel(player) == 10) {
			return ChatColor.AQUA + "";
		}

		if (Rank.getRankLevel(player) == 5 || Rank.getRankLevel(player) == 6) {
			return ChatColor.GRAY + "";
		}
		if (Rank.getRankLevel(player) == 7) {
			return ChatColor.RED + "";
		}
		if (Rank.getRankLevel(player) <= 11) {
			return ChatColor.AQUA + "" + ChatColor.BOLD;
		}

		if (Rank.getRankLevel(player) == 3) {
			return ChatColor.YELLOW + "";
		}
		if (Rank.getRankLevel(player) == 4) {
			return ChatColor.RED + "";
		}
		return ChatColor.GRAY + "";

	}
	public static String getRank(Player player) {
		if (Rank.getRankLevel(player)==8) {
			return ChatColor.RED + "Mod";
		}
		if (Rank.getRankLevel(player)==9) {
			return org.bukkit.ChatColor.DARK_RED + "SrMod";
		}
		if(Rank.getRankLevel(player)==10){
			return ChatColor.DARK_RED + "" + ChatColor.BOLD + "Admin";
		}
		if(Rank.getRankLevel(player)==11) {
			return org.bukkit.ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Developer";
		}

		if (Rank.getRankLevel(player)==3) {
			return org.bukkit.ChatColor.DARK_PURPLE + "VIP";
		}
		if (Rank.getRankLevel(player)==7) {
			return org.bukkit.ChatColor.AQUA + "Diamond";
		}
		if(Rank.getRankLevel(player)==6){
			return  ChatColor.GOLD + "Gold";
		}
		if(Rank.getRankLevel(player)==5) {
			return org.bukkit.ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Iron";
		}
		if(Rank.getRankLevel(player)==1) {
			return ChatColor.AQUA +"Default";
		}

		return org.bukkit.ChatColor.AQUA +"Default";
	}

	public static ItemStack createItem(Material material, String displayname, String lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);

		meta.setLore(Lore);


		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack createItem(Material material, int amount, String displayname, String lore) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);
		meta.setLore(Lore);


		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack createItem(Material material, String displayname) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		item.setItemMeta(meta);
		return item;
	}
	public static int getHealth(Player player) {
		return (int) StrictMath.ceil(damageable(player).getHealth());
	}
	public static Damageable damageable(Player player) {
		return player;
	}

	public static void shootfireWork(Location l,Player p, FireworkEffect.Type type, Color color, Color fadecolor, int power, boolean flicker, boolean trial){
		Firework f = (Firework) p.getWorld().spawn(l, Firework.class);

		FireworkMeta fm = f.getFireworkMeta();
		fm.addEffect(FireworkEffect.builder()
				.flicker(flicker)
				.trail(trial)
				.with(type)
				.withColor(color)
				.withFade(fadecolor)
				.build());
		fm.setPower(power);
		f.setFireworkMeta(fm);
	}
	public static void giveJoinItems(Player player){
		player.getInventory().setItem(0,createItem(Material.NETHER_STAR,"§9Class Editor","View tier and edit class tiers"));
		player.getInventory().setItem(8,createItem(Material.EYE_OF_ENDER,"§9Back to Lobby","Return to hub"));
	}
	public static ItemStack addGlow(ItemStack item){
		net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		NBTTagCompound tag = null;
		if (!nmsStack.hasTag()) {
			tag = new NBTTagCompound();
			nmsStack.setTag(tag);
		}
		if (tag == null) tag = nmsStack.getTag();
		NBTTagList ench = new NBTTagList();
		tag.set("ench", ench);
		nmsStack.setTag(tag);
		return CraftItemStack.asCraftMirror(nmsStack);
	}
	public static void newBar(Player player,String text){
		Location loc = player.getLocation();
		WorldServer world = ((CraftWorld) player.getLocation().getWorld()).getHandle();

		EntityEnderDragon dragon = new EntityEnderDragon(world);
		dragon.setLocation(loc.getX() - 30, loc.getY() - 100, loc.getZ(), 0, 0);
		dragon.setCustomName(text);
		dragon.setInvisible(true);

		Packet<?> packet = new PacketPlayOutSpawnEntityLiving(dragon);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
	public static void RemoveBar(Player player){
		Location loc = player.getLocation();
		WorldServer world = ((CraftWorld) player.getLocation().getWorld()).getHandle();
		org.bukkit.World w =  player.getWorld();
		EntityEnderDragon dragon = new EntityEnderDragon(world);
		for(Entity dragons : w.getEntities()){
			if(dragons.getType() == EntityType.ENDER_DRAGON){
				dragon.setHealth(0);
			}
		}
	}
	public void setTeamPrefix(Player player, String prefix){
		Team team;
	}
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		return false;
	}
}

