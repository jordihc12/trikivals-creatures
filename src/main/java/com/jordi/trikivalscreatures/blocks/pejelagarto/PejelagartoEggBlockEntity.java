package com.jordi.trikivalscreatures.blocks.pejelagarto;

import com.jordi.trikivalscreatures.blocks.ModBlockEntities;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoVariant;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PejelagartoEggBlockEntity extends BlockEntity {
    private final List<PejelagartoVariant> eggData = new ArrayList<>(4);

    public PejelagartoEggBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PEJELAGARTO_EGG_BE, pos, state);
    }

    public void addEgg(PejelagartoVariant variant) {
        if(this.eggData.size() < 4) {
            this.eggData.add(variant);
            this.markDirty();

            BlockState old = this.getCachedState();
            BlockState newState = old.with(PejelagartoEggBlock.EGGS, this.eggData.size());
            this.world.setBlockState(this.pos, newState, 3);
        }
    }

    public PejelagartoVariant removeEgg() {
        PejelagartoVariant variant = eggData.remove(eggData.size() - 1);
        this.markDirty();
        BlockState old = this.getCachedState();
        BlockState newState = old.with(PejelagartoEggBlock.EGGS, this.eggData.size());
        this.world.setBlockState(this.pos, newState, 3);

        return variant;
    }

    public List<PejelagartoVariant> getEggs() {
        return eggData;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        NbtList list = nbt.getList("Eggs", NbtElement.COMPOUND_TYPE);
        this.eggData.clear();
        for (NbtElement element : list) {
            this.eggData.add(PejelagartoVariant.byId(
                    ((NbtCompound) element).getInt("variant")));
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        NbtList list = new NbtList();
        for (PejelagartoVariant variant : this.eggData) {
            NbtCompound nbtCompound = new NbtCompound();
            nbtCompound.putInt("variant", variant.getId());

            list.add(nbtCompound);
        }
        nbt.put("Eggs", list);
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
