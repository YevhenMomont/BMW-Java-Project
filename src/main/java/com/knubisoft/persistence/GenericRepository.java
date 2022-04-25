package com.knubisoft.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public abstract class GenericRepository<T> {

    protected NamedParameterJdbcOperations operations;

    @Autowired
    public void setOperations(NamedParameterJdbcOperations operations) {
        this.operations = operations;
    }

    public int insert(String sql, SqlParameterSource sqlParameterSource) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        operations.update(sql, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKeys().get("id");
    }

    public void truncate(String tableName) {
        String sql = "TRUNCATE TABLE :table";
        operations.update(sql, new MapSqlParameterSource().addValue("table", tableName));
    }

    public abstract RowMapper<T> rowMapper();

}
