package trader.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import trader.BrokerException;
import trader.BrokerModel;
import trader.BrokerModelImpl;
import trader.Stock;

@ManagedBean(name="stocks")
@RequestScoped
public class StocksManagedBean {
private BrokerModel model = BrokerModelImpl.getInstance();
   
	private Stock[] allStocks = null;
	
	/** Creates a new instance of StocksManagedBean */
    public StocksManagedBean() {
    }

    public Stock[] getAllStocks() {
        allStocks = null;
        try {
            allStocks = model.getAllStocks();
        } catch (BrokerException be) {
        	return null;
        }
        return allStocks;
    }

    public void setAllStocks(Stock[] allStocks) {
		this.allStocks = allStocks;
	}
    
}
