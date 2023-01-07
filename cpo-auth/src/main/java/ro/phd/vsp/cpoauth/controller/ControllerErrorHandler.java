package ro.phd.vsp.cpoauth.controller;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.phd.vsp.cpocommon.exception.UnauthorizedException;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ControllerErrorHandler {

    @ExceptionHandler({UnauthorizedException.class, JwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorizedException(Exception ex) {
        log.error("Unauthorized: {}", ex.getMessage());
        return "Unauthorized - " + ex.getMessage();
    }

}
