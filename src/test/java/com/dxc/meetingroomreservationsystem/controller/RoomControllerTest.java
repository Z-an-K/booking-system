package com.dxc.meetingroomreservationsystem.controller;

import com.dxc.meetingroomreservationsystem.pojo.Room;
import com.dxc.meetingroomreservationsystem.service.RoomService;
import com.dxc.meetingroomreservationsystem.utils.R;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RoomControllerTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRooms() {
        // Arrange
        List<Room> rooms = Arrays.asList(new Room(), new Room());
        when(roomService.list()).thenReturn(rooms);

        // Act
        R<List<Room>> result = roomController.getRooms(any());

        // Assert
        assertEquals(R.createBySuccess(rooms), result);
        verify(roomService, times(1)).list();
    }

    @Test
    public void testSaveRoom() {
        // Arrange
        Room room = new Room();
        when(roomService.save(room)).thenReturn(true);

        // Act
        R result = roomController.saveRoom(room);

        // Assert
        assertEquals(R.createBySuccess(), result);
        verify(roomService, times(1)).save(room);
    }

    @Test
    public void testUpdateRoom() {
        // Arrange
        Room room = new Room();
        when(roomService.updateById(room)).thenReturn(true);

        // Act
        R result = roomController.updateRoom(room);

        // Assert
        assertEquals(R.createBySuccess(), result);
        verify(roomService, times(1)).updateById(room);
    }

    @Test
    public void testDelRoom() {
        // Arrange
        List<Integer> ids = Arrays.asList(1, 2, 3);
        when(roomService.removeBatchByIds(ids)).thenReturn(true);

        // Act
        R result = roomController.delRoom(ids);

        // Assert
        assertEquals(R.createBySuccess(), result);
        verify(roomService, times(1)).removeBatchByIds(ids);
    }
}