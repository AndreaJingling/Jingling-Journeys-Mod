/**
 *         Jingling Journeys (WIP description)<br>
 *         Copyright (C) 2025  Jingling Journeys Team (WIP name)<br>
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

package jingling_journeys.world.level.block;

import jingling_journeys.world.level.block.state.properties.ChimneyType;
import jingling_journeys.world.level.block.state.properties.ModBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class ChimneyBlock extends Block {
    public static final EnumProperty<ChimneyType> TYPE =
            ModBlockStateProperties.CHIMNEY_TYPE;

    public ChimneyBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        ChimneyType chimneyType = ChimneyType.TOP;
        BlockState stateBelow = pContext.getLevel().getBlockState(pContext.getClickedPos().below());
        BlockState stateAbove = pContext.getLevel().getBlockState(pContext.getClickedPos().above());

        if (stateBelow.getBlock() instanceof ChimneyBlock
                && stateBelow.getValue(TYPE) == ChimneyType.TOP)
        {
            pContext.getLevel().scheduleTick(pContext.getClickedPos().below(),
                    stateBelow.getBlock(), 1);
        }

        if (stateAbove.getBlock() instanceof ChimneyBlock)
        {
            chimneyType = ChimneyType.CONNECTION;
        }

        return this.defaultBlockState().setValue(TYPE, chimneyType);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(TYPE);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick(@NotNull BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos,
                     @NotNull RandomSource pRandom) {
        BlockState stateAbove = pLevel.getBlockState(pPos.above());

        if (stateAbove.getBlock() instanceof ChimneyBlock
                && stateAbove.getValue(TYPE) == ChimneyType.TOP
                && pState.getValue(TYPE) != ChimneyType.CONNECTION)
        {
            pLevel.setBlock(pPos, pState.setValue(TYPE, ChimneyType.CONNECTION), 2);
        }
        super.tick(pState, pLevel, pPos, pRandom);
    }


    /**
     * @return the Direction pointing from the given state to its attached table component
     */
    public static Direction getConnectedDirection(BlockState state) {
        return state.getValue(TYPE) == ChimneyType.TOP ? Direction.DOWN : Direction.UP;
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
                                           @NotNull BlockState pFacingState,
                                           @NotNull LevelAccessor pLevel,
                                           @NotNull BlockPos pCurrentPos,
                                           @NotNull BlockPos pFacingPos) {
        if (pState.getValue(TYPE) == ChimneyType.CONNECTION && pFacing == Direction.UP &&
                (!(pFacingState.getBlock() instanceof ChimneyBlock) ||
                (pFacingState.getValue(TYPE) != ChimneyType.TOP))) {
            return pState.setValue(TYPE, ChimneyType.TOP);
        }
        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @SuppressWarnings("deprecation")
    public @NotNull InteractionResult use(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos,
                                          @NotNull Player pPlayer, @NotNull InteractionHand pHand,
                                          @NotNull BlockHitResult pHit) {
        if (pState.getValue(TYPE) == ChimneyType.TOP)
        {
            return InteractionResult.PASS; // TODO Implement Crawling in Chimney and Fireplace
        } else {
            return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        }
    }
}
