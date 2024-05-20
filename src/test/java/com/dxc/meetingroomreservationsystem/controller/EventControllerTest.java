package com.dxc.meetingroomreservationsystem.controller;

import com.dxc.meetingroomreservationsystem.pojo.BookingTable;
import com.dxc.meetingroomreservationsystem.pojo.EventBo;
import com.dxc.meetingroomreservationsystem.pojo.EventItem;
import com.dxc.meetingroomreservationsystem.service.EventService;
import com.dxc.meetingroomreservationsystem.utils.R;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEventByRange() {
        // Arrange
        String start = "2022-01-01";
        String end = "2022-01-31";
        List<EventItem> events = new ArrayList<>();
        when(eventService.getEventByRange(start, end)).thenReturn(events);

        // Act
        R<List<EventItem>> result = eventController.getEventByRange(start, end);

        // Assert
        assertEquals(events, result.getData());
        verify(eventService, times(1)).getEventByRange(start, end);
    }

    @Test
    public void testSaveEvent() {
        // Arrange
        EventBo event = new EventBo();
        when(eventService.saveEvent(event)).thenReturn(true);

        // Act
        R<Boolean> result = eventController.saveEvent(event);

        // Assert
        assertTrue(result.getData());
        assertEquals("Booking Success", result.getMsg());
        verify(eventService, times(1)).saveEvent(event);
    }

    @Test
    public void testUpdateEvent() {
        // Arrange
        EventBo event = new EventBo();
        when(eventService.updateEvent(event)).thenReturn(true);

        // Act
        R<Boolean> result = eventController.updateEvent(event);

        // Assert
        assertTrue(result.getData());
        assertEquals("Update Success", result.getMsg());
        verify(eventService, times(1)).updateEvent(event);
    }

    @Test
    public void testGetEventByUser() {
        // Arrange
        Integer userId = 1;
        String location = "Location";
        String roomNumber = "RoomNumber";
        List<BookingTable> eventByUser = new ArrayList<>();
        when(eventService.getEventByUser(userId, location, roomNumber)).thenReturn(eventByUser);

        // Act
        R<List<BookingTable>> result = eventController.getEventByUser(userId, location, roomNumber);

        // Assert
        assertEquals(eventByUser, result.getData());
        verify(eventService, times(1)).getEventByUser(userId, location, roomNumber);
    }

    @Test
    public void testDeleteByGroupId() {
        // Arrange
        String groupId = "groupId";
        when(eventService.deleteByGroupId(groupId)).thenReturn(true);

        // Act
        R<Boolean> result = eventController.deleteByGroupId(groupId);

        // Assert
        assertTrue(result.getData());
        assertEquals("delete success", result.getMsg());
        verify(eventService, times(1)).deleteByGroupId(groupId);
    }
}