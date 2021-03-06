/*
 * This file is generated by jOOQ.
 */
package com.ivan.sunnyblog.base.models.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Category implements Serializable {

    private static final long serialVersionUID = -1135082235;

    private Long   categoryId;
    private String name;

    public Category() {}

    public Category(Category value) {
        this.categoryId = value.categoryId;
        this.name = value.name;
    }

    public Category(
        Long   categoryId,
        String name
    ) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Category (");

        sb.append(categoryId);
        sb.append(", ").append(name);

        sb.append(")");
        return sb.toString();
    }
}
