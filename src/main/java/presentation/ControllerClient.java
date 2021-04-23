package presentation;

import bll.Clientbll;
import model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

/**
 * Aceasa clasa reprezinta clasa pentru controllerul ferestrei dedicate operatiilor pe tabelul clienti.
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class ControllerClient {
    /**
     * Camoul ce reprezinta fereastre de controlat
     */
    private ClientWindow clientWindow;

    /**
     * Constructor ce primeste ca parametru fereastra de controlat
     * Adauga listeneri pentru butoane definiti printr-o clasa interna ce implementeaza ActionListener
     * @param clientWindow d
     */
    public ControllerClient(ClientWindow clientWindow){
        this.clientWindow=clientWindow;
        this.clientWindow.addActionFindAllButton(new FindAllButtonListener());
        this.clientWindow.addInsertButtonListener(new InsertButtonListener());
        this.clientWindow.addUpdateButtonListener(new UpdateButtonListener());
        this.clientWindow.addDeleteButtonListener(new DeleteButtonListener());
    }

    /**
     * Aceasa clasa reprezinta clasa interna pentru listenerul butonului de find all din fereastra ClientWindow.
     * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
     * @since 22 April 2021
     */
    public class FindAllButtonListener implements ActionListener{
        /**
         * Va actualiza panelul cu tabelul de clienti
         * @param e listener
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            clientWindow.setResultsPanelFindAll();
        }
    }
    /**
     * Aceasa clasa reprezinta clasa interna pentru listenerul butonului de insert din fereastra ClientWindow.
     * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
     * @since 22 April 2021
     */
    public class InsertButtonListener implements ActionListener{
        /**
         * Va extrage cu ajutrol metodelor de get cvalorile de inserat in tabelul din baza de date
         * @param e listener
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer id_client=null;
            try{
                id_client=Integer.parseInt(clientWindow.getIdClientText());
            }catch (NumberFormatException | InputMismatchException a){
                clientWindow.showError("Invalid id");
                return;
            }
            Client client=new Client(id_client, clientWindow.getNameClientText(), clientWindow.getGenderText(),
                    clientWindow.getEmailText(),clientWindow.getTelText(),clientWindow.getAddressText());
            Clientbll clientbll=new Clientbll();
            try{
                clientbll.insert(client);
                clientWindow.setUpdatePanel();
                clientWindow.showError("SUCCESS!!");
            }catch (Exception exception){
                clientWindow.showError(exception.getMessage());
            }
        }
    }
    /**
     * Aceasa clasa reprezinta clasa interna pentru listenerul butonului deupdate din fereastra ClientWindow.
     * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
     * @since 22 April 2021
     */
    public class UpdateButtonListener implements ActionListener{
        /**
         * Va extrage valorile din tabelul de update pe care utilizatorul le modifica si va apela metodele obietelor
         * de tipul Clientbll actulizarea tabelului din baza de date
         * @param e listener
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable updateT=clientWindow.getUpdateTable();
            Integer id_client=null;
            updateT.setRowSelectionAllowed(true);
            try{
                id_client=Integer.parseInt((String) updateT.getValueAt(updateT.getSelectedRow(),0));
            }catch (Exception exc){
                clientWindow.showError("Invalid id_client");
                return;
            }
            int row=updateT.getSelectedRow();
            Client client=new Client(id_client, updateT.getValueAt(row,1).toString(),
                    updateT.getValueAt(row,2).toString(),
                    updateT.getValueAt(row,3).toString(),
                    updateT.getValueAt(row,4).toString(),
                    updateT.getValueAt(row,5).toString());

            Clientbll clientbll=new Clientbll();
            try{
                clientbll.update(client);
                clientWindow.setUpdatePanel();
            }catch (Exception exception){
                clientWindow.showError(exception.getMessage());
            }
        }
    }
    /**
     * Aceasa clasa reprezinta clasa interna pentru listenerul butonului de delete din fereastra ClientWindow.
     * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
     * @since 22 April 2021
     */
    public class DeleteButtonListener implements ActionListener{
        /**
         * Va extrage dim tabelul din fereastra clientului linia selectata si va terge clientul cu id-ul corespunzator din
         * baza de date
         * @param e listener
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable updateT=clientWindow.getUpdateTable();
            Integer id_client=null;
            updateT.setRowSelectionAllowed(true);
            try{
                id_client=Integer.parseInt((String) updateT.getValueAt(updateT.getSelectedRow(),0));
            }catch (Exception exc){
                clientWindow.showError("Invalid id_client");
                return;
            }
            Clientbll clientbll=new Clientbll();
            try{
                clientbll.delete(id_client);
                clientWindow.setUpdatePanel();
            }catch (Exception exception){
                clientWindow.showError(exception.getMessage());
            }
        }
    }

}
