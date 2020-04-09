/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.spleefleague.superjump.game.versus.classic;

import com.spleefleague.core.game.Arena;
import com.spleefleague.core.menu.InventoryMenuAPI;
import com.spleefleague.core.menu.InventoryMenuItem;
import com.spleefleague.superjump.SuperJump;
import com.spleefleague.superjump.game.SJArena;
import com.spleefleague.superjump.game.SJMode;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * @author NickM13
 */
public class ClassicSJArena extends SJArena {
    
    public ClassicSJArena() {
        mode = SJMode.CLASSIC.getArenaMode();
    }
    
    public static InventoryMenuItem createMenu() {
        String mainColor = ChatColor.AQUA + "" + ChatColor.BOLD;
        InventoryMenuItem menuItem = InventoryMenuAPI.createItem()
                .setName(mainColor + "SuperJump: Classic")
                .setDescription("Classic Description.")
                .setDisplayItem(Material.DIAMOND_AXE, 22)
                .createLinkedContainer("SuperJump: Classic");
        
        InventoryMenuItem mapItem = InventoryMenuAPI.createItem()
                .setName("Map Select: Classic Spleef")
                .setDisplayItem(Material.FILLED_MAP)
                .createLinkedContainer("Map Select: Classic Spleef");
        
        mapItem.getLinkedContainer()
                .addMenuItem(InventoryMenuAPI.createItem()
                        .setName("Random Arena")
                        .setDisplayItem(Material.EMERALD));
        
        Arena.getArenas(SJMode.CLASSIC.getArenaMode()).forEach((String s, Arena arena) -> mapItem.getLinkedContainer()
                .addMenuItem(InventoryMenuAPI.createItem()
                        .setName(arena.getDisplayName())
                        .setDescription(cp -> arena.getDescription())
                        .setDisplayItem(cp -> { return new ItemStack(Material.ENDER_EYE); })
                        .setAction(cp -> SuperJump.getInstance().queuePlayer(SJMode.ENDLESS.getArenaMode(), SuperJump.getInstance().getPlayers().get(cp), arena))));
        Arena.getArenas(SJMode.CLASSIC.getArenaMode()).forEach((String s, Arena arena) -> mapItem.getLinkedContainer().addMenuItem(InventoryMenuAPI.createItem()
                .setName(arena.getDisplayName())
                .setDescription(cp -> arena.getDescription())
                .setDisplayItem(cp -> { return new ItemStack(Material.FILLED_MAP); })
                .setAction(cp -> SuperJump.getInstance().queuePlayer(SJMode.CLASSIC.getArenaMode(), SuperJump.getInstance().getPlayers().get(cp), arena))));
        
        menuItem.getLinkedContainer().addMenuItem(mapItem);
        
        return menuItem;
    }
}
