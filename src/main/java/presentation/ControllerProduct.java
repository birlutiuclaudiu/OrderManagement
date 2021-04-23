package presentation;

import bll.Productbll;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

/**
 * Aceasa clasa reprezinta clasa pentru controllerul ferestrei dedicate operatiilor pe tabelul order.
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class ControllerProduct {
    /**
     * Camp pentru fereastra de controlat
     */
    private ProductWindow productWindow;

    /**
     * Primeste ca parametru fereastra de controlat
     * Adauga listenerele pentru butoanele din interfata
     * @param productWindow fereastra pentru produse
     */
    public ControllerProduct( ProductWindow productWindow){
        this.productWindow=productWindow;
        this.productWindow.addActionFindAllButton(new FindAllButtonListener());
        this.productWindow.addInsertButtonListener(new InsertButtonListener());
        this.productWindow.addUpdateButtonListener(new UpdateButtonListener());
        this.productWindow.addDeleteButtonListener(new DeleteButtonListener());
    }

    /**
     * Aceasa clasa reprezinta clasa interna pentru listenerul butonului de find all din fereastra ProductWindow.
     * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
     * @since 22 April 2021
     */
    public class FindAllButtonListener implements ActionListener{
        /**
         * Va actualiza panelul cu tabelul de produse
         * @param e s
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            productWindow.setResultsPanelFindAll();
        }
    }

    /**
     * Aceasa clasa reprezinta clasa interna pentru listenerul butonului de insert din fereastra ProductWindow.
     * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
     * @since 22 April 2021
     */
    public class InsertButtonListener implements ActionListener{
        /**
         *Metoda va extrage valorile din interfata grafica si prin intermediul obiectului ce se ocupa cu manipularea
         * tabelei din baza de date va adauga noul produs
         * @param e s
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer id_product =null;
            Integer quantity=null;
            Float price=null;
            try{
                id_product =Integer.parseInt(productWindow.getIdClientText());
            }catch (NumberFormatException | InputMismatchException a){
                productWindow.showError("Invalid id");
                return;
            }
            try{
                quantity =Integer.parseInt(productWindow.getQuantityText());
            }catch (NumberFormatException | InputMismatchException a){
                productWindow.showError("Invalid quantity");
                return;
            }
            try{
                price =Float.parseFloat(productWindow.getPriceText());
            }catch (NumberFormatException | InputMismatchException a){
                productWindow.showError("Invalid id");
                return;
            }
            Product product =new Product(id_product, productWindow.getNameProductText(), quantity, price);
            Productbll productbll=new Productbll();
            try{
                productbll.insert(product);
                productWindow.setUpdatePanel();
            }catch (Exception exception){
                productWindow.showError(exception.getMessage());
            }
        }
    }
    /**
     * Aceasa clasa reprezinta clasa interna pentru listenerul butonului de update din fereastra ProductWindow.
     * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
     * @since 22 April 2021
     */
    public class UpdateButtonListener implements ActionListener{
        /**
         * Metoda va prelua datele din tabelul de update din interfata grafica si va actualiza tabelul din baza de date
         * @param e s
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable updateT= productWindow.getUpdateTable();
            Integer id_product =null;
            Integer quantity=null;
            Float price=null;
            updateT.setRowSelectionAllowed(true);
            try{
                id_product=Integer.parseInt((String) updateT.getValueAt(updateT.getSelectedRow(),0));
            }catch (Exception exc){
                productWindow.showError("Invalid id_client");
                return;
            }
            try{
                quantity=Integer.parseInt((String) updateT.getValueAt(updateT.getSelectedRow(),2));
            }catch (Exception exc) {
                productWindow.showError("Invalid id_client");
                return;
            }
            try{
                price=Float.parseFloat((String) updateT.getValueAt(updateT.getSelectedRow(),3));
            }catch (Exception exc) {
                productWindow.showError("Invalid id_client");
                return;
            }
            int row=updateT.getSelectedRow();
            Product product=new Product(id_product, updateT.getValueAt(row,1).toString(),
                   quantity,price);

            Productbll productbll=new Productbll();
            try{
                productbll.update(product);
                productWindow.setUpdatePanel();
            }catch (Exception exception){
                productWindow.showError(exception.getMessage());
            }
        }
    }
    /**
     * Aceasa clasa reprezinta clasa interna pentru listenerul butonului de delete din fereastra ProductWindow.
     * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
     * @since 22 April 2021
     */
    public class DeleteButtonListener implements ActionListener{
        /**
         * Va sterge produsul din tabelul din baza de date
         * @param e s
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable updateT= productWindow.getUpdateTable();
            Integer id_product =null;
            updateT.setRowSelectionAllowed(true);
            try{
                id_product =Integer.parseInt((String) updateT.getValueAt(updateT.getSelectedRow(),0));
            }catch (Exception exc){
                productWindow.showError("Invalid id_client");
                return;
            }
            Productbll productbll=new Productbll();
            try{
                productbll.delete(id_product);
                productWindow.setUpdatePanel();
            }catch (Exception exception){
                productWindow.showError(exception.getMessage());
            }
        }
    }
}
