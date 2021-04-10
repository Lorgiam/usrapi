package com.insoftar.usrapi.service.impl;

import com.insoftar.usrapi.dto.UserPostDto;
import com.insoftar.usrapi.dto.UserUpdateDto;
import com.insoftar.usrapi.model.User;
import com.insoftar.usrapi.repository.UserRepository;
import com.insoftar.usrapi.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

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

    @Override
    public Mono<Integer> validateCorreo(String correo,String id) {
        return this.mongoTemplate.findOne(Query.query(Criteria.where("correo").is(correo)),User.class)
                .flatMap(usr -> {
                    if(usr.getId().equals(id)){
                        return Mono.just(2);
                    }else{
                        return Mono.just(1);
                    }
                })
                .switchIfEmpty(Mono.just(0));
    }

    @Override
    public Mono<Integer> validateCedula(Integer cedula,String id) {
        return this.mongoTemplate.findOne(Query.query(Criteria.where("cedula").is(cedula)),User.class)
                .flatMap(usr -> {
                    if(usr.getId().equals(id)){
                        return Mono.just(2);
                    }else{
                        return Mono.just(1);
                    }
                })
                .switchIfEmpty(Mono.just(0));
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
