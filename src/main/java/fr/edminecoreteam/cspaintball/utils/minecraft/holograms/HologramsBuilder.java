package fr.edminecoreteam.cspaintball.utils.minecraft.holograms;

import fr.edminecoreteam.cspaintball.Core;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.*;

public class HologramsBuilder
{
    private final static Core core = Core.getInstance();
    private final HashMap<String, List<ArmorStand>> armorStands = new HashMap<>();
    private final HashMap<Player, HashMap<String, List<EntityArmorStand>>> armorStandsNMS = new HashMap<>();

    public HologramsBuilder()
    {
        //To Main
    }

    public void createBukkitHologram(String id, List<String> entry, Location location)
    {
        List<ArmorStand> aList = new ArrayList<>();
        for (String en : entry)
        {
            Location loc = new Location(location.getWorld(), location.getX(), location.getY() - 0.3f, location.getZ());
            ArmorStand armorStand = (ArmorStand) Bukkit.getWorld(location.getWorld().getName()).spawnEntity(loc, EntityType.ARMOR_STAND);
            armorStand.setVisible(false);
            armorStand.setSmall(true);
            armorStand.setCustomName(en);
            armorStand.setCustomNameVisible(true);
            armorStand.setGravity(false);
            armorStand.setMarker(true);
            aList.add(armorStand);
        }
        armorStands.put(id, aList);
        System.out.println("EDMINE-API: Load Hologram with ID: " + id + " | and loads " + entry.size() + " entities.");
    }

    public void removeBukkitHolgram(String id)
    {
        for (Map.Entry<String, List<ArmorStand>> en : armorStands.entrySet())
        {
            String key = en.getKey();
            if (key.equalsIgnoreCase(id))
            {
                int entities = 0;
                for (ArmorStand stand : en.getValue())
                {
                    stand.remove();
                    entities++;
                }
                armorStands.remove(id);
                System.out.println("EDMINE-API: Remove Hologram with ID: " + id + " | and removed " + entities + " entities.");
                return;
            }
        }
    }

    public void updateLineBukkitHolograms(String id, int getLine, String newLine)
    {
        int line = getLine - 1;
        for (Map.Entry<String, List<ArmorStand>> en : armorStands.entrySet())
        {
            String key = en.getKey();
            if (key.equalsIgnoreCase(id))
            {
                ArmorStand armorStand = en.getValue().get(line);
                armorStand.setCustomName(newLine);
                System.out.println("EDMINE-API: Update Hologram Line (" + getLine + ") with ID: " + id);
                return;
            }
        }
    }

    public void removeLineBukkitHolograms(String id, int getLine)
    {
        for (Map.Entry<String, List<ArmorStand>> en : armorStands.entrySet())
        {
            String key = en.getKey();
            if (key.equalsIgnoreCase(id))
            {
                for(int i = 0; i < en.getValue().size(); i++)
                {
                    if (i > getLine)
                    {
                        ArmorStand armorStand = en.getValue().get(i);
                        Location loc = new Location(armorStand.getWorld(), armorStand.getLocation().getX(), armorStand.getLocation().getY() + 0.3f, armorStand.getLocation().getZ());
                        armorStand.teleport(loc);
                    }
                }
                en.getValue().remove(getLine);
                System.out.println("EDMINE-API: Remove Hologram Line (" + getLine + ") with ID: " + id);
            }
        }
    }

    public void createPacketHologram(Player p, String id, List<String> entry, Location location)
    {
        List<EntityArmorStand> aList = new ArrayList<>();
        for (String en : entry)
        {
            WorldServer ws = ((CraftWorld)location.getWorld()).getHandle();
            EntityArmorStand nmsStand = new EntityArmorStand(ws);
            nmsStand.setLocation(location.getX(), location.getY() -0.3f, location.getZ(), 0.0f, 0.0f);
            nmsStand.setInvisible(true);
            nmsStand.setSmall(true);
            nmsStand.setCustomName(en);
            nmsStand.setCustomNameVisible(true);
            nmsStand.setGravity(false);
            aList.add(nmsStand);
            PacketPlayOutSpawnEntityLiving sendPacket = new PacketPlayOutSpawnEntityLiving((EntityLiving) nmsStand);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(sendPacket);
        }
        HashMap<String, List<EntityArmorStand>> innerMap = new HashMap<>();
        innerMap.put(id, aList);
        armorStandsNMS.put(p, innerMap);
        System.out.println("EDMINE-API: Load Packet Hologram for player (" + p.getName() + ") with ID: " + id + " | and send " + entry.size() + " packets.");
    }

    public void removePacketHologram(Player p, String id)
    {
        for (Map.Entry<Player, HashMap<String, List<EntityArmorStand>>> en : armorStandsNMS.entrySet())
        {
            Player key = en.getKey();
            if (key == p)
            {
                HashMap<String, List<EntityArmorStand>> innerMap = en.getValue();
                for (Map.Entry<String, List<EntityArmorStand>> entry : innerMap.entrySet())
                {
                    if (entry.getKey().equalsIgnoreCase(id))
                    {
                        int packets = 0;
                        for (EntityArmorStand entityArmorStands : entry.getValue())
                        {
                            PacketPlayOutEntityDestroy sendPacket = new PacketPlayOutEntityDestroy(entityArmorStands.getId());
                            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(sendPacket);
                            packets++;
                        }
                        armorStandsNMS.remove(p, innerMap);
                        System.out.println("EDMINE-API: Remove Packet Hologram for player (" + p.getName() + ") with ID: " + id + " | and removed " + packets + " packets.");
                        return;
                    }
                }
            }
        }
    }
}
