package com.spacewalk.testwalk.entity;

import com.spacewalk.testwalk.dto.request.CommentCreateRequestDto;
import com.spacewalk.testwalk.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "post_id", nullable = false)

    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at", nullable = false, updatable = false)
    // 이 필드가 "created_at"에 맵핑되고, null이 불가능하며, 업데이트가 불가능
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Comment(Post post, User user, CommentCreateRequestDto dto) {
        this.post = post;
        this.user = user;
        this.text = dto.getText();
    }

    @PrePersist
    //엔티티가 생성될 때 실행되는 로직
    protected void onCreate() {
        // 엔티티를 현재 날짜와 시간을 값으로 설정
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    //엔티티가 업데이트될 때 실행되는 로직
    protected void onUpdate() {

        updatedAt = LocalDateTime.now();
    }

}
