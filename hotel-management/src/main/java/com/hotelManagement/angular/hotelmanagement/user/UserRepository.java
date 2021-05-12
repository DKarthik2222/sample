package com.hotelManagement.angular.hotelmanagement.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
//	public List<User> updateUser(String id);
	
}