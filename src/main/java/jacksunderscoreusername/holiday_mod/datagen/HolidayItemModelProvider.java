package jacksunderscoreusername.holiday_mod.datagen;

import jacksunderscoreusername.holiday_mod.Main;
import jacksunderscoreusername.holiday_mod.registry.HolidayItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class HolidayItemModelProvider extends ItemModelProvider {
    public HolidayItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Main.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(HolidayItems.EXAMPLE_GUMMY_CANDY);
        simpleItem(HolidayItems.EXAMPLE_WRAPPED_CANDY);
        simpleItem(HolidayItems.CANDY_CANE);
        simpleItem(HolidayItems.SHARPENED_CANDY_CANE);
    }

    // HELPER METHODS
    public ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Main.MOD_ID, "item/" + item.getId().getPath()));
    }
}
