package holiday_mod.world.entity.vehicle;

import holiday_mod.world.entity.ModEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class GenericSmallSledEntity extends SledEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public GenericSmallSledEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public GenericSmallSledEntity(Level pLevel, double pX, double pY, double pZ) {
        this(ModEntityTypes.genericSmallSledEntityType.get(), pLevel);
        this.setPos(pX, pY, pZ);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag pCompound) {

    }

    @Override
    protected float getEyeHeight(@NotNull Pose pPose, EntityDimensions pDimensions) {
        return pDimensions.height;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}