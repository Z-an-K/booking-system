package com.dxc.meetingroomreservationsystem.service.impl.eventSaveStrategy;

import com.dxc.meetingroomreservationsystem.pojo.EventBo;

public interface EventSaveStrategy {

    boolean saveEvent(EventBo event);
}
