package Beans;

import Entities.Items;
import Beans.util.JsfUtil;
import Beans.util.PaginationHelper;
import Entities.ItemsCategory;
import Entities.ItemsDet;
import Entities.RptItemsCtlg;
import Entities.RptItemsCtlgDet;
import Facade.ItemsFacade;
import JWSAdmin.Jwsadmin;
import Util.Message;
import Util.Files;
import Util.ReportsPDF;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "itemsController")
@SessionScoped
public class ItemsController implements Serializable {

    private Items current;
    private List<Items> items = null;
    @EJB
    private Facade.ItemsFacade ejbFacade;
    @EJB
    private Facade.ItemsDetFacade ejbIDetFacade;
    @EJB
    private Facade.ItemsCategoryFacade ejbICategoryFacade;
    @EJB
    private Facade.RptItemsCtlgDetFacade ejbRptICtlgDet;
    @EJB
    private Facade.RptItemsCtlgFacade ejbRptICtlg;
    
    private int selectedItemIndex;
    boolean enable_saveLbs = false;
    boolean editable = false;
    boolean exist = false;
    private boolean isFirstItemDet;
    
    private Map<String,String> types = new HashMap<>();
    private Map<String,String> sizes = new HashMap<>();
    private Map<String,String> gender = new HashMap<>();
    
    
    private ItemsDet itemsdet_tmp;
    private RptItemsCtlgDet itemrptdet_tmp;
    private RptItemsCtlg rpt_ctmp;
    
    private UploadedFile file;
    private Message msg = new Message();
    
    private List<Items> filteredItems;
    
    private List<ItemsDet> acmpltItemsDet;
    private List<ItemsDet> itemsDetReport;
    private List<ItemsCategory> itemsCategory;
    private List<RptItemsCtlgDet> itemsRpt;
    
    ReportsPDF reportPdf = new ReportsPDF();
    
    private boolean addUnisexGender = false;
    private boolean addInventory = false;
    private boolean enableUpload=false;
    private String genderRpt;
    
    public ItemsController() {
        
    }
    
    @PostConstruct
    public void init(){
        setTypes(new HashMap<String, String>());
        getTypes().put("Non-inventory Part", "Non-inventory Part");
        getTypes().put("Service", "Service");
        
        setSizes(new HashMap<String, String>());
        getSizes().put("Small", "Small");
        getSizes().put("Medium", "Medium");
        getSizes().put("Large", "Large");
        getSizes().put("XLarge", "XLarge");
        
        setGender(new HashMap<String, String>());
        getGender().put("Men", "M");
        getGender().put("Women", "W");
        getGender().put("Unisex", "U");
        getGender().put("Girls", "KG");
        getGender().put("Boys", "KB");
        getGender().put("Kids Unisex", "KU");
        
        addUnisexGender = false;
        addInventory = false;
        enableUpload=false;                 // check where to enable this ************************************************************
        
        resetCurrentItemDet();
        resetCurrent();
        setItemsDetReport((List<ItemsDet>) new ArrayList());
        getItemsDetReport().add(itemsdet_tmp);
        setIsFirstItemDet(true);
        itemsCategory = ejbICategoryFacade.findItemsCategory();
        
        setRpt_ctmp(new RptItemsCtlg());
        itemrptdet_tmp = new RptItemsCtlgDet();
        itemsRpt = new ArrayList();
        
    }
    
    public void resetCurrentItemDet(){
        itemsdet_tmp = new ItemsDet();
        itemsdet_tmp.setBrand("-");
        itemsdet_tmp.setColor("-");
        itemsdet_tmp.setISize("-");
        itemsdet_tmp.setPic("");
        itemsdet_tmp.setGender(" ");
        itemsdet_tmp.setPicName("upload.jpg");
        itemsdet_tmp.setPrice(BigInteger.ZERO);
    }
    
    public void resetCurrent(){
        current = new Items();
        current.setDescription("-");
        current.setCode("-");
    }
    
