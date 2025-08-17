package Events;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import info.techwizmatt.ServerCore.API.CoinAPI;
import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.Stats;
import dev.tjxjnoobie.uhcgames.managers.Arena;
import dev.tjxjnoobie.uhcgames.other.Inventories;

import dev.tjxjnoobie.uhcgames.other.Storage;
import dev.tjxjnoobie.uhcgames.other.Utils;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class InteractEvent implements Listener{

	
	@SuppressWarnings("deprecation")
	@EventHandler
   public void onClick(PlayerInteractEvent e){
   	Player p = e.getPlayer();
   	String pname = p.getName();
   	String puuid = p.getUniqueId().toString();
   	Action a = e.getAction();
   	if(a==Action.RIGHT_CLICK_BLOCK || a==Action.RIGHT_CLICK_AIR){
   		
   		ItemStack hand = p.getItemInHand();
   		if(hand!=null&&hand.getType()==Material.COMPASS) {
			if (Main.getInstance().arenas.get(0).watching.contains(p)) {
				Inventory inv = Bukkit.createInventory(null, 27, "§3§lSpectate a Player");

				for (String pall : Arena.players) {
					Player m = Bukkit.getPlayer(pall);
					ItemStack h = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
					SkullMeta hm = (SkullMeta) h.getItemMeta();
					hm.setDisplayName(m.getName());
					hm.setOwner(m.getName());
					h.setItemMeta(hm);
					inv.addItem(h);

					continue;
				}
				p.openInventory(inv);

			}
		}
			if(hand!=null&&hand.getType()==Material.NETHER_STAR) {
				if (Arena.joinable) {
					Storage storage = new Storage();
					Inventory inv2 = Bukkit.createInventory(null, 9*6, "§9§lClass Editor");
					inv2.setItem(8, Utils.createItem(Material.YELLOW_FLOWER,"§6Coins§f: " + storage.getCoins(p)));
					// BRUTE
					ItemStack upgraded = Utils.createItem(Material.COAL,"§8[§4X§8]§c§lUPGRADED§8[§4X§8]");
					ItemStack upgradedIron = Utils.createItem(Material.IRON_INGOT,"§8[§4X§8]§c§lUPGRADED§8[§4X§8]");
					ItemStack upgradedGold = Utils.createItem(Material.GOLD_INGOT,"§8[§4X§8]§c§lUPGRADED§8[§4X§8]");
					ItemStack upgradedDiamond = Utils.createItem(Material.DIAMOND,"§8[§4X§8]§c§lUPGRADED§8[§4X§8]");

					ItemMeta upgradelore = upgraded.getItemMeta();
					ItemMeta upgradeloreIron = upgradedIron.getItemMeta();

					if((int)storage.getBruteTier(p)==0){
						ItemStack furnace = Utils.createItem(Material.DIAMOND_CHESTPLATE,"§9Brute §8(§7Tier §a"+ storage.getBruteTier(p)+"§8)");
						inv2.setItem(10, furnace);

						inv2.setItem(19, Utils.createItem(Material.COAL,"§a§lUpgrade Brute to §cTier 1 §a§lfor 200 coins" + "","§b♦ §7Protection I Leggings"));
					}else if((int)storage.getBruteTier(p)==1) {
						ItemStack coal = Utils.createItem(Material.IRON_INGOT, "§a§lUpgrade Brute to §cTier 2 §a§lfor 500 coins");
						ItemMeta loremeta = coal.getItemMeta();
						List<String> lore = new ArrayList<String>();
						lore.add("§b♦ §7Protection I Leggings");
						lore.add("§b♦ §7Protection I Chest");
						loremeta.setLore(lore);
						coal.setItemMeta(loremeta);
						inv2.setItem(28,coal);
						inv2.setItem(19, upgraded);
						List<String> lore2 = new ArrayList<String>();
						lore2.add("§b♦ §7You Receive §b♦");
						lore2.add("§b♦ §7Protection I Leggings ");
						ItemStack furnace = Utils.createItem(Material.DIAMOND_CHESTPLATE,"§9Brute §8(§7Tier §a"+ storage.getBruteTier(p)+"§8)");
						ItemMeta loremeta2 = furnace.getItemMeta();
						loremeta2.setLore(lore2);
						furnace.setItemMeta(loremeta2);
						inv2.setItem(10, furnace);
					}else if((int)storage.getBruteTier(p)==2) {
						ItemStack coal = Utils.createItem(Material.GOLD_INGOT, "§a§lUpgrade Brute to §cTier 3 §a§lfor 2500 coins ");
						ItemMeta loremeta = coal.getItemMeta();
						List<String> lore = new ArrayList<String>();
						lore.add("§b♦ §7Protection II Leggings");
						lore.add("§b♦ §7Protection I Chest");
						loremeta.setLore(lore);
						coal.setItemMeta(loremeta);
						inv2.setItem(37,coal);

						upgradelore.setLore(lore);
						upgraded.setItemMeta(upgradelore);

						upgradeloreIron.setLore(lore);
						upgradedIron.setItemMeta(upgradeloreIron);
						inv2.setItem(19,upgraded);
						inv2.setItem(28,upgradedIron);
						List<String> lore2 = new ArrayList<String>();
						lore2.add("§b♦ §7You Receive §b♦");
						lore2.add("§b♦ §7Protection I Leggings");
						lore2.add("§b♦ §7Protection I Chest");

						ItemStack furnace = Utils.createItem(Material.DIAMOND_CHESTPLATE,"§9Brute §8(§7Tier §a"+ storage.getBruteTier(p)+"§8)");
						ItemMeta loremeta2 = furnace.getItemMeta();

						loremeta2.setLore(lore2);
						furnace.setItemMeta(loremeta2);
						inv2.setItem(10, furnace);
					}else if((int)storage.getBruteTier(p)==3) {
						ItemStack coal = Utils.createItem(Material.DIAMOND, "§a§lUpgrade Brute to §cTier 4 §a§lfor 5000 coins");
						ItemMeta loremeta = coal.getItemMeta();
						List<String> lore = new ArrayList<String>();
						lore.add("§b♦ §7Protection II Leggings");
						lore.add("§b♦ §7Protection II Chest");
						loremeta.setLore(lore);
						Utils.addGlow(coal);
						coal.setItemMeta(loremeta);
						inv2.setItem(46,coal);
						inv2.setItem(19, upgraded);
						inv2.setItem(28,upgradedIron);
						inv2.setItem(37,upgradedGold);
						List<String> lore2 = new ArrayList<String>();
						lore2.add("§b♦ §7You Receive §b♦");
						lore2.add("§b♦ §7Protection II Leggings");
						lore2.add("§b♦ §7Protection I Chest");
						ItemStack furnace = Utils.createItem(Material.DIAMOND_CHESTPLATE,"§9Brute §8(§7Tier §a"+ storage.getBruteTier(p)+"§8)");
						ItemMeta loremeta2 = furnace.getItemMeta();

						loremeta2.setLore(lore2);
						furnace.setItemMeta(loremeta2);

						inv2.setItem(10, furnace);
					}else if((int)storage.getBruteTier(p)== 4){
						List<String> lore = new ArrayList<String>();
						lore.add("§b♦ §7You Receive §b♦");
						lore.add("§b♦ §7Protection II Leggings");
						lore.add("§b♦ §7Protection II Chest");
						ItemStack furnace = Utils.createItem(Material.DIAMOND_CHESTPLATE,"§9Brute §8(§7Tier §a"+storage.getBruteTier(p)+"§8)");
						ItemMeta loremeta = furnace.getItemMeta();

						loremeta.setLore(lore);
						furnace.setItemMeta(loremeta);
						upgraded.setItemMeta(upgradelore);
						inv2.setItem(10, furnace);

						inv2.setItem(19, upgraded);
						inv2.setItem(28,upgradedIron);
						inv2.setItem(37,upgradedGold);
						inv2.setItem(46,upgradedDiamond);
					}
					//RUSHER
					if((int)storage.getRusherTier(p)==0){
						inv2.setItem(21, Utils.createItem(Material.COAL,"§a§lUpgrade Rusher to §cTier 1 §a§lfor 200 coins" + "","§b♦ §7Stone Sword"));
						ItemStack sword = Utils.createItem(Material.DIAMOND_SWORD,"§9Rusher §8(§7Tier §a"+ storage.getRusherTier(p)+"§8)");
						inv2.setItem(12, sword);

					}else if((int)storage.getRusherTier(p)==1) {
						inv2.setItem(30, Utils.createItem(Material.IRON_INGOT, "§a§lUpgrade Rusher to §cTier 2 §a§lfor 1000 coins","§b♦ §7Iron Sword"));
						inv2.setItem(21, upgraded);
						List<String> lore = new ArrayList<String>();
						lore.add("§b♦ §7You Receive §b♦");
						lore.add("§b♦ §7Stone Sword");
						ItemStack sword = Utils.createItem(Material.DIAMOND_SWORD,"§9Rusher §8(§7Tier §a"+ storage.getRusherTier(p)+"§8)");
						ItemMeta loremeta = sword.getItemMeta();

						loremeta.setLore(lore);
						sword.setItemMeta(loremeta);
						upgraded.setItemMeta(upgradelore);
						inv2.setItem(12, sword);

					}else if((int)storage.getRusherTier(p)==2) {
						ItemStack coal = Utils.createItem(Material.GOLD_INGOT, "§a§lUpgrade Rusher to §cTier 3§a§l for 2500 coins");
						ItemMeta loremeta = coal.getItemMeta();
						List<String> lore = new ArrayList<String>();
						lore.add("§b♦ §7Diamond Sword");
						loremeta.setLore(lore);
						coal.setItemMeta(loremeta);
						inv2.setItem(39,coal);
						inv2.setItem(21, upgraded);
						inv2.setItem(30, upgradedIron);
						List<String> lore2 = new ArrayList<String>();
						lore2.add("§b♦ §7You Receive §b♦");
						lore2.add("§b♦ §7Iron Sword");
						ItemStack sword = Utils.createItem(Material.DIAMOND_SWORD,"§9Rusher §8(§7Tier §a"+ storage.getRusherTier(p)+"§8)");
						ItemMeta loremeta2 = sword.getItemMeta();

						loremeta2.setLore(lore2);
						sword.setItemMeta(loremeta2);
						upgraded.setItemMeta(upgradelore);
						inv2.setItem(12, sword);
					}else if((int)storage.getRusherTier(p)==3) {
						ItemStack diamond = Utils.createItem(Material.DIAMOND, "§a§lUpgrade Rusher to §cTier 4 §a§lfor 5000 coins");
						ItemMeta loremeta = diamond.getItemMeta();
						List<String> lore = new ArrayList<String>();
						lore.add("§b♦ §7Diamond Sword");
						lore.add("§b♦ §7Protection I Leggings");
						lore.add("§b♦ §7Projectile Protection I Leggings");
						loremeta.setLore(lore);
						Utils.addGlow(diamond);
						diamond.setItemMeta(loremeta);

						List<String> lore2 = new ArrayList<String>();
						lore2.add("§b♦ §7You Receive §b♦");
						lore2.add("§b♦ §7Diamond Sword");
						ItemStack sword = Utils.createItem(Material.DIAMOND_SWORD,"§9Rusher §8(§7Tier §a"+storage.getRusherTier(p)+"§8)");
						ItemMeta loremeta2 = sword.getItemMeta();

						loremeta2.setLore(lore2);
						sword.setItemMeta(loremeta2);
						upgraded.setItemMeta(upgradelore);
						inv2.setItem(12, sword);
						inv2.setItem(48,diamond);
						inv2.setItem(21, upgraded);
						inv2.setItem(30, upgradedIron);
						inv2.setItem(39, upgradedGold);
					}else if((int)storage.getRusherTier(p)==4){
						List<String> lore = new ArrayList<String>();
						lore.add("§b♦ §7You Receive §b♦");
						lore.add("§b♦ §7Diamond Sword");
						lore.add("§b♦ §7Protection I Leggings");
						lore.add("§b♦ §7Projectile Protection I Leggings");
						ItemStack sword = Utils.createItem(Material.DIAMOND_SWORD,"§9Rusher §8(§7Tier §a"+ storage.getRusherTier(p)+"§8)");
						ItemMeta loremeta = sword.getItemMeta();

						loremeta.setLore(lore);
						sword.setItemMeta(loremeta);
						upgraded.setItemMeta(upgradelore);
						inv2.setItem(12, sword);

						inv2.setItem(48,upgradedDiamond);
						inv2.setItem(21, upgraded);
						inv2.setItem(30, upgradedIron);
						inv2.setItem(39, upgradedGold);
					}
					// HEALER
					if((int)storage.getHealerTier(p)==0){
						inv2.setItem(23, Utils.createItem(Material.COAL,"§a§lUpgrade Healer to §cTier 1 §a§lfor 200 coins","§b♦ §71 Golden Apple"));
						inv2.setItem(14, Utils.createItem(Material.POTION,"§9Healer §8(§7Tier §a"+ Stats.getTier(pname,puuid,"UUID","HEALER")+"§8)"));

					}else if((int)storage.getHealerTier(p)==1) {
						inv2.setItem(32, Utils.createItem(Material.IRON_INGOT, "§a§lUpgrade Healer to §cTier 2 for §a§11000 coins","§b♦ §72 Golden Apples"));
						List<String> lore = new ArrayList<String>();
						lore.add("§b♦ §7You Receive §b♦");
						lore.add("§b♦ §71 Golden Apple");
						ItemStack pot = Utils.createItem(Material.POTION,"§9Healer §8(§7Tier §a"+ Stats.getTier(pname,puuid,"UUID","HEALER")+"§8)");
						ItemMeta loremeta = pot.getItemMeta();

						loremeta.setLore(lore);
						pot.setItemMeta(loremeta);
						upgraded.setItemMeta(upgradelore);
						inv2.setItem(14, pot);
						inv2.setItem(23,upgraded);

					}else if((int)storage.getHealerTier(p)==2) {
						ItemStack coal = Utils.createItem(Material.GOLD_INGOT, "§a§lUpgrade Healer to §cTier 3 §a§lfor §a§12500 coins");
						ItemMeta loremeta = coal.getItemMeta();
						List<String> lore = new ArrayList<String>();
						lore.add("§b♦ §72 Golden Apples");
						loremeta.setLore(lore);
						coal.setItemMeta(loremeta);
						inv2.setItem(41,coal);
						inv2.setItem(23, upgraded);
						inv2.setItem(32, upgradedIron);
						List<String> lore2 = new ArrayList<String>();
						lore2.add("§b♦ §7You Receive §b♦");
						lore2.add("§b♦ §71 Golden Apple");
						ItemStack pot = Utils.createItem(Material.POTION,"§9Healer §8(§7Tier §a"+ storage.getHealerTier(p)+"§8)");
						ItemMeta loremeta2 = pot.getItemMeta();
						loremeta2.setLore(lore2);
						pot.setItemMeta(loremeta2);
						upgraded.setItemMeta(upgradelore);
						inv2.setItem(14, pot);
					}else if((int)storage.getHealerTier(p)==3) {
						ItemStack diamond = Utils.createItem(Material.DIAMOND, "§a§lUpgrade Healer to §cTier 4 §a§lfor 5000 coins");
						ItemMeta loremeta = diamond.getItemMeta();
						List<String> lore = new ArrayList<String>();
						lore.add("§b♦ §71 Golden Apples");
						lore.add("§b♦ §71 Golden Head");
						loremeta.setLore(lore);
						Utils.addGlow(diamond);
						diamond.setItemMeta(loremeta);
						List<String> lore2 = new ArrayList<String>();
						lore2.add("§b♦ §7You Receive §b♦");
						lore2.add("§b♦ §72 Golden Apples");

						ItemStack pot = Utils.createItem(Material.POTION,"§9Healer §8(§7Tier §a"+ storage.getHealerTier(p)+"§8)");
						ItemMeta loremeta2 = pot.getItemMeta();

						loremeta2.setLore(lore2);
						pot.setItemMeta(loremeta2);
						upgraded.setItemMeta(upgradelore);
						inv2.setItem(14, pot);
						inv2.setItem(50,diamond);
						inv2.setItem(23, upgraded);
						inv2.setItem(32, upgradedIron);
						inv2.setItem(41, upgradedGold);
					}else if((int)storage.getHealerTier(p)==4){
						List<String> lore = new ArrayList<String>();
						lore.add("§b♦ §7You Receive §b♦");
						lore.add("§b♦ §72 Golden Apples");
						lore.add("§b♦ §71 Golden Head");
						lore.add("§b♦ §74 Gold Ingots");


						ItemStack pot = Utils.createItem(Material.POTION,"§9Healer §8(§7Tier §a"+ storage.getHealerTier(p)+"§8)");
						ItemMeta loremeta2 = pot.getItemMeta();

						loremeta2.setLore(lore);
						pot.setItemMeta(loremeta2);
						upgraded.setItemMeta(upgradelore);
						inv2.setItem(14, pot);
						inv2.setItem(50,upgradedDiamond);
						inv2.setItem(23, upgraded);
						inv2.setItem(32, upgradedIron);
						inv2.setItem(41, upgradedGold);
					}
					// ARCHER
					if((int)storage.getArcherTier(p)==0){
						inv2.setItem(16, Utils.createItem(Material.BOW,"§9Archer §8(§7Tier §a"+ storage.getArcherTier(p)+"§8)"));
						ItemStack coal = Utils.createItem(Material.COAL, "§a§lUpgrade Archer to §cTier 1 §a§l200 coins");
						ItemMeta loremeta = coal.getItemMeta();
						List<String> lore = new ArrayList<String>();
						lore.add("§b♦ §72 String");
						lore.add("§b♦ §78 Arrows");
						loremeta.setLore(lore);
						coal.setItemMeta(loremeta);
						inv2.setItem(25,coal);

					}else if((int)storage.getArcherTier(p)==1) {
						ItemStack iron = Utils.createItem(Material.IRON_INGOT, "§a§lUpgrade Archer to §cTier 2 §a§l1000 coins");
						ItemMeta loremeta = iron.getItemMeta();
						List<String> lore = new ArrayList<String>();
						lore.add("§b♦ §23 String");
						lore.add("§b♦ §712 Arrows");

						loremeta.setLore(lore);

						iron.setItemMeta(loremeta);

						List<String> lore2 = new ArrayList<String>();
						lore2.add("§b♦ §7You Receive §b♦");
						lore2.add("§b♦ §72 String");
						lore2.add("§b♦ §78 Arrows");

						ItemStack bow = Utils.createItem(Material.BOW,"§9Archer §8(§7Tier §a"+ storage.getArcherTier(p)+"§8)");
						ItemMeta loremeta2 = bow.getItemMeta();

						loremeta2.setLore(lore2);
						bow.setItemMeta(loremeta2);
						upgraded.setItemMeta(upgradelore);

						inv2.setItem(16, bow);
						inv2.setItem(25, upgraded);
						inv2.setItem(34,iron);

					}else if((int)storage.getArcherTier(p)==2) {
						ItemStack coal = Utils.createItem(Material.GOLD_INGOT, "§a§lUpgrade Archer to §cTier 3 §a§l2500 coins");
						ItemMeta loremeta = coal.getItemMeta();
						List<String> lore = new ArrayList<String>();
						lore.add("§b♦ §7Bow");
						lore.add("§b♦ §712 Arrows");
						loremeta.setLore(lore);
						coal.setItemMeta(loremeta);

						List<String> lore2 = new ArrayList<String>();
						lore2.add("§b♦ §7You Receive §b♦");
						lore2.add("§b♦ §73 String");
						lore2.add("§b♦ §712 Arrows");

						ItemStack bow = Utils.createItem(Material.BOW,"§9Archer §8(§7Tier §a"+ storage.getArcherTier(p)+"§8)");
						ItemMeta loremeta2 = bow.getItemMeta();

						loremeta2.setLore(lore2);
						bow.setItemMeta(loremeta2);
						upgraded.setItemMeta(upgradelore);
						inv2.setItem(16, bow);
						inv2.setItem(43,coal);
						inv2.setItem(25, upgraded);
						inv2.setItem(34, upgradedIron);
					}else if((int)storage.getArcherTier(p)==3) {
						ItemStack diamond = Utils.createItem(Material.DIAMOND, "§a§lUpgrade Archer to §cTier 4§a§l for 5000 coins");
						ItemMeta loremeta = diamond.getItemMeta();
						List<String> lore = new ArrayList<String>();
						lore.add("§b♦ §7Bow");
						lore.add("§b♦ §716 Arrows");
						lore.add("§b♦ §7Power I Book");
						lore.add("§b♦ §72 String");
						loremeta.setLore(lore);
						loremeta.addEnchant(Enchantment.DURABILITY,1,false);
						loremeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						Utils.addGlow(diamond);
						diamond.setItemMeta(loremeta);

						List<String> lore2 = new ArrayList<String>();
						lore2.add("§b♦ §7You Receive §b♦");
						lore2.add("§b♦ §7Bow");
						lore2.add("§b♦ §712 Arrows");

						ItemStack bow = Utils.createItem(Material.BOW,"§9Archer §8(§7Tier §a"+ storage.getArcherTier(p)+"§8)");
						ItemMeta loremeta2 = bow.getItemMeta();

						loremeta2.setLore(lore2);
						bow.setItemMeta(loremeta2);
						upgraded.setItemMeta(upgradelore);
						inv2.setItem(16, bow);
						inv2.setItem(52,diamond);
						inv2.setItem(25, upgraded);
						inv2.setItem(34, upgradedIron);
						inv2.setItem(43, upgradedGold);
					}else if((int)storage.getArcherTier(p)==4){
						List<String> lore2 = new ArrayList<String>();
						lore2.add("§b♦ §7You Receive §b♦");
						lore2.add("§b♦ §7Bow");
						lore2.add("§b♦ §716 Arrows");
						lore2.add("§b♦ §7Power I Book");
						lore2.add("§b♦ §72 String");
						ItemStack bow = Utils.createItem(Material.BOW,"§9Archer §8(§7Tier §a"+ storage.getArcherTier(p)+"§8)");
						ItemMeta loremeta2 = bow.getItemMeta();

						loremeta2.setLore(lore2);
						bow.setItemMeta(loremeta2);
						upgraded.setItemMeta(upgradelore);
						inv2.setItem(16, bow);
						inv2.setItem(52,upgradedDiamond);
						inv2.setItem(25, upgraded);
						inv2.setItem(34, upgradedIron);
						inv2.setItem(43, upgradedGold);
					}
					ItemStack star = Utils.createItem(Material.NETHER_STAR,"§6§lArmour Tier:§f " + storage.getArmourTier(p));

					if((int)storage.getArmourTier(p)==0){
						List<String> lore = new ArrayList<>();
						lore.add("§9Upgrade for 500 coins");
						lore.add("§7Current§b:");
						lore.add("§b♦ §7Full Leather Armour");
						lore.add("§b♦ §7Wood Sword");
						lore.add("§7Next§b:");
						lore.add("§b♦ §7Iron Helmet");
						lore.add("§b♦ §7Leather Chestplate");
						lore.add("§b♦ §7LeatherLeggings");
						lore.add("§b♦ §7Leather Boots");
						lore.add("§b♦ §7WoodSword");
						lore.add("§b♦ §71 Extra Gold");
						ItemMeta starMeta = star.getItemMeta();
						starMeta.setLore(lore);
						star.setItemMeta(starMeta);
						inv2.setItem(4,star);

					}
					if((int)storage.getArmourTier(p)==1){
						List<String> lore = new ArrayList<>();
						lore.add("§9Upgrade for 2000 coins");
						lore.add("§7Current§b:");
						lore.add("§b♦ §7Iron Helmet");
						lore.add("§b♦ §7Leather Chestplate");
						lore.add("§b♦ §7LeatherLeggings");
						lore.add("§b♦ §7Leather Boots");
						lore.add("§b♦ §7WoodSword");
						lore.add("§b♦ §71 Extra Gold");
						lore.add("§7Next§b:");
						lore.add("§b♦ §7Iron Helmet");
						lore.add("§b♦ §7Leather Chestplate");
						lore.add("§b♦ §7Leather Leggings");
						lore.add("§b♦ §7Iron Boots");
						lore.add("§b♦ §7Wood Sword");
						lore.add("§b♦ §72 Extra Gold");
						ItemMeta starMeta = star.getItemMeta();
						starMeta.setLore(lore);
						star.setItemMeta(starMeta);
						inv2.setItem(4,star);

					}
					if((int)storage.getArmourTier(p)==2){
						List<String> lore = new ArrayList<>();
						lore.add("§9Upgrade for 4000 coins");
						lore.add("§7Current§b:");
						lore.add("§b♦ §7Iron Helmet");
						lore.add("§b♦ §7Leather Chestplate");
						lore.add("§b♦ §7Leather Leggings");
						lore.add("§b♦ §7Iron Boots");
						lore.add("§b♦ §7Wood Sword");
						lore.add("§b♦ §72 Extra Gold");
						lore.add("§7Next§b:");
						lore.add("§b♦ §7Iron Helmet");
						lore.add("§b♦ §7Leather Chestplate");
						lore.add("§b♦ §7Iron Leggings");
						lore.add("§b♦ §7Iron Boots");
						lore.add("§b♦ §7Wood Sword");
						lore.add("§b♦ §73 Extra Gold");
						ItemMeta starMeta = star.getItemMeta();
						starMeta.setLore(lore);
						star.setItemMeta(starMeta);
						inv2.setItem(4,star);

					}
					if((int)storage.getArmourTier(p)==3){
						List<String> lore = new ArrayList<>();
						lore.add("§9Upgrade for 8000 coins");
						lore.add("§7Current§b:");
						lore.add("§b♦ §7Iron Helmet");
						lore.add("§b♦ §7Leather Chestplate");
						lore.add("§b♦ §7Iron Leggings");
						lore.add("§b♦ §7Iron Boots");
						lore.add("§b♦ §7Wood Sword");
						lore.add("§b♦ §73 Extra Gold");
						lore.add("§7Next§b:");
						lore.add("§b♦ §7Iron Helmet");
						lore.add("§b♦ §7Iron Chestplate");
						lore.add("§b♦ §7Iron Leggings");
						lore.add("§b♦ §7Iron Boots");
						lore.add("§b♦ §7Stone Sword");
						lore.add("§b♦ §74 Extra Gold");
						ItemMeta starMeta = star.getItemMeta();
						starMeta.setLore(lore);
						star.setItemMeta(starMeta);
						inv2.setItem(4,star);

					}
					if((int)storage.getArmourTier(p)==4){
						List<String> lore = new ArrayList<>();
						lore.add("§7Current§b:");
						lore.add("§b♦ §7Iron Helmet");
						lore.add("§b♦ §7Iron Chestplate");
						lore.add("§b♦ §7Iron Leggings");
						lore.add("§b♦ §7Iron Boots");
						lore.add("§b♦ §7Stone Sword");
						lore.add("§b♦ §74 Extra Gold");
						lore.add("§7Next§b:");
						lore.add("§8[§4X§8]§c§lUPGRADED§8[§4X§8]");
						ItemMeta starMeta = star.getItemMeta();
						starMeta.setLore(lore);
						star.setItemMeta(starMeta);

						inv2.setItem(4,star);

					}



					p.openInventory(inv2);

				}

				e.setCancelled(true);
			}
   			if(hand!=null&&hand.getType()==Material.EYE_OF_ENDER){
	   				Main.SendToServer(p.getName(), "lobby", p);
	   			}

   		}

	if(Main.getInstance().arenas.get(0).ingame.contains(p)){
		if(a==Action.LEFT_CLICK_BLOCK) {
			if (BlockPlace.placed.containsValue(e.getClickedBlock().getLocation())) {
				if (e.getClickedBlock().getType() == Material.COBBLESTONE || e.getClickedBlock().getType() == Material.WOOD ||
						e.getClickedBlock().getType() == Material.WORKBENCH) {
					e.getClickedBlock().breakNaturally();
				}
			} else {
				return;
			}
		}
		}else {
		return;
	}

	}
		@EventHandler
		public void onInvInteract(InventoryClickEvent e){
			Player player = (Player) e.getWhoClicked();

			if(e.getInventory().getName().equalsIgnoreCase("§9§lClass Editor")){
   				String pname = player.getName();
   				String puuid = player.getUniqueId().toString();
   				Storage storage = new Storage();
   				if((int)storage.getBruteTier(player)==0 && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.stripColor("Brute"))&&
						e.getCurrentItem().getType() == Material.COAL){
   					if((int)storage.getCoins(player)<200){
   						int needed = 200- (int)storage.getCoins(player) ;
   						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						e.setCancelled(true);
						player.closeInventory();
						return;
					}
					CoinAPI.subtractTokens(player,200);
					Stats.setTier(pname,puuid,1,"BRUTE");
					player.sendMessage(Main.prefix+"You've bought §aTier I §7Brute for §a200 §7coins");
   					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);
					player.closeInventory();
				}

				if((int)storage.getBruteTier(player)==1 && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.stripColor("Brute"))&&
						e.getCurrentItem().getType() == Material.IRON_INGOT){
					if((int)storage.getCoins(player)<1000){
						int needed =  1000 -(int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						e.setCancelled(true);
						player.closeInventory();
						return;
					}
					CoinAPI.subtractTokens(player,1000);
					Stats.setTier(pname,puuid,2,"BRUTE");
					player.sendMessage(Main.prefix+"You've bought §aTier II §7Brute for §a1000 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);
					player.closeInventory();

				}

			if((int)storage.getBruteTier(player)==2 && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.stripColor("Brute"))&&
					e.getCurrentItem().getType() == Material.GOLD_INGOT){
				if((int)storage.getCoins(player)<2500){
					int needed =  2500-(int)storage.getCoins(player);
					player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
					player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
					player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
					e.setCancelled(true);
					player.closeInventory();
					return;
				}
				CoinAPI.subtractTokens(player,2500);
				Stats.setTier(pname,puuid,3,"BRUTE");
				player.sendMessage(Main.prefix+"You've bought §aTier III §7Brute for §a2500 §7coins");
				player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);
				player.closeInventory();

			}
				if((int)storage.getBruteTier(player)==3 && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.stripColor("Brute"))&&
						e.getCurrentItem().getType() == Material.DIAMOND){
					if((int)storage.getCoins(player)<5000){
						int needed =  5000-(int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						e.setCancelled(true);
						player.closeInventory();
						return;
					}

					CoinAPI.subtractTokens(player,5000);
					storage.addCoins(player,CoinAPI.GetTokens(player));
					Stats.setTier(pname,puuid,4,"BRUTE");
					player.sendMessage(Main.prefix+"You've bought §aTier IV §7Brute for §a5000 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);

					player.closeInventory();


				}
				// RUSHER
				if((int)storage.getRusherTier(player)==0 && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.stripColor("Rusher"))&&
						e.getCurrentItem().getType() == Material.COAL){
					if((int)storage.getCoins(player)<200){
						int needed = 200- (int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						e.setCancelled(true);
						player.closeInventory();

						return;
					}
					CoinAPI.subtractTokens(player,200);
					Stats.setTier(pname,puuid,1,"RUSHER");

					player.sendMessage(Main.prefix+"You've bought §aTier I §7Rusher for §a200 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);
					player.closeInventory();

				}
				if((int)storage.getRusherTier(player)==1 && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.stripColor("Rusher"))&&
						e.getCurrentItem().getType() == Material.IRON_INGOT){
					if((int)storage.getCoins(player)<1000){
						int needed = 1000- (int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						e.setCancelled(true);
						return;
					}
					CoinAPI.subtractTokens(player,1000);
					Stats.setTier(pname,puuid,2,"RUSHER");
					storage.addCoins(player,CoinAPI.GetTokens(player));
					player.sendMessage(Main.prefix+"You've bought §aTier II §7Rusher for §a200 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);

				}
				if((int)storage.getRusherTier(player)==2 && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.stripColor("Rusher"))&&
						e.getCurrentItem().getType() == Material.GOLD_INGOT){
					if((int)storage.getCoins(player)<2500){
						int needed = 2500- (int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						return;
					}
					Stats.setTier(pname,puuid,3,"RUSHER");
					CoinAPI.subtractTokens(player,2500);
					storage.addCoins(player,CoinAPI.GetTokens(player));
					player.sendMessage(Main.prefix+"You've bought §aTier III §7Rusher for §a2500 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);
				}
				if((int)storage.getRusherTier(player)==3 && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.stripColor("Rusher"))&&
						e.getCurrentItem().getType() == Material.DIAMOND){
					if((int)storage.getCoins(player)<5000){
						int needed = 5000- (int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						return;
					}
					Stats.setTier(pname,puuid,4,"RUSHER");
					CoinAPI.subtractTokens(player,5000);
					storage.addCoins(player,CoinAPI.GetTokens(player));
					player.sendMessage(Main.prefix+"You've bought §aTier IV §7Rusher for §a5000 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);

				}
				// HEALER
				if((int)storage.getHealerTier(player)==0 && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.stripColor("Healer"))&&
						e.getCurrentItem().getType() == Material.COAL){
					if((int)storage.getCoins(player)<200){
						int needed = 200- (int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						e.setCancelled(true);
						player.closeInventory();
						return;
					}
					Stats.setTier(pname,puuid,1,"HEALER");
					CoinAPI.subtractTokens(player,200);
					player.sendMessage(Main.prefix+"You've bought §aTier I §7Healer for §a200 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);

				}
				if((int)storage.getHealerTier(player)==1 && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.stripColor("Healer"))&&
						e.getCurrentItem().getType() == Material.IRON_INGOT){
					if((int)storage.getCoins(player)<1000){
						int needed = 1000- (int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						e.setCancelled(true);
						player.closeInventory();
						return;
					}
					Stats.setTier(pname,puuid,2,"HEALER");
					CoinAPI.subtractTokens(player,1000);
					player.sendMessage(Main.prefix+"You've bought §aTier II §7Healer for §a1000 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);

				}
				if((int)storage.getHealerTier(player)==2 && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.stripColor("Healer"))&&
						e.getCurrentItem().getType() == Material.GOLD_INGOT){
					if((int)storage.getCoins(player)<2500){
						int needed = 2500- (int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						return;
					}
					Stats.setTier(pname,puuid,3,"HEALER");
					CoinAPI.subtractTokens(player,2500);
					player.sendMessage(Main.prefix+"You've bought §aTier III §7Healer for §a2500 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);

				}
				if((int)storage.getHealerTier(player)==3 && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.stripColor("Healer"))&&
						e.getCurrentItem().getType() == Material.DIAMOND){
					if((int)storage.getCoins(player)<5000){
						int needed = 5000- (int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						return;
					}
					Stats.setTier(pname,puuid,4,"HEALER");
					CoinAPI.subtractTokens(player,200);
					player.sendMessage(Main.prefix+"You've bought §aTier IV §7Healer for §a5000 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);

				}
				// ARCHER
				if((int)storage.getArcherTier(player)==0 && e.getCurrentItem().getItemMeta().getDisplayName().contains("Archer")&&
						e.getCurrentItem().getType() == Material.COAL){
					if((int)storage.getCoins(player)<200){
						int needed = 200- (int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						e.setCancelled(true);
						player.closeInventory();
						return;
					}
					Stats.setTier(pname,puuid,1,"ARCHER");
					CoinAPI.subtractTokens(player,200);
					player.sendMessage(Main.prefix+"You've bought §aTier I §7Archer for §a200 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);

				}
				if((int)storage.getArcherTier(player)==1 && e.getCurrentItem().getItemMeta().getDisplayName().contains("Archer")&&
						e.getCurrentItem().getType() == Material.IRON_INGOT){
					if((int)storage.getCoins(player)<1000){
						int needed = 1000- (int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						e.setCancelled(true);
						player.closeInventory();
						return;
					}
					Stats.setTier(pname,puuid,2,"ARCHER");
					CoinAPI.subtractTokens(player,1000);
					player.sendMessage(Main.prefix+"You've bought §aTier II §7Archer for §a1000 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);

				}
				if((int)storage.getArcherTier(player)==2 && e.getCurrentItem().getItemMeta().getDisplayName().contains("Archer")&&
						e.getCurrentItem().getType() == Material.GOLD_INGOT){
					if((int)storage.getCoins(player)<2500){
						int needed = 2500- (int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						e.setCancelled(true);
						player.closeInventory();
						return;
					}

					Stats.setTier(pname,puuid,3,"ARCHER");
					CoinAPI.subtractTokens(player,2500);
					player.sendMessage(Main.prefix+"You've bought §aTier III §7Archer for §a2500 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);

				}
				if((int)storage.getArcherTier(player)==3 && e.getCurrentItem().getItemMeta().getDisplayName().contains("Archer")&&
						e.getCurrentItem().getType() == Material.DIAMOND){
					if((int)storage.getCoins(player)<5000){
						int needed = 5000- (int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						e.setCancelled(true);
						player.closeInventory();
						return;
					}
					Stats.setTier(pname,puuid,4,"ARCHER");
					CoinAPI.subtractTokens(player,5000);
					player.sendMessage(Main.prefix+"You've bought §aTier IV §7Archer for §a5000 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);

				}
				//ARMOUR
				if((int)storage.getArmourTier(player)==0 && e.getCurrentItem().getType() == Material.NETHER_STAR){
					if((int)storage.getCoins(player)<500){
						int needed = 500- (int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						e.setCancelled(true);
						player.closeInventory();
						return;
					}
					Stats.setTier(pname,puuid,1,"ARMOUR");
					CoinAPI.subtractTokens(player,500);
					player.sendMessage(Main.prefix+"You've bought §aTier I §7Armour for §a500 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);
				}

				if((int)storage.getArmourTier(player)==1 && e.getCurrentItem().getType() == Material.NETHER_STAR){
					if((int)storage.getCoins(player)<2000){
						int needed = 2000- (int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						e.setCancelled(true);
						player.closeInventory();
						return;
					}
					Stats.setTier(pname,puuid,2,"ARMOUR");
					CoinAPI.subtractTokens(player,2000);
					player.sendMessage(Main.prefix+"You've bought §aTier II §7Armour for §a2000 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);
				}
				if((int)storage.getArmourTier(player)==2 && e.getCurrentItem().getType() == Material.NETHER_STAR){
					if((int)storage.getCoins(player)<4000){
						int needed = 4000- (int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						e.setCancelled(true);
						player.closeInventory();
						return;
					}
					Stats.setTier(pname,puuid,3,"ARMOUR");
					CoinAPI.subtractTokens(player,4000);
					player.sendMessage(Main.prefix+"You've bought §aTier III §7Armour for §a4000 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);
				}
				if((int)storage.getArmourTier(player)==3 && e.getCurrentItem().getType() == Material.NETHER_STAR){
					if((int)storage.getCoins(player)<8000){
						int needed = 8000- (int)storage.getCoins(player) ;
						player.sendMessage(Main.prefix+"§cYou cannot afford this transaction");
						player.sendMessage(Main.prefix+"§cYou need §a"+ needed + " §cmore coins");
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK,1.0f,1.0f);
						e.setCancelled(true);
						player.closeInventory();
						return;
					}
					Stats.setTier(pname,puuid,4,"ARMOUR");
					CoinAPI.subtractTokens(player,8000);
					player.sendMessage(Main.prefix+"You've bought §aTier IV §7Armour for §a8000 §7coins");
					player.playSound(player.getLocation(), Sound.LEVEL_UP,2.0f,2.0f);
				}
				if((int)storage.getArmourTier(player)==4 && e.getCurrentItem().getType() == Material.NETHER_STAR){
					player.playSound(player.getLocation(), Sound.NOTE_BASS,0.5f,0.5f);
					player.sendMessage(Main.prefix+"§cMaximum upgrade acquired!");
				}

					new BukkitRunnable(){
					@Override
					public void run() {
						storage.updateTiers(player);

					}
				}.runTaskLater(Main.getPlugin(),5);
			// Kit Selector
				if(e.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE){
					if((int)storage.getBruteTier(player)==0){
						player.playSound(player.getLocation(),Sound.NOTE_BASS,0.5f,0.5f);
						player.sendMessage(Main.prefix+"§cYou don't own this kit!");
						e.setCancelled(true);
						player.closeInventory();
						return;
					}
					Arena.selected.remove(player);
				Arena.selected.put(player,"BRUTE");
				player.sendMessage(Main.prefix+"You selected kit §aBrute");
				player.playSound(player.getLocation(),Sound.ANVIL_USE,1.0f,1.0f);
				Stats.setSelectedKit(pname,puuid,"BRUTE");
				}

				if(e.getCurrentItem().getType() == Material.DIAMOND_SWORD){
					if((int)storage.getRusherTier(player)==0){
						player.playSound(player.getLocation(),Sound.NOTE_BASS,0.5f,0.5f);
						player.sendMessage(Main.prefix+"§cYou don't own this kit!");
						e.setCancelled(true);
						player.closeInventory();

						return;
					}
					Arena.selected.remove(player);
					Arena.selected.put(player,"RUSHER");
					player.sendMessage(Main.prefix+"You selected kit §aRusher");
					player.playSound(player.getLocation(),Sound.HURT_FLESH,1.0f,1.0f);
					Stats.setSelectedKit(pname,puuid,"RUSHER");

				}
				if(e.getCurrentItem().getType() == Material.POTION){
					if((int)storage.getHealerTier(player)==0){
						player.playSound(player.getLocation(),Sound.NOTE_BASS,0.5f,0.5f);
						player.sendMessage(Main.prefix+"§cYou don't own this kit!");
						e.setCancelled(true);
						player.closeInventory();
						return;
					}
					Arena.selected.remove(player);
					Arena.selected.put(player,"HEALER");
					player.sendMessage(Main.prefix+"You selected kit §aHealer");
					player.playSound(player.getLocation(),Sound.DRINK,1.0f,1.0f);
					Stats.setSelectedKit(pname,puuid,"HEALER");
				}
				if(e.getCurrentItem().getType() == Material.BOW){
					if((int)storage.getArcherTier(player)==0){
						player.playSound(player.getLocation(),Sound.NOTE_BASS,0.5f,0.5f);
						player.sendMessage(Main.prefix+"§cYou don't own this kit!");
						e.setCancelled(true);
						player.closeInventory();

						return;
					}
					Arena.selected.remove(player);
					Arena.selected.put(player,"ARCHER");
					player.sendMessage(Main.prefix+"You selected kit §aArcher");
					player.playSound(player.getLocation(),Sound.SHOOT_ARROW,1.0f,1.0f);
					Stats.setSelectedKit(pname,puuid,"ARCHER");
				}
				e.setCancelled(true);
				player.closeInventory();

			}

   }


@SuppressWarnings("deprecation")
public void SkullTeleport(Player player,ItemStack item){
    if(item.getType() != Material.AIR && item != null){
            SkullMeta skullmeta = (SkullMeta)item.getItemMeta();
            if(skullmeta.getDisplayName() != null){
                    if(Bukkit.getPlayer(skullmeta.getDisplayName()) != null){
                            Player target = Bukkit.getPlayer(skullmeta.getDisplayName());
                            player.teleport(target);
                            player.sendMessage(Main.prefix +"Now spectating... " + target.getDisplayName());
                    } 
            }
    }
}
@EventHandler
public void onCli2(PlayerInteractEvent e){
	Player p = e.getPlayer();
	Action a = e.getAction();
	if(a==Action.RIGHT_CLICK_BLOCK || a==Action.RIGHT_CLICK_AIR){
		
		ItemStack hand = p.getItemInHand();
		if(hand!=null&&hand.getType()==Material.EYE_OF_ENDER&&Main.getInstance().arenas.get(0).watching.contains(p)){
   				Main.SendToServer(p.getName(), "lobby", p);
   				return;
				}
	   				
		if(hand!=null&&hand.getType()==Material.BOOK&&Main.getInstance().arenas.get(0).watching.contains(p)){
	   			Inventories.getServers.OpenSGGUI(p);
	   			}
		}
	}
@EventHandler
public void onInteract(PlayerInteractEvent e) {
	for (Arena arena : Main.getInstance().arenas) {
		if (arena.watching.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
		if (((e.getAction().equals(Action.LEFT_CLICK_AIR)) || (e.getAction().equals(Action.RIGHT_CLICK_AIR) ||
				e.getAction().equals(Action.RIGHT_CLICK_AIR)) || (e.getAction().equals(Action.RIGHT_CLICK_AIR) ))){
			if(arena.watching.contains(e.getPlayer())) {
				if (e.getItem().getType() == Material.BOAT) {
					e.setCancelled(true);
				}
			}
		}
	}
	
}
@EventHandler
public void onClickinv(InventoryClickEvent e){
	if(e.getInventory().getName().contains("Spectate")){
		e.setCancelled(true);
		Player p = (Player)e.getWhoClicked();
		if(e.getCurrentItem().getType().equals(Material.SKULL_ITEM)){
			SkullTeleport(p,e.getCurrentItem());
		}
	}
}
@EventHandler
public void onPlayerInteract(PlayerInteractEvent event) {
	for (final Arena arena : Main.getInstance().arenas) {
		if (arena.getIngame().contains(event.getPlayer())) {
			Player player = event.getPlayer();
			if ((event.getAction() == Action.LEFT_CLICK_BLOCK)
					&& (player.getTargetBlock((HashSet<Byte>)null, 5).getType() == Material.FIRE)) {
				final Location loc = player.getTargetBlock((HashSet<Byte>)null, 5).getLocation();
				new BukkitRunnable() {
					public void run() {
						if (arena.cleanup) {
							loc.getBlock().setType(Material.AIR);
							cancel();
						}
					}
				}.runTaskTimer(Main.getInstance(), 20L, 20L);
			}
		}
	}
}
}

