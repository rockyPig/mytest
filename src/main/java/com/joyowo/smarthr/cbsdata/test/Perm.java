package com.joyowo.smarthr.cbsdata.test;

/**
 * @ClassName Perm
 * @Author Spark
 * @Date 2019/4/24 21:49
 * @Description TODO
 * @Version v1.2
 **/
public class Perm {
    private Long id;
    private Long role_menu_action_id;
    private Long user_id;
    private Integer range_type;
    private String bus_table;
    private String  bus_table_column;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRole_menu_action_id() {
        return role_menu_action_id;
    }

    public void setRole_menu_action_id(Long role_menu_action_id) {
        this.role_menu_action_id = role_menu_action_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getRange_type() {
        return range_type;
    }

    public void setRange_type(Integer range_type) {
        this.range_type = range_type;
    }

    public String getBus_table() {
        return bus_table;
    }

    public void setBus_table(String bus_table) {
        this.bus_table = bus_table;
    }

    public String getBus_table_column() {
        return bus_table_column;
    }

    public void setBus_table_column(String bus_table_column) {
        this.bus_table_column = bus_table_column;
    }
}
