package com.rangelcham.adventuremod.doublejump;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class DoubleJumpHandler {
    private static boolean isInAir = false;
    private static boolean canDoubleJump = false;
    private static boolean jumpReleased = true;
    private static boolean preventFallDamage = false;
    public static boolean unlockedDoubleJump = false;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        if (unlockedDoubleJump) {
            if (!(event.getEntity() instanceof LocalPlayer player)) return;

            if (!player.onGround() && !isInAir) {
                isInAir = true;
                canDoubleJump = true;
                jumpReleased = false;
            }

            if (player.onGround()) {
                isInAir = false;
                canDoubleJump = false;
                jumpReleased = true;
                if (preventFallDamage) {
                    player.fallDistance = 0.0F; // Reinicia la distancia de caída al aterrizar
                    preventFallDamage = false;
                }
            }

            if (!Minecraft.getInstance().options.keyJump.isDown()) {
                jumpReleased = true;
            }

            if (canDoubleJump && jumpReleased && Minecraft.getInstance().options.keyJump.isDown()) {
                performDoubleJump(player);
                player.fallDistance = 0.0F;
                preventFallDamage = true;
                canDoubleJump = false;
            }
        }
    }

    public static void performDoubleJump(LocalPlayer player) {
        Vec3 motion = player.getDeltaMovement();
        player.setDeltaMovement(motion.x, 0.6, motion.z);

        player.playSound(SoundEvents.AMETHYST_BLOCK_BREAK, 1.0F, 1.0F);

        for (int i = 0; i < 25; i++) {
            player.level().addParticle(ParticleTypes.END_ROD,
                    player.getX(), player.getY(), player.getZ(),
                    (Math.random() - 0.5) * 0.2, 0.2, (Math.random() - 0.5) * 0.2);
            player.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.AMETHYST_CLUSTER.defaultBlockState()),
                    player.getX(), player.getY(), player.getZ(),
                    (Math.random() - 0.5) * 0.2, 0.2, (Math.random() - 0.5) * 0.2);
        }
    }

}
