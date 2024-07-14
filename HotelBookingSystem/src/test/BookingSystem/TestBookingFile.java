package BookingSystem;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.mockito.*;

public class TestBookingFile {
	
	@Test
	public void testCase23() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("src/TestFile.txt"))) {
			String line;
			int lineToRead = 3;
			for (int i= 1; i < lineToRead; i++) {
					br.readLine();
			}
			if((line = br.readLine()) != null) { 
				String [] testData =  line.split(","); //delimiter to separate values
				String memberType = null;
				boolean exclRewards = false;
				int vipRooms = 0;
				int deluxeRooms = 0;
				int standardRooms = 0;
				String expectedResult = null;
				
				for (String data : testData) { //Separating naming conventions for test data's names
					String [] keyValue = data.split("=");
					String key = keyValue [0];
					String value = keyValue[1];
					
					switch (key) {
					case "User":
						memberType = value;
						break;
					case "ExclReward":
						exclRewards = Boolean.parseBoolean(value);
						break;
					case "VIPRooms":
						vipRooms = Integer.parseInt(value);
						break;
					case "DeluxeRooms":
						deluxeRooms = Integer.parseInt(value);
						break;
					case "StandardRooms":
						standardRooms = Integer.parseInt(value);
						break;
					case "ExpectedResult":
						expectedResult = value;
						break;
						
					}
							
				}
			
			//Creating the objects
			Booking booking = new Booking();
			User member = new User("MemberUser", memberType, exclRewards);
			Room room = new Room(vipRooms, deluxeRooms, standardRooms);	
			booking.room_booked = new ArrayList<>();
			booking.room = room;
			
			
			boolean result = booking.setBooking(member); //Book a room
			//member.setExcelReward(false);
			boolean result2 = booking.setBooking(member); //Book 2nd room
			
			assertTrue(result); //Verify vip booking successful
			assertTrue(result2); //Verify deluxe booking successful
			assertEquals(expectedResult, booking.room_booked.get(0).get(1)+ booking.room_booked.get(1).get(1)); //Verify that vip and deluxe room is booked
	}
	}
	}
	
	@Test
	public void testCase24() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("src/TestFile.txt"))) {
			String line;
			int lineToRead = 4;
			for (int i= 1; i < lineToRead; i++) {
					br.readLine();
			}
			if((line = br.readLine()) != null) { 
				String [] testData =  line.split(","); //delimiter to separate values
				String memberType = null;
				boolean exclRewards = false;
				int vipRooms = 0;
				int deluxeRooms = 0;
				int standardRooms = 0;
				String expectedResult = null;
				
				for (String data : testData) { //Separating naming conventions for test data's names
					String [] keyValue = data.split("=");
					String key = keyValue [0];
					String value = keyValue[1];
					
					switch (key) {
					case "User":
						memberType = value;
						break;
					case "ExclReward":
						exclRewards = Boolean.parseBoolean(value);
						break;
					case "VIPRooms":
						vipRooms = Integer.parseInt(value);
						break;
					case "DeluxeRooms":
						deluxeRooms = Integer.parseInt(value);
						break;
					case "StandardRooms":
						standardRooms = Integer.parseInt(value);
						break;
					case "ExpectedResult":
						expectedResult = value;
						break;
						
					}
							
				}
			
			//Creating the objects
			Booking booking = new Booking();
			User member = new User("MemberUser", memberType, exclRewards);
			Room room = new Room(vipRooms, deluxeRooms, standardRooms);	
			booking.room_booked = new ArrayList<>();
			booking.room = room;
			
			
			boolean result = booking.setBooking(member); //Book a room
			//member.setExcelReward(false);
			boolean result2 = booking.setBooking(member); //Book 2nd room
			
			assertTrue(result); //Verify vip room booking
			assertTrue(result2); //Verify deluxe room booking
			assertEquals(expectedResult, String.valueOf(member.getExclReward())); //Verify that exclusive reward is false
	}
	}
	}
	
	@Test
	public void testCase25() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("src/TestFile.txt"))) {
			String line;
			int lineToRead = 5;
			for (int i= 1; i < lineToRead; i++) {
					br.readLine();
			}
			if((line = br.readLine()) != null) { 
				String [] testData =  line.split(","); //delimiter to separate values
				String memberType = null;
				boolean exclRewards = false;
				int vipRooms = 0;
				int deluxeRooms = 0;
				int standardRooms = 0;
				String expectedResult = null;
				
				for (String data : testData) { //Separating naming conventions for test data's names
					String [] keyValue = data.split("=");
					String key = keyValue [0];
					String value = keyValue[1];
					
					switch (key) {
					case "User":
						memberType = value;
						break;
					case "ExclReward":
						exclRewards = Boolean.parseBoolean(value);
						break;
					case "VIPRooms":
						vipRooms = Integer.parseInt(value);
						break;
					case "DeluxeRooms":
						deluxeRooms = Integer.parseInt(value);
						break;
					case "StandardRooms":
						standardRooms = Integer.parseInt(value);
						break;
					case "ExpectedResult":
						expectedResult = value;
						break;
						
					}
							
				}
			
			//Creating the objects
			Booking booking = new Booking();
			User member = new User("MemberUser", memberType, exclRewards);
			Room room = new Room(vipRooms, deluxeRooms, standardRooms);	
			booking.room_booked = new ArrayList<>();
			booking.room = room;
			
			
			boolean result = booking.setBooking(member,"vip"); //Attempt to book a room
			assertFalse(result); //Verify that booking was unsuccessful
			String roomAllocated = "none"; //A placeholder to replace empty value
			assertEquals(expectedResult, roomAllocated); //Verify that there is no room given
	}
	}
	}
	
	@Test
	public void testCase26() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("src/TestFile.txt"))) {
			String line;
			int lineToRead = 6;
			for (int i= 1; i < lineToRead; i++) {
					br.readLine();
			}
			if((line = br.readLine()) != null) { 
				String [] testData =  line.split(","); //delimiter to separate values
				String memberType = null;
				boolean exclRewards = false;
				int vipRooms = 0;
				int deluxeRooms = 0;
				int standardRooms = 0;
				String expectedResult = null;
				
				for (String data : testData) { //Separating naming conventions for test data's names
					String [] keyValue = data.split("=");
					String key = keyValue [0];
					String value = keyValue[1];
					
					switch (key) {
					case "User":
						memberType = value;
						break;
					case "ExclReward":
						exclRewards = Boolean.parseBoolean(value);
						break;
					case "VIPRooms":
						vipRooms = Integer.parseInt(value);
						break;
					case "DeluxeRooms":
						deluxeRooms = Integer.parseInt(value);
						break;
					case "StandardRooms":
						standardRooms = Integer.parseInt(value);
						break;
					case "ExpectedResult":
						expectedResult = value;
						break;
						
					}
							
				}
			
			//Creating the objects
			Booking booking = new Booking();
			User member = new User("MemberUser", memberType, exclRewards);
			Room room = new Room(vipRooms, deluxeRooms, standardRooms);	
			booking.room_booked = new ArrayList<>();
			booking.room = room;
			
			
			boolean result = booking.setBooking(member); //Book a room
			assertTrue(result); //Verify that Booking was successful
			assertEquals(expectedResult, booking.room_booked.get(0).get(1)); //Verify that there is no room given
	}
	}
	}
	
	
	
	
}

