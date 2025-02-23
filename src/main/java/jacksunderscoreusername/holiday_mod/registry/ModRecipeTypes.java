package jacksunderscoreusername.holiday_mod.registry;

import jacksunderscoreusername.holiday_mod.Main;
import jacksunderscoreusername.holiday_mod.recipies.SleighConstructionTableRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;

import static jacksunderscoreusername.holiday_mod.Main.LOGGER;

public class ModRecipeTypes {
    public static final RegistryObject<RecipeType<SleighConstructionTableRecipe>> SLEIGH_CONSTRUCTION_TYPE = Main.RECIPE_TYPES.register("sleigh_constructing", () ->  SleighConstructionTableRecipe.Type.INSTANCE);

    public static void init() {
    }
}
