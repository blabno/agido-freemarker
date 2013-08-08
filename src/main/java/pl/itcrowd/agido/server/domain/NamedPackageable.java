package pl.itcrowd.agido.server.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.*;

@MappedSuperclass
public abstract class NamedPackageable extends ProjectMember {

    @NotNull
    @Size(max = 255)
    @Column(name = "NAME", nullable = false)
    protected String name;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    protected java.lang.Package parent;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public java.lang.Package getParent()
    {
        return parent;
    }

    public void setParent(java.lang.Package parent)
    {
        this.parent = parent;
    }
}
