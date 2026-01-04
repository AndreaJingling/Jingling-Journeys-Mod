package jingling_journeys.client;

import jingling_journeys.world.item.ModItems;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModRecipeBookCategories {
    public static final RecipeBookCategories SLEIGH_CONSTRUCTING_MISC =
            RecipeBookCategories.create("SLEIGH_CONSTRUCTING_MISC",
                    new ItemStack(ModItems.GENERIC_SMALL_SLED.get()));

    public static void init() {

    }
}
