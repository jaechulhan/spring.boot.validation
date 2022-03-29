package net.prolancer.validation.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.prolancer.validation.common.contants.AppProperties;
import net.prolancer.validation.common.config.MessageHelper;
import net.prolancer.validation.common.contants.AppConstants;
import net.prolancer.validation.common.entity.ResponseHandler;
import net.prolancer.validation.entity.User;
import net.prolancer.validation.service.UserService;
import org.apache.commons.io.FilenameUtils;
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
@AllArgsConstructor
public class UserController {

    private final MessageHelper messageHelper;

    private final AppProperties appProperties;

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseHandler.ok(messageHelper.get("default.save.success.message")
                , HttpStatus.CREATED
                , savedUser);
    }

    @PostMapping(value = "/upload", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> uploadFile(@Valid @RequestPart("user") User user, @RequestPart(value = "file", required = false) List<MultipartFile> files) {
        if (files != null) {
            int idx = 0;
            for (MultipartFile mFile : files) {
                if (mFile.getSize() > 0) {
                    String extension = FilenameUtils.getExtension(mFile.getOriginalFilename());
                    String fileName = UUID.randomUUID().toString().replace("-", "");
                    log.info("Files #{}: {} => {} => {}", ++idx, mFile.getName(), mFile.getOriginalFilename(), fileName + "." + extension);
                    File file = new File(appProperties.uploadFilePath + fileName + "." + extension);
                    try {
                        mFile.transferTo(file);
                    } catch (IOException e) {
                        log.error(e.getMessage());
                    }
                }
            }
        }

        User savedUser = userService.createUser(user);
        return ResponseHandler.ok(messageHelper.get("default.submit.success.message")
                , HttpStatus.OK
                , savedUser);
    }

}
