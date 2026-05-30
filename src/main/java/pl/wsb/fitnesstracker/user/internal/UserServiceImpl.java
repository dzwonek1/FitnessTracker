package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("Deleting User {}",userId);
        userRepository.findById(userId).ifPresent(userRepository::delete);
    }

    @Override
    public User updateUser(Long userId, User user) {
        log.info("Updating User {}", userId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        User existingUser = optionalUser.get();

        existingUser.updateUser(user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail()
        );
        return userRepository.save(existingUser);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}