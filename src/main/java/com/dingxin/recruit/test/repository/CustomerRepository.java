package com.dingxin.recruit.test.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void delete(String table, Map<String, String> condition) {

        if(table.isEmpty() || condition.isEmpty()) return;

        StringBuffer sql = new StringBuffer("delete from " + table.trim() + " where 1 = 1");
        Iterator<String> it = condition.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            sql.append(" AND " + key + " = '" + condition.get(key) + "'");
        }

        jdbcTemplate.execute(
            sql.toString()
        );
    }

    public List<Map<String, Object>> get(String table, Map<String,String> condition){

        if(table.isEmpty() || condition.isEmpty()) return null;

        StringBuffer sql = new StringBuffer("select * from " + table.trim() + " where 1 = 1");

        Iterator<String> it = condition.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            sql.append(" AND " + key + " = '" + condition.get(key) + "'");
        }

        List<Map<String, Object>> result=jdbcTemplate.queryForList(sql.toString());

        return result;
    }
}
