package mx.metaphorce.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.metaphorce.entity.User;
import mx.metaphorce.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Page<User> fetchUserList(int page, int size) {
		// create Pageable object using the page and size
		Pageable pageable = PageRequest.of(page, size);
		// fetch the page object by additionally passing pageable with the filters
		return userRepository.findAll(pageable);

	}

	@Override
	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User updateUser(User user, Long userId) {
		Optional<User> userOp = userRepository.findById(userId);
		if (userOp.isPresent()) {
			User userDB = userOp.get();
			if (Objects.nonNull(user.getUsername()) && !"".equalsIgnoreCase(user.getUsername())) {
				userDB.setUsername(user.getUsername());
			}

			if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
				userDB.setEmail(user.getEmail());
			}
			return userRepository.save(userDB);
		}else {
			return null;
		}
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
