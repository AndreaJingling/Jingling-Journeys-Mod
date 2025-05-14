package holiday_mod.world.level.block.entity;

import holiday_mod.world.item.crafting.SleighConstructionTableRecipe;
import holiday_mod.world.inventory.SleighConstructionTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SleighConstructionTableBlockEntity extends BlockEntity implements MenuProvider {
    // ITEMS & INVENTORY

    private final ItemStackHandler itemHandler = new ItemStackHandler(19) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return slot != 0 && super.isItemValid(slot, stack);
        }
    };

    private static final int OUTPUT_SLOT = 0;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;
    @SuppressWarnings("unused")
    private static ItemLike output;

    public void drops() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }

        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public SleighConstructionTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SLEIGH_CONSTRUCTION_TABLE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override

            public int get(int i) {
                return 0;
            }
            @Override

            public void set(int i, int i1) {

            }
            @Override

            public int getCount() {
                return 0;
            }
        };
    }

    // MISCELLANEOUS & SAVING

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("container.holiday_mod.sleigh_construction_table");
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return cap == ForgeCapabilities.ITEM_HANDLER ? lazyItemHandler.cast() : super.getCapability(cap);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("inv", itemHandler.serializeNBT());
        tag.putBoolean("hasCrafted", hasCrafted);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        hasCrafted = tag.getBoolean("hasCrafted");
    }

    // SCREEN & PROCESSING

    @Override
    public @Nullable AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player) {
        return new SleighConstructionTableMenu(id, inventory, this, this.data);
    }

    private boolean hasCrafted = false;

    @SuppressWarnings("unused")
    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide) {
            return;
        }

        SimpleContainer container = new SimpleContainer(18);
        for (int i = 1; i < 18; i++) {
            container.setItem(i, itemHandler.getStackInSlot(i + 1));
        }

        Optional<SleighConstructionTableRecipe> recipeOptional = level.getRecipeManager()
                .getRecipeFor(SleighConstructionTableRecipe.Type.INSTANCE, container, level);

        if (recipeOptional.isPresent()) {
            SleighConstructionTableRecipe recipe = recipeOptional.get();


            if (itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() && !hasCrafted) {
                assert getLevel() != null;
                itemHandler.setStackInSlot(OUTPUT_SLOT, recipe.getResultItem(getLevel().registryAccess()).copy());
                hasCrafted = true;
            }
        } else {
            if (!itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
                itemHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
            }
            hasCrafted = false;
        }

        if (hasCrafted && itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
            for (int i = 1; i < 19; i++) {
                itemHandler.extractItem(i, 1, false);
            }
            hasCrafted = false;
        }

        level.sendBlockUpdated(worldPosition, state, state, Block.UPDATE_ALL);
    }
}
