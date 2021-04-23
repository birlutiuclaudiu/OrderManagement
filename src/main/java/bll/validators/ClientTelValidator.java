package bll.validators;

import model.Client;

import java.util.regex.Pattern;

/**
 * Aceasa clasa prezinta validatorul pentru numarul de telefon al unui client;
 * Numarul trebuie sa contina 10 cifre si sa inceapa cu 07
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class ClientTelValidator implements Validator<Client> {

    /**
     * Camp ce reprezinta patternul pe care se face match-ul pentru nr de telefon
     */
    private static final String PHONE_PATTERN ="[0][7][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]";

    /**
     * Metoda va arunca o exceptie in cazul in care campul pentru telefon al clientului nu corespunde
     * @param t obiect de tipul client pe care se vor apela metatodele accesoare
     */
    public void validate(Client t) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        if (!pattern.matcher(t.getTelephone()).matches()) {
            throw new IllegalArgumentException("Telephone is not a valid telephone!");
        }
    }
}

