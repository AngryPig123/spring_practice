package org.multimodule.value_object.common;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * packageName    : org.multimodule.value_object.common
 * fileName       : ValueObjectException
 * author         : AngryPig123
 * date           : 2024-07-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-21        AngryPig123       최초 생성
 */
public abstract class ValueObjectException extends RuntimeException {

    protected ValueObjectException() {
    }

    protected ValueObjectException(String message) {
        super(message);
    }

}
