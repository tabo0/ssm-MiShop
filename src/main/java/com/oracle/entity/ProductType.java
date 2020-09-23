package com.oracle.entity;

public class ProductType
{
    private Integer id;
    private String name;

    public ProductType() {
        super();
    }
    public ProductType(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
