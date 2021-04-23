package bll;

import dao.ProductDao;
import model.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Aceasa clasa reprezinta clasa de businesslogic pentru produs. Prin intermediul ei se va cere actualizarea tabelului
 * Product din baza de date
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class Productbll {
    /**
     * Camp ce va contine instanta clasei ce implementeaza metodele de manipulare a bazei de date pentru tabela Product
     */
    private ProductDao productDAO;
    /**
     * Constructor fara parametru
     * Se vor instantia  obiectul ce va apela metodele de de modificare a tabelei Product
     */
    public Productbll(){
        productDAO=new ProductDao();
    }

    /**
     * Metoda ce va cere cautarea ununi produs in baza de date dupa id
     * @param id reprezinta id_ul produsului de cautat
     * @return returneaza un obiect de tip Product extras din tabela Product sau null in cazul in care nu e niciun produs cu
     * id-ul dat
     */
    public Product findProductById(int id){
        Product product=productDAO.findById(id);
        if(product==null)
            throw new NoSuchElementException("The client with id " +id+" was not found!");
        return product;
    }

    /**
     * Metoda ce va cere extragerea tuturor produselor din tabela Product
     * @return va returna o lista de produse
     */
    public List<Product> findAll(){
        List<Product> products=productDAO.findAll();
        if(products==null)
            throw new NoSuchElementException("Empty table");
        return products;
    }

    /**
     * Metoda ce va insera un produs in baza de date, iar daca nu se va reusi va arunca exceptia corespunzatoare
     * @param product obiect de tipul Product; valorile campurilor lui vor fi inserate in tabelul Product
     * @return returneaza inapoi produsul in caz de succes
     * @throws SQLException se va arunca in cazul unei erori/exceptii la stergerea din baza de date
     * @throws IllegalAccessException s
     */
    public Product insert(Product product) throws SQLException, IllegalAccessException{
        productDAO.insert(product);
        return product;
    }
    /**
     * Metoda ca va cere actualizarea unui produs cu noile valori ale campului
     * @param product obiectul de tip Product
     * @return in caz de succes va returna inapoi produsul, daca nu va arunca o exceptie SQL
     * @throws SQLException se va arunca in cazul unei erori/exceptii la stergerea din baza de date
     * @throws IllegalAccessException s
     */
    public Product update(Product product) throws SQLException, IllegalAccessException{
        productDAO.update(product);
        return product;
    }
    /**
     * Metoda ca va cere stergerea unui produs cu id-ul transmis ca parametru
     * @param id id-ul produsului de sters din baza de date ;
     * @throws SQLException se va arunca in cazul unei erori/exceptii la stergerea din baza de date
     * @throws IllegalAccessException s */
    public void delete(int id) throws SQLException, IllegalAccessException{
        productDAO.delete(id);
    }
}
