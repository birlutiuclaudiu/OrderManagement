package bll;

import dao.SoldProductsDao;
import model.Product;
import model.SoldProducts;

import java.sql.SQLException;
import java.util.List;

/**
 * Aceasa clasa reprezinta clasa de businesslogic pentru produsele vandute. Prin intermediul ei se va cere actualizarea tabelului
 * pentru gestionarea produslelor vandute
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class SoldProductsbll {
    /**
     * Camp ce va contine instanta clasei ce implementeaza metodele de manipulare a bazei de date pentru tabele produsele
     * vandute
     */
    private SoldProductsDao soldProductsDao;

    /**
     * Constructor fara parametru
     * Se va instantia obiectul ce va apela metodele de modificare a tabelei soldproducts
     */
    public SoldProductsbll(){
        soldProductsDao=new SoldProductsDao();
    }

    /**
     * Metoda ce va cere extragerea tuturor produselor din tabela soldproduct pentru comanda cu id-ul transmis ca parametru
     * @return va returna o lista de produse vandute in comanda cu id-ul dat
     * @param id_order id
     */
    public List<Product> findProductsOfOrder(int id_order){
        return soldProductsDao.findProductsOfOrder(id_order);
    }
    /**
     * Metoda ce va insera un produs in tabela de produse vandute, iar daca nu se va reusi va arunca exceptia corespunzatoare
     * @param product obiect de tipul SoldProducts; valorile campurilor lui vor fi inserate in tabelul Soldproducts
     * @return returneaza inapoi produsul in caz de succes
     * @throws SQLException se va arunca in cazul unei erori/exceptii la stergerea din baza de date
     * @throws IllegalAccessException s
     */
    public SoldProducts insert(SoldProducts product) throws SQLException, IllegalAccessException{
        soldProductsDao.insert(product);
        return product;
    }

    /**
     * Metoda ce va cere calcularea pretului total pentru o comanda din tabela Soldproducts
     * @param id_order cid-ul comenzii
     * @return returneaza in caz de succes pretul total al comenzii
     * @throws SQLException se va arunca in cazul unei erori/exceptii la stergerea din baza de date
     * @throws IllegalAccessException s
     */
    public Float findTotalPrice(int id_order) throws SQLException, IllegalAccessException{
        return soldProductsDao.findTotalPrice(id_order);
    }

    /**
     * Metoda ca va cere actualizarea unui produs vandut cu noile valori ale campului
     * @param soldProducts obiectul de tip SoldProducts ce va fi actulizat in tabela
     *  in caz de succes va returna inapoi produsul, daca nu va arunca o exceptie SQL
     * @throws SQLException se va arunca in cazul unei erori/exceptii la stergerea din baza de date
     * @throws IllegalAccessException s
     */
    public void update(SoldProducts soldProducts) throws SQLException, IllegalAccessException {
        soldProductsDao.update(soldProducts);
    }


}
