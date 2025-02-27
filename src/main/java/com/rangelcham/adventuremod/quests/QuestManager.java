package com.rangelcham.adventuremod.quests;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

//
//Author: RangelCham73
//

public class QuestManager {
    private static final Map<String, Quest> ACTIVE_QUESTS = new HashMap<>();

    public static void startQuest(Player player, String questID) {
//        if (!ACTIVE_QUESTS.containsKey(questID)) {
//            ACTIVE_QUESTS.put(questID, QuestRegistry.getQuest(questID));
//        }
    }

    public static void completeQuest(Player player, String questID) {
        if (ACTIVE_QUESTS.containsKey(questID)) {
            Quest quest = ACTIVE_QUESTS.get(questID);
            quest.giveReward(player);
            ACTIVE_QUESTS.remove(questID);
        }
    }
}