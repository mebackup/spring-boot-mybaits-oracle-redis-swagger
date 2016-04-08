package com.my.domain;

import java.util.Date;

public class Inventory {
    private long id;
    private Date lastModifiedDate;
    private int reductionCount;
    private int leftCount;
    private Product product;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(int leftCount) {
        this.leftCount = leftCount;
    }

    public int getReductionCount() {
        return reductionCount;
    }

    public void setReductionCount(int reductionCount) {
        this.reductionCount = reductionCount;
    }

}
