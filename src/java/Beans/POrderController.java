package Beans;

import Entities.POrder;
import Beans.util.JsfUtil;
import Beans.util.PaginationHelper;
import Entities.Customers;
import Entities.Items;
import Entities.ItemsDet;
import Entities.PMakelabels;
import Entities.POrderDet;
import Entities.PymtTerms;
import Facade.POrderFacade;
import JWSAdmin.Jwsadmin;
import Util.ReportsPDF;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "pOrderController")
@SessionScoped
public class POrderController implements Serializable {

    private POrder current;
    private List<POrder> items;
    @EJB
    private Facade.POrderFacade ejbFacade;
    @EJB
    private Facade.POrderDetFacade ejbPODet;
    @EJB
    private Facade.CustomersFacade ejbCustomer;
    @EJB
    private Facade.PymtTermsFacade ejbPymtTerms;
    @EJB
    private Facade.ItemsDetFacade ejbitemsDet;
    @EJB
    private Facade.ItemsFacade ejbitems;
    @EJB
    private Facade.PMakelabelsFacade PmakelabelsEjb;
    
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private String cust_text;
    private List<PymtTerms> pymtTerms;
    private POrderDet porderdet_tmp;
    private List<ItemsDet> itemsDetList;
    private String style;
    private ItemsDet itemdet_tmp;
    private Customers customer_tpm;
    private boolean POexist;
    private boolean PL_btn_enable;
    private PMakelabels pmakelabel;
    private List<PMakelabels> list_pmakelbls;
    private int gid_tmp=0;
    ReportsPDF reportPdf = new ReportsPDF();
    
    
    private String additional_msj;
    

    public POrderController() {
    }

    @PostConstruct
    public void init(){
        current = new POrder();
        current.setSdate(new Date());
        pymtTerms = ejbPymtTerms.findAll();
        //setItemsDetList(ejbitemsDet.findItemsDetActive());
        style="-";
        porderdet_tmp = new POrderDet();
        POexist = false;
        setPL_btn_enable(false);
    }
    
    public POrder getSelected() {
        if (current == null) {
            current = new POrder();
            selectedItemIndex = -1;
        }
        return current;
    }

    private POrderFacade getFacade() {
        return ejbFacade;
    }

   
    public String prepareList() {
        //recreateModel();
        return "List";
    }

    public String prepareView() {
        return "View";
    }

    public String prepareCreate() {
        current = new POrder();
        selectedItemIndex = -1;
        return "Create";
    }

    public List<Customers> getCustomersSearch(){
        if(ejbCustomer.getCustomersSearch("") == null){
            JsfUtil.addErrorMessage("No results for that search!");
        }
        return ejbCustomer.getCustomersSearch("");
    }
    
    public List<Customers> getCustomersSearchQuery(String query){
        if(ejbCustomer.getCustomersSearch(query) == null){
            JsfUtil.addErrorMessage("No results for that search!");
        }
        return ejbCustomer.getCustomersSearch(query);
    }
    
    public String create() {
        try {
            int i=0;
            if(POexist){
                while(current.getPOrderDetList().size()>i){
                    porderdet_tmp = current.getPOrderDetList().get(i);
                    porderdet_tmp.setColor(porderdet_tmp.getItem().getColor()); //probando prueba
                    if(porderdet_tmp.getId()!=null){
                        ejbPODet.edit(porderdet_tmp);
                    }else{
                        ejbPODet.create(porderdet_tmp);
                    }
                    i++;
                }
                update();
            }else{                
                getFacade().create(current);
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("POrderCreated"));
            }
            resetPOrderdet_tmp();
            resetCurrent();
            POexist = false;            
            setPL_btn_enable(false);
            return "";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    
    /*public String saveItemsintoDB() {
        int order=0;
        Random rand = new Random();
        porderdet_tmp = new POrderDet();
        double qtylbs=0;
        double qtydzleft=0;
        double qtyunitsleft=0;
        int totalbox=0;
        list_pmakelbls = new ArrayList();
        gid_tmp = rand.nextInt(100000);
        try {
           
            for(int i=0; i < current.getPOrderDetList().size(); i++){
                porderdet_tmp = current.getPOrderDetList().get(i);
                qtylbs=0 ;
                qtylbs = Math.floor((porderdet_tmp.getPcs().divide(BigInteger.valueOf(12)).divide(porderdet_tmp.getDz())).doubleValue());
                qtydzleft = 
                for(int j = 0; j< qtylbs; j++){ 
                    pmakelabel = new PMakelabels();
                    pmakelabel.setCorp(jwsadminSesion().getCorp());
                    pmakelabel.setDzBox(porderdet_tmp.getDz().intValue());
                    pmakelabel.setUnitsBox(porderdet_tmp.getDz().intValue());
                    pmakelabel.setNBox(Integer.valueOf((int)qtylbs));
                    pmakelabel.setOsgroup(order+1);
                    pmakelabel.setPo(current.getPo());
                    pmakelabel.setShipto(current.getCustomer().getShipAdd());
                    pmakelabel.setStyle(porderdet_tmp.getStyle());
                    pmakelabel.setTotalBox(obj_itemlabel.getTotalBox());
                    pmakelabel.setGId(gid_tmp);
                    pmakelabel.setColor(porderdet_tmp.getColor());
                    pmakelabel.setInvoice(Integer.valueOf(current.getInvoice()));
                    pmakelabel.setVStyle(porderdet_tmp.getVItem());
                    PmakelabelsEjb.create(pmakelabel);
                    order++;
                    list_pmakelbls.add(pmakelabel);
                }
                totalbox+=qtylbs;
            }
            
            
            Facade.util.JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PMakelabelsCreated"));
            return "";
        } catch (Exception e) {
            Facade.util.JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }*/
    
    public boolean updateInventory(){
        try{
            int i =0;
            while(current.getPOrderDetList().size()>i){
                
            }
            return true;
        }catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return false;
        }
    }

