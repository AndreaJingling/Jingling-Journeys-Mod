package holiday_mod.world.item;

import holiday_mod.registry.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static holiday_mod.world.food.ModFoods.CANDY_CANE;

public class CandyCane extends Item {
    public CandyCane(Properties pProperties) {
        super(pProperties);
    }

    public static Item.Properties getProperties() {
        return new Item.Properties().food(CANDY_CANE);
    }

    // If the player is not in creative it consumes the item and gives them the sharpened variant.
    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        ItemStack newStack = super.finishUsingItem(stack, level, livingEntity);
        return (livingEntity instanceof Player player && player.getAbilities().instabuild) ?
                newStack : ModItems.SHARPENED_CANDY_CANE.get().getDefaultInstance();
    }
}
