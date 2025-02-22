package jacksunderscoreusername.holiday_mod.registry;

import jacksunderscoreusername.holiday_mod.Main;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class HolidayCreativeTabs {
    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = Main.CREATIVE_MODE_TABS.register(Main.MOD_ID, () -> CreativeModeTab.builder()
            .icon(()-> HolidayItems.CANDY_CANE.get().getDefaultInstance())
            .title(Component.translatable("itemGroup." + Main.MOD_ID + ".creative_tab"))
            .displayItems((parameters, output) -> {
                for (RegistryObject<Item> item : HolidayItems.ALL_ITEMS)
                    output.accept(item.get());
            }).build());

    public static void init() {
    }
}
