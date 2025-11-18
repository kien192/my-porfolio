package service;

import dto.UserDto;

public class UserService {
	
	public boolean authenticateUser(UserDto udt) {
		return true;
	}
	
	public UserDto getUserInformationByUserId(String userId) {
		return new UserDto("12", "Kien");
	}
}
