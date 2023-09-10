package fr.edminecoreteam.cspaintball.game.weapons;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class WeaponsMap
{

    private final Map<Player, HashMap<String, Integer>> weaponMap;
    private final HashMap<Player, String> weapon_refill;
    private final HashMap<Player, String> weapon_wait_for_shoot;

    public WeaponsMap()
    {
        this.weaponMap = new HashMap<Player, HashMap<String, Integer>>();
        this.weapon_refill = new HashMap<Player, String>();
        this.weapon_wait_for_shoot = new HashMap<Player, String>();
    }

    public Map<Player, HashMap<String, Integer>> getMap()
    {
        return this.weaponMap;
    }

    public HashMap<String, Integer> getHashMap(Player p)
    {
        return weaponMap.get(p);
    }
    public HashMap<Player, String> getWeapon_refill() { return this.weapon_refill; }
    public HashMap<Player, String> getWeapon_wait_for_shoot() { return this.weapon_wait_for_shoot; }
}
