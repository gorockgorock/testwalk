package com.spacewalk.testwalk.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    @Pattern(regexp = "^[a-zA-z0-9]{4,10}", message = "4자 이상 10자 이하영어 대소문자나 숫자만 가능합니다")
    private String username;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Pattern(regexp = "^[a-zA-z0-9]{8,15}", message = "8자 이상 15자 이하영어 대소문자나 숫자만 가능합니다")
    private String password;
    @Pattern(regexp = "^[0-9]{3,20}", message = "숫자만 입력해주세요")
    private String phone_number;

}