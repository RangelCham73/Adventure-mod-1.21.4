package com.rangelcham.adventuremod.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.rangelcham.adventuremod.screens.TabbedScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.lwjgl.glfw.GLFW;

//
//Author: RangelCham73
//

public class KeybindHandler {
    public static final KeyMapping SKILL_TREE = new KeyMapping("key.adventuremod.skill_tree", GLFW.GLFW_KEY_K, "category.adventuremod");
    public static final KeyMapping QUEST_LOG = new KeyMapping("key.adventuremod.quest_log", GLFW.GLFW_KEY_J, "category.adventuremod");
    public static final KeyMapping PLAYER_STATS = new KeyMapping("key.adventuremod.player_stats", GLFW.GLFW_KEY_P, "category.adventuremod");


    public static void register() {
        IEventBus eventBus = NeoForge.EVENT_BUS;
        eventBus.addListener(EventPriority.HIGH, KeybindHandler::handleKeyInputEvent);
        eventBus.addListener(EventPriority.HIGH, KeybindHandler::handleGuiMouseKeyPress);
        eventBus.addListener(EventPriority.HIGH, KeybindHandler::handleGuiKeyPress);
    }

    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(SKILL_TREE);
        event.register(QUEST_LOG);
        event.register(PLAYER_STATS);
    }

    public static void handleGuiMouseKeyPress(ScreenEvent.MouseButtonPressed.Pre event) {

    }

    public static void handleGuiKeyPress(ScreenEvent.KeyPressed.Pre event) {
        InputConstants.Key key = InputConstants.getKey(event.getKeyCode(), event.getScanCode());
        if (QUEST_LOG.isActiveAndMatches(key) && sendQuestLogOpenOrCloseMessage()) {
            event.setCanceled(true);
        } else if (SKILL_TREE.isActiveAndMatches(key) && sendSkillTreeOpenOrCloseMessage()) {
            event.setCanceled(true);
        } else if (PLAYER_STATS.isActiveAndMatches(key) && sendPlayerStatsOpenOrCloseMessage()) {
            event.setCanceled(true);
        }
    }


    public static void handleKeyInputEvent(ClientTickEvent.Post event) {
        if (SKILL_TREE.consumeClick()) {
            sendSkillTreeOpenOrCloseMessage();
        } else if (QUEST_LOG.consumeClick()) {
            sendQuestLogOpenOrCloseMessage();
        } else if (PLAYER_STATS.consumeClick()) {
            sendPlayerStatsOpenOrCloseMessage();
        }
    }

    @SuppressWarnings({"java:S2440"})
    private static boolean sendSkillTreeOpenOrCloseMessage() {
        Minecraft mc = Minecraft.getInstance();
        Screen currentScreen = mc.screen;

        if (currentScreen instanceof TabbedScreen) {
            mc.setScreen(null); // Cerrar la pantalla si ya está abierta
        } else {
            mc.setScreen(new TabbedScreen(1)); // Abrir la pantalla si está cerrada
            return false;
        }
        return true;
    }

    @SuppressWarnings({"java:S2440"})
    private static boolean sendQuestLogOpenOrCloseMessage() {
        Minecraft mc = Minecraft.getInstance();
        Screen currentScreen = mc.screen;

        if (currentScreen instanceof TabbedScreen) {
            mc.setScreen(null); // Cerrar la pantalla si ya está abierta
        } else {
            mc.setScreen(new TabbedScreen(2)); // Abrir la pantalla si está cerrada
            return false;
        }
        return true;
    }

    @SuppressWarnings({"java:S2440"})
    private static boolean sendPlayerStatsOpenOrCloseMessage() {
        Minecraft mc = Minecraft.getInstance();
        Screen currentScreen = mc.screen;

        if (currentScreen instanceof TabbedScreen) {
            mc.setScreen(null); // Cerrar la pantalla si ya está abierta
        } else {
            mc.setScreen(new TabbedScreen(0)); // Abrir la pantalla si está cerrada
            return false;
        }
        return true;
    }
}