package holiday_mod.registry;

import holiday_mod.Main;
import holiday_mod.world.item.crafting.SleighConstructionTableRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeTypes {
    @SuppressWarnings("unused")
    public static final RegistryObject<RecipeType<SleighConstructionTableRecipe>> SLEIGH_CONSTRUCTION_TYPE =
            Main.RECIPE_TYPES.register("sleigh_constructing", () ->  SleighConstructionTableRecipe.Type.INSTANCE);

    public static void init() {
    }
}
