package fr.edminecoreteam.cspaintball.game.weapons.pistolets;

import fr.edminecoreteam.cspaintball.Core;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class USPS implements Listener
{

    private static final Core core = Core.getInstance();

    private double recoil = 0.1; //recul de tir
    private double speed_shoot = 5; //vitesse de tir
    private int bullet_charger = 9; //nombre de balles par chargeur
    private int max_bullet = 27; //total de munitions
    private Material weapon = Material.WOOD_HOE; //materiel de l'ame
    private String weapon_name = "USP-s"; //titre de l'arme
    private int wait_for_shoot_delay = 7; //temps d'armement (ticks)
    private int weightslow = 0; //niveau de vitesse (quand l'arme est porté)
    private int time_refill = 2; //temps de recharge (secondes)




    public void get(Player p)
    {
        if (!p.getInventory().contains(weapon))
        {
            if (!core.weaponsList().getUsps_max_bullet_count().containsKey(p) && !core.weaponsList().getUsps_bullet_charger_count().containsKey(p))
            {
                core.weaponsList().getUsps_max_bullet_count().put(p, max_bullet);
                core.weaponsList().getUsps_bullet_charger_count().put(p, bullet_charger);

                ItemStack gunStarter = new ItemStack(weapon, core.weaponsList().getUsps_bullet_charger_count().get(p));
                ItemMeta gunStarterM = gunStarter.getItemMeta();
                gunStarterM.setDisplayName("§f" + weapon_name + " §a" + core.weaponsList().getUsps_bullet_charger_count().get(p) + "§8/§a" + core.weaponsList().getUsps_max_bullet_count().get(p));
                gunStarter.setItemMeta((ItemMeta)gunStarterM);
                p.getInventory().addItem(gunStarter);
                weightcheck(p);
                return;
            }
            ItemStack gunStarter = new ItemStack(weapon, core.weaponsList().getUsps_bullet_charger_count().get(p));
            ItemMeta gunStarterM = gunStarter.getItemMeta();
            gunStarterM.setDisplayName("§f" + weapon_name + " §a" + core.weaponsList().getUsps_bullet_charger_count().get(p) + "§8/§a" + core.weaponsList().getUsps_max_bullet_count().get(p));
            gunStarter.setItemMeta((ItemMeta)gunStarterM);
            p.getInventory().addItem(gunStarter);
            weightcheck(p);
        }
        else
        {
            if (core.weaponsList().getUsps_max_bullet_count().get(p) == 0 && core.weaponsList().getUsps_bullet_charger_count().get(p) == 0)
            {
                ItemStack[] inventory = p.getInventory().getContents();
                for (int slot = 0; slot < inventory.length; slot++) {
                    ItemStack item = inventory[slot];

                    if (item != null && item.getType() == weapon) {
                        ItemStack gunStarter = new ItemStack(weapon, 1);
                        ItemMeta gunStarterM = gunStarter.getItemMeta();
                        gunStarterM.setDisplayName("§f" + weapon_name + " §cPlus de munitions...");
                        gunStarter.setItemMeta((ItemMeta)gunStarterM);
                        p.getInventory().setItem(slot, gunStarter);
                        core.weaponsList().getUsps_max_bullet_count().remove(p);
                        core.weaponsList().getUsps_bullet_charger_count().remove(p);
                    }
                }
            }
            ItemStack[] inventory = p.getInventory().getContents();
            for (int slot = 0; slot < inventory.length; slot++) {
                ItemStack item = inventory[slot];

                if (item != null && item.getType() == weapon) {
                    ItemStack gunStarter = new ItemStack(weapon, core.weaponsList().getUsps_bullet_charger_count().get(p));
                    ItemMeta gunStarterM = gunStarter.getItemMeta();
                    gunStarterM.setDisplayName("§f" + weapon_name + " §a" + core.weaponsList().getUsps_bullet_charger_count().get(p) + "§8/§a" + core.weaponsList().getUsps_max_bullet_count().get(p));
                    gunStarter.setItemMeta((ItemMeta)gunStarterM);
                    p.getInventory().setItem(slot, gunStarter);
                }
            }
        }
    }

    /*@EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (e.getItemDrop().getItemStack().getType() == weapon)
        {
            if (e.getItemDrop().getItemStack().getItemMeta().getDisplayName().contains(weapon_name))
            {
                e.setCancelled(true);
                refill(p);
            }
        }
    }*/

    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack it = e.getItem();
        if (it == null) { return; }

        if (it.getType() == weapon && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().contains(weapon_name)
                && (a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK))
        {
            if (core.weaponsList().getUsps_max_bullet_count().containsKey(p) && core.weaponsList().getUsps_bullet_charger_count().containsKey(p))
            {
                e.setCancelled(true);
                ItemStack hand = p.getInventory().getItemInHand();

                if (!core.weaponsList().getUsps_refill().contains(p))
                {
                    if (!core.weaponsList().getUsps_wait_for_shoot().contains(p))
                    {
                        if (core.weaponsList().getUsps_bullet_charger_count().get(p) > 1)
                        {
                            int get_bullet_charger_count = core.weaponsList().getUsps_bullet_charger_count().get(p) - 1;
                            core.weaponsList().getUsps_bullet_charger_count().replace(p, get_bullet_charger_count);
                            get(p);

                            Snowball snowball = p.launchProjectile(Snowball.class);

                            double speed = speed_shoot;
                            snowball.setVelocity(p.getLocation().getDirection().multiply(speed));
                            Vector pushDirection = p.getLocation().getDirection().multiply(-recoil);
                            p.setVelocity(pushDirection);
                            for (Player pls : core.getServer().getOnlinePlayers())
                            {
                                pls.playSound(p.getLocation(), Sound.EXPLODE, 0.5f, 2.0f);
                            }
                            core.weaponsList().getUsps_wait_for_shoot().add(p);
                            new BukkitRunnable() {
                                int t = 0;
                                int f = 0;
                                public void run() {

                                    ++t;
                                    ++f;
                                    if (f == wait_for_shoot_delay) {
                                        core.weaponsList().getUsps_wait_for_shoot().remove(p);
                                        cancel();
                                    }

                                    if (t == 1) {
                                        run();
                                    }
                                }
                            }.runTaskTimer((Plugin) core, 0L, 1L);
                            return;
                        }
                        if (core.weaponsList().getUsps_bullet_charger_count().get(p) == 1)
                        {
                            int get_bullet_charger_count = core.weaponsList().getUsps_bullet_charger_count().get(p) - 1;
                            core.weaponsList().getUsps_bullet_charger_count().replace(p, get_bullet_charger_count);
                            refill(p);

                            Snowball snowball = p.launchProjectile(Snowball.class);

                            double speed = speed_shoot;
                            snowball.setVelocity(p.getLocation().getDirection().multiply(speed));
                            Vector pushDirection = p.getLocation().getDirection().multiply(-recoil);
                            p.setVelocity(pushDirection);
                            for (Player pls : core.getServer().getOnlinePlayers())
                            {
                                pls.playSound(p.getLocation(), Sound.EXPLODE, 0.5f, 2.0f);
                            }
                        }
                        if (core.weaponsList().getUsps_bullet_charger_count().get(p) <= 0 || core.weaponsList().getUsps_max_bullet_count().get(p) <= 0 || !core.weaponsList().getUsps_max_bullet_count().containsKey(p))
                        {
                            get(p);
                            for (Player pls : core.getServer().getOnlinePlayers())
                            {
                                pls.playSound(p.getLocation(), Sound.CLICK, 0.8f, 1.5f);
                            }
                        }
                    }
                }
                else
                {
                    for (Player pls : core.getServer().getOnlinePlayers())
                    {
                        pls.playSound(p.getLocation(), Sound.CLICK, 0.8f, 1.5f);
                    }
                }
            }
            else
            {
                for (Player pls : core.getServer().getOnlinePlayers())
                {
                    pls.playSound(p.getLocation(), Sound.CLICK, 0.8f, 1.5f);
                }
            }

        }
        if (it.getType() == weapon && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().contains(weapon_name)
                && (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK))
        {
            e.setCancelled(true);
            if (!core.weaponsList().getUsps_refill().contains(p))
            {
                refill(p);
                return;
            }
            get(p);
        }
    }

    public void refill(Player p)
    {

        if (core.weaponsList().getUsps_refill().contains(p))
        {
            return;
        }

        if (!core.weaponsList().getUsps_max_bullet_count().containsKey(p) && !core.weaponsList().getUsps_bullet_charger_count().containsKey(p))
        {
            return;
        }

        if (core.weaponsList().getUsps_max_bullet_count().get(p) == 0)
        {
            return;
        }

        core.weaponsList().getUsps_refill().add(p);
        int finaltime_refill = 0;

        for (int i = 0 ; i <= time_refill ; i++)
        {
            finaltime_refill = finaltime_refill + 20;
        }
        int finalTime_refill = finaltime_refill / 2;
        new BukkitRunnable() {

            int t = 0;
            int f = 0;
            int m = finalTime_refill;

            public void run() {
                ++t;
                ++f;

                sendProgressBar(p, "Recharge en cours...", f, m);

                if (f == m) {
                    int diff = bullet_charger - core.weaponsList().getUsps_bullet_charger_count().get(p);
                    if (core.weaponsList().getUsps_max_bullet_count().get(p) > 0) {
                        if (core.weaponsList().getUsps_max_bullet_count().get(p) >= diff) {
                            int new_max_bullet_count = core.weaponsList().getUsps_max_bullet_count().get(p) - diff;
                            core.weaponsList().getUsps_max_bullet_count().replace(p, new_max_bullet_count);
                            core.weaponsList().getUsps_bullet_charger_count().replace(p, 9);
                            get(p);
                            core.weaponsList().getUsps_refill().remove(p);
                            core.title.sendActionBar(p,"");
                            cancel();
                        } else if (core.weaponsList().getUsps_max_bullet_count().get(p) < diff) {
                            int new_max_bullet_count = core.weaponsList().getUsps_max_bullet_count().get(p) - diff;
                            int real_diff = 0;
                            for (int slot = new_max_bullet_count; slot == 0; slot++) {
                                ++real_diff;
                            }
                            int new_bullet_charger_count = core.weaponsList().getUsps_bullet_charger_count().get(p) + real_diff;
                            core.weaponsList().getUsps_max_bullet_count().replace(p, 0);
                            core.weaponsList().getUsps_bullet_charger_count().replace(p, new_bullet_charger_count + real_diff);
                            get(p);
                            core.weaponsList().getUsps_refill().remove(p);
                            core.title.sendActionBar(p,"");
                            cancel();
                        }
                    } else if (core.weaponsList().getUsps_max_bullet_count().get(p) == 0) {
                        for (Player pls : core.getServer().getOnlinePlayers()) {
                            pls.playSound(p.getLocation(), Sound.CLICK, 0.8f, 1.5f);
                        }
                    }
                }
                if (t == 1) {
                    run();
                }
            }
        }.runTaskTimer((Plugin) core, 0L, 1L);
    }

    public void weightcheck(Player p)
    {
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.SLOW, 999999999, weightslow, false, false);

        new BukkitRunnable() {
            int t = 0;
            public void run() {

                if (p.getInventory().contains(weapon))
                {
                    if (p.getInventory().getItemInHand().getType() == weapon)
                    {
                        p.addPotionEffect(potionEffect);
                    }
                    else
                    {
                        for (PotionEffect effect : p.getActivePotionEffects())
                        {
                            p.removePotionEffect(effect.getType());
                        }
                    }
                }
                else
                {
                    cancel();
                }

                ++t;
                if (t == 1) {
                    run();
                }
            }
        }.runTaskTimer((Plugin) core, 0L, 2L);
    }

    public void sendProgressBar(Player player, String message, int current, int max) {

        float percentage = (float) current / max;
        int progressBars = Math.round(percentage * 10);
        String progressBarString = "§a";
        for (int i = 0; i < 10; i++) {
            if (i < progressBars) {
                progressBarString += "⬛";
            } else {
                progressBarString += "§7⬛";
            }
        }
        String actionBarMessage = progressBarString + " §6" + message;
        core.title.sendActionBar(player, actionBarMessage);

    }
}
