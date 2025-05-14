package holiday_mod.data;

import holiday_mod.Main;
import holiday_mod.world.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    private ItemModelBuilder exampleGummyCandy;
    private ItemModelBuilder exampleWrappedCandy;
    private ItemModelBuilder sharpenedCandyCane;
    private ItemModelBuilder genericSmallSled;
    private ItemModelBuilder elfSpawnEgg;

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Main.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        exampleGummyCandy = simpleItem(ModItems.EXAMPLE_GUMMY_CANDY);
        exampleWrappedCandy = simpleItem(ModItems.EXAMPLE_WRAPPED_CANDY);
        sharpenedCandyCane = simpleItem(ModItems.SHARPENED_CANDY_CANE);
        // genericSmallSled = simpleItem(ModItems.GENERIC_SMALL_SLED);
    }

    // HELPER METHODS
    public ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        assert item.getId() != null;
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.tryBuild(Main.MOD_ID, "item/" + item.getId().getPath()));
    }

    @SuppressWarnings("unused")
    public ItemModelBuilder getExampleGummyCandy() {
        return exampleGummyCandy;
    }

    @SuppressWarnings("unused")
    public ItemModelBuilder getExampleWrappedCandy() {
        return exampleWrappedCandy;
    }

    @SuppressWarnings("unused")
    public ItemModelBuilder getSharpenedCandyCane() {
        return sharpenedCandyCane;
    }

    @SuppressWarnings("unused")
    public ItemModelBuilder getGenericSmallSled() {
        return genericSmallSled;
    }

    @SuppressWarnings("unused")
    public ItemModelBuilder getElfSpawnEgg() {
        return elfSpawnEgg;
    }
}