    public void findItemsDetByCode(){
        Items i = ejbitems.findItembyCode(getStyle());
        if(i != null){
            itemsDetList = i.getItemsDetList();
            porderdet_tmp.setDescription(i.getDescription());
            porderdet_tmp.setStyle(style);
            porderdet_tmp.setColor(itemsDetList.get(0).getColor()); //probando prueba 
            porderdet_tmp.setItem(itemsDetList.get(0));             //probando prueba 
        }else{
            JsfUtil.addErrorMessage("There were no results for that style!");
        }
        
    }
    
    public void findPOrderByPONumber(){
        current = ejbFacade.findByPOrder(current.getPo());
        if(current == null){
            JsfUtil.addErrorMessage("There were no results for that PO number!");
            POexist = false;
            return;
        }
        POexist=true;
    }
    
      
    public void addAction(){
        if(current.getPOrderDetList()== null)
        {
           current.setPOrderDetList(new ArrayList());
        }
       // porderdet_tmp.setColor( );  Set correct color
        porderdet_tmp.setColor(porderdet_tmp.getItem().getColor());
        porderdet_tmp.setPOrder(current);
        current.getPOrderDetList().add(porderdet_tmp);
        resetPOrderdet_tmp();
    }
    
    public void resetPOrderdet_tmp(){
        porderdet_tmp = new POrderDet();
        itemsDetList = new ArrayList();
    }
    
    public void resetCurrent(){
        current = new POrder();
    }
    
    public void selectItemDetAction(){
        if(itemdet_tmp != null){
            porderdet_tmp.setColor(itemdet_tmp.getColor());
        }
    }
    
    public String prepareEdit() {
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("POrderUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public void printPickListSPDF(){
        additional_msj = additional_msj+reportPdf.createPickListPDF(current.getPo(), current.getId());
        Facade.util.JsfUtil.addSuccessMessage(" "+additional_msj);
    }
    
    public String destroy() {
        performDestroy();
        //recreatePagination();
        //recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        //recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            //recreateModel();
            return "List";
        }
    }

       
    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("POrderDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public List<POrder> getItems() {
        if (items == null) {
            items=ejbFacade.findAll();
        }
        return items;
    }
 
     private Jwsadmin jwsadminSesion(){
        ExternalContext contexto=FacesContext.getCurrentInstance().getExternalContext();
        Jwsadmin jwsadmin =(Jwsadmin)contexto.getSessionMap().get("jwsadmin");
        return jwsadmin;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    /**
     * @return the cust_text
     */
    public String getCust_text() {
        return cust_text;
    }

    /**
     * @param cust_text the cust_text to set
     */
    public void setCust_text(String cust_text) {
        this.cust_text = cust_text;
    }

    /**
     * @return the pymtTerms
     */
    public List<PymtTerms> getPymtTerms(){
        if(pymtTerms==null){
            pymtTerms = ejbPymtTerms.findAll();
        }
        return pymtTerms;
    }

    /**
     * @param pymtTerms the pymtTerms to set
     */
    public void setPymtTerms(List<PymtTerms> pymtTerms) {
        this.pymtTerms = pymtTerms;
    }

    /**
     * @return the porderdet
     */
    public POrderDet getPorderdet_tmp() {
        return porderdet_tmp;
    }

    /**
     * @param porderdet the porderdet to set
     */
    public void setPorderdet_tmp(POrderDet porderdet) {
        this.porderdet_tmp = porderdet;
    }

    /**
     * @return the itemsDetList
     */
    public List<ItemsDet> getItemsDetList() {
        return itemsDetList;
    }

    /**
     * @param itemsDetList the itemsDetList to set
     */
    public void setItemsDetList(List<ItemsDet> itemsDetList) {
        this.itemsDetList = itemsDetList;
    }

    /**
     * @return the style
     */
    public String getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * @return the itemdet_tmp
     */
    public ItemsDet getItemdet_tmp() {
        return itemdet_tmp;
    }

    /**
     * @param itemdet_tmp the itemdet_tmp to set
     */
    public void setItemdet_tmp(ItemsDet itemdet_tmp) {
        this.itemdet_tmp = itemdet_tmp;
    }

    /**
     * @return the customer_tpm
     */
    public Customers getCustomer_tpm() {
        return customer_tpm;
    }

    /**
     * @param customer_tpm the customer_tpm to set
     */
    public void setCustomer_tpm(Customers customer_tpm) {
        this.customer_tpm = customer_tpm;
    }

    /**
     * @return the PL_btn_enable
     */
    public boolean isPL_btn_enable() {
        return PL_btn_enable;
    }

    /**
     * @param PL_btn_enable the PL_btn_enable to set
     */
    public void setPL_btn_enable(boolean PL_btn_enable) {
        this.PL_btn_enable = PL_btn_enable;
    }

    @FacesConverter(forClass = POrder.class)
    public static class POrderControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            POrderController controller = (POrderController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pOrderController");
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
            if (object instanceof POrder) {
                POrder o = (POrder) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + POrder.class.getName());
            }
        }

    }

}
