package org.multimodule.value_object.vo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.multimodule.value_object.common.vo.phone.Phone;
import org.multimodule.value_object.common.vo.phone.PhoneException;

/**
 * packageName    : org.multimodule.value_object.vo
 * fileName       : PhoneTest
 * author         : AngryPig123
 * date           : 2024-07-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-21        AngryPig123       최초 생성
 */

@Slf4j
class PhoneTest {

    @Test
    void fullPhoneNumberTest() {

        Phone _11Phone = new Phone("010-2824-8203", true);
        Assertions.assertEquals("****", _11Phone.getMiddle());

        Phone _10Phone = new Phone("010-284-8203", true);
        Assertions.assertEquals("***", _10Phone.getMiddle());

        Assertions.assertAll(() -> {

            Assertions.assertThrows(PhoneException.class, () ->
                    new Phone("01-2824-8203", true)
            );

            Assertions.assertThrows(PhoneException.class, () ->
                    new Phone("010-24-8203", true)
            );

            Assertions.assertThrows(PhoneException.class, () ->
                    new Phone("010-284-803", true)
            );

            Assertions.assertThrows(NullPointerException.class, () ->
                    new Phone(null, true)
            );

        });

    }

}
