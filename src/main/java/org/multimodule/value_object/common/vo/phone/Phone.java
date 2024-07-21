package org.multimodule.value_object.common.vo.phone;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.Set;

/**
 * packageName    : org.multimodule.value_object.vo
 * fileName       : Phone
 * author         : AngryPig123
 * date           : 2024-07-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-21        AngryPig123       최초 생성
 */

@ToString(exclude = {"MASKING_CHAR", "FIRST_NUMBER_ACCESS_SET"})
public class Phone {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final String MASKING_CHAR = "*";

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final Set<String> FIRST_NUMBER_ACCESS_SET = Set.of("010", "011", "017", "019");

    @Getter
    private final String first;

    private final String middle;

    @Getter
    private final String last;

    @Getter
    private final boolean masking;

    public String getMiddle() {

        return masking ? MASKING_CHAR.repeat(middle.length()) : middle;

    }

    public String phoneNumber() {

        String maskedMiddle = masking ? MASKING_CHAR.repeat(this.middle.length()) : middle;
        return String.format("%s-%s-%s", first, maskedMiddle, last);

    }

    public Phone(String fullPhoneNumber, boolean masking) {

        validatePart(fullPhoneNumber, "phone number is cannot be null");
        String phoneNumber = fullPhoneNumber.replace("-", "");

        if (phoneNumber.length() == 10) {

            this.first = phoneNumber.substring(0, 3);
            this.middle = phoneNumber.substring(3, 6);
            this.last = phoneNumber.substring(6);

        } else if (phoneNumber.length() == 11) {

            this.first = phoneNumber.substring(0, 3);
            this.middle = phoneNumber.substring(3, 7);
            this.last = phoneNumber.substring(7);

        } else {
            throw new PhoneException("Invalid phone number length: " + phoneNumber.length());
        }

        firstValidate(this.first);
        this.masking = masking;

    }

    public Phone(String fullPhoneNumber) {

        this(fullPhoneNumber, false);

    }

    public Phone(String first, String middle, String last) {

        this(first, middle, last, false);

    }

    @JsonCreator
    public Phone(
            @JsonProperty("first") String first,
            @JsonProperty("middle") String middle,
            @JsonProperty("last") String last,
            @JsonProperty("masking") boolean masking
    ) {

        this.first = firstValidate(first);
        this.middle = middleAndLastValidate(middle, "middle");
        this.last = middleAndLastValidate(last, "last");
        this.masking = masking;

    }

    private String firstValidate(String part) {

        validatePart(part, "first");
        if (!FIRST_NUMBER_ACCESS_SET.contains(this.first)) {
            throw new PhoneException(this.first + " not exists first number list");
        }
        return part;

    }

    private String middleAndLastValidate(String part, String name) {

        validatePart(part, name);
        if (part.length() > 4 || 3 > part.length()) {
            throw new PhoneException(
                    String.format("%s length is min 3 max 4 not %d", part, part.length())
            );
        }
        return part;

    }

    private void validatePart(String part, String name) {

        Objects.requireNonNull(part, name + " cannot be null");
        if (part.isEmpty()) {
            throw new PhoneException(name + " cannot be empty");
        }

    }

}
