package com.rangelcham.adventuremod.abilities;

import net.minecraft.world.entity.player.Player;

//
//Author: RangelCham73
//

public interface IAbility {
    void activate(Player player);
    boolean isUnlocked(Player player);
}
