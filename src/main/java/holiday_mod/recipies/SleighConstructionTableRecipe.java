package holiday_mod.recipies;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import holiday_mod.Main;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class SleighConstructionTableRecipe implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;

    public SleighConstructionTableRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
    }

    @Override
    public @NotNull String getGroup() {
        return "misc";
    }

    @Override
    public boolean matches(@NotNull SimpleContainer simpleContainer, Level level) {
        if(level.isClientSide())
            return false;

        for (int i = 1; i < 18; i++) {
            ItemStack stack = simpleContainer.getItem(i);
            if (!inputItems.get(i).test(stack)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull SimpleContainer simpleContainer,
                                       @NotNull RegistryAccess registryAccess) {
        return output.copy();
    }

    @SuppressWarnings("unused")
    public ItemStack getOutput() {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return this.inputItems;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<SleighConstructionTableRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        @SuppressWarnings("unused")
        public static final String ID = "sleigh_constructing";
    }

    public static class Serializer implements RecipeSerializer<SleighConstructionTableRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public @NotNull SleighConstructionTableRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
            JsonArray patternJson = GsonHelper.getAsJsonArray(json, "pattern");
            if (patternJson.size() != 3) {
                throw new JsonParseException("Invalid pattern: Expected exactly 3 rows, but got " + patternJson.size());
            }

            String[] pattern = new String[3];
            for (int i = 0; i < 3; i++) {
                pattern[i] = GsonHelper.convertToString(patternJson.get(i), "pattern[" + i + "]");
                if (pattern[i].length() != 6) {
                    throw new JsonParseException("Invalid pattern: Each row must have exactly 6 characters");
                }
            }

            // Read the key object mapping characters to Ingredients
            JsonObject keyJson = GsonHelper.getAsJsonObject(json, "key");
            Map<Character, Ingredient> key = new HashMap<>();
            for (Map.Entry<String, JsonElement> entry : keyJson.entrySet()) {
                String keyCharStr = entry.getKey();
                if (keyCharStr.length() != 1) {
                    throw new JsonParseException("Invalid key entry: '" + keyCharStr + "' is not a single character");
                }
                char symbol = keyCharStr.charAt(0);
                Ingredient ingredient = Ingredient.fromJson(entry.getValue());
                key.put(symbol, ingredient);
            }
            Main.LOGGER.info("Key: {}", key);

            NonNullList<Ingredient> ingredients = NonNullList.withSize(18, Ingredient.EMPTY);
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 6; col++) {
                    char symbol = pattern[row].charAt(col);
                    // Allow spaces to represent an empty slot.
                    Ingredient ingredient = symbol == ' ' ? Ingredient.EMPTY : key.get(symbol);
                    if (ingredient == null) {
                        throw new JsonParseException("Pattern references symbol '" + symbol
                                + "' which is not defined in the key");
                    }
                    ingredients.set(row * 6 + col, ingredient);
                }
            }

            JsonObject resultJson = GsonHelper.getAsJsonObject(json, "result");
            ItemStack result = ShapedRecipe.itemStackFromJson(resultJson);

            return new SleighConstructionTableRecipe(ingredients, result, id);
        }

        @Override
        public @Nullable SleighConstructionTableRecipe fromNetwork(@NotNull ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            inputs.replaceAll(ignored -> Ingredient.fromNetwork(buf));

            ItemStack output = buf.readItem();
            return new SleighConstructionTableRecipe(inputs, output, id);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, SleighConstructionTableRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(null), false);
        }
    }
}
