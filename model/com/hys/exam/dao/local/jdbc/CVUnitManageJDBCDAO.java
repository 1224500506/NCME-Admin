package com.hys.exam.dao.local.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CVUnitManageDAO;
import com.hys.exam.model.CVUnit;

/**
 *
 * @author yhq
 */
public class CVUnitManageJDBCDAO extends BaseDao implements CVUnitManageDAO{
    @Override
    public CVUnit findCvunit(CVUnit cvu) {
            // TODO Auto-generated method stub

            String sql = "select * from cv_unit WHERE id ="+cvu.getId()+"";

            List<CVUnit> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class));

            if(list.size()>0){
                    return list.get(0);
            }

            return null;
    }
}
