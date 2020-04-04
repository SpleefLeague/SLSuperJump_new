/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.spleefleague.superjump.game.party;

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
public class PartySJArena extends SJArena {
    
    public PartySJArena() {
        mode = SJMode.PARTY.getArenaMode();
    }
    
    public static InventoryMenu createMenu() {
        String mainColor = ChatColor.AQUA + "" + ChatColor.BOLD;
        InventoryMenu spleefMenu = InventoryMenu.createMenu()
                .setTitle("Party SuperJump Menu")
                .setName(mainColor + "SuperJump: Party")
                .setDescription("Party Description.")
                .setDisplayItem(Material.DIAMOND_AXE, 20);
        
        InventoryMenu spleefMapMenu = InventoryMenu.createMenu()
                .setTitle("Map Select: Party Spleef")
                .setName("Map Select: Party Spleef")
                .setDisplayItem(new ItemStack(Material.FILLED_MAP));
        
        spleefMapMenu.addMenuItem(InventoryMenu.createItem()
                .setName("Random Arena")
                .setDisplayItem(new ItemStack(Material.EMERALD))
                .setAction(cp -> SuperJump.getInstance().queuePlayer(SJMode.PARTY.getArenaMode(), SuperJump.getInstance().getPlayers().get(cp))));
        
        getArenas(SJMode.PARTY.getArenaMode()).forEach((String s, Arena arena) -> spleefMapMenu.addMenuItem(InventoryMenu.createItem()
                .setName(arena.getDisplayName())
                .setDescription(cp -> arena.getDescription())
                .setDisplayItem(cp -> { return new ItemStack(Material.FILLED_MAP); })
                .setAction(cp -> SuperJump.getInstance().queuePlayer(SJMode.PARTY.getArenaMode(), SuperJump.getInstance().getPlayers().get(cp), arena))));
        
        spleefMenu.addMenuItem(spleefMapMenu, 0);
        
        return spleefMenu;
    }

}
