package Flooring.Mastery.Dao;

import Flooring.Mastery.Dto.Product;
import Flooring.Mastery.ServiceLayer.ValidationException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDaoImpl implements ProductDao {
    
    List<Product> listOfProducts = new ArrayList<>();
    
    @Override
    public List<Product> ListAllProducts() throws ValidationException, PersistanceException{
        readProductFile();
        return listOfProducts;
    }
    
    public static final String PRODUCT_FILE = "Products.txt";
    public static final String DELIMITER = ",";
    
    private Product unmarshalTxt(String Taxtxt){
        
        String[] dividedTxt = Taxtxt.split(DELIMITER);
        String type = dividedTxt[0]; 
        BigDecimal CPQF = new BigDecimal(dividedTxt[1]);
        BigDecimal LCPSF = new BigDecimal(dividedTxt[2]);
        
        Product productFromFile = new Product(type, CPQF, LCPSF);
        return productFromFile;
    }
    private void readProductFile() throws ValidationException, PersistanceException{
        
        Scanner scan;
        try {
            scan = new Scanner (new BufferedReader(new FileReader(PRODUCT_FILE) ) );
        } catch (IOException e) {
            throw new PersistanceException("Could not load Product FIle in memory.");
        }
        
        String currentLine;
        Product current;
        listOfProducts.removeAll(listOfProducts);
        
        currentLine = scan.nextLine();
        while(scan.hasNext()){ 
            currentLine = scan.nextLine(); 
            current = unmarshalTxt(currentLine);
            listOfProducts.add(current);
        }
        scan.close();
    }
    
}
