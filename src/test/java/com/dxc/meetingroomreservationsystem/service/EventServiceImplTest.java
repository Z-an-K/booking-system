package com.dxc.meetingroomreservationsystem.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.dxc.meetingroomreservationsystem.mapper.EventMapper;
import com.dxc.meetingroomreservationsystem.pojo.*;
import com.dxc.meetingroomreservationsystem.service.RoomService;
import com.dxc.meetingroomreservationsystem.service.UserService;
import com.dxc.meetingroomreservationsystem.service.impl.EventServiceImpl;
import com.dxc.meetingroomreservationsystem.service.impl.eventSaveStrategy.EventSaveStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EventServiceImplTest {

    @Mock
    private EventMapper eventMapper;

    @Mock
    private EventSaveStrategyFactory factory;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEventByRange() {
        // Arrange
        String start = "2022-01-01";
        String end = "2022-01-31";
        List<EventDto> eventsByRange = new ArrayList<>();
        eventsByRange.add(new EventDto());
        when(eventMapper.getEventsByRange(start, end)).thenReturn(eventsByRange);

        // Act
        List<EventItem> events = eventService.getEventByRange(start, end);

        // Assert
        assertNotNull(events);
        assertEquals(1, events.size());
    }

    @Test
    @Transactional
    public void testSaveEvent() {
        // Arrange
        EventBo event = new EventBo();
        when(eventService.saveOrUpdateEvent(event)).thenReturn(true);

        // Act
        boolean result = eventService.saveEvent(event);

        // Assert
        assertTrue(result);
    }

    @Test
    @Transactional
    public void testUpdateEvent() {
        // Arrange
        EventBo eventBo = new EventBo();
        List<Integer> ids = new ArrayList<>();
        when(eventMapper.getEventIdByGroupId(eventBo.getGroupId())).thenReturn(ids);
        when(eventService.saveOrUpdateEvent(eventBo)).thenReturn(true);

        // Act
        boolean result = eventService.updateEvent(eventBo);

        // Assert
        assertTrue(result);
        verify(eventService, times(1)).removeBatchByIds(ids);
        verify(eventMapper, times(1)).deleteByEventIds(ids);
    }

    @Test
    public void testGetEventByUser() {
        // Arrange
        int userId = 1;
        String location = "Location";
        String roomNumber = "RoomNumber";
        List<EventDto> events = new ArrayList<>();
        events.add(new EventDto());
        when(eventMapper.getEventByUser(userId, location, roomNumber)).thenReturn(events);

        // Act
        List<BookingTable> bookingTables = eventService.getEventByUser(userId, location, roomNumber);

        // Assert
        assertNotNull(bookingTables);
        assertEquals(1, bookingTables.size());
    }

    @Test
    @Transactional
    public void testDeleteByGroupId() {
        // Arrange
        String groupId = "groupId";
        List<Event> events = new ArrayList<>();
        events.add(new Event());
        when(eventService.list(any(Wrapper.class))).thenReturn(events);
        when(eventMapper.deleteByEventIds(any())).thenReturn(1); // Fix: Change `thenReturn(true)` to `thenReturn(1)`
        when(eventService.remove(any())).thenReturn(true);

        // Act
        boolean result = eventService.deleteByGroupId(groupId);

        // Assert
        assertTrue(result);
        verify(eventService, times(1)).remove(any());
    }
}