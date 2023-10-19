package ra.model;

import java.io.Serializable;

public class Catalog implements Serializable {
    private static int nextId = 1 ;
    private int catalogId ;
    private String catalogName ;

    public Catalog(){
        this.catalogId = nextId++ ;
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

    }
}

