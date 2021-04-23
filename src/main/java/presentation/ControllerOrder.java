package presentation;

import bll.Clientbll;
import bll.Orderbll;
import bll.Productbll;
import bll.SoldProductsbll;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import model.Client;
import model.Order;
import model.Product;
import model.SoldProducts;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * Aceasa clasa reprezinta clasa pentru controllerul ferestrei dedicate operatiilor pe tabelul order.
 * @author Claudiu-Andrei Birlutiu, 30226
 * @since  22 April 2021
 */
public class ControllerOrder {
    /**
     * Reprezinta campul pentru fereastra dedicata tabelului de comenzi care va fi controlata
     */
    private OrderWindow orderWindow;
    /**
     * Campul pentru id_ul noi comenzi
     */
    private Integer id_order;
    /**
     * Camp ce indica starea cosului de cumparaturi
     */
    private boolean emptyCart=true;

    /**
     * Constructor fara paramteri ce seteaza campul dedicat ferestrei si adauga listeneri pentru butonele din cadrul ferestrei
     * @param orderWindow f
     */
    public ControllerOrder(OrderWindow orderWindow) {
        this.orderWindow = orderWindow;
        this.orderWindow.addNewOrderButtonListener(new NewOrderListener());
        this.orderWindow.addProductButton(new AddProductListener());
        this.orderWindow.addGenerateButtonListener(new GenerateListener());
    }

    /**
     * Aceasa clasa reprezinta clasa interna pentru listenerul butonului de newOrder din fereastra OrderWindow.
     * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
     * @since  22 April 2021
     */
    public class NewOrderListener implements ActionListener {
        /**
         * Va seta campul din controller cu id-ul noi comenzi; si va permite adaugarea de produse in cos
         * @param e listener
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(id_order!=null) {
                orderWindow.showError("Unfinished old-order");
                return;
            }
            Orderbll orderbll=new Orderbll();
            id_order=orderbll.findNextIdOrder();
            orderWindow.setLabelOrder(id_order.toString());
        }
    }
    /**
     * Aceasa clasa reprezinta clasa interna pentru listenerul butonului de addProduct din fereastra ClientWindow.
     * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
     * @since  22 April 2021
     */
    public class AddProductListener implements ActionListener {
        /**
         * Camp pentru obiettul ce gestioneaza operatiile pe tabela Product din baza de date
         */
        private Productbll productbll;
        /**
         * Camp pentru cantitatea de produse vandute
         */
        private Integer quantity;
        /**
         * Camp pentru obiettul ce gestioneaza operatiile pe tabela SoldProducts din baza de date
         */
        private SoldProductsbll soldProductsbll =new SoldProductsbll();

