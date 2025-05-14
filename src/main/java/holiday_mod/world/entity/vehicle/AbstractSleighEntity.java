package holiday_mod.world.entity.vehicle;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;

public abstract class AbstractSleighEntity extends Entity implements GeoEntity {
    public AbstractSleighEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }
}
