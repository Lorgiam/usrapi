package com.insoftar.usrapi.resource;

import com.insoftar.usrapi.dto.UserPostDto;
import com.insoftar.usrapi.dto.UserUpdateDto;
import com.insoftar.usrapi.model.User;
import com.insoftar.usrapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private IUserService userService;

    @GetMapping("/findAll")
    public Flux<User> findAll(){
        return  this.userService.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<User> save(@RequestBody UserPostDto userPostDto) {
        return this.userService.save(userPostDto);
    }

    @PutMapping("/update/{id}")
    private Mono<ResponseEntity<User>> update(@PathVariable("id") String id, @RequestBody UserUpdateDto userUpdateDto) {
        return this.userService.update(id, userUpdateDto)
                .flatMap(usr -> Mono.just(ResponseEntity.ok(usr)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @DeleteMapping("/delete/{id}")
    private Mono<ResponseEntity<User>> delete(@PathVariable("id") String id) {
        return this.userService.delete(id)
                .flatMap(usr -> Mono.just(ResponseEntity.ok(usr)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

}
