package ra.controller;

import ra.model.Catalog;
import ra.service.CatalogService;

import java.util.List;

public class CatalogController {
    private CatalogService catalogService ;

    public CatalogController() {
        catalogService = new CatalogService();
    }

    public List<Catalog> findAll(){
        return catalogService.findAll();
    }
    public void getAll(){
        catalogService.getAll();
    }

    public Catalog findById(int id){
        return catalogService.findById(id);
    }

    public void save(Catalog catalog) {
        catalogService.save(catalog);
    }
    public void delete(int id) {
        catalogService.delete(id);
    }
    public void deleteCatalog(){
        catalogService.deleteCatalog();
    }

    public List<Catalog> searchByName(String searchName) {
        return catalogService.searchByName(searchName);
    }

    public Catalog createCatalog(){
         return catalogService.createCatalog();
    }

    public void updateCatalog(){
        catalogService.updateCatalog();
    }
    public void searchCatalogByName(){
        catalogService.searchCatalogByName();
    }
}
