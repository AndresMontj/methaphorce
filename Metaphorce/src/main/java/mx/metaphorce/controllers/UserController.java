package mx.metaphorce.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import mx.metaphorce.entity.User;
import mx.metaphorce.entity.UserModelAssembler;
import mx.metaphorce.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@EnableMethodSecurity
public class UserController {

	// Annotation
	@Autowired
	private UserService userService;

	@Autowired
	private UserModelAssembler userModelAssembler;

	@Autowired
	private PagedResourcesAssembler<User> pagedResourcesAssembler;

	// Create operation
	@PostMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EntityModel<User>> saveUser(@Valid @RequestBody User user) {
		return new ResponseEntity<>(userModelAssembler.toModel(userService.saveUser(user)), HttpStatus.CREATED);
	}

	// Read operation
	@GetMapping("/users")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<PagedModel<EntityModel<User>>> fetchUserPage(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "30") int size) {
		return new ResponseEntity<>(
				pagedResourcesAssembler.toModel(userService.fetchUserList(page, size), userModelAssembler),
				HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<EntityModel<User>> getUserById(@PathVariable("id") Long userId) {
		Optional<User> user = userService.findUserById(userId);
		if (user.isPresent()) {
			return new ResponseEntity<>(userModelAssembler.toModel(user.get()), HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Update operation
	@PutMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EntityModel<User>> updateUser(@RequestBody User user, @PathVariable("id") Long userId) {
		Optional<User> userDB = userService.findUserById(userId);
		if (userDB.isPresent()) {
			return new ResponseEntity<>(userModelAssembler.toModel(userService.updateUser(user, userId)),
					HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Delete operation
	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deleteUserById(@PathVariable("id") Long userId) {
		Optional<User> userDB = userService.findUserById(userId);
		if (userDB.isPresent()) {
			userService.deleteUserById(userId);
			return ResponseEntity.ok("{operation : Deleted Successfully}");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
