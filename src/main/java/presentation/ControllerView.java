package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aceasa clasa reprezinta clasa pentru controllerul ferestrei principale view din care se selecteaza celelalte ferestre
 * pentru a fi afisate
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 */
public class ControllerView {
    /**
     * Camp pentru fereastra principala de controlat
     */
    private View view;
    /**
     * Camp pentru controllerul ferestrei dedeicate clientilor
     */
    private ControllerClient controllerClient;
    /**
     * Camp pentru controllerul ferestrei dedeicate comenzilor
     */
    private ControllerOrder controllerOrder;
    /**
     * Camp pentru controllerul ferestrei dedeicate produselor
     */
    private ControllerProduct controllerProduct;
    /**
     * Camp pentru fereastra dedicata clientilor
     */
    private ClientWindow clientWindow;
    /**
     * Camp pentru fereastra dedicata comenzilor
     */
    private OrderWindow orderWindow;
    /**
     * Camp pentru fereastra dedicata produselor
     */
    private ProductWindow productWindow;

    /**
     * Va primi ca parametru fereastra principala
     * Va crea celelalte ferestre sli controllerele lor
     * Va adauga listeneri pentru selectia ferestrei de afisat
     * @param view c
     */
    public ControllerView(View view){
        this.view=view;
        clientWindow=new ClientWindow();
        controllerClient=new ControllerClient(clientWindow);
        orderWindow=new OrderWindow();
        controllerOrder=new ControllerOrder(orderWindow);
        productWindow=new ProductWindow();
        controllerProduct=new ControllerProduct(productWindow);
        this.view.addClientListener(new ClientListener());
        this.view.addOrderListener(new OrderListener());
        this.view.addProductListener(new ProductListener());
    }

    /**
     * Aceasa clasa reprezinta clasa interna pentru listenerul butonului ce slecteaza fereastra clientWindow.
     * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
     * @since 22 April 2021
     */
    public class ClientListener implements ActionListener{
        /**
         *Face vizibila fereastra clientWindow
         * @param e s
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            clientWindow.setVisible(true);
            clientWindow.setUpdatePanel();
            productWindow.setVisible(false);
            orderWindow.setVisible(false);
        }
    }
    /**
     * Aceasa clasa reprezinta clasa interna pentru listenerul butonului ce slecteaza fereastra ClientWindow.
     * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
     * @since 22 April 2021
     */
    public class OrderListener implements ActionListener{
        /**
         *Face vizibila fereastra orderWindow
         * @param e s
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            clientWindow.setVisible(false);
            productWindow.setVisible(false);
            orderWindow.setVisible(true);
            orderWindow.setProductsPanel();
            orderWindow.setClientsPanel();
        }
    }

    /**
     * Aceasa clasa reprezinta clasa interna pentru listenerul butonului ce slecteaza fereastra productWindow.
     * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
     * @since 22 April 2021
     */
    public class ProductListener implements ActionListener{
        /**
         *Face vizibila fereastra productWindow
         * @param e s
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            clientWindow.setVisible(false);
            productWindow.setVisible(true);
            productWindow.setUpdatePanel();
            orderWindow.setVisible(false);
        }
    }

}
