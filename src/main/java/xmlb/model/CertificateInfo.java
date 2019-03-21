package xmlb.model;

import java.util.Date;

public class CertificateInfo {

    private String country;
    private String state;
    private String loc;
    private String org;
    private String orgUnit;
    private String commName;
    private Date startDate;
    private Date endDate;
    private String alias;
    private String password;


    public CertificateInfo() {
        super();
    }

    public CertificateInfo(String country, String state, String loc, String org, String orgUnit, String commName, Date startDate, Date endDate,String alias,String password) {
        this.country = country;
        this.state = state;
        this.loc = loc;
        this.org = org;
        this.orgUnit = orgUnit;
        this.commName = commName;
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

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
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
}
