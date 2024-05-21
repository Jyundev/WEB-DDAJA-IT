package com.web.ddajait.config.constant.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

   // Common
   INTERNAL_SERVER_ERROR(500, "C001", "internal server error"),
   INVALID_INPUT_VALUE(400, "C002", "invalid input type"),
   METHOD_NOT_ALLOWED(405, "C003", "method not allowed"),
   INVALID_TYPE_VALUE(400, "C004", "invalid type value"),
   BAD_CREDENTIALS(400, "C005", "bad credentials"),

   // Member
   MEMBER_NOT_EXIST(404, "M001", "member not exist"),
   USER_EMAIL_ALREADY_EXISTS(400, "M002", "user email already exists"),
   NO_AUTHORITY(403, "M003", "no authority"),
   NEED_LOGIN(401, "M004", "need login"),
   AUTHENTICATION_NOT_FOUND(401, "M005", "Security Context에 인증 정보가 없습니다."),
   MEMBER_ALREADY_LOGOUT(400, "M006", "member already logout"),

   // Auth
   REFRESH_TOKEN_INVALID(400, "A001", "refresh token invalid");

   private int status;
   private final String code;
   private final String message;

}
