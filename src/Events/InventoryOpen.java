package Events;

import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;

import net.minecraft.server.v1_8_R3.TileEntityChest;
import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.craftbukkit.v1_8_R3.block.CraftChest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.enchantments.Enchantment.PROTECTION_ENVIRONMENTAL;


public class InventoryOpen implements Listener{
	
	public int tier = 1;
	public HashMap<Integer,Block> tiers = new HashMap<>();
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Block APIBlock = Bukkit.getWorld(player.getWorld().getName()).getBlockAt(e.getClickedBlock().getLocation());
		Chest APIChest = (Chest) APIBlock.getState();
		CraftChest BukkitChest = (CraftChest) APIChest;
		TileEntityChest NMSChest = BukkitChest.getTileEntity();
		Random random = new Random();

		if (APIBlock.getType() == Material.CHEST&&e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (random.nextInt(100) <= 80) {
				tier = 1;
			}
			if (random.nextInt(100) <= 18) {
				tier = 2;
			}
			if (random.nextInt(100) <= 2) {
				tier = 3;
			}
			tiers.put(tier,e.getClickedBlock());



		}
	}
	// ** TIER TWO CHESTS ** \\
	@EventHandler
	public void onInventoryOpenEvent2(InventoryOpenEvent e) {

			ItemStack dhelm = new ItemStack(Material.DIAMOND_HELMET, 1);
			ItemMeta dehelmMeta = dhelm.getItemMeta();
			ItemStack dchest = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
			ItemMeta dchestMeta = dchest.getItemMeta();
			ItemStack dlegs = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
			ItemMeta dlegsMeta = dlegs.getItemMeta();
			ItemStack dboots = new ItemStack(Material.DIAMOND_BOOTS, 1);
			ItemMeta dbootsMeta = dboots.getItemMeta();
			ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
			EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
			ItemStack pot = new ItemStack(Material.POTION, 1);
			PotionMeta potmeta = (PotionMeta) pot.getItemMeta();
			ItemStack dsword = new ItemStack(Material.DIAMOND_SWORD, 1);
			ItemMeta dswordMeta = dsword.getItemMeta();
			ItemStack isword = new ItemStack(Material.IRON_SWORD, 1);
			ItemMeta iswordMeta = dsword.getItemMeta();
			PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 20*30, 0);
			PotionEffect speed1 = new PotionEffect(PotionEffectType.SPEED, 20*60, 0);
			PotionEffect fire = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20*60, 0);
			Block APIBlock = Bukkit.getWorld(e.getPlayer().getWorld().getName()).getBlockAt(tiers.get(tier).getLocation());
			Chest APIChest = (Chest) APIBlock.getState();
			CraftChest BukkitChest = (CraftChest) APIChest;
			TileEntityChest NMSChest = BukkitChest.getTileEntity();
			for (Arena a : Main.getInstance().arenas) {
				if (Arena.joinable) {
					return;
				}
				Random random = new Random();
				if (e.getInventory().getType() == InventoryType.ENCHANTING) {
					e.getInventory().setItem(1, new ItemStack(Material.LAPIS_ORE, 64, (short) 4));
				}

				if ((a.getIngame().contains(e.getPlayer())) && (e.getInventory().getType().equals(InventoryType.CHEST))
						&& (!a.used.contains(e.getInventory()) && e.getInventory().getSize() == 27)) {



					//*\\ Tier 1 //*\\
					if (tier == 1) {
						NMSChest.a("Tier I");
						e.getInventory().clear();
						a.used.add(e.getInventory());

						Random rand = new Random();
						if (rand.nextInt(100) <= 95) {
							int i = rand.nextInt(6);
							if (i == 0) {
								e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.BREAD, 2));
							}else if(i ==1){
								e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.WOOD, 24));

							}else if(i ==2){
								e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.ARROW, 12));

							}else if(i ==3){
								e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.COBBLESTONE, 24));
							}else if(i == 4){
								e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.DIAMOND_HELMET, 1));
							}else if(i == 5){
								e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.DIAMOND_BOOTS, 1));
							}
						}else {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.APPLE));
						}
						if (rand.nextInt(100) <= 25) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.COBBLESTONE, 24));
						}
						if (rand.nextInt(100) <= 20) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.WOOD, 24));
						}
						if (rand.nextInt(100) <= 15) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GOLD_INGOT, 4));
						}
						if (rand.nextInt(100) <= 25) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.WATER_BUCKET));
						}
						if (rand.nextInt(100) <= 20) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.LAVA_BUCKET));
						}
						if (rand.nextInt(100) <= 25) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.APPLE));
						}
						if (rand.nextInt(100) <= 15) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.ARROW, 12));
						}
						if (rand.nextInt(100) <= 10) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.DIAMOND));
						}


						if (rand.nextInt(100) <= 9.3) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.EXP_BOTTLE, 8));
						}
						if (rand.nextInt(100) <= 8.5) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.EXP_BOTTLE, 10));
						}
						if (rand.nextInt(100) <= 9.2) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.EXP_BOTTLE, 12));
						}

						if (rand.nextInt(100) <= 15) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.FISHING_ROD));
						}
						// BOOKS
						if (rand.nextInt(100) <= 9.3) {
							meta.addStoredEnchant(Enchantment.DAMAGE_ALL, 1, true);
							book.setItemMeta(meta);
							e.getInventory().setItem(Main.getRand(e.getInventory()), book);
						}

						if (rand.nextInt(100) <= 9.4) {
							meta.addStoredEnchant(Enchantment.ARROW_DAMAGE, 1, true);
							book.setItemMeta(meta);
							e.getInventory().setItem(Main.getRand(e.getInventory()), book);
						}

						if (rand.nextInt(100) <= 9.5) {
							meta.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
							book.setItemMeta(meta);
							e.getInventory().setItem(Main.getRand(e.getInventory()), book);
						}


						if (rand.nextInt(100) <= 35) {
							int i = rand.nextInt(1);
							if (i == 0) {
								e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.BREAD,2));
							} else {
								e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.APPLE));
							}
						}

						if (rand.nextInt(100) <= 12.1) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.BOW));
						}

						if (rand.nextInt(100) <= 9) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.IRON_SWORD));
						}
						if (rand.nextInt(100) <= 13) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GOLDEN_APPLE));
						}


						if (rand.nextInt(100) <= 10) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GOLD_INGOT, 2));
						}
						return;

					}
					//*\\ Tier 2 //*\\

					if (tier == 2) {
						NMSChest.a("Tier II");
						a.used.add(e.getInventory());
						e.getInventory().clear();
						a.used.add(e.getInventory());
						Random rand = new Random();


						//DIAMOND ARMOUR
						if (rand.nextInt(100) <= 20.1) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), dhelm);
						}
						if (rand.nextInt(100) <= 20.2) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), dchest);
						}
						if (rand.nextInt(100) <= 20.3) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), dlegs);
						}
						if (rand.nextInt(100) <= 20.4) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), dboots);
						}


						// MISC. ITEMS
						if (rand.nextInt(100) <= 14.2) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GOLD_INGOT, 12));
						}
						if (rand.nextInt(100) <= 15) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.ENDER_PEARL, 1));
						}

						// BOOKS


						if (rand.nextInt(100) <= 9.4) {
							meta.addStoredEnchant(Enchantment.DAMAGE_ALL, 2, true);
							book.setItemMeta(meta);
							e.getInventory().setItem(Main.getRand(e.getInventory()), book);
						}

						if (rand.nextInt(100) <= 9) {
							meta.addStoredEnchant(Enchantment.ARROW_DAMAGE, 2, true);
							book.setItemMeta(meta);
							e.getInventory().setItem(Main.getRand(e.getInventory()), book);
						}

						// POTIONS
						if (rand.nextInt(100) <= 16.66) {
							potmeta.addCustomEffect(speed, true);
							potmeta.setMainEffect(PotionEffectType.SPEED);
							potmeta.setDisplayName("Potion of Swiftness (30 Seconds)");
							pot.setItemMeta(potmeta);
							e.getInventory().setItem(Main.getRand(e.getInventory()), pot);
						}
						if (rand.nextInt(100) <= 16.66) {
							potmeta.clearCustomEffects();
							potmeta.setMainEffect(PotionEffectType.SPEED);
							potmeta.addCustomEffect(speed1, true);
							potmeta.setDisplayName("Potion of Swiftness (60 Seconds)");
							pot.setItemMeta(potmeta);
							e.getInventory().setItem(Main.getRand(e.getInventory()), pot);
						}


						// MISC. PT.2
						if (rand.nextInt(100) <= 10) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GOLDEN_APPLE));
						}
						if (rand.nextInt(100) <= 8) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.DIAMOND));
						}

						if (rand.nextInt(100) <= 98) {
							int i = rand.nextInt(5);
							if (i == 1) {
								e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.BREAD, 2));
							}else if(i == 2){
								e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.DIAMOND_HELMET, 1));
							}else if(i == 3){
								e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.DIAMOND_BOOTS, 1));
							}else if(i == 4){
								meta.addStoredEnchant(Enchantment.ARROW_DAMAGE, 2, true);
								book.setItemMeta(meta);
								e.getInventory().setItem(Main.getRand(e.getInventory()), book);
							}else if(i == 5){
								meta.addStoredEnchant(Enchantment.DAMAGE_ALL, 2, true);
								book.setItemMeta(meta);
								e.getInventory().setItem(Main.getRand(e.getInventory()), book);

							} else {
								e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GOLDEN_APPLE));
							}
						}
						if (rand.nextInt(100) <= 35) {
							int i = rand.nextInt(1);
							if (i == 1) {
								e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.BREAD,2));
							} else {
								e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GOLDEN_APPLE));
							}
						}



						if (rand.nextInt(100) <= 15) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.BOW));
						}
						if (rand.nextInt(100) <= 1) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GOLDEN_APPLE));
						}
						if (rand.nextInt(100) <= 13) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GOLD_INGOT, 4));
						}
						if (rand.nextInt(100) <= 10) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.DIAMOND));
						}
						return;

					}
					//*\\ Tier 3 //*\\

					if (tier == 3) {
						NMSChest.a("Tier III");
						a.used.add(e.getInventory());
						e.getInventory().clear();
						a.used.add(e.getInventory());
						Random rand = new Random();
						if (rand.nextInt(100) <= 70) {
							potmeta.setMainEffect(PotionEffectType.SPEED);
							potmeta.addCustomEffect(speed1, true);
							potmeta.setDisplayName("Potion of Swiftness (60 Seconds)");
							pot.setItemMeta(potmeta);
							e.getInventory().setItem(Main.getRand(e.getInventory()), pot);
						}
						if (rand.nextInt(100) <= 70) {
							potmeta.clearCustomEffects();
							potmeta.setMainEffect(PotionEffectType.FIRE_RESISTANCE);
							potmeta.addCustomEffect(fire, true);
							potmeta.setDisplayName("Potion of Fire Resistance (60 Seconds)");
							pot.setItemMeta(potmeta);
							e.getInventory().setItem(Main.getRand(e.getInventory()), pot);
						}
						if (rand.nextInt(100) <= 70) {
							meta.addStoredEnchant(Enchantment.DAMAGE_ALL, 3, true);
							book.setItemMeta(meta);
							e.getInventory().setItem(Main.getRand(e.getInventory()), book);
						}
						if (rand.nextInt(100) <= 70) {
							meta.addStoredEnchant(Enchantment.ARROW_FIRE, 1, true);
							book.setItemMeta(meta);
							e.getInventory().setItem(Main.getRand(e.getInventory()), book);
						}
						if (rand.nextInt(100) <= 70) {
							meta.addStoredEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
							book.setItemMeta(meta);
							e.getInventory().setItem(Main.getRand(e.getInventory()), book);
						}
						if (rand.nextInt(100) <= 70) {
							e.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.ENDER_PEARL, 3));
						}
						return;

					}
					// DOUBLE CHEST
					Random rand = new Random();
					Inventory inventory = e.getInventory();
					if (a.getIngame().contains(e.getPlayer()) && (e.getInventory().getType().equals(InventoryType.CHEST))
							&& (!a.used.contains(e.getInventory()) && e.getInventory().getSize() == 54)) {
						Bukkit.getLogger().log(Level.INFO, "ITS A DOUBLE CHEST");
						DoubleChest doubleChest = (DoubleChest) e.getInventory();

						// You have a double chest instance
						if (rand.nextInt(100) <= 14.1) {
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), dhelm);
						}
						if (rand.nextInt(100) <= 14.2) {
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), dchest);
						}
						if (rand.nextInt(100) <= 14.3) {
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), dlegs);
						}
						if (rand.nextInt(100) <= 14.4) {
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), dboots);
						}
						if (rand.nextInt(100) <= 13.2) {
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GOLD_INGOT, 12));
						}
						if (rand.nextInt(100) <= 13) {
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.ENDER_PEARL, 1));
						}
						if (rand.nextInt(100) <= 7.1) {
							meta.addStoredEnchant(Enchantment.DAMAGE_ALL, 1, true);
							book.setItemMeta(meta);
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), book);


						}
						if (rand.nextInt(100) <= 5.1) {
							meta.addStoredEnchant(Enchantment.DAMAGE_ALL, 2, true);
							book.setItemMeta(meta);
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), book);
						}


						if (rand.nextInt(100) <= 7.2) {
							meta.addStoredEnchant(Enchantment.ARROW_DAMAGE, 1, true);
							book.setItemMeta(meta);
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), book);
						}
						if (rand.nextInt(100) <= 5.2) {
							meta.addStoredEnchant(Enchantment.ARROW_DAMAGE, 2, true);
							book.setItemMeta(meta);
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), book);
						}

						if (rand.nextInt(100) <= 7.3) {
							meta.addStoredEnchant(Enchantment.ARROW_FIRE, 2, true);
							book.setItemMeta(meta);
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), book);
						}
						if (rand.nextInt(100) <= 5.3) {
							meta.addStoredEnchant(Enchantment.ARROW_KNOCKBACK, 2, true);
							book.setItemMeta(meta);
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), book);
						}

						// MISC. PT.2
						if (rand.nextInt(100) <= 10) {
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GOLDEN_APPLE));
						}
						if (rand.nextInt(100) <= 8) {
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.DIAMOND));
						}
						if (rand.nextInt(100) <= 10) {
							ItemStack fs = new ItemStack(Material.FLINT_AND_STEEL);
							fs.setDurability((short) (fs.getDurability() - 4));
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), fs);
						}
						if (rand.nextInt(100) <= 55) {
							int i = rand.nextInt(4);
							if (i == 0) {

								doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.COOKED_BEEF));
							} else if (i == 1) {
								doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GRILLED_PORK));
							} else if (i == 2) {
								doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.COOKED_CHICKEN));
							} else if (i == 3) {
								doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.APPLE, 2));
							} else {
								doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GOLDEN_APPLE));
							}
						}
						if (rand.nextInt(100) <= 35) {
							int i = rand.nextInt(4);
							if (i == 0) {
								doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.COOKED_CHICKEN));
							} else if (i == 1) {
								doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.COOKED_BEEF));
							} else if (i == 2) {
								doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GRILLED_PORK));
							} else if (i == 3) {
								doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.APPLE, 2));
							} else {
								doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GOLDEN_APPLE));
							}
						}
						if (rand.nextInt(100) <= 35) {
							int i = rand.nextInt(4);
							if (i == 0) {
								doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.COOKED_CHICKEN));
							} else if (i == 1) {
								doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.COOKED_BEEF));
							} else if (i == 2) {
								doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GRILLED_PORK));
							} else if (i == 3) {
								doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.APPLE, 2));
							} else {
								doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GOLDEN_APPLE));
							}
						}
						if (rand.nextInt(100) <= 15) {
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.BOW));
						}
						if (rand.nextInt(100) <= 1) {
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GOLDEN_APPLE));
						}
						if (rand.nextInt(100) <= 13) {
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.GOLD_INGOT, 4));
						}
						if (rand.nextInt(100) <= 10) {
							doubleChest.getInventory().setItem(Main.getRand(e.getInventory()), new ItemStack(Material.DIAMOND));
						}
						a.used.add(doubleChest.getInventory());
					}
				}


			}
		}


	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (e.getInventory().getType() == InventoryType.ENCHANTING) {
			e.getInventory().setItem(1, null);
		}
	}

	public void setChestTitle(Player player, String title){
		Block APIBlock = Bukkit.getWorld(player.getWorld().getName()).getBlockAt(player.getLocation());
// Make sure, it is a Chest, otherwise you'll cause casting execptions
		if (APIBlock.getType() == Material.CHEST) {
			// Get the Spigot / Bukkit Chest (which doesn't contain a opportunity to set the title)
			Chest APIChest = (Chest) APIBlock.getState();
			// Get the origial intern Bukkit Class of the Chest
			CraftChest BukkitChest = (CraftChest) APIChest;
			// Get the Vanilla net.minecraft.server Chest out of it
			TileEntityChest NMSChest = BukkitChest.getTileEntity();
			// Now use the method "a" to set the Title
			NMSChest.a(title);
		}
	}

	}


