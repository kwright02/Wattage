package net.kwright02.wattage.api;

import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.Direction;

public interface InterfaceEnergyConnection {

   boolean isConnectable(Direction facing);

}
