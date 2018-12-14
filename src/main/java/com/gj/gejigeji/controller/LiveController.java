package com.gj.gejigeji.controller;

import com.gj.gejigeji.model.Address;
import com.gj.gejigeji.repository.AddressRepository;
import com.gj.gejigeji.vo.AddressParam;
import com.gj.gejigeji.vo.OkResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("live")
public class LiveController {

    @Autowired
    AddressRepository addressRepository;

    @PostMapping
    public OkResult setLiveAddress(@RequestBody AddressParam addressParam) {
        Address address1 = new Address(null,addressParam.getAddress(),new Date());
        addressRepository.save(address1);
        return new OkResult(true);
    }

    @GetMapping
    public Address getLiveAddress() {
        final List<Address> all = addressRepository.findAll();
        return all.get(all.size()-1);
    }
}
