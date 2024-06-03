package com.spacewalk.testwalk.dto.response;

import com.spacewalk.testwalk.user.User;

public class UserResponseDto extends CommonResponseDto{
    private Long id;
    private String username;
    private String email;
    private String password;
    private String phone_number;

    public UserResponseDto (User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.phone_number = user.getPhone_number();

    }
}
