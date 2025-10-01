/*
 * Autor: Grace Criollo
 * Update: 
 * and open the template in the editor.
 */
package Beans;

import Entities.PMakelabels;
import Facade.util.JsfUtil;
import JWSAdmin.Jwsadmin;
import Objects.ItemLabel;
import Util.*;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.RowEditEvent;
import java.util.Random;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;



/**
 *
 * @author EMP
 */
@ManagedBean(name = "labelsBean")
@SessionScoped
public class LabelsBean implements Serializable {

    private String item_ponumber;
    private int totalbox;
    private String item_style;
    private int item_qtybox; //qty of box equals labels you want for the item to add
    private int item_units;
    private int item_dzperbox;
    private String item_shipto;
    private String item_color;
    private String item_vstyle;
    private Integer item_invoice;
    private Integer item_gid;
    private PMakelabels pmakelabel;    
    
    private PMakelabels obj_itemlabel;
    private Message msg = new Message();
    
    private int total_labels = 0;
    private int total_dz=0;
    private int total_units=0;
    
    private boolean editable = false;
    private String aditional_msj="";
    
    private boolean enable_btnPrintLbs = false;
    private boolean enable_btnPrintSLbs = false;
    private boolean enable_btnPrintPS = false;
    private boolean enable_saveLbs = true;    
    private boolean enable_updateLbs = true;
    private boolean enable_btnUpdate  = true;
    
    
    
    @EJB
    private Facade.PMakelabelsFacade PmakelabelsEjb;
    @EJB
    private Facade.CorpFacade CorpEjb;
    
    private List<PMakelabels> items = new ArrayList();
    private int gid_tmp=0;
    private String po_tmp="";
    
    ReportsPDF reportPdf = new ReportsPDF();

    
    @PostConstruct
    public void init() {
        items.add(new PMakelabels("EXAMPLE","8956-4",4,2,12,"629 Grove St",0,"",0,""));
        setDisable_btnPrintLbs(true);
        setDisable_btnPrintSLbs(true);
        setDisable_btnPrintPS(true);
        setDisable_btnUpdateLbs(true);
        setDisable_saveLbs(true);
    }
    
    public void resetCurrentItem(){
        //item_ponumber ="";
        //totalbox=0;
        item_style="-";
        item_color="-";
        item_qtybox=0;
        item_units=0;
        item_dzperbox=0;
        
    }
    
    public void resetPage(){
        item_ponumber="";
        item_style="-";
        item_shipto="";
        item_qtybox=0;
        item_units=0;
        item_dzperbox=0;
        items = new ArrayList();
        total_labels = 0;
        total_dz=0;
        total_units=0;
        setGid_tmp(0);
        setPo_tmp("");
        aditional_msj="";
        setItem_invoice((Integer) 0);
        disablePrintBtns();
        items.add(new PMakelabels("EXAMPLE","8956-4",4,2,12,"629 Grove St",0,"",0,""));
        editable=false;
    }
    /**
     * @return the ponumber
     */
    public String getPonumber() {
        return item_ponumber;
    }

    /**
     * @param ponumber the ponumber to set
     */
    public void setPonumber(String ponumber) {
        this.item_ponumber = ponumber;
    }

    /**
     * @return the totalbox
     */
    public int getTotalbox() {
        return totalbox;
    }

    /**
     * @param totalbox the totalbox to set
     */
    public void setTotalbox(int totalbox) {
        this.totalbox = totalbox;
    }

    /**
     * @return the item_style
     */
    public String getItem_style() {
        return item_style;
    }

    /**
     * @param item_style the item_style to set
     */
    public void setItem_style(String item_style) {
        this.item_style = item_style;
    }

    /**
     * @return the item_qtybox
     */
    public int getItem_qtybox() {
        return item_qtybox;
    }

    /**
     * @param item_qtybox the item_qtybox to set
     */
    public void setItem_qtybox(int item_qtybox) {
        this.item_qtybox = item_qtybox;
    }

    /**
     * @return the item_units
     */
    public int getItem_units() {
        return item_units;
    }

    /**
     * @param item_units the item_units to set
     */
    public void setItem_units(int item_units) {
        this.item_units = item_units;
    }

    /**
     * @return the item_dzperbox
     */
    public int getItem_dzperbox() {
        return item_dzperbox;
    }

