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

import holiday_mod.world.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends net.minecraft.data.recipes.RecipeProvider {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CANDY_CANE.get())
                .pattern(" # ")
                .pattern("# #")
                .pattern("  #")
                .define('#', net.minecraft.world.item.Items.SUGAR)
                .unlockedBy(getHasName(net.minecraft.world.item.Items.SUGAR),
                        net.minecraft.data.recipes.RecipeProvider.has(net.minecraft.world.item.Items.SUGAR))
                .save(writer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.EXAMPLE_GUMMY_CANDY.get())
                .requires(net.minecraft.world.item.Items.SUGAR)
                .requires(net.minecraft.world.item.Items.SUGAR)
                .unlockedBy(getHasName(net.minecraft.world.item.Items.SUGAR),
                        net.minecraft.data.recipes.RecipeProvider.has(net.minecraft.world.item.Items.SUGAR))
                .save(writer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.EXAMPLE_WRAPPED_CANDY.get())
                .requires(net.minecraft.world.item.Items.SUGAR)
                .requires(net.minecraft.world.item.Items.PAPER)
                .unlockedBy(getHasName(net.minecraft.world.item.Items.SUGAR),
                        net.minecraft.data.recipes.RecipeProvider.has(net.minecraft.world.item.Items.SUGAR))
                .unlockedBy(getHasName(net.minecraft.world.item.Items.PAPER),
                        net.minecraft.data.recipes.RecipeProvider.has(net.minecraft.world.item.Items.PAPER))
                .save(writer);
    }
}
