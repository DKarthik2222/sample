package com.hotelManagement.angular.hotelmanagement.booking;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends MongoRepository<Bookings, String> {

}