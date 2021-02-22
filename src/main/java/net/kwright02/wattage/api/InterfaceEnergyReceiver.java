package net.kwright02.wattage.api;

import net.minecraft.util.math.Direction;

public interface InterfaceEnergyReceiver extends InterfaceEnergyHandler {

    int receiveEnergy(Direction facing, int amount, boolean simulate);

}
