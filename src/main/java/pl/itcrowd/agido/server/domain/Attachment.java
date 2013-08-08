package pl.itcrowd.agido.server.domain;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Audited
@Entity
public class Attachment implements Serializable {

    public static final int DATA_MAX_SIZE = 1000000;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CONTENT_TYPE", length = 255, nullable = false)
    private String contentType;

    @NotNull
    @Size(max = DATA_MAX_SIZE)
    @Column(name = "DATA", nullable = true, length = DATA_MAX_SIZE)
    private byte[] data;

    @Id
    @GeneratedValue(generator = "ATTACHMENT_ID_SEQUENCE", strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "LENGTH", nullable = false)
    private int length;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPLOAD_DATE", nullable = false)
    private Date uploadDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USECASE_ID")
    private pl.itcrowd.agido.server.domain.Usecase usecase;

    public String getContentType()
    {
        return contentType;
    }

    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    public byte[] getData()
    {
        return data;
    }

    public void setData(byte[] data)
    {
        this.data = data;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getUploadDate()
    {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate)
    {
        this.uploadDate = uploadDate;
    }

    public Usecase getUsecase()
    {
        return usecase;
    }

    public void setUsecase(Usecase usecase)
    {
        this.usecase = usecase;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Attachment)) {
            return false;
        }

        Attachment that = (Attachment) o;

        return !(getId() == null || !getId().equals(that.getId()));
    }

    @Override
    public int hashCode()
    {
        return getId() != null ? getId().hashCode() : super.hashCode();
    }
}
