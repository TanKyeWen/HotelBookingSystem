package BookingSystem;

import java.util.ArrayList;

public class WaitingList {
    ArrayList<User> vip_waiting_list, member_waiting_list, non_member_waiting_list;

    public WaitingList() {
        vip_waiting_list = new ArrayList<>();
        member_waiting_list = new ArrayList<>();
        non_member_waiting_list = new ArrayList<>();
    }

    // adding user to waiting queue
    public void addWaiting(User user) throws IllegalArgumentException {
        if (user.getMemberType().equals("vip")) {
            vip_waiting_list.add(user);
        } else if (user.getMemberType().equals("member")) {
            member_waiting_list.add(user);
        } else if (user.getMemberType().equals("non_member")) {
            non_member_waiting_list.add(user);
        } else {
            throw new IllegalArgumentException(); //"Invalid member type."
        }
    }

    // return waiting list by inserting member type in String
    public ArrayList<User> getWaiting(String member_type) throws IllegalArgumentException {
        switch (member_type.toLowerCase()) {
            case "vip":
                return vip_waiting_list;
            case "member":
                return member_waiting_list;
            case "non_member":
                return non_member_waiting_list;
            default:
                throw new IllegalArgumentException(); //"Invalid member type."
        }
    }

    // remove user by entering user
    public boolean removeWaiting(User user) {
        try {
            switch (user.getMemberType().toLowerCase()) {
                case "vip":
                    return vip_waiting_list.remove(user);
                case "member":
                    return member_waiting_list.remove(user);
                case "non_member":
                    return non_member_waiting_list.remove(user);
                default:
                    return false;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }
}