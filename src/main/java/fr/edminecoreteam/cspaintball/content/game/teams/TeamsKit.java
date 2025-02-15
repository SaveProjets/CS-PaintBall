package fr.edminecoreteam.cspaintball.content.game.teams;

import fr.edminecoreteam.cspaintball.Core;
import fr.edminecoreteam.cspaintball.content.game.weapons.Weapons;
import fr.edminecoreteam.cspaintball.content.game.weapons.WeaponsList;
import fr.edminecoreteam.cspaintball.utils.minecraft.skull.SkullNBT;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class TeamsKit
{
    private static Core core = Core.getInstance();
    private final ItemStack getSkull(String url) {
        return SkullNBT.getSkull(url);
    }

    public void repearArmor(Player p)
    {
        if (core.teams().getAttacker().contains(p))
        {
            ItemStack helmet = getSkull("http://textures.minecraft.net/texture/49a53f43f306f4597be9aeda17eb049501d544fda8d54a528cae5aae8eb3c4d1");

            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta chestplateM = (LeatherArmorMeta) chestplate.getItemMeta();
            chestplateM.setColor(Color.fromRGB(201, 158, 79));
            chestplate.setItemMeta(chestplateM);

            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta leggingsM = (LeatherArmorMeta) leggings.getItemMeta();
            leggingsM.setColor(Color.fromRGB(110,89,41));
            leggings.setItemMeta(leggingsM);

            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta bootsM = (LeatherArmorMeta) boots.getItemMeta();
            bootsM.setColor(Color.fromRGB(32, 32, 32));
            boots.setItemMeta(bootsM);

            p.getEquipment().setHelmet(helmet);
            p.getEquipment().setChestplate(chestplate);
            p.getEquipment().setLeggings(leggings);
            p.getEquipment().setBoots(boots);
        }
        if (core.teams().getDefenser().contains(p))
        {
            ItemStack helmet = getSkull("http://textures.minecraft.net/texture/331a5989478944f9b5f1a1813d598f41b34945ff08a08acd887bca027953848");

            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta chestplateM = (LeatherArmorMeta) chestplate.getItemMeta();
            chestplateM.setColor(Color.fromRGB(11, 38, 61));
            chestplate.setItemMeta(chestplateM);

            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta leggingsM = (LeatherArmorMeta) leggings.getItemMeta();
            leggingsM.setColor(Color.fromRGB(8, 32, 54));
            leggings.setItemMeta(leggingsM);

            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta bootsM = (LeatherArmorMeta) boots.getItemMeta();
            bootsM.setColor(Color.fromRGB(11, 38, 61));
            boots.setItemMeta(bootsM);

            p.getEquipment().setHelmet(helmet);
            p.getEquipment().setChestplate(chestplate);
            p.getEquipment().setLeggings(leggings);
            p.getEquipment().setBoots(boots);
        }
    }

    public void reEquip(Player p)
    {
        ItemStack shop = getSkull("http://textures.minecraft.net/texture/3e4e7384241617cca7a96d8979b0be358dcbd47427f5b2f0c370cb689be55c12");
        ItemMeta shopM = shop.getItemMeta();
        shopM.setDisplayName("§eBoutique D'Armement");
        shop.setItemMeta(shopM);

        if (core.teams().getAttacker().contains(p))
        {
            ItemStack helmet = getSkull("http://textures.minecraft.net/texture/49a53f43f306f4597be9aeda17eb049501d544fda8d54a528cae5aae8eb3c4d1");

            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta chestplateM = (LeatherArmorMeta) chestplate.getItemMeta();
            chestplateM.setColor(Color.fromRGB(201, 158, 79));
            chestplate.setItemMeta(chestplateM);

            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta leggingsM = (LeatherArmorMeta) leggings.getItemMeta();
            leggingsM.setColor(Color.fromRGB(110,89,41));
            leggings.setItemMeta(leggingsM);

            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta bootsM = (LeatherArmorMeta) boots.getItemMeta();
            bootsM.setColor(Color.fromRGB(32, 32, 32));
            boots.setItemMeta(bootsM);

            p.getEquipment().setHelmet(helmet);
            p.getEquipment().setChestplate(chestplate);
            p.getEquipment().setLeggings(leggings);
            p.getEquipment().setBoots(boots);
            p.getInventory().setItem(3, shop);
        }
        if (core.teams().getDefenser().contains(p))
        {
            ItemStack helmet = getSkull("http://textures.minecraft.net/texture/331a5989478944f9b5f1a1813d598f41b34945ff08a08acd887bca027953848");

            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta chestplateM = (LeatherArmorMeta) chestplate.getItemMeta();
            chestplateM.setColor(Color.fromRGB(11, 38, 61));
            chestplate.setItemMeta(chestplateM);

            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta leggingsM = (LeatherArmorMeta) leggings.getItemMeta();
            leggingsM.setColor(Color.fromRGB(8, 32, 54));
            leggings.setItemMeta(leggingsM);

            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta bootsM = (LeatherArmorMeta) boots.getItemMeta();
            bootsM.setColor(Color.fromRGB(11, 38, 61));
            boots.setItemMeta(bootsM);

            p.getEquipment().setHelmet(helmet);
            p.getEquipment().setChestplate(chestplate);
            p.getEquipment().setLeggings(leggings);
            p.getEquipment().setBoots(boots);
            p.getInventory().setItem(3, shop);
        }
    }

    public void equipNotDeathDefault(Player p)
    {
        p.setGameMode(GameMode.ADVENTURE);

        ItemStack shop = getSkull("http://textures.minecraft.net/texture/3e4e7384241617cca7a96d8979b0be358dcbd47427f5b2f0c370cb689be55c12");
        ItemMeta shopM = shop.getItemMeta();
        shopM.setDisplayName("§eBoutique D'Armement");
        shop.setItemMeta(shopM);

        if (core.teams().getAttacker().contains(p))
        {
            ItemStack helmet = getSkull("http://textures.minecraft.net/texture/49a53f43f306f4597be9aeda17eb049501d544fda8d54a528cae5aae8eb3c4d1");

            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta chestplateM = (LeatherArmorMeta) chestplate.getItemMeta();
            chestplateM.setColor(Color.fromRGB(201, 158, 79));
            chestplate.setItemMeta(chestplateM);

            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta leggingsM = (LeatherArmorMeta) leggings.getItemMeta();
            leggingsM.setColor(Color.fromRGB(110,89,41));
            leggings.setItemMeta(leggingsM);

            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta bootsM = (LeatherArmorMeta) boots.getItemMeta();
            bootsM.setColor(Color.fromRGB(32, 32, 32));
            boots.setItemMeta(bootsM);

            p.getEquipment().setHelmet(helmet);
            p.getEquipment().setChestplate(chestplate);
            p.getEquipment().setLeggings(leggings);
            p.getEquipment().setBoots(boots);

            p.setFoodLevel(16);
            p.setHealth(p.getMaxHealth());
            Weapons weapons = new Weapons(p);
            weapons.refillMax();
            p.getInventory().setItem(3, shop);
        }

        if (core.teams().getDefenser().contains(p))
        {
            ItemStack helmet = getSkull("http://textures.minecraft.net/texture/331a5989478944f9b5f1a1813d598f41b34945ff08a08acd887bca027953848");

            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta chestplateM = (LeatherArmorMeta) chestplate.getItemMeta();
            chestplateM.setColor(Color.fromRGB(11, 38, 61));
            chestplate.setItemMeta(chestplateM);

            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta leggingsM = (LeatherArmorMeta) leggings.getItemMeta();
            leggingsM.setColor(Color.fromRGB(8, 32, 54));
            leggings.setItemMeta(leggingsM);

            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta bootsM = (LeatherArmorMeta) boots.getItemMeta();
            bootsM.setColor(Color.fromRGB(11, 38, 61));
            boots.setItemMeta(bootsM);

            p.getEquipment().setHelmet(helmet);
            p.getEquipment().setChestplate(chestplate);
            p.getEquipment().setLeggings(leggings);
            p.getEquipment().setBoots(boots);

            p.setFoodLevel(16);
            p.setHealth(p.getMaxHealth());
            Weapons weapons = new Weapons(p);
            weapons.refillMax();
            p.getInventory().setItem(3, shop);
        }
    }

    public void equipDefault(Player p)
    {
        p.setGameMode(GameMode.ADVENTURE);

        ItemStack shop = getSkull("http://textures.minecraft.net/texture/3e4e7384241617cca7a96d8979b0be358dcbd47427f5b2f0c370cb689be55c12");
        ItemMeta shopM = shop.getItemMeta();
        shopM.setDisplayName("§eBoutique D'Armement");
        shop.setItemMeta(shopM);

        if (core.teams().getAttacker().contains(p))
        {
            p.getInventory().clear();
            ItemStack helmet = getSkull("http://textures.minecraft.net/texture/49a53f43f306f4597be9aeda17eb049501d544fda8d54a528cae5aae8eb3c4d1");

            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta chestplateM = (LeatherArmorMeta) chestplate.getItemMeta();
            chestplateM.setColor(Color.fromRGB(201, 158, 79));
            chestplate.setItemMeta(chestplateM);

            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta leggingsM = (LeatherArmorMeta) leggings.getItemMeta();
            leggingsM.setColor(Color.fromRGB(110,89,41));
            leggings.setItemMeta(leggingsM);

            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta bootsM = (LeatherArmorMeta) boots.getItemMeta();
            bootsM.setColor(Color.fromRGB(32, 32, 32));
            boots.setItemMeta(bootsM);

            p.getEquipment().setHelmet(helmet);
            p.getEquipment().setChestplate(chestplate);
            p.getEquipment().setLeggings(leggings);
            p.getEquipment().setBoots(boots);

            ItemStack knife = new ItemStack(Material.IRON_SWORD, 1);
            ItemMeta knifeM = knife.getItemMeta();
            knifeM.setDisplayName("§fCouteau");
            knife.setItemMeta(knifeM);

            p.getInventory().addItem(knife);
            p.setFoodLevel(16);

            Weapons weapons = new Weapons(p);
            weapons.get(WeaponsList.GLOCK18);
            p.getInventory().setItem(3, shop);
        }

        if (core.teams().getDefenser().contains(p))
        {
            p.getInventory().clear();
            ItemStack helmet = getSkull("http://textures.minecraft.net/texture/331a5989478944f9b5f1a1813d598f41b34945ff08a08acd887bca027953848");

            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta chestplateM = (LeatherArmorMeta) chestplate.getItemMeta();
            chestplateM.setColor(Color.fromRGB(11, 38, 61));
            chestplate.setItemMeta(chestplateM);

            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta leggingsM = (LeatherArmorMeta) leggings.getItemMeta();
            leggingsM.setColor(Color.fromRGB(8, 32, 54));
            leggings.setItemMeta(leggingsM);

            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta bootsM = (LeatherArmorMeta) boots.getItemMeta();
            bootsM.setColor(Color.fromRGB(11, 38, 61));
            boots.setItemMeta(bootsM);

            p.getEquipment().setHelmet(helmet);
            p.getEquipment().setChestplate(chestplate);
            p.getEquipment().setLeggings(leggings);
            p.getEquipment().setBoots(boots);

            ItemStack knife = new ItemStack(Material.IRON_SWORD, 1);
            ItemMeta knifeM = knife.getItemMeta();
            knifeM.setDisplayName("§fCouteau");
            knife.setItemMeta(knifeM);

            p.getInventory().addItem(knife);
            p.setFoodLevel(16);

            Weapons weapons = new Weapons(p);
            weapons.get(WeaponsList.USPS);
            p.getInventory().setItem(3, shop);
        }
    }
}
