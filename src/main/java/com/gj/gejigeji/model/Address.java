package com.gj.gejigeji.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;



@Document(collection = "address")
public class Address {

    @Id
    private String id;

    private String address;

    private Date createTime;

    public Address() {
    }

    public Address(String id, String address, Date createTime) {
        this.id = id;
        this.address = address;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return URLDecoder.decode(address);
    }

    public void setAddress(String address) {

        this.address = URLEncoder.encode(address);
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
