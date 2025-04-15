package com.rangelcham.adventuremod.player.stats;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

import java.util.ArrayList;
import java.util.List;

public class StatsScreen extends Screen {
    private final PlayerStats stats;
    private final List<Component> statLines = new ArrayList<>();

    public StatsScreen(PlayerStats stats) {
        super(Component.literal("Atributos"));
        this.stats = stats;
        generateStatLines();
    }

    private void generateStatLines() {
        statLines.clear();

        statLines.add(Component.literal("==== Atributos ====").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFD700)))); // Dorado

        addStatLine("Fuerza", stats.strength, stats.isMaxStrength);
        addStatLine("Destreza", stats.dexterity, stats.isMaxDexterity);
        addStatLine("Constitución", stats.constitution, stats.isMaxConstitution);
        addStatLine("Inteligencia", stats.intelligence, stats.isMaxIntelligence);
        addStatLine("Sabiduría", stats.wisdom, stats.isMaxWisdom);

        statLines.add(Component.literal("===================").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFD700))));
    }

    private void addStatLine(String name, int value, boolean isMax) {
        String status = isMax ? " (¡Máximo!)" : "";
        TextColor color = isMax ? TextColor.fromRgb(0x00FF00) : TextColor.fromRgb(0xFFFFFF); // Verde si está al máximo

        statLines.add(
                Component.literal(name + ": " + value + status)
                        .setStyle(Style.EMPTY.withColor(color))
        );
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.addRenderableWidget(Button.builder(Component.literal("Cerrar"), b -> this.onClose())
                .pos(centerX - 50, centerY)
                .size(100, 20)
                .build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks); // Fondo
        super.render(guiGraphics, mouseX, mouseY, partialTicks);

        int y = 30;
        for (Component line : statLines) {
            guiGraphics.drawString(this.font, line, 20, y, 0xFFFFFF, false);
            y += 12;
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}