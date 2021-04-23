package start;

import com.itextpdf.text.DocumentException;
import presentation.ControllerView;
import presentation.View;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * Aceasa clasa reprezinta clasa ce contine metoda main de start a aplicatiei warehouse management
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class Start {
	/**
	 * Metoda main care lanseaza aplicatia prin instantierea ferestrei si a controllerului acesteia
	 * @param args argumente
	 */
	public static void main(String[] args){
		View view=new View();
		ControllerView controllerView=new ControllerView(view);
		view.setVisible(true);
	}


}