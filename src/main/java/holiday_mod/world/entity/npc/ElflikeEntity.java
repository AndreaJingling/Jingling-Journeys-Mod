package holiday_mod.world.entity.npc;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;

public abstract class ElflikeEntity extends AgeableMob implements InventoryCarrier, Npc, Merchant, GeoEntity {
    protected ElflikeEntity(EntityType<? extends ElflikeEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public @NotNull SimpleContainer getInventory() {
        return null; // TODO
    }

    @Override
    public void setTradingPlayer(@Nullable Player pTradingPlayer) {

    }

    @Override
    public @Nullable Player getTradingPlayer() {
        return null;
    }

    @Override
    public @NotNull MerchantOffers getOffers() {
        return null;
    }

    @Override
    public void overrideOffers(@NotNull MerchantOffers pOffers) {

    }

    @Override
    public void notifyTrade(@NotNull MerchantOffer pOffer) {

    }

    @Override
    public void notifyTradeUpdated(@NotNull ItemStack pStack) {

    }

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int pXp) {

    }

    @Override
    public boolean showProgressBar() {
        return false;
    }
}
