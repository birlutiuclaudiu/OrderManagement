package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Aceasa clasa reprezinta clasa modelului Order. Aceasta va contine campurile corespunzatoare tabelului din baza de date
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class Order {
    /**
     * Camp id-ul comenzii
     */
    private Integer id_order;
    /**
     * Camp id-ul clientului
     */
    private Integer id_client;
    /**
     * Camp pretul total
     */
    private Float total_price;
    /**
     * Camp pentru data generarii comenzii
     */
    private String date;

    /**
     * Constructor fara parametri
     */
    public Order(){
        super();
    }

    /**
     * Setarea campurilor;
     * Data se va seta cu data curenta
     * @param id_order s
     * @param id_client s
     * @param total_price s
     */
    public Order(int id_order, int id_client,float total_price) {
        this.id_order = id_order;
        this.id_client = id_client;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.date = dtf.format(now);
        this.total_price=total_price;
    }

    /**
     * Getter id_order
     * @return a
     */
    public Integer getId_order() {
        return id_order;
    }
    /**
     * Getter getId_client
     * @return a
     */
    public Integer getId_client() {
        return id_client;
    }
    /**
     * Getter getTotal_price
     * @return a
     */
    public Float getTotal_price() {
        return total_price;
    }
    /**
     * Getter getDate
     * @return date
     */

    public String getDate() {
        return date;
    }
    /**
     * Setter setId_order
     * @param id_order id-ul comenzii
     */
    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }
    /**
     * Setter setId_client
     *@param id_client  s
     */
    public void setId_client(Integer id_client) {
        this.id_client = id_client;
    }

    /**
     * Setter setTotal_price
     * @param total_price  s
     */
    public void setTotal_price(Float total_price) {
        this.total_price = total_price;
    }
    /**
     * Setter setDate
     * @param date date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return Metoda to string pentru a afisa comanda
     */
    @Override
    public String toString() {
        return "Order{" +
                "id_order=" + id_order +
                ", id_client=" + id_client +
                ", date=" + date +
                ", totalPrice=" + total_price +
                '}';
    }
}
