package com.rangelcham.adventuremod.player;

import com.rangelcham.adventuremod.player.stats.PlayerStats;

public class ModPlayer {
    public int level;
    public int statPoints;
    public int usedStatPoints;
    public int abilityPoints;
    public int usedAbilityPoints;
    public int experiencePoints;
    public PlayerStats stats;

    private final int[] EXPERIENCE_REQUIREMENT = {100, 120, 140, 160, 180, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 900, 1000, 1000, 1000, 1000, 1000, 1000};

    public void giveExperience(int experience) {
        int experienceAux = experiencePoints + experience;
        int experienceRequired = EXPERIENCE_REQUIREMENT[level-1];

        if (experienceAux >= experienceRequired) {
            levelUp();
            experiencePoints += experience - experienceAux;
        } else {
            experiencePoints = experienceAux;
        }
    }

    public void levelUp() {
        level++;
        if (level > 10) {
            statPoints += 2;
        } else {
            statPoints += 1;
        }
    }
}
