/**
 *         Minecraft Holiday Mod (WIP description)<br>
 *         Copyright (C) 2025  Minecraft Holiday Mod Team (WIP name)<br>
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

package holiday_mod.world.level.block;

import holiday_mod.world.inventory.SleighConstructionTableMenu;
import holiday_mod.world.level.block.state.properties.ModBlockStateProperties;
import holiday_mod.world.level.block.state.properties.SleighConstructionTableType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SleighConstructionTableBlock extends Block {
    private static final Component CONTAINER_TITLE =
            Component.translatable("container.holiday_mod.sleigh_construction_table");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<SleighConstructionTableType> TYPE =
            ModBlockStateProperties.SLEIGH_CONSTRUCTION_TABLE_TYPE;

    public SleighConstructionTableBlock(Properties pProperties) {
        super(pProperties);
    }

    @SuppressWarnings("deprecation")
    public @NotNull BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    @SuppressWarnings("deprecation")
    public @NotNull BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState()
                .setValue(FACING, pContext.getHorizontalDirection().getCounterClockWise())
                .setValue(TYPE,
                        (pContext.getLevel().getBlockState(pContext.getClickedPos().north()).getBlock()
                                instanceof SleighConstructionTableBlock)
                                || (pContext.getLevel().getBlockState(pContext.getClickedPos().east()).getBlock()
                                instanceof SleighConstructionTableBlock)
                                || (pContext.getLevel().getBlockState(pContext.getClickedPos().south()).getBlock()
                                instanceof SleighConstructionTableBlock)
                                || (pContext.getLevel().getBlockState(pContext.getClickedPos().west()).getBlock()
                                instanceof SleighConstructionTableBlock) ?
                                SleighConstructionTableType.EXTENSION : SleighConstructionTableType.MAIN
                        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING).add(TYPE);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull InteractionResult use(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos,
                                          @NotNull Player pPlayer, @NotNull InteractionHand pHand,
                                          @NotNull BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            // NetworkHooks.openScreen(((ServerPlayer)pPlayer), getMenuProvider(pState, pLevel, pPos));
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    /**
     * @return the Direction pointing from the given state to its attached table component
     */
    public static Direction getConnectedDirection(BlockState p_51585_) {
        Direction direction = p_51585_.getValue(FACING);
        return p_51585_.getValue(TYPE) == SleighConstructionTableType.MAIN ? direction.getClockWise() : direction.getCounterClockWise();
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    @SuppressWarnings("deprecation")
    public @NotNull BlockState updateShape(@NotNull BlockState pState,
                                           @NotNull Direction pFacing,
                                           BlockState pFacingState,
                                           @NotNull LevelAccessor pLevel,
                                           @NotNull BlockPos pCurrentPos,
                                           @NotNull BlockPos pFacingPos) {
        if (pFacingState.is(this) && pFacing.getAxis().isHorizontal()) {
            SleighConstructionTableType sleighConstructionTableType = pFacingState.getValue(TYPE);
            if (pState.getValue(TYPE) == SleighConstructionTableType.MAIN
                    && sleighConstructionTableType != SleighConstructionTableType.MAIN
                    && pState.getValue(FACING) == pFacingState.getValue(FACING)
                    && getConnectedDirection(pFacingState) == pFacing.getOpposite()) {
                return pState.setValue(TYPE, sleighConstructionTableType.getOpposite());
            }
        } else if (getConnectedDirection(pState) == pFacing) {
            return pState.setValue(TYPE, SleighConstructionTableType.MAIN);
        }

        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    private boolean hasExtension(BlockState pState, Level pLevel, BlockPos pPos) {
        return (pState.getValue(TYPE) == SleighConstructionTableType.EXTENSION) ||
                (pLevel.getBlockState(pPos.north()).getBlock() instanceof SleighConstructionTableBlock
                        && pLevel.getBlockState(pPos.north()).getValue(TYPE) == SleighConstructionTableType.EXTENSION) ||
                (pLevel.getBlockState(pPos.east()).getBlock() instanceof SleighConstructionTableBlock
                        && pLevel.getBlockState(pPos.east()).getValue(TYPE) == SleighConstructionTableType.EXTENSION) ||
                (pLevel.getBlockState(pPos.south()).getBlock() instanceof SleighConstructionTableBlock
                        && pLevel.getBlockState(pPos.south()).getValue(TYPE) == SleighConstructionTableType.EXTENSION) ||
                (pLevel.getBlockState(pPos.west()).getBlock() instanceof SleighConstructionTableBlock
                        && pLevel.getBlockState(pPos.west()).getValue(TYPE) == SleighConstructionTableType.EXTENSION);
    }

    @Nullable
    @SuppressWarnings("deprecation")
    public MenuProvider getMenuProvider(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos) {
        return new SimpleMenuProvider((windowId, inventory, player) ->
                new SleighConstructionTableMenu(windowId, inventory, null,
                        ContainerLevelAccess.create(pLevel, pPos), hasExtension(pState, pLevel, pPos)),
                CONTAINER_TITLE);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean skipRendering(@NotNull BlockState pState, BlockState pAdjacentBlockState,
                                 @NotNull Direction pSide) {
        return pAdjacentBlockState.is(this) || super.skipRendering(pState, pAdjacentBlockState, pSide);
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter level, BlockPos pos,
                                             FluidState fluidState)
    {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
    }
}
