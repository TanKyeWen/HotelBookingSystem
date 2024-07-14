package BookingSystem;

import java.util.ArrayList;

public class Booking {
    Room room;
    WaitingList waitingList;
    ArrayList<ArrayList<String>> room_booked;

    // set booking
    public boolean setBooking(User user) throws IllegalArgumentException {
        try {
            int count = 0;
            for (ArrayList<String> sublist : room_booked) {
                if (sublist.get(0).equals(user.getName())) {
                    count++;
                    if (count >= user.getMaxRoomBook()) {
                        throw new IllegalArgumentException(); //"User has already reached the maximum number of room bookings."
                    }
                }
            }

            ArrayList<String> booking_info = new ArrayList<>();
            switch (user.getMemberType().toLowerCase()) {
                case "vip":
                    if (room.checkRoom("vip")) {
                        booking_info.add(user.getName());
                        booking_info.add("vip");
                        waitingList.addWaiting(user);
                        room_booked.add(booking_info);
                        user.setExcelReward(false);
                        return true;
                    } else if (room.checkRoom("deluxe")) {
                        booking_info.add(user.getName());
                        booking_info.add("deluxe");
                        waitingList.addWaiting(user);
                        room_booked.add(booking_info);
                        return true;
                    } else if (room.checkRoom("standard")) {
                        booking_info.add(user.getName());
                        booking_info.add("standard");
                        waitingList.addWaiting(user);
                        room_booked.add(booking_info);
                        return true;
                    } else {
                        waitingList.addWaiting(user);
                        return false;
                    }
                case "member":
                    if (user.getExclReward()) {
                        if (room.checkRoom("vip")) {
                            booking_info.add(user.getName());
                            booking_info.add("vip");
                            user.setExcelReward(false);
                           
                            room_booked.add(booking_info);
                            return true;
                        }
                    } else if (room.checkRoom("deluxe")) {
                        booking_info.add(user.getName());
                        booking_info.add("deluxe");
                        
                        room_booked.add(booking_info);
                        return true;
                    } else if (room.checkRoom("standard")) {
                        booking_info.add(user.getName());
                        booking_info.add("standard");
                        
                        room_booked.add(booking_info);
                        return true;
                    } else {
                        waitingList.addWaiting(user);
                        return false;
                    }

                case "non_member":
                    if (room.checkRoom("standard")) {
                        booking_info.add(user.getName());
                        booking_info.add("standard");
          
                        room_booked.add(booking_info);
                        return true;
                    } else {
                        waitingList.addWaiting(user);
                        return false;
                    }
                default:
                    throw new IllegalArgumentException();
            }
        } catch (Exception e) {
        	 throw new IllegalArgumentException();
        }
    }

    // Method Overloading with an extra String parameter
    public boolean setBooking(User user, String roomType) throws IllegalArgumentException {
        try {
            int count = 0;
            for (ArrayList<String> sublist : room_booked) {
                if (sublist.get(0).equals(user.getName())) {
                    count++;
                    if (count >= user.getMaxRoomBook()) {
                        throw new IllegalArgumentException();//"User has already reached the maximum number of room bookings.
                    }
                }
            }

            ArrayList<String> booking_info = new ArrayList<>();
            switch (user.getMemberType().toLowerCase()) {
                case "vip":
                    if (room.checkRoom("vip") && roomType.equals("vip")) {
                        booking_info.add(user.getName());
                        booking_info.add("vip");
                        waitingList.addWaiting(user);
                        room_booked.add(booking_info);
                        return true;
                    } else if (room.checkRoom("deluxe") && roomType.equals("deluxe")) {
                        booking_info.add(user.getName());
                        booking_info.add("deluxe");
                        waitingList.addWaiting(user);
                        room_booked.add(booking_info);
                        return true;
                    } else if (room.checkRoom("standard") && roomType.equals("standard")) {
                        booking_info.add(user.getName());
                        booking_info.add("standard");
                        waitingList.addWaiting(user);
                        room_booked.add(booking_info);
                        return true;
                    } else {
                        waitingList.addWaiting(user);
                        return false;
                    }
                case "member":
                    if (user.getExclReward()) {
                        if (room.checkRoom("vip") && roomType.equals("vip")) {
                            booking_info.add(user.getName());
                            booking_info.add("vip");
                            user.setExcelReward(false);
                            waitingList.addWaiting(user);
                            room_booked.add(booking_info);
                            return true;
                        }
                    } else if (room.checkRoom("deluxe") && roomType.equals("deluxe")) {
                        booking_info.add(user.getName());
                        booking_info.add("deluxe");
                        waitingList.addWaiting(user);
                        room_booked.add(booking_info);
                        return true;
                    } else if (room.checkRoom("standard") && roomType.equals("standard")) {
                        booking_info.add(user.getName());
                        booking_info.add("standard");
                        waitingList.addWaiting(user);
                        room_booked.add(booking_info);
                        return true;
                    } else {
                        waitingList.addWaiting(user);
                        return false;
                    }

                case "non_member":
                    if (room.checkRoom("standard") && roomType.equals("standard")) {
                        booking_info.add(user.getName());
                        booking_info.add("standard");
                        waitingList.addWaiting(user);
                        room_booked.add(booking_info);
                        return true;
                    } else {
                        waitingList.addWaiting(user);
                        return false;
                    }
                default:
                    return false;
            }
        } catch (Exception e) {
           
            return false;
        }
    }
    
