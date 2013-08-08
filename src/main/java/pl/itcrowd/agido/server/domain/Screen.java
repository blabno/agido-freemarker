package pl.itcrowd.agido.server.domain;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Audited(auditParents = {NamedPackageable.class, ProjectMember.class})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "SCREEN_TYPE")
@DiscriminatorValue("SIMPLE")
public class Screen extends NamedPackageable {

    public static final int DATA_MAX_SIZE = 1000000;

    @Size(max = DATA_MAX_SIZE)
    @Column(name = "DATA", nullable = true, length = DATA_MAX_SIZE)
    private byte[] data;

    @ForeignKey(name = "FK___ENTRY_POINT___SCREEN", inverseName = "FK___ENTRY_POINT___ENTRY_POINT")
    @ManyToMany
    @JoinTable(name = "ENTRY_POINT",
        joinColumns = @JoinColumn(name = "SCREEN_ID"),
        inverseJoinColumns = @JoinColumn(name = "ENTRY_POINT_ID"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"SCREEN_ID", "ENTRY_POINT_ID"}))
    private Set<Screen> entryPoints;

    @Id
    @GeneratedValue(generator = "SCREEN_ID_SEQUENCE", strategy = GenerationType.IDENTITY)
    private Long id;

    @ForeignKey(name = "FK___USECASE_SCREEN___SCREEN", inverseName = "FK___USECASE_SCREEN___USECASE")
    @ManyToMany
    @JoinTable(name = "USECASE_SCREEN",
        joinColumns = @JoinColumn(name = "SCREEN_ID"),
        inverseJoinColumns = @JoinColumn(name = "USECASE_ID"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"SCREEN_ID", "USECASE_ID"}))
    private Set<Usecase> usecases;

    public byte[] getData()
    {
        return data;
    }

    public void setData(byte[] data)
    {
        this.data = data;
    }

    public Set<Screen> getEntryPoints()
    {
        if (entryPoints == null) {
            entryPoints = new HashSet<Screen>();
        }
        return entryPoints;
    }

    public void setEntryPoints(Set<Screen> entryPoints)
    {
        this.entryPoints = entryPoints;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Set<Usecase> getUsecases()
    {
        if (usecases == null) {
            usecases = new HashSet<Usecase>();
        }
        return usecases;
    }

    public void setUsecases(Set<Usecase> usecases)
    {
        this.usecases = usecases;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Screen)) {
            return false;
        }

        Screen that = (Screen) o;

        return !(getId() == null || !getId().equals(that.getId()));
    }

    @Override
    public int hashCode()
    {
        return getId() != null ? getId().hashCode() : super.hashCode();
    }
}
