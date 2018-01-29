package com.opensourceteam.modules.po.admin;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_business_check")
public class TBusinessCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分部门
     */
    @Column(name = "depart_name")
    private String departName;

    /**
     * 职员姓名
     */
    @Column(name = "employee_name")
    private String employeeName;

    /**
     * 应出考天数(日)
     */
    @Column(name = "check_should")
    private BigDecimal checkShould;

    /**
     * 实际出勤天数(日)
     */
    @Column(name = "check_actual")
    private BigDecimal checkActual;

    /**
     * 餐补天数(日)
     */
    @Column(name = "overtime_meal_days")
    private BigDecimal overtimeMealDays;

    /**
     * 入离职缺勤(日)
     */
    @Column(name = "check_absenteeism_days")
    private BigDecimal checkAbsenteeismDays;

    /**
     * 病假(日)
     */
    @Column(name = "check_sick_leave_days")
    private BigDecimal checkSickLeaveDays;

    /**
     * 事假(日)
     */
    @Column(name = "check_absence_leave_days")
    private BigDecimal checkAbsenceLeaveDays;

    /**
     * 丧假(日)
     */
    @Column(name = "check_funeral_leave_days")
    private BigDecimal checkFuneralLeaveDays;

    /**
     * 年假(日)
     */
    @Column(name = "check_year_leave_days")
    private BigDecimal checkYearLeaveDays;

    /**
     * 婚假(日)
     */
    @Column(name = "check_marry_leave_days")
    private BigDecimal checkMarryLeaveDays;

    @Column(name = "create_date")
    private Date createDate;

    private Integer creator;

    @Column(name = "is_del")
    private Boolean isDel;

    /**
     * 备注
     */
    private String remark;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取分部门
     *
     * @return depart_name - 分部门
     */
    public String getDepartName() {
        return departName;
    }

    /**
     * 设置分部门
     *
     * @param departName 分部门
     */
    public void setDepartName(String departName) {
        this.departName = departName;
    }

    /**
     * 获取职员姓名
     *
     * @return employee_name - 职员姓名
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * 设置职员姓名
     *
     * @param employeeName 职员姓名
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * 获取应出考天数(日)
     *
     * @return check_should - 应出考天数(日)
     */
    public BigDecimal getCheckShould() {
        return checkShould;
    }

    /**
     * 设置应出考天数(日)
     *
     * @param checkShould 应出考天数(日)
     */
    public void setCheckShould(BigDecimal checkShould) {
        this.checkShould = checkShould;
    }

    /**
     * 获取实际出勤天数(日)
     *
     * @return check_actual - 实际出勤天数(日)
     */
    public BigDecimal getCheckActual() {
        return checkActual;
    }

    /**
     * 设置实际出勤天数(日)
     *
     * @param checkActual 实际出勤天数(日)
     */
    public void setCheckActual(BigDecimal checkActual) {
        this.checkActual = checkActual;
    }

    /**
     * 获取餐补天数(日)
     *
     * @return overtime_meal_days - 餐补天数(日)
     */
    public BigDecimal getOvertimeMealDays() {
        return overtimeMealDays;
    }

    /**
     * 设置餐补天数(日)
     *
     * @param overtimeMealDays 餐补天数(日)
     */
    public void setOvertimeMealDays(BigDecimal overtimeMealDays) {
        this.overtimeMealDays = overtimeMealDays;
    }

    /**
     * 获取入离职缺勤(日)
     *
     * @return check_absenteeism_days - 入离职缺勤(日)
     */
    public BigDecimal getCheckAbsenteeismDays() {
        return checkAbsenteeismDays;
    }

    /**
     * 设置入离职缺勤(日)
     *
     * @param checkAbsenteeismDays 入离职缺勤(日)
     */
    public void setCheckAbsenteeismDays(BigDecimal checkAbsenteeismDays) {
        this.checkAbsenteeismDays = checkAbsenteeismDays;
    }

    /**
     * 获取病假(日)
     *
     * @return check_sick_leave_days - 病假(日)
     */
    public BigDecimal getCheckSickLeaveDays() {
        return checkSickLeaveDays;
    }

    /**
     * 设置病假(日)
     *
     * @param checkSickLeaveDays 病假(日)
     */
    public void setCheckSickLeaveDays(BigDecimal checkSickLeaveDays) {
        this.checkSickLeaveDays = checkSickLeaveDays;
    }

    /**
     * 获取事假(日)
     *
     * @return check_absence_leave_days - 事假(日)
     */
    public BigDecimal getCheckAbsenceLeaveDays() {
        return checkAbsenceLeaveDays;
    }

    /**
     * 设置事假(日)
     *
     * @param checkAbsenceLeaveDays 事假(日)
     */
    public void setCheckAbsenceLeaveDays(BigDecimal checkAbsenceLeaveDays) {
        this.checkAbsenceLeaveDays = checkAbsenceLeaveDays;
    }

    /**
     * 获取丧假(日)
     *
     * @return check_funeral_leave_days - 丧假(日)
     */
    public BigDecimal getCheckFuneralLeaveDays() {
        return checkFuneralLeaveDays;
    }

    /**
     * 设置丧假(日)
     *
     * @param checkFuneralLeaveDays 丧假(日)
     */
    public void setCheckFuneralLeaveDays(BigDecimal checkFuneralLeaveDays) {
        this.checkFuneralLeaveDays = checkFuneralLeaveDays;
    }

    /**
     * 获取年假(日)
     *
     * @return check_year_leave_days - 年假(日)
     */
    public BigDecimal getCheckYearLeaveDays() {
        return checkYearLeaveDays;
    }

    /**
     * 设置年假(日)
     *
     * @param checkYearLeaveDays 年假(日)
     */
    public void setCheckYearLeaveDays(BigDecimal checkYearLeaveDays) {
        this.checkYearLeaveDays = checkYearLeaveDays;
    }

    /**
     * 获取婚假(日)
     *
     * @return check_marry_leave_days - 婚假(日)
     */
    public BigDecimal getCheckMarryLeaveDays() {
        return checkMarryLeaveDays;
    }

    /**
     * 设置婚假(日)
     *
     * @param checkMarryLeaveDays 婚假(日)
     */
    public void setCheckMarryLeaveDays(BigDecimal checkMarryLeaveDays) {
        this.checkMarryLeaveDays = checkMarryLeaveDays;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return creator
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * @return is_del
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     * @param isDel
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", departName=").append(departName);
        sb.append(", employeeName=").append(employeeName);
        sb.append(", checkShould=").append(checkShould);
        sb.append(", checkActual=").append(checkActual);
        sb.append(", overtimeMealDays=").append(overtimeMealDays);
        sb.append(", checkAbsenteeismDays=").append(checkAbsenteeismDays);
        sb.append(", checkSickLeaveDays=").append(checkSickLeaveDays);
        sb.append(", checkAbsenceLeaveDays=").append(checkAbsenceLeaveDays);
        sb.append(", checkFuneralLeaveDays=").append(checkFuneralLeaveDays);
        sb.append(", checkYearLeaveDays=").append(checkYearLeaveDays);
        sb.append(", checkMarryLeaveDays=").append(checkMarryLeaveDays);
        sb.append(", createDate=").append(createDate);
        sb.append(", creator=").append(creator);
        sb.append(", isDel=").append(isDel);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}