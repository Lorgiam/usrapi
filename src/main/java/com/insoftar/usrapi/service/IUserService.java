package com.insoftar.usrapi.service;

import com.insoftar.usrapi.dto.UserPostDto;
import com.insoftar.usrapi.dto.UserUpdateDto;
import com.insoftar.usrapi.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {
  Flux<User> findAll();
  Mono<User> findById(String id);
  Mono<User> save(UserPostDto userPostDto);
  Mono<User> update(String id, UserUpdateDto userUpdateDto);
  Mono<User> delete(String id);
  Mono<Integer> validateCorreo(String correo,String id);
  Mono<Integer> validateCedula(Integer cedula,String id);
}
