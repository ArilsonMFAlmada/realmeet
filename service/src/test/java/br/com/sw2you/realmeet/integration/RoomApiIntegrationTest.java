package br.com.sw2you.realmeet.integration;

import br.com.sw2you.realmeet.api.facade.RoomApi;
import br.com.sw2you.realmeet.core.BaseIntegrationTest;
import br.com.sw2you.realmeet.domain.repository.RoomRepository;
import br.com.sw2you.realmeet.utils.TestConstants;
import br.com.sw2you.realmeet.utils.TestDataCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;

import static br.com.sw2you.realmeet.utils.TestDataCreator.newRoomBuilder;
import static org.junit.jupiter.api.Assertions.*;

public class RoomApiIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private RoomApi api;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    protected void setupEach() throws Exception {
        setLocalHostBasePath(api.getApiClient(), "/v1");
    }

    @Test
    public void testGetRoomSuccess() {

        var room = newRoomBuilder().build();

        roomRepository.saveAndFlush(room);

        assertNotNull(room.getId());
        assertTrue(room.getActive());

        var dto = api.getRoom(room.getId());

        assertEquals(room.getId(), dto.getId());
        assertEquals(room.getName(), dto.getName());
        assertEquals(room.getSeats(), dto.getSeats());
    }

    @Test
    void testGetRoomInactive() {

        var room = TestDataCreator.newRoomBuilder().withActive(false).build();

        assertFalse(room.getActive());

        assertThrows(HttpClientErrorException.class,() -> api.getRoom(room.getId()));
    }

    @Test
    void testGetRoomDoesNotExist() {

        assertThrows(HttpClientErrorException.class,() -> api.getRoom(TestConstants.DEFAULT_ROOM_ID));
    }
}
