package holiday_mod.registry;

import holiday_mod.Main;
import holiday_mod.world.item.CandyCane;
import holiday_mod.world.item.SharpenedCandyCane;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModItems {
    // This is all the "candy" items that will be added to loot pools and the wandering trader's trade pool.
    public static final ArrayList<RegistryObject<Item>> ALL_CANDIES = new ArrayList<>();
    // Where this is just ALL the items, and is used to add everything to the creative tab.
    public static final ArrayList<RegistryObject<Item>> ALL_ITEMS = new ArrayList<>();

    public static final RegistryObject<Item> EXAMPLE_GUMMY_CANDY =
            registerSimpleCandy("example_gummy_candy",
                    () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10 * 20, 1));
    public static final RegistryObject<Item> EXAMPLE_WRAPPED_CANDY =
            registerSimpleCandy("example_wrapped_candy",
                    () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10 * 20, 1));
    public static final RegistryObject<Item> CANDY_CANE =
            register("candy_cane", CandyCane::new, CandyCane.getProperties(), true);
    public static final RegistryObject<Item> SHARPENED_CANDY_CANE =
            register("sharpened_candy_cane", SharpenedCandyCane::new, SharpenedCandyCane.getProperties(),
                    true);

    // This can handle any candy that only applies a buff on eating, currently only speed.
    public static RegistryObject<Item> registerSimpleCandy(String id,
                                                           Supplier<MobEffectInstance> effectSupplier) {
        return register(id, Item::new, new Item.Properties()
                .food((new FoodProperties.Builder())
                        .nutrition(4)
                        .saturationMod(1.2F)
                        .effect(effectSupplier, 1.0F)
                        .alwaysEat()
                        .build()), true);
    }

    // For registering non-candies.
    @SuppressWarnings("unused")
    public static RegistryObject<Item> register(String id,
                                                Function<Item.Properties, Item> constructor,
                                                Item.Properties properties) {
        return register(id, constructor, properties, false);
    }

    public static RegistryObject<Item> register(String id,
                                                Function<Item.Properties, Item> constructor,
                                                Item.Properties properties, boolean isCandy) {
        RegistryObject<Item> item = Main.ITEMS.register(id, () -> constructor.apply(properties));
        ALL_ITEMS.add(item);
        if (isCandy) ALL_CANDIES.add(item);
        return item;
    }

    // Used to load this class at the needed time from main.
    public static void init() {

    }
}
