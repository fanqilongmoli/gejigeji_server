package com.gj.gejigeji.service;

import com.gj.gejigeji.model.Theme;
import com.gj.gejigeji.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {

    @Autowired
    ThemeRepository themeRepository;

    public List<Theme> getAll() {
        return themeRepository.findAll();
    }
}
