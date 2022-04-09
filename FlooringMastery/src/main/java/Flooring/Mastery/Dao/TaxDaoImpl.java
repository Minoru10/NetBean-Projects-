package Flooring.Mastery.Dao;

import Flooring.Mastery.Dto.Tax;
import Flooring.Mastery.ServiceLayer.ValidationException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaxDaoImpl implements TaxesDao{
    
    List<Tax> listOfTaxes = new ArrayList<>();

    @Override
    public List<Tax> ListAllTaxes() throws ValidationException, PersistanceException{
        readTaxFile();
        return listOfTaxes;
    }
    
    public static final String TAX_FILE = "Taxes.txt";
    public static final String DELIMITER = ",";
    
    private Tax unmarshalTxt(String Taxtxt){
        
        String[] dividedTxt = Taxtxt.split(DELIMITER);
        String stateAbbrv = dividedTxt[0]; 
        String stateName = dividedTxt[1];
        BigDecimal taxRate = new BigDecimal(dividedTxt[2]);
        
        Tax taxFromFile = new Tax(stateAbbrv, stateName, taxRate);
        return taxFromFile;
    }
    private void readTaxFile() throws ValidationException, PersistanceException{
        
        Scanner scan;
        try {
            scan = new Scanner (new BufferedReader(new FileReader(TAX_FILE) ) );
        } catch (IOException e) {
            throw new PersistanceException("Could not load Tax File in memory.");
        }
        
        String currentLine;
        Tax current; 
        listOfTaxes.removeAll(listOfTaxes);
        
        currentLine = scan.nextLine();
        while(scan.hasNext()){ 
            currentLine = scan.nextLine(); 
            current = unmarshalTxt(currentLine);
            listOfTaxes.add(current);
        }
        scan.close();
    }
}
