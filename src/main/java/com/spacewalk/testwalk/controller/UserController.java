package com.spacewalk.testwalk.controller;

import com.spacewalk.testwalk.dto.request.SignupRequestDto;
import com.spacewalk.testwalk.dto.request.UserRequestDto;
import com.spacewalk.testwalk.dto.response.CommonResponseDto;
import com.spacewalk.testwalk.dto.response.UserResponseDto;
import com.spacewalk.testwalk.service.UserService;
import com.spacewalk.testwalk.user.UserDetailsImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        try {
            userService.signup(signupRequestDto);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity
                    .badRequest()
                    .body(new CommonResponseDto("중복된 사용자이름 입니다.", HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(new CommonResponseDto("회원가입 되었습니다", HttpStatus.CREATED.value()));
    }
    @GetMapping("/{username}")
    public ResponseEntity<CommonResponseDto> getUser(@PathVariable String username) {
        try {
            UserResponseDto responseDTO = userService.getUserDto(username);
            return ResponseEntity.ok().body(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponseDto> updateUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UserRequestDto userRequestDto) {
        try {
            UserResponseDto responseDTO = userService.updateUser(userDetails.getUser(),userRequestDto);
            return ResponseEntity.ok().body(responseDTO);
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return null;
        }
    }

    @RequestMapping(value="/logout", method=RequestMethod.GET)

    public void deleteCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("AUTHORIZATION_HEADER", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
