/**
 *         Jingling Journeys (WIP description)<br>
 *         Copyright (C) 2025  Jingling Journeys Team (WIP name)<br>
 *         Copyright (C) diesieben07<br>
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

package jingling_journeys.world.inventory;

import jingling_journeys.world.item.crafting.ModRecipeTypes;
import jingling_journeys.world.item.crafting.SleighConstructionTableRecipe;
import jingling_journeys.world.level.block.ModBlocks;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public class SleighConstructionTableMenu extends RecipeBookMenu<CraftingContainer> {
    private final CraftingContainer craftingSlots;
    private final ResultContainer resultSlots = new ResultContainer();
    private final ContainerLevelAccess access;
    private final Player player;

    public SleighConstructionTableMenu(int pContainerId, Inventory pPlayerInventory, FriendlyByteBuf extraData,
                                       ContainerLevelAccess pAccess, boolean hasExtension) {
        super(hasExtension ? ModMenuTypes.SLEIGH_CONSTRUCTION_TABLE_MENU_EXTENDED.get() :
                        ModMenuTypes.SLEIGH_CONSTRUCTION_TABLE_MENU_SINGLE.get(),
                pContainerId);
        this.access = pAccess;
        this.player = pPlayerInventory.player;
        this.craftingSlots = new TransientCraftingContainer(this, this.getGridWidth(), this.getGridHeight());

        this.addSlot(new ResultSlot(pPlayerInventory.player, this.craftingSlots, this.resultSlots,
                this.getResultSlotIndex(), 148, 35));

        for (int y = 0; y < this.getGridHeight(); y++) {
            for (int x = 0; x < this.getGridWidth(); x++) {
                int index = y + x * this.getGridHeight(); // Calculate correct slot index
                this.addSlot(new Slot(this.craftingSlots, index, 8 + x * 18, 17 + y * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(pPlayerInventory, l, 8 + l * 18, 142));
        }

        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(pPlayerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }
    }

    protected static void slotChangedCraftingGrid(AbstractContainerMenu pMenu, Level pLevel, Player pPlayer, CraftingContainer pContainer, ResultContainer pResult) {
        if (!pLevel.isClientSide) {
            ServerPlayer serverplayer = (ServerPlayer)pPlayer;
            ItemStack itemStack = ItemStack.EMPTY;
            Optional<SleighConstructionTableRecipe> optional = Objects.requireNonNull(pLevel.getServer())
                    .getRecipeManager().getRecipeFor(ModRecipeTypes.SLEIGH_CONSTRUCTION_TYPE.get(), pContainer, pLevel);
            if (optional.isPresent()) {
                SleighConstructionTableRecipe recipe = optional.get();
                if (pResult.setRecipeUsed(pLevel, serverplayer, recipe)) {
                    ItemStack itemStack1 = recipe.assemble(pContainer, pLevel.registryAccess());
                    if (itemStack1.isItemEnabled(pLevel.enabledFeatures())) {
                        itemStack = itemStack1;
                    }
                }
            }

            pResult.setItem(0, itemStack);
            pMenu.setRemoteSlot(0, itemStack);
            serverplayer.connection.send(new ClientboundContainerSetSlotPacket(pMenu.containerId,
                    pMenu.incrementStateId(), 0, itemStack));
        }
    }

    public boolean hasExtension() {
        return ModMenuTypes.SLEIGH_CONSTRUCTION_TABLE_MENU_EXTENDED.get().equals(this.getType());
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(this.access, player, ModBlocks.SLEIGH_CONSTRUCTION_TABLE.get());
    }

    @Override
    public @NotNull RecipeBookType getRecipeBookType() {
        return ModRecipeBookTypes.SLEIGH_CONSTRUCTING;
    }

    public void slotsChanged(@NotNull Container pInventory) {
        this.access.execute((p_39386_, p_39387_) -> {
            slotChangedCraftingGrid(this, p_39386_, this.player, this.craftingSlots, this.resultSlots);
        });
    }

    @Override
    public void fillCraftSlotsStackedContents(@NotNull StackedContents pItemHelper) {
        this.craftingSlots.fillStackedContents(pItemHelper);
    }

    @Override
    public void clearCraftingContent() {
        this.craftingSlots.clearContent();
        this.resultSlots.clearContent();
    }

    @Override
    public boolean recipeMatches(Recipe<? super CraftingContainer> pRecipe) {
        return pRecipe.matches(this.craftingSlots, this.player.level());
    }

    @Override
    public int getResultSlotIndex() {
        return 0;
    }

    @Override
    public int getGridWidth() {
        return hasExtension() ? 6 : 3;
    }

    @Override
    public int getGridHeight() {
        return 3;
    }

    @Override
    public int getSize() {
        return this.hasExtension() ? 18 : 9;
    }

    @Override
    public boolean shouldMoveToInventory(int pSlotIndex) {
        return pSlotIndex != this.getResultSlotIndex();
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot.hasItem()) {
            ItemStack itemStack1 = slot.getItem();
            itemStack = itemStack1.copy();
            if (pIndex == getResultSlotIndex()) {
                this.access.execute((p_39378_, p_39379_) -> {
                    itemStack1.getItem().onCraftedBy(itemStack1, p_39378_, pPlayer);
                });
                if (!this.moveItemStackTo(itemStack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemStack1, itemStack);
            } else if (pIndex >= this.getSize() && pIndex < 37 + this.getSize()) {
                if (!this.moveItemStackTo(itemStack1, 1, this.getSize(), false)) {
                    if (pIndex < 38) {
                        if (!this.moveItemStackTo(itemStack1, 29 + this.getSize(),
                                37 + this.getSize(), false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemStack1, this.getSize(), 29 + this.getSize(),
                            false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemStack1, this.getSize(), 37 + this.getSize(),
                    false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemStack1.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(pPlayer, itemStack1);
            if (pIndex == getResultSlotIndex()) {
                pPlayer.drop(itemStack1, false);
            }
        }

        return itemStack;
    }

    public void removed(@NotNull Player pPlayer) {
        super.removed(pPlayer);
        this.access.execute((p_39371_, p_39372_) -> {
            this.clearContainer(pPlayer, this.craftingSlots);
        });
    }

    public boolean canTakeItemForPickAll(@NotNull ItemStack pStack, Slot pSlot) {
        return pSlot.container != this.resultSlots && super.canTakeItemForPickAll(pStack, pSlot);
    }
}
