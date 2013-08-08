package pl.itcrowd.agido.server.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Audited(auditParents = {NamedPackageable.class, ProjectMember.class})
@Entity
@Table(name = "USECASE")
public class Usecase extends NamedPackageable {

    @OneToMany(mappedBy = "usecase")
    private List<Attachment> attachments;

    @Id
    @GeneratedValue(generator = "USECASE_ID_SEQUENCE", strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "SUMMARY")
    private String summary;

    @ManyToMany(mappedBy = "usecases")
    private Set<Test> tests;

    @ManyToMany(mappedBy = "usecases")
    private Set<Screen> screens;

    public List<Attachment> getAttachments()
    {
        if (attachments == null) {
            attachments = new ArrayList<Attachment>();
        }
        return attachments;
    }

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

    public Set<Test> getTests()
    {
        if (null == tests) {
            tests = new HashSet<Test>();
        }
        return tests;
    }

    public Set<Screen> getScreens()
    {
        if (null == screens) {
            screens = new HashSet<Screen>();
        }
        return screens;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Usecase)) {
            return false;
        }

        Usecase that = (Usecase) o;

        return !(getId() == null || !getId().equals(that.getId()));
    }

    @Override
    public int hashCode()
    {
        return getId() != null ? getId().hashCode() : super.hashCode();
    }
}
