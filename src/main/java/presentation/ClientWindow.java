package presentation;

import bll.Clientbll;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Aceasa clasa reprezinta clasa pentru fereastra dedicata operatiilor pe tabelul clienti.
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class ClientWindow extends JFrame {
    /**
     * Tabelul pentru clienti
     */
    private JTable clientTable;
    /**
     * Buton de find all
     */
    private JButton findAllButton = new JButton("Find all");
    /**
     * Buton de add
     */
    private JButton addNewClientButton = new JButton("Add New Client");
    /**
     * Buton de edit
     */
    private JButton editClientButton = new JButton("Edit client");
    /**
     * Buton de delete
     */
    private JButton deleteClientButton = new JButton("Delete Client");
    /**
     * Panel dedicat rezultatelor interogarii
     */
    private JPanel resultsPanel;
    /**
     * TextField  id-ului
     */
    private JTextField idClientText = new JTextField(15);
    /**
     * Panel dedicat rezultatelor interogarii
     */
    private JLabel idClientLabel = new JLabel("Id_client");
    /**
     * TextField  numelui
     */
    private JTextField nameClientText = new JTextField(20);
    /**
     * Label pentru nume
     */
    private JLabel nameClientLabel = new JLabel("Name");
    /**
     * Label pentru gender
     */
    private JLabel genderLabel = new JLabel("Gender");
    /**
     * TextField  gender
     */
    private JTextField genderText = new JTextField(20);
    /**
     * TextField dedicat emailului
     */
    private JLabel emailLabel = new JLabel("Email");
    /**
     * TextField dedicat emailului
     */
    private JTextField emailText = new JTextField(20);
    /**
     * Label dedicat telefonului
     */
    private JLabel telLabel = new JLabel("Telephone");
    /**
     * TextField dedicat telefonului
     */
    private JTextField telText = new JTextField(20);
    /**
     * Label dedicat adresei
     */
    private JLabel addressLabel = new JLabel("Address");
    /**
     * TextField dedicat adresei
     */
    private JTextField addressText = new JTextField(20);
    /**
     * Buton pentru inserare
     */
    private JButton insertButton = new JButton("Insert client");
    /**
     * Buton pentru editare
     */
    private JButton editButton = new JButton("Edit client");
    /**
     * Buton pentru stergere
     */
    private JButton deleteButton = new JButton("Delete client");
    /**
     * JPanel dedicat zonei de insert
     */
    private JPanel insertPanel=new JPanel();
    /**
     * JPanel dedicat zonei de cautare
     */
    private JPanel findPanel=new JPanel();
    /**
     * JPanel dedicat zonei de actualizare
     */
    private JPanel updatePanel=new JPanel();
    /**
     * Culoare background
     */
    private Color blackColor=new Color(29, 29, 29);
    /**
     * Tabel cu clientii de actualizat
     */
    private JTable updateTable=new JTable();

    /**
     * Constructor fara parametri
     * In ccadrul lui se adauga panel-urile pentru diferitele operatii
     */
    public ClientWindow() {
        this.setPreferredSize(new Dimension(1000, 600));
        this.setMinimumSize(new Dimension(1000, 600));
        this.setLocation(new Point(500,100));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JTabbedPane selectionPanel=new JTabbedPane();
        findPanel.setLayout(new BoxLayout(findPanel,BoxLayout.Y_AXIS));
        findPanel.setPreferredSize(new Dimension(1000,800));
        findPanel.add(findAllButton);
        findPanel.setBackground(blackColor);

        selectionPanel.add("Search ", findPanel);
        createInsertPanel();
        selectionPanel.add("Insert client", insertPanel);
        setUpdatePanel();
        selectionPanel.add("Update client",updatePanel);

        resultsPanel = new JPanel();
        resultsPanel.setPreferredSize(new Dimension(1000, 400));
        resultsPanel.setBackground(blackColor);
        findPanel.add(resultsPanel);
        mainPanel.add(selectionPanel);
        //mainPanel.add(resultsPanel);
        this.setContentPane(mainPanel);
        this.pack();
        this.setTitle("Client Window");
    }

    /**
     * Listener pentru butonul de selectie totala
     * @param a actionlistener
     */
    public void addActionFindAllButton(ActionListener a) {
        this.findAllButton.addActionListener(a);
    }
    /**
     * Actulalizarea panel-ului cu tabelul clientilor
     */
    public void setResultsPanelFindAll() {
        resultsPanel.removeAll();
        Clientbll clientbll = new Clientbll();
        List<Client> clients = clientbll.findAll();
        clientTable = View.createClientTable(new ArrayList<Object>(clients));
        clientTable.setBounds(1, 1, 1000, 600);
        clientTable.setPreferredSize(new Dimension(1000, 600));
        resultsPanel.add(new JScrollPane(clientTable));
        clientTable.setBackground(new Color(243, 148, 57));
        clientTable.setEnabled(false);
        resultsPanel.revalidate();
    }
    /**
     * Creeaza panel-ulul de insert
     */
    private void createInsertPanel(){
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setSize(new Dimension(400,500));
        panel.add(idClientLabel);
        panel.add(idClientText);
        panel.add(nameClientLabel);
        panel.add(nameClientText);
        panel.add(genderLabel);
        panel.add(genderText);
        panel.add(emailLabel);
        panel.add(emailText);
        panel.add(telLabel);
        panel.add(telText);
        panel.add(addressLabel);
        panel.add(addressText);
        panel.add(insertButton);
        insertPanel.add(new JLabel("INSERT NEW CLIENT"));
        insertPanel.add(panel);
    }
    /**
     * Actualizeaza panelul de update tabel clienti
     */
    public void setUpdatePanel(){
        updatePanel.removeAll();
        updatePanel.setPreferredSize(new Dimension(900,600));
        updatePanel.setLayout(new BoxLayout(updatePanel,BoxLayout.Y_AXIS));
        Clientbll clientbll = new Clientbll();
        List<Client> clients = clientbll.findAll();
        updateTable = View.createClientTable(new ArrayList<Object>(clients));
        updateTable.setBounds(new Rectangle(20,20,1000, 600));
        updateTable.setRowHeight(20);
        updateTable.setPreferredSize(new Dimension(2000,800));
         updateTable.setForeground(new Color(246, 246, 246));
        updatePanel.setPreferredSize(new Dimension(1000, 400));
        updateTable.setBackground(new Color(47, 94, 85));
        updatePanel.add(new JScrollPane(updateTable));
        updateTable.setEnabled(true);
        updateTable.setRowSelectionAllowed(true);
        updatePanel.add(editButton);
        updatePanel.add(deleteButton);
        updatePanel.setBackground(new Color(2,2,2));
        updatePanel.revalidate();
    }
    /**
     * Adauga listener pentru tbutonul de insert
     * @param a listener
     */
    public void addInsertButtonListener(ActionListener a){
        this.insertButton.addActionListener(a);
    }
    /**
     * Adauga listener pentru tbutonul de update/edit
     * @param a listener
     */
    public void addUpdateButtonListener(ActionListener a){
        this.editButton.addActionListener(a);
    }
    /**
     * Adauga listener pentru tbutonul de delete
     * @param a listener
     */
    public void addDeleteButtonListener(ActionListener a){
        this.deleteButton.addActionListener(a);
    }
    /**
     * Afiseaza o informatie inntr-un windoe informativ
     * @param msg mesaj informare
     */
    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
    /**
     * getter pentru tabelul de clienti din panelul update
     * @return tabel
     */
    public JTable getUpdateTable(){
        return updateTable;
    }
    /**
     * getter pentru id-ul clientului
     * @return string
     */
    public String getIdClientText(){
        return this.idClientText.getText();
    }
    /**
     * getter pentru nume
     * @return string
     */
    public String getNameClientText(){
        return this.nameClientText.getText();
    }
    /**
     * getter pentru gender
     * @return string
     */
    public String getGenderText(){
        return this.genderText.getText();
    }
    /**
     * getter pentru email
     * @return string
     */
    public String getEmailText(){
        return this.emailText.getText();
    }
    /**
     * getter pentru
     * @return string
     */
    public String getTelText(){
        return this.telText.getText();
    }
    /**
     * getter pentru adresa
     * @return string
     */
    public String getAddressText(){
        return this.addressText.getText();
    }
}
