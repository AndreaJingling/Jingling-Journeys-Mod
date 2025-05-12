package holiday_mod.registry;

import holiday_mod.world.item.crafting.SleighConstructionTableRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.RegistryObject;

import static holiday_mod.Main.RECIPES;

public class ModRecipes {
    @SuppressWarnings("unused")
    public static final RegistryObject<RecipeSerializer<SleighConstructionTableRecipe>> SLEIGH_CONSTRUCTION_TABLE_SERIALIZER
            = RECIPES.register("sleigh_constructing", () -> SleighConstructionTableRecipe.Serializer.INSTANCE);

    public static void init() {

    }
}
