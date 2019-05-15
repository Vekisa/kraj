package xmlb.model.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "endpoint")
public class EndPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 191)
    @Column
    private String url;

    @Size(max = 191)
    @Column
    private String method;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    public EndPoint(){
        roles = new ArrayList<>();
    }

    public EndPoint(String url, String method){
        this.setUrl(url);
        this.setMethod(method);
        roles = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
