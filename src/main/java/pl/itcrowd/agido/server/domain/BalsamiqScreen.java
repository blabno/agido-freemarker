package pl.itcrowd.agido.server.domain;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Audited(auditParents = {NamedPackageable.class, ProjectMember.class, Screen.class})
@Entity
@DiscriminatorValue("BALSAMIQ")
public class BalsamiqScreen extends Screen {

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "SOURCE", nullable = true)
    private String source;

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }
}
