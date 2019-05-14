package xmlb.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import xmlb.service.LoginService;

import javax.servlet.http.HttpServletRequest;

public class AuthenticationSuccess implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private LoginService loginService;

    @Autowired
    private HttpServletRequest request;

    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            loginService.loginSucceeded(request.getRemoteAddr());
        } else {
            loginService.loginSucceeded(xfHeader.split(",")[0]);
        }
    }
}
