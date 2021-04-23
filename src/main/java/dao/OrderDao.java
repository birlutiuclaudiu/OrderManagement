package dao;

import connection.ConnectionFactory;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Aceasa clasa reprezinta clasa pentru oprartiile de manipulare a tabelei Order. Aceasta extinde clasa AbstractDao
 * pentru a mosteni metodele operatiilor de tipul CRUD
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class OrderDao extends AbstractDao<Order> {
    /**
     * Metoda ce construcieste stringul pentru statementul de aflare a valorii maxime a id-urilor comenzilor din tabela
     * @return statementul SQL
     */
    private String createQueryMax() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT MAX(id_order) AS maxim ");
        sb.append(" FROM  warehouse.order ");
        return sb.toString();
    }

    /**
     * Metoda ce determina prin aflarea maximului dintre id-urile comenzilor, un id nou valid pentru o noua comanda
     * @return va returna id-ul mazxim din tabel +1
     */
    public Integer findNextIdOrder(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(createQueryMax());
            resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("maxim")+1;
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
