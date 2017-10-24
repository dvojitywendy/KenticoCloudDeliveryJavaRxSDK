package delivery.models.type;


import java.util.Date;

public class ContentTypeSystemAttributes {

    private String id;
    private String name;
    private String codename;
    private Date lastModified;

    public ContentTypeSystemAttributes(
            String id,
            String name,
            String codename,
            Date lastModified
    ){
        this.id = id;
        this.name = name;
        this.codename = codename;
        this.lastModified = lastModified;
    }


    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCodename() {
        return this.codename;
    }

    public Date getLastModified() {
        return this.lastModified;
    }
}
