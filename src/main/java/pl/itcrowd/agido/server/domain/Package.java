package pl.itcrowd.agido.server.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Audited(auditParents = {NamedPackageable.class, ProjectMember.class})
@Entity
public class Package extends NamedPackageable {

    @Column(name = "HAS_CHILDREN", nullable = false)
    private boolean hasChildren;

    @Id
    @GeneratedValue(generator = "PACKAGE_ID_SEQUENCE", strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private Set<Screen> screens;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private Set<Package> subpackages;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private Set<Test> tests;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private Set<Usecase> usecases;

    public Long getId()

    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Set<Screen> getScreens()
    {
        if (null == screens) {
            screens = new HashSet<Screen>();
        }
        return screens;
    }

    public Set<Package> getSubpackages()
    {
        if (null == subpackages) {
            subpackages = new HashSet<Package>();
        }
        return subpackages;
    }

    public Set<Test> getTests()
    {
        if (null == tests) {
            tests = new HashSet<Test>();
        }
        return tests;
    }

    public Set<Usecase> getUsecases()
    {
        if (null == usecases) {
            usecases = new HashSet<Usecase>();
        }
        return usecases;
    }

    public boolean isHasChildren()
    {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren)
    {
        this.hasChildren = hasChildren;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Package)) {
            return false;
        }

        Package that = (Package) o;

        return !(getId() == null || !getId().equals(that.getId()));
    }

    @Override
    public int hashCode()
    {
        return getId() != null ? getId().hashCode() : super.hashCode();
    }
}
