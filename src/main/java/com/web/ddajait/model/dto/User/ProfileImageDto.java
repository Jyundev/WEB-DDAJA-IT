package com.web.ddajait.model.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileImageDto {

    private String profileImage;

    @Override
    public String toString() {
        return "profileImage : " + profileImage;
    }

}
