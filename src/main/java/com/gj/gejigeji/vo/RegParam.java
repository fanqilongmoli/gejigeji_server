package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class RegParam {

    @ApiModelProperty(value = "手机号",required = true)
    private String phone;
    @ApiModelProperty(value = "验证码",required = true)
    private String authCode;

    @ApiModelProperty(value = "密码",required = true)
    @Length(min = 6, max = 32)
    @NotEmpty
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
