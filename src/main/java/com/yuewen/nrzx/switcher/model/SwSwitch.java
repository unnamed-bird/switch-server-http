package com.yuewen.nrzx.switcher.model;

/**
 * project : switch-server
 *
 * @author 2020-04-26 16:29</br>
 */
public class SwSwitch {
    public static final int SW_TYPE_REG = 2;
    public static final int SW_TYPE_GRAY = 3;
    private Integer id;
    private String name;
    private String nameKey;
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