        /**
         * Metoda ce extrage datele din interfata grafica si face operatiile corespunzatoare da adaugare a produselor in cos
         * si actulizarea lor in tabelul Product (cantitate mai putina)
         * @param e listener
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(id_order==null){
                orderWindow.showError("New order button not pressed"); return;
            }
            JTable productsTable= orderWindow.getProductsTable();
            Integer id_product=null;
            productsTable.setRowSelectionAllowed(true);
            try{
                id_product=Integer.parseInt((String) productsTable.getValueAt(productsTable.getSelectedRow(),0));
            }catch (Exception exc){
                orderWindow.showError("Invalid id_product");
                return;
            }
            productbll=new Productbll();
            Product product=productbll.findProductById(id_product);
            if(!canBuyProduct(product))
                return;
            product.setQuantity(product.getQuantity()-quantity);
            try {
                productbll.update(product);
                orderWindow.setProductsPanel();
            }catch (Exception exc){
                orderWindow.showError(exc.getMessage());
            }
            try {
                soldProductsbll.insert(new SoldProducts(product.getId_product(), id_order,quantity,quantity*product.getPrice()));
            }catch (Exception exc){
                orderWindow.showError(exc.getMessage());
                return;
            }
            emptyCart=false;
        }

        /**
         * Metoda ce returneaza true sau false daca mai sunt suficiente produse
         * @param product produs
         */
        private boolean canBuyProduct(Product product){
            quantity=null;
            try{
                quantity=Integer.parseInt(orderWindow.getQuantityText());
            }catch(NumberFormatException exc){
                orderWindow.showError("Invalid quantity");
                return false;
            }
            if(quantity>product.getQuantity()){
                orderWindow.showError("Out of stock");
                return false;
            }else {
                return true;
            }
        }
    }
    /**
     * Aceasa clasa reprezinta clasa interna pentru listenerul butonului de generateOrder din fereastra ClientWindow.
     * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
     * @since 22 April 2021
     */
    public class GenerateListener implements ActionListener{
        /**
         * Camp pentru obiettul ce gestioneaza operatiile pe tabela Client din baza de date
         */
        private Clientbll clientbll=new Clientbll();
        /**
         * Camp pentru obiettul ce gestioneaza operatiile pe tabela Order din baza de date
         */
        private Orderbll orderbll=new Orderbll();
        /**
         * Camp ce reprezinta clientul comenzii
         */
        private Client client;
        /**
         * Camp pentru obiettul ce gestioneaza operatiile pe tabela SoldProducts din baza de date
         */
        private SoldProductsbll soldProductsbll=new SoldProductsbll();
        /**
         * Camp pentru pretul total per comanda
         */
        private float totalPrice;

        /**
         * Metoda ce va adauga comanda noua in baza de date si va genera factura
         * @param e listener
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(emptyCart){
                orderWindow.showError("Empty cart!");
                return;
            }
            JTable clientsTable =orderWindow.getClientsTable();
            Integer id_client=null;
            clientsTable.setRowSelectionAllowed(true);
            try{
                id_client=Integer.parseInt((String) clientsTable.getValueAt(clientsTable.getSelectedRow(),0));
            }catch (Exception exc){
                orderWindow.showError("Invalid id_client");
                return;
            }
            client=clientbll.findClientById(id_client);
            try {
                totalPrice=soldProductsbll.findTotalPrice(id_order);
            } catch (SQLException | IllegalAccessException throwables) {
                    orderWindow.showError(throwables.getMessage());
            }
            try {
                orderbll.insert(new Order(id_order,client.getId_client(),totalPrice));
            } catch (SQLException | IllegalAccessException throwables) {
                orderWindow.showError(throwables.getMessage());
            }
            generatePdfOrder();
            orderWindow.showError("Order "+id_order+" generated!\nYou can place a new order");
            id_order=null;
            emptyCart=true;
        }

        /**
         * Metoda ce va crea un pdf cu datele corespunzatoare comenzii
         */
        private void generatePdfOrder(){
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream("Order_"+id_order+".pdf"));
            } catch (FileNotFoundException | DocumentException e) {
               orderWindow.showError(e.getMessage());
            }
            document.open();
            Order order=orderbll.findOrderById(id_order);
            List<Product> products=soldProductsbll.findProductsOfOrder(id_order);
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Paragraph paragraph=new Paragraph();
            try {
                paragraph.add(new Paragraph("\tORDER nb: "+id_order.toString()+"\n", font));
                paragraph.add(new Paragraph("Client: "+client.getName()+
                        "\n   Email: "+client.getEmail() +
                        "\n   Telephone: "+client.getTelephone()+
                        "\n   Address: "+ client.getAddress() + " \n PRODUCTS\n",font));
                for(Product product:products){
                    paragraph.add(new Paragraph(product.getName()+ "   "+product.getQuantity() +" buc.  " +
                                                   product.getPrice()+" lei\n",font));
                }
                paragraph.add(new Paragraph("\n\nTOTAL PRICE: "+order.getTotal_price() + "    Date:"+order.getDate(),font));
                document.add(paragraph);
            }catch (DocumentException exc){
                orderWindow.showError(exc.getMessage());
            }
            document.close();
        }
    }
}