    /**
     * @param item_dzperbox the item_dzperbox to set
     */
    public void setItem_dzperbox(int item_dzperbox) {
        this.item_dzperbox = item_dzperbox;
    }

    /**
     * @return the items
     */
    public List<PMakelabels> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<PMakelabels> items) {
        this.items = items;
    }
    
    /**
     * @return the item_shipto
     */
    public String getItem_shipto() {
        return item_shipto;
    }

    /**
     * @param item_shipto the item_shipto to set
     */
    public void setItem_shipto(String item_shipto) {
        this.item_shipto = item_shipto;
    }
    
    
    public void enablePrintBtns(){
        setDisable_btnPrintLbs(false);
        setDisable_btnPrintSLbs(false);
        setDisable_btnPrintPS(false);
        setDisable_saveLbs(true);        
    }
    
    public void disablePrintBtns(){
        setDisable_btnPrintLbs(true);
        setDisable_btnPrintSLbs(true);
        setDisable_btnPrintPS(true);
        setDisable_saveLbs(false);        
    }
    
    public void enableEditBtns(){
        setDisable_btnPrintLbs(false);
        setDisable_btnPrintSLbs(false);
        setDisable_btnPrintPS(false);
        setDisable_btnUpdateLbs(false);
        setDisable_saveLbs(true);  
    }
    
    /**
     * Action: Add a new item of labels to create
     */
    public void addAction() {
        if(!isEditable()){
            getItems().remove(0); //If it's first item, remove sample item
            enable_saveLbs=false;
        }
        if(item_style.equals("-")){
        }
        obj_itemlabel = new PMakelabels(item_style,item_ponumber,item_qtybox,
                item_units,item_dzperbox, item_shipto,totalbox,item_color, item_invoice, getItem_vstyle());
        total_labels += item_qtybox;
        getItems().add(obj_itemlabel);
        resetCurrentItem();
        setEditable(true);
        msg.addMessage("Item added!!");
    }
    
    public void saveLabels(){
        saveItemsintoDB();
        enablePrintBtns();
    }

    public void updateLabels(){
        updateItemsintoDB();
        enableEditBtns();
    }
    
    public void printPDF(){
        aditional_msj = reportPdf.createLabelsPDF(getPo_tmp(),BigDecimal.valueOf(getGid_tmp()));
    }
    public void printSimpleLabelsPDF(){
        aditional_msj = aditional_msj+reportPdf.createLabelsSimplePDF(getPo_tmp(),BigDecimal.valueOf(getGid_tmp()));
        JsfUtil.addSuccessMessage(""+aditional_msj);
    }
    
   public void printPSPDF(){
        aditional_msj = aditional_msj+reportPdf.createPackingSPDF(getPo_tmp(),BigDecimal.valueOf(getGid_tmp()));
        JsfUtil.addSuccessMessage(""+aditional_msj);
   }
    
    public String saveItemsintoDB() {
        int order=0;
        setGid_tmp(0);
        setPo_tmp("");
        Random rand = new Random();
        setGid_tmp(rand.nextInt(100000));
        try {
           
            for(int i=0; i < items.size(); i++){
                obj_itemlabel = items.get(i);
                if(i == 0 ){
                    setPo_tmp(obj_itemlabel.getPo());
                }
                for(int j = 0; j< obj_itemlabel.getNBox(); j++){ 
                    setPmakelabel(new PMakelabels());
                    getPmakelabel().setCorp(jwsadminSesion().getCorp());
                    getPmakelabel().setDzBox(obj_itemlabel.getDzBox());
                    getPmakelabel().setUnitsBox(obj_itemlabel.getUnitsBox());
                    getPmakelabel().setNBox(obj_itemlabel.getNBox());
                    getPmakelabel().setOsgroup(order+1);
                    getPmakelabel().setPo(obj_itemlabel.getPo());
                    getPmakelabel().setShipto(obj_itemlabel.getShipto());
                    getPmakelabel().setStyle(obj_itemlabel.getStyle());
                    getPmakelabel().setTotalBox(obj_itemlabel.getTotalBox());
                    getPmakelabel().setGId(getGid_tmp());
                    getPmakelabel().setColor(obj_itemlabel.getColor());
                    getPmakelabel().setInvoice(obj_itemlabel.getInvoice());
                    getPmakelabel().setVStyle(obj_itemlabel.getVStyle());
                    PmakelabelsEjb.create(getPmakelabel());
                    order++;
                }
            }           
            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("Set of Labels Created!"));
            return "";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("An error occurred saving in DB, check DB conexion or report Issue to IT!"));
            return null;
        }
    }
    
    public String updateItemsintoDB() { //Developing, pending to test and finish
        int order=0;
        setPo_tmp("");
        try {
           
            for(int i=0; i < items.size(); i++){
                obj_itemlabel = items.get(i);
                if(i == 0 ){
                    setPo_tmp(obj_itemlabel.getPo());
                }
                for(int j = 0; j< obj_itemlabel.getNBox(); j++){ 
                    setPmakelabel(new PMakelabels());
                    getPmakelabel().setCorp(jwsadminSesion().getCorp());
                    getPmakelabel().setDzBox(obj_itemlabel.getDzBox());
                    getPmakelabel().setUnitsBox(obj_itemlabel.getUnitsBox());
                    getPmakelabel().setNBox(obj_itemlabel.getNBox());
                    getPmakelabel().setOsgroup(order+1);
                    getPmakelabel().setPo(obj_itemlabel.getPo());
                    getPmakelabel().setShipto(obj_itemlabel.getShipto());
                    getPmakelabel().setStyle(obj_itemlabel.getStyle());
                    getPmakelabel().setTotalBox(obj_itemlabel.getTotalBox());
                    getPmakelabel().setGId(obj_itemlabel.getGId());
                    getPmakelabel().setColor(obj_itemlabel.getColor());
                    getPmakelabel().setInvoice(obj_itemlabel.getInvoice());
                    getPmakelabel().setVStyle(obj_itemlabel.getVStyle());
                    if(getPmakelabel().getId()== null){
                        PmakelabelsEjb.create(getPmakelabel());
                    }else{
                        PmakelabelsEjb.edit(getPmakelabel());
                    }
                    order++;
                }
            }           
            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("The Labels were Updated!"));
            return "";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("An error occurred saving in DB, check DB conexion or report Issue to IT!"));
            return null;
        }
    }
    
    public void deleteLabel() {
        items.remove(pmakelabel);
        PmakelabelsEjb.remove(pmakelabel);
        pmakelabel = null;
    }
    
    public void calculateTotals(){  // hasta aqui nos quedamos
        total_labels = 0;
        total_dz=0;
        total_units=0;
        for(int i=0; i < items.size(); i++){
            total_labels += items.get(i).getNBox();
            total_dz = total_dz + items.get(i).getDzBox()*items.get(i).getNBox();
            total_units = total_units + items.get(i).getUnitsBox()*items.get(i).getNBox();
            //setTotal_dz(getTotal_dz() + items.get(i).getDzperbox()*items.get(i).getQtybox());
            
        }
        total_units = total_units + total_dz*12;
        if(total_labels != totalbox){
            aditional_msj = "Total Boxes and total labels are different!";
        }else aditional_msj="";
    }
    
    public void findLabelsByPONumberGID(){
        items = PmakelabelsEjb.findLabelsByPONumberGID(getPo_tmp(), getGid_tmp());
        if(items !=null){
            enableEditBtns();
        }
    }
   

    public void onRowEdit(RowEditEvent event) {
        calculateTotals();
    }
     
    public void onRowCancel(RowEditEvent event) {
        msg.addMessage("Item edit cancelled "+(((ItemLabel)event.getObject()).getId()));
    }
    /**
     * @return the total_labels
     */
    public int getTotal_labels() {
        return total_labels;
    }

    /**
     * @param total_labels the total_labels to set
     */
    public void setTotal_labels(int total_labels) {
        this.total_labels = total_labels;
    }

    /**
     * @return the editable
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * @param editable the editable to set
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * @return the total_units
     */
    public int getTotal_units() {
        return total_units;
    }

    /**
     * @param total_units the total_units to set
     */
    public void setTotal_units(int total_units) {
        this.total_units = total_units;
    }

    /**
     * @return the total_dz
     */
    public int getTotal_dz() {
        return total_dz;
    }

    /**
     * @param total_dz the total_dz to set
     */
    public void setTotal_dz(int total_dz) {
        this.total_dz = total_dz;
    }

    /**
     * @return the aditional_msj
     */
    public String getAditional_msj() {
        return aditional_msj;
    }

    /**
     * @param aditional_msj the aditional_msj to set
     */
    public void setAditional_msj(String aditional_msj) {
        this.aditional_msj = aditional_msj;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return item_color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.item_color = color;
    }

    /**
     * @return the item_invoice
     */
    public Integer getItem_invoice() {
        return item_invoice;
    }

    /**
     * @param item_invoice the item_invoice to set
     */
    public void setItem_invoice(Integer item_invoice) {
        this.item_invoice = item_invoice;
    }

    /**
     * @return the enable_btnPrintLbs
     */
    public boolean isEnable_btnPrintLbs() {
        return enable_btnPrintLbs;
    }

    /**
     * @param enable_btnPrintLbs the enable_btnPrintLbs to set
     */
    public void setDisable_btnPrintLbs(boolean enable_btnPrintLbs) {
        this.enable_btnPrintLbs = enable_btnPrintLbs;
    }

    /**
     * @return the enable_btnPrintSLbs
     */
    public boolean isEnable_btnPrintSLbs() {
        return enable_btnPrintSLbs;
    }

    /**
     * @param enable_btnPrintSLbs the enable_btnPrintSLbs to set
     */
    public void setDisable_btnPrintSLbs(boolean enable_btnPrintSLbs) {
        this.enable_btnPrintSLbs = enable_btnPrintSLbs;
    }

    /**
     * @return the enable_btnPrintPS
     */
    public boolean isEnable_btnPrintPS() {
        return enable_btnPrintPS;
    }

    /**
     * @param enable_btnPrintPS the enable_btnPrintPS to set
     */
    public void setDisable_btnPrintPS(boolean enable_btnPrintPS) {
        this.enable_btnPrintPS = enable_btnPrintPS;
    }

    /**
     * @return the enable_saveLbs
     */
    public boolean isEnable_saveLbs() {
        return enable_saveLbs;
    }

    /**
     * @param enable_saveLbs the enable_saveLbs to set
     */
    public void setDisable_saveLbs(boolean enable_saveLbs) {
        this.enable_saveLbs = enable_saveLbs;
    }

    /**
     * @return the item_vstyle
     */
    public String getItem_vstyle() {
        return item_vstyle;
    }

    /**
     * @param item_vstyle the item_vstyle to set
     */
    public void setItem_vstyle(String item_vstyle) {
        this.item_vstyle = item_vstyle;
    }

    /**
     * @return the item_gid
     */
    public Integer getItem_gid() {
        return item_gid;
    }

    /**
     * @param item_gid the item_gid to set
     */
    public void setItem_gid(Integer item_gid) {
        this.item_gid = item_gid;
    }
    
    private Jwsadmin jwsadminSesion(){
        ExternalContext contexto=FacesContext.getCurrentInstance().getExternalContext();
        Jwsadmin jwsadmin =(Jwsadmin)contexto.getSessionMap().get("jwsadmin");
        return jwsadmin;
    }

    /**
     * @return the po_tmp
     */
    public String getPo_tmp() {
        return po_tmp;
    }

    /**
     * @param po_tmp the po_tmp to set
     */
    public void setPo_tmp(String po_tmp) {
        this.po_tmp = po_tmp;
    }

    /**
     * @return the gid_tmp
     */
    public int getGid_tmp() {
        return gid_tmp;
    }

    /**
     * @param gid_tmp the gid_tmp to set
     */
    public void setGid_tmp(int gid_tmp) {
        this.gid_tmp = gid_tmp;
    }

    /**
     * @return the enable_updateLbs
     */
    public boolean isEnable_updateLbs() {
        return enable_updateLbs;
    }

    /**
     * @param enable_updateLbs the enable_updateLbs to set
     */
    public void setDisable_btnUpdateLbs(boolean enable_updateLbs) {
        this.enable_updateLbs = enable_updateLbs;
    }

    /**
     * @return the pmakelabel
     */
    public PMakelabels getPmakelabel() {
        return pmakelabel;
    }

    /**
     * @param pmakelabel the pmakelabel to set
     */
    public void setPmakelabel(PMakelabels pmakelabel) {
        this.pmakelabel = pmakelabel;
    }

    /**
     * @return the enable_btnUpdate
     */
    public boolean isEnable_btnUpdate() {
        return enable_btnUpdate;
    }

    /**
     * @param enable_btnUpdate the enable_btnUpdate to set
     */
    public void setEnable_btnUpdate(boolean enable_btnUpdate) {
        this.enable_btnUpdate = enable_btnUpdate;
    }
}
