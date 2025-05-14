package holiday_mod.world.item;

import holiday_mod.Main;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    @SuppressWarnings("unused")
    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB =
            Main.CREATIVE_MODE_TABS.register(Main.MOD_ID, () -> CreativeModeTab.builder()
            .icon(()-> ModItems.CANDY_CANE.get().getDefaultInstance())
            .title(Component.translatable("itemGroup." + Main.MOD_ID + ".creative_tab"))
            .displayItems((parameters, output) -> {
                for (RegistryObject<Item> item : ModItems.ALL_ITEMS)
                    output.accept(item.get());
            }).build());

    // Used to load this class at the needed time from main.
    public static void init() {
    }
}
