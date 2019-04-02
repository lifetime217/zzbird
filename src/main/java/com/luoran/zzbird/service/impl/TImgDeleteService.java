package com.luoran.zzbird.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luoran.zzbird.core.ext.AbstractBaseService;
import com.luoran.zzbird.core.ext.BaseDao;
import com.luoran.zzbird.dao.ITImgDeleteDao;
import com.luoran.zzbird.entity.biz.TImgDelete;
import com.luoran.zzbird.service.ITImgDeleteService;
import com.luoran.zzbird.utils.UUID;

/**
 * @author wsl
 *
 */
@Service
public class TImgDeleteService extends AbstractBaseService<TImgDelete> implements ITImgDeleteService {
	@Autowired
	private ITImgDeleteDao imgDao;

	@Override
	public BaseDao<TImgDelete> getDao() {
		return imgDao;
	}

	@Override
	public String add(TImgDelete t) {
		return super.add(t);
	}

	@Override
	public void addImg(String imgs) {
		if (!"".equals(imgs)) {
			String[] imgArray = imgs.split(",");
			TImgDelete tImgDelete = new TImgDelete();
			for (String img : imgArray) {
				tImgDelete.setId(UUID.get());	
				tImgDelete.setImgName(img);
				tImgDelete.setAddTime(new Date());
				add(tImgDelete);
			}
		}
	}

}