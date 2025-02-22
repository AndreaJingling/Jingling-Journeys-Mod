package jacksunderscoreusername.holiday_mod.datagen;

import jacksunderscoreusername.holiday_mod.Main;
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

    }

    // HELPER METHODS
    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void simpleItem(RegistryObject<Block> blockRegistryObject) {
        itemModels().withExistingParent(blockRegistryObject.getId().getPath(), new ResourceLocation("item/generated")).texture("layer0", new ResourceLocation(Main.MOD_ID, "item/" + blockRegistryObject.getId().getPath()));
    }
}
