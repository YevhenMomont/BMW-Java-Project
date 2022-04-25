package com.knubisoft.persistence.impl;

import com.knubisoft.model.Address;
import com.knubisoft.model.Geo;
import com.knubisoft.persistence.AddressRepository;
import com.knubisoft.persistence.GenericRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepositoryImpl extends GenericRepository<Address> implements AddressRepository {

    @Override
    public int insert(Address address) {
        String sql = "INSERT INTO address (street, suite, city, zipcode, lat, lng)"
                + " VALUES (:street, :suite, :city, :zipcode, :lat, :lng)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("street", address.getStreet());
        mapSqlParameterSource.addValue("suite", address.getSuite());
        mapSqlParameterSource.addValue("city", address.getCity());
        mapSqlParameterSource.addValue("zipcode", address.getZipCode());
        mapSqlParameterSource.addValue("lat", address.getGeo().getLat());
        mapSqlParameterSource.addValue("lng", address.getGeo().getLng());

        return insert(sql, mapSqlParameterSource);
    }

    @Override
    public void truncate() {
        truncate("address");
    }

    @Override
    public RowMapper<Address> rowMapper() {
        return (rs, rowNum) -> Address.builder()
                .id(rs.getInt("id"))
                .city(rs.getString("city"))
                .street(rs.getString("street"))
                .suite(rs.getString("suite"))
                .zipCode(rs.getString("zipcode"))
                .geo(Geo.builder()
                        .lat(rs.getDouble("lat"))
                        .lng(rs.getDouble("lng"))
                        .build())
                .build();
    }
}
