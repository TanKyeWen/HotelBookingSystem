package BookingSystem;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class TestBooking{
	
	
	@Mock
    private Room room;

    @Mock
    private WaitingList waitingList;

    private Booking booking;
    
    
    @Before
    public void setUp() {
        booking = new Booking();
        booking.room = room;
        booking.waitingList = waitingList;
        booking.room_booked = new ArrayList<>();
        
        ArrayList<User> vip_waiting_list = new ArrayList<>();
        ArrayList<User> member_waiting_list = new ArrayList<>();
        ArrayList<User> non_member_waiting_list = new ArrayList<>();
    }

    @Test
    public void testCase1() {
    	//Setup mock behavior to indicate VIP rooms are available
    	when(room.checkRoom("vip")).thenReturn(true);
    	
    	User vipUser = new User("VIPUser", "VIP", false); //Create a VIP User
    	
    	boolean result = booking.setBooking(vipUser); //Perform Booking
    	
    	assertTrue(result); //Verify that booking was successful
    	
    	ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
    	assertEquals("vip", roomBooked.get(0).get(1)); //Verify VIP room is given
    	
    }
  		
    @Test
    public void testCase2() {
    	//Setup mock behavior to indicate VIP rooms are not available, but deluxe rooms are available
    	when(room.checkRoom("vip")).thenReturn(false);
    	when(room.checkRoom("deluxe")).thenReturn(true);
    	
    	User vipUser = new User("VIPUser", "VIP", false); //Create a VIP User
    	
    	boolean result = booking.setBooking(vipUser); //Perform Booking
    	
    	assertTrue(result); //Verify Booking was successful
    	
    	ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
    	assertEquals("deluxe", roomBooked.get(0).get(1)); //Verify Deluxe room is given
    	
    	
    }
    
    @Test
    public void testCase3() {
    	//Setup mock behavior to indicate VIP and deluxe rooms are not available, but standard rooms are available
    	when(room.checkRoom("vip")).thenReturn(false);
    	when(room.checkRoom("deluxe")).thenReturn(false);
    	when(room.checkRoom("standard")).thenReturn(true);
    	
    	User vipUser = new User("VIPUser", "VIP", false); //Create a VIP User
    	
    	boolean result = booking.setBooking(vipUser); //Perform Booking
    	
    	assertTrue(result); //Verify Booking was successful
    	
    	ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
    	assertEquals("standard", roomBooked.get(0).get(1)); //Verify standard room is given
    	
    	
    }
    
    @Test
    public void testCase4() {
    	//Setup mock behavior to indicate no rooms are available
    	when(room.checkRoom("vip")).thenReturn(false);
    	when(room.checkRoom("deluxe")).thenReturn(false);
    	when(room.checkRoom("standard")).thenReturn(false);
    	
    	User vipUser = new User("VIPUser", "VIP", false); //Create a VIP User
    	
    	boolean result = booking.setBooking(vipUser); //Perform Booking
    	
    	assertFalse(result); //Verify Booking failed
    	
    	ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
    	assertTrue(roomBooked.isEmpty()); //Verify that no room is given
    }
    

   	
    @Test
    public void testCase6() {
    	
    	//Setup mock behavior to indicate all rooms are available
    	when(room.checkRoom(anyString())).thenReturn(true);
    	
    	User vipUser = new User("vipUser", "vip", false); //Create a VIP User
    	
    	boolean result = booking.setBooking(vipUser); //Perform Booking
    	
    	assertTrue(result); //Verify that booking was successful
    	
    	//Verifying the number of bookings
    	ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
    	assertEquals(1, roomBooked.size());
    	
    	//Verify VIP room is given
    	String actualRoomAllocation = roomBooked.get(0).get(1);
    	assertEquals("Room allocation is not VIP", "vip", actualRoomAllocation);
    	
    }
    
    @Test
    public void testCase7() {
    	
    	//Setup mock behavior to indicate all rooms are available
    	when(room.checkRoom(anyString())).thenReturn(true);
    	
    	User vipUser = new User("vipUser", "vip", false); //Create a VIP User
    	
    	boolean result1 = booking.setBooking(vipUser); //Perform first Booking
    	boolean result2 = booking.setBooking(vipUser); //Perform second Booking
    	boolean result3 = booking.setBooking(vipUser); //Perform third Booking
    	
    	assertTrue(result1); //Verify that booking 1 was successful
    	assertTrue("Booking failed for 2nd room", result2); //Verify that booking 2 was successful
    	assertTrue(result3); //Verify that booking 3 was successful
    	
    	//Verifying the number of bookings
    	ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
    	assertEquals(3, roomBooked.size());
    	
    	//Verify VIP room is given
    	assertEquals("1st room allocation is not VIP", "vip", roomBooked.get(0).get(1));
    	assertEquals("2nd room allocation is not VIP", "vip", roomBooked.get(1).get(1));
    	assertEquals("3rd room allocation is not VIP", "vip", roomBooked.get(2).get(1));
    	
    }
    
    @Test(expected = Exception.class) //Expected result to be an Exception thrown when 4th booking is tried
    public void testCase8() {
    	
    	//Setup mock behavior to indicate all rooms are available
    	when(room.checkRoom(anyString())).thenReturn(true);
    	
    	User vipUser = new User("vipUser", "vip", false); //Create a VIP User
    	
    	boolean result1 = booking.setBooking(vipUser); //Perform first Booking
    	boolean result2 = booking.setBooking(vipUser); //Perform second Booking
    	boolean result3 = booking.setBooking(vipUser); //Perform third Booking
    	boolean result4 = booking.setBooking(vipUser); //Attempt to perform a fourth Booking	
    }
    
    @Test
    public void testCase9() {
    	
    	//Setup mock behavior to indicate all rooms are available
    	when(room.checkRoom(anyString())).thenReturn(true);
    	
    	User vipUser = new User("vipUser", "vip", false); //Create a VIP User
    	
    	try {
        	booking.setBooking(vipUser, "abc");
        } catch (IllegalArgumentException e) {
        	assertTrue(true); //If IllegalArgumentException is caught, then it matches the expected result
        }
    	
    }
    
    @Test
    public void testCase10() {
    	//Setup mock behavior to indicate 1 vip room, more than 2 deluxe room available
    	when(room.checkRoom("vip")).thenReturn(true).thenReturn(false);
    	when(room.checkRoom("deluxe")).thenReturn(true);
    	
    	User vipUser = new User("vipUser", "vip", false);
    	
    	boolean result1 = booking.setBooking(vipUser); 
    	boolean result2 = booking.setBooking(vipUser); 
    	boolean result3 = booking.setBooking(vipUser); 
    	
    	assertTrue(result1); //Verify Booking1 successful
    	assertTrue(result2); //Verify Booking2 successful
    	assertTrue(result3); //Verify Booking3 successful
    	
    	ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
    	//Verify 1 VIP room and 2 Deluxe room given
    	assertEquals("vip", roomBooked.get(0).get(1));
    	assertEquals("deluxe", roomBooked.get(1).get(1));
    	assertEquals("deluxe", roomBooked.get(2).get(1));
    }
    
    @Test
    public void testCase11() {
        // Setup mock behavior to indicate only 1 VIP room and 2 Standard rooms are available
        when(room.checkRoom("vip")).thenReturn(true).thenReturn(false);
        when(room.checkRoom("deluxe")).thenReturn(false);
        when(room.checkRoom("standard")).thenReturn(true);
        
        User vipUser = new User("vipUser", "vip", false);
        
        boolean result1 = booking.setBooking(vipUser);
        boolean result2 = booking.setBooking(vipUser);
        boolean result3 = booking.setBooking(vipUser);
        
        assertTrue(result1); //Verify Booking1 successful
    	assertTrue(result2); //Verify Booking2 successful
    	assertTrue(result3); //Verify Booking3 successful
        
        ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
        assertEquals("vip", roomBooked.get(0).get(1));
        assertEquals("standard", roomBooked.get(1).get(1));
        assertEquals("standard", roomBooked.get(2).get(1));
    }




    @Test
    public void testCase12() {
        // Setup mock behavior to indicate 1 VIP room, 1 Deluxe room, and more than 1 Standard room are available
        when(room.checkRoom("vip")).thenReturn(true).thenReturn(false);
        when(room.checkRoom("deluxe")).thenReturn(true).thenReturn(false);
        when(room.checkRoom("standard")).thenReturn(true).thenReturn(false);
        
        User vipUser = new User("vipUser", "vip", false);
        
        boolean result1 = booking.setBooking(vipUser);
        boolean result2 = booking.setBooking(vipUser);
        boolean result3 = booking.setBooking(vipUser); 
        
        assertTrue(result1); //Verify Booking1 successful
    	assertTrue(result2); //Verify Booking2 successful
    	assertTrue(result3); //Verify Booking3 successful
        
        ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
        assertEquals("vip", roomBooked.get(0).get(1));
        assertEquals("deluxe", roomBooked.get(1).get(1));
        assertEquals("standard", roomBooked.get(2).get(1));
    }

    @Test
    public void testCase13() {
        // Setup mock behavior to indicate only 2 VIP rooms are available, all Deluxe and Standard rooms are fully booked
        when(room.checkRoom("vip")).thenReturn(true).thenReturn(true);
        when(room.checkRoom("deluxe")).thenReturn(false);
        when(room.checkRoom("standard")).thenReturn(false);
        
        User vipUser = new User("vipUser", "vip", false);
        
        boolean result1 = booking.setBooking(vipUser);
        boolean result2 = booking.setBooking(vipUser);
        boolean result3 = booking.setBooking(vipUser); 
        
        assertTrue(result1); //Verify Booking1 successful
    	assertTrue(result2); //Verify Booking2 successful
    	assertTrue(result3); //Verify Booking3 successful
        
        ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
        assertEquals("vip", roomBooked.get(0).get(1));
        assertEquals("vip", roomBooked.get(1).get(1));
        
        // Verify that the user is added to the vip_waiting_list
        verify(waitingList, atLeastOnce()).addWaiting(vipUser);
    }

    @Test
    public void testCase14() {
        // Setup mock behavior to indicate only 2 VIP rooms are available, all Deluxe and Standard rooms are fully booked
        when(room.checkRoom("vip")).thenReturn(true).thenReturn(true);
        when(room.checkRoom("deluxe")).thenReturn(false);
        when(room.checkRoom("standard")).thenReturn(false);
        
        User vipUser = new User("vipUser", "vip", false);
        
        boolean result1 = booking.setBooking(vipUser);
        boolean result2 = booking.setBooking(vipUser);
        boolean result3 = booking.setBooking(vipUser); 
        
        assertTrue(result1); //Verify Booking1 successful
    	assertTrue(result2); //Verify Booking2 successful
    	assertTrue(result3); //Verify Booking3 successful
        
        ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
        assertEquals("vip", roomBooked.get(0).get(1));
        assertEquals("vip", roomBooked.get(1).get(1));
        
        // Verify the VIP user is added to the waiting list
        verify(waitingList, atLeastOnce()).addWaiting(vipUser);
      
    }

    @Test
    public void testCase15() {
        // Setup mock behavior to indicate no VIP rooms, 2 Deluxe rooms, and no Standard rooms are available
        when(room.checkRoom("vip")).thenReturn(false);
        when(room.checkRoom("deluxe")).thenReturn(true).thenReturn(true);
        when(room.checkRoom("standard")).thenReturn(false);
        
        User vipUser = new User("vipUser", "vip", false);
        
        boolean result1 = booking.setBooking(vipUser);
        boolean result2 = booking.setBooking(vipUser);
        boolean result3 = booking.setBooking(vipUser); 
        
        assertTrue(result1); //Verify Booking1 successful
    	assertTrue(result2); //Verify Booking2 successful
    	assertTrue(result3); //Verify Booking3 successful
        
        
        ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
        assertEquals("deluxe", roomBooked.get(0).get(1));
        assertEquals("deluxe", roomBooked.get(1).get(1));
    }

    @Test
    public void testCase16() {
        // Setup mock behavior to indicate no VIP rooms, 2 Deluxe rooms, and no Standard rooms are available
    	when(room.checkRoom("vip")).thenReturn(false);
    	when(room.checkRoom("deluxe")).thenReturn(true);
    	when(room.checkRoom("standard")).thenReturn(false);
        
        User vipUser = new User("vipUser", "vip", false);
        
        boolean result1 = booking.setBooking(vipUser);
        boolean result2 = booking.setBooking(vipUser);
        boolean result3 = booking.setBooking(vipUser); 
        
        assertTrue(result1); //Verify Booking1 successful
    	assertTrue(result2); //Verify Booking2 successful
    	assertTrue(result3); //Verify Booking3 successful
    	
        ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
        assertEquals("deluxe", roomBooked.get(0).get(1));
        assertEquals("deluxe", roomBooked.get(1).get(1));
        
        verify(waitingList, atLeastOnce()).addWaiting(vipUser);
    }

    @Test
    public void testCase17() {
        // Setup mock behavior to indicate no VIP rooms, no Deluxe rooms, and 2 Standard rooms are available
        when(room.checkRoom("vip")).thenReturn(false);
        when(room.checkRoom("deluxe")).thenReturn(false);
        when(room.checkRoom("standard")).thenReturn(true);
        
        User vipUser = new User("vipUser", "vip", false);
        
        boolean result1 = booking.setBooking(vipUser);
        boolean result2 = booking.setBooking(vipUser);
        boolean result3 = booking.setBooking(vipUser); 
        
        assertTrue(result1); //Verify Booking1 successful
    	assertTrue(result2); //Verify Booking2 successful
    	assertTrue(result3); //Verify Booking3 successful
        
        ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
        assertEquals("standard", roomBooked.get(0).get(1));
        assertEquals("standard", roomBooked.get(1).get(1));
    }

    @Test
    public void testCase18() {
        // Setup mock behavior to indicate no VIP rooms, no Deluxe rooms, and 2 Standard rooms are available
        when(room.checkRoom("vip")).thenReturn(false);
        when(room.checkRoom("deluxe")).thenReturn(false);
        when(room.checkRoom("standard")).thenReturn(true);
        
        User vipUser = new User("vipUser", "vip", false);
        
        boolean result1 = booking.setBooking(vipUser);
        boolean result2 = booking.setBooking(vipUser);
        boolean result3 = booking.setBooking(vipUser); 
        
        assertTrue(result1); //Verify Booking1 successful
    	assertTrue(result2); //Verify Booking2 successful
    	assertTrue(result3); //Verify Booking3 successful
        
        ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
        assertEquals("standard", roomBooked.get(0).get(1));
        assertEquals("standard", roomBooked.get(1).get(1));
        
        verify(waitingList, atLeastOnce()).addWaiting(vipUser);
    }
    
    @Test
    public void testCase19() {
        // If a VIP room is available and a Member redeems an exclusive rewards and books a room,
        // it should book a VIP room automatically
        when(room.checkRoom("vip")).thenReturn(true);
        
        User memberUser = new User("MemberUser", "MEMBER", false); // Create a MEMBER User
        memberUser.setExcelReward(true);
        
        boolean result = booking.setBooking(memberUser); // Perform Booking
        
        assertTrue(result); // Verify that booking was successful
        
        ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
        assertEquals("vip", roomBooked.get(0).get(1)); // Verify VIP room is given
    }
    
    @Test
    public void testCase20() {
    	//Setup mock behavior to indicate VIP rooms are available
    	when(room.checkRoom("vip")).thenReturn(true); 	
    	User memberUserExclusive = new User("MemberUserExclusive", "member", true);
    	boolean result = booking.setBooking(memberUserExclusive); //Perform Booking
 
    	assertTrue(result); //Verify that booking was successful
    	assertFalse(memberUserExclusive.getExclReward()); //Verify that the Exclusive Reward is updated 
    	
    	ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
    	
        assertEquals("vip", roomBooked.get(0).get(1)); //Verify VIP room is given
    }
    
    @Test
    public void testCase21() {
    	when(room.checkRoom("vip")).thenReturn(false); 	
    	when(room.checkRoom("deluxe")).thenReturn(true);
    	
    	User memberUserExclusive = new User("MemberUserExclusive", "member", true);
    	boolean result = booking.setBooking(memberUserExclusive); //Perform Booking
 
    	assertTrue(result); //Verify that booking was successful
 
    	
    	ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
    	
        assertEquals("deluxe", roomBooked.get(0).get(1)); 
    }
    
    @Test
    public void testCase22() {
    	//Setup mock behavior to indicate VIP and deluxe rooms are available
    	when(room.checkRoom("vip")).thenReturn(false);
   
    	
    	User memberUserExclusive = new User("MemberUserExclusive", "member", true);
    	boolean result1 = booking.setBooking(memberUserExclusive); //Perform Booking
 
    	assertTrue(memberUserExclusive.getExclReward()); //Exclusive Reward is true
    }
    
    @Test
    public void testCase23() {
    	//Setup mock behavior to indicate VIP and deluxe rooms are available
    	when(room.checkRoom("vip")).thenReturn(true);
    	when(room.checkRoom("deluxe")).thenReturn(true);
    	User memberUserExclusive = new User("MemberUserExclusive", "member", true);
    	boolean result1 = booking.setBooking(memberUserExclusive); //Perform Booking
    	boolean result2 = booking.setBooking(memberUserExclusive);
    	
    	assertTrue(result1); //Verify that booking was successful for VIP room
    	assertFalse(memberUserExclusive.getExclReward()); //Verify that the Exclusive Reward is updated
    	assertTrue(result2); //Verify that booking was successful deluxe room
    	
    	ArrayList<ArrayList<String>> roomBooked = booking.room_booked; //Get the room booked
    	
    	//Verify that the booking room is correct
    	assertEquals("vip", roomBooked.get(0).get(1));
    	assertEquals("deluxe", roomBooked.get(1).get(1));
    }

    @Test
    public void testCase24() {
    	when(room.checkRoom("vip")).thenReturn(true).thenReturn(false);
    	when(room.checkRoom("deluxe")).thenReturn(true);
    	
    	User memberUserExclusive = new User("MemberUserExclusive", "member", true);
    	boolean result1 = booking.setBooking(memberUserExclusive); //Perform Booking
    	boolean result2 = booking.setBooking(memberUserExclusive);
    	
    	
    	assertTrue(result1); //Verify that booking was successful for VIP room
    	assertFalse(memberUserExclusive.getExclReward()); //Verify that the Exclusive Reward is updated
    	assertTrue(result2); //Verify that booking was successful deluxe room
    	
    	//ArrayList<ArrayList<String>> roomBooked = booking.room_booked; //Get the room booked
    	
    	//Verify that the booking room is correct
    	//assertEquals("vip", roomBooked.get(0).get(1));
    	//assertEquals("deluxe", roomBooked.get(1).get(1));
    }
    
    @Test
    public void testCase25() {
    	//Setup mock behavior to indicate all rooms are available
    	when(room.checkRoom("vip")).thenReturn(true); // Assume VIP room is available

        
        User memberUser = new User("MemberUser", "member", false); // Member without exclusive rewards
        
        // Act
        boolean result = booking.setBooking(memberUser); // Perform Booking
         
        // Assert
        assertFalse(result); // Verify that booking is prohibited
        
        ArrayList<ArrayList<String>> roomBooked = booking.room_booked; //Get the room booked
    	
    	//Verify that the booking room is  0 because room allocation is 0
    	assertEquals(0, roomBooked.size());
    }

    @Test
    public void testCase26() {
    	//Setup mock behavior to indicate VIP rooms are available
    	when(room.checkRoom("deluxe")).thenReturn(true);
 
    	User memberUser = new User("MemberUser", "member", false);
    	boolean result1 = booking.setBooking(memberUser); //Perform Booking
    	
    	assertTrue(result1); //Verify that booking was successful
    	
    	ArrayList<ArrayList<String>> roomBooked = booking.room_booked; //Get the room booked
    	
    	//Verify that the booking room is correct
    	assertEquals("deluxe", roomBooked.get(0).get(1));
    }    
    
    
    @Test
    public void testCase27() {
    	when(room.checkRoom("deluxe")).thenReturn(false);
    	when(room.checkRoom("standard")).thenReturn(true);
    	
    	User memberUser = new User("MemberUser", "member", false);
    	boolean result1 = booking.setBooking(memberUser); //Perform Booking
    	
    	assertTrue(result1); //Verify that booking was successful
    	
    	ArrayList<ArrayList<String>> roomBooked = booking.room_booked; //Get the room booked
    	
    	//Verify that the booking room is correct
    	assertEquals("standard", roomBooked.get(0).get(1));
    	
    }
    
    @Test
    public void testCase28() {
    	when(room.checkRoom("deluxe")).thenReturn(false);
    	when(room.checkRoom("standard")).thenReturn(false);
    	
    	User memberUser = new User("MemberUser", "member", false);
    	boolean result1 = booking.setBooking(memberUser); //Perform Booking
    	
    	assertFalse(result1); //Verify that booking was unsuccessful due to no room is booked
    	
  	
    }
    
    
    @Test
    public void testCase29() {
    	when(room.checkRoom("deluxe")).thenReturn(false);
    	when(room.checkRoom("standard")).thenReturn(false);
    	
    	User memberUser = new User("MemberUser", "member", false);
    	boolean result1 = booking.setBooking(memberUser); //Perform Booking
    	
    	
    	
    	verify(waitingList, atLeastOnce()).addWaiting(memberUser); // member is in waiting list
    }
    
    
 
    
    @Test(expected = IllegalArgumentException.class)
    public void testCase30() {
        // All rooms available
    	 when(room.checkRoom("vip")).thenReturn(true);
    	 when(room.checkRoom("deluxe")).thenReturn(true);
         when(room.checkRoom("standard")).thenReturn(true);
         
         
         User memberUser = new User("memberUser", "member", false);
         booking.setBooking(memberUser, 0);
    }
    
    @Test
    public void testCase45() {
    	Booking booking = new Booking();
		User memberUser = new User("MemberUser", "member", false);
		booking.room_booked = new ArrayList<>();
		ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
		booking.room = new Room(10,10,10);
		
		// Room booked 1 time
		booking.setBooking(memberUser);
			
		// verify that room is booked 1 time
		assertEquals(1,roomBooked.size());
		
		boolean result2 = booking.cancelBooking(memberUser);
		assertEquals(0,roomBooked.size());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCase46() {
    	Booking booking = new Booking();
		User memberUser = new User("MemberUser", "abc", false);
		booking.room_booked = new ArrayList<>();
		ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
		booking.room = new Room(10,10,10);
		
		// Room booked 1 time
		booking.setBooking(memberUser);
    }
}
    
    
    
    
    
    
    