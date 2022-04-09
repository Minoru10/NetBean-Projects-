package Flooring.Master.Main;

import Flooring.Mastery.Controller.controller;
import Flooring.Mastery.Dao.OrderDaoImpl;
import Flooring.Mastery.Dao.PersistanceException;
import Flooring.Mastery.Dao.ProductDaoImpl;
import Flooring.Mastery.Dao.TaxDaoImpl;
import Flooring.Mastery.ServiceLayer.ValidationException;
import Flooring.Mastery.ServiceLayer.serviceLayerImpl;
import Flooring.Mastery.View.View;
import Flooring.Mastery.View.userIOImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) throws PersistanceException, ValidationException {
       
        userIOImpl input = new userIOImpl();
        OrderDaoImpl orderDao = new OrderDaoImpl();
        ProductDaoImpl productDao = new ProductDaoImpl();
        TaxDaoImpl taxDao = new TaxDaoImpl();
        View view = new View(input);
        serviceLayerImpl service = new serviceLayerImpl(orderDao, taxDao, productDao);
        controller con = new controller(view, service);
        con.run();

          ApplicationContext appContxt = new ClassPathXmlApplicationContext("applicationContext.xml");
          Flooring.Mastery.Controller.controller controller = appContxt.getBean("controller", controller.class);
    }
}
