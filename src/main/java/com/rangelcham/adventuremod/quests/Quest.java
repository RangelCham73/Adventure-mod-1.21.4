package com.rangelcham.adventuremod.quests;

import net.minecraft.world.entity.player.Player;

import java.util.function.Consumer;

//
//Author: RangelCham73
//

public class Quest {
    private final String id;
    private final String description;
    private final Consumer<Player> reward;

    public Quest(String id, String description, Consumer<Player> reward) {
        this.id = id;
        this.description = description;
        this.reward = reward;
    }

    public void giveReward(Player player) {
        reward.accept(player);
    }
}