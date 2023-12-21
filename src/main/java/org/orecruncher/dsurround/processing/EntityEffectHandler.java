package org.orecruncher.dsurround.processing;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import org.orecruncher.dsurround.config.Configuration;
import org.orecruncher.dsurround.config.libraries.IEntityEffectLibrary;
import org.orecruncher.dsurround.effects.entity.EntityEffectInfo;
import org.orecruncher.dsurround.lib.logging.IModLog;

import java.util.stream.Collectors;

@Environment(EnvType.CLIENT)
public class EntityEffectHandler  extends ClientHandler {

    private final IEntityEffectLibrary entityEffectLibrary;

    public EntityEffectHandler(Configuration config, IEntityEffectLibrary entityEffectLibrary, IModLog logger) {
        super("EntityEffect Handler", config, logger);

        this.entityEffectLibrary = entityEffectLibrary;
    }

    @Override
    public void process(final PlayerEntity player) {

        var range = config.entityEffects.entityEffectRange;
        var world = player.getEntityWorld();

        // Get living entities in the world.  Since the API does some fancy tracking of entities we create a box
        // larger than the normal range size.
        var worldBox = Box.from(player.getEyePos()).expand(range * 2);
        var loadedEntities = world.getEntitiesByClass(LivingEntity.class, worldBox, entity -> true);

        for (var entity : loadedEntities) {
            var hasInfo = this.entityEffectLibrary.doesEntityEffectInfoExist(entity);
            var inRange = entity.isInRange(player, range);
            EntityEffectInfo info = null;

            if (!hasInfo && entity.isAlive()) {
                // If it does not have info, but is alive, and is not a spectator get info for it.
                if (inRange && !entity.isSpectator()) {
                    this.logger.debug("Obtaining effect info for %s (id %d)", entity.getClass().getSimpleName(), entity.getId());
                    info = this.entityEffectLibrary.getEntityEffectInfo(entity);
                    EntityEffectInfo finalInfo = info;
                    this.logger.debug(() -> {
                        var txt = finalInfo.getEffects().stream()
                                .map(e -> e.getClass().getSimpleName())
                                .collect(Collectors.joining(","));
                        return String.format("Effects attached: %s", txt);
                    });
                }
            } else if (hasInfo) {
                // If it does have info just get whatever is currently cached
                info = this.entityEffectLibrary.getEntityEffectInfo(entity);
            }

            if (info != null) {
                if (inRange && info.isAlive() && !entity.isSpectator()) {
                    info.tick();
                } else {
                    this.logger.debug("Clearing effect info for %s (id %d)", entity.getClass().getSimpleName(), entity.getId());
                    info.deactivate();
                    this.entityEffectLibrary.clearEntityEffectInfo(entity);
                }
            }
        }
    }
}
