package com.example.controller.common.exception.member;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginFailException extends RuntimeException
{
    public LoginFailException(String message)
    {
        super(message);
    }
}
