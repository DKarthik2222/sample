package com.hotelManagement.angular.hotelmanagement.user;

import java.util.List;
import java.util.Optional;

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
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	

	@Autowired
	private UserRepository repository;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/addUser")
	public String saveUser(@RequestBody User user){
		repository.save(user);
		return "Added User with id : "+user.getId();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(
    @PathVariable(value = "id") String userId,
    @RequestBody User userDetails) throws InvalidConfigurationPropertyValueException {
         User user = repository.findById(userId)
          .orElseThrow(() -> new InvalidConfigurationPropertyValueException("User not found on :: "+ userId, userDetails, userId));

        user.setWallet(userDetails.getWallet());
        final User updatedUser = repository.save(user);
        return ResponseEntity.ok(updatedUser);
   }

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/findAllUsers")
	public List<User> getUsers(){
		return repository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/findAllUsers/{id}")
	public Optional<User> getUser(@PathVariable String id){
		return repository.findById(id);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/deleteUser/{id}")
	public String deleteRoom(@PathVariable String id){
		repository.deleteById(id);
		return "User deleted with id : "+id;
	}

}
