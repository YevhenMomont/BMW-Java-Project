package com.knubisoft.persistence;

import com.knubisoft.model.Address;

public interface AddressRepository {

    int insert(Address address);

    void truncate();

}
