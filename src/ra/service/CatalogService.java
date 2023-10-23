package ra.service;

import ra.config.InpustMethods;
import ra.model.Catalog;
import ra.util.DataBase;

import java.util.ArrayList;
import java.util.List;

public class CatalogService {
    private List<Catalog> catalogs;
    private CartService cartService;
    private DataBase<Catalog> catalogData = new DataBase();

    public CatalogService(){
        List<Catalog> list = catalogData.readFormFile(DataBase.CATALOG_PATH);
        if (list == null) {
            list = new ArrayList<>();
        }
        this.catalogs = list;
    }

    public List<Catalog> findAll(){
        return catalogs;
    }
    public void getAll(){
        for (Catalog c : catalogs) {
            c.displayCatalog();
        }
    }
    public Catalog findById(int id){
        for (Catalog c : catalogs) {
            if (c.getCatalogId() == id ) {
                return c ;
            }
        }
        return null;
    }

    public void save(Catalog catalog) {
        if (findById(catalog.getCatalogId()) == null) {
            // add
            catalogs.add(catalog);
        } else {
            // update
            catalogs.set(catalogs.indexOf(findById(catalog.getCatalogId())), catalog);
        }
        // luu vao file
        catalogData.writeToFile(catalogs, DataBase.CATALOG_PATH);
    }



    public List<Catalog> searchByName(String searchName) {
        List<Catalog> catalogListSearch = new ArrayList<>();
        for (Catalog catalog : catalogs) {
            if (catalog.getCatalogName().toLowerCase().contains(searchName.toLowerCase())) {
                catalogListSearch.add(catalog);
            }
        }
        return catalogListSearch;
    }
    public void searchCatalogByName(){
        System.out.println("Nhap vao ten danh muc san pham ");
        String name = InpustMethods.getString();
        List<Catalog> catalogList = searchByName(name);

        if(catalogList.isEmpty()) {
            System.err.println("Khong tim thay ten danh muc san pham trong danh sach ");
        } else {
           for (Catalog c : catalogList){
               c.displayCatalog();
           }
        }
    }

    public Catalog createCatalog(){
        Catalog newCatalog = new Catalog() ;
        if (catalogs.isEmpty()) {
            newCatalog.setCatalogId(1);
        } else {
            newCatalog.setCatalogId(catalogs.get(catalogs.size() - 1).getCatalogId() + 1);
        }
        System.out.println("Nhap vao ten danh muc");
        newCatalog.setCatalogName(InpustMethods.getString());
        save(newCatalog);
        return newCatalog;
    }

    public void updateCatalog(){
        System.out.println("Nhap vao Id cua danh muc san pham ");
        int id = InpustMethods.getInteger();
        Catalog updateCatalog = findById(id) ;
        if (updateCatalog == null) {
            System.err.println("Khong tim thay danh muc san pham trong danh sach");
            return;
        }
        System.out.println("Nhap vao ten moi cua danh muc san pham");
        updateCatalog.setCatalogName(InpustMethods.getString());
        save(updateCatalog);
    }

    public void deleteCatalog(){
        System.out.println("Nhap vao Id danh muc san pham can xoa ");
        int id = InpustMethods.getInteger();
        Catalog deleteCatalog = findById(id) ;
        if (deleteCatalog == null) {
            System.err.println("Khong tim thay danh muc san pham trong danh sach");
        } else {
            delete(id);
            System.out.println("Xoa danh muc san pham thanh cong");
        }
    }
    public void delete(int id) {
        for (Catalog c : catalogs) {
            if (c.getCatalogId() == id) {
                catalogs.remove(c);
                catalogData.writeToFile(catalogs, DataBase.CATALOG_PATH);
                return;
            }
        }
    }
}
