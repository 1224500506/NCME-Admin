package com.hys.exam.model;



import java.io.Serializable;
import java.util.List;

import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertInfo;

public class ExpertGroupModel implements Serializable {

    /**
     * Lee
     */
    private static final long serialVersionUID = 3299179869424516827L;

    private ExpertGroup group;

    private List<ExpertInfo> expertList;

    public ExpertGroup getGroup() {
        return group;
    }

    public void setGroup(ExpertGroup group) {
        this.group = group;
    }

    public List<ExpertInfo> getExpertList() {
        return expertList;
    }

    public void setExpertList(List<ExpertInfo> expertList) {
        this.expertList = expertList;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}