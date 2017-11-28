package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysMenu;
import com.imall.iportal.core.main.entity.SysResource;
import com.imall.iportal.core.main.entity.SysRoleMenu;
import com.imall.iportal.core.main.repository.SysRoleMenuRepository;
import com.imall.iportal.core.main.service.SysRoleMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * T_PT_SYS_ROLE_MENU【角色菜单】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysRoleMenuServiceImpl extends AbstractBaseService<SysRoleMenu, Long> implements SysRoleMenuService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
	private SysRoleMenuRepository getSysRoleMenuRepository() {
		return (SysRoleMenuRepository) baseRepository;
	}

	@Override
	@Transactional
	public void saveOrDeleteRoleMenu(Long roleId , Long id, Boolean isChecked) {

		List<Long> menuIdList = ServiceManager.sysMenuService.findLastChild(id);//找不到子级则说明是最后一级
		if(menuIdList.isEmpty()){
			menuIdList.add(id);
		}
		//新增
		if(isChecked){
			for(Long menuId : menuIdList){
				saveRoleMenu(roleId, menuId);
			}
		}
		//删除
		if(!isChecked){
			for(Long menuId : menuIdList){
				List<SysRoleMenu> sysRoleMenuList = getSysRoleMenuRepository().findByRoleIdAndMenuId(roleId, menuId);
				for(SysRoleMenu sysRoleMenu : sysRoleMenuList){
					delete(sysRoleMenu);
				}

			}
		}
	}

	@Override
	@Transactional
	public void saveAllotRoleMenu(Long roleId , Long id, Boolean isChecked) {

		List<SysMenu> menuList = ServiceManager.sysMenuService.findLastChildObject(id);//找不到子级则说明是最后一级
		if(menuList.isEmpty()){
			menuList.add(ServiceManager.sysMenuService.findOne(id));
		}
		Subject subject = SecurityUtils.getSubject();

		//新增
		if(isChecked){
			for(SysMenu menu : menuList){
				SysResource resource = ServiceManager.sysResourceService.findOne(menu.getResourceId());
				//判断操作者是否拥有该节点权限
				if(subject.isPermitted(resource.getPermissionCode())){
					saveRoleMenu(roleId, menu.getId());
				}
			}
		}
		//删除
		if(!isChecked){
			for(SysMenu menu : menuList){
				SysResource resource = ServiceManager.sysResourceService.findOne(menu.getResourceId());
				//判断操作者是否拥有该节点权限
				if(subject.isPermitted(resource.getPermissionCode())) {
					List<SysRoleMenu> sysRoleMenuList = getSysRoleMenuRepository().findByRoleIdAndMenuId(roleId, menu.getId());
					for (SysRoleMenu sysRoleMenu : sysRoleMenuList) {
						delete(sysRoleMenu);
					}
				}
			}
		}
	}

	@Override
	public List<Long> findMenuIdByRoleId(Long roleId){
		return getSysRoleMenuRepository().findMenuIdByRoleId(roleId);
	}

	private void saveRoleMenu(Long roleId , Long menuId){
		SysRoleMenu sysRoleMenu = new SysRoleMenu();
		sysRoleMenu.setRoleId(roleId);
		sysRoleMenu.setMenuId(menuId);
		save(sysRoleMenu);
	}


}