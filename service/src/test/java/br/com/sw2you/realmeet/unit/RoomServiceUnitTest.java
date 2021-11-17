package br.com.sw2you.realmeet.unit;

import br.com.sw2you.realmeet.core.BaseUnitTest;
import br.com.sw2you.realmeet.domain.repository.RoomRepository;
import br.com.sw2you.realmeet.exception.RoomNotFoundException;
import br.com.sw2you.realmeet.service.RoomService;
import br.com.sw2you.realmeet.utils.TestConstants;
import br.com.sw2you.realmeet.utils.TestDataCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.sw2you.realmeet.utils.MapperUtils.roomMapper;
import static br.com.sw2you.realmeet.utils.TestConstants.DEFAULT_ROOM_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RoomServiceUnitTest extends BaseUnitTest {

    private RoomService victim;

    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    void setupEach() {
        victim = new RoomService(roomRepository, roomMapper());
    }

    @Test
    void testGetRoomSuccess() {

        var room = TestDataCreator.newRoomBuilder().withId(DEFAULT_ROOM_ID).build();

        when(roomRepository.findByIdAndActive(DEFAULT_ROOM_ID, true)).thenReturn(Optional.ofNullable(room));

        var dto = victim.getRoom(DEFAULT_ROOM_ID);

        assertEquals(room.getId(), dto.getId());
        assertEquals(room.getName(), dto.getName());
        assertEquals(room.getSeats(), dto.getSeats());
    }

    @Test
    void testGetRoomNotFound() {

        var room = TestDataCreator.newRoomBuilder().withId(DEFAULT_ROOM_ID).build();

        when(roomRepository.findByIdAndActive(DEFAULT_ROOM_ID, true)).thenReturn(Optional.empty());

        Assertions.assertThrows(RoomNotFoundException.class, () -> victim.getRoom(DEFAULT_ROOM_ID));
    }
}