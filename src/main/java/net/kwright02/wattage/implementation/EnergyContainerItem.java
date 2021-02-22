package net.kwright02.wattage.implementation;

import net.kwright02.wattage.api.InterfaceEnergyContainerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class EnergyContainerItem extends Item implements InterfaceEnergyContainerItem {

    private static String ENERGY = "Energy";

    protected int capacity, maxReceive, maxExtract;

    public EnergyContainerItem() {
        super(null);
    }

    public EnergyContainerItem(int capacity) {
        this(capacity, capacity, capacity);
    }

    public EnergyContainerItem(int capacity, int maxTransfer) {
        this(capacity, maxTransfer, maxTransfer);
    }

    public EnergyContainerItem(int capacity, int maxReceive, int maxExtract) {
        super(null);
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    public EnergyContainerItem setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public EnergyContainerItem setMaxTransfer(int maxTransfer) {
        this.setMaxReceive(maxTransfer);
        this.setMaxExtract(maxTransfer);
        return this;
    }

    public EnergyContainerItem setMaxReceive(int maxReceive) {
        this.maxReceive = maxReceive;
        return this;
    }

    public EnergyContainerItem setMaxExtract(int maxExtract) {
        this.maxExtract = maxExtract;
        return this;
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if (!container.hasTag()) {
            container.setTag(new CompoundTag());
        }
        int stored = Math.min(container.getTag().getInt(ENERGY), this.getMaxEnergyStored(container));
        int energyReceived = Math.min(this.capacity - stored, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            container.getTag().putInt(ENERGY, stored += energyReceived);
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        if (container.getTag() == null || !container.getTag().contains(ENERGY)) {
            return 0;
        }
        int stored = Math.min(container.getTag().getInt(ENERGY), this.getMaxEnergyStored(container));
        int energyExtracted = Math.min(stored, Math.min(this.maxExtract, maxExtract));
        if (!simulate) {
            container.getTag().putInt(ENERGY, stored -= energyExtracted);
        }
        return energyExtracted;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        if (container.getTag() == null || !container.getTag().contains(ENERGY)) {
            return 0;
        }
        return Math.min(container.getTag().getInt(ENERGY), this.getMaxEnergyStored(container));
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return this.capacity;
    }

}
