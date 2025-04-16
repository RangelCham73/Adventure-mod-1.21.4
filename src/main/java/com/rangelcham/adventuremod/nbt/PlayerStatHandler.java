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
    private static final String LEVEL_KEY = "level";
    private static final String STAT_POINT_KEY = "statPoint";
    private static final String USED_STAT_POINT_KEY = "usedStatPoint";
    private static final String SPECIAL_POINT_KEY = "specialPoint";
    private static final String USED_SPECIAL_POINT_KEY = "usedSpecialPoint";
    private static final String ABILITY_POINT_KEY = "abilityPoint";
    private static final String USED_ABILITY_POINT_KEY = "usedAbilityPoint";
    private static final String EXPERIENCE_KEY = "experience";
    private static final String STRENGTH_KEY = "strength";
    private static final String DEXTERITY_KEY = "dexterity";
    private static final String CONSTITUTION_KEY = "constitution";
    private static final String INTELLIGENCE_KEY = "intelligence";
    private static final String WISDOM_KEY = "wisdom";

    public static void saveStats(Player player) {
        CompoundTag tag = player.getPersistentData();
        CompoundTag statTag = new CompoundTag();
        statTag.putInt(LEVEL_KEY, StatsHandler.modPlayer.level);
        statTag.putInt(STAT_POINT_KEY, StatsHandler.modPlayer.statPoints);
        statTag.putInt(USED_STAT_POINT_KEY, StatsHandler.modPlayer.usedStatPoints);
        statTag.putInt(SPECIAL_POINT_KEY, StatsHandler.modPlayer.specialStatPoints);
        statTag.putInt(USED_SPECIAL_POINT_KEY, StatsHandler.modPlayer.usedSpecialStatPoints);
        statTag.putInt(ABILITY_POINT_KEY, StatsHandler.modPlayer.abilityPoints);
        statTag.putInt(USED_ABILITY_POINT_KEY, StatsHandler.modPlayer.usedAbilityPoints);
        statTag.putInt(EXPERIENCE_KEY, StatsHandler.modPlayer.experiencePoints);
        statTag.putInt(STRENGTH_KEY, StatsHandler.modPlayer.stats.strength);
        statTag.putInt(DEXTERITY_KEY, StatsHandler.modPlayer.stats.dexterity);
        statTag.putInt(CONSTITUTION_KEY, StatsHandler.modPlayer.stats.constitution);
        statTag.putInt(INTELLIGENCE_KEY, StatsHandler.modPlayer.stats.intelligence);
        statTag.putInt(WISDOM_KEY, StatsHandler.modPlayer.stats.wisdom);
        tag.put(STATS_KEY, statTag);
        player.getPersistentData().put(STATS_KEY, statTag);
    }

    public static void loadStats(Player player) {
        CompoundTag tag = player.getPersistentData();
        CompoundTag statTag = tag.getCompound(STATS_KEY);

        // NIVEL
        if (statTag.contains(LEVEL_KEY)) {
            StatsHandler.modPlayer.level = statTag.getInt(LEVEL_KEY);
        } else {
            StatsHandler.modPlayer.level = 1;
        }

        // PUNTOS DE ATRIBUTO
        if (statTag.contains(STAT_POINT_KEY)) {
            StatsHandler.modPlayer.statPoints = statTag.getInt(STAT_POINT_KEY);
        } else {
            StatsHandler.modPlayer.statPoints = 0;
        }

        // PUNTOS DE ATRIBUTO USADOS
        if (statTag.contains(USED_STAT_POINT_KEY)) {
            StatsHandler.modPlayer.usedStatPoints = statTag.getInt(USED_STAT_POINT_KEY);
        } else {
            StatsHandler.modPlayer.usedStatPoints = 0;
        }

        // PUNTOS ESPECIALES
        if (statTag.contains(SPECIAL_POINT_KEY)) {
            StatsHandler.modPlayer.specialStatPoints = statTag.getInt(SPECIAL_POINT_KEY);
        } else {
            StatsHandler.modPlayer.specialStatPoints = 0;
        }

        // PUNTOS ESPECIALES USADOS
        if (statTag.contains(USED_SPECIAL_POINT_KEY)) {
            StatsHandler.modPlayer.usedSpecialStatPoints = statTag.getInt(USED_SPECIAL_POINT_KEY);
        } else {
            StatsHandler.modPlayer.usedSpecialStatPoints = 0;
        }

        // PUNTOS DE HABILIDAD
        if (statTag.contains(ABILITY_POINT_KEY)) {
            StatsHandler.modPlayer.abilityPoints = statTag.getInt(ABILITY_POINT_KEY);
        } else {
            StatsHandler.modPlayer.abilityPoints = 0;
        }

        // PUNTOS DE HABILIDAD USADOS
        if (statTag.contains(USED_ABILITY_POINT_KEY)) {
            StatsHandler.modPlayer.usedAbilityPoints = statTag.getInt(USED_ABILITY_POINT_KEY);
        } else {
            StatsHandler.modPlayer.usedAbilityPoints = 0;
        }

        // EXPERIENCIA
        if (statTag.contains(EXPERIENCE_KEY)) {
            StatsHandler.modPlayer.experiencePoints = statTag.getInt(EXPERIENCE_KEY);
        } else {
            StatsHandler.modPlayer.experiencePoints = 0;
        }

        // FUERZA
        if (statTag.contains(STRENGTH_KEY)) {
            StatsHandler.modPlayer.stats.strength = statTag.getInt(STRENGTH_KEY);
        }

        // DESTREZA
        if (statTag.contains(DEXTERITY_KEY)) {
            StatsHandler.modPlayer.stats.dexterity = statTag.getInt(DEXTERITY_KEY);
        }

        // CONSTITUCION
        if (statTag.contains(CONSTITUTION_KEY)) {
            StatsHandler.modPlayer.stats.constitution = statTag.getInt(CONSTITUTION_KEY);
        }

        // INTELIGENCIA
        if (statTag.contains(INTELLIGENCE_KEY)) {
            StatsHandler.modPlayer.stats.intelligence = statTag.getInt(INTELLIGENCE_KEY);
        }

        // SABIDURIA
        if (statTag.contains(WISDOM_KEY)) {
            StatsHandler.modPlayer.stats.wisdom = statTag.getInt(WISDOM_KEY);
        }
    }
}
