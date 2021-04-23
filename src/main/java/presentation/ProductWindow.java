package presentation;

import bll.Productbll;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Aceasa clasa reprezinta clasa pentru fereastra dedicata operatiilor pentru produse.
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class ProductWindow extends JFrame {
    /**
     * Tabel produse ce va fi afisat
     */
    private JTable productsTable;
    /**
     * Tabel produse ce pot fi actualizate
     */
    private JTable updateTable=new JTable();
    /**
     * Text field pentru id
     */
    private JTextField idProductText = new JTextField(15);
    /**
     * Label pentru id
     */
    private JLabel idProductLabel = new JLabel("Id_product");
    /**
     * Text field pentru nume
     */
    private JTextField nameProductText = new JTextField(20);
    /**
     * Label pentru nume
     */
    private JLabel nameProductLabel = new JLabel("Name");
    /**
     * Label pentru cantitate
     */
    private JLabel quantityLabel = new JLabel("Quantity");
    /**
     * Text field pentru cantitate
     */
    private JTextField quantityText = new JTextField(20);
    /**
     * Label pentru pret
     */
    private JLabel priceLabel = new JLabel("Price");
    /**
     * Text field pentru pret
     */
    private JTextField priceText = new JTextField(20);

    /**
     * Buton de findall products
     */
    private JButton findAllButton = new JButton("Find all");
    /**
     * Buton de edit product
     */
    private JButton editButton = new JButton("Edit product");
    /**
     * Buton de delete product
     */
    private JButton deleteButton = new JButton("Delete product");
    /**
     * Buton de add product
     */
    private JButton insertButton =new JButton("Add product");
    /**
     * Panel pentru inserare product
     */
    private JPanel insertPanel=new JPanel();
    /**
     * Panel pentru cautare product
     */
    private JPanel findPanel=new JPanel();
    /**
     * Panel pentru actualizare product
     */
    private JPanel updatePanel=new JPanel();
    /**
     * Panel pentru rezultate
     */
    private JPanel resultsPanel;
    /**
     * Culoare background
     */
    private Color blackColor=new Color(29, 29, 29);

    /**
     * Constructor ce aranjeaa panel-urile in frame
     */
    public ProductWindow() {
        this.setPreferredSize(new Dimension(1000, 600));
        this.setMinimumSize(new Dimension(1000, 600));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JTabbedPane selectionPanel=new JTabbedPane();
        findPanel.add(findAllButton);
        findPanel.setBackground(blackColor);
        selectionPanel.add("Search ", findPanel);
        createInsertPanel();
        selectionPanel.add("Insert product", insertPanel);
        setUpdatePanel();
        selectionPanel.add("Update product",updatePanel);

        resultsPanel = new JPanel();
        resultsPanel.setPreferredSize(new Dimension(1000, 400));
        resultsPanel.setBackground(blackColor);
        findPanel.add(resultsPanel);
        mainPanel.add(selectionPanel);
        this.setContentPane(mainPanel);
        this.pack();
        this.setTitle("Product Window");

    }

    /**
     * Actualizeaza paenelul cu toate produsele din baza de date
     */
    public void setResultsPanelFindAll() {
        resultsPanel.removeAll();
        Productbll productbll = new Productbll();
        List<Product> products = productbll.findAll();
        productsTable = View.createClientTable(new ArrayList<Object>(products));
        resultsPanel.add(new JScrollPane(productsTable));
        productsTable.setBounds(700, 200, 1000, 600);
        productsTable.setPreferredSize(new Dimension(1000, 400));
        productsTable.setBackground(new Color(139, 196, 76));
        productsTable.setEnabled(false);
        resultsPanel.revalidate();
    }

    /**
     * @return string
     */
    public String getIdClientText(){
        return this.idProductText.getText();
    }
    /**
     * @return nume
     */
    public String getNameProductText(){
        return this.nameProductText.getText();
    }
    /**
     * @return string
     */
    public String getQuantityText(){
        return this.quantityText.getText();
    }
    /**
     * @return string
     */
    public String getPriceText(){
        return this.priceText.getText();
    }

    /**
     * Creeaza panelul pentru logica de insert produse
     */
    private void createInsertPanel(){
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setSize(new Dimension(400,500));
        this.setLocation(new Point(500,100));

        panel.add(idProductLabel);
        panel.add(idProductText);
        panel.add(nameProductLabel);
        panel.add(nameProductText);
        panel.add(quantityLabel);
        panel.add(quantityText);
        panel.add(priceLabel);
        panel.add(priceText);
        panel.add(insertButton);
        insertPanel.add(new JLabel("INSERT NEW CLIENT"));
        insertPanel.add(panel);
    }

    /**
     * Actualizeaza tabelul cu produsele de actualizat
     */
    public void setUpdatePanel(){
        updatePanel.removeAll();
        Productbll productbll = new Productbll();
        List<Product> products = productbll.findAll();
        updateTable = View.createClientTable(new ArrayList<Object>(products));
        updateTable.setAutoResizeMode(1);
        updatePanel.setBounds(400, 200, 800, 600);
        updatePanel.setPreferredSize(new Dimension(1000, 400));
        updateTable.setBackground(new Color(220, 198, 198));
        updatePanel.add(new JScrollPane(updateTable));
        updateTable.setEnabled(true);
        updateTable.setRowSelectionAllowed(true);
        updatePanel.add(editButton);
        updatePanel.add(deleteButton);
        updatePanel.revalidate();
    }

    /**
     * Adauga listener pentru buton find all
     * @param a listener
     */
    public void addActionFindAllButton(ActionListener a) {
        this.findAllButton.addActionListener(a);
    }
    /**
     * Adauga listener pentru buton insert
     * @param a listener
     */
    public void addInsertButtonListener(ActionListener a){
        this.insertButton.addActionListener(a);
    }
    /**
     * Adauga listener pentru buton update
     *  @param a listener
     */
    public void addUpdateButtonListener(ActionListener a){
        this.editButton.addActionListener(a);
    }
    /**
     * Adauga listener pentru buton delete
     *  @param a listener
     */
    public void addDeleteButtonListener(ActionListener a){
        this.deleteButton.addActionListener(a);
    }
    /**
     *Window cu mesaj de informare
     *  @param msg mseaj de informare
     */
    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    /**
     * Getter pentru tabelul de update
     * @return table
     */
    public JTable getUpdateTable(){
        return updateTable;
    }
}
