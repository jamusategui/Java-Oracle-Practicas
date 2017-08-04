package webservicetester;

import trader.StockPrice;
import trader.StockPriceService;
import trader.*;

public class Main {

	public static void main(String[] args) {
		StockPriceService service = new StockPriceService();
		StockPrice port = service.getStockPricePort();System.out.println("Stock price is : " + port.getStockPrice("JDK"));

	}

}