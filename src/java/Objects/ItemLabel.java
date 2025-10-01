/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author EMP
 */
public class ItemLabel {
    private int id;
    private String style;
    private int qtybox; //qty of box equals labels you want for the item to add
    private int units;
    private int dzperbox;
    private String PO;
    private String shipto;
    private int totalbox;
    private String color;
    private Integer invoice;
    private String vstyle;
    
    
    public ItemLabel(int id,String style,String po, int qtybox,int units,int dzperbox, String shipto, int totalbox, String color, Integer invoice, String vstyle){
        this.id = id;
        this.style = style;
        this.qtybox = qtybox;
        this.units = units;
        this.dzperbox = dzperbox;
        this.shipto = shipto;
        this.PO = po;
        this.totalbox = totalbox;
        this.color = color;
        this.invoice = invoice;
        this.vstyle = vstyle;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the qtybox
     */
    public int getQtybox() {
        return qtybox;
    }

    /**
     * @param qtybox the qtybox to set
     */
    public void setQtybox(int qtybox) {
        this.qtybox = qtybox;
    }

    /**
     * @return the units
     */
    public int getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(int units) {
        this.units = units;
    }

    /**
     * @return the dzperbox
     */
    public int getDzperbox() {
        return dzperbox;
    }

    /**
     * @param dzperbox the dzperbox to set
     */
    public void setDzperbox(int dzperbox) {
        this.dzperbox = dzperbox;
    }

    /**
     * @return the PO
     */
    public String getPO() {
        return PO;
    }

    /**
     * @param PO the PO to set
     */
    public void setPO(String PO) {
        this.PO = PO;
    }

    /**
     * @return the shipto
     */
    public String getShipto() {
        return shipto;
    }

    /**
     * @param shipto the shipto to set
     */
    public void setShipto(String shipto) {
        this.shipto = shipto;
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
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the invoice
     */
    public Integer getInvoice() {
        return invoice;
    }

    /**
     * @param invoice the invoice to set
     */
    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    /**
     * @return the vstyle
     */
    public String getVstyle() {
        return vstyle;
    }

    /**
     * @param vstyle the vstyle to set
     */
    public void setVstyle(String vstyle) {
        this.vstyle = vstyle;
    }
    
}
