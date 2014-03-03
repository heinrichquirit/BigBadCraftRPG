package main.java.net.bigbadcraft.bigbadcraftrpg.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;

import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigHandler {
	
	private BigBadCraftRPG plugin;
	public ConfigHandler(BigBadCraftRPG plugin) {
		this.plugin = plugin;
	}
	
	public void reloadGoldConf() {
		if (plugin.goldFile == null) 
			plugin.goldFile = new File(plugin.getDataFolder(), "goldingotbalances.yml");
		
		plugin.goldConf = YamlConfiguration.loadConfiguration(plugin.goldFile);
		
		InputStream stream = plugin.getResource("goldingotbalances.yml");
		if (stream != null) {
			YamlConfiguration streamConf = YamlConfiguration.loadConfiguration(stream);
			plugin.goldConf.setDefaults(streamConf);
		}
			
	}
	
	public void saveGoldConf() {
		if (plugin.goldConf == null || plugin.goldFile == null)
			return;
		
		try {
			plugin.goldConf.save(plugin.goldFile);
		} catch (IOException e) {
			Utils.log(Level.SEVERE, "Could not save credits config to " + plugin.goldFile);
		}
	}
	
	public void reloadVoteTokenConf(){
		if (plugin.voteTokenFile == null){
			plugin.voteTokenFile = new File(plugin.getDataFolder(), "votetokens.yml");
		}
		plugin.voteTokenConf = YamlConfiguration.loadConfiguration(plugin.voteTokenFile);
		InputStream stream = plugin.getResource("votetokens.yml");
		if (stream != null){
			YamlConfiguration streamConf = YamlConfiguration.loadConfiguration(stream);
			plugin.voteTokenConf.setDefaults(streamConf);
		}
	}
	
	public void saveVoteTokenConf(){
		if (plugin.voteTokenConf == null || plugin.voteTokenFile == null){
			return;
		}
		try{
			plugin.voteTokenConf.save(plugin.voteTokenFile);
		}catch(IOException e){
			Utils.log(Level.SEVERE, "Could not save vote tokens config to " + plugin.voteTokenFile);
		}
	}
	
	public void reloadSpawnsConf(){
		if (plugin.spawnsFile == null){
			plugin.spawnsFile = new File(plugin.getDataFolder(), "spawns.yml");
		}
		plugin.spawnsConf = YamlConfiguration.loadConfiguration(plugin.spawnsFile);
		InputStream stream = plugin.getResource("spawns.yml");
		if (stream != null){
			YamlConfiguration streamConf = YamlConfiguration.loadConfiguration(stream);
			plugin.spawnsConf.setDefaults(streamConf);
		}
	}
	
	public void saveSpawnsConf(){
		if (plugin.spawnsConf == null || plugin.spawnsFile == null){
			return;
		}
		try{
			plugin.spawnsConf.save(plugin.spawnsFile);
		}catch(IOException e){
			Utils.log(Level.SEVERE, "Could not save vote tokens config to " + plugin.spawnsFile);
		}
	}
}
