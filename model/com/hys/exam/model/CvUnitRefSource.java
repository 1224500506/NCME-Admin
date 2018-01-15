package com.hys.exam.model;

import java.io.Serializable;

public class CvUnitRefSource implements Serializable {
    private Long unit_ref_id;
    private Long unit_id;
    private Long source_id;

    public Long getUnit_ref_id() {
        return unit_ref_id;
    }

    public void setUnit_ref_id(Long unit_ref_id) {
        this.unit_ref_id = unit_ref_id;
    }

    public Long getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(Long unit_id) {
        this.unit_id = unit_id;
    }

    public Long getSource_id() {
        return source_id;
    }

    public void setSource_id(Long source_id) {
        this.source_id = source_id;
    }
}
