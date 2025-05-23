package holiday_mod.world.item;

import holiday_mod.Main;
import holiday_mod.world.level.block.ModBlocks;
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
                output.accept(ModItems.CANDY_CANE.get());
                output.accept(ModItems.SHARPENED_CANDY_CANE.get());
                output.accept(ModItems.EXAMPLE_GUMMY_CANDY.get());
                output.accept(ModItems.EXAMPLE_WRAPPED_CANDY.get());
                output.accept(ModItems.GENERIC_SMALL_SLED.get());
                output.accept(ModItems.ELF_SPAWN_EGG.get());
                output.accept(ModBlocks.getSleighConstructionTableItem().get());
            }).build());

    // Used to load this class at the needed time from main.
    public static void init() {
    }
}
