package br.com.sw2you.realmeet.unit;

import br.com.sw2you.realmeet.domain.entity.Room;
import br.com.sw2you.realmeet.mapper.RoomMapper;
import br.com.sw2you.realmeet.utils.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RoomMapperUnitTest {

    private RoomMapper victim;

    @BeforeEach
    void setupEach() {
        victim = MapperUtils.roomMapper();
    }

    @Test
    void testFromEntityToDto() {
        var room = Room.newBuilder().withId(1L).withName("Room A").withSeats(6).build();
        var dto = victim.fromEntityToDto(room);

        assertEquals(room.getId(), dto.getId());
        assertEquals(room.getName(), dto.getName());
        assertEquals(room.getSeats(), dto.getSeats());
    }
}
