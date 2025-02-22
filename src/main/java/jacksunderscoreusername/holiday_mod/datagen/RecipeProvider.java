package jacksunderscoreusername.holiday_mod.datagen;

import jacksunderscoreusername.holiday_mod.Items;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {
    public RecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.CANDY_CANE.get())
                .pattern(" # ")
                .pattern("# #")
                .pattern("  #")
                .define('#', net.minecraft.world.item.Items.SUGAR)
                .unlockedBy(getHasName(net.minecraft.world.item.Items.SUGAR), net.minecraft.data.recipes.RecipeProvider.has(net.minecraft.world.item.Items.SUGAR))
                .save(writer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.EXAMPLE_GUMMY_CANDY.get())
                .requires(net.minecraft.world.item.Items.SUGAR)
                .requires(net.minecraft.world.item.Items.SUGAR)
                .unlockedBy(getHasName(net.minecraft.world.item.Items.SUGAR), net.minecraft.data.recipes.RecipeProvider.has(net.minecraft.world.item.Items.SUGAR))
                .save(writer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.EXAMPLE_WRAPPED_CANDY.get())
                .requires(net.minecraft.world.item.Items.SUGAR)
                .requires(net.minecraft.world.item.Items.PAPER)
                .unlockedBy(getHasName(net.minecraft.world.item.Items.SUGAR), net.minecraft.data.recipes.RecipeProvider.has(net.minecraft.world.item.Items.SUGAR))
                .unlockedBy(getHasName(net.minecraft.world.item.Items.PAPER), net.minecraft.data.recipes.RecipeProvider.has(net.minecraft.world.item.Items.PAPER))
                .save(writer);
    }
}
