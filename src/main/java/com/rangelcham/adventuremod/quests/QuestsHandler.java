package com.rangelcham.adventuremod.quests;

import com.rangelcham.adventuremod.AdventureMod;
import com.rangelcham.adventuremod.nbt.PlayerQuestHandler;
import com.rangelcham.adventuremod.player.abilities.dash.DashKeybind;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.*;

@EventBusSubscriber(modid = AdventureMod.MODID)
public class QuestsHandler {

    private static Quest quest1 = new Quest(
            "Principal",
            "Mata al jefe",
            QuestType.PRINCIPAL,
            new String[]{"Habla con el mago", "Mata al jefe", "Recoge la recompensa"},
            0,
            Optional.of(100),
            Optional.empty(),
            Optional.empty(),
            false,
            false
    );
    private static Quest quest2 = new Quest(
            "Recogida de flores",
            "Recolecta 5 flores",
            QuestType.TASK,
            new String[]{"Recolecta 5 flores", "Recoge la recompensa"},
            0,
            Optional.empty(),
            Optional.of(10),
            Optional.empty(),
            false,
            false
    );
    private static Quest quest3 = new Quest(
            "Caza de monstruos",
            "Mata a 5 zombis",
            QuestType.HUNT,
            new String[]{"Mata a 5 zombis", "Recoge la recompensa"},
            0,
            Optional.empty(),
            Optional.empty(),
            Optional.of(1),
            false,
            false
    );

    public static Map<Integer, Quest> quests = new HashMap<Integer, Quest>();
    static {
        quests.put(1, quest1);  // ID 1 para la misión 1
        quests.put(2, quest2);  // ID 2 para la misión 2
        quests.put(3, quest3);  // ID 3 para la misión 3
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        if (!(event.getEntity() instanceof LocalPlayer player)) return;

        if (QuestKeyBind.QUESTS_OPEN_SCREEN.isDown()) {
            Minecraft mc = Minecraft.getInstance();

            if (mc.screen == null) {
                QuestScreen screen = new QuestScreen();
                screen.setMissions(QuestsHandler.quests.values());
                mc.setScreen(screen);
            }
        }
    }

    public static void doStep(CommandSourceStack command, int id, int step) {
        Quest quest = quests.get(id);
        if (quest != null) {
            if (quest.isActive && step > 0) {
                quest.currentStep = step;
                if (quest.currentStep == quest.steps.length) {
                    completeQuest(quest, id);
                }
            } else {
                quest.isActive = true;
            }
            PlayerQuestHandler.saveQuests(command.getPlayer());
        }
    }

    public static void completeQuest(Quest quest, int id) {
        quest.isCompleted = true;
    }
}
