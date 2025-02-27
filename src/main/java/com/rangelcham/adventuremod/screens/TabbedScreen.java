package com.rangelcham.adventuremod.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import com.rangelcham.adventuremod.AdventureMod;

//
//Author: RangelCham73
//

public class TabbedScreen extends Screen {
    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(AdventureMod.MODID, "textures/screens/background.png");

    private int activeTab;

    public TabbedScreen(int tabIndex) {
        super(Component.literal("Menú Principal"));
        this.activeTab = tabIndex; // Establecemos la pestaña inicial
    }

    public void setActiveTab(int tabIndex) {
        this.activeTab = tabIndex;
    }

    public int getActiveTab() {
        return this.activeTab;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2 - 50; // Ajustar la posición

        // Botones para cambiar de pestaña
        this.addRenderableWidget(
                Button.builder(Component.literal("Jugador"), (button) -> {
                    setActiveTab(0);
                }).pos(centerX - 128, centerY - 60).size(60, 20).build()
        );
        this.addRenderableWidget(
                Button.builder(Component.literal("Habilidades"), (button) -> {
                    setActiveTab(1);
                }).pos(centerX - 67, centerY - 60).size(60, 20).build()
        );
        this.addRenderableWidget(
                Button.builder(Component.literal("Misiones"), (button) -> {
                    setActiveTab(2);
                }).pos(centerX - 6, centerY - 60).size(60, 20).build()
        );
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics, mouseX, mouseY, partialTick);

        RenderSystem.enableBlend();
        graphics.blit(RenderType::guiTextured, BACKGROUND_TEXTURE, this.width / 2 - 128, this.height / 2 - 90, 0, 0, 256, 180, 256, 180);
        RenderSystem.disableBlend();

        super.render(graphics, mouseX, mouseY, partialTick);

        // Renderizar contenido de la pestaña actual
        switch (activeTab) {
            case 0 -> renderPlayerTab(graphics);
            case 1 -> renderSkillTreeTab(graphics);
            case 2 -> renderQuestLogTab(graphics);
        }
    }

    private void renderPlayerTab(GuiGraphics graphics) {
        graphics.drawString(this.font, "Estadísticas del Jugador", this.width / 2 - 50, this.height / 2 - 40, 0xFFFFFF);
    }

    private void renderSkillTreeTab(GuiGraphics graphics) {
        graphics.drawString(this.font, "Árbol de Habilidades", this.width / 2 - 50, this.height / 2 - 40, 0xFFFFFF);
    }

    private void renderQuestLogTab(GuiGraphics graphics) {
        graphics.drawString(this.font, "Registro de Misiones", this.width / 2 - 50, this.height / 2 - 40, 0xFFFFFF);
    }
}
