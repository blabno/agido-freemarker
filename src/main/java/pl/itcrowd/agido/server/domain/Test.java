package pl.itcrowd.agido.server.domain;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Audited(auditParents = {NamedPackageable.class, ProjectMember.class})
@Entity
@Table(name = "TEST")
public class Test extends NamedPackageable {

    private Long id;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "SUMMARY")
    private String summary;

    @ForeignKey(name = "FK___USECASE_TEST___TEST", inverseName = "FK___USECASE_TEST___USECASE")
    @ManyToMany
    @JoinTable(name = "USECASE_TEST",
        joinColumns = @JoinColumn(name = "TEST_ID"),
        inverseJoinColumns = @JoinColumn(name = "USECASE_ID"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"TEST_ID", "USECASE_ID"}))
    private Set<Usecase> usecases;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public Set<Usecase> getUsecases()
    {
        if (null == usecases) {
            usecases = new HashSet<Usecase>();
        }
        return usecases;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Test)) {
            return false;
        }

        Test that = (Test) o;

        return !(getId() == null || !getId().equals(that.getId()));
    }

    @Override
    public int hashCode()
    {
        return getId() != null ? getId().hashCode() : super.hashCode();
    }
}
