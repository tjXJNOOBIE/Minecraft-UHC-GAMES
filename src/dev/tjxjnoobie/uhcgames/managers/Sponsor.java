package dev.tjxjnoobie.uhcgames.managers;

import info.techwizmatt.ServerCore.API.CoinAPI;
import dev.tjxjnoobie.uhcgames.Main;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.logging.Level;

public class Sponsor
  implements Listener
{
  public static Map<ItemStack,Integer > items = new HashMap<>();
  public static HashMap<Player, ItemStack> item = new HashMap();
  public static Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GREEN + "Choose an item");
  public static HashMap<Player, Player> players = new HashMap<>();
  public static ItemStack cake = new ItemStack(Material.CAKE,1);
  int arrow =80;
  public static void openMenu(Player player) {
    inv.clear();


    for (ItemStack item : items.keySet()) {

      ItemMeta meta = item.getItemMeta();
      meta.setDisplayName(ChatColor.LIGHT_PURPLE + WordUtils.capitalizeFully(item.getType().toString().replaceAll("_", " ").toLowerCase()));
      List<String> lore = new ArrayList();
      lore.add(ChatColor.GRAY + "" + items.get(item) + "");

      meta.setLore(lore);
      item.setItemMeta(meta);
      inv.addItem(item);
    }
    player.openInventory(inv);
  }
  
  @EventHandler
  public void onInteract(InventoryClickEvent event)
  {
    if (event.getInventory().getTitle().equals(inv.getTitle())) {
      Bukkit.getLogger().log(Level.INFO,"CLICKED ON " +event.getCurrentItem() +event.getCursor() + event.getWhoClicked().getItemOnCursor() +event.getWhoClicked().getItemInHand());

      Bukkit.getLogger().log(Level.INFO,"SPONSOR ITEMS " +items.get(event.getCursor()));
      Bukkit.getLogger().log(Level.INFO,"SPONSOR ITEMS " +items.get(event.getCursor()));
      Bukkit.getLogger().log(Level.INFO,"SPONSOR ITEMS " +items.get(event.getWhoClicked().getItemOnCursor()));
      Bukkit.getLogger().log(Level.INFO,"SPONSOR ITEMS " +items.get(event.getWhoClicked().getItemInHand()));
      if(event.getCurrentItem().getType() == Material.ARROW){

      }

      List<String> lore = event.getCurrentItem().getItemMeta().getLore();
      int d = 0;
      String lores = lore.get(d);
      int i = Integer.parseInt(ChatColor.stripColor(String.valueOf(lores)));
      Bukkit.getLogger().log(Level.INFO,"SPONSOR ITEMS " +items.get(event.getWhoClicked().getItemInHand()));

      PointManager manager = new PointManager();
      if(CoinAPI.GetTokens((Player) event.getWhoClicked()) < i) {
        event.getWhoClicked().sendMessage(Main.prefix + ChatColor.RED +"You can't afford this item.");
        return;
      }
      if (CoinAPI.GetTokens((Player) event.getWhoClicked()) >= i) {
          CoinAPI.subtractTokens((Player) event.getWhoClicked(),i);
        Player giveitems = (Player)players.get(event.getWhoClicked());
        ItemStack item = event.getCurrentItem();
        item.setAmount(event.getCurrentItem().getAmount());
        ((CommandSender) event.getWhoClicked()).sendMessage(Main.prefix + "Sent sponsor item to " + giveitems.getDisplayName());
        ItemMeta meta = new ItemStack(Material.STONE).getItemMeta();
        item.setItemMeta(meta);
        List<String> lore3 = new ArrayList<>();
        lore3.add("PURCHASED!");

        ItemMeta meta2 = event.getCurrentItem().getItemMeta();
        event.getCurrentItem().getItemMeta().setLore(lore3);
        event.getCurrentItem().setItemMeta(meta2);

        giveitems.getInventory().addItem(new ItemStack[] { item });
        giveitems.sendMessage(Main.getInstance().prefix + ChatColor.WHITE + " Suprise - you were sponsored by " + ChatColor.GREEN + ((Player) event.getWhoClicked()).getDisplayName() + ChatColor.WHITE + "!");
        giveitems.sendMessage(Main.getInstance().prefix + ChatColor.WHITE + "Remember to thank them!");
        giveitems.playSound(giveitems.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
      }
      event.setCancelled(true);
      Sponsor.items.replace(Sponsor.cake,100);
      Sponsor.items.replace(new ItemStack(Material.ENDER_PEARL),150);
      Sponsor.items.replace(new ItemStack(Material.BOW),100);
      Sponsor.items.replace(new ItemStack(Material.ARROW, 5),100);
      ItemStack fs = new ItemStack(Material.FLINT_AND_STEEL);
      Sponsor.items.replace(fs,80 );
      Sponsor.items.replace(new ItemStack(Material.EXP_BOTTLE, 5),100);
      Sponsor.items.replace(new ItemStack(Material.PORK),75);
      openMenu((Player) event.getWhoClicked());

    }
  }




    }
