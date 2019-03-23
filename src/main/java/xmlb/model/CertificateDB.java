package xmlb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CertificateDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String alias;

    @Column
    private String sn;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private String signedByAlias;

    public CertificateDB() {
        super();
    }

    public CertificateDB(String alias, String sn, Date startDate, Date endDate, String signedByAlias) {
        this.alias = alias;
        this.sn = sn;
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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
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
}
