package pretius;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;


public class Main 
{
	
	private double amount;
	public Main(String sciezka)
	{
		
		File plik = new File(sciezka);
		if (plik.isFile())
			System.out.println("Istnieje");
		else{
				System.out.println("Nie istnieje");
		}		
	}
	private void printamount(){
		System.out.format("Total amount: %f",amount);
	}
	public void readFile(String filepath) throws IOException,ParseException{
		
		FileReader filereader = new FileReader(filepath);
		BufferedReader bufferedreader = new BufferedReader(filereader);
		String[] container;
		
		String textline = bufferedreader.readLine();
		try{
		do{
			if(textline.contains("@amount"))
			{
				
				System.out.println( textline.indexOf("@amount:"));
				int index_start= textline.indexOf("@amount:")+8;
				int index_end = textline.indexOf("PLN");
				String value= textline.substring(index_start,index_end);
				
				NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
			    Number number = format.parse(value);
			    double d = number.doubleValue();
			    this.amount = this.amount+number.doubleValue();
			    System.out.println(amount);

				
			//	DecimalFormat df = new DecimalFormat();
			//	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			//	symbols.setDecimalSeparator(',');
			//	symbols.setGroupingSeparator(' ');
			//	df.setDecimalFormatSymbols(symbols);
			//	df.parse(value);
				//System.out.println(value);
			}
		
			System.out.println(textline);
			textline =bufferedreader.readLine();
			
			
		} while(textline!= null);
		}finally{
		
		bufferedreader.close();
		}
		
		
		
	}
	

	public static void main(String[] args)
	{
		String directory= "data/java_Plik z danymi.txt";
		//directory = args[0];
		Main main = new Main(directory);
		try {
			main.readFile(directory);		
			
			//"data/java_Plik z danymi.txt"
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e){
			e.printStackTrace();
		}
		main.printamount();
	}
	

}
