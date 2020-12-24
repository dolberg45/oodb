package model;

import java.util.Comparator;

public class PurchaseComparator implements Comparator<Purchase> {

    @Override
    public int compare(Purchase o1, Purchase o2) {
        if (o1.getCar().getPrice() == o2.getCar().getPrice()) {
            return 0;
        } else if (o1.getCar().getPrice() < o2.getCar().getPrice()) {
            return -1;
        } else {
            return 1;
        }
    }
}
