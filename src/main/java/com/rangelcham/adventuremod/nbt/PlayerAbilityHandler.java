package com.rangelcham.adventuremod.nbt;


import com.rangelcham.adventuremod.player.abilities.dash.DashHandler;
import com.rangelcham.adventuremod.player.abilities.doublejump.DoubleJumpHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class PlayerAbilityHandler {

    private static final String DOUBLE_JUMP_KEY = "hasDoubleJump";
    private static final String DASH_KEY = "hasDash";
    private static final String ABILITIES_KEY = "abilities";

    public static void saveAbilities(Player player) {
        CompoundTag tag = player.getPersistentData();
        CompoundTag abilityTag = new CompoundTag();
        abilityTag.putBoolean(DOUBLE_JUMP_KEY, DoubleJumpHandler.unlockedDoubleJump);
        abilityTag.putBoolean(DASH_KEY, DashHandler.unlockedDash);
        tag.put(ABILITIES_KEY, abilityTag);
        player.getPersistentData().put(ABILITIES_KEY, abilityTag);
    }

    public static void loadAbilities(Player player) {
        CompoundTag tag = player.getPersistentData();
        CompoundTag abilityTag = tag.getCompound(ABILITIES_KEY);
        if (abilityTag.contains(DOUBLE_JUMP_KEY)) {
            boolean doubleJump = abilityTag.getBoolean(DOUBLE_JUMP_KEY);
            if (doubleJump) {
                DoubleJumpHandler.unlockedDoubleJump = true;
            }
        }
        if (abilityTag.contains(DASH_KEY)) {
            boolean dash = abilityTag.getBoolean(DASH_KEY);
            if (dash) {
                DashHandler.unlockedDash = true;
            }
        }
    }
}