package main.java.net.bigbadcraft.bigbadcraftrpg.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class FileHandler {
	
	private Plugin plugin;
	private String fileName;
	private File file;
	private FileConfiguration configuration;
	
	public FileHandler(Plugin plugin, String fileName, File file, FileConfiguration configuration){
		this.plugin = plugin;
		this.fileName = fileName;
		this.file = file;
		this.configuration = configuration;
	}
	
	public void reloadSettings(){
		if (file == null){
			file = new File(plugin.getDataFolder(), fileName);
		}
		configuration = YamlConfiguration.loadConfiguration(file);
		InputStream stream = plugin.getResource(fileName);
		if (stream != null){
			YamlConfiguration streamConf = YamlConfiguration.loadConfiguration(stream);
			configuration.setDefaults(streamConf);
		}
	}
	
	public void saveSettings(){
		if (configuration == null || file == null){
			return;
		}
		try{
			configuration.save(file);
		}catch(IOException e){
			Utils.log(Level.SEVERE, "Could not save config to " + file);
		}
	}
	
}
