package com.bookstore.shopping.checkout;

import lombok.Getter;

import java.util.Calendar;
import java.util.Date;

@Getter
public class CheckoutInfo {

    private float productCost;
    private float productTotal;
    private float shippingCostTotal;
    private float paymentTotal;
    private int deliverDays;
    private boolean codSupported;

    public Date getDeliverDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, deliverDays);

        return calendar.getTime();
    }

    public void setProductCost(float productCost) {
        this.productCost = productCost;
    }

    public void setProductTotal(float productTotal) {
        this.productTotal = productTotal;
    }

    public void setShippingCostTotal(float shippingCostTotal) {
        this.shippingCostTotal = shippingCostTotal;
    }

    public void setPaymentTotal(float paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public void setDeliverDays(int deliverDays) {
        this.deliverDays = deliverDays;
    }

    public void setCodSupported(boolean codSupported) {
        this.codSupported = codSupported;
    }
}
