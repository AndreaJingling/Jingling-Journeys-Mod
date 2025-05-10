package holiday_mod.world.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties CANDY_CANE = (new FoodProperties.Builder())
            .nutrition(4)
            .saturationMod(1.2F)
            .effect(() ->
                            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10 * 20, 1),
                    1.0F)
            .alwaysEat()
            .build();
}
