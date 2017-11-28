package com.imall.iportal.shiro.service;

import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.UrlAuthVo;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by yang on 2015-10-30.
 * 动态拦截Controller的xxx.json的请求，需要拦截的.json的url需要配置到sysResource或sysResourceUrl
 */
/*@Service*/
public class ShiroFilerChainManager {

    /*@Autowired*/
    private DefaultFilterChainManager filterChainManager;


    public void setFilterChainManager(DefaultFilterChainManager filterChainManager) {
        this.filterChainManager = filterChainManager;
    }

    private Map<String, NamedFilterList> defaultFilterChains;

    @PostConstruct
    public void init() {
        defaultFilterChains = new LinkedHashMap<String, NamedFilterList>(filterChainManager.getFilterChains());
        initFilterChains(ServiceManager.sysResourceService.findAllUrlAuth());
    }

    public void initFilterChains(List<UrlAuthVo> urlAuthVoList) {
        //1、首先删除以前老的filter chain
        filterChainManager.getFilterChains().clear();
        //2、循环URL Filter 注册filter chain
        for (UrlAuthVo urlAuthVo : urlAuthVoList) {
            //注册perms filter
            if (!StringUtils.isEmpty(urlAuthVo.getPermission())) {
                filterChainManager.addToChain(urlAuthVo.getUrl(), "perms", urlAuthVo.getPermission());
            }
        }
        //3. 数据库配置的url .json的权限优先，所以现在才注册默认的
        if(defaultFilterChains != null) {
            filterChainManager.getFilterChains().putAll(defaultFilterChains);
        }
    }

}
