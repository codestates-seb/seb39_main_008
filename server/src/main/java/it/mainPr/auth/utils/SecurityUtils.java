package it.mainPr.auth.utils;

import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public SecurityUtils() {
    }

    public static String getCurrentMemberEmail() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null || authentication.getName().equals("anonymousUser")) {
            throw new BusinessLogicalException(ExceptionCode.NO_AUTHORIZED);
        }

        return (String)authentication.getPrincipal();
    }
}