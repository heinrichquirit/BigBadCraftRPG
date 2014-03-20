package main.java.net.bigbadcraft.bigbadcraftrpg.utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;


public class ConfigHandler {
	
	private Plugin plugin;
	private String file_name;
	private File file;
	private FileConfiguration configuration;
	
	public ConfigHandler(Plugin plugin, String file_name) {
		this.plugin = plugin;
		this.file_name = file_name;	
		file = new File(plugin.getDataFolder(), file_name);
		configuration = YamlConfiguration.loadConfiguration(file);
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setConfiguration(FileConfiguration configuration) {
		this.configuration = configuration;
	}
	
	public FileConfiguration getConfiguration() {
		return configuration;
	}
	
	public void load() {
		file = new File(plugin.getDataFolder(), file_name);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		configuration = YamlConfiguration.loadConfiguration(file);
		reload();
	}
	
	public void reload() {
		if (file == null) {
			file = new File(plugin.getDataFolder(), file_name);
		}
		configuration = YamlConfiguration.loadConfiguration(file);
		InputStream stream = plugin.getResource(file_name);
		if (stream != null) {
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(stream);
			configuration.setDefaults(defConfig);
		}
	}
	
	public void save() {
		if (configuration == null || file == null) {
			return;
		}
		try {
			configuration.save(file);
		} catch (IOException e) {
			plugin.getLogger().severe("Could not save configuration settings to " + file);
		}
	}
	
	public void destroy() {
		if (file.exists()) {
			if (!file.delete()) {
				plugin.getLogger().info("Could not delete file " + file); 
				return;
			}
			plugin.getLogger().severe("Successfully deleted file " + file);
		}
	}
}
