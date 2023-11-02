package com.st6.ChristmasFlair.models;

import com.st6.ChristmasFlair.models.DeviceStatus.Status;

public interface Device {
    boolean execute(DeviceRequest deviceRequest);
    String getDeviceName();
    Status getStatus();
    void notifyObservers(Status status);
    void registerObserver(Observer observer);
    void setStatus(Status status);
    void unregisterObserver(Observer observer);
}
