package com.imall.iportal.core.index.service.impl;



import com.imall.commons.base.util.BCryptUtil;
import com.imall.commons.base.util.JsonBinder;
import com.imall.commons.dicts.IndexManageStateCodeEnum;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.index.commons.HttpRequest;
import com.imall.iportal.core.index.commons.IndexURL;
import com.imall.iportal.core.index.entity.EsIndexLog;
import com.imall.iportal.core.index.service.EsIndexLogService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.imall.iportal.core.index.entity.EsIndexManage;
import com.imall.iportal.core.index.repository.EsIndexManageRepository;
import com.imall.iportal.core.index.service.EsIndexManageService;
import com.imall.commons.base.service.impl.AbstractBaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * T_PT_ES_INDEX_MANAGE【索引管理】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class EsIndexManageServiceImpl extends AbstractBaseService<EsIndexManage, Long> implements EsIndexManageService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final static String PROTOCOL = "http";

	@Autowired
	private EsIndexLogService esIndexLogService;

	@SuppressWarnings("unused")
    private EsIndexManageRepository getEsIndexManageRepository() {
		return (EsIndexManageRepository) baseRepository;
	}

	@Override
	public EsIndexManage getByIndexTypeCode(IndexTypeCodeEnum typeCodeEnum) {
		return getEsIndexManageRepository().getByIndexTypeCode(typeCodeEnum.toCode());
	}

	@Override
	public Page<EsIndexManage> query(Pageable pageable) {
		return getEsIndexManageRepository().query(pageable);
	}

	@Override
	@Transactional
	public void updateManageRemainAndState(String indexTypeCode, int remainNum) {
		IndexTypeCodeEnum typeCodeEnum = IndexTypeCodeEnum.fromCode(indexTypeCode);
		EsIndexManage indexManage = getByIndexTypeCode(typeCodeEnum);
		if (indexManage != null) {
			indexManage.setRemainNum((long)remainNum);
			indexManage.setState(remainNum > 0 ? IndexManageStateCodeEnum.PROCESSING.toCode() : IndexManageStateCodeEnum.FREEING.toCode());
		}
	}

	@Override
	@Transactional
	public void rebuildIndex(Long indexManageId) {
		EsIndexManage manage = findOne(indexManageId);
		Integer affectCount = doAppRebuildIndexRequest(indexManageId, manage.getIndexTypeCode(), manage.getRemoteUrl(), manage.getAppName(), manage.getAppKey(), manage.getAppSecret());
		if (affectCount != 0) {
			manage.setState(IndexManageStateCodeEnum.PROCESSING.toCode());
			manage.setRemainNum(Long.valueOf(affectCount));
			update(manage);
		}
	}

	private Integer doAppRebuildIndexRequest(Long indexManageId, String indexTypeCode, String sendUrl, String appName, String appKey, String appSecret) {
		String failCause = "";
		Integer affectCount = 0;

		IndexURL url = new IndexURL(sendUrl);
		String excuteAddress = url.getAddress();
		String serviceName = url.getServiceName();
		String methodName = url.getMethodName();

		HttpRequest request = new HttpRequest();
		request.setRequestId(UUID.randomUUID().toString());
		request.setAppName(appName);
		request.setProtocol(PROTOCOL);
		request.setAppKey(appKey);
		request.setSign(BCryptUtil.hashpw(appKey, appSecret));
		request.setInterfaceName(serviceName);

		request.setServiceVersion("");
		request.setMethodName(methodName);
		request.setParameters(new Object[]{indexTypeCode});
		request.setParameterTypes(new Class[]{String.class});

		CloseableHttpClient client = HttpClientBuilder.create().build();
		//httpserver服务Ip:服务端口/接口名/方法名
		HttpPost post = new HttpPost("http://" + excuteAddress + "/");
		//配置请求的超时设置
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(120)
				.setConnectTimeout(120)
				.setSocketTimeout(5000).build();

		post.setConfig(requestConfig);
		HttpResponse res = null;
		try {
			System.out.println("JsonBinder.toJson(request)" + JsonBinder.toJson(request));
			StringEntity s = new StringEntity(JsonBinder.toJson(request));
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			post.setEntity(s);
			res = client.execute(post);
			if (res.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				failCause = EntityUtils.toString(entity);
			} else {
				affectCount = Integer.valueOf(EntityUtils.toString(res.getEntity()));
			}
		} catch (Exception e) {
			failCause = e.getMessage();
		} finally {
			if (res != null && !failCause.isEmpty()) {
				EsIndexLog esIndexLog = new EsIndexLog();
				esIndexLog.setIndexManageId(indexManageId);
				esIndexLog.setLogContString(failCause.isEmpty() ? res.getStatusLine().getStatusCode() + "" : failCause);
				esIndexLogService.save(esIndexLog);
			}
			try {
				client.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return affectCount;
	}
}