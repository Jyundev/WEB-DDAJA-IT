package com.web.ddajait.config.error.custom;

import org.springframework.dao.DuplicateKeyException;

import com.web.ddajait.util.EmailValidator;

import lombok.Getter;

@Getter
public class DuplicateMemberException extends DuplicateKeyException{
    public DuplicateMemberException(String msg) {

        super(generateMessage(msg));
    }

    private static String generateMessage(String msg) {
        if (EmailValidator.isValid(msg)) {
            return String.format("%s는 이미 존재하는 Email입니다", msg);
        } else {
            return String.format("%s는 이미 존재하는 닉네임입니다", msg);

        }
    }
    
}
