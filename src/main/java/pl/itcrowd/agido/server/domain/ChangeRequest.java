package pl.itcrowd.agido.server.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table(name = "CHANGE_REQUEST",
    uniqueConstraints = @UniqueConstraint(name = "UNIQ___CHANGE_REQUEST___NUMBER_IN_PROJECT", columnNames = {"PROJECT_ID", "CR_NUMBER"}))
@Entity
public class ChangeRequest extends ProjectMember {
//
//    @NotNull
//    @Size(min = 1)
//    @Type(type = "org.hibernate.type.TextType")
//
//    @Column(name = "MESSAGE", nullable = false)
//    protected String message;
//
//    @OneToMany(mappedBy = "changeRequest")
//    private Set<HistoryPackage> changedPackages;
//
//    @OneToMany(mappedBy = "changeRequest")
//    private Set<HistoryScreen> changedScreens;
//
//    @OneToMany(mappedBy = "changeRequest")
//    private Set<HistoryTest> changedTests;
//
//    @OneToMany(mappedBy = "changeRequest")
//    private Set<HistoryUsecase> changedUsecases;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "COMMIT_DATE", nullable = true)
//    private Date commitDate;
//
//    @Id
//    @GeneratedValue(generator = "CHANGE_REQUEST_ID_SEQUENCE", strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    //    TODO crNumber should be not-null only after commitDate beconse non-null
//    @NotNull
//    @Min(1)
//    @Column(name = "CR_NUMBER", nullable = false)
//    private long number;
//
//    public Set<HistoryPackage> getChangedPackages()
//    {
//        if (null == changedPackages) {
//            changedPackages = new HashSet<HistoryPackage>();
//        }
//        return changedPackages;
//    }
//
//    public Set<HistoryScreen> getChangedScreens()
//    {
//        if (null == changedScreens) {
//            changedScreens = new HashSet<HistoryScreen>();
//        }
//        return changedScreens;
//    }
//
//    public Set<HistoryTest> getChangedTests()
//    {
//        if (null == changedTests) {
//            changedTests = new HashSet<HistoryTest>();
//        }
//        return changedTests;
//    }
//
//    public Set<HistoryUsecase> getChangedUsecases()
//    {
//        if (null == changedUsecases) {
//            changedUsecases = new HashSet<HistoryUsecase>();
//        }
//        return changedUsecases;
//    }
//
//    public Date getCommitDate()
//    {
//        return commitDate;
//    }
//
//    public void setCommitDate(Date commitDate)
//    {
//        this.commitDate = commitDate;
//    }
//
//    public Long getId()
//    {
//        return id;
//    }
//
//    public void setId(Long id)
//    {
//        this.id = id;
//    }
//
//    public String getMessage()
//    {
//        return message;
//    }
//
//    public void setMessage(String name)
//    {
//        this.message = name;
//    }
//
//    public long getNumber()
//    {
//        return number;
//    }
//
//    public void setNumber(long number)
//    {
//        this.number = number;
//    }
//
//    @Override
//    public boolean equals(Object o)
//    {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof ChangeRequest)) {
//            return false;
//        }
//
//        ChangeRequest that = (ChangeRequest) o;
//
//        return !(getId() == null || !getId().equals(that.getId()));
//    }
//
//    @Override
//    public int hashCode()
//    {
//        return getId() != null ? getId().hashCode() : super.hashCode();
//    }
}
