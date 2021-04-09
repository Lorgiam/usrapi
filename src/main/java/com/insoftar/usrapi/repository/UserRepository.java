package com.insoftar.usrapi.repository;

import com.insoftar.usrapi.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User,String> {

}
