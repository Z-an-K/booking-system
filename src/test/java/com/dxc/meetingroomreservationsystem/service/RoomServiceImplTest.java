package com.dxc.meetingroomreservationsystem.service;

import com.dxc.meetingroomreservationsystem.mapper.RoomMapper;
import com.dxc.meetingroomreservationsystem.pojo.Room;
import com.dxc.meetingroomreservationsystem.service.impl.RoomServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RoomServiceImplTest {

    @Mock
    private RoomMapper roomMapper;

    @InjectMocks
    private RoomServiceImpl roomService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRoomByNumber() {
        // Arrange
        String roomNumber = "123";
        Room room = new Room();
        when(roomMapper.selectOne(any())).thenReturn(room);

        // Act
        Room result = roomService.getRoomByNumber(roomNumber);

        // Assert
        assertEquals(room, result);
        verify(roomMapper, times(1)).selectOne(any());
    }
}