package model;

/**
 * Aceasa clasa reprezinta clasa modelului Product. Aceasta va contine campurile corespunzatoare tabelului din baza de date
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class Product {
    /**
     * Camp id-ul produsului
     */
    private Integer id_product;
    /**
     * Camp nume
     */
    private String name;
    /**
     * Camp  cantitate produs
     */
    private Integer quantity;
    /**
     * Camp pret
     */
    private Float price;

    /**
     * Constructor fara parametri
     */
    public Product(){
    }

    /**
     * Constructor cu paraamtri
     * Se vor seta campurile
     * @param id_prod f
     * @param name f
     * @param quantity f
     * @param price f
     */
    public Product(int id_prod, String name, int quantity,float price) {
        this.id_product = id_prod;
        this.name = name;
        this.quantity = quantity;
        this.price=price;
    }

    /**
     * @return camp
     */
    public Integer getId_product() {
        return id_product;
    }
    /**
     *
     * @return camp
     */

    public String getName() {
        return name;
    }
    /**
     *
     * @return camp
     */
    public Integer getQuantity() {
        return quantity;
    }
    /**
     *
     * @return camp
     */
    public Float getPrice() {
        return price;
    }

    /**
     *
     * @param id_product id
     */
    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    /**
     *
     * @param name nume
     */
    public void setName(String name) {
        this.name = name;
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
     * @return va retura stringul pentru afisarea unui produs
     */
    @Override
    public String toString() {
        return "Product{" +
                "id_prod=" + id_product +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
