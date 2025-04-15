package com.rangelcham.adventuremod.quests;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.Mth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QuestScreen extends Screen {
    private int scrollOffset = 0;
    private final List<Component> missionLines = new ArrayList<>();
    private final int lineHeight = 12;
    private final int paddingTop = 20;

    public QuestScreen() {
        super(Component.literal("Misiones"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.addRenderableWidget(Button.builder(Component.literal("Cerrar"), b -> this.onClose())
                .pos(centerX - 50, this.height - 30)
                .size(100, 20)
                .build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);

        int y = paddingTop - scrollOffset;
        for (Component line : missionLines) {
            if (y > -lineHeight && y < this.height) { // Optimiza: solo dibuja si se ve en pantalla
                guiGraphics.drawString(this.font, line, 20, y, 0xFFFFFF, false);
            }
            y += lineHeight;
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        int scrollStep = 10;

        scrollOffset -= verticalAmount * scrollStep;

        int totalHeight = missionLines.size() * lineHeight;
        int visibleHeight = this.height - paddingTop - 40;

        scrollOffset = Mth.clamp(scrollOffset, 0, Math.max(0, totalHeight - visibleHeight));
        return true;
    }


    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public void setMissions(Collection<Quest> quests) {
        missionLines.clear();

        for (Quest quest : quests) {
            if (!quest.isActive) continue;

            missionLines.add(Component.literal("----------------------"));
            missionLines.add(
                    Component.literal("Misión: ")
                            .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x00FFFF)))
                            .append(Component.literal(quest.title).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFFF00))))
            );

            missionLines.add(
                    Component.literal("Descripción: ")
                            .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0000FF)))
                            .append(Component.literal(quest.description).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFFFFF))))
            );

            missionLines.add(Component.literal("Progreso:").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFFFFF))));

            for (int i = 0; i < quest.steps.length; i++) {
                Component line = Component.literal("");
                if (i < quest.currentStep) {
                    line = ((net.minecraft.network.chat.MutableComponent) line).append(Component.literal("[X] ").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x00FF00))));
                    line = ((net.minecraft.network.chat.MutableComponent) line).append(Component.literal(quest.steps[i]).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x00FF00))));
                } else {
                    line = ((net.minecraft.network.chat.MutableComponent) line).append(Component.literal("[ ] ").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF0000))));
                    line = ((net.minecraft.network.chat.MutableComponent) line).append(Component.literal(quest.steps[i]).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF0000))));
                }
                missionLines.add(line);
            }

            if (quest.isCompleted) {
                missionLines.add(Component.literal("¡Misión completada!")
                        .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFD700))));
            }
            missionLines.add(Component.literal("----------------------"));
            missionLines.add(Component.literal(""));
        }
    }
}
