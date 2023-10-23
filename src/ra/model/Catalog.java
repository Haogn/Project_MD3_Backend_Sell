package ra.model;

import java.io.Serializable;

public class Catalog implements Serializable {

    private int catalogId ;
    private String catalogName ;

    public Catalog(){
    }

    public Catalog(int catalogId, String catalogName) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public void inputCatalog() {

    }

    public void displayCatalog(){
        System.out.println("Id : " +catalogId + " ➖ Ten danh muc : " + catalogName) ;
    }
}

