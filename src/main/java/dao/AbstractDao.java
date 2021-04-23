package dao;

import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Aceasa clasa reprezinta clasa generica pentru opratiile CRUD. Aceasta va fi extinsa de clasele ce se ocupa cu
 * mainpularea diferitelor tabele.
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class AbstractDao<T> {
    /**
     * Camp ce reprezinta clasa obiectului ce extinde aceasta clasa generica
     */
    private final Class<T> type;

    /**
     * Constructor pentru clasa
     */
    public AbstractDao() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    //---------------------------------------FIND BY ID-----------------------------------------------------------------

    /**
     * Metoda ce va construi stringu pentru statemntul SQL pentru cautarea dupa id.
     * @param field contine numele field-ului din conditia WHERE
     * @return stringul pentru statement
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("warehouse.").append(type.getSimpleName());
        sb.append(" WHERE ").append(field).append(" =?");
        return sb.toString();
    }

    /**
     * Metoda ce va implementa logica de cautare dupa id intr-o tabela
     * @param id primeaste un intreg ce rreprezinta id-ul produsului de cautat
     * @return un obiect ce are campurile corespunzatoare tabelului in care se face interogarea
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id_"+type.getSimpleName());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
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
     * Metoda private ce extrage toate valorile cursorului rezultat in urma interogariii  tabelu-ul corespunzatoru tipului
     * Se foloseste tehnica de reflexie
     * se creeaza o instanta noua pe obiectul de tipul corespunzator subclasei; si se parcurg campurile clasei, se
     * citesc din baza de date si apoi vor fi setate dinamic prin metoda propertyDescriptor.getWriteMethod();
     * @param resultSet va returna o lista de obiecte ce au campurile din tabelul interogat
     * @return a
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        //se ia constructorul fara parametri
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | SQLException | InvocationTargetException
                                            | IntrospectionException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }
    //------------------------------------------FIND ALL-----------------------------------------------------------------------
    /**
     * Metoda ce va construi stringulpentru statemntul SQL pentru selectia totala.
     * @return stringul pentru statement
     */
    private String createFindAllStatement(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("warehouse."+type.getSimpleName());
        return sb.toString();
    }
    /**
     * Metoda ce va implementa logica de selectie a tuturor liniilor din tabelul corespunzator tipului pe care se face
     * interogarea
     * @return o lista de obiecte corespunzatorea rezultatelor interogarii
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createFindAllStatement();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
  //----------------------------------------INSERT IN TABLES----------------------------------------------------------------------------
    /**
     * Metoda ce va construi stringul pentru statemntul SQL pentru inserare in tabela.
     * @return stringul pentru statement
     * @param numberOfFields numarul de campuri
     */
   private String createInsertStatement(Integer numberOfFields){
        StringBuilder toReturn=new StringBuilder();
        toReturn.append("INSERT INTO ");
        toReturn.append("warehouse."+type.getSimpleName());
        toReturn.append(" VALUES(");
        for(int i=0; i<numberOfFields; i++){
            if(i!=0)
                toReturn.append(",?");
            else
                toReturn.append("?");
        }
        toReturn.append(");");
        return toReturn.toString();
   }

    /**
     * Metoda ce va executa statementul de inserare a unui obiect in tabelul corespunzator tipului
     * @param t premiste un obiect de tip concret (Client, Order, Product etc)
     * @return returneaza in caz de succes obiectul de inserat
     * @throws SQLException a
     * @throws IllegalAccessException a
     */
    public T insert(T t) throws SQLException, IllegalAccessException {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Field> fields= Arrays.asList(t.getClass().getDeclaredFields());
        String query = createInsertStatement(fields.size());
        connection = ConnectionFactory.getConnection();
        statement = connection.prepareStatement(query);
            int i=1;
            for(Field field: fields) {
                field.setAccessible(true);
                if (field.getType().getSimpleName().equals("Integer")) {
                    statement.setInt(i, (int) field.get(t));
                } else if (field.getType().getSimpleName().equals("Float")) {
                    statement.setFloat(i, (float) field.get(t));
                } else {
                    statement.setString(i, (String) field.get(t).toString());
                }
                i++;
            }
            statement.execute();
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
            return t;
    }

    //---------------------------------------------UPDATE-------------------------------------------------------------
    /**
     * Metoda ce va construi stringul pentru statemntul SQL pentru actulizarea tabelei.
     * @param fields fileds
     * @return stringul pentru statement
     */
    private String createUpdateStatement(List<Field> fields){
        StringBuilder toReturn=new StringBuilder();
        toReturn.append("UPDATE ");
        toReturn.append("warehouse."+type.getSimpleName());
        toReturn.append(" SET ");
        int i=1;
        //se vor modifica toate campurile inafara de primul
        for(Field field: fields){

            if(i!=1) {
                if (i == fields.size())
                    toReturn.append(field.getName() + "=?");
                else
                    toReturn.append(field.getName() + "=?,");
            }
            i++;
        }

        toReturn.append(" WHERE "+ fields.get(0).getName() +"=?");
        return toReturn.toString();
    }

    /**
     * Metoda ce va executa statementul de actualizare a unui obiect in tabelul corespunzator tipului
     * @param t primeste un obiect de tip concret (Client, Order, Product etc)
     * @return returneaza in caz de succes obiectul de actualizat
     * @throws SQLException a
     * @throws IllegalAccessException a
     */
    public T update(T t) throws SQLException, IllegalAccessException {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Field> fields= Arrays.asList(t.getClass().getDeclaredFields());
        String query = createUpdateStatement(fields);
        int id=0;

        connection = ConnectionFactory.getConnection();
        statement = connection.prepareStatement(query);
        int i=1;
        for(Field field: fields){
            field.setAccessible(true);
            if(i!=1) {
                if (field.getType().getSimpleName().equals("Integer")) {
                    statement.setInt(i-1, (int) field.get(t));
                } else if (field.getType().getSimpleName().equals("Float")) {
                    statement.setFloat(i-1, (float) field.get(t));
                } else
                    statement.setString(i-1, (String) field.get(t).toString());
            }else{
                id=(int) field.get(t);
            }
            i++;
        }
        statement.setInt(i-1,id);
        statement.execute();
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
        return t;
    }

    //----------------------------------------------DELETE----------------------------------------------------
    /**
     * Metoda ce va construi stringul pentru statemntul SQL pentru stergere unor linii din tabela dupa id.
     * @return stringul pentru statement
     */
    private String createDeleteStatement() {
        StringBuilder toReturn=new StringBuilder();
        toReturn.append("DELETE FROM ");
        toReturn.append("warehouse."+type.getSimpleName());
        toReturn.append(" WHERE id_"+type.getSimpleName()+"=?");
        return toReturn.toString();
    }
    /**
     * Metoda ce va executa statementul de stergere a obiectului cu id-ul transmis ca parametru din tabelul corespunzator tipului
     * @param id intreg ce reprezinta id-ul liniei de sters din tabel
     * In caz de eroare se va arunca o exceptie
     * @throws SQLException a
     * @throws IllegalAccessException a
     */
    public void delete(int id) throws SQLException, IllegalAccessException {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteStatement();

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            System.out.println(statement.toString());
            statement.execute();
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

}
