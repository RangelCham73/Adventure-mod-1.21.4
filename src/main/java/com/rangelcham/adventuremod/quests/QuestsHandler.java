package com.rangelcham.adventuremod.quests;

import com.rangelcham.adventuremod.AdventureMod;
import com.rangelcham.adventuremod.nbt.PlayerQuestHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import java.util.*;

@EventBusSubscriber(modid = AdventureMod.MODID)
public class QuestsHandler {

    public static HashMap<Integer, Quest> quests = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        if (!(event.getEntity() instanceof LocalPlayer player)) return;

        if (QuestKeyBind.QUESTS_OPEN_SCREEN.isDown()) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.screen == null) {
                QuestScreen screen = new QuestScreen();
                screen.setMissions(quests.values());
                mc.setScreen(screen);
            }
        }
    }

    public static void doStep(CommandSourceStack command, int id, int step) {
        Quest quest = quests.get(id);
        if (quest != null) {
            if (quest.isActive && step > 0) {
                quest.currentStep = step;
                if (quest.currentStep == quest.steps.size()) {
                    completeQuest(quest, id);
                }
            } else {
                quest.isActive = true;
            }
            PlayerQuestHandler.saveQuests(command.getPlayer());
        }
    }

    private static void completeQuest(Quest quest, int id) {
        quest.isCompleted = true;
    }
}
