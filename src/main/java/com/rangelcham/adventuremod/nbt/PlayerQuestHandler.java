package com.rangelcham.adventuremod.nbt;

import com.rangelcham.adventuremod.player.abilities.dash.DashHandler;
import com.rangelcham.adventuremod.player.abilities.doublejump.DoubleJumpHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class PlayerQuestHandler {

    public static void saveQuests(Player player) {
        CompoundTag tag = player.getPersistentData();
//        Minecraft.getInstance().gui.getChat().addMessage(Component.literal(tag.getAsString())); // debug
        CompoundTag abilityTag = new CompoundTag();
        abilityTag.putBoolean("hasDoubleJump", DoubleJumpHandler.unlockedDoubleJump);
        abilityTag.putBoolean("hasDash", DashHandler.unlockedDash);
        tag.put("abilities", abilityTag);
//        Minecraft.getInstance().gui.getChat().addMessage(Component.literal(tag.getAsString())); // debug
        player.getPersistentData().put("abilities", abilityTag);
    }
}
