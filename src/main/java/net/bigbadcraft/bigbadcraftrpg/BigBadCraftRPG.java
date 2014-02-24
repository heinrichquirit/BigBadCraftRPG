package main.java.net.bigbadcraft.bigbadcraftrpg;

import java.io.File;
import java.util.HashSet;

import main.java.net.bigbadcraft.bigbadcraftrpg.commands.CommandManager;
import main.java.net.bigbadcraft.bigbadcraftrpg.listeners.EntityDropIngotListener;
import main.java.net.bigbadcraft.bigbadcraftrpg.listeners.GodModeListener;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.ConfigHandler;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.ConfigPath;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class BigBadCraftRPG extends JavaPlugin {
	
	private static BigBadCraftRPG instance;
	
	private final HashSet<String> commands = new HashSet<String>();
	public final HashSet<String> godmode = new HashSet<String>();
	
	/* Configuration settings */
	public String colourScheme;
	public int maxSpawnmobLimit;
	public int ingotMaxLimit;
	
	public ConfigHandler confHandler;
	
	/* Custom configuration files */
	public File goldFile;
	public FileConfiguration goldConf;
	
	public void onEnable() {
		
		instance = this;
		
		populateCommands();
		
		saveDefaultConfig();
		
		colourScheme = getConfig().getString(ConfigPath.COLOUR_SCHEME);
		ingotMaxLimit = getConfig().getInt(ConfigPath.INGOT_MAX);
		maxSpawnmobLimit = getConfig().getInt(ConfigPath.MOB_SPAWN_LIMIT);
		
		confHandler = new ConfigHandler(this);
		
		/* Initialize and load the gold ingots balances file */
		goldFile = new File(getDataFolder(), "goldingotbalances.yml");
		Utils.loadFile(goldFile);
		goldConf = YamlConfiguration.loadConfiguration(goldFile);
		confHandler.reloadGoldConf();
		
		registerListener(new EntityDropIngotListener(this));
		registerListener(new GodModeListener(this));
		
		for (String command:commands){
			getCommand(command).setExecutor(new CommandManager(this));
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
	}
	
	private void registerListener(Listener listener){
		getServer().getPluginManager().registerEvents(listener, this);
	}
	
	public static BigBadCraftRPG getInstance(){
		return instance;
	}
}