    public void resetitemsRpt(){
        setRpt_ctmp(new RptItemsCtlg());
        itemrptdet_tmp = new RptItemsCtlgDet();
        itemsRpt = new ArrayList();
    }
    
    public Items getSelected() {
        if (current == null) {
            current = new Items();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ItemsFacade getFacade() {
        return ejbFacade;
    }
    

    public String prepareList() {
        return "List";
    }
    
    
    public List<ItemsDet> getItemsDet(){
        if(current.getItemsDetList()==null)
            current.setItemsDetList(new ArrayList());
        return current.getItemsDetList();
    }
    
    public List<ItemsCategory> getItemsCategory(){
        if(itemsCategory == null)
            itemsCategory = ejbICategoryFacade.findItemsCategory();
        return itemsCategory;
    }
    
    public void addMessage(){
        if(addUnisexGender)
            JsfUtil.addSuccessMessage("Styles Unisex will be included in the catalog");
    }
    
    public void addAction() {
        if(!isEditable()){
            //getItems().remove(0); //si es el primer item, remover primero el obj temporal
            enable_saveLbs=false;
        }
        itemsdet_tmp.setItemFk(current);
        itemsdet_tmp.setStatus("A");
        itemsdet_tmp.setSdate(new Date());
        itemsdet_tmp.setCorp(jwsadminSesion().getCorp());
        current.getItemsDetList().add(itemsdet_tmp);       
        resetCurrentItemDet();
        setEditable(true);
        msg.addMessage("Item added!!");
    }
    
    public void addItemDetReport(){
        if(isFirstItemDet){
            getItemsDetReport().remove(0);
            isFirstItemDet=false;
        }
        itemsdet_tmp.setCorp(jwsadminSesion().getCorp());
        getItemsDetReport().add(itemsdet_tmp);
        itemrptdet_tmp.setIdItemDet(itemsdet_tmp);
        getItemsRpt().add(itemrptdet_tmp);
        itemrptdet_tmp = new RptItemsCtlgDet();
    }
    
    public void generateCatalogGeneral(){
        String s;
        String unisex;
        
        if(addUnisexGender){
            if(getGenderRpt().startsWith("K")){
                unisex = "KU";
            }
            else{
                unisex = "U";
            }
        }else{
            unisex="";
        }
        s = reportPdf.createCatalogGeneralPDF(jwsadminSesion().getCorp().getId(), 
                                        this.current.getCategoryfk().getId(),
                                        genderRpt,
                                        addInventory,
                                        unisex);
        JsfUtil.addSuccessMessage(s);
    }
    
    public void saveItemsForCatalog(){
        try{
            ejbRptICtlg.create(rpt_ctmp);                
            for (RptItemsCtlgDet itemsRpt1 : itemsRpt) {
                itemrptdet_tmp = itemsRpt1;
                itemrptdet_tmp.setIdRpt(rpt_ctmp);
                itemrptdet_tmp.setCorp(jwsadminSesion().getCorp());
                ejbRptICtlgDet.create(itemrptdet_tmp);
            }
        }catch(Exception e){
            JsfUtil.addErrorMessage(e, e.getMessage());
        }
    }
    
    public void generateCatalogByItem(){
        String s;
        
        saveItemsForCatalog(); 
        s = reportPdf.createCatalogByItemPDF(jwsadminSesion().getCorp().getId(), rpt_ctmp.getId());
        resetitemsRpt();
        JsfUtil.addSuccessMessage(s);
    }
    
    public void clearAction(){
        resetCurrentItemDet();
        resetCurrent();
    }
    
    public void selectItemDettoEdit(ItemsDet i){
        this.itemsdet_tmp = i;
    }

    public String prepareView() {
        return "View";
    }

    public String prepareCreate() {
        current = new Items();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create(){
        try{
            int i=0;  
            
            if(exist){  // think if you shoud validate itemdet here ****************************
              while(current.getItemsDetList().size()>i){
                 itemsdet_tmp = current.getItemsDetList().get(i);
                
                 if(itemsdet_tmp.getId()!=null){
                    ejbIDetFacade.edit(itemsdet_tmp);
                 }else{
                    ejbIDetFacade.create(itemsdet_tmp);
                 }
                 i++;
              }
              update();    
            }else{
              current.setStatus("A");
              if(current.getCategoryfk()==null){
                current.setCategoryfk(itemsCategory.get(0));
              }
              current.setSdate(new Date());
              current.setCorp(jwsadminSesion().getCorp());
              getFacade().create(current);
              JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ItemsCreated"));
            }
            resetCurrent();
            resetCurrentItemDet();
            exist = false;
            return "";
        }catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return "";
        }
    }
    
    
    public void updateItemDetAction(){ // 04-15-21 not being use
        try{
            validateItemDet(itemsdet_tmp);
            ejbIDetFacade.edit(itemsdet_tmp);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ItemsDetUpdated"));
            resetCurrentItemDet();
        }catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));            
        }
    }
    // CREATE A FUNCTION TO VALIDATE ITEMdET IS CORRECT BEFORE TO UPDATE IN THE DB AND USE IN OTHER FUNCTIONS   
    
    public void validateItemDet(ItemsDet a){
        if(a.getCorp() == null){
           a.setCorp(jwsadminSesion().getCorp());
        }
        if(a.getStatus() == null){
            a.setStatus("A");
        }
        if(a.getSdate() == null){
           a.setSdate(new Date());
        }
    }
    public String prepareEdit() {
        return "Edit";
    }

    public String update(){
        try{
            current.setCorp(jwsadminSesion().getCorp());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ItemsUpdated"));
            return "";
        }catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        return "List";
    }

    public String destroyAndView() {
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            return "List";
        }
    }
 
    
    
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void handleFileUpload(){
        Files a = new Files();
        //String path = jwsadminSesion().getPicsPath();
        String path = jwsadminSesion().getUrl_pic_web_abs();
        String new_name = current.getCode().replace('/','-')+"-"+itemsdet_tmp.getColor().replace('/', '-')+".jpg";
        itemsdet_tmp.setPic(path+new_name);
        System.out.println("URL PIC:"+itemsdet_tmp.getPic()+"--------***************************************");
        itemsdet_tmp.setPicName(new_name);
        if(file!= null){
            try {
                //a.copyFile(itemsdet_tmp.getPic(), file.getInputstream());
                a.copyFile(jwsadminSesion().getUrl_pic_web_abs()+itemsdet_tmp.getPicName(), file.getInputstream()); // saving in the web file 
            }catch (IOException ex) {
               JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("UploadFileError"));
            }
        }
        FacesMessage msg = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void findItemByCode(){
        current = ejbFacade.findItembyCode(current.getCode());        
        if(current ==null){
            JsfUtil.addSuccessMessage("NO items found for code entered!");
            current = new Items();
            resetCurrentItemDet();  // testing %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%^^^^^^^^^^^^^^^^^^^^^^^^&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
            exist=false;
        }else{
            resetCurrentItemDet();
            exist=true;
        }
    }
    
    
    
    public List<ItemsDet> findItemDetbyColor(String color){
        acmpltItemsDet = ejbIDetFacade.findItemsDetbyColor(color, jwsadminSesion().getCorp().getId());
        return acmpltItemsDet;
               
    }
    
    public List<Items> getItems() {
        items= ejbFacade.getItems();
        
        return items;
    }
    
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public boolean isEditable(){
        return editable;
    }
    
    public void setEditable(boolean edi){
        editable = edi;
    }
    /**
     * @return the itemsdet_tmp
     */
    public ItemsDet getItemsdet_tmp() {
        return itemsdet_tmp;
    }

    /**
     * @param itemsdet_tmp the itemsdet_tmp to set
     */
    public void setItemsdet_tmp(ItemsDet itemsdet_tmp) {
        this.itemsdet_tmp = itemsdet_tmp;
    }

    /**
     * @return the types
     */
    public Map<String,String> getTypes() {
        return types;
    }

    /**
     * @param types the types to set
     */
    public void setTypes(Map<String,String> types) {
        this.types = types;
    }

    /**
     * @return the filteredItems
     */
    public List<Items> getFilteredItems() {
        return filteredItems;
    }

    /**
     * @param filteredItems the filteredItems to set
     */
    public void setFilteredItems(List<Items> filteredItems) {
        this.filteredItems = filteredItems;
    }

    /**
     * @return the sizes
     */
    public Map<String,String> getSizes() {
        return sizes;
    }

    /**
     * @param sizes the sizes to set
     */
    public void setSizes(Map<String,String> sizes) {
        this.sizes = sizes;
    }

    /**
     * @return the isFirstItemDet
     */
    public boolean isIsFirstItemDet() {
        return isFirstItemDet;
    }

    /**
     * @param isFirstItemDet the isFirstItemDet to set
     */
    public void setIsFirstItemDet(boolean isFirstItemDet) {
        this.isFirstItemDet = isFirstItemDet;
    }

    /**
     * @return the itemsDetReport
     */
    public List<ItemsDet> getItemsDetReport() {
        return itemsDetReport;
    }

    /**
     * @param itemsDetReport the itemsDetReport to set
     */
    public void setItemsDetReport(List<ItemsDet> itemsDetReport) {
        this.itemsDetReport = itemsDetReport;
    }

    /**
     * @return the gender
     */
    public Map<String,String> getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Map<String,String> gender) {
        this.gender = gender;
    }

    /**
     * @return the addUnisexGender
     */
    public boolean isAddUnisexGender() {
        return addUnisexGender;
    }

    /**
     * @param addUnisexGender the addUnisexGender to set
     */
    public void setAddUnisexGender(boolean addUnisexGender) {
        this.addUnisexGender = addUnisexGender;
    }

    /**
     * @return the addInventory
     */
    public boolean isAddInventory() {
        return addInventory;
    }

    /**
     * @param addInventory the addInventory to set
     */
    public void setAddInventory(boolean addInventory) {
        this.addInventory = addInventory;
    }

    /**
     * @return the genderRpt
     */
    public String getGenderRpt() {
        return genderRpt;
    }

    /**
     * @param genderRpt the genderRpt to set
     */
    public void setGenderRpt(String genderRpt) {
        this.genderRpt = genderRpt;
    }

    /**
     * @return the itemsRpt
     */
    public List<RptItemsCtlgDet> getItemsRpt() {
        return itemsRpt;
    }

    /**
     * @param itemsRpt the itemsRpt to set
     */
    public void setItemsRpt(List<RptItemsCtlgDet> itemsRpt) {
        this.itemsRpt = itemsRpt;
    }

    /**
     * @return the itemrpt_tmp
     */
    public RptItemsCtlgDet getItemrptDet_tmp() {
        return itemrptdet_tmp;
    }

    /**
     * @param itemrpt_tmp the itemrpt_tmp to set
     */
    public void setItemrptDet_tmp(RptItemsCtlgDet itemrpt_tmp) {
        this.itemrptdet_tmp = itemrpt_tmp;
    }

    /**
     * @return the rpt_ctmp
     */
    public RptItemsCtlg getRpt_ctmp() {
        return rpt_ctmp;
    }

    /**
     * @param rpt_ctmp the rpt_ctmp to set
     */
    public void setRpt_ctmp(RptItemsCtlg rpt_ctmp) {
        this.rpt_ctmp = rpt_ctmp;
    }

    
    
    @FacesConverter(forClass = Items.class)
    public static class ItemsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ItemsController controller = (ItemsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "itemsController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.math.BigDecimal getKey(String value) {
            java.math.BigDecimal key;
            key = new java.math.BigDecimal(value);
            return key;
        }

        String getStringKey(java.math.BigDecimal value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Items) {
                Items o = (Items) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Items.class.getName());
            }
        }

    }
    
    private Jwsadmin jwsadminSesion(){
        ExternalContext contexto=FacesContext.getCurrentInstance().getExternalContext();
        Jwsadmin jwsadmin =(Jwsadmin)contexto.getSessionMap().get("jwsadmin");
        return jwsadmin;
    }

}
