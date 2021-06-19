package com.odegant.IndustriaTextil.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.FileNotFoundException;
import java.io.OutputStream;

public interface ReporteService {

    void reportes(Integer id, OutputStream outputStream) throws FileNotFoundException, JRException;
}
