
package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.util.List;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    private final UserProvider userProvider;

    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        User user = new User(userDto.firstName(), userDto.lastName(), userDto.birthdate(), userDto.email());
        return userMapper.toUserDto(userService.createUser(user));
    }

    @GetMapping
    public List<UserDto> getUsers() throws InterruptedException {

        return this.userProvider.findAllUsers().stream()
                .map(this.userMapper::toUserDto)
                .toList();
    }
    @GetMapping("/simple")
    public List<SimpleUserDto> getSimpleUsers() {
        return this.userProvider.findAllUsers().stream()
                .map(user -> new SimpleUserDto(user.getFirstName(), user.getLastName()))
                .toList();
    }
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return this.userProvider.getUser(id)
                .map(this.userMapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
    @GetMapping("/email")
    public List<UserEmailDto> getUserByEmailFragment(@RequestParam("email") String emailFragment) {
        return this.userProvider.findAllUsers().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(emailFragment.toLowerCase()))
                .map(user -> new UserEmailDto(user.getId(), user.getEmail()))
                .toList();
    }
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return this.userProvider.findAllUsers().stream()
                .filter(user -> user.getBirthdate().isBefore(time))
                .map(this.userMapper::toUserDto)
                .toList();
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User user = new User(userDto.firstName(), userDto.lastName(), userDto.birthdate(), userDto.email());
        return userMapper.toUserDto(userService.updateUser(userId, user));
    }
}