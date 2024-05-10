package mx.metaphorce.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import mx.metaphorce.entity.User;

@Service
public interface UserService {

	// save operation
	User saveUser(User user);

	// read operation
	Page<User> fetchUserList(int page, int size);

	// update operation
	User updateUser(User user, Long userId);

	// delete operation
	void deleteUserById(Long id);

	// read specified user
	Optional<User> findUserById(Long id);
	
	Optional<User> findByUsername(String username);

}
