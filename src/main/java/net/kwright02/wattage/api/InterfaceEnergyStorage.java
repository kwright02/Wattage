package net.kwright02.wattage.api;

public interface InterfaceEnergyStorage {

    int receiveEnergy(int amount, boolean simulate);
    int extractEnergy(int amount, boolean simulate);
    int getStoredEnergy();
    int getMaxEnergyStored();

}
