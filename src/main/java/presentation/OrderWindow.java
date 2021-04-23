package presentation;

import bll.Clientbll;
import bll.Productbll;
import model.Client;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Aceasa clasa reprezinta clasa pentru fereastra dedicata operatiilor pentru comenzi.
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class OrderWindow extends JFrame {
    /**
     * Tabelul clinetilor
     */
    private JTable clientsTable;
    /**
     * Tabelul produselor
     */
    private JTable productsTable;
    /**
     * Panelul clinetilor
     */
    private JPanel clientsPanel=new JPanel();
    /**
     * Panelul produselor
     */
    private JPanel productsPanel=new JPanel();
    /**
     * Label cantitate
     */
    private JLabel quantityLabel=new JLabel("Quantity:");
    /**
     * Textfield cantitate
     */
    private JTextField quantityText=new JTextField(20);
    /**
     * Buton pentru o noua comanda
     */
    private JButton newOrderButton=new JButton("New Order");
    /**
     * Buton pentru genereare noua comanda si pdf-ul
     */
    private JButton generateOrder=new JButton("Generate order");
    /**
     * Buton adaugare produs cos
     */
    private JButton addProductButton=new JButton("Add product");
    /**
     * Label pentru id comanda
     */
    private JLabel  nextOrder=new JLabel("Id order: ");

    /**
     * Constructor ce defineste aranjamentul panel-urilor
     */
    public OrderWindow(){
        this.setPreferredSize(new Dimension(1000, 600));
        this.setMinimumSize(new Dimension(1000, 600));
        this.setLocation(new Point(500,100));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel newOrderPanel=new JPanel();
        newOrderPanel.add(newOrderButton);

        JPanel selectionPanel=new JPanel();
        selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.X_AXIS));
        setClientsPanel();
        selectionPanel.add(clientsPanel);
        setProductsPanel();
        selectionPanel.add(productsPanel);

        JPanel quantityPanel=new JPanel();
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantityText);

        JPanel addProductPanel=new JPanel();
        addProductPanel.add(addProductButton);

        JPanel generatePanel=new JPanel();
        generatePanel.add(generateOrder);


        mainPanel.add(newOrderPanel);
        mainPanel.add(nextOrder);
        mainPanel.add(selectionPanel);
        mainPanel.add(quantityPanel);
        mainPanel.add(addProductPanel);
        mainPanel.add(generatePanel);
        quantityText.setSize(new Dimension(60,10));
        this.setContentPane(mainPanel);
        this.pack();
        this.setTitle("Order Window");

    }

    /**
     * Actualizare tabel clienti
     */
    public void setClientsPanel(){
        clientsPanel.removeAll();
        Clientbll clientbll = new Clientbll();
        List<Client> clients = clientbll.findAll();
        clientsTable = View.createClientTable(new ArrayList<Object>(clients));

        clientsTable.setBounds(700, 200, 1000, 600);
        clientsTable.setPreferredSize(new Dimension(1000, 400));
        clientsPanel.add(new JScrollPane(clientsTable));
        clientsTable.setRowSelectionAllowed(true);
       // clientsTable.setEnabled(true);
        clientsPanel.revalidate();
    }

    /**
     * Actualizeza tabel produse
     */
    public void setProductsPanel(){
        productsPanel.removeAll();
        Productbll productbll = new Productbll();
        List<Product> products = productbll.findAll();
        productsTable = View.createClientTable(new ArrayList<Object>(products));

        productsTable.setBounds(700, 200, 1000, 600);
        productsTable.setPreferredSize(new Dimension(1000, 400));
        productsPanel.add(new JScrollPane(productsTable));
        productsTable.setRowSelectionAllowed(true);
        productsTable.setEnabled(true);
        productsTable.setRowSelectionAllowed(true);
        productsPanel.revalidate();
    }

    /**
     * Adauga listener pentru butonul de generate
     * @param a listener
     */
    public void addGenerateButtonListener(ActionListener a){
        this.generateOrder.addActionListener(a);
    }
    /**
     * Adauga listener pentru butonul de add new product
     *  @param a listener
     */
    public void addNewOrderButtonListener(ActionListener a){
        this.newOrderButton.addActionListener(a);
    }
    /**
     * Adauga listener pentru butonul de generate
     *  @param a listener
     */
    public void addProductButton(ActionListener a){
        this.addProductButton.addActionListener(a);
    }
    /**
     * @return  table
     */
    public JTable getClientsTable(){
        return clientsTable;
    }
    /**
     *@return  table
     */
    public JTable getProductsTable(){
        return productsTable;
    }
    /**
     *@param msg mesaj
     */
    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
    /**
     *@return  cantitate
     */
    public String getQuantityText(){
        return this.quantityText.getText();
    }
    /**
     * Setare label cu id-ul comenzii care se construieste
     * @param s setare new order
     */
    public void setLabelOrder(String s){
        nextOrder.setText("Id_order: "+s);
    }
}
