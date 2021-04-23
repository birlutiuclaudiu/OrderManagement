package bll;

import dao.OrderDao;
import model.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Aceasa clasa reprezinta clasa de businesslogic pentru order. Prin intermediul ei se va cere actualizarea tabelului
 * pentru comenzi
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class Orderbll {
    /**
     * Camp ce va contine instanta clasei ce implementeaza metodele de manipulare a bazei de date pentru tabela order
     */
    private OrderDao orderDAO;

    /**
     * Constructor fara parametru
     * Se vor instantia obiectul ce va apela metodele de modificare a tabelei order
     */
    public Orderbll(){
        orderDAO=new OrderDao();
    }

    /**
     * Metoda ce va cauta o comanda in baza de date dupa id
     * @param id reprezinta id_ul comaenzii de cautat
     * @return returneaza un obiect de tip Order extras din tabela Order sau null in cazul in care nu e nicio comanda cu
     * id-ul dat
     */
    public Order findOrderById(int id){
        Order product=orderDAO.findById(id);
        if(product==null)
            throw new NoSuchElementException("The order with id " +id+" was not found!");
        return product;
    }

    /**
     * Metoda ce va extrage toate comenzile din tabel Order
     * @return va returna o lista de Order
     */
    public List<Order> findAll(){
        List<Order> orders=orderDAO.findAll();
        if(orders==null)
            throw new NoSuchElementException("Empty table");
        return orders;
    }
    /**
     * Metoda ce va insera o comanda noua in baza de date, iar daca nu se va reusi va arunca exceptia corespunzatoare
     * @param order obiect de tipul Order; valorile campurilor lui vor fi inserate in tabelul Order
     * @return returneaza coamanda in caz de succes
     * @throws SQLException s
     * @throws IllegalAccessException s
     */
    public Order insert(Order order) throws SQLException, IllegalAccessException{
        orderDAO.insert(order);
        return order;
    }

    /**
     * Metoda ca va cere actualizarea unei comenzi cu noile valori ale campului
     * @param order obiectul de tip Order
     * @return in caz de succes va returna inapoi comanda, daca nu va arunca o exceptie SQL
     * @throws SQLException s
     * @throws IllegalAccessException s
     */
    public Order update(Order order) throws SQLException, IllegalAccessException{
        orderDAO.update(order);
        return order;
    }

    /**
     * Metoda va cere stergerea comenzii cu id-ul transmis ca parametru
     * @param id id-ul comenzii de sters din baza de date ;
     * @throws SQLException se va arunca in cazul unei erori/exceptii la stergerea din baza de date
     * @throws IllegalAccessException s
     */
    public void delete(int id) throws SQLException, IllegalAccessException{
        orderDAO.delete(id);
    }

    /**
     * Metoda va cere aflarea urmatorului id valid pentru o noua comanda
     * @return un numar intreg ce reprezinta un id eligibile pentru o noua comanda
     */
    public Integer findNextIdOrder(){
        return orderDAO.findNextIdOrder();
    }
}
