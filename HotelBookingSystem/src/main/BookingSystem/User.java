package BookingSystem;

import java.util.Arrays;

public class User {
	
	// member_type VIP,member,normal
	private String name, member_type;

	//Maximum room booking
	private int max_room_book;
	private boolean excl_reward;

	public User(String name, String member_type, boolean excl_reward){
		this.name = name;
		this.member_type = member_type.toLowerCase();
		setMaxRoomBook();
		this.excl_reward = excl_reward;
	}

	public String getName(){
		return name;
	}

	public String getMemberType() {
		return member_type;
	}

	public int getMaxRoomBook(){
		return max_room_book;
	}

	public boolean getExclReward(){
		return excl_reward;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public void setMemberType(String member_type){
		String[] valid_member_type = {"vip", "member", "non_member"};

		// Java-8 function
		if(Arrays.stream(valid_member_type).anyMatch(member_type.toLowerCase()::equals)){
			this.member_type = member_type.toLowerCase();
		 } else {
	            throw new IllegalArgumentException(); //member_type + " is not a valid user type."
	     }
	}

	public void setMaxRoomBook(){
		switch (getMemberType().toLowerCase()) {
		case "vip":
			max_room_book = 3;
			break;

		case "member":
			max_room_book = 2;
			break;

		default: //non-member
			max_room_book = 1;
			break;
		}
	}

	public void setExcelReward(boolean  excl_reward) {
		this.excl_reward=excl_reward;
	}
	
	
	public boolean setMemberType2(String member_type){
		String[] valid_member_type = {"vip", "member", "non_member"};

		// Java-8 function
		if(Arrays.stream(valid_member_type).anyMatch(member_type.toLowerCase()::equals)){
			this.member_type = member_type.toLowerCase();
			return true;
		 } else {
	            throw new IllegalArgumentException(); //member_type + " is not a valid user type."
	     }
	}
	
}
