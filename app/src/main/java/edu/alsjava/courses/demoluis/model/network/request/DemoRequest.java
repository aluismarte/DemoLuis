package edu.alsjava.courses.demoluis.model.network.request;

/**
 * Created by aluis on 11/26/19.
 */
public class DemoRequest {

    private int offset;
    private int quantity = 10;

    public DemoRequest(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
