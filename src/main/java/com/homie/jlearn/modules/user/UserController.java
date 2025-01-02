package com.homie.jlearn.modules.user;

import com.homie.jlearn.modules.user.dto.CreateUserCommand;
import com.homie.jlearn.modules.user.dto.UpdateUserCommand;
import com.homie.jlearn.modules.user.port.UserCriteria;
import com.homie.jlearn.modules.user.port.in.QueryUserUseCase;
import com.homie.jlearn.modules.user.port.in.UserUseCase;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserUseCase userUseCase;

    private final QueryUserUseCase queryUserUseCase;

    public UserController(UserUseCase userUseCase, QueryUserUseCase queryUserUseCase) {
        this.userUseCase = userUseCase;
        this.queryUserUseCase = queryUserUseCase;
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody @Valid CreateUserCommand command) throws URISyntaxException {
        log.debug("REST request to create user: {}", command);
        User user = userUseCase.create(command);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("/api/users/" + user.getId()));

        return ResponseEntity.created(new URI("/api/users/" + user.getId())).headers(headers).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
        @PathVariable(value = "id", required = true) final String id,
        @RequestBody @Valid UpdateUserCommand command
    ) {
        log.debug("REST request to update user: {}", command);
        command.setId(id);
        User user = userUseCase.update(command);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Updated-Resource", "/api/users/" + id);
        headers.add("X-Timestamp", String.valueOf(System.currentTimeMillis()));

        return ResponseEntity.ok().headers(headers).body(user);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getPageUsers(UserCriteria criteria, Pageable pageable) {
        log.debug("REST request to get page users: {}", criteria);
        Page<User> pageUsers = queryUserUseCase.findPageCriteria(criteria, pageable);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Pages", String.valueOf(pageUsers.getTotalPages()));
        headers.add("X-Total-Count", String.valueOf(pageUsers.getTotalElements()));
        headers.add("X-Current-Page", String.valueOf(pageable.getPageNumber() + 1));
        headers.add("X-Page-Size", String.valueOf(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(pageUsers.getContent());
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(UserCriteria criteria) {
        log.debug("REST request to get all users: {}", criteria);
        List<User> users = queryUserUseCase.findListByCriteria(criteria);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(users.size()));

        return ResponseEntity.ok().headers(headers).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id", required = true) final String id) {
        log.debug("REST request to get user: {}", id);
        Optional<User> user = queryUserUseCase.findById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Resource-ID", id);

        return user
            .map(u -> ResponseEntity.ok().headers(headers).body(u))
            .orElseGet(() -> ResponseEntity.notFound().headers(headers).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id", required = true) final String id) {
        log.debug("REST request to delete user: {}", id);
        userUseCase.deleteById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Deleted-Resource", "/api/users/" + id);
        headers.add("X-Timestamp", String.valueOf(System.currentTimeMillis()));

        return ResponseEntity.noContent().headers(headers).build();
    }
}
