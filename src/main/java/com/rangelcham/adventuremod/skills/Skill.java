package com.rangelcham.adventuremod.skills;

import net.minecraft.world.entity.player.Player;

//
//Author: RangelCham73
//

public class Skill {
    private final String name;
    private final SkillType type;
    private final int cost;

    public Skill(String name, SkillType type, int cost) {
        this.name = name;
        this.type = type;
        this.cost = cost;
    }

    public void apply(Player player) {
        player.getPersistentData().putBoolean(name, true);
    }
}
