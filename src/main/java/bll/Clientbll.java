package bll;

import bll.validators.ClientGenderValidator;
import bll.validators.ClientTelValidator;
import bll.validators.EmailValidator;
import bll.validators.Validator;
import dao.ClientDao;
import model.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Aceasa clasa reprezinta clasa de businesslogic pentru client. Prin intermediul ei se va cere actualizarea tabelului
 * pentru clienti
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class Clientbll {
    /**
     * Camp ce va contine instanta clasei ce implementeaza metodele de manipulare a bazei de date pentru tabele client
     */
    private ClientDao clientDAO;
    /**
     * Camp ce va contine o lista de validatoare pentru campurile clientului
     */
    private List<Validator<Client>> validators;

    /**
     * Constructor fara parametru
     * Se vor instantia validatoarele si obiectul ce va apela metodele de modificare a tabelei clienti
     */
    public Clientbll(){
        validators = new ArrayList<Validator<Client>>();
        validators.add(new ClientGenderValidator());
        validators.add(new EmailValidator());
        validators.add(new ClientTelValidator());
        clientDAO=new ClientDao();
    }

    /**
     * Metoda ce va cere cautarea unui client in baza de date dupa id
     * @param id reprezinta id_ul clientului de cautat
     * @return returneaza un obiect de tip Client extras din tabela Client sau null in cazul in care nu e niciun client cu
     * id-ul dat
     */
    public Client findClientById(int id){
        Client client=clientDAO.findById(id);
        if(client==null)
            throw new NoSuchElementException("The client with id " +id+" was not found!");
        return client;
    }

    /**
     * Metoda ce va extrage toti clientii din baza de date
     * @return va returna o lista de Clienti
     */
    public List<Client> findAll(){
        List<Client> clients=clientDAO.findAll();
        if(clients==null)
            throw new NoSuchElementException("Empty table");
        return clients;
    }

    /**
     * Metoda ce va insera un client in baza de date, iar daca nu se va reusi va arunca exceptia corespunzatoare
     * @param client obiect de tipul client; valorile campurilor lui vor fi inserate in tabelul Client
     * @return returneaza clientul in caz de succes
     * @throws SQLException se va arunca in cazul unei erori/exceptii la stergerea din baza de date
     * @throws IllegalAccessException s
     * @throws IllegalArgumentException s
     */
    public Client insert(Client client) throws SQLException, IllegalAccessException, IllegalArgumentException{
        for(Validator<Client> validator:validators)
            validator.validate(client);
       clientDAO.insert(client);
       return client;
    }

    /**
     * Metoda ca va cere actualizarea unui client cu noile valori ale campului
     * @param client obiectul de tip Client
     * @return in caz de succes va returna inapoi Clientul, daca nu va arunca o exceptie SQL
     * @throws SQLException se va arunca in cazul unei erori/exceptii la stergerea din baza de date
     * @throws IllegalAccessException s
     * @throws IllegalArgumentException s
     */
    public Client update(Client client) throws SQLException, IllegalAccessException,IllegalArgumentException{
        for(Validator<Client> validator:validators)
            validator.validate(client);
        clientDAO.update(client);
        return client;
    }

    /**
     * Metoda ca va cere stergerea unui cleintului cu id-ul transmis ca parametru
     * @param id id-ul clientului de sters din baza de date ;
     * @throws SQLException se va arunca in cazul unei erori/exceptii la stergerea din baza de date
     * @throws IllegalAccessException s
     */
    public void delete(int id) throws SQLException, IllegalAccessException{
        clientDAO.delete(id);
    }
}
