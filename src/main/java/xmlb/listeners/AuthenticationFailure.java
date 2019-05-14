package xmlb.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import xmlb.service.LoginService;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthenticationFailure implements ApplicationListener<AuthenticationFailureBadCredentialsEvent>{



        @Autowired
        private LoginService loginService;

        @Autowired
        private HttpServletRequest request;

        public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
            final String xfHeader = request.getHeader("X-Forwarded-For");
            if (xfHeader == null) {
                loginService.loginFailed(request.getRemoteAddr());
            } else {
                loginService.loginFailed(xfHeader.split(",")[0]);
            }
        }
}
