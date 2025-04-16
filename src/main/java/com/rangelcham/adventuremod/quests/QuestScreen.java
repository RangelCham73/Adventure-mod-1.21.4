package com.rangelcham.adventuremod.quests;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.*;

import net.minecraft.util.Mth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QuestScreen extends Screen {
    private int scrollOffset = 0;
    private final List<Component> missionLines = new ArrayList<>();

    private static final int LINE_HEIGHT = 12;
    private static final int PADDING_TOP = 20;

    // Colores constantes
    private static final int COLOR_TITLE = 0xFFFF00;
    private static final int COLOR_TITLE_LABEL = 0x00FFFF;
    private static final int COLOR_DESC = 0xFFFFFF;
    private static final int COLOR_DESC_LABEL = 0x0000FF;
    private static final int COLOR_STEP_DONE = 0x00FF00;
    private static final int COLOR_STEP_TODO = 0xFF0000;
    private static final int COLOR_COMPLETE = 0xFFD700;

    public QuestScreen() {
        super(Component.literal("Misiones"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;

        this.addRenderableWidget(Button.builder(Component.literal("Cerrar"), b -> this.onClose())
                .pos(centerX - 50, this.height - 30)
                .size(100, 20)
                .build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);

        int y = PADDING_TOP - scrollOffset;
        for (Component line : missionLines) {
            if (y + LINE_HEIGHT > 0 && y < this.height) {
                guiGraphics.drawString(this.font, line, 20, y, 0xFFFFFF, false);
            }
            y += LINE_HEIGHT;
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        scrollOffset -= verticalAmount * 10;

        int totalHeight = missionLines.size() * LINE_HEIGHT;
        int visibleHeight = this.height - PADDING_TOP - 40;

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
                    Component.literal("Mision: ")
                            .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(COLOR_TITLE_LABEL)))
                            .append(Component.literal(quest.title).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(COLOR_TITLE))))
            );

            missionLines.add(
                    Component.literal("Descripción: ")
                            .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(COLOR_DESC_LABEL)))
                            .append(Component.literal(quest.description).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(COLOR_DESC))))
            );

            missionLines.add(Component.literal("Progreso:")
                    .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(COLOR_DESC))));

            for (int i = 0; i < quest.steps.size(); i++) {
                boolean isDone = i < quest.currentStep;
                String prefix = isDone ? "[X] " : "[ ] ";
                int color = isDone ? COLOR_STEP_DONE : COLOR_STEP_TODO;

                missionLines.add(Component.literal(prefix + quest.steps.get(i))
                        .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(color))));
            }

            if (quest.isCompleted) {
                missionLines.add(Component.literal("¡Misión completada!")
                        .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(COLOR_COMPLETE))));
            }

            missionLines.add(Component.literal("----------------------"));
            missionLines.add(Component.literal(""));
        }
    }
}
