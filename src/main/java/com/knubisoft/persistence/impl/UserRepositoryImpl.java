package com.knubisoft.persistence.impl;

import com.knubisoft.model.Address;
import com.knubisoft.model.Company;
import com.knubisoft.model.Geo;
import com.knubisoft.model.User;
import com.knubisoft.persistence.GenericRepository;
import com.knubisoft.persistence.UserRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl extends GenericRepository<User> implements UserRepository {

    @Override
    public void insert(User user) {
        String sql =
                "INSERT INTO \"user\" (id, name, username, email, phone, website, address_id, company_id) "
                        + " VALUES (:id, :name, :username, :email, :phone, :website, :address_id, :company_id)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", user.getId());
        mapSqlParameterSource.addValue("name", user.getName());
        mapSqlParameterSource.addValue("username", user.getName());
        mapSqlParameterSource.addValue("email", user.getEmail());
        mapSqlParameterSource.addValue("phone", user.getPhone());
        mapSqlParameterSource.addValue("website", user.getWebsite());
        mapSqlParameterSource.addValue("address_id", user.getAddress().getId());
        mapSqlParameterSource.addValue("company_id", user.getCompany().getId());

        insert(sql, mapSqlParameterSource);
    }

    @Override
    public List<User> findAll() {
        String sql =
                "SELECT u.id AS u_id, u.name AS u_name, u.username AS u_username, u.email AS u_email,"
                        + "u.phone AS u_phone, u.website AS u_website, ad.id AS ad_id, ad.street AS ad_street,"
                        + "ad.suite AS ad_suite, ad.city AS ad_city, ad.zipcode AS ad_zipcode, ad.lat AS ad_lat,"
                        + "ad.lng AS ad_lng, co.id AS co_id, co.name AS co_name, co.catch_phrase AS co_catch_phrase,"
                        + "co.bs AS co_bs "
                        + "FROM \"user\" as u JOIN address ad ON ad.id = u.address_id "
                        + "JOIN company co ON co.id = u.company_id";

        return operations.query(sql, new MapSqlParameterSource(), rowMapper());
    }

    @Override
    public void truncate() {
        truncate("\"user\"");
    }

    @Override
    public RowMapper<User> rowMapper() {
        return (rs, rowNum) -> User.builder()
                .id(rs.getInt("u_id"))
                .name(rs.getString("u_name"))
                .userName(rs.getString("u_username"))
                .email(rs.getString("u_email"))
                .phone(rs.getString("u_phone"))
                .website(rs.getString("u_website"))
                .address(getRowMapperForAddress().mapRow(rs, rowNum))
                .company(getRowMapperForCompany().mapRow(rs, rowNum))
                .build();
    }

    public RowMapper<Address> getRowMapperForAddress() {
        return (rs, rowNum) -> Address.builder()
                .id(rs.getInt("ad_id"))
                .street(rs.getString("ad_street"))
                .suite(rs.getString("ad_suite"))
                .zipCode(rs.getString("ad_zipcode"))
                .geo(Geo.builder().lat(rs.getDouble("ad_lat"))
                        .lng(rs.getDouble("ad_lng")).build())
                .build();
    }

    public RowMapper<Company> getRowMapperForCompany() {
        return (rs, rowNum) -> Company.builder()
                .id(rs.getInt("co_id"))
                .name(rs.getString("co_name"))
                .catchPhrase(rs.getString("co_catch_phrase"))
                .bs(rs.getString("co_bs"))
                .build();
    }
}
