package com.rangelcham.adventuremod.dash;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class DashHandler {
    private static final int DASH_COOLDOWN_TICKS = 40; // 2 segundos (40 ticks)
    private static int dashCooldown = 0;
    public static boolean unlockedDash = false;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        if (unlockedDash) {
            if (!(event.getEntity() instanceof LocalPlayer player)) return;
            if (dashCooldown > 0) dashCooldown--;

            if (DashKeybind.DASH_ACTION.isDown() && dashCooldown == 0) {
                performDash(player);
                dashCooldown = DASH_COOLDOWN_TICKS;
            }
        }
    }

    private static void performDash(LocalPlayer player) {
        Vec3 lookVec = player.getLookAngle().normalize();
        lookVec = new Vec3(lookVec.x, 0, lookVec.z).normalize();
        Vec3 dashVelocity = lookVec.scale(1.2);
        player.setDeltaMovement(dashVelocity);

        player.playSound(SoundEvents.AMETHYST_BLOCK_BREAK, 1.0F, 1.2F);
        player.playSound(SoundEvents.BREEZE_CHARGE, 1.0F, 1.0F);

        for (int i = 0; i < 25; i++) {
            double offsetX = (Math.random() - 0.5);
            double offsetY = (Math.random() - 0.3);
            double offsetZ = (Math.random() - 0.5);

            player.level().addParticle(ParticleTypes.END_ROD,
                    player.getX() + offsetX * 2, player.getY() + 0.5 + offsetY * 2, player.getZ() + offsetZ * 2,
                    lookVec.x * 0.2, lookVec.y * 0.2, lookVec.z * 0.2); // Dirección horizontal del Dash

            player.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.AMETHYST_CLUSTER.defaultBlockState()),
                    player.getX() + offsetX * 3, player.getY() + 0.5 + offsetY * 3, player.getZ() + offsetZ * 3,
                    lookVec.x * 5.0, lookVec.y * 5.0, lookVec.z * 5.0); // Partículas acompañan el Dash
        }
    }


}
