package bll.validators;


/**
 * Aceasa interfata descrie tipul de validator si metoda pe care trebuie sa o implementeze
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public interface Validator<T> {

	/**
	 * Metoda ce va trebui implemetnata de clasele validare a anumitor campuri
	 * In cazul in care campurile nu vor corsepunde sa va arunca o exceptie
	 * @param t paramtru generic
	 */
	public void validate(T t);
}
