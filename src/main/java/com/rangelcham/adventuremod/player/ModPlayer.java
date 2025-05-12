package com.rangelcham.adventuremod.player;

import com.rangelcham.adventuremod.player.stats.PlayerStats;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class ModPlayer {
    public int level;
    public int statPoints;
    public int usedStatPoints;
    public int specialStatPoints;
    public int usedSpecialStatPoints;
    public int abilityPoints;
    public int usedAbilityPoints;
    public int experiencePoints;
    public PlayerStats stats;

    // Usar un array de requisitos de experiencia constante
    private static final int[] EXPERIENCE_REQUIREMENT = {
            100, 120, 140, 160, 180, 200, 250, 300, 350, 400, 450, 500, 550,
            600, 650, 700, 750, 800, 900, 1000, 1000, 1000, 1000, 1000, 1000
    };

    public ModPlayer() {
        stats = new PlayerStats();
    }

    public void giveExperience(int experience) {
        experiencePoints += experience;  // Sumar la experiencia directamente

        while (level < EXPERIENCE_REQUIREMENT.length && experiencePoints >= EXPERIENCE_REQUIREMENT[level - 1]) {
            experiencePoints -= EXPERIENCE_REQUIREMENT[level - 1];  // Restar la experiencia utilizada para subir de nivel
            levelUp();  // Subir de nivel
        }
    }

    public void levelUp() {
        level++;
        // Añadir puntos de estadísticas según el nivel
        statPoints += (level > 20) ? 2 : 1;
        Minecraft.getInstance().gui.getChat().addMessage(Component.literal("Has subido al nivel " + level));
    }
}
