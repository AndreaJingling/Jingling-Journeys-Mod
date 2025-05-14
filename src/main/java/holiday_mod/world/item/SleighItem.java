package holiday_mod.world.item;

import holiday_mod.world.entity.vehicle.AbstractSleighEntity;
import holiday_mod.world.entity.vehicle.GenericSmallSledEntity;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

public class SleighItem extends Item {
    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);

    public SleighItem(Properties pProperties) {
        super(pProperties);
    }

    public static Item.Properties getProperties() {
        return new Item.Properties();
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use
            (@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        HitResult hitresult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.ANY);
        if (hitresult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            Vec3 vec3 = pPlayer.getViewVector(1.0F);
            List<Entity> list = pLevel.getEntities(pPlayer,
                    pPlayer.getBoundingBox().expandTowards(vec3.scale(5.0D)).inflate(1.0D),
                    ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vec3 vec31 = pPlayer.getEyePosition();

                for(Entity entity : list) {
                    AABB aabb = entity.getBoundingBox().inflate((double)entity.getPickRadius());
                    if (aabb.contains(vec31)) {
                        return InteractionResultHolder.pass(itemstack);
                    }
                }
            }

            if (hitresult.getType() == HitResult.Type.BLOCK) {
                AbstractSleighEntity sleigh = this.getSleigh(pLevel, hitresult);
                sleigh.setYRot(pPlayer.getYRot());
                if (!pLevel.noCollision(sleigh, sleigh.getBoundingBox())) {
                    return InteractionResultHolder.fail(itemstack);
                } else {
                    if (!pLevel.isClientSide) {
                        pLevel.addFreshEntity(sleigh);
                        pLevel.gameEvent(pPlayer, GameEvent.ENTITY_PLACE, hitresult.getLocation());
                        if (!pPlayer.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                    }

                    pPlayer.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
                }
            } else {
                return InteractionResultHolder.pass(itemstack);
            }
        }
    }

    private AbstractSleighEntity getSleigh(Level pLevel, HitResult pHitResult) {
        return new GenericSmallSledEntity(pLevel,
                pHitResult.getLocation().x, pHitResult.getLocation().y, pHitResult.getLocation().z);
    }
}
