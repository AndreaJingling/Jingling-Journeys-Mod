package jacksunderscoreusername.holiday_mod.candies;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class ExampleCandy extends Item {
    public ExampleCandy(Properties pProperties) {
        super(pProperties);
    }

    public static Properties getProperties() {
        return new Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(1).saturationMod(2f).build());
    }
}
