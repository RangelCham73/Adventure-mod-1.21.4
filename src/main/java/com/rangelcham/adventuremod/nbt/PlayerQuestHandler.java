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

    public static void saveQuests(Player player) {
        CompoundTag tag = player.getPersistentData();
        CompoundTag questsTag = new CompoundTag();
        int i = 1;
        for (Quest quest : QuestsHandler.quests.values()) {
            CompoundTag questTag = new CompoundTag();
            questTag.putBoolean("active", quest.isActive);
            questTag.putBoolean("completed", quest.isCompleted);
            questTag.putInt("currentStep", quest.currentStep);

            questsTag.put(String.valueOf(i), questTag);
            i++;
        }
        tag.put("quests", questsTag);
        Minecraft.getInstance().gui.getChat().addMessage(Component.literal(tag.getAsString())); // debug
    }

    public static void loadQuests(Player player) {
        CompoundTag questsTag = player.getPersistentData().getCompound("quests");

        for (String key : questsTag.getAllKeys()) {
            CompoundTag questTag = questsTag.getCompound(key);

            Quest quest = QuestsHandler.quests.get(Integer.parseInt(key));

            if (quest != null) {
                quest.isActive = questTag.getBoolean("active");
                quest.isCompleted = questTag.getBoolean("completed");
                quest.currentStep = questTag.getInt("currentStep");
                QuestsHandler.quests.put(Integer.parseInt(key), quest);
            }
        }
        Minecraft.getInstance().gui.getChat().addMessage(Component.literal(questsTag.getAsString())); // debug
    }
}
