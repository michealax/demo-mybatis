package com.shane.mybatis.common.i18n;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CustomLocalChangeInterceptor extends LocaleChangeInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        String newLocal = request.getHeader(getParamName());
        if (newLocal != null) {
            if (checkHttpMethod(request.getMethod())) {
                LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
                if (localeResolver == null) {
                    throw new IllegalStateException("no LocaleResolver found");
                }

                try {
                    localeResolver.setLocale(request, response, parseLocaleValue(newLocal));
                } catch (IllegalArgumentException e) {
                    if (isIgnoreInvalidLocale()) {
                        log.debug("Ignoring invalid locale value - {}", e.getMessage());
                    } else {
                        throw e;
                    }
                }
            }
            return true;
        }

        return super.preHandle(request, response, handler);
    }

    private boolean checkHttpMethod(String method) {
        String[] methods = getHttpMethods();
        if (ObjectUtils.isEmpty(methods)) {
            return true;
        }

        for (String m : methods) {
            if (m.equalsIgnoreCase(method)) {
                return true;
            }
        }
        return false;
    }
}
