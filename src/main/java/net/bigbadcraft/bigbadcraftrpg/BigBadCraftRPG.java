package main.java.net.bigbadcraft.bigbadcraftrpg;

import java.io.File;
import java.util.HashSet;
import java.util.List;

import main.java.net.bigbadcraft.bigbadcraftrpg.commands.CommandManager;
import main.java.net.bigbadcraft.bigbadcraftrpg.listeners.ColourizedSignListener;
import main.java.net.bigbadcraft.bigbadcraftrpg.listeners.DeathSpawnListener;
import main.java.net.bigbadcraft.bigbadcraftrpg.listeners.EntityDropIngotListener;
import main.java.net.bigbadcraft.bigbadcraftrpg.listeners.GodModeListener;
import main.java.net.bigbadcraft.bigbadcraftrpg.listeners.NoMobSpawnerListener;
import main.java.net.bigbadcraft.bigbadcraftrpg.managers.VoteTokenManager;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.ConfigHandler;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.ConfigPath;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
	
	public ConfigHandler confHandler;
	
	/* Custom configuration files */
	public File goldFile;
	public FileConfiguration goldConf;
	public File voteTokenFile;
	public FileConfiguration voteTokenConf;
	public File spawnFile;
	public FileConfiguration spawnConf;
	
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
		
		confHandler = new ConfigHandler(this);
		
		/* Initialize and load the gold ingots balances file */
		goldFile = new File(getDataFolder(), "goldingotbalances.yml");
		Utils.loadFile(goldFile);
		goldConf = YamlConfiguration.loadConfiguration(goldFile);
		confHandler.reloadGoldConf();
		
		voteTokenFile = new File(getDataFolder(), "votetokens.yml");
		Utils.loadFile(voteTokenFile);
		voteTokenConf = YamlConfiguration.loadConfiguration(voteTokenFile);
		confHandler.reloadVoteTokenConf();
		
		spawnFile = new File(getDataFolder(), "spawn.yml");
		Utils.loadFile(spawnFile);
		spawnConf = YamlConfiguration.loadConfiguration(spawnFile);
		confHandler.reloadSpawnConf();
		
		registerListener(new EntityDropIngotListener(this));
		registerListener(new GodModeListener(this));
		registerListener(new DeathSpawnListener(this));
		registerListener(new ColourizedSignListener());
		registerListener(new NoMobSpawnerListener());
		
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
	}
	
	private void registerListener(Listener listener){
		getServer().getPluginManager().registerEvents(listener, this);
	}
	
}
