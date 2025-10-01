/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.FileResolver;

/**
 *
 * @author EMP
 */
public class ReportsPDF {
    private String urlJasperReports = "C:\\WSAdmin\\reports\\";
    private Object JRXmlLoader;
    static Connection conn = null;
     
    SimpleDateFormat dtf = new SimpleDateFormat("yyyy-mm-dd");
    Date d;

    
    public String createConnectionXEPDB1(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
          }
          catch (ClassNotFoundException e) {
            return e.getMessage();
          }
          //Para iniciar el Logger.
          //inicializaLogger();
          try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@10.1.10.58:1521/XEPDB1","globalac", "global19");
            conn.setAutoCommit(false);
          }
          catch (SQLException e) {
            return e.getLocalizedMessage();
          }
          return "";
    }
    
    public String createLabelsPDF(String po, BigDecimal gid){
        try {
            d = new Date();
            String sourceFileName = urlJasperReports + "ShippingLabel.jrxml";
            String filename = dtf.format(d)+"-"+gid+".pdf";
            
            createConnectionXEPDB1();
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            
            Map parameters = new HashMap();
            parameters.put("p_ponumber", po);
            parameters.put("p_gid", gid);
            
            JasperReport report = JasperCompileManager.compileReport(sourceFileName);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);
            // Exporta el informe a PDF
            JasperExportManager.exportReportToPdfFile(print,urlJasperReports+filename);
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);
            response.setContentType("WSAdmin/pdf");
            response.setHeader("Content-Disposition", "inline;filename=" + filename);            
            response.getOutputStream().write(pdfBytes);
            response.flushBuffer();
            return "";
        } catch (IOException | JRException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
    }
    
    public String createLabelsSimplePDF(String po, BigDecimal gid){
        try {
            d = new Date();
            String sourceFileName = urlJasperReports + "ShippingLabel_Simple.jrxml";
            String filename = dtf.format(d)+"-"+gid+".pdf";
            
            createConnectionXEPDB1();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            
            
            Map parameters = new HashMap();
            parameters.put("p_ponumber", po);
            parameters.put("p_gid", gid);
            
            JasperReport report = JasperCompileManager.compileReport(sourceFileName);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);
            // Exporta el informe a PDF
            JasperExportManager.exportReportToPdfFile(print,urlJasperReports+filename);
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);
            response.setContentType("WSAdmin/pdf");
            response.setHeader("Content-Disposition", "inline;filename=" + filename);
            response.getOutputStream().write(pdfBytes);
            response.flushBuffer();
            return "";
        } catch (IOException | JRException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
    }
    
    public String createPackingSPDF(String po, BigDecimal gid){
        try {
            d = new Date();
            String sourceFileName = urlJasperReports + "PackingSlip.jrxml";
            String filename = dtf.format(d)+"-"+gid.toString()+"PS.pdf";
            
            createConnectionXEPDB1();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            
            
            Map parameters = new HashMap();
            parameters.put("p_ponumber", po);
            parameters.put("p_gid", gid);
            
            JasperReport report = JasperCompileManager.compileReport(sourceFileName);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);
            // Exporta el informe a PDF
            JasperExportManager.exportReportToPdfFile(print,urlJasperReports+filename);
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);
            response.setContentType("WSAdmin/pdf");
            response.setHeader("Content-Disposition", "inline;filename=" + filename);
            response.getOutputStream().write(pdfBytes);
            response.flushBuffer();
            return "";
        } catch (IOException | JRException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
    }
    
    public String createPickListPDF(String po, BigDecimal id){
        try {
            d = new Date();
            String sourceFileName = urlJasperReports + "PickList.jrxml";
            String filename = dtf.format(d)+"-"+id.toString()+"PickList.pdf";
            
            createConnectionXEPDB1();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            
            Map parameters = new HashMap();
            parameters.put("P_PONUMBER", po);
            parameters.put("P_POID", id);
                        
            JasperReport report = JasperCompileManager.compileReport(sourceFileName);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);
            // Exporta el informe a PDF
            JasperExportManager.exportReportToPdfFile(print,urlJasperReports+filename);
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);
            response.setContentType("WSAdmin/pdf");
            response.setHeader("Content-Disposition", "inline;filename=" + filename);
            response.getOutputStream().write(pdfBytes);
            //response.flushBuffer();
            response.getOutputStream().flush();
            response.getOutputStream().close();
            return "";
        } catch (IOException | JRException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
    }
    
    public String createCatalogGeneralPDF(BigDecimal corp, BigDecimal category, String gender, boolean addinventory, String unisex){
        try {
            d = new Date();
            String sourceFileName = urlJasperReports + "catalog_general_category.jrxml";
            String filename = dtf.format(d)+"-"+gender+"_Catalog.pdf";
            
            createConnectionXEPDB1();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            
            Map parameters = new HashMap();
            parameters.put("P_CORP", corp);
            parameters.put("P_GENDER", gender);
            parameters.put("P_IDCATEGORY", category);
            parameters.put("P_ADDINVENTORY", addinventory);
            parameters.put("P_UNISEX", unisex);
                        
            JasperReport report = JasperCompileManager.compileReport(sourceFileName);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);
            // Exporta el informe a PDF
            JasperExportManager.exportReportToPdfFile(print,urlJasperReports+filename);
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);
            response.setContentType("WSAdmin/pdf");
            response.setHeader("Content-Disposition", "inline;filename=" + filename);
            response.getOutputStream().write(pdfBytes);
            response.flushBuffer();
            return "";
        } catch (IOException | JRException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
    }
    
    public String createCatalogByItemPDF(BigDecimal corp, BigDecimal rptid){
        try {
            d = new Date();
            String sourceFileName = urlJasperReports + "rpt_catalog_byItem.jrxml";
            String filename = dtf.format(d)+"-"+corp+"_CatalogByItem.pdf";
            
            createConnectionXEPDB1();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            
            Map parameters = new HashMap();
            parameters.put("P_CORP", corp);
            parameters.put("P_RPTID", rptid);
                        
            JasperReport report = JasperCompileManager.compileReport(sourceFileName);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);
            // Exporta el informe a PDF
            JasperExportManager.exportReportToPdfFile(print,urlJasperReports+filename);
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);
            response.setContentType("WSAdmin/pdf");
            response.setHeader("Content-Disposition", "inline;filename=" + filename);
            response.getOutputStream().write(pdfBytes);
            response.flushBuffer();
            return "";
        } catch (IOException | JRException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
    }
}
