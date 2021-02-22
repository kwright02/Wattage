package net.kwright02.wattage.implementation;

import net.kwright02.wattage.api.InterfaceEnergyProvider;
import net.kwright02.wattage.api.InterfaceEnergyReceiver;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;

public class TileEnergyHandler extends BlockEntity implements InterfaceEnergyReceiver, InterfaceEnergyProvider {

    protected EnergyStorage storage = new EnergyStorage(32000);

    private TileEnergyHandler(BlockEntityType<?> type) {
        super(type);
    }

    @Override
    public void fromTag(BlockState state, CompoundTag nbt) {
        super.fromTag(state, nbt);
        this.storage.readFromNBT(nbt);
    }

    @Override
    public CompoundTag toTag(CompoundTag nbt) {
        super.toTag(nbt);
        this.storage.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public boolean isConnectable(Direction from) {
        return true;
    }

    @Override
    public int receiveEnergy(Direction from, int maxReceive, boolean simulate) {
        return this.storage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(Direction from, int maxExtract, boolean simulate) {
        return this.storage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getStoredEnergy(Direction from) {
        return this.storage.getStoredEnergy();
    }

    @Override
    public int getMaxEnergyStored(Direction from) {
        return this.storage.getMaxEnergyStored();
    }
}