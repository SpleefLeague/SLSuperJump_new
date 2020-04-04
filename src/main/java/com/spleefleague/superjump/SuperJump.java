/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.spleefleague.superjump;

import com.mongodb.client.MongoDatabase;
import com.spleefleague.core.Core;
import com.spleefleague.core.game.Leaderboard;
import com.spleefleague.core.menu.InventoryMenu;
import com.spleefleague.core.player.PlayerManager;
import com.spleefleague.core.plugin.CorePlugin;
import com.spleefleague.superjump.commands.*;
import com.spleefleague.superjump.game.SJArena;
import com.spleefleague.superjump.game.SJMode;
import com.spleefleague.superjump.game.conquest.ConquestSJArena;
import com.spleefleague.superjump.game.endless.EndlessSJArena;
import com.spleefleague.superjump.game.pro.ProSJArena;
import com.spleefleague.superjump.game.versus.VersusSJArena;
import com.spleefleague.superjump.player.SuperJumpPlayer;
import org.bukkit.Material;

/**
 * @author NickM13
 */
public class SuperJump extends CorePlugin {
    
    private static SuperJump instance;
    
    private InventoryMenu superJumpMenu;
    
    @Override
    public void init() {
        instance = this;
        
        initCommands();
        
        playerManager = new PlayerManager<>(this, SuperJumpPlayer.class, getPluginDB().getCollection("Players"));
        
        SJMode.init();
        for (SJMode mode : SJMode.values()) {
            if (mode.hasQueue()) {
                addBattleManager(mode.getArenaMode());
            }
        }
        
        SJArena.init();
        initMenu();
        initLeaderboards();
        
        playerManager.initOnline();
    }
    
    public static SuperJump getInstance() {
        return instance;
    }
    
    @Override
    public void close() {
        playerManager.close();
    }
    
    private void initCommands() {
        Core.getInstance().addCommand(new SuperJumpCommand());
        
        Core.getInstance().flushCommands();
    }
    
    public InventoryMenu getSJMenu() {
        return superJumpMenu;
    }
    
    private void initMenu() {
        superJumpMenu = InventoryMenu.createMenu()
                .setTitle("SuperJump Menu")
                .setName("SuperJump")
                .setDescription("Jump and run your way to the finish line as fast as you can. Whether you are racing a single opponent, a group of friends, or even the clock, the objective is the same!")
                .setDisplayItem(Material.LEATHER_BOOTS, 65);
        
        superJumpMenu.addMenuItem(EndlessSJArena.createMenu(), 2);
        superJumpMenu.addMenuItem(ConquestSJArena.createMenu(), 3);
        superJumpMenu.addMenuItem(VersusSJArena.createMenu(), 4);
        //superJumpMenu.addMenuItem(PartySJArena.createMenu(), 5);
        //superJumpMenu.addMenuItem(PracticeSJArena.createMenu(), 6);
        superJumpMenu.addMenuItem(ProSJArena.createMenu(), 7);
        
        InventoryMenu.getHotbarMenu(InventoryMenu.InvMenuType.SLMENU).addMenuItem(superJumpMenu, 0, 1);
    }
    
    private void initLeaderboards() {
        Leaderboard.init(EndlessSJArena.EndlessLeaderboard.DAILY.getName(), Leaderboard.LeaderboardStyle.DAILY);
        Leaderboard.init(EndlessSJArena.EndlessLeaderboard.BEST.getName(), Leaderboard.LeaderboardStyle.ALLTIME);
    }
    
    @Override
    public MongoDatabase getPluginDB() {
        return Core.getInstance().getMongoClient().getDatabase("SuperJump");
    }
    
}
