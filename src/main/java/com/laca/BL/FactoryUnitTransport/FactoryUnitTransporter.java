package com.laca.BL.FactoryUnitTransport;

import com.laca.Interfaces.IConstructUser;
import com.laca.UnitTrasportImplementation.Motorcycle;
import com.laca.UnitTrasportImplementation.Truck;
import com.laca.UnitTrasportImplementation.Van;
import com.laca.UnitTrasportImplementation.Walk;
import com.laca.entity.PackageUnitAbstract.UnitTransporterAbstract;
import com.laca.entity.concretUsers.*;

public class FactoryUnitTransporter {
    public static UnitTransporterAbstract createUnitTransport(UnitTransporterAbstract unitTransporterAbstract) {
        if ("Walk".equalsIgnoreCase( unitTransporterAbstract.getType())) {
            return new Walk(unitTransporterAbstract.getName(),unitTransporterAbstract.getPlate(),unitTransporterAbstract.getHigh(),unitTransporterAbstract.getWidth(),unitTransporterAbstract.getType(),unitTransporterAbstract.getMaxWeight(),unitTransporterAbstract.getIsActive());
        } else if ("MotorCycle".equalsIgnoreCase(unitTransporterAbstract.getType())) {
            return new Motorcycle(unitTransporterAbstract.getName(),unitTransporterAbstract.getPlate(),unitTransporterAbstract.getHigh(),unitTransporterAbstract.getWidth(),unitTransporterAbstract.getType(),unitTransporterAbstract.getMaxWeight(),unitTransporterAbstract.getIsActive());
        }else if ("Truck".equalsIgnoreCase(unitTransporterAbstract.getType())) {
            return new Truck(unitTransporterAbstract.getName(),unitTransporterAbstract.getPlate(),unitTransporterAbstract.getHigh(),unitTransporterAbstract.getWidth(),unitTransporterAbstract.getType(),unitTransporterAbstract.getMaxWeight(),unitTransporterAbstract.getIsActive());
        }else if ("Van".equalsIgnoreCase(unitTransporterAbstract.getType())) {
            System.out.println("hola");
            return new Van(unitTransporterAbstract.getName(),unitTransporterAbstract.getPlate(),unitTransporterAbstract.getHigh(),unitTransporterAbstract.getWidth(),unitTransporterAbstract.getType(),unitTransporterAbstract.getMaxWeight(),unitTransporterAbstract.getIsActive());
        }
        throw new IllegalArgumentException("Invalid pastry type: " + unitTransporterAbstract.getType());
    }
}
