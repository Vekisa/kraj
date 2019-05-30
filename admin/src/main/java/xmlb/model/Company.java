package xmlb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import xmlb.model.User.User;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30)
    @Column
    private String name;

    @Column
    @JsonIgnore
    private String filePath;

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<User> admins;

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<Certificate> certificates;

    public Company() {
        admins = new ArrayList<>();
        certificates = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
