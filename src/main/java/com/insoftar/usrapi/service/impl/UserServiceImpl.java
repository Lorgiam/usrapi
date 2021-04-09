package com.insoftar.usrapi.service.impl;

import com.insoftar.usrapi.dto.UserPostDto;
import com.insoftar.usrapi.dto.UserUpdateDto;
import com.insoftar.usrapi.model.User;
import com.insoftar.usrapi.repository.UserRepository;
import com.insoftar.usrapi.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ModelMapper mapper;

    @Override
    public Flux<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Mono<User> findById(String id) {
        return this.userRepository.findById(id);
    }

    @Override
    public Mono<User> save(UserPostDto userPostDto) {
        return this.userRepository.save(mapToEntityFromPostDto(userPostDto));
    }

    @Override
    public Mono<User> update(String id, UserUpdateDto userUpdateDto) {
        return this.userRepository.findById(id)
                .flatMap(usr -> {
                    usr.setId(id);
                    return this.userRepository.save(mapToEntityFromUpdateDto(userUpdateDto));
                }).switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<User> delete(String id) {
        return this.userRepository
                .findById(id)
                .flatMap(usr -> this.userRepository.deleteById(usr.getId()).thenReturn(usr));
    }

    public UserPostDto mapToDto(User user){
        UserPostDto userPostDto = this.mapper.map(user,UserPostDto.class);
        return  userPostDto;
    }
    public User mapToEntityFromPostDto(UserPostDto userPostDto){
        User user = this.mapper.map(userPostDto,User.class);
        return user;
    }
    public User mapToEntityFromUpdateDto(UserUpdateDto userUpdateDto){
        User user = this.mapper.map(userUpdateDto,User.class);
        return user;
    }
}
