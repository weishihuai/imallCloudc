package com.imall.iportal.core.example.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.example.commons.ResGlobalExt;
import com.imall.iportal.core.example.entity.SysDoc;
import com.imall.iportal.core.example.repository.SysDocRepository;
import com.imall.iportal.core.example.service.SysDocService;
import com.imall.iportal.core.example.valid.SysDocSaveValid;
import com.imall.iportal.core.example.valid.SysDocUpdateValid;
import com.imall.iportal.core.example.vo.DocParamVo;
import com.imall.iportal.core.example.vo.SysDocVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysDocServiceImpl extends AbstractBaseService<SysDoc, Long> implements SysDocService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysDocRepository getSysDocRepository() {
		return (SysDocRepository) baseRepository;
	}

	@Transactional
	@Override
	public SysDoc save(SysDocSaveValid sysDocSaveValid) {
		return save(CommonUtil.copyProperties(sysDocSaveValid, new SysDoc()));
	}

	@Transactional
	@Override
	public SysDoc update(SysDocUpdateValid sysDocUpdateValid) {
		return save(CommonUtil.copyProperties(sysDocUpdateValid, new SysDoc()));
	}

	@Override
	public Page<SysDocVo> query(Pageable pageable, DocParamVo paramsVo) {
		//throw new BusinessException(ResGlobal.RESOURCE_RELATE_DELETE_ERROR,new String[]{"test","test"});
		return getSysDocRepository().query(pageable, paramsVo);
	}

	@Autowired
	private JpaTransactionManager jpaTransactionManager;

	//手动控制事务这么写
	@Override
	public SysDoc saveDoc(SysDoc sysDoc) throws BusinessException{
		DefaultTransactionDefinition td = new DefaultTransactionDefinition();
		td.setName("Process saveOrder");
		//使用底层数据库默认隔离级别。
		td.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		//支持现有的事务，如果没有则新建一个事务。
		td.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus txs = jpaTransactionManager.getTransaction(td);
		SysDoc dbSysDoc = null;
		try {
			//1.保存订单信息
			//2.锁库存
			//3.锁余额
			//4.锁券
			//5.保存任务队列
			//提交事务
			dbSysDoc = this.save(sysDoc); //不能使用 getSysDocRepository().save() 因为update的问题，需要调用超类AbstractBaseService的save
			jpaTransactionManager.commit(txs);
		} catch(DataIntegrityViolationException dive) {
			if (!txs.isCompleted()) {
				//回滚事务
				jpaTransactionManager.rollback(txs);
			}
			try {
				//6.解锁库存
			}
			catch (Exception e){
				//解锁失败，写日志
			}
			try {
				//7.解锁余额
			}
			catch (Exception e){
				//解锁失败，写日志
			}
			try {
				//8.解锁券
			}
			catch (Exception e){
				//解锁失败，写日志
			}
			//以上解锁如果有解锁失败的只以人工补尝
			throw new BusinessException("xxxxxxx", dive);
		}
		return dbSysDoc;
	}

	@Override
	public void deleteByDocIds(List<Long> docIdList) {
		for (Long id :docIdList){
			//xxService.findOne(id);
			////throw new BusinessException(ResGlobal.RESOURCE_RELATE_DELETE_ERROR,new String[]{id});
		}
		throw new BusinessException(ResGlobalExt.DOC_TEST_ERROR,new String[]{"test"});

		//delete(docIdList);
	}

	@Override
	@Transactional
	public Integer queryDocToQueue() {
		return getSysDocRepository().queryDocToQueue();
	}
}