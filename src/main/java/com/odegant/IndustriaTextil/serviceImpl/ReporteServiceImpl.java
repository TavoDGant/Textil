package com.odegant.IndustriaTextil.serviceImpl;

import com.odegant.IndustriaTextil.dao.EmpleadoDAO;
import com.odegant.IndustriaTextil.entity.Empleado;
import com.odegant.IndustriaTextil.service.ReporteService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private EmpleadoDAO empleadoDAO;

    public void reportes(Integer id, OutputStream outputStream) throws FileNotFoundException, JRException {
        List<Empleado> empleado = empleadoDAO.findById(id).stream().collect(Collectors.toList());

        File file = ResourceUtils.getFile("classpath:reporte.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(empleado);

        Map<String, Object> map = new HashMap<>();
        map.put("Industria", "Textil");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, source);
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    }
}
