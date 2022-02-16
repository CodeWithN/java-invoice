package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.*;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    //private Collection<Product> products = new ArrayLis t<>();
   // private List<Product> products = new ArrayList<>();
    private Map<Product, Integer> products= new HashMap<>();

    public void addProduct(Product product) {
        this.addProduct(product,1);//product WITH QUANTITY =1
    }

    public void addProduct(Product product, Integer quantity) {
        //if conditions will be implements to both ,,addProduct"

        this.products.put(product, quantity); //we use put, add is not at map
    }

    public BigDecimal getNetPrice() {
        BigDecimal sum = BigDecimal.ZERO;

        for (Product product : this.products.keySet()) { //it takes out a set of keys and it will be a collection, not set
            //for all keys do sth
            Integer quantity = this.products.get(product);
            BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(quantity);
            sum =sum.add(product.getPrice().multiply(quantityAsBigDecimal));
        }
        return sum;
    }

    public BigDecimal getTax() {
        BigDecimal tax = BigDecimal.ZERO;
        for (Product product : this.products.keySet()) {
            tax =tax.add(product.getPrice().multiply(product.getTaxPercent()));
        }
        return tax;
    }

    public BigDecimal getTotal() {

        BigDecimal total = BigDecimal.ZERO;

        for (Product product : this.products.keySet()) {

            Integer quantity = this.products.get(product);
            BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(quantity);
            total =total.add((product.getPrice()).multiply(quantityAsBigDecimal)
                    .add(product.getPrice().multiply(product.getTaxPercent().multiply(quantityAsBigDecimal))));
        }
        return total;
    }
}
