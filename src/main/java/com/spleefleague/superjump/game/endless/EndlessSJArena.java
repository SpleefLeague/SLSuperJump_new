/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.spleefleague.superjump.game.endless;

import com.spleefleague.core.annotation.DBField;
import com.spleefleague.core.game.Arena;
import static com.spleefleague.core.game.Arena.getArenas;
import com.spleefleague.core.game.Leaderboard;
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
public class EndlessSJArena extends SJArena {
    
    public enum EndlessLeaderboard {
        DAILY("SJ_EL_DAILY"),
        BEST("SJ_EL_ALLTIME");
        
        String name;
        
        EndlessLeaderboard(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
    }
    
    @DBField
    protected Integer jumpcount;
    
    public EndlessSJArena() {
        mode = SJMode.ENDLESS.getArenaMode();
    }
    
    public static void initLeaderboard() {
        
    }
    
    public static InventoryMenu createMenu() {
        String mainColor = ChatColor.AQUA + "" + ChatColor.BOLD;
        InventoryMenu menu = InventoryMenu.createMenu()
                .setTitle("Endless SuperJump Menu")
                .setName(mainColor + "SuperJump: Endless")
                .setDescription("Endless Description.")
                .setDisplayItem(Material.DIAMOND_AXE, 20);
        
        getArenas(SJMode.ENDLESS.getArenaMode()).forEach((String s, Arena arena) -> menu.addMenuItem(InventoryMenu.createItem()
                .setName(arena.getDisplayName())
                .setDescription(cp -> arena.getDescription())
                .setDisplayItem(cp -> { return new ItemStack(Material.ENDER_EYE); })
                .setAction(cp -> SuperJump.getInstance().queuePlayer(SJMode.ENDLESS.getArenaMode(), SuperJump.getInstance().getPlayers().get(cp), arena))));
        
        return menu;
    }
    
    public int getJumpCount() {
        return jumpcount;
    }
    
}
