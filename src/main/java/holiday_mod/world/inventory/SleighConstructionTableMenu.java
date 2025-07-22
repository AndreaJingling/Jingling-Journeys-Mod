/**
 *         Minecraft Holiday Mod (WIP description)<br>
 *         Copyright (C) 2025  Minecraft Holiday Mod Team (WIP name)<br>
 *         Copyright (C) diesieben07<br>
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

package holiday_mod.world.inventory;

import com.google.common.collect.Lists;
import holiday_mod.world.item.crafting.ModRecipeTypes;
import holiday_mod.world.item.crafting.SleighConstructionTableRecipe;
import holiday_mod.world.level.block.ModBlocks;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class SleighConstructionTableMenu extends ItemCombinerMenu {
    private final Level level;
    @Nullable
    private SleighConstructionTableRecipe selectedRecipe;
    private final boolean hasExtension;
    private final List<SleighConstructionTableRecipe> recipes;

    public SleighConstructionTableMenu(int pContainerId, Inventory pPlayerInventory, FriendlyByteBuf extraData,
                                       ContainerLevelAccess access, boolean hasExtension) {
        super(MenuType.SMITHING, pContainerId, pPlayerInventory, access);
        this.level = pPlayerInventory.player.level();
        this.recipes = this.level.getRecipeManager().getAllRecipesFor(ModRecipeTypes.SLEIGH_CONSTRUCTION_TYPE.get());
        this.hasExtension = hasExtension;
    }

    protected boolean mayPickup(@NotNull Player pPlayer, boolean pHasStack) {
        return this.selectedRecipe != null && this.selectedRecipe.matches((SimpleContainer) this.inputSlots, this.level);
    }

    protected @NotNull ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        ItemCombinerMenuSlotDefinition.Builder builder = ItemCombinerMenuSlotDefinition.create()
                .withResultSlot(0, 148, 35);
        for (int y = 0; y < 3; y++) { // Rows (3 high)
            for (int x = 0; x < (hasExtension ? 6 : 3); x++) { // Columns (6 wide)
                int index = y + x * 3 + 1; // Calculate correct slot index
                builder.withSlot(index, 8 + x * 18, 17 + y * 18, (item) ->
                        this.recipes.stream().anyMatch((recipes) ->
                                recipes.getIngredients().stream().anyMatch((item2) ->
                                        item2.test(item))));
            }
        }
        return builder.build();
    }

    private void shrinkStackInSlot(int pIndex) {
        ItemStack itemstack = this.inputSlots.getItem(pIndex);
        if (!itemstack.isEmpty()) {
            itemstack.shrink(1);
            this.inputSlots.setItem(pIndex, itemstack);
        }
    }

    protected void onTake(Player pPlayer, ItemStack pStack) {
        pStack.onCraftedBy(pPlayer.level(), pPlayer, pStack.getCount());
        this.resultSlots.awardUsedRecipes(pPlayer, ((SimpleContainer) this.inputSlots).items.stream()
                .filter((p_19197_) -> !p_19197_.isEmpty()).collect(Collectors.toList()));
        for (int slotIndex = 1; slotIndex < (hasExtension ? TE_INVENTORY_SLOT_COUNT_WITH_EXTENSION :
                TE_INVENTORY_SLOT_COUNT_WITHOUT_EXTENSION); slotIndex++)
        {
            this.shrinkStackInSlot(slotIndex);
        }
        this.access.execute((p_40263_, p_40264_) -> {
            p_40263_.levelEvent(1044, p_40264_, 0);
        });
    }

    /**
     * Called when the Anvil Input Slot changes, calculates the new result and puts it in the output slot.
     */
    public void createResult() {
        List<SleighConstructionTableRecipe> list =
                this.level.getRecipeManager().getRecipesFor(
                                ModRecipeTypes.SLEIGH_CONSTRUCTION_TYPE.get(), (SimpleContainer) this.inputSlots, this.level);
        if (list.isEmpty()) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
        } else {
            SleighConstructionTableRecipe sleighConstructionTableRecipe = list.get(0);
            ItemStack itemstack = sleighConstructionTableRecipe.assemble((SimpleContainer) this.inputSlots,
                    this.level.registryAccess());
            if (itemstack.isItemEnabled(this.level.enabledFeatures())) {
                this.selectedRecipe = sleighConstructionTableRecipe;
                this.resultSlots.setRecipeUsed(sleighConstructionTableRecipe);
                this.resultSlots.setItem(0, itemstack);
            }
        }

    }

    protected boolean isValidBlock(BlockState pState) {
        return pState.is(Blocks.SMITHING_TABLE);
    }

    public boolean hasExtension() {
        return hasExtension;
    }

    //CREDIT FOR THIS PART GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    private static final int TE_INVENTORY_SLOT_COUNT_WITHOUT_EXTENSION = 3 * 3 + 1;
    private static final int TE_INVENTORY_SLOT_COUNT_WITH_EXTENSION = 6 * 3 + 1;
    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + (hasExtension ? TE_INVENTORY_SLOT_COUNT_WITH_EXTENSION : TE_INVENTORY_SLOT_COUNT_WITHOUT_EXTENSION), false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX +
                (hasExtension ? TE_INVENTORY_SLOT_COUNT_WITH_EXTENSION : TE_INVENTORY_SLOT_COUNT_WITHOUT_EXTENSION)) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(this.access, player, ModBlocks.SLEIGH_CONSTRUCTION_TABLE.get());
    }
}
