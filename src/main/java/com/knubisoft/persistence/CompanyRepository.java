package com.knubisoft.persistence;

import com.knubisoft.model.Company;

public interface CompanyRepository {

    int insert(Company company);

    void truncate();

}
