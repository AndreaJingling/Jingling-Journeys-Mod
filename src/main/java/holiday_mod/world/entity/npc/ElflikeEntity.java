package holiday_mod.world.entity.npc;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;

public abstract class ElflikeEntity extends AgeableMob implements InventoryCarrier, Npc, Merchant, GeoEntity {
    protected ElflikeEntity(EntityType<? extends ElflikeEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
}
