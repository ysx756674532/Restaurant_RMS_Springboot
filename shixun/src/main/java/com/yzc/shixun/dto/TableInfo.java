package com.yzc.shixun.dto;

public class TableInfo {

    private Integer id;
    private Integer tableId;
    private Integer tableCapa;
    private String tablePosition;
    private Integer restId;

    @Override
    public String toString() {
        return "TableInfo{" +
                "id=" + id +
                ", tableId=" + tableId +
                ", tableCapa='" + tableCapa + '\'' +
                ", tablePosition='" + tablePosition + '\'' +
                ", restId=" + restId +
                '}';
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTableCapa() {
        return tableCapa;
    }

    public void setTableCapa(Integer tableCapa) {
        this.tableCapa = tableCapa;
    }

    public String getTablePosition() {
        return tablePosition;
    }

    public void setTablePosition(String tablePosition) {
        this.tablePosition = tablePosition;
    }

    public Integer getRestId() {
        return restId;
    }

    public void setRestId(Integer restId) {
        this.restId = restId;
    }
}
