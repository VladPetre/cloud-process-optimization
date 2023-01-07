package ro.phd.vsp.cpoauth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ro.phd.vsp.cpoauth.service.AuthService;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor

//TODO: remove this
@Slf4j
public class AuthController {

    private final AuthService authService;

    @GetMapping(value = "/{sid}")
    @ResponseStatus(HttpStatus.OK)
    public void authDevice(@RequestHeader(name = "Authorization") String bearerToken, @PathVariable String sid) {
        authService.authorize(bearerToken, sid);
    }


    //TODO: remove this
    @GetMapping("logs")
    public void logTest()
    {
        log.info("info");
        log.warn("warn");
        log.error("error");
    }
}
