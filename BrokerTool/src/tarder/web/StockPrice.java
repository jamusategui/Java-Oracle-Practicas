package tarder.web;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import trader.BrokerException;
import trader.BrokerModel;
import trader.Stock;

@WebService
public class StockPrice {
	@EJB private BrokerModel model;
	@WebMethod
	public String getStockPrice(String symbol) {
		Double price = null;
		try{
			price = model.getStock(symbol).getPrice();
			return String.valueOf(price);
		
		} catch (BrokerException e) {
			String error="Price unavailable";
			return error;
		}
	}
}

