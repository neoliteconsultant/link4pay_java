package io.link4pay.model.transaction;

public class Item{
    public String itemName;
    public String itemId;
    public String itemPricePerUnit;
    public String itemQuantity;

    /**
     *
     * @param itemName
     * @param itemId
     * @param itemPricePerUnit
     * @param itemQuantity
     */
    public Item(String itemName, String itemId, String itemPricePerUnit, String itemQuantity) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.itemPricePerUnit = itemPricePerUnit;
        this.itemQuantity = itemQuantity;
    }
}