package presentation;

// Packages to import
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Aceasa clasa reprezinta clasa pentru fereastra principala din care se selecteaza celelalte ferestre.
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class View extends JFrame {
        // frame
        /**
         * Buton ce va face vizibila fereastra clientilor
         */
        private JButton clientButton=new JButton("Client operation");
        /**
         * Buton ce va face vizibila fereastra comenzilor
         */
        private JButton orderButton=new JButton("Order Operation");
        /**
         * Buton ce va face vizibila fereastra produselor
         */
        private JButton productButton=new JButton("Product Operation");
        // Constructor

        /**
         * Constructor ce aranjeaza panourile din frame
         */
        public View(){
                this.setPreferredSize(new Dimension(400,200));
                this.setMaximumSize(new Dimension(400,200));
                this.setMinimumSize(new Dimension(400,200));
                this.setLocation(new Point(100,100));

                JPanel mainPanel=new JPanel();
                mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
                mainPanel.add(new JLabel("Order Management"));
                JPanel buttonsPanel=new JPanel();
                buttonsPanel.add(clientButton);
                buttonsPanel.add(orderButton);
                buttonsPanel.add(productButton);
                mainPanel.add(buttonsPanel);
                this.setContentPane(mainPanel);
                this.pack();
                this.setTitle("Order Window");
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        /**
         * Metoda ce foloseste reflection technique pentru a crea un tabel cu o lista de obiecte primita ca parametru
         * @param objects lista de obiecte
         * @return tabel populat
         */
        public static JTable createClientTable(List<Object> objects) {
                if(objects.size()==0){
                        String[] headers={"empty"};
                        String[][] data={{""}};
                        return new JTable(data,headers);
                }
                int length=objects.get(0).getClass().getDeclaredFields().length;
                String[] headers=new String[length];
                String[][] data=new String[objects.size()][length];
                int i=0;
                for(Object object: objects){
                        int j=0;
                        for (Field field : object.getClass().getDeclaredFields()) {
                                field.setAccessible(true); // set modifier to public
                                Object value;
                                try {
                                        value = field.get(object);
                                        data[i][j]=value.toString();
                                        if(i==0)
                                                headers[j]=field.getName();
                                        j++;
                                } catch (IllegalArgumentException | IllegalAccessException e) {
                                        e.printStackTrace();
                                }
                        }
                        i++;
                }
                return new JTable(data,headers);
        }

        /**
         * Adaugare listener
         * @param e listener
         */
        public void addProductListener(ActionListener e){
                this.productButton.addActionListener(e);
        }
        /**
         * Adaugare listener
         * @param e listener
         */
        public void addClientListener(ActionListener e){
                this.clientButton.addActionListener(e);
        }
        /**
         * Adaugare listener
         * @param e listener
         */
        public void addOrderListener(ActionListener e){
                this.orderButton.addActionListener(e);
        }
    }



