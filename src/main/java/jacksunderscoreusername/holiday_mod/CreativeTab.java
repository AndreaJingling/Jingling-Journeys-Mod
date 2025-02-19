package jacksunderscoreusername.holiday_mod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTab {
    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = Main.CREATIVE_MODE_TABS.register(Main.MOD_IDID, () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(()->Items.EXAMPLE_CANDY.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(Items.EXAMPLE_CANDY.get());
            }).build());

    public static void init() {
    }
}
