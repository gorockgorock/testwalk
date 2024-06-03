package com.spacewalk.testwalk.entity;

import com.spacewalk.testwalk.dto.request.PostCreateRequestDto;
import com.spacewalk.testwalk.dto.request.PostUpdateRequestDto;
import com.spacewalk.testwalk.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "post")
public class Post extends DateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long postId;

    @NotNull
    @Column(nullable = false)
    public String title;

    @NotNull
    @Column(nullable = false)
    public String content;

    @Enumerated(EnumType.STRING)
    public Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private User user;

    public Post(User user, PostCreateRequestDto postCreateRequestDto){
        this.user = user;
        this.title = postCreateRequestDto.getTitle();
        this.content = postCreateRequestDto.getContent();
        this.category = postCreateRequestDto.getCategory();
    }

    public void update(User user, PostUpdateRequestDto postUpdateRequestDto) {
        this.user = user;
        this.title = postUpdateRequestDto.getTitle();
        this.content = postUpdateRequestDto.getContent();
        this.category = postUpdateRequestDto.getCategory();
    }
}
