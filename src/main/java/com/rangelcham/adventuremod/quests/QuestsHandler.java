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

    // Constantes de ID para claridad
    public static final int QUEST_ID_MAIN = 1;
    public static final int QUEST_ID_FLOWERS = 2;
    public static final int QUEST_ID_MONSTERS = 3;

    private static final Quest quest1 = new Quest(
            "Principal",
            "Mata al jefe",
            QuestType.PRINCIPAL,
            List.of("Habla con el", "Mata al jefe", "Recoge la recompensa"),
            0, 100, 0, 0,
            false, false
    );

    private static final Quest quest2 = new Quest(
            "Recogida de flores",
            "Recolecta 5 flores",
            QuestType.TASK,
            List.of("Recolecta 5 flores", "Recoge la recompensa"),
            0, 0, 10, 0,
            false, false
    );

    private static final Quest quest3 = new Quest(
            "Caza de monstruos",
            "Mata a 5 zombis",
            QuestType.HUNT,
            List.of("Mata a 5 zombis", "Recoge la recompensa"),
            0, 0, 0, 1,
            false, false
    );

    public static Map<Integer, Quest> quests = new HashMap<>();

    static {
        quests.put(QUEST_ID_MAIN, quest1);
        quests.put(QUEST_ID_FLOWERS, quest2);
        quests.put(QUEST_ID_MONSTERS, quest3);
    }

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
