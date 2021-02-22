package net.kwright02.wattage.api;

import net.minecraft.util.math.Direction;

public interface InterfaceEnergyHandler extends InterfaceEnergyConnection {

    int getStoredEnergy(Direction facing);

    int getMaxEnergyStored(Direction facing);

}
