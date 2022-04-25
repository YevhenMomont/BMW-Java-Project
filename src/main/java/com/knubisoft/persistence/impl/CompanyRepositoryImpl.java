package com.knubisoft.persistence.impl;

import com.knubisoft.model.Company;
import com.knubisoft.persistence.CompanyRepository;
import com.knubisoft.persistence.GenericRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyRepositoryImpl extends GenericRepository<Company> implements CompanyRepository {

    @Override
    public int insert(Company company) {
        String sql = "INSERT INTO company (name, catch_phrase, bs)"
                + "VALUES (:name, :catch_phrase, :bs)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", company.getName());
        mapSqlParameterSource.addValue("catch_phrase", company.getCatchPhrase());
        mapSqlParameterSource.addValue("bs", company.getBs());

        return insert(sql, mapSqlParameterSource);
    }

    @Override
    public void truncate() {
        truncate("company");
    }

    @Override
    public RowMapper<Company> rowMapper() {
        return (rs, rowNum) -> Company.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .catchPhrase(rs.getString("catch_phrase"))
                .bs(rs.getString("bs"))
                .build();
    }
}
