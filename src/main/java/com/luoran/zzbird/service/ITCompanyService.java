package com.luoran.zzbird.service;

import java.util.List;

import com.luoran.zzbird.core.ext.IBaseService;
import com.luoran.zzbird.entity.biz.TCompany;

/**
 * @author lifetime
 *
 */
public interface ITCompanyService extends IBaseService<TCompany> {

	List<TCompany> queryPointUser();
}
