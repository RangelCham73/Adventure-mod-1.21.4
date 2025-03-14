package com.rangelcham.adventuremod.nbt;


import com.rangelcham.adventuremod.dash.DashHandler;
import com.rangelcham.adventuremod.doublejump.DoubleJumpHandler;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class PlayerAbilityHandler {

    private static final String DOUBLE_JUMP_KEY = "UnlockedDoubleJump";
    private static final String DASH_KEY = "UnlockedDash";

    // Función para verificar si el doble salto está desbloqueado
    public static boolean isDoubleJumpUnlocked(Player player) {
        CompoundTag tag = player.getPersistentData();
        return tag.getBoolean(DOUBLE_JUMP_KEY);
    }

    // Función para verificar si el dash está desbloqueado
    public static boolean isDashUnlocked(Player player) {
        CompoundTag tag = player.getPersistentData();
        return tag.getBoolean(DASH_KEY);
    }

    // Función para desbloquear el doble salto
    public static void unlockDoubleJump(Player player) {
        CompoundTag tag = player.getPersistentData();
        tag.putBoolean(DOUBLE_JUMP_KEY, true);
    }

    // Función para desbloquear el dash
    public static void unlockDash(Player player) {
        CompoundTag tag = player.getPersistentData();
        tag.putBoolean(DASH_KEY, true);
    }

    // Función para guardar el estado de las habilidades
    public static void saveAbilities(ServerPlayer player) {
        CompoundTag tag = player.getPersistentData();
        CompoundTag abilityTag = new CompoundTag();
        abilityTag.putBoolean("hasDoubleJump", DoubleJumpHandler.unlockedDoubleJump);
        abilityTag.putBoolean("hasDash", DashHandler.unlockedDash);
        tag.put("abilities", abilityTag);
        player.getPersistentData().put("abilities", abilityTag);
    }

    // Función para cargar el estado de las habilidades
    public static void loadAbilities(ServerPlayer player) {
        CompoundTag tag = player.getPersistentData();
        if (tag.contains(DOUBLE_JUMP_KEY)) {
            boolean doubleJump = tag.getBoolean(DOUBLE_JUMP_KEY);
            if (doubleJump) {
                DoubleJumpHandler.unlockedDoubleJump = true;
            }
        }
        if (tag.contains(DASH_KEY)) {
            boolean dash = tag.getBoolean(DASH_KEY);
            if (dash) {
                DashHandler.unlockedDash = true;
            }
        }
    }
}