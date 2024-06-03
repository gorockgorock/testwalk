package com.spacewalk.testwalk.dto.response;

import com.spacewalk.testwalk.entity.Category;
import com.spacewalk.testwalk.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostReadResponseDto {
    private Long postId;
    private String title;
    private String content;
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostReadResponseDto(Post post){
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.category = post.getCategory();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}