package com.laca.entity.PackageUnitAbstract.UnitTrasportImplementation;


import com.laca.entity.PackageUnitAbstract.UnitTransporterAbstract;

public class Van extends UnitTransporterAbstract {

    public Van() {
        this.setType("Van");
        this.setMaxWeight(60);
    }

    public Van(String name, String plate, double high, double width, String type, double maxWeight,boolean isActive) {
        super(name, plate, high, width, type, maxWeight,isActive);
        this.setType("Truck");
        this.setMaxWeight(60);
    }
}
