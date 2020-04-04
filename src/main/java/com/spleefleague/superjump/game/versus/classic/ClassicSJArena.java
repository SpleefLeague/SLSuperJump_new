/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.spleefleague.superjump.game.versus.classic;

import com.spleefleague.core.game.Arena;
import static com.spleefleague.core.game.Arena.getArenas;
import com.spleefleague.core.menu.InventoryMenu;
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
    
    public static InventoryMenu createMenu() {
        String mainColor = ChatColor.AQUA + "" + ChatColor.BOLD;
        InventoryMenu menu = InventoryMenu.createMenu()
                .setTitle("Classic SuperJump Menu")
                .setName(mainColor + "SuperJump: Classic")
                .setDescription("Classic Description.")
                .setDisplayItem(Material.DIAMOND_AXE, 20);
        
        InventoryMenu mapMenu = InventoryMenu.createMenu()
                .setTitle("Map Select: Classic Spleef")
                .setName("Map Select: Classic Spleef")
                .setDisplayItem(new ItemStack(Material.FILLED_MAP));
        
        mapMenu.addMenuItem(InventoryMenu.createItem()
                .setName("Random Arena")
                .setDisplayItem(new ItemStack(Material.EMERALD))
                .setAction(cp -> SuperJump.getInstance().queuePlayer(SJMode.CLASSIC.getArenaMode(), SuperJump.getInstance().getPlayers().get(cp))));
        
        getArenas(SJMode.CLASSIC.getArenaMode()).forEach((String s, Arena arena) -> mapMenu.addMenuItem(InventoryMenu.createItem()
                .setName(arena.getDisplayName())
                .setDescription(cp -> arena.getDescription())
                .setDisplayItem(cp -> { return new ItemStack(Material.FILLED_MAP); })
                .setAction(cp -> SuperJump.getInstance().queuePlayer(SJMode.CLASSIC.getArenaMode(), SuperJump.getInstance().getPlayers().get(cp), arena))));
        
        menu.addMenuItem(mapMenu, 0);
        
        return menu;
    }
}
