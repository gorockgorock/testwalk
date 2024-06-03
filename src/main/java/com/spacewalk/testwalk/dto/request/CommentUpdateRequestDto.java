package com.spacewalk.testwalk.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentUpdateRequestDto {
    private String text;
    private String username;
    private String password;
}
