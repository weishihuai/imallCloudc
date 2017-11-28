package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysRolePermission;
import com.imall.iportal.core.main.repository.SysRolePermissionRepository;
import com.imall.iportal.core.main.service.SysRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * T_PT_SYS_ROLE_PERMISSION【角色资源权限】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysRolePermissionServiceImpl extends AbstractBaseService<SysRolePermission, Long> implements SysRolePermissionService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysRolePermissionRepository getSysRolePermissionRepository() {
		return (SysRolePermissionRepository) baseRepository;
	}
	@Transactional
	@Override
	public void saveOrDeleteRolePermission(Long roleId, Long id, Boolean isChecked) {
		List<Long> resourceIdList = ServiceManager.sysResourceService.findLastChild(id);//找不到子级则说明是最后一级
		if(resourceIdList.isEmpty()){
			resourceIdList.add(id);
		}
		//新增
		if(isChecked){
			for(Long menuId : resourceIdList){
				saveRolePermission(roleId, menuId);
			}
		}
		//删除
		if(!isChecked){
			for(Long resourceId : resourceIdList){
				List<SysRolePermission> sysRolePermissionList = getSysRolePermissionRepository().findByRoleIdAndResourceId(roleId, resourceId);
				for(SysRolePermission sysRolePermission : sysRolePermissionList){
					delete(sysRolePermission);
				}

			}
		}
	}

	@Override
	public List<Long> findResourceIdByRoleId(Long roleId) {
		return getSysRolePermissionRepository().findResourceIdByRoleId(roleId);
	}

	private void saveRolePermission(Long roleId , Long resourceId){
		SysRolePermission sysRolePermission = new SysRolePermission();
		sysRolePermission.setRoleId(roleId);
		sysRolePermission.setResourceId(resourceId);
		save(sysRolePermission);
	}
}