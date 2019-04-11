package xmlb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="certificate")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String alias;

    @Column
    private String serialNumber;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private String signedByAlias;

    @Column
    private Boolean revoked;

    @Column
    private Boolean isLeaf;

    @ManyToOne
    private Company company;

    @OneToOne
    private Communication communication;

    public Certificate() {
        super();
    }

    public Certificate(String alias, String serialNumber, Date startDate, Date endDate, String signedByAlias) {
        this.alias = alias;
        this.serialNumber = serialNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.signedByAlias = signedByAlias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSignedByAlias() {
        return signedByAlias;
    }

    public void setSignedByAlias(String signedByAlias) {
        this.signedByAlias = signedByAlias;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Boolean getRevoke() { return revoked; }

    public void setRevoke(Boolean revoked) { this.revoked = revoked; }

    public Boolean getLeaf() { return isLeaf; }

    public void setLeaf(Boolean isLeaf) { this.isLeaf = isLeaf; }
}
