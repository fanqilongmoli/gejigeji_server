package com.gj.gejigeji.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "resource_conf")
public class ResourceConf {

    @Id
    private String id;
    @ApiModelProperty("编号")
    private Integer num;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("url")
    private String url;

    public ResourceConf() {
    }

    public ResourceConf(String id, Integer num, String name, String url) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
