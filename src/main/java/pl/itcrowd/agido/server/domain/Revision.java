package pl.itcrowd.agido.server.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Immutable;
import org.hibernate.envers.ModifiedEntityNames;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Immutable
@RevisionEntity
@Entity
@Table(name = "REVISION")
public class Revision implements Serializable {

    @Id
    @SequenceGenerator(name = "REVISION_ID_SEQUENCE", sequenceName = "REVISION_ID_SEQUENCE", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "REVISION_ID_SEQUENCE", strategy = GenerationType.SEQUENCE)
    @RevisionNumber
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "REVCHANGES", joinColumns = @JoinColumn(name = "REV"))
    @Column(name = "ENTITYNAME")
    @Fetch(FetchMode.JOIN)
    @ModifiedEntityNames
    private Set<String> modifiedEntityNames = new HashSet<String>();

    @RevisionTimestamp
    @Column(name = "revtstmp", nullable = false)
    private long timestamp;

    public Long getId()
    {
        return id;
    }

    public Set<String> getModifiedEntityNames()
    {
        return modifiedEntityNames;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    @Override
    public String toString()
    {
        return "Revision{" +
            "timestamp=" + timestamp +
            ", id=" + id +
            '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Revision)) {
            return false;
        }

        Revision that = (Revision) o;

        return !(getId() == null || !getId().equals(that.getId()));
    }

    @Override
    public int hashCode()
    {
        return getId() != null ? getId().hashCode() : super.hashCode();
    }
}
