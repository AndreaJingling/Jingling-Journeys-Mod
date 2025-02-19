package jacksunderscoreusername.holiday_mod;

import jacksunderscoreusername.holiday_mod.candies.ExampleCandy;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class Items {
    public static final RegistryObject<Item> EXAMPLE_CANDY = register("example_candy", ExampleCandy::new, ExampleCandy.getProperties());

    public static RegistryObject<Item> register(String id, Function<Item.Properties, Item> constructor, Item.Properties properties) {
        return Main.ITEMS.register(id, () -> constructor.apply(properties));
    }

    public static void init() {

    }

    ;
}
