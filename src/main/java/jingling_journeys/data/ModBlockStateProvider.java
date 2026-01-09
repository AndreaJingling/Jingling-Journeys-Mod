/**
 * Jingling Journeys - Give your Minecraft World a little Festive Cheer, Sleigh Riding Fun, and
 * The Spirit of the Season.<br>
 * Copyright (C) 2025-2026  Jingling Journeys Team<br>
 * <br>
 * This file part of Jingling Journeys.<br>
 * <br>
 * This program is free software: you can redistribute it and/or modify<br>
 * it under the terms of the GNU Lesser General Public License as published by<br>
 * the Free Software Foundation, either version 3 of the License, or<br>
 * (at your option) any later version.<br>
 * <br>
 * This program is distributed in the hope that it will be useful,<br>
 * but WITHOUT ANY WARRANTY; without even the implied warranty of<br>
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the<br>
 * GNU Lesser General Public License for more details.<br>
 * <br>
 * You should have received a copy of the GNU Lesser General Public License<br>
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.<br>
 */

package jingling_journeys.data;

import jingling_journeys.Main;
import jingling_journeys.world.level.block.FireplaceBlock;
import jingling_journeys.world.level.block.ModBlocks;
import jingling_journeys.world.level.block.state.properties.ChimneyType;
import jingling_journeys.world.level.block.state.properties.ModBlockStateProperties;
import jingling_journeys.world.level.block.state.properties.SleighConstructionTableType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Main.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModelFile sleighConstructionTableMainModelFile = this.models()
                .cube("sleigh_construction_table_main",
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/sleigh_construction_table_main_bottom"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/sleigh_construction_table_main_top"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/sleigh_construction_table_main_side1"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/sleigh_construction_table_main_side3"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/sleigh_construction_table_main_side2"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/sleigh_construction_table_main_side4")
                )
                .renderType("translucent");
        ModelFile sleighConstructionTableExtensionModelFile = this.models()
                .cube("sleigh_construction_table_extension",
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/sleigh_construction_table_extension_bottom"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/sleigh_construction_table_extension_top"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/sleigh_construction_table_extension_side1"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/sleigh_construction_table_extension_side3"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/sleigh_construction_table_extension_side2"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/sleigh_construction_table_extension_side4")
                )
                .renderType("translucent");
        ModelFile leatherworkerTableModelFile = this.models()
                .cube("leatherworker_table",
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/leatherworker_table_bottom"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/leatherworker_table_top"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/leatherworker_table_side1"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/leatherworker_table_side3"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/leatherworker_table_side2"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/leatherworker_table_side4")
                        )
                .renderType("translucent");
        ModelFile toysmithTableModelFile = this.models()
                .cube("toysmith_table",
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/toysmith_table_bottom"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/toysmith_table_top"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/toysmith_table_side1"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/toysmith_table_side3"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/toysmith_table_side2"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                                "block/toysmith_table_side4")
                )
                .renderType("translucent");
        ModelFile topChimneyModelFile = this.models().cubeBottomTop("chimney_top",
                ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                        "block/chimney_connection_sides"),
                ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                        "block/chimney_connection_ends"),
                ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                        "block/chimney_top"))
                .renderType("translucent");
        ModelFile connectionChimneyModelFile = this.models().cubeColumn("chimney_connection",
                ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                        "block/chimney_connection_sides"),
                ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,
                        "block/chimney_connection_ends"))
                .renderType("translucent");
        ModelFile litFireplaceModelFile = this.models().cube("fireplace_lit",
                ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "block/fireplace_lit_top"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "block/fireplace_lit_bottom"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "block/fireplace_lit_side1"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "block/fireplace_lit_side3"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "block/fireplace_lit_side2"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "block/fireplace_lit_side4"))
                .renderType("translucent");
        ModelFile unlitFireplaceModelFile = this.models().cube("fireplace_unlit",
                ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "block/fireplace_unlit_top"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "block/fireplace_unlit_bottom"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "block/fireplace_unlit_side1"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "block/fireplace_unlit_side3"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "block/fireplace_unlit_side2"),
                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "block/fireplace_unlit_side4"))
                .renderType("translucent");

        horizontalBlock(ModBlocks.SLEIGH_CONSTRUCTION_TABLE.get(), (BlockState state) ->
                        (state.getValue(ModBlockStateProperties.SLEIGH_CONSTRUCTION_TABLE_TYPE)
                                == SleighConstructionTableType.MAIN) ? sleighConstructionTableMainModelFile:
                                sleighConstructionTableExtensionModelFile);

        horizontalBlock(ModBlocks.LEATHERWORKER_TABLE.get(), leatherworkerTableModelFile);
        horizontalBlock(ModBlocks.TOYSMITH_TABLE.get(), toysmithTableModelFile);
        getVariantBuilder(ModBlocks.CHIMNEY.get())
                .partialState().with(ModBlockStateProperties.CHIMNEY_TYPE, ChimneyType.TOP)
                .modelForState().modelFile(topChimneyModelFile).addModel()
                .partialState().with(ModBlockStateProperties.CHIMNEY_TYPE, ChimneyType.CONNECTION)
                .modelForState().modelFile(connectionChimneyModelFile).addModel();
        simpleBlockItem(ModBlocks.CHIMNEY.get(), connectionChimneyModelFile);
        horizontalBlock(ModBlocks.FIREPLACE.get(), (blockState) ->
                blockState.getValue(BlockStateProperties.LIT) ? litFireplaceModelFile : unlitFireplaceModelFile);
        simpleBlockItem(ModBlocks.FIREPLACE.get(), unlitFireplaceModelFile);
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
