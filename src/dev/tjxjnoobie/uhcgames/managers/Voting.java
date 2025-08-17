package dev.tjxjnoobie.uhcgames.managers;

import info.techwizmatt.ServerCore.Rank.Rank;
import dev.tjxjnoobie.uhcgames.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


import java.util.ArrayList;

public class Voting {
	public ArrayList<Player> voted = new ArrayList();
	public static int i1;
	public static int i2;
	public static int i3;
	public static int i4;
	public static int i5;

	public void vote(Player player, int i) {

		if (Arena.joinable) {
			if (this.voted.contains(player)) {
				player.sendMessage(Main.prefix + ChatColor.RED + "You already voted for a map!");
			} else {
				if ((i > 5) || (i <= 0)) {
					player.sendMessage(Main.prefix + ChatColor.RED + "Please choose between 1-5.");
					return;
				}
				this.voted.add(player);

				player.sendMessage(Main.prefix + ChatColor.GOLD + "You voted for map " + ChatColor.RED +
						"#" + i + ChatColor.GOLD + ".");
				if (Rank.getRankLevel(player)>=11) {
					if (i == 1) {
						this.i1 += 100;
					} else if (i == 2) {
						this.i2 += 100;
					} else if (i == 3) {
						this.i3 += 100;
					} else if (i == 4) {
						this.i4 += 100;
					} else if (i == 5) {
						this.i5 += 100;
					}
				}
				if (Rank.getRankLevel(player)==5) {
					if (i == 1) {
						this.i1 += 2;
					} else if (i == 2) {
						this.i2 += 2;
					} else if (i == 3) {
						this.i3 += 2;
					} else if (i == 4) {
						this.i4 += 2;
					} else if (i == 5) {
						this.i5 += 2;
					}
				}
				if (Rank.getRankLevel(player)==6) {
					if (i == 1) {
						this.i1 += 3;
					} else if (i == 2) {
						this.i2 += 3;
					} else if (i == 3) {
						this.i3 += 3;
					} else if (i == 4) {
						this.i4 += 3;
					} else if (i == 5) {
						this.i5 += 3;
					}
				}
				if (Rank.getRankLevel(player)==7) {
					if (i == 1) {
						this.i1 += 5;
					} else if (i == 2) {
						this.i2 += 5;
					} else if (i == 3) {
						this.i3 += 5;
					} else if (i == 4) {
						this.i4 += 5;
					} else if (i == 5) {
						this.i5 += 5;
					}
				}
				if (Rank.getRankLevel(player)==8) {
					if (i == 1) {
						this.i1 += 5;
					} else if (i == 2) {
						this.i2 += 5;
					} else if (i == 3) {
						this.i3 += 5;
					} else if (i == 4) {
						this.i4 += 5;
					} else if (i == 5) {
						this.i5 += 5;
					}
				}
				if (Rank.getRankLevel(player)==3) {
					if (i == 1) {
						this.i1 += 5;
					} else if (i == 2) {
						this.i2 += 5;
					} else if (i == 3) {
						this.i3 += 5;
					} else if (i == 4) {
						this.i4 += 5;
					} else if (i == 5) {
						this.i5 += 5;
					}
				}
				if (Rank.getRankLevel(player)==9) {
					if (i == 1) {
						this.i1 += 5;
					} else if (i == 2) {
						this.i2 += 5;
					} else if (i == 3) {
						this.i3 += 5;
					} else if (i == 4) {
						this.i4 += 5;
					} else if (i == 5) {
						this.i5 += 5;
					}
				}
				if (Rank.getRankLevel(player)==10) {
					if (i == 1) {
						this.i1 += 5;
					} else if (i == 2) {
						this.i2 += 5;
					} else if (i == 3) {
						this.i3 += 5;
					} else if (i == 4) {
						this.i4 += 5;
					} else if (i == 5) {
						this.i5 += 5;
					}
				}
				if (Rank.getRankLevel(player)==1) {
					if (i == 1) {
						this.i1 += 1;
					} else if (i == 2) {
						this.i2 += 1;
					} else if (i == 3) {
						this.i3 += 1;
					} else if (i == 4) {
						this.i4 += 1;
					} else if (i == 5) {
						this.i5 += 1;
					}
				}
			}
		}
	}
}



