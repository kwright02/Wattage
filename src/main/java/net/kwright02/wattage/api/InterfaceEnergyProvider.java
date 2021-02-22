package net.kwright02.wattage.api;

import net.minecraft.util.math.Direction;

public interface InterfaceEnergyProvider extends InterfaceEnergyHandler {

    int extractEnergy(Direction facing, int amount, boolean simulate);

}
