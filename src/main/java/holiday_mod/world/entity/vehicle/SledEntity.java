package holiday_mod.world.entity.vehicle;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public abstract class SledEntity extends AbstractSleighEntity {
    public SledEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
}
