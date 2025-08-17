package dev.tjxjnoobie.uhcgames.managers;

import dev.tjxjnoobie.uhcgames.Main;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class MapManager
{
  public String MAP_NAME = "Error: 1";
  public ArrayList<Location> spawns = new ArrayList();
  public boolean inUse = false;

  public MapManager(String map_name) {
    this.MAP_NAME = map_name;
    if (this.spawns != null) {
      loadSpawns();
    }
  }

  public void toggleUse()
  {
    this.inUse = (!this.inUse);
  }

  public boolean getInUse()
  {
    return this.inUse;
  }

  public ArrayList<Location> getSpawns()
  {
    loadSpawns();
    return this.spawns;
  }

  public Location getSpawn(int ID)
  {

    return (Location)this.spawns.get(ID);
  }

  public void loadSpawns()
  {
    this.spawns.clear();
    List<Location> locations = (List<Location>) Main.getInstance().getConfig().getList("arena." + this.MAP_NAME + ".locations.spawns");
    this.spawns.addAll(locations);
  }

  public void addSpawn(Location location)
  {
    this.spawns.add(location);

    List<Location> locations = (List<Location>) Main.getInstance().getConfig().getList("arena." + this.MAP_NAME + ".locations.spawns");
    if (locations != null)
    {
      locations.add(location);
    }
    else
    {
      locations = new ArrayList();
      locations.add(location);
    }
    Main.getInstance().getConfig().set("arena." + this.MAP_NAME + ".locations.spawns", locations);
    Main.getInstance().saveConfig();
    loadSpawns();
  }

  public void removeSpawn(int ID)
  {
    this.spawns.remove(ID);
  }

  public void clearSpawns()
  {
    this.spawns.clear();
  }

  public String getMapName()
  {
    return this.MAP_NAME;
  }

  public void setMapName(String map_name)
  {
    this.MAP_NAME = map_name;
  }
}
