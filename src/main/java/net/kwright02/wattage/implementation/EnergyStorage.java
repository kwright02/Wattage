package net.kwright02.wattage.implementation;

import net.kwright02.wattage.api.InterfaceEnergyStorage;
import net.minecraft.nbt.CompoundTag;

public class EnergyStorage implements InterfaceEnergyStorage {

    protected int energy, capacity, maxReceive, maxExtract;

    public EnergyStorage(int capacity) {
        this(capacity, capacity, capacity);
    }

    public EnergyStorage(int capacity, int maxTransfer) {
        this(capacity, maxTransfer, maxTransfer);
    }

    public EnergyStorage(int capacity, int maxReceive, int maxExtract) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    public EnergyStorage readFromNBT(CompoundTag nbt) {
        this.energy = nbt.getInt("Energy");
        if (this.energy > this.capacity) {
            this.energy = this.capacity;
        }
        return this;
    }

    public CompoundTag writeToNBT(CompoundTag nbt) {
        if (this.energy < 0) {
            this.energy = 0;
        }
        nbt.putInt("Energy", this.energy);
        return nbt;
    }

    public EnergyStorage setCapacity(int capacity) {
        this.capacity = capacity;
        if (this.energy > capacity) {
            this.energy = capacity;
        }
        return this;
    }

    public EnergyStorage setMaxTransfer(int maxTransfer) {
        this.setMaxReceive(maxTransfer);
        this.setMaxExtract(maxTransfer);
        return this;
    }

    public EnergyStorage setMaxReceive(int maxReceive) {
        this.maxReceive = maxReceive;
        return this;
    }

    public EnergyStorage setMaxExtract(int maxExtract) {
        this.maxExtract = maxExtract;
        return this;
    }

    public void setEnergyStored(int energy) {
        this.energy = energy;
        if (this.energy > this.capacity) {
            this.energy = this.capacity;
        } else if (this.energy < 0) {
            this.energy = 0;
        }
    }

    public void modifyEnergyStored(int energy) {
        this.energy += energy;
        if (this.energy > this.capacity) {
            this.energy = this.capacity;
        } else if (this.energy < 0) {
            this.energy = 0;
        }
    }

    @Override
    public int receiveEnergy(int amount, boolean simulate) {
        int energyReceived = Math.min(this.capacity - this.energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            this.energy += energyReceived;
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(int amount, boolean simulate) {
        int energyExtracted = Math.min(this.energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate) {
            this.energy -= energyExtracted;
        }
        return energyExtracted;
    }

    public int getMaxReceive() {
        return this.maxReceive;
    }

    public int getMaxExtract() {
        return this.maxExtract;
    }

    @Override
    public int getStoredEnergy() {
        return energy;
    }

    @Override
    public int getMaxEnergyStored() {
        return capacity;
    }
}
