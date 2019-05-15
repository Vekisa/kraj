package xmlb.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    @Size(max = 191)
    @Column
    private String signedByAlias;

    @Size(max = 191)
    @Column
    private String signedBySerialNumber;

    @Size(max = 191)
    @Column
    private String country;

    @Size(max = 191)
    @Column
    private String state;

    @Size(max = 191)
    @Column
    private String locality;

    @Size(max = 191)
    @Column
    private String organizationUnit;

    @Size(max = 191)
    @Column
    private String commonName;

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

    public Certificate(String alias, String serialNumber, Date startDate, Date endDate, String signedByAlias,String signedBySerialNumber, Boolean revoked, Boolean isLeaf,String country, String state, String locality,
                       String organizationUnit, String commonName) {
        this.alias = alias;
        this.serialNumber = serialNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.signedByAlias = signedByAlias;
        this.signedBySerialNumber = signedBySerialNumber;
        this.revoked = revoked;
        this.isLeaf = isLeaf;
        this.country = country;
        this.state = state;
        this.locality = locality;
        this.setOrganizationUnit(organizationUnit);
        this.setCommonName(commonName);
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

    public Boolean getRevoke() { return getRevoked(); }

    public void setRevoke(Boolean revoked) { this.setRevoked(revoked); }

    public Boolean getLeaf() { return isLeaf; }

    public void setLeaf(Boolean isLeaf) { this.isLeaf = isLeaf; }

    public Boolean getRevoked() {
        return revoked;
    }

    public void setRevoked(Boolean revoked) {
        this.revoked = revoked;
    }

    public Communication getCommunication() {
        return communication;
    }

    public void setCommunication(Communication communication) {
        this.communication = communication;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(String organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getSignedBySerialNumber() {
        return signedBySerialNumber;
    }

    public void setSignedBySerialNumber(String signedBySerialNumber) {
        this.signedBySerialNumber = signedBySerialNumber;
    }
}
