package com.hotelManagement.angular.hotelmanagement.room;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hotelManagement.angular.hotelmanagement.room.Room;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {

}

