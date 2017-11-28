package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysAuth;
import com.imall.iportal.core.main.entity.SysMenu;
import com.imall.iportal.core.main.entity.SysResource;
import com.imall.iportal.core.main.entity.SysRole;
import com.imall.iportal.core.main.repository.SysAuthRepository;
import com.imall.iportal.core.main.service.SysAuthService;
import com.imall.iportal.core.main.vo.AuthParamsVo;
import com.imall.iportal.core.main.vo.SysRoleVo;
import com.imall.iportal.core.main.vo.TagAuthVo;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * T_PT_SYS_AUTH【授权】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysAuthServiceImpl extends AbstractBaseService<SysAuth, Long> implements SysAuthService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private SysAuthRepository getSysAuthRepository() {
        return (SysAuthRepository) baseRepository;
    }

    @Override
    public List<Long> findRoleIdsByJobId(Long jobId) {
        return getSysAuthRepository().findRoleIdsByJobId(jobId);
    }

    @Override
    public Page<SysRoleVo> findRolesByJobId(Pageable pageable, AuthParamsVo paramsVo) {
        return getSysAuthRepository().query(pageable, paramsVo.getJobId(), paramsVo.getRoleName(), paramsVo.getRoleDescription());
    }


    @Override
    @Transactional
    public void saveJobRole(Long jobId, Long[] roleIds) {
        for (int i = 0; i < roleIds.length; i++) {
            List<SysAuth> sysAuths = getSysAuthRepository().findByJobIdAndRoleId(jobId, roleIds[i]);
            if (CollectionUtils.isEmpty(sysAuths)) {
                SysAuth sysAuth1 = new SysAuth();
                sysAuth1.setJobId(jobId);
                sysAuth1.setRoleId(roleIds[i]);
                save(sysAuth1);
            }

        }

    }

    @Override
    @Transactional
    public void deleteJobRole(Long jobId, Long[] roleIds) {
        for (int i = 0; i < roleIds.length; i++) {
            List<SysAuth> sysAuths = getSysAuthRepository().findByJobIdAndRoleId(jobId, roleIds[i]);
            if (!CollectionUtils.isEmpty(sysAuths)) {
                for (SysAuth newSysAuth : sysAuths) {
                    getSysAuthRepository().delete(newSysAuth);
                }
            }
        }
    }

    @Override
    public TagAuthVo getTagAuthVoByJobId(Long jobId) {
        //角色
        Set<String> rolesSet = new HashSet<String>();
        //权限
        Set<String> permissionsSet = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        //授权
        List<SysAuth> authList = getSysAuthRepository().findByJobId(jobId);
        for (SysAuth auth : authList) {
            SysRole role = ServiceManager.sysRoleService.findOne(auth.getRoleId());
            if (BoolCodeEnum.YES == BoolCodeEnum.fromCode(role.getIsAvailable())) {
                //收集角色
                rolesSet.add(role.getRoleCode()); //roleCode
                //
                List<Long> menuIdList = ServiceManager.sysRoleMenuService.findMenuIdByRoleId(role.getId());
                Set<SysMenu> menuSet = new HashSet<SysMenu>();
                for (Long menuId : menuIdList) {
                    SysMenu sysMenu = ServiceManager.sysMenuService.findOne(menuId);
                    if (sysMenu == null) {
                        continue;
                    }
                    menuSet.add(sysMenu);

                    //添加父菜单
                    int len = sysMenu.getNodeCode().length() / 8;
                    for (int a = 1; a <= len; a++) {
                        String code = sysMenu.getNodeCode().substring(0, a * 8);
                        menuSet.add(ServiceManager.sysMenuService.getByNodeCode(code));
                    }
                }
                List<Long> permissionResourceIdList = ServiceManager.sysRolePermissionService.findResourceIdByRoleId(role.getId());
                for (SysMenu sysMenu : menuSet) {
                    if (BoolCodeEnum.YES == BoolCodeEnum.fromCode(sysMenu.getIsAvailable())) {
                        if (sysMenu.getResourceId() != null) {
                            //父(当前节点)
                            SysResource currResource = ServiceManager.sysResourceService.findOne(sysMenu.getResourceId());
                            if (currResource != null) {
                                if (BoolCodeEnum.YES == BoolCodeEnum.fromCode(currResource.getIsAvailable())) {
                                    //收集资源
                                    permissionsSet.add(currResource.getPermissionCode());
                                }
                                //子
                                //List<SysResource> resourceList = ServiceManager.sysResourceService.findByKey(SysResource.PARENT_ID, currResource.getId());
                                List<SysResource> resourceList = ServiceManager.sysResourceService.findByNodeCodeLike(currResource.getNodeCode());
                                for (SysResource resource : resourceList) {
                                    if (BoolCodeEnum.YES == BoolCodeEnum.fromCode(resource.getIsAvailable()) && permissionResourceIdList.contains(resource.getId())) {
                                        //收集资源
                                        permissionsSet.add(resource.getPermissionCode());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        TagAuthVo tagAuthVo = new TagAuthVo();
        tagAuthVo.setJobId(jobId);
        tagAuthVo.setRolesSet(new ArrayList<String>(rolesSet));
        tagAuthVo.setPermissionsSet(new ArrayList<String>(permissionsSet));
        return tagAuthVo;
    }

}