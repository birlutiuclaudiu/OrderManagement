package model;

/**
 * Aceasa clasa reprezinta clasa modelul Client. Aceasta va contine campurile corespunzatoare tabelului din baza de date
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class Client {
    /**
     * Id-ul clientului
     */
    private Integer id_client;
    /**
     *Numele clientului
     */
    private String name;
    /**
     *Gender
     */
    private String gender;
    /**
     *Email (contine validator)
     */
    private String email;
    /**
     *Numarul de telefon al clientului
     */
    private String telephone;
    /**
     *Adresa clientului
     */
    private String address;

    /**
     * Constructor fara parametri
     */
    public Client(){
        super();
    }

    /**
     * Se vor seta campurile cu valorile transimise ca parametru
     * @param id_client a
     * @param name a
     * @param gender a
     * @param email a
     * @param telephone a
     * @param address a
     */
    public Client(int id_client,String name,String gender,String email, String telephone,String address){
        this.id_client=id_client;
        this.name=name;
        this.gender=gender;
        this.email=email;
        this.telephone=telephone;
        this.address=address;
    }

    /**
     * Getter id_client
     * @return camp
     */
    public int getId_client() {
        return id_client;
    }
    /**
     * Getter getName
     * @return camp
     */
   public String getName(){
        return this.name;
   }
    /**
     * Getter getGender
     * @return camp
     */
    public String getGender() {
        return gender;
    }
    /**
     * Getter getEmail
     * @return camp
     */
    public String getEmail() {
        return email;
    }
    /**
     * Getter getTelephone
     * @return camp
     */
    public String getTelephone(){
        return this.telephone;
    }
    /**
     * Getter getAddress
     * @return camp
     */
    public String getAddress(){
        return this.address;
    }

    /**
     * Setter setId_client
     * @param id_client  a
     */
    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
    /**
     * Setter setName
     * @param name nume
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Setter setGender
     * @param gender g
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    /**
     * Setter setEmail
     * @param email email
     */

    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Setter setTelephone
     * @param telephone tel
     */

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    /**
     * Setter Address
     * @param address addr
     */

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return Suprascrierea metodei toString pentr afisarea campurilor clientului
     */
    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
