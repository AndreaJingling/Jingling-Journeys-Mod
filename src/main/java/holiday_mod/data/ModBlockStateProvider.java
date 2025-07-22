/**
 *         Minecraft Holiday Mod (WIP description)<br>
 *         Copyright (C) 2025  Minecraft Holiday Mod Team (WIP name)<br>
 *         <br>
 *         This file part of Minecraft Holiday Mod.<br>
 *         <br>
 *         This program is free software: you can redistribute it and/or modify<br>
 *         it under the terms of the GNU General Public License as published by<br>
 *         the Free Software Foundation, either version 3 of the License, or<br>
 *         (at your option) any later version.<br>
 *         <br>
 *         This program is distributed in the hope that it will be useful,<br>
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of<br>
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the<br>
 *         GNU General Public License for more details.<br>
 *         <br>
 *         You should have received a copy of the GNU General Public License<br>
 *         along with this program.  If not, see <https://www.gnu.org/licenses/>.<br>
 */

package holiday_mod.data;

import holiday_mod.Main;
import holiday_mod.world.level.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Main.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        translucentHorizontallyDirectionalBlockItem(ModBlocks.SLEIGH_CONSTRUCTION_TABLE);
        translucentBlockItem(ModBlocks.LEATHERWORKER_TABLE);
        translucentBlockItem(ModBlocks.TOYSMITH_TABLE);
    }

    // HELPER METHODS
    @SuppressWarnings("SameParameterValue")
    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    @SuppressWarnings("SameParameterValue")
    private void translucentHorizontallyDirectionalBlockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                ((BlockModelBuilder) directionalBlock(blockRegistryObject.get(), ))
                .renderType("translucent"));
    }

    @SuppressWarnings("SameParameterValue")
    private void translucentBlockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), ((BlockModelBuilder) cubeAll(blockRegistryObject.get()))
                .renderType("translucent"));
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
