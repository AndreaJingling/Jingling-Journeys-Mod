/**
 *         Jingling Journeys - Give your Minecraft World a little Festive Cheer, Sleigh Riding Fun, and 
 *         The Spirit of the Season.<br>
 *         Copyright (C) 2025-2026  Jingling Journeys Team<br>
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
import jingling_journeys.world.item.ModItems;
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
    private ItemModelBuilder reindeerSpawnEgg;

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Main.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        exampleGummyCandy = simpleItem(ModItems.EXAMPLE_GUMMY_CANDY);
        exampleWrappedCandy = simpleItem(ModItems.EXAMPLE_WRAPPED_CANDY);
        sharpenedCandyCane = simpleItem(ModItems.SHARPENED_CANDY_CANE);
        genericSmallSled = simpleItem(ModItems.GENERIC_SMALL_SLED);
        assert ModItems.ELF_SPAWN_EGG.getId() != null;
        elfSpawnEgg = withExistingParent(ModItems.ELF_SPAWN_EGG.getId().getPath(),
                ResourceLocation.parse("item/template_spawn_egg"));
        assert ModItems.REINDEER_SPAWN_EGG.getId() != null;
        reindeerSpawnEgg = withExistingParent(ModItems.REINDEER_SPAWN_EGG.getId().getPath(),
                ResourceLocation.parse("item/template_spawn_egg"));
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
}
