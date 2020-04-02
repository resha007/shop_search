package com.shop.domain;
// Generated Mar 31, 2020 7:37:15 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * District generated by hbm2java
 */
public class District  implements java.io.Serializable {


     private Integer id;
     private Province province;
     private String code;
     private String name;
     private String status;
     private Set<Area> areas = new HashSet<Area>(0);

    public District() {
    }

	
    public District(Province province) {
        this.province = province;
    }
    public District(Province province, String code, String name, String status, Set<Area> areas) {
       this.province = province;
       this.code = code;
       this.name = name;
       this.status = status;
       this.areas = areas;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Province getProvince() {
        return this.province;
    }
    
    public void setProvince(Province province) {
        this.province = province;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public Set<Area> getAreas() {
        return this.areas;
    }
    
    public void setAreas(Set<Area> areas) {
        this.areas = areas;
    }




}


