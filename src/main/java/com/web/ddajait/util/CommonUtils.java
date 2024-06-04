package com.web.ddajait.util;

import jakarta.servlet.http.HttpSession;

public class CommonUtils {
    public static final String FILE_EXTENSION_SEPARATOR = ".";

    public static String getFileName(String originalFileName) {
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        return originalFileName.substring(0, fileExtensionIndex); // 파일 이름
    }

    public static String buildFileName(String originalFileName) {
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR); // 파일 확장자 구분선
        String fileExtension = originalFileName.substring(fileExtensionIndex); // 파일 확장자
        String fileName = originalFileName.substring(0, fileExtensionIndex); // 파일 이름
        String now = String.valueOf(System.currentTimeMillis()); // 파일 업로드 시간

        return fileName + "_" + now + fileExtension;
    }

    public static Long checkSessionId(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            System.out.println("User ID from session: " + userId);
        } else {
            System.out.println("No User ID found in session.");
        }

        return userId;
    }

}
