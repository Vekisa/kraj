package xmlb.model;

import javax.persistence.*;

@Entity
@Table(name="revoke")
public class Revoke {

    @Id
    private String alias;


    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
