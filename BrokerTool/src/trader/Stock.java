package trader;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="STOCK")
public class Stock implements Serializable {

    @Id @Column(name="SYMBOL")private String symbol;
	@Column(name="PRICE")private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
    
    public Stock() {
        this.symbol = null;
        this.price = 0;
    }

    // Methods to return the private values of this object
    public double getPrice() {
        return price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setPrice(double newPrice) {
        price = newPrice;
    }

    public String toString() {
        return "Stock:  " + symbol + "  " + price;
    }
}
