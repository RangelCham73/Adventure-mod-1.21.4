package com.rangelcham.adventuremod.quests;

import com.rangelcham.adventuremod.AdventureMod;
import com.rangelcham.adventuremod.nbt.PlayerQuestHandler;
import com.rangelcham.adventuremod.quests.data.ExcelReader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.apache.poi.ss.usermodel.Workbook;
import java.util.*;

@EventBusSubscriber(modid = AdventureMod.MODID)
public class QuestsHandler {

    private static ExcelReader excelReader = new ExcelReader();
    public static HashMap<Integer, Quest> quests = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        if (!(event.getEntity() instanceof LocalPlayer player)) return;

        if (QuestKeyBind.QUESTS_OPEN_SCREEN.isDown()) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.screen == null) {
                QuestScreen screen = new QuestScreen();
                screen.setMissions(excelReader.readExcel().values());
                mc.setScreen(screen);
            }
        }
    }

    public static void doStep(CommandSourceStack command, int id, int step) {
        Quest quest = excelReader.readExcel().get(id);
        quests.put(id, quest); // Guardar en memoria para luego hacer el guardado de datos en el fichero NBT
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
