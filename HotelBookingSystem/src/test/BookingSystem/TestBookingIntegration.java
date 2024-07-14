package BookingSystem;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;


@RunWith(JUnitParamsRunner.class)
public class TestBookingIntegration {
	
	
	@Test
	@Parameters({"member,John,2","member,Jennie,2","member,Jerry,2"})
	// Test case 31
	public void testMemberBookValid1(String memberType, String memberName, int ER) { 
		// Setting up the test environment
		Booking booking = new Booking();
		User memberUser = new User(memberName, memberType, false);
		booking.room_booked = new ArrayList<>();
		ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
		booking.room = new Room(10,10,10);
		
		// Room booked 2 times
		booking.setBooking(memberUser);
		booking.setBooking(memberUser);
		int AR = roomBooked.size();
		assertEquals(ER,AR);
	}

	@Test
	@Parameters({"member,John,2","member,Jennie,2","member,Jerry,2"})
	// Test Case 31
	public void testMemberBookValid2(String memberType, String memberName, int ER) {
		Booking booking = new Booking();
		Room mockR = mock(Room.class);
		booking.room = mockR;
		booking.room_booked = new ArrayList<>();
		ArrayList<ArrayList<String>> roomBooked = booking.room_booked;
		User memberUser = new User(memberName, memberType, false);
		when(booking.room.checkRoom(anyString())).thenReturn(true);
		
		booking.setBooking(memberUser);
		booking.setBooking(memberUser);
		int AR = roomBooked.size();
		assertEquals(ER,AR);
	}

    @Test(expected=IllegalArgumentException.class)
	@Parameters({"member,John","member,Jennie","member,Jerry"})
	// Test case 32
	public void testMemberBookingInvalid1a(String memberType,String memberName) {
		Booking booking = new Booking();
		User memberUser = new User(memberName, memberType, false);
		booking.room_booked = new ArrayList<>();
		booking.room = new Room(10,10,10);
		
		// Room booked 2 times
		booking.setBooking(memberUser);
		booking.setBooking(memberUser);
		
		// Invalid test
		booking.setBooking(memberUser);
	}

   @Test(expected=IllegalArgumentException.class)
	@Parameters({"member,John","member,Jennie","member,Jerry"})
	// Test case 32
	public void testMemberBookingInvalid1b(String memberType,String memberName) {
		Booking booking = new Booking();
		User memberUser = new User(memberName, memberType, false);
		Room mockR = mock(Room.class);
		booking.room = mockR;
		booking.room_booked = new ArrayList<>();
		when(booking.room.checkRoom(anyString())).thenReturn(true);

		// Room booked 2 times
		booking.setBooking(memberUser);
		booking.setBooking(memberUser);
		
		// Invalid test
		booking.setBooking(memberUser);
	}
    
   @Test(expected=IllegalArgumentException.class)
	// Test case 33
	public void testMemberBookingInvalid2a() {
		Booking booking = new Booking();
		User memberUser = new User("John", "abc", false);
		booking.room_booked = new ArrayList<>();
		booking.room = new Room(10,10,10);
		
		// Invalid Booking
		booking.setBooking(memberUser);
	}
	
   @Test(expected=IllegalArgumentException.class)
	// Test case 33
	public void testMemberBookingInvalid2b() {
		Booking booking = new Booking();
		User memberUser = new User("John", "abc", false);
		booking.room_booked = new ArrayList<>();
		Room mockR = mock(Room.class);
		booking.room = mockR;
		when(booking.room.checkRoom(anyString())).thenReturn(true);
		
		// Invalid Booking
		booking.setBooking(memberUser);
	}
	
   @Test
	@Parameters({"non_member,John","non_member,Jennie","non_member,Jerry"})
	// Test case 34
	public void testNonMemberBookingValid1(String memberType, String memberName) {
		Booking booking = new Booking();
		User memberUser = new User(memberName, memberType, false);
		booking.room_booked = new ArrayList<>();
		booking.room = new Room(10,10,10);
		
		// Room booked room for once
		booking.setBooking(memberUser);
		assertEquals("standard",booking.room_booked.get(0).get(1));
	}
	
