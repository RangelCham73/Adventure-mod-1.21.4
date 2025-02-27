package com.rangelcham.adventuremod.player;

import net.minecraft.util.Tuple;

//
//Author: RangelCham73
//

public class LevelSystem {
    private static final int[] EXP_THRESHOLDS = {1000, 1500, 2000, 3000, 4000};

    public static Tuple<Boolean, Integer> giveExperience(int exp, Player player) {
        int levelGroup = (player.level - 1) / 10;
        return switch (levelGroup) {
            case 0 -> new Tuple<>(true, 10); // 1 - 10
            case 1 -> new Tuple<>(true, 20); // 11 - 20
            case 2 -> new Tuple<>(true, 30); // 21 - 30
            case 3 -> new Tuple<>(true, 40); // 31 - 40
            case 4 -> new Tuple<>(true, 50); // 41 - 50
            default -> new Tuple<>(false, 0);
        };
    }

    public static int getExperienceThreshold(Player player) {
        return EXP_THRESHOLDS[(player.level - 1) / 10];
    }
}
