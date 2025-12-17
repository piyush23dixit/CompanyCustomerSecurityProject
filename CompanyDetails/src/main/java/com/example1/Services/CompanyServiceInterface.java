package com.example1.Services;

import com.example1.Collection.CompCustDTO;
import com.example1.Collection.CompanyDTO;

public interface CompanyServiceInterface {

	CompanyDTO addCompany(CompanyDTO comp);

	CompanyDTO getCompany(String compId);

    CompCustDTO getAllCustByCompId(String compId);

}
