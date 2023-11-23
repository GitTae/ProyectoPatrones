package com.laca.BL.FactoryUsers;


import com.laca.Interfaces.IConstructUser;
import com.laca.entity.concretUsers.*;

public class FactoryUserss {

    public static IConstructUser createUser(String type) {
        if ("AdminUser".equalsIgnoreCase(type)) {
            return new AdminUser();
        } else if ("ClientUser".equalsIgnoreCase(type)) {
            return new ClientUser();
        }else if ("RutesUser".equalsIgnoreCase(type)) {
            return new RutesUser();
        }else if ("TransportUser".equalsIgnoreCase(type)) {
            return new TransportUser();
        }else if ("VisualizatorPackagesInProgress".equalsIgnoreCase(type)) {
            return new VisualizatorPackagesInProgress();
        }
        throw new IllegalArgumentException("Invalid pastry type: " + type);
    }
}
