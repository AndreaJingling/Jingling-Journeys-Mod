/**
 *         Jingling Journeys (WIP description)<br>
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

import jingling_journeys.world.item.ModItems;
import jingling_journeys.world.level.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CANDY_CANE.get())
                .define('#', Items.SUGAR)
                .pattern(" # ")
                .pattern("# #")
                .pattern("  #")
                .unlockedBy("has_sugar", RecipeProvider.has(Items.SUGAR))
                .save(writer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.EXAMPLE_GUMMY_CANDY.get())
                .requires(Items.SUGAR)
                .requires(Items.SUGAR)
                .unlockedBy("has_sugar", RecipeProvider.has(Items.SUGAR))
                .save(writer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.EXAMPLE_WRAPPED_CANDY.get())
                .requires(Items.SUGAR)
                .requires(Items.PAPER)
                .unlockedBy("has_sugar", RecipeProvider.has(Items.SUGAR))
                .unlockedBy("has_paper", RecipeProvider.has(Items.PAPER))
                .save(writer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.getSleighConstructionTableItem().get())
                .define('c', Tags.Items.INGOTS_COPPER)
                .define('P', ItemTags.PLANKS)
                .define('G', Tags.Items.DUSTS_GLOWSTONE)
                .pattern("ccc")
                .pattern("PGP")
                .pattern("PPP")
                .unlockedBy("has_copper_ingot", RecipeProvider.has(Tags.Items.INGOTS_COPPER))
                .unlockedBy("has_planks", RecipeProvider.has(ItemTags.PLANKS))
                .unlockedBy("has_glowstone_dust", RecipeProvider.has(Tags.Items.DUSTS_GLOWSTONE))
                .save(writer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.getLeatherworkerTableItem().get())
                .define('i', Tags.Items.INGOTS_IRON)
                .define('C', Tags.Items.COBBLESTONE)
                .define('L', Tags.Items.LEATHER)
                .pattern("iLi")
                .pattern("CCC")
                .pattern("CCC")
                .unlockedBy("has_iron_ingot", RecipeProvider.has(Tags.Items.INGOTS_IRON))
                .unlockedBy("has_cobblestone", RecipeProvider.has(Tags.Items.COBBLESTONE))
                .unlockedBy("has_leather", RecipeProvider.has(Tags.Items.LEATHER))
                .save(writer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.getToysmithTableItem().get())
                .define('s', Tags.Items.SHEARS) // Scissors
                .define('S', Tags.Items.STRING) // Thread
                .define('N', Tags.Items.RODS_WOODEN) // Needle
                .define('t', Tags.Items.INGOTS_IRON) // Needle Tip
                .define('T', ItemTags.WOOL) // Toy
                .define('P', ItemTags.PLANKS)
                .pattern(" St")
                .pattern("sTN")
                .pattern("PPP")
                .unlockedBy("has_scissors", RecipeProvider.has(Tags.Items.SHEARS))
                .unlockedBy("has_thread", RecipeProvider.has(Tags.Items.STRING))
                .unlockedBy("has_needle", RecipeProvider.has(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_needle_tip", RecipeProvider.has(Tags.Items.INGOTS_IRON))
                .unlockedBy("has_toy", RecipeProvider.has(ItemTags.WOOL))
                .unlockedBy("has_table", RecipeProvider.has(ItemTags.PLANKS))
                .save(writer);
    }
}
