package main.java.net.bigbadcraft.bigbadcraftrpg.objects;

import org.bukkit.Location;

public class Warp {

	private String warpName;
	private Location loc;
	
	public Warp(String warpName, Location loc){
		this.warpName = warpName;
		this.loc = loc;
	}
	
	public void setName(String warpName){
		this.warpName = warpName;
	}
	
	public String getName(){
		return warpName;
	}
	
	public void setLocation(Location loc){
		this.loc = loc;
	}
	
	public Location getLocation(){
		return loc;
	}
	
	public String toString(){
		String world = loc.getWorld().getName();
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		float yaw = loc.getYaw();
		float pitch = loc.getPitch();
		return warpName+":"+world+":"+x+":"+y+":"+z+":"+yaw+":"+pitch;
	}
	
}
