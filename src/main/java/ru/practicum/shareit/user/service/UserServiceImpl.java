package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.UserStorage;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserStorage userStorage;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = userStorage.save(UserMapper.toUser(userDto));
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        User foundUser = userStorage.findById(userId)
                .orElseThrow(() -> new NotFoundException("Не найден пользователь с id " + userId));

        User user = UserMapper.toUser(userDto);

        if (user.getName() != null) {
            foundUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            foundUser.setEmail(user.getEmail());
        }

        return UserMapper.toUserDto(userStorage.save(foundUser));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUserById(Long userId) {
        User user = userStorage.findById(userId).orElseThrow(() ->
                new NotFoundException("Пользователь с id " + userId + " не найден"));
        return UserMapper.toUserDto(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<UserDto> getUsers() {
        return UserMapper.toUserDtoList(userStorage.findAll());
    }

    @Override
    public void deleteUserById(Long userId) {
        userStorage.deleteById(userId);
    }
}
