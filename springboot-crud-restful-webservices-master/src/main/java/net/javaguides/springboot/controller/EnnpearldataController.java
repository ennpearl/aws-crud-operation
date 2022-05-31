package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.entity.Ennpearldata;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.repository.EnnpearldataRepository;
import net.javaguides.springboot.repository.UserRepository;

@RestController
@RequestMapping("/api/ennpearldata")
public class EnnpearldataController {

	@Autowired
	private EnnpearldataRepository ennpearldataRepository;

	// get all users
	@GetMapping
	public List<Ennpearldata> getAllUsers() {
		return this.ennpearldataRepository.findAll();
	}

	// get user by id
	@GetMapping("/{id}")
	public Ennpearldata getUserById(@PathVariable (value = "id") long userId) {
		return this.ennpearldataRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
	}

	// create user
	@PostMapping
	public Ennpearldata createUser(@RequestBody Ennpearldata ennpearldata) {
		return this.ennpearldataRepository.save(ennpearldata);
	}
	
	// update user
	@PutMapping("/{id}")
	public Ennpearldata updateUser(@RequestBody Ennpearldata user, @PathVariable ("id") long userId) {
		Ennpearldata existingUser = this.ennpearldataRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
		 existingUser.setFirstName(user.getFirstName());
		 existingUser.setLastName(user.getLastName());
		 existingUser.setEmail(user.getEmail());
		 return this.ennpearldataRepository.save(existingUser);
	}
	
	// delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Ennpearldata> deleteUser(@PathVariable ("id") long userId){
		Ennpearldata existingUser = this.ennpearldataRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
		 this.ennpearldataRepository.delete(existingUser);
		 return ResponseEntity.ok().build();
	}
}
