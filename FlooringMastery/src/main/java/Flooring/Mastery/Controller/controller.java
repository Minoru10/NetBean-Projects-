package Flooring.Mastery.Controller;

import Flooring.Mastery.Dao.PersistanceException;
import Flooring.Mastery.Dto.Order;
import Flooring.Mastery.Dto.Product;
import Flooring.Mastery.ServiceLayer.ValidationException;
import Flooring.Mastery.ServiceLayer.serviceLayerImpl;
import Flooring.Mastery.View.View;
import java.math.BigDecimal;

public class controller {
    
    View view;// = new View();
    serviceLayerImpl service;// = new serviceLayerImpl();

    public controller(View view, serviceLayerImpl service) {
        this.view = view;
        this.service = service;
    }
    
    public void run() throws ValidationException, PersistanceException{
        boolean keepGoing = true; //Boolean to keep the while loop going
        
            while (keepGoing){
                
                int menuSelection = view.printMenu(); //Stores user choice from the menu list
                
                try{
                    switch(menuSelection){ 

                      case 1:
                            DisplayOrders();
                            break;
                        case 2:
                            AddOrder();
                            break;
                        case 3:
                            EditOrder();
                            break;
                        case 4:
                            RemoveOrder();
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
                }catch (ValidationException | PersistanceException e) {
                    view.displayMessage(e.getMessage());
                 } 
            }
        
    }
    private void DisplayOrders() throws ValidationException, PersistanceException{
        String Date = view.getDateInput("Enter the new order date in the for YYYY-MM-DD: ");
        for (Order current: service.ListAllOrders(Date)){
            view.displayOrderInfo(current);
        }
        view.getStringInput("Press Enter to continue"); 
    }
    private void AddOrder() throws ValidationException, PersistanceException{
        Order order = getInfoAndCreateOrder();
        service.validateOrderData(order);
        view.printMessage("=====Order Summary=======");
        view.displayOrderInfo(order);
        String decision = view.getStringInput("DO you want to place the order? Enter Y(yes) or N(no)");
        if (decision.equals("Y")){
            service.addOrder(order, order.getDate());
            view.displayMessage("ORDER HAS BEEN PLACES!!");
            view.getStringInput("Press Enter to continue");
        }
        else
            view.getStringInput("Press Enter to continue");
    }
    private void EditOrder() throws ValidationException, PersistanceException{
        String date = view.getDateInput("Enter the new order date in the for YYYY-MM-DD: ");
        int num = view.getIntInput("Enter the order number: ");
        Order newOrder = null;
        for (Order current: service.ListAllOrders(date)){
            if (current.getOrderNumber() == num){
                newOrder = getEditInfo(current);
                newOrder.setDate(date);
            }
        }
        view.printMessage("=====Changes Summary=======");
        view.displayOrderInfo(newOrder);
        String decision = view.getStringInput("DO you want to save changes to order? Enter Y(yes) or N(no)");
        if (decision.equals("Y")){
            service.editAndSave(newOrder, date);
            view.displayMessage("ORDER CHANGES SAVES!!");
            view.getStringInput("Press Enter to continue");
        }
        else
            view.getStringInput("Press Enter to continue");
    }
    private void RemoveOrder() throws PersistanceException, ValidationException {
        String date = view.getDateInput("Enter the order date: ");
        int num = view.getIntInput("Enter the order number: ");
        Order order = null;
        for (Order current: service.ListAllOrders(date)){
            if (current.getOrderNumber() == num)
                order = current;
        }
        view.displayOrderInfo(order);
        String decision = view.getStringInput("Are you sure you want to delete order? Enter Y(yes) or N(no)");
        if (decision.equals("Y"))
            service.removeOrder(order, date);
        else
            view.getStringInput("Press Enter to continue");
         
    }
    
    
    
    private Order getInfoAndCreateOrder() throws ValidationException, PersistanceException {
     
        String Date = view.getStringInput("Enter the new order date in the for YYYY-MM-DD: ");
        service.checkForFile(Date);
        Date = "Orders_"+Date+".txt";
        int orderNum = service.generateOrderNumber(Date);
        Order newOrder = new Order(orderNum);
        newOrder.setDate(Date);
        newOrder.setCustomerName(view.getStringInput("Enter new Customer Name: "));
        newOrder.setState(view.getStringInput("Enter state: "));
        for (Product current: service.ListProducts()){
            view.displayMessage
            (current.getProductType() + " " + current.getCostPerSquareFoot().toString() + " " + current.getLaborCostPerSquareFoot().toString());
        }
        newOrder.setProductType(view.getStringInput("Enter product type from list above: "));
        newOrder.setArea(view.getDecimalValueWithinRange("Enter the Area: ", 100, 10000));
        
        return newOrder;
    }
    
    
    private Order getEditInfo(Order order) throws ValidationException, PersistanceException{
        String currentName = order.getCustomerName();
        String currentState = order.getState();
        String currentProductType = order.getProductType();
        BigDecimal currentArea = order.getArea();
        
        String newName = view.getStringInput("Enter customer name (" + currentName + "):");
        String newState = view.getStringInput("Enter state (" + currentState + "):");
        String newPT = view.getStringInput("Enter product type (" + currentProductType + "):");
        String newArea = view.getStringInput("Enter Area (" + currentArea + "):");
        
        if ( !newName.equals(null) && newName.trim().length() != 0 && !newName.isBlank())
            order.setCustomerName(newName);
        if ( !newState.equals(null) && newState.trim().length() != 0 && !newState.isBlank())
            order.setState(newState);
        if ( !newPT.equals(null) && newPT.trim().length() != 0 && !newPT.isBlank())
            order.setProductType(newPT);
        if ( !newArea.equals(null) && newArea.trim().length() != 0 && !newArea.isBlank())
            order.setArea(new BigDecimal(newArea));
        
        service.validateOrderData(order);
            
        return order;
    }


    
}
