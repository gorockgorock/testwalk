package com.spacewalk.testwalk.dto.request;

import com.spacewalk.testwalk.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {
    private String title;
    private String content;
    private Category category;
    private String password;
}