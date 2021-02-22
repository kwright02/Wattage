package net.kwright02.wattage.api;

import net.minecraft.item.ItemStack;

public interface InterfaceEnergyContainerItem {

    int receiveEnergy(ItemStack var1, int amount, boolean simulate);

    int extractEnergy(ItemStack var1, int amount, boolean simulate);

    int getEnergyStored(ItemStack var1);

    int getMaxEnergyStored(ItemStack var1);

}
