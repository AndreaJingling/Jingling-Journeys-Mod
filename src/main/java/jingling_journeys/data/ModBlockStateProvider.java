/**
 *         Jingling Journeys (WIP description)<br>
 *         Copyright (C) 2025  Jingling Journeys Team (WIP name)<br>
 *         <br>
 *         This file part of Jingling Journeys.<br>
 *         <br>
 *         This program is free software: you can redistribute it and/or modify<br>
 *         it under the terms of the GNU Lesser General Public License as published by<br>
 *         the Free Software Foundation, either version 3 of the License, or<br>
 *         (at your option) any later version.<br>
 *         <br>
 *         This program is distributed in the hope that it will be useful,<br>
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of<br>
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the<br>
 *         GNU Lesser General Public License for more details.<br>
 *         <br>
 *         You should have received a copy of the GNU Lesser General Public License<br>
 *         along with this program.  If not, see <https://www.gnu.org/licenses/>.<br>
 */

package jingling_journeys.data;

import jingling_journeys.Main;
import jingling_journeys.world.level.block.ModBlocks;
import jingling_journeys.world.level.block.state.properties.ChimneyType;
import jingling_journeys.world.level.block.state.properties.ModBlockStateProperties;
import jingling_journeys.world.level.block.state.properties.SleighConstructionTableType;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Main.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        horizontalBlock(ModBlocks.SLEIGH_CONSTRUCTION_TABLE.get(), (BlockState state) ->
            new ModelFile.ExistingModelFile(
                    (state.getValue(ModBlockStateProperties.SLEIGH_CONSTRUCTION_TABLE_TYPE)
                            == SleighConstructionTableType.MAIN) ?
                            ResourceLocation.tryBuild(Main.MOD_ID, "block/sleigh_construction_table_main") :
                            ResourceLocation.tryBuild(Main.MOD_ID, "block/sleigh_construction_table_extension"),
                    models().existingFileHelper)
        );
        blockItemWithExistingModel(ModBlocks.LEATHERWORKER_TABLE,
                ResourceLocation.tryBuild(Main.MOD_ID, "block/leatherworker_table"));
        blockItemWithExistingModel(ModBlocks.TOYSMITH_TABLE,
                ResourceLocation.tryBuild(Main.MOD_ID, "block/toysmith_table"));
        getVariantBuilder(ModBlocks.CHIMNEY.get())
                .forAllStates(
                        (state) -> ConfiguredModel.builder()
                                .modelFile(new ModelFile.ExistingModelFile(
                                (state.getValue(ModBlockStateProperties.CHIMNEY_TYPE)
                                        == ChimneyType.TOP) ?
                                        ResourceLocation.tryBuild(Main.MOD_ID, "block/chimney_top") :
                                        ResourceLocation.tryBuild(Main.MOD_ID, "block/chimney_connection"),
                                models().existingFileHelper)).build()
                );
        simpleBlockItem(ModBlocks.CHIMNEY.get(),
                new ModelFile.ExistingModelFile(ResourceLocation.tryBuild(Main.MOD_ID, "block/chimney_top"),
                        models().existingFileHelper));
        blockItemWithExistingModel(ModBlocks.FIREPLACE,
                ResourceLocation.tryBuild(Main.MOD_ID, "block/fireplace"));
    }

    // HELPER METHODS
    @SuppressWarnings({"SameParameterValue", "unused"})
    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    @SuppressWarnings({"SameParameterValue", "unused"})
    private void blockItemWithExistingModel(RegistryObject<Block> blockRegistryObject, ResourceLocation modelLocation) {
        simpleBlockWithItem(blockRegistryObject.get(),
                new ModelFile.ExistingModelFile(modelLocation, models().existingFileHelper));
    }

    @SuppressWarnings({"SameParameterValue", "unused"})
    private void translucentBlockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), ((BlockModelBuilder) cubeAll(blockRegistryObject.get()))
                .renderType("translucent"));
    }

    @SuppressWarnings({"SameParameterValue", "unused"})
    private void simpleItem(RegistryObject<Block> blockRegistryObject) {
        assert blockRegistryObject.getId() != null;
        itemModels()
                .withExistingParent(blockRegistryObject.getId().getPath(),
                        ResourceLocation.parse("item/generated"))
                .texture("layer0",
                        ResourceLocation.tryBuild(Main.MOD_ID, "item/" + blockRegistryObject.getId().getPath()));
    }
}
