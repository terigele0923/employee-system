package com.example.employee_system.presentation.response;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSearchResponse {

    /** 従業員一覧 */
    private List<EmployeeSummaryResponse> employees = new ArrayList<>();

    /** 現在のページ番号（1から開始） */
    private Integer currentPage;

    /** 1ページに表示する件数 */
    private Integer pageSize;

    /** 検索条件に一致した総件数 */
    private Long totalCount;

    /** 総ページ数 */
    private Integer totalPages;

    public List<EmployeeSummaryResponse> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeSummaryResponse> employees) {
        this.employees = employees;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * 前のページが存在するか判定する。
     */
    public boolean isHasPrevious() {
        return currentPage != null && currentPage > 1;
    }

    /**
     * 次のページが存在するか判定する。
     */
    public boolean isHasNext() {
        return currentPage != null
                && totalPages != null
                && currentPage < totalPages;
    }
}