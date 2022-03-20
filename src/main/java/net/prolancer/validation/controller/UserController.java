package net.prolancer.validation.controller;

import lombok.extern.slf4j.Slf4j;
import net.prolancer.validation.common.contants.AppConstants;
import net.prolancer.validation.common.entity.ResponseHandler;
import net.prolancer.validation.entity.User;
import net.prolancer.validation.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(AppConstants.REST_API_PREFIX)
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseHandler.ok("Successfully created", HttpStatus.CREATED, savedUser);
    }

    @PostMapping(value = "/upload", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> uploadFile(@Valid @RequestPart("user") User user, @RequestPart("file") List<MultipartFile> files) {
        log.info("User: {}", user.toString());

        int idx = 0;
        for (MultipartFile multipartFile : files) {
            String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            String fileName = UUID.randomUUID().toString().replace("-", "");
            log.info("Files #{}: {} => {} => {}", ++idx, multipartFile.getName(), multipartFile.getOriginalFilename(), fileName + "." + extension);
            File file = new File("/Users/jaechulhan/Downloads/" + fileName + "." + extension);
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }

        User savedUser = userService.createUser(user);
        return ResponseHandler.ok("Successfully uploaded", HttpStatus.OK, savedUser);
    }

}
