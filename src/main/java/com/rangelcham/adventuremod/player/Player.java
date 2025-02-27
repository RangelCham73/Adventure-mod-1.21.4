package com.rangelcham.adventuremod.player;

import com.rangelcham.adventuremod.abilities.IAbility;
import com.rangelcham.adventuremod.skills.Skill;
import com.rangelcham.adventuremod.utils.Tree;

import java.util.List;

//
//Author: RangelCham73
//

public class Player {
    private static final int MAX_LEVEL = 50;
    private static final int[] SKILL_POINTS = {1, 1, 2, 2, 3};
    private static final int[] STAT_POINTS = {1, 2, 3, 4, 5};

    public int experience;
    public int level;
    public int skillPoints;
    public int statPoints;
    public PlayerStats stats;
    public List<IAbility> abilities;
    public Tree<Skill> skillTree;

    public Player(int experience, int level, PlayerStats stats, List<IAbility> abilities, Tree<Skill> skillTree) {
        this.experience = experience;
        this.level = level;
        this.stats = stats;
        this.abilities = abilities;
        this.skillTree = skillTree;
    }

    // Method to get the required experience to reach the next level
    public int getLevelExperience() {
        return LevelSystem.getExperienceThreshold(this);
    }

    // Method to give experince to player, also level up in case player has the required experience
    public void giveExperience(int experience) {
        if (level < MAX_LEVEL) {
            var response = LevelSystem.giveExperience(experience, this);
            if (response.getA()) {
                this.levelUp();
            }
            this.experience = response.getB();
        }
    }

    // Method to level up the player and give the skill and stats points
    public void levelUp() {
        this.skillPoints += SKILL_POINTS[(level - 1) / 10];
        this.statPoints += STAT_POINTS[(level - 1) / 10];
        this.level++;
    }
}
