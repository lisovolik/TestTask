package ua.lisovolik.model;

/**
 * Created by Alexandr Lisovolik on  26.09.2024
 */

public class ServiceType {
    private final int serviceId;
    private final int variationId;

    public ServiceType(int serviceId, int variationId) {
        this.serviceId = serviceId;
        this.variationId = variationId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public int getVariationId() {
        return variationId;
    }
}
