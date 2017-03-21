package me.geosmart.pssms.client.front.web.DTO;

import java.sql.Date;

/**
 * 退单查询DTO
 *
 * @author geosmart
 * @since 2017-03-21
 */
public class BackOrderQueryDTO {
    private int pageNumber;
    private int pageSize;
    private String customerCode;
    private Date beginDate;
    private Date endDate;
    private String backOrderId;
    private String backOrderStatus;

    public String getBackOrderId() {
        return backOrderId;
    }

    public void setBackOrderId(String backOrderId) {
        this.backOrderId = backOrderId;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getBackOrderStatus() {
        return backOrderStatus;
    }

    public void setBackOrderStatus(String backOrderStatus) {
        this.backOrderStatus = backOrderStatus;
    }
}
