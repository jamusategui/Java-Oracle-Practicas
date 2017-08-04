package trader;

import java.util.*;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Local
@Stateless
public class BrokerModelImpl implements BrokerModel {

	@PersistenceContext
	private EntityManager em;

	/** Creates a new instance of BrokerModelImpl */
	public BrokerModelImpl() {
	}

	// Customer segment state change methods
	/**
	 * ---------------------------------------------------------- Adds the
	 * Customer to the broker model
	 */
	public void addCustomer(Customer cust) throws BrokerException {
		try {
			em.persist(cust);
		} catch (Exception ex) {
			throw new BrokerException("Duplicate Id : " + cust.getId());
		}
	}

	/**
	 * ------------------------------------------------------------- deletes the
	 * customer from the broker model
	 */
	public void deleteCustomer(Customer cust) throws BrokerException {
		String id = cust.getId();
		Customer c = em.find(Customer.class, id);

		if (c == null) {
			throw new BrokerException("Record for " + id + " not found");
		} else {
			em.remove(c);
		}

	}

	/**
	 * ------------------------------------------------------------- Updates the
	 * customer in the broker model
	 */
	public void updateCustomer(Customer cust) throws BrokerException {
		Customer c = em.find(Customer.class, cust.getId());
		if (c == null) {
			throw new BrokerException("Customer : " + cust.getId()
					+ " not found");
		} else {
			em.merge(cust);
		}
	}

	// Customer segment state query methods
	/**
	 * ------------------------------------------------------------- Given an
	 * id, returns the Customer from the model
	 */
	public Customer getCustomer(String id) throws BrokerException {
		Customer cust = em.find(Customer.class, id);
		if (cust != null) {
			return cust;
		} else {
			throw new BrokerException("Customer : " + id + " not found");
		}
	}

	/**
	 * ------------------------------------------------------------- Returns all
	 * customers in the broker model
	 */
	public Customer[] getAllCustomers() throws BrokerException {
		Query query = em.createNativeQuery("SELECT * FROM CUSTOMER",
				Customer.class);
		List customers = query.getResultList();
		return (Customer[]) customers.toArray(new Customer[0]);
	}

	
	
/**********************************************************************************************
 **********************************************************************************************	
 **********************************************************************************************/

	
	
	public CustomerShare[] getAllCustomerShares(String customerId)
			throws BrokerException {
		Query query = em.createNativeQuery("SELECT * FROM SHARES WHERE SSN = '"
				+ customerId + "'", CustomerShare.class);
		List shares = query.getResultList();

		if (shares.size() == 0) {
			throw new BrokerException("Shares for " + customerId + " not found");
		} else {
			return (CustomerShare[]) shares.toArray(new CustomerShare[0]);
		}
	}

	public CustomerShare[] getAllCustomerSharesParam(String customerId)
			throws BrokerException {
		Query query = em.createNativeQuery("SELECT * FROM SHARES WHERE SSN = ?", CustomerShare.class);
		
		query.setParameter(1, customerId);
		List shares = query.getResultList();

		if (shares.size() == 0) {
			throw new BrokerException("Shares for " + customerId + " not found");
		} else {
			return (CustomerShare[]) shares.toArray(new CustomerShare[0]);
		}
	}

	public CustomerShare[] getAllCustomerSharesParamName(String customerId)
			throws BrokerException {
		Query query = em.createNativeQuery("SELECT * FROM SHARES WHERE SSN = ?custId", CustomerShare.class);
		
		query.setParameter("custId", customerId);
		List shares = query.getResultList();

		if (shares.size() == 0) {
			throw new BrokerException("Shares for " + customerId + " not found");
		} else {
			return (CustomerShare[]) shares.toArray(new CustomerShare[0]);
		}
	}
	
	private static final String QUERY_HQL_FIND_ALL_CUSTOMERS_SHARES = "SELECT cs FROM CustomerShare cs WHERE cs.customerId = :custID";
	public CustomerShare[] getAllCustomerSharesNoNative(String customerId) throws BrokerException {
		Query query = em.createQuery(QUERY_HQL_FIND_ALL_CUSTOMERS_SHARES, Customer.class);
		
		query.setParameter("custID", customerId);
		List shares = query.getResultList();
		if (shares.size() == 0) {
			throw new BrokerException("Shares for " + customerId + " not found");
		} else {
			return (CustomerShare[]) shares.toArray(new CustomerShare[0]);
		}
	}
	

	
/**********************************************************************************************
 **********************************************************************************************	
 **********************************************************************************************/	

	
	
	public void addCustomerShare(CustomerShare cs) throws BrokerException {
		CustomerShare[] shares = getAllCustomerShares(cs.getCustomerId());
		for (int i = 0; i < shares.length; i++) {
			if (cs.getStockSymbol().equals(shares[i].getStockSymbol())) {
				throw new BrokerException("Duplicate Share : "
						+ cs.getCustomerId() + " " + cs.getStockSymbol());
			}
		}
		em.persist(cs);
	}

	public void updateCustomerShare(CustomerShare cs) throws BrokerException {
		CustomerShare share = em.find(CustomerShare.class, cs.getId());
		if (share == null) {
			throw new BrokerException("Share : " + cs.getCustomerId() + " "
					+ cs.getStockSymbol() + " not found.");
		} else {
			em.merge(cs);
		}
	}

	
	
	/**********************************************************************************************
	 **********************************************************************************************	
	 **********************************************************************************************/	

	
	
	public Stock[] getAllStocks() throws BrokerException {
		Query query = em.createNativeQuery("SELECT * FROM STOCK", Stock.class);
		List stocks = query.getResultList();
		return (Stock[]) stocks.toArray(new Stock[0]);
	}

	public Stock getStock(String symbol) throws BrokerException {
		Stock stock = em.find(Stock.class, symbol);
		if (stock == null) {
			throw new BrokerException("Stock : " + symbol + " not found");
		} else {
			return stock;
		}
	}

	public void addStock(Stock stock) throws BrokerException {
		try {
			em.persist(stock);
		} catch (EntityExistsException exe) {
			throw new BrokerException("Duplicate Stock : " + stock.getSymbol());
		}
	}

	public void updateStock(Stock stock) throws BrokerException {
		Stock s = em.find(Stock.class, stock.getSymbol());
		if (s == null) {
			throw new BrokerException("Stock : " + stock.getSymbol()
					+ " not found");
		} else {
			em.merge(stock);
		}
	}

	public void deleteStock(Stock stock) throws BrokerException {
		String id = stock.getSymbol();
		Stock s = em.find(Stock.class, id);
		if (s == null) {
			throw new BrokerException("Stock : " + id
					+ " not found");
		} else {
			em.remove(stock);
		}
	}
	
	
	
}
