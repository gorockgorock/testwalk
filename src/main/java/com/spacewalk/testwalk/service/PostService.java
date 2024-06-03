package com.spacewalk.testwalk.service;

import com.spacewalk.testwalk.dto.request.PostCreateRequestDto;
import com.spacewalk.testwalk.dto.request.PostUpdateRequestDto;
import com.spacewalk.testwalk.dto.response.PostCreateResponseDto;
import com.spacewalk.testwalk.dto.response.PostReadResponseDto;
import com.spacewalk.testwalk.dto.response.PostUpdateResponseDto;
import com.spacewalk.testwalk.entity.Post;
import com.spacewalk.testwalk.repository.PostRepository;
import com.spacewalk.testwalk.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PasswordEncoder passwordEncoder;
    private final PostRepository postRepository;

    @Transactional
    public PostCreateResponseDto createPost(User user, PostCreateRequestDto postCreateRequestDto) {
        Post post = postRepository.save(new Post(user,postCreateRequestDto));
        return new PostCreateResponseDto(post);
    }

    public List<PostReadResponseDto> readAll() {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostReadResponseDto> postListToDto = postList.stream()
                .map(m -> new PostReadResponseDto(m)).collect(Collectors.toList());
        return postListToDto;
    }

    public PostReadResponseDto readDetail(Long postId) throws NoSuchElementException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("해당 글을 찾을 수 없습니다."));
        return new PostReadResponseDto(post);
    }

    @Transactional
    public PostUpdateResponseDto update(User user, Long postId, PostUpdateRequestDto postUpdateRequestDto) throws NoSuchElementException{
        Post post = postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("해당 글을 찾을 수 없습니다."));

        if(Objects.equals(user.getId(),post.getUser().getId()) &&passwordEncoder.matches(postUpdateRequestDto.getPassword(),user.getPassword())){
            post.update(user, postUpdateRequestDto);
            return new PostUpdateResponseDto(post);
        }else{
            return new PostUpdateResponseDto(HttpStatus.UNAUTHORIZED,"권한이 없습니다.");
        }
    }

    @Transactional
    public void delete(User user,String password, Long postId) throws NoSuchElementException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("해당 글을 찾을 수 없습니다."));

        if(Objects.equals(user.getId(), post.getUser().getId()) && passwordEncoder.matches(password.toString(),user.getPassword())){
            postRepository.deleteById(postId);
        }else{
            System.out.println(new AuthenticationException("권한이 없습니다."));
        }
    }
}