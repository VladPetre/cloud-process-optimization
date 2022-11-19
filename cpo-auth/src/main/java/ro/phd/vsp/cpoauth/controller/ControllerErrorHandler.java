package ro.phd.vsp.cpoauth.controller;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ro.phd.vsp.cpocommon.exception.UnauthorizedException;

@ControllerAdvice
@Slf4j
public class ControllerErrorHandler {

    @ExceptionHandler({UnauthorizedException.class, JwtException.class})
    public ResponseEntity<String> handleUnauthorizedException(Exception ex) {
        log.error("Unauthorized: {}", ex.getMessage());
        return new ResponseEntity<>("Unauthorized - " + ex.getMessage(), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

}
