package com.hotelManagement.angular.hotelmanagement.booking;

import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import org.assertj.core.error.OptionalShouldContainInstanceOf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;

@RestController
public class BookingsController {
	
	@Autowired
	private BookingsRepository bookingsRepository;
	
	@Autowired
	private BookingUtils bookingUtils;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/addBooking")
	public String saveBooking(@RequestBody Bookings bookings){
		bookingsRepository.save(bookings);
		return "Added Booking with id : "+bookings.getId();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/updateBookings/{id}")
    public ResponseEntity<Bookings> updateBookings(
    @PathVariable(value = "id") String bookingId,
    @RequestBody Bookings bookingDetails) throws InvalidConfigurationPropertyValueException {
         Bookings bookings = bookingsRepository.findById(bookingId)
          .orElseThrow(() -> new InvalidConfigurationPropertyValueException("User not found on :: "+ bookingId, bookingDetails, bookingId));

         bookings.setuId(bookingDetails.getuId());
         bookings.setrId(bookingDetails.getrId());
         bookings.setBookedDays(bookingDetails.getBookedDays());
         bookings.setBookedFrom(bookingDetails.getBookedFrom());
         bookings.setBookedTo(bookingDetails.getBookedTo());
         bookings.setCostPerDay(bookingDetails.getCostPerDay());
         bookings.setCurrentStatus(bookingDetails.isCurrentStatus());
        final Bookings updatedBookings= bookingsRepository.save(bookings);
        return ResponseEntity.ok(updatedBookings);
   }
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/findAllBookings")
	public List<Bookings> getBookings(){
		return bookingsRepository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/findAllBookings/{id}")
	public Optional<Bookings> getBooking(@PathVariable String id){
		return bookingsRepository.findById(id);
	}
	
	 @CrossOrigin(origins = "http://localhost:4200")
     @GetMapping("/bookingUtils/{id}")
     public File getBookingUtils(@PathVariable String id){
      JSONObject bookingObj = new JSONObject();
      Optional<Bookings> booking=bookingsRepository.findById(id);
      bookingObj.put("id",booking.get().getId());
       bookingObj.put("uId",booking.get().getuId());
       bookingObj.put("rId",booking.get().getrId());
       bookingObj.put("bookedDays",booking.get().getBookedDays());
       bookingObj.put("bookedFrom",booking.get().getBookedFrom());
       bookingObj.put("bookedTo",booking.get().getBookedTo());
       bookingObj.put("costPerDay",booking.get().getCostPerDay());
       return bookingUtils.writeToCsv(bookingObj);
     }   
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/deleteBooking/{id}")
	public String deleteBooking(@PathVariable String id){
		bookingsRepository.deleteById(id);
		return "Booking deleted with id : "+id;
	}
}
