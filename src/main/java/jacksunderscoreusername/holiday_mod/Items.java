package jacksunderscoreusername.holiday_mod;

import jacksunderscoreusername.holiday_mod.candies.CandyCane;
import jacksunderscoreusername.holiday_mod.candies.SharpenedCandyCane;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Function;

public class Items {
    public static final ArrayList<RegistryObject<Item>> ALL_ITEMS = new ArrayList<>();

    public static final RegistryObject<Item> EXAMPLE_GUMMY_CANDY = registerSimpleCandy("example_gummy_candy", () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10 * 20, 1));
    public static final RegistryObject<Item> EXAMPLE_WRAPPED_CANDY = registerSimpleCandy("example_wrapped_candy", () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10 * 20, 1));
    public static final RegistryObject<Item> CANDY_CANE = register("candy_cane", CandyCane::new, CandyCane.getProperties());
    public static final RegistryObject<Item> SHARPENED_CANDY_CANE = register("sharpened_candy_cane", SharpenedCandyCane::new, SharpenedCandyCane.getProperties());

    public static RegistryObject<Item> registerSimpleCandy(String id, java.util.function.Supplier<MobEffectInstance> effectSupplier) {
        return register(id, Item::new, new Item.Properties().food((new FoodProperties.Builder()).nutrition(4).saturationMod(1.2F).effect(effectSupplier, 1.0F).alwaysEat().build()));
    }

    public static RegistryObject<Item> register(String id, Function<Item.Properties, Item> constructor, Item.Properties properties) {
        RegistryObject<Item> item = Main.ITEMS.register(id, () -> constructor.apply(properties));
        ALL_ITEMS.add(item);
        return item;
    }

    public static void init() {

    }
}
