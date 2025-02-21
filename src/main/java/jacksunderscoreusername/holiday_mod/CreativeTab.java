package jacksunderscoreusername.holiday_mod;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTab {
    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = Main.CREATIVE_MODE_TABS.register(Main.MOD_ID, () -> CreativeModeTab.builder()
            .icon(()->Items.CANDY_CANE.get().getDefaultInstance())
            .title(Component.translatable("itemGroup." + Main.MOD_ID + ".creative_tab"))
            .displayItems((parameters, output) -> {
                for (RegistryObject<Item> item : Items.ALL_ITEMS)
                    output.accept(item.get());
            }).build());

    public static void init() {
    }
}
