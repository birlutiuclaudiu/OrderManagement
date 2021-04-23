package bll.validators;
import model.Client;

/**
 * Aceasa interfata descrie tipul de validator si metoda pe care trebuie sa o implementeze
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class ClientGenderValidator implements Validator<Client> {

	/**
	 * Metoda va arunca o exceptie in cazul in care campul pentru gen al clientului nu corespunde
	 * @param t obiect de tipul client pe care se vor apela metatodele accesoare
	 */
	public void validate(Client t) {
		if (!(t.getGender().equals("M") || t.getGender().equals("F") || t.getGender().equals("m") || t.getGender().equals("f") ) ) {
			throw new IllegalArgumentException("The Client gender is incorrect M:F or m:f!");
		}
	}

}
