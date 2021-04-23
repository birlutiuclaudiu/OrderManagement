package model;

/**
 * Aceasa clasa reprezinta clasa modelului SoldProducts (produse vandute). Aceasta va contine campurile corespunzatoare
 * tabelului din baza de date
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class SoldProducts {
    /**
     * Camp id_produs
     */
    private Integer id_soldProducts;
    /**
     * Camp id_order
     */
    private Integer id_order;
    /**
     * Camp cantitate produs vandut
     */
    private Integer quantity;
    /**
     * Camp pretul produsului
     */
    private Float price;
    /**
     * Constructor fara parametri
     */
    public SoldProducts(){

    }

    /**
     * Constructorul pentru produsele vandute
     * @param id_soldProducts setare camp
     * @param id_order setare camp
     * @param quantity setare camp
     * @param price setare camp
     */
    public SoldProducts(Integer id_soldProducts, Integer id_order, Integer quantity, Float price) {
        this.id_soldProducts = id_soldProducts;
        this.id_order = id_order;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @return camp
     */
    public Integer getId_soldProducts() {
        return id_soldProducts;
    }
    /**
     *@return camp
     */
    public Integer getId_order() {
        return id_order;
    }
    /**
     *@return camp
     */

    public Integer getQuantity() {
        return quantity;
    }
    /**
     * @return pret
     */

    public Float getPrice() {
        return price;
    }

    /**
     *
     * @param id_soldProducts s
     */
    public void setId_soldProducts(Integer id_soldProducts) {
        this.id_soldProducts = id_soldProducts;
    }

    /**
     *
     * @param id_order id
     */
    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }

    /**
     *
     * @param quantity cantitate
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @param price pret
     */
    public void setPrice(Float price) {
        this.price = price;
    }
    /**
     *
     * @return va returna stringul pentru afisarea produsului vandut
     */
    @Override
    public String toString() {
        return "SoldProducts{" +
                "id_soldProducts=" + id_soldProducts +
                ", id_order=" + id_order +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
