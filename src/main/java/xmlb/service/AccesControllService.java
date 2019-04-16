package xmlb.service;

import org.springframework.stereotype.Service;

@Service
public class AccesControllService {

    public Boolean hasAccess(String s) {
        String[] parts = s.split("/");
        System.out.println("/" + s.substring(3 + parts[0].length() + parts[1].length()+parts[2].length(),s.length()));
        return true;
    }
}
