package com.imall.iportal.core.shop.service.impl;



import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.StaffHealthDocInf;
import com.imall.iportal.core.shop.repository.StaffHealthDocInfRepository;
import com.imall.iportal.core.shop.service.StaffHealthDocInfService;
import com.imall.iportal.core.shop.vo.StaffHealthDocInfDetailVo;
import com.imall.iportal.core.shop.vo.StaffHealthDocInfSaveVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class StaffHealthDocInfServiceImpl extends AbstractBaseService<StaffHealthDocInf, Long> implements StaffHealthDocInfService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private StaffHealthDocInfRepository getStaffHealthDocInfRepository() {
		return (StaffHealthDocInfRepository) baseRepository;
	}


	@Override
	@Transactional
	public void saveStaffHealthDocInf(List<StaffHealthDocInfSaveVo> staffHealthDocInfSaveVos) throws ParseException {
		for (StaffHealthDocInfSaveVo staffHealthDocInfSaveVo:staffHealthDocInfSaveVos) {
			StaffHealthDocInf staffHealthDocInf = CommonUtil.copyProperties(staffHealthDocInfSaveVo, new StaffHealthDocInf());
			staffHealthDocInf.setCheckDateString(staffHealthDocInfSaveVo.getCheckDateString());
			save(staffHealthDocInf);
		}
	}

	@Override
	public List<StaffHealthDocInfDetailVo> findByStaffHealthDocId(Long id) {
		List<StaffHealthDocInfDetailVo> staffHealthDocInfDetailVos = new ArrayList<>();
		for (StaffHealthDocInf staffHealthDocInf :getStaffHealthDocInfRepository().findByStaffHealthDocId(id)) {
			StaffHealthDocInfDetailVo staffHealthDocInfDetailVo = CommonUtil.copyProperties(staffHealthDocInf, new StaffHealthDocInfDetailVo());
			staffHealthDocInfDetailVo.setCheckDateString(staffHealthDocInf.getCheckDateString());
			staffHealthDocInfDetailVos.add(staffHealthDocInfDetailVo);
		}
		return staffHealthDocInfDetailVos;
	}
}