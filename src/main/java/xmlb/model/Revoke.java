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

    @Column
    private Boolean leaf; //true list je, false povucen je

    public Revoke(){}

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }
}
