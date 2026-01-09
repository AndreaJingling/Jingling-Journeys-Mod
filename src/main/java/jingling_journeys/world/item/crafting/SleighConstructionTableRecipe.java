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

package jingling_journeys.world.item.crafting;

import com.google.gson.*;
import jingling_journeys.Main;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SleighConstructionTableRecipe implements Recipe<CraftingContainer> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;
    private final boolean isExtended;

    public SleighConstructionTableRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id,
                                         boolean isExtended) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
        this.isExtended = isExtended;
    }

    public boolean isExtended() {
        return this.isExtended;
    }

    @Override
    public @NotNull String getGroup() {
        return "misc";
    }

    @Override
    public boolean matches(@NotNull CraftingContainer container, Level level) {
        if(level.isClientSide())
            return false;

        for (int i = 1; i < (isExtended ? 18 : 9); i++) {
            ItemStack stack = container.getItem(i);
            if (i >= inputItems.size() || !inputItems.get(i).test(stack)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isIncomplete() {
        List<Ingredient> ingredients = isExtended ? this.getIngredients() : this.getIngredients().subList(0, 9);
        return ingredients.isEmpty() || ingredients.stream().anyMatch(ForgeHooks::hasNoElements);
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingContainer container,
                                       @NotNull RegistryAccess registryAccess) {
        return output.copy();
    }

    @SuppressWarnings("unused")
    public ItemStack getOutput() {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth >= (this.isExtended ? 6 : 3);
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
                throw new JsonSyntaxException("Invalid pattern: Expected exactly 3 rows, but got " + patternJson.size());
            }

            String[] pattern = new String[3];
            boolean isExtended = true;
            for (int i = 0; i < 3; i++) {
                pattern[i] = GsonHelper.convertToString(patternJson.get(i), "pattern[" + i + "]");
                isExtended = isExtended && pattern[i].length() == 6;
                if (pattern[i].length() != 3 && pattern[i].length() != 6 && pattern[0].length() != pattern[i].length()) {
                    throw new JsonSyntaxException("Invalid pattern: All rows must either have 3 characters or" +
                            " all rows must have 6 characters!");
                }
            }

            // Read the key object mapping characters to Ingredients
            JsonObject keyJson = GsonHelper.getAsJsonObject(json, "key");
            Map<Character, Ingredient> key = new HashMap<>();
            for (Map.Entry<String, JsonElement> entry : keyJson.entrySet()) {
                String keyCharStr = entry.getKey();
                if (keyCharStr.length() != 1) {
                    throw new JsonSyntaxException("Invalid key entry: '" + keyCharStr + "' is not a single character");
                }
                char symbol = keyCharStr.charAt(0);
                Ingredient ingredient = Ingredient.fromJson(entry.getValue());
                key.put(symbol, ingredient);
            }
            Main.LOGGER.info("Key: {}", key);

            NonNullList<Ingredient> ingredients = NonNullList.withSize(18, Ingredient.EMPTY);
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < (isExtended ? 6 : 3); col++) {
                    char symbol = pattern[row].charAt(col);
                    // Allow spaces to represent an empty slot.
                    Ingredient ingredient = symbol == ' ' ? Ingredient.EMPTY : key.get(symbol);
                    if (ingredient == null) {
                        throw new JsonSyntaxException("Pattern references symbol '" + symbol
                                + "' which is not defined in the key");
                    }
                    ingredients.set(row + col * 3, ingredient);
                }
            }

            JsonObject resultJson = GsonHelper.getAsJsonObject(json, "result");
            ItemStack result = ShapedRecipe.itemStackFromJson(resultJson);

            return new SleighConstructionTableRecipe(ingredients, result, id, isExtended);
        }

        @Override
        public @Nullable SleighConstructionTableRecipe fromNetwork(@NotNull ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            inputs.replaceAll(ignored -> Ingredient.fromNetwork(buf));

            ItemStack output = buf.readItem();
            boolean isExtended = buf.readBoolean();
            return new SleighConstructionTableRecipe(inputs, output, id, isExtended);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, SleighConstructionTableRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(
                    RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY)), false);
            buf.writeBoolean(recipe.isExtended());
        }
    }
}
