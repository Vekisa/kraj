package xmlb.dto;

import java.util.Date;

public class CertificateDTO {

    private String parent;
    private String country;
    private String state;
    private String locality;
    private String organization;
    private String organizationUnit;
    private String commonName;
    private Date startDate;
    private Date endDate;
    private String alias;
    private String password;
    private Boolean leaf;

    public CertificateDTO() {
        super();
    }

    public CertificateDTO(String parent, String country, String state, String locality, String organization, String organizationUnit, String commonName, Date startDate, Date endDate, String alias, String password) {
        this.parent = parent;
        this.country = country;
        this.state = state;
        this.locality = locality;
        this.organization = organization;
        this.organizationUnit = organizationUnit;
        this.commonName = commonName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.alias = alias;
        this.password = password;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getParent() { return parent; }

    public void setParent(String parent) { this.parent = parent; }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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

}
