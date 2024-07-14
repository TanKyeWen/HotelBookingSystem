package BookingSystem;

public class Room {
    // Set arbitrary numbers for the rooms available
    private int vip_room, deluxe_room, standard_room;

    public Room(int vip_room, int deluxe_room, int standard_room) {
        this.vip_room = vip_room;
        this.deluxe_room = deluxe_room;
        this.standard_room = standard_room;
    }

    // check room availability and book room
    public boolean checkRoom(String room_type) throws IllegalArgumentException {
        try {
            if (room_type.equalsIgnoreCase("vip")) {
                if (vip_room > 0) {
                    return true;
                } else {
                    throw new IllegalArgumentException(); //"No VIP rooms available."
                }
            } else if (room_type.equalsIgnoreCase("deluxe")) {
                if (deluxe_room > 0) {
                    return true;
                } else {
                    throw new IllegalArgumentException(); //"No Deluxe rooms available.
                }
            } else if (room_type.equalsIgnoreCase("standard")) {
                if (standard_room > 0) {
                    return true;
                } else {
                    throw new IllegalArgumentException(); //"No Standard rooms available."
                }
            }
            throw new IllegalArgumentException(); //"Room type does not exist."
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    // Working in progress
}
