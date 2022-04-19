package UserIO;

import Dto.DVD;


public class ViewDVD {
   
    interfaceIO in = new implimentationIO();
    
    public void print(String msg){
        in.print(msg);
    }
    public String getStringInfo(String prompt){
        return in.readString(prompt);
    }
    
    public DVD createDVD(){
        String ID = in.readString("Enter DVD ID: ");
        String title = in.readString("Enter DVD title: ");
        String date = in.readString("Enter DVD release date: ");
        
        DVD newDVD = new DVD(ID);
        newDVD.setTitle(title);
        newDVD.setReleaseDate(date);
        
        return newDVD;
    }
    
    public void displayDVD(DVD dvd){
        String s = String.format("ID:%s %s %s",
                            dvd.getID(),
                            dvd.getTitle(),
                            dvd.getReleaseDate());
        in.print(s);
    }
}
