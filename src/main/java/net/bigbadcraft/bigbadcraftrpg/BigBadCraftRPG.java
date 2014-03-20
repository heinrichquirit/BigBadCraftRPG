package main.java.net.bigbadcraft.bigbadcraftrpg;

import java.util.HashSet;
import java.util.List;

import main.java.net.bigbadcraft.bigbadcraftrpg.commands.CommandManager;
import main.java.net.bigbadcraft.bigbadcraftrpg.listeners.BanCommandListener;
import main.java.net.bigbadcraft.bigbadcraftrpg.listeners.ColourizedSignListener;
import main.java.net.bigbadcraft.bigbadcraftrpg.listeners.DeathSpawnListener;
import main.java.net.bigbadcraft.bigbadcraftrpg.listeners.EntityDropIngotListener;
import main.java.net.bigbadcraft.bigbadcraftrpg.listeners.GodModeListener;
import main.java.net.bigbadcraft.bigbadcraftrpg.listeners.NoMobSpawnerListener;
import main.java.net.bigbadcraft.bigbadcraftrpg.listeners.SilkTouchListener;
import main.java.net.bigbadcraft.bigbadcraftrpg.managers.VoteTokenManager;
import main.java.net.bigbadcraft.bigbadcraftrpg.shop.ShopBuyListener;
import main.java.net.bigbadcraft.bigbadcraftrpg.shop.ShopCreateListener;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.ConfigHandler;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.ConfigPath;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class BigBadCraftRPG extends JavaPlugin {
	
	private final HashSet<String> commands = new HashSet<String>();
	public final HashSet<String> godmode = new HashSet<String>();
	
	/* Configuration settings */
	public int maxSpawnmobLimit;
	public int ingotMaxLimit;
	public List<String> rules;
	public List<String> voteTokens;
	public List<String> bannedCommands;
	
	public ConfigHandler goldHandler;
	public ConfigHandler voteHandler;
	public ConfigHandler spawnHandler;
	
	/* Managers */
	private CommandManager commandManager;
	public VoteTokenManager voteTokenManager;
	
	public void onEnable() {
		
		voteTokenManager = new VoteTokenManager(this);
		commandManager = new CommandManager(this);
		
		populateCommands();
		saveDefaultConfig();
		
		ingotMaxLimit = getConfig().getInt(ConfigPath.INGOT_MAX);
		maxSpawnmobLimit = getConfig().getInt(ConfigPath.MOB_SPAWN_LIMIT);
		rules = getConfig().getStringList(ConfigPath.RULES);
		voteTokens = getConfig().getStringList(ConfigPath.VOTE_TOKEN);
		bannedCommands = getConfig().getStringList(ConfigPath.BAN_COMMANDS);
		
		/* Neaten this up */
		goldHandler = new ConfigHandler(this, "goldingotbalances.yml");
		goldHandler.load();
		voteHandler = new ConfigHandler(this, "votetokens.yml");
		voteHandler.load();
		spawnHandler = new ConfigHandler(this, "spawn.yml");
		spawnHandler.load();
		
		registerListener(new EntityDropIngotListener(this));
		registerListener(new GodModeListener(this));
		registerListener(new DeathSpawnListener(this));
		registerListener(new ColourizedSignListener());
		registerListener(new NoMobSpawnerListener());
		registerListener(new SilkTouchListener());
		registerListener(new BanCommandListener(this));
		
		/* Shop Listener */
		registerListener(new ShopCreateListener());
		registerListener(new ShopBuyListener(this));
		
		for (String command:commands){
			getCommand(command).setExecutor(commandManager);
		}
		
	}
	
	private void populateCommands(){
		commands.add("viewgold");
		commands.add("bounty");
		commands.add("list");
		commands.add("fly");
		commands.add("night");
		commands.add("day");
		commands.add("god");
		commands.add("give");
		commands.add("killall");
		commands.add("spawnmob");
		commands.add("votetoken");
		commands.add("gamemode");
		commands.add("paygold");
		commands.add("clearinventory");
		commands.add("spawn");
		commands.add("rules");
		commands.add("teleport");
		commands.add("displaymana");
	}
	
	private void registerListener(Listener listener){
		getServer().getPluginManager().registerEvents(listener, this);
	}
	
}
