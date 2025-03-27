package com.rangelcham.adventuremod.nbt;

import com.rangelcham.adventuremod.player.ModPlayer;
import com.rangelcham.adventuremod.player.abilities.dash.DashHandler;
import com.rangelcham.adventuremod.player.abilities.doublejump.DoubleJumpHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class PlayerStatHandler {
    private static final String STATS_KEY = "stats";
    private static final String STRENGTH_KEY = "strength";
    private static final String DEXTERITY_KEY = "dexterity";
    private static final String CONSTITUTION_KEY = "constitution";
    private static final String INTELLIGENCE_KEY = "intelligence";
    private static final String WISDOM_KEY = "wisdom";

    public static void saveStats(Player player, ModPlayer modPlayer) {
        CompoundTag tag = player.getPersistentData();
//        Minecraft.getInstance().gui.getChat().addMessage(Component.literal(tag.getAsString())); // debug
        CompoundTag statTag = new CompoundTag();
        statTag.putInt(STRENGTH_KEY, modPlayer.stats.strength);
        statTag.putInt(DEXTERITY_KEY, modPlayer.stats.dexterity);
        statTag.putInt(CONSTITUTION_KEY, modPlayer.stats.constitution);
        statTag.putInt(INTELLIGENCE_KEY, modPlayer.stats.intelligence);
        statTag.putInt(WISDOM_KEY, modPlayer.stats.wisdom);
        tag.put(STATS_KEY, statTag);
//        Minecraft.getInstance().gui.getChat().addMessage(Component.literal(tag.getAsString())); // debug
        player.getPersistentData().put("stats", statTag);
    }

    public static int getStrength(Player player) {
        CompoundTag tag = player.getPersistentData();
//        Minecraft.getInstance().gui.getChat().addMessage(Component.literal(tag.getAsString())); // debug
        CompoundTag statTag = tag.getCompound(STATS_KEY);
        if (statTag.contains(STRENGTH_KEY)) {
            return statTag.getInt(STRENGTH_KEY);
        } else {
            return 6;
        }
    }

    public static int getDexterity(Player player) {
        CompoundTag tag = player.getPersistentData();
//        Minecraft.getInstance().gui.getChat().addMessage(Component.literal(tag.getAsString())); // debug
        CompoundTag statTag = tag.getCompound(STATS_KEY);
        if (statTag.contains(DEXTERITY_KEY)) {
            return statTag.getInt(DEXTERITY_KEY);
        } else {
            return 6;
        }
    }

    public static int getConstitution(Player player) {
        CompoundTag tag = player.getPersistentData();
//        Minecraft.getInstance().gui.getChat().addMessage(Component.literal(tag.getAsString())); // debug
        CompoundTag statTag = tag.getCompound(STATS_KEY);
        if (statTag.contains(CONSTITUTION_KEY)) {
            return statTag.getInt(CONSTITUTION_KEY);
        } else {
            return 6;
        }
    }

    public static int getIntelligence(Player player) {
        CompoundTag tag = player.getPersistentData();
//        Minecraft.getInstance().gui.getChat().addMessage(Component.literal(tag.getAsString())); // debug
        CompoundTag statTag = tag.getCompound(STATS_KEY);
        if (statTag.contains(INTELLIGENCE_KEY)) {
            return statTag.getInt(INTELLIGENCE_KEY);
        } else {
            return 6;
        }
    }

    public static int getWisdom(Player player) {
        CompoundTag tag = player.getPersistentData();
//        Minecraft.getInstance().gui.getChat().addMessage(Component.literal(tag.getAsString())); // debug
        CompoundTag statTag = tag.getCompound(STATS_KEY);
        if (statTag.contains(WISDOM_KEY)) {
            return statTag.getInt(WISDOM_KEY);
        } else {
            return 6;
        }
    }
}
