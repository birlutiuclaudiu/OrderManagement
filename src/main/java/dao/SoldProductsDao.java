package dao;

import connection.ConnectionFactory;
import model.Product;
import model.SoldProducts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Aceasa clasa reprezinta clasa pentru oprartiile de manipulare a tabelei SoldProducts. Aceasta extinde clasa AbstractDao
 * pentru a mosteni metodele operatiilor de tipul CRUD
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class SoldProductsDao extends AbstractDao<SoldProducts>{
    /**
     * Metoda ce creeaza un statemnt SQL prin care se obtine informatii despre produsele vandute intr-o comanda prin
     * aplicarea de join intre tabele
     * @return stamentul SQL
     */
   private String createQueryFind() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT product.id_product, product.name, soldproducts.quantity, soldproducts.price ");
        sb.append(" FROM  warehouse.soldproducts, warehouse.product ");
        sb.append(" WHERE soldproducts.id_soldproducts=product.id_product AND id_order=? ");
        return sb.toString();
    }
    /**
     * Metoda ce ca va executa statemntul SQL prin care se obtine informatii despre produsele vandute intr-o comanda prin
     * aplicarea de join intre tabele
     * @return lista de produse vandute pe comanda cu id-ul transmis ca parametru
     * @param id_order id-ul comenzii ale carei produse se cauta
     */
   public List<Product> findProductsOfOrder(int id_order){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
       try {
           connection = ConnectionFactory.getConnection();
           statement = connection.prepareStatement(createQueryFind());
           statement.setInt(1, id_order);
           resultSet = statement.executeQuery();

           return createList(resultSet);
       } catch (SQLException e) {
          e.printStackTrace();
       } finally {
           ConnectionFactory.close(resultSet);
           ConnectionFactory.close(statement);
           ConnectionFactory.close(connection);
       }
       return null;
   }

    /**
     * Metoda ce va extrage rezultatele din cursorul de rezultate
     * @param resultSet cursorul rezultat in urma executitei statement-ului SQL
     * @return o lista de produse
     */
   private List<Product> createList(ResultSet resultSet){
       List<Product> products=new ArrayList<Product>();
       try{
           while(resultSet.next()){
               int id_product=resultSet.getInt("id_product");
               String name=resultSet.getString("name");
               int quantity= resultSet.getInt("quantity");
               float price=resultSet.getFloat("price");
               products.add(new Product(id_product,name,quantity,price));
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       return products;
   }
/////////////////////////////////////////////////TOTAL PRICE//////////////////////////////////////////////////////////

    /**
     * Metoda ce creeaza un statemnt SQL prin care se obtine pretul total pentru o comanda
     * @return stamentul SQL
     */
    private String createQueryTotalPrice() {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT SUM(price) as totalprice ");
        sb.append(" FROM  warehouse.soldproducts");
        sb.append(" WHERE id_order=? ");
        return sb.toString();
    }
    /**
     * Metoda ce va executa statementul SQL de calcularea a pretului total al unei comenzi
     * @param id_order id_ul comenzii pentru calcul pretului sau total
     * @return pretul total al comenzii
     */
    public Float findTotalPrice(int id_order){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(createQueryTotalPrice());
            statement.setInt(1,id_order);
            resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getFloat("totalprice");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
