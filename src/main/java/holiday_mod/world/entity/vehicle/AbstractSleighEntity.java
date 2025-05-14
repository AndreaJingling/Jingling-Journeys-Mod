package holiday_mod.world.entity.vehicle;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;

public abstract class AbstractSleighEntity extends Entity implements GeoEntity {
    private boolean inputLeft;
    private boolean inputRight;
    private boolean inputUp;
    private boolean inputDown;

    public AbstractSleighEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public void setInput(boolean pInputLeft, boolean pInputRight, boolean pInputUp, boolean pInputDown) {
        this.inputLeft = pInputLeft;
        this.inputRight = pInputRight;
        this.inputUp = pInputUp;
        this.inputDown = pInputDown;
    }
}
