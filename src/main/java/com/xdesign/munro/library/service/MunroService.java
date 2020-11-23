package com.xdesign.munro.library.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.xdesign.munro.library.models.Munro;
import com.xdesign.munro.library.repository.MunroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xdesign.munro.library.helper.CSVHelper;

@Service
public class MunroService
{

  @Autowired
  MunroRepository munroRepository;

  public void saveMunros(MultipartFile file) {
    try {
      List<Munro> munros = CSVHelper.csvToMunros(file.getInputStream());
      munroRepository.saveAll(munros);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }


  public ByteArrayInputStream load() {
    List<Munro> munros = munroRepository.findAll();

    ByteArrayInputStream in = CSVHelper.munrosToCSV(munros);
    return in;
  }

   public List<Munro> getAllMunros() {
    return munroRepository.findAll();
  }
}
