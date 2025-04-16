package com.rangelcham.adventuremod.nbt;

import com.rangelcham.adventuremod.player.abilities.dash.DashHandler;
import com.rangelcham.adventuremod.player.abilities.doublejump.DoubleJumpHandler;
import com.rangelcham.adventuremod.quests.Quest;
import com.rangelcham.adventuremod.quests.QuestType;
import com.rangelcham.adventuremod.quests.QuestsHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerQuestHandler {
    private static final String QUEST_KEY = "quests";
    private static final String ACTIVE_KEY = "active";
    private static final String COMPLETED_KEY = "completed";
    private static final String STEP_KEY = "currentStep";

    public static void saveQuests(Player player) {
        CompoundTag tag = player.getPersistentData();
        CompoundTag questsTag = new CompoundTag();
        int i = 1;
        for (Quest quest : QuestsHandler.quests.values()) {
            CompoundTag questTag = new CompoundTag();
            questTag.putBoolean(ACTIVE_KEY, quest.isActive);
            questTag.putBoolean(COMPLETED_KEY, quest.isCompleted);
            questTag.putInt(STEP_KEY, quest.currentStep);

            questsTag.put(String.valueOf(i), questTag);
            i++;
        }
        tag.put(QUEST_KEY, questsTag);
    }

    public static void loadQuests(Player player) {
        CompoundTag questsTag = player.getPersistentData().getCompound(QUEST_KEY);

        for (String key : questsTag.getAllKeys()) {
            CompoundTag questTag = questsTag.getCompound(key);

            Quest quest = QuestsHandler.quests.get(Integer.parseInt(key));

            if (quest != null) {
                quest.isActive = questTag.getBoolean(ACTIVE_KEY);
                quest.isCompleted = questTag.getBoolean(COMPLETED_KEY);
                quest.currentStep = questTag.getInt(STEP_KEY);
                QuestsHandler.quests.put(Integer.parseInt(key), quest);
            }
        }
    }
}
