package br.com.sw2you.realmeet.utils;

import br.com.sw2you.realmeet.domain.entity.Room;

import static br.com.sw2you.realmeet.utils.TestConstants.*;

public class TestDataCreator {

    private TestDataCreator() {

    }

public static Room.Builder newRoomBuilder () {
        return Room.newBuilder().withName(DEFAULT_ROOM_NAME).withSeats(DEFAULT_ROOM_SEATS);
    }
}
