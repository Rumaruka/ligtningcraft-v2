package com.rumaruka.lc.common.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;

import java.util.List;

import static com.rumaruka.lc.init.LCEntity.LIGHTNING_BOLT_LC;

public class LightningBoltLC extends LightningBolt {


    /**
     * Declares which state the lightning bolt is in. Whether it's in the air, hit the ground, etc.
     */
    private int life;
    /**
     * A random long that is used to change the vertex of the lightning rendered in RenderLightningBolt
     */
    public long boltVertex;
    /**
     * Determines the time before the EntityLightningBolt is destroyed. It is a random integer decremented over time.
     */
    private int flashes;

    private boolean doSetFire;

    public LightningBoltLC(EntityType<? extends LightningBolt> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.life = 2;
        this.flashes = this.random.nextInt(3) + 1;

    }


    /**
     * A lightning bolt that isn't annoying (quieter!)
     */


    @Override
    public void tick() {
        super.tick();
        if (this.life == 2) {
            if (this.level().isClientSide()) {
                this.level()
                        .playLocalSound(
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                SoundEvents.LIGHTNING_BOLT_THUNDER,
                                SoundSource.WEATHER,
                                10000.0F,
                                0.8F + this.random.nextFloat() * 0.2F,
                                false
                        );
                this.level()
                        .playLocalSound(
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                SoundEvents.LIGHTNING_BOLT_IMPACT,
                                SoundSource.WEATHER,
                                2.0F,
                                0.5F + this.random.nextFloat() * 0.2F,
                                false
                        );
            } else {
                Difficulty difficulty = this.level().getDifficulty();
                if (difficulty == Difficulty.NORMAL || difficulty == Difficulty.HARD) {
                    int i = Mth.floor(this.getX());
                    int j = Mth.floor(this.getY());
                    int k = Mth.floor(this.getZ());
                    // this.spawnFire(4);
                    level().setBlockAndUpdate(new BlockPos(i, j, k), Blocks.FIRE.defaultBlockState());
                }

                // this.powerLightningRod();

                this.gameEvent(GameEvent.LIGHTNING_STRIKE);
            }
        }

        --this.life;
        if (this.life < 0) {
            if (this.flashes == 0) {
                if (this.level() instanceof ServerLevel) {
                    List<Entity> list = this.level()
                            .getEntities(
                                    this,
                                    new AABB(
                                            this.getX() - 15.0, this.getY() - 15.0, this.getZ() - 15.0, this.getX() + 15.0, this.getY() + 6.0 + 15.0, this.getZ() + 15.0
                                    ),
                                    p_147140_ -> p_147140_.isAlive() && !this.getHitEntities().toList().contains(p_147140_)
                            );

                    for (ServerPlayer serverplayer : ((ServerLevel) this.level()).getPlayers(p_147157_ -> p_147157_.distanceTo(this) < 256.0F)) {
                        CriteriaTriggers.LIGHTNING_STRIKE.trigger(serverplayer, this, list);
                    }
                }

                this.discard();
            } else if (this.life < -this.random.nextInt(10)) {
                --this.flashes;
                this.life = 1;
                this.seed = this.random.nextLong();

            }
        }

        if (this.life >= 0) {
            if (!(this.level() instanceof ServerLevel)) {
                this.level().setSkyFlashTime(2);
            } else {
                List<Entity> list1 = this.level()
                        .getEntities(
                                this,
                                new AABB(this.getX() - 3.0, this.getY() - 3.0, this.getZ() - 3.0, this.getX() + 3.0, this.getY() + 6.0 + 3.0, this.getZ() + 3.0),
                                Entity::isAlive
                        );

                for (Entity entity : list1) {
                    if (!net.neoforged.neoforge.event.EventHooks.onEntityStruckByLightning(entity, this))
                        entity.thunderHit((ServerLevel) this.level(), this);
                }

                this.getHitEntities().toList().addAll(list1);

            }
        }
    }
}

