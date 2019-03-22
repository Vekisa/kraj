package xmlb.model;

import javax.persistence.*;

@Entity
@Table(name="revokes")
public class Revoke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String alias;

    public Revoke(){}

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
