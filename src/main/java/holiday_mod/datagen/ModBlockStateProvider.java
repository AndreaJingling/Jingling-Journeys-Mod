package holiday_mod.datagen;

import holiday_mod.Main;
import holiday_mod.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Main.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockItem(ModBlocks.SLEIGH_CONSTRUCTION_TABLE);
    }

    // HELPER METHODS
    @SuppressWarnings("SameParameterValue")
    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    @SuppressWarnings("unused")
    private void simpleItem(RegistryObject<Block> blockRegistryObject) {
        assert blockRegistryObject.getId() != null;
        itemModels()
                .withExistingParent(blockRegistryObject.getId().getPath(),
                        ResourceLocation.parse("item/generated"))
                .texture("layer0",
                        ResourceLocation.tryBuild(Main.MOD_ID, "item/" + blockRegistryObject.getId().getPath()));
    }
}
