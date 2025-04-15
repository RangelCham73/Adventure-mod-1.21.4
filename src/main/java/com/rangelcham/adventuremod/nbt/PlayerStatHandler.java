package com.rangelcham.adventuremod.nbt;

import com.rangelcham.adventuremod.player.ModPlayer;
import com.rangelcham.adventuremod.player.abilities.dash.DashHandler;
import com.rangelcham.adventuremod.player.abilities.doublejump.DoubleJumpHandler;
import com.rangelcham.adventuremod.player.stats.StatsHandler;
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
        CompoundTag statTag = new CompoundTag();
        statTag.putInt(STRENGTH_KEY, modPlayer.stats.strength);
        statTag.putInt(DEXTERITY_KEY, modPlayer.stats.dexterity);
        statTag.putInt(CONSTITUTION_KEY, modPlayer.stats.constitution);
        statTag.putInt(INTELLIGENCE_KEY, modPlayer.stats.intelligence);
        statTag.putInt(WISDOM_KEY, modPlayer.stats.wisdom);
        tag.put(STATS_KEY, statTag);
        player.getPersistentData().put(STATS_KEY, statTag);
    }

    public static void loadStats(Player player) {
        CompoundTag tag = player.getPersistentData();
        CompoundTag statTag = tag.getCompound(STATS_KEY);

        // FUERZA
        if (statTag.contains(STRENGTH_KEY)) {
            StatsHandler.playerStats.strength = statTag.getInt(STRENGTH_KEY);
            if (StatsHandler.playerStats.strength >= StatsHandler.playerStats.CAP_STAT) {
                StatsHandler.playerStats.isMaxStrength = true;
            }
        } else {
            StatsHandler.playerStats.strength = 6;
            StatsHandler.playerStats.isMaxStrength = false;
        }

        // DESTREZA
        if (statTag.contains(DEXTERITY_KEY)) {
            StatsHandler.playerStats.dexterity = statTag.getInt(DEXTERITY_KEY);
            if (StatsHandler.playerStats.dexterity >= StatsHandler.playerStats.CAP_STAT) {
                StatsHandler.playerStats.isMaxDexterity = true;
            }
        } else {
            StatsHandler.playerStats.dexterity = 6;
            StatsHandler.playerStats.isMaxDexterity = false;
        }

        // CONSTITUCION
        if (statTag.contains(CONSTITUTION_KEY)) {
            StatsHandler.playerStats.constitution = statTag.getInt(CONSTITUTION_KEY);
            if (StatsHandler.playerStats.constitution >= StatsHandler.playerStats.CAP_STAT) {
                StatsHandler.playerStats.isMaxConstitution = true;
            }
        } else {
            StatsHandler.playerStats.constitution = 6;
            StatsHandler.playerStats.isMaxConstitution = false;
        }

        // INTELIGENCIA
        if (statTag.contains(INTELLIGENCE_KEY)) {
            StatsHandler.playerStats.intelligence = statTag.getInt(INTELLIGENCE_KEY);
            if (StatsHandler.playerStats.intelligence >= StatsHandler.playerStats.CAP_STAT) {
                StatsHandler.playerStats.isMaxIntelligence = true;
            }
        } else {
            StatsHandler.playerStats.intelligence = 6;
            StatsHandler.playerStats.isMaxIntelligence = false;
        }

        // SABIDURIA
        if (statTag.contains(WISDOM_KEY)) {
            StatsHandler.playerStats.wisdom = statTag.getInt(WISDOM_KEY);
            if (StatsHandler.playerStats.wisdom >= StatsHandler.playerStats.CAP_STAT) {
                StatsHandler.playerStats.isMaxWisdom = true;
            }
        } else {
            StatsHandler.playerStats.wisdom = 6;
            StatsHandler.playerStats.isMaxWisdom = false;
        }
    }
}
