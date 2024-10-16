/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.List;

/**
 *
 * @author 041AlikinOS
 */
public class PagedDataDto<T> {
    
    private List<T> data;
    private Long totalPage;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "PagedDataDto{" + "data=" + data + ", totalPage=" + totalPage + '}';
    }
    
    
    
}
