package com.pobluesky.lineitem.dto.request.car;


import com.pobluesky.lineitem.entity.type.car.IxPlate;
import com.pobluesky.lineitem.entity.type.car.Kind;
import com.pobluesky.lineitem.entity.type.car.Lab;
import com.pobluesky.lineitem.entity.type.car.StandardOrg;
import java.time.LocalDate;

public record CarLineItemUpdateRequestDTO(
    Lab lab,
    Kind kind,
    StandardOrg standardOrg,
    String salesVehicleName,
    String partName,
    IxPlate ixPlate,
    String thickness,
    String width,
    Integer quantity,
    LocalDate expectedDeliveryDate,
    String transportationDestination,
    String orderEdge,
    String tolerance,
    String annualCost
) {
}
