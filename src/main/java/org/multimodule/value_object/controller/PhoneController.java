package org.multimodule.value_object.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.multimodule.value_object.common.vo.phone.Phone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : org.multimodule.value_object.controller
 * fileName       : PhoneController
 * author         : AngryPig123
 * date           : 2024-07-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-21        AngryPig123       최초 생성
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/phone")
public class PhoneController {

    @PostMapping
    public ResponseEntity<String> phoneEntity(
            @RequestBody Phone phone
    ) {

        log.info("phone = {}", phone.phoneNumber());
        return new ResponseEntity<>("success", HttpStatus.OK);

    }

}
