package me.TYSluukie.fdchub;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener, PluginMessageListener {
	
	@SuppressWarnings("deprecation")
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	    this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
			public void run() {
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("PlayerCount");
				out.writeUTF("ALL");
			}
		}, 20, 20);
	}
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("BungeeCord")) {
			return;
		}
	    
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.getPlayer().sendMessage("HALLO JUMBO!");
		ScoreboardManager sm = Bukkit.getScoreboardManager();
		Scoreboard onJoin = sm.getNewScoreboard();
		Objective o = onJoin.registerNewObjective("FreedomCraft", "dummy");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "FlameNetwork");
		int ALL = 1;
		int Kingdomcount = 0;
		int Kitpvpcount = 0;
		int Creativecount = 0;
		Score spacer = null;
		Score players = null;
		Score sp = null;
		Score Kingdom = null;
		Score Kitpvp = null;
		Score Creative = null;
		Score sc = null;
		Score ip = null;
		spacer = o.getScore(ChatColor.AQUA + "");
		spacer.setScore(7);
		players = o.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Online spelers: " + ChatColor.GREEN + ALL);
		players.setScore(6);
		sp = o.getScore(ChatColor.AQUA + "");
		sp.setScore(5);
		Kingdom = o.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Kingdom: " + ChatColor.GREEN + Kingdomcount);
		Kingdom.setScore(4);
		Kitpvp = o.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Kitpvp: " + ChatColor.GREEN + Kitpvpcount);
		Kitpvp.setScore(3);
		Creative = o.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Creative: " + ChatColor.GREEN + Creativecount);
		Creative.setScore(2);
		sc = o.getScore(ChatColor.AQUA + "");
		sc.setScore(1);
		ip = o.getScore(ChatColor.RED + "" + ChatColor.BOLD + "play.FreedomKingdoms.nl");
		ip.setScore(0);
		p.setScoreboard(onJoin);
	}
}
