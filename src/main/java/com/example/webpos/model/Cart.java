package com.example.webpos.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {
        Item item1 = null;
        for (Item value : items) {
            if (value.getProduct().getId().equals(item.getProduct().getId())) {
                item1 = value;
            }
        }

        if (item1 != null) {
            items.remove(item1);
        }
        int amount = (item1 == null ? 0 : item1.getQuantity()) + item.getQuantity();

        if (amount > 0) {
            boolean result = items.add(new Item(item.getProduct(), amount));
            if (result) {
                items.sort(Comparator.comparing(o -> o.getProduct().getId()));
            }
        }
        return false;
    }

    public boolean deleteItem(String productId, int amount) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getProduct().getId().equals(productId)) {
                if (item.getQuantity() > amount) {
                    item.setQuantity(item.getQuantity() - amount);
                } else {
                    items.remove(item);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (items.size() ==0){
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n"  );

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
            total += items.get(i).getQuantity() * items.get(i).getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n"  );

        stringBuilder.append("Total...\t\t\t" + total );

        return stringBuilder.toString();
    }
}
