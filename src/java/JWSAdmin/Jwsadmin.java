/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JWSAdmin;

import Beans.util.JsfUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Entities.Corp;
import Entities.Users;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
/**
 *
 * @author EMP
 */
@ManagedBean(name= "jwsadmin")
@SessionScoped
public class Jwsadmin implements Serializable{
    private Corp corp;
    @EJB
    private Facade.CorpFacade ejbCorpFacade;
    @EJB
    private Facade.UsersFacade ejbUser;
    private String url_pic_web;
    private String db_url_connection;
    private String db_ip;
    private String db_user;
    private String db_password;
    private String url_pic_web_abs;
    
    private String user;
    private String pass;
    
    private Users userlogin;
    
    public Jwsadmin(){
    }
    
    @PostConstruct
    public void init(){
        //getCurrentCorp();
        readProperties();
    }
    
    private void getCurrentCorp(){
        corp= ejbCorpFacade.findCorpbyId(userlogin.getCorp().getId());
        if(corp == null){
            setCorp(new Corp());
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    
    public String login(){
        userlogin = ejbUser.validateUserAccess(getUser(), getPass());
        if(userlogin!=null){
            getCurrentCorp();
            return "index.xhtml";
        }else{
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return "";
        }
            
    }
    
    private void readProperties(){
    try (InputStream input = new FileInputStream("C:/WSAdmin/config.properties.txt")) {

            Properties prop = new Properties();
            prop.load(input);

            // get the property value and print it out
            setUrl_pic_web(prop.getProperty("pics_url_web"));
            setDb_ip(prop.getProperty("db.ip"));
            setDb_url_connection(prop.getProperty("db.connection"));
            setDb_user(prop.getProperty("db.user"));
            setDb_password(prop.getProperty("db.password"));
            setUrl_pic_web_abs(prop.getProperty("pics_url_web_absolute"));

        } catch (IOException ex) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    
    
    
    public String getPicsPath(){
        if(corp==null){
            getCurrentCorp();
        }
        return corp.getUrlitempics();
     }

    /**
     * @return the corp
     */
    public Corp getCorp() {
        return corp;
    }

    /**
     * @param corp the corp to set
     */
    public void setCorp(Corp corp) {
        this.corp = corp;
    }

    /**
     * @return the url_pic_web
     */
    public String getUrl_pic_web() {
        if(url_pic_web==null){
            readProperties();
        }
        return url_pic_web;
    }

    /**
     * @param url_pic_web the url_pic_web to set
     */
    public void setUrl_pic_web(String url_pic_web) {
        this.url_pic_web = url_pic_web;
    }

    /**
     * @return the db_url_connection
     */
    public String getDb_url_connection() {
        return db_url_connection;
    }

    /**
     * @param db_url_connection the db_url_connection to set
     */
    public void setDb_url_connection(String db_url_connection) {
        this.db_url_connection = db_url_connection;
    }

    /**
     * @return the db_ip
     */
    public String getDb_ip() {
        return db_ip;
    }

    /**
     * @param db_ip the db_ip to set
     */
    public void setDb_ip(String db_ip) {
        this.db_ip = db_ip;
    }

    /**
     * @return the db_user
     */
    public String getDb_user() {
        return db_user;
    }

    /**
     * @param db_user the db_user to set
     */
    public void setDb_user(String db_user) {
        this.db_user = db_user;
    }

    /**
     * @return the db_password
     */
    public String getDb_password() {
        return db_password;
    }

    /**
     * @param db_password the db_password to set
     */
    public void setDb_password(String db_password) {
        this.db_password = db_password;
    }

    /**
     * @return the url_pic_web_abs
     */
    public String getUrl_pic_web_abs() {
        return url_pic_web_abs;
    }

    /**
     * @param url_pic_web_abs the url_pic_web_abs to set
     */
    public void setUrl_pic_web_abs(String url_pic_web_abs) {
        this.url_pic_web_abs = url_pic_web_abs;
    }

    /**
     * @return the userlogin
     */
    public Users getUserlogin() {
        return userlogin;
    }

    /**
     * @param userlogin the userlogin to set
     */
    public void setUserlogin(Users userlogin) {
        this.userlogin = userlogin;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
}