    //Method overloading with an extra Integer parameter
    public boolean setBooking(User user, int roomCount) throws IllegalArgumentException {
    	if (roomCount <= 0) {
    		throw new IllegalArgumentException();
    	}
    		
        try {
        	int count = 0;
            for (ArrayList<String> sublist : room_booked) {
                if (sublist.get(0).equals(user.getName())) {
                	count++;
                    if (count >= user.getMaxRoomBook()) {
                        throw new IllegalArgumentException(); //"User has already reached the maximum number of room bookings."
                    }
                }
            }
            

            ArrayList<String> booking_info = new ArrayList<>();
            switch (user.getMemberType().toLowerCase()) {
                case "vip":
                    if (room.checkRoom("vip")) {
                        booking_info.add(user.getName());
                        booking_info.add("vip");
                        waitingList.addWaiting(user);
                        room_booked.add(booking_info);
                        return true;
                    } else if (room.checkRoom("deluxe")) {
                        booking_info.add(user.getName());
                        booking_info.add("deluxe");
                        waitingList.addWaiting(user);
                        room_booked.add(booking_info);
                        return true;
                    } else if (room.checkRoom("standard")) {
                        booking_info.add(user.getName());
                        booking_info.add("standard");
                        waitingList.addWaiting(user);
                        room_booked.add(booking_info);
                        return true;
                    } else {
                        waitingList.addWaiting(user);
                        return false;
                    }
                case "member":
                    if (user.getExclReward()) {
                        if (room.checkRoom("vip")) {
                            booking_info.add(user.getName());
                            booking_info.add("vip");
                            waitingList.addWaiting(user);
                            room_booked.add(booking_info);
                            return true;
                        }
                    } else if (room.checkRoom("deluxe")) {
                        booking_info.add(user.getName());
                        booking_info.add("deluxe");
                        waitingList.addWaiting(user);
                        room_booked.add(booking_info);
                        return true;
                    } else if (room.checkRoom("standard")) {
                        booking_info.add(user.getName());
                        booking_info.add("standard");
                        waitingList.addWaiting(user);
                        room_booked.add(booking_info);
                        return true;
                    } else {
                        waitingList.addWaiting(user);
                        return false;
                    }

                case "non_member":
                    if (room.checkRoom("standard")) {
                        booking_info.add(user.getName());
                        booking_info.add("standard");
                        waitingList.addWaiting(user);
                        room_booked.add(booking_info);
                        return true;
                    } else {
                        waitingList.addWaiting(user);
                        return false;
                    }
                default:
                    return false;
            }
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
    
    public boolean cancelBooking(User user) {
        try {
            for (int i = 0; i < room_booked.size(); i++) {
                if (room_booked.get(i).get(0).equals(user.getName())) {
                    room_booked.remove(i);
                    return true;
                }
            }
            return false; // If no booking found for the user
        } catch (Exception e) {
            throw new IllegalArgumentException();

        }
    }

}


