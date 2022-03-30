package Flooring.Mastery.Controller;

import Flooring.Mastery.Dao.DaoImpl;
import Flooring.Mastery.Dao.DaoInterface;
import Flooring.Mastery.Dto.Order;
import Flooring.Mastery.ServiceLayer.serviceLayerIO;
import Flooring.Mastery.ServiceLayer.serviceLayerImpl;
import Flooring.Mastery.View.View;
import java.io.FileNotFoundException;

public class controller {
    
    View view = new View();
    serviceLayerIO service = new serviceLayerImpl();
    DaoInterface Dao = new DaoImpl();
    
    public void run() throws FileNotFoundException{
        boolean keepGoing = true; //Boolean to keep the while loop going
        
            while (keepGoing){
                
                int menuSelection = view.getIntInput("Enter Selection", 1, 6); //Stores user choice from the menu list
                
                switch(menuSelection){ 
                    
                  case 1:
                        DisplayOrders();
                        break;
                    case 2:
                        AddOrder();
                        break;
                    case 3:
                        //EditOrder();
                        break;
                    case 4:
                        //RemoveOrder();
                        break;
                    case 5:
                        //ExportAll();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                       //unknownCommand();
                }
            }
    }
    
    private Order getInfoAndCreateOrder() throws FileNotFoundException{
        
        String Date = view.getStringInput("Enter order date: ");
        int orderNumber = Dao.orderList(Date).size() + 1;
        Order newOrder = new Order(orderNumber);
        newOrder.setDate(Date);
        newOrder.setCustomerName(view.getStringInput("Enter Customer Name: "));
        newOrder.setState(view.getStringInput("Enter state: "));
        newOrder.setProductType(view.getStringInput("Enter product type: "));
        newOrder.setArea(view.getStringInput("Enter the Area: "));
        
        return newOrder;
    }
    private void AddOrder() throws FileNotFoundException{
        Order order = getInfoAndCreateOrder();
        Dao.addOrder(order);
    }
//    private void removeOrder(Order order){
//        view.displayMessage("========= Your are about to DELETE the order below =============");
//        view.displayOrderInfo(order);
//        String choice = view.getStringInput("Enter y to continue or n to cancel");
//        if (choice.equals("y"))
//            service.removeOrder(order.getDate(), order.getOrderNumber());
//        view.printMessage("Current List size is " + Dao.orderList().size());
//    }
    private void DisplayOrders() throws FileNotFoundException{
        String Date = view.getStringInput("Enter a date for the order: ");
        for (Order current: Dao.orderList(Date)){
            view.displayOrderInfo(current);
        }
    }
    
}