  @Test
	@Parameters({"non_member,John","non_member,Jennie","non_member,Jerry"})
	// Test case 34
	public void testNonMemberBookingValid2(String memberType, String memberName) {
		Booking booking = new Booking();
		User memberUser = new User(memberName, memberType, false);
		booking.room_booked = new ArrayList<>();
		Room mockR = mock(Room.class);
		booking.room = mockR;
		when(booking.room.checkRoom(anyString())).thenReturn(true);
		
		// Room booked room for once
		booking.setBooking(memberUser);
		assertEquals("standard",booking.room_booked.get(0).get(1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	@Parameters({"non_member,John","non_member,Jennie","non_member,Jerry"})
	// Test case 38
	public void testNonMemberBookingInvalidNumOfRoomBooking1(String memberType,String memberName) {
		Booking booking = new Booking();
		User memberUser = new User(memberName, memberType, false);
		booking.room_booked = new ArrayList<>();
		booking.room = new Room(10,10,10);
		
		// Room booked 1 time
		booking.setBooking(memberUser);
		
		// Invalid test
		booking.setBooking(memberUser);
	}
	
	@Test(expected=IllegalArgumentException.class)
	@Parameters({"non_member,John","non_member,Jennie","non_member,Jerry"})
	// Test case 38
	public void testNonMemberBookingInvalidNumOfRoomBooking2(String memberType,String memberName) {
		Booking booking = new Booking();
		User memberUser = new User(memberName, memberType, false);
		booking.room_booked = new ArrayList<>();
		Room mockR = mock(Room.class);
		booking.room = mockR;
		when(booking.room.checkRoom(anyString())).thenReturn(true);
		
		// Room booked 1 time
		booking.setBooking(memberUser);
		
		// Invalid test
		booking.setBooking(memberUser);
	}
	
	@Test(expected=IllegalArgumentException.class)
	@Parameters({"abc,John","abc,Jennie","abc,Jerry"})
	// Test case 39
	public void testNonMemberBookingInvalidMemberType1(String memberType,String memberName) {
		Booking booking = new Booking();
		User memberUser = new User(memberName, memberType, false);
		booking.room_booked = new ArrayList<>();
		booking.room = new Room(10,10,10);
		
		// Invalid booking
		booking.setBooking(memberUser);
	}
	
	@Test(expected=IllegalArgumentException.class)
	@Parameters({"abc,John","abc,Jennie","abc,Jerry"})
	// Test case 39
	public void testNonMemberBookingInvalidMemberType2(String memberType,String memberName) {
		Booking booking = new Booking();
		User memberUser = new User(memberName, memberType, false);
		booking.room_booked = new ArrayList<>();
		Room mockR = mock(Room.class);
		booking.room = mockR;
		when(booking.room.checkRoom(anyString())).thenReturn(true);
		
		// Invalid booking
		booking.setBooking(memberUser);
	}
	
	@Test
	// Test case 40
	public void testCreateVIPUser() {
		User memberUser = new User("TAN", "VIP", false);
		assertEquals("TAN", memberUser.getName());
		assertEquals("vip", memberUser.getMemberType());
	}
	
	@Test
	// Test case 41
	public void testCreateMemberUser() {
		User memberUser = new User("YANG", "MEMBER", false);
		assertEquals("YANG", memberUser.getName());
		assertEquals("member", memberUser.getMemberType());
	}
	
	@Test
	// Test case 42
	public void testCreateNonMemberUser() {
		User memberUser = new User("CHENG", "NON_MEMBER", false);
		assertEquals("CHENG", memberUser.getName());
		assertEquals("non_member", memberUser.getMemberType());
	}
	
	
}
