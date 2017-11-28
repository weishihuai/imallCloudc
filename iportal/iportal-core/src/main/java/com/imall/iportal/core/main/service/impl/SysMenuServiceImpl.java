package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.global.Global;
import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.Node;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.MenuTypeCodeEnum;
import com.imall.commons.dicts.ResourceTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysApp;
import com.imall.iportal.core.main.entity.SysMenu;
import com.imall.iportal.core.main.entity.SysResource;
import com.imall.iportal.core.main.repository.SysMenuRepository;
import com.imall.iportal.core.main.service.SysMenuService;
import com.imall.iportal.core.main.valid.SysMenuSaveValid;
import com.imall.iportal.core.main.valid.SysMenuUpdateValid;
import com.imall.iportal.core.main.vo.PortalMenuVo;
import com.imall.iportal.core.main.vo.RouterConfigVo;
import com.imall.iportal.core.main.vo.SysMenuVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * T_PT_SYS_MENU【菜单】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysMenuServiceImpl extends AbstractBaseService<SysMenu, Long> implements SysMenuService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysMenuRepository getSysMenuRepository() {
		return (SysMenuRepository) baseRepository;
	}

	@Override
	public Page<SysMenuVo> query(Pageable pageable, Long parentId, String menuName) {
		return getSysMenuRepository().query(pageable,parentId,menuName);
	}

	@Transactional
	@Override
	public SysMenu save(SysMenuSaveValid sysMenuSaveValid) {
		sysMenuSaveValid.setNodeCode(ServiceManager.treeCodeGenerateBuilder.generate8Code(SysMenu.class, sysMenuSaveValid.getParentId()));
		return saveMenu(CommonUtil.copyProperties(sysMenuSaveValid, new SysMenu()));
	}

	@Transactional
	@Override
	public SysMenu update(SysMenuUpdateValid sysMenuUpdateValid) {
		return saveMenu(CommonUtil.copyProperties(sysMenuUpdateValid, new SysMenu()));
	}

	@Override
	public List<Node> buildMenuTree(Long parentId, Boolean isIncludeRoot) {
		List<SysMenu> sysMenuList = getSysMenuRepository().findByParentId(parentId);
		List<Node> nodeList = new ArrayList<>();

		for(SysMenu sysMenu : sysMenuList){
			nodeList.add(buildNode(sysMenu));
		}
		if(isIncludeRoot&& parentId.equals(Global.TREE_DEFAULT_ID)){
			List<Node> nodes = new ArrayList<>();
			SysMenu sysMenu = getSysMenuRepository().findOne(Global.TREE_DEFAULT_ID);
			Node node = buildNode(sysMenu);
			node.setOpen(true);
			node.setChildren(nodeList);
			nodes.add(node);
			return nodes;
		}
		return nodeList;
	}

	@Override
	public SysMenuVo findById(Long id) {
		SysMenuVo sysMenuVo = new SysMenuVo();
		sysMenuVo.setResourceName("");
		SysMenu sysMenu = baseRepository.findOne(id);
		CommonUtil.copyProperties(sysMenu, sysMenuVo);
		if(sysMenu.getResourceId()!=null) {
			SysResource sysResource = ServiceManager.sysResourceService.findOne(sysMenu.getResourceId());
			if (sysResource != null) {
				sysMenuVo.setResourceName(sysResource.getResourceName());
			}
		}
		return sysMenuVo;
	}

	@Override
	@Transactional
	public void deleteMenu(List<Long> ids) {
		baseRepository.delete(ids);
		for (Long id:ids){
			SysMenu sysMenu=findOne(id);
			List<String> sysMenus = getSysMenuRepository().findMenuNameByParentId(id);
			if(!sysMenus.isEmpty()){
				String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.MENU_DELETE_ERROR);
				throw new BusinessException(ResGlobal.MENU_DELETE_ERROR,String.format(message,new Object[]{sysMenu.getMenuName(), StringUtils.join(sysMenus.toArray(),",")}));
			}

		}
	}

	@Override
	public List<Long> findLastChild(Long id){
		List<Long> menuIdList = new ArrayList<>();
		List<SysMenu> menuList = recursionLastChild(getSysMenuRepository().findByParentId(id));
		for(SysMenu sysMenu  : menuList){
			menuIdList.add(sysMenu.getId());
		}
		return menuIdList;
	}

	@Override
	public List<SysMenu> findLastChildObject(Long id){
		return recursionLastChild(getSysMenuRepository().findByParentId(id));
	}

	@Override
	public List<Node> buildMenuTreeFilterByRole(Long roleId, Long parentId,Long jobId){
		List<SysMenu> sysMenuList = getSysMenuRepository().findByParentId(parentId);
		List<Node> nodeList = new ArrayList<>();
		Set<Long> authMenus = getAuthMenus(jobId);
		for(SysMenu sysMenu : sysMenuList){
			if(authMenus.contains(sysMenu.getId())) {
				List<Long> menuIdList = ServiceManager.sysRoleMenuService.findMenuIdByRoleId(roleId);
				if (menuIdList.contains(sysMenu.getId())) {
					nodeList.add(buildNode(sysMenu));
					continue;
				}

				List<Long> lastChildIdList = findLastChild(sysMenu.getId());
				lastChildIdList.retainAll(menuIdList);
				//有子节点被分配，则当前父节点需要显示
				if (lastChildIdList.size() > 0) {
					nodeList.add(buildNode(sysMenu));
				}
			}
		}

		if(!nodeList.isEmpty()&& parentId.equals(Global.TREE_DEFAULT_ID)){
			List<Node> nodes = new ArrayList<>();
			SysMenu sysMenu = getSysMenuRepository().findOne(Global.TREE_DEFAULT_ID);
			Node node = buildNode(sysMenu);
			node.setOpen(true);
			node.setChildren(nodeList);
			nodes.add(node);
			return nodes;
		}

		return nodeList;
	}

	@Override
	public List<Node> buildMenuTreeCheckByRole(Long roleId, Long parentId, Boolean isIncludeRoot,Long jobId){

		//获取角色已分配的菜单
		List<Long> menuIdList = ServiceManager.sysRoleMenuService.findMenuIdByRoleId(roleId);
		//找出子节点
		List<Node> nodeList = new ArrayList<>();
        List<SysMenu> sysMenuList = getSysMenuRepository().findByParentId(parentId);
		Set<Long> authMenus = getAuthMenus(jobId);
		for(SysMenu sysMenu : sysMenuList){
			if(authMenus.contains(sysMenu.getId())) {
				nodeList.add(buildAndCheckedNode(sysMenu, menuIdList));
			}
		}

		//是否包含根节点
		if(isIncludeRoot&& parentId.equals(Global.TREE_DEFAULT_ID)){
			List<Node> nodes = new ArrayList<>();
			SysMenu sysMenu = getSysMenuRepository().findOne(Global.TREE_DEFAULT_ID);
			Node node = buildAndCheckedNode(sysMenu, menuIdList);
			node.setOpen(true);
			node.setChildren(nodeList);
			nodes.add(node);
			return nodes;
		}
		return nodeList;
	}


	private Set<Long> getAuthMenus(Long jobId){
		List<Long> roleIds = ServiceManager.sysAuthService.findRoleIdsByJobId(jobId);
		List<Long> menuList = new ArrayList<Long>();
		for (Long rId :roleIds) {
			List<Long> menuIds=  ServiceManager.sysRoleMenuService.findMenuIdByRoleId(rId);
			if(!menuIds.isEmpty()){
				menuList.addAll(menuIds);
			}
		}
		Set<Long> menuSets = new HashSet<Long>(menuList);
		menuList = new ArrayList<Long>();
		for(Long menuId:menuSets){
			recursionParent(menuId,menuList);
		}
		menuSets = new HashSet<Long>(menuList);
		return menuSets;

	}
	/**
	 * 递归获取父节点
	 * @param menuId
	 * @return
	 */
	private void recursionParent(Long menuId,List<Long> menuList){
		SysMenu sysMenu = getSysMenuRepository().findOne(menuId);
		if(sysMenu !=null){
			menuList.add(sysMenu.getId());
			recursionParent(sysMenu.getParentId(),menuList);
		}
	}

	@Override
	public List<RouterConfigVo> getPortalRouterConfig() {
		List<String> routerKeyList = new ArrayList<>();
		List<RouterConfigVo> routerConfigVoList = new ArrayList<>();
		List<SysMenu> menuList =  getSysMenuRepository().findMenu(MenuTypeCodeEnum.MENU.toCode(), BoolCodeEnum.YES.toCode());
		//处理菜单资源
		for(SysMenu sysMenu : menuList){
			if(sysMenu.getResourceId()!=null){
				RouterConfigVo routerConfigVo = buildRouterConfig(sysMenu.getNodeCode(), sysMenu.getResourceId());
				if(!routerKeyList.contains(routerConfigVo.getRouterKey())){
					routerConfigVoList.add(routerConfigVo);
					routerKeyList.add(routerConfigVo.getRouterKey());
				}
			}
		}
		//处理菜单对应的资源的子级资源
		for(SysMenu sysMenu : menuList){
			if(sysMenu.getResourceId()!=null){
				SysResource sysResource = ServiceManager.sysResourceService.findOne(sysMenu.getResourceId());
				if(sysResource!=null){
					//取它的子资源
					List<SysResource> subResourceList = ServiceManager.sysResourceService.findByNodeCodeLike(sysResource.getNodeCode());
					for(SysResource subResource : subResourceList){
						if(subResource.getNodeCode().equals(sysResource.getNodeCode())){
							continue; //自己不加入
						}
						if(ResourceTypeCodeEnum.LNK == ResourceTypeCodeEnum.fromCode(subResource.getResourceType())){
							RouterConfigVo subRouterConfigVo = buildRouterConfig(sysMenu.getNodeCode(), subResource.getId());
							if(!routerKeyList.contains(subRouterConfigVo.getRouterKey())){
								routerConfigVoList.add(subRouterConfigVo);
								routerKeyList.add(subRouterConfigVo.getRouterKey());
							}
						}
					}
				}
			}
		}

		return routerConfigVoList;
	}

	private RouterConfigVo buildRouterConfig(String menuNodeCode, Long resourceId){
		RouterConfigVo routerConfigVo = new RouterConfigVo();
		routerConfigVo.setMenuNodeCode(menuNodeCode);
		SysResource sysResource = ServiceManager.sysResourceService.findOne(resourceId);
		if(sysResource!=null){
			SysApp sysApp = ServiceManager.sysAppService.findOne(sysResource.getAppId());
			String host = sysApp.getHostname()+ "/" +sysApp.getWebContext() + "/" ;
			routerConfigVo.setAppId(sysApp.getId());
			routerConfigVo.setResourceNodeCode(sysResource.getNodeCode());
			routerConfigVo.setResourceId(sysResource.getId());
			routerConfigVo.setPermissionCode(sysResource.getPermissionCode());
			routerConfigVo.setRouterKey(sysResource.getRouterKey());
			routerConfigVo.setRouterTemplateUrl(host + (sysResource.getRouterTemplateUrl()==null ? "" : sysResource.getRouterTemplateUrl()));
			if(StringUtils.isNotBlank(sysResource.getRouterTemplateJs())) { //todo:单应用模式不需要 host
				routerConfigVo.setRouterTemplateJs(host + sysResource.getRouterTemplateJs());
			}
		}
		return routerConfigVo;
	}

	@Override
	public SysMenu findByResourceId(Long resourceId) {
		return getSysMenuRepository().findByResourceId(resourceId);
	}

	@Override
	public SysMenu getByNodeCode(String nodeCode) {
		return getSysMenuRepository().findByNodeCode(nodeCode);
	}

	@Transactional
	@Override
	public SysMenu saveMenu(SysMenu sysMenu) {
		Boolean isAdd=sysMenu.getId()==null;
		save(sysMenu);
		if(isAdd){
			ServiceManager.sysRoleMenuService.saveOrDeleteRoleMenu(Global.DEFAULT_ADMIN_ROLE_ID,sysMenu.getId(),true);
		}
		return sysMenu;
	}

	@Override
	public List<PortalMenuVo> getPortalMenus() {
		return getMenu(MenuTypeCodeEnum.APP,Global.TREE_DEFAULT_ID);
	}

	private List<PortalMenuVo> getMenu(MenuTypeCodeEnum typeCodeEnum,Long parentId){
		List<PortalMenuVo> menuPortalMenuVoList = new ArrayList<>();
		List<SysMenu> menuList = getSysMenuRepository().findMenu(typeCodeEnum.toCode(),parentId, BoolCodeEnum.YES.toCode());
		for(SysMenu sysMenu : menuList){
			PortalMenuVo portalMenuVo = buildMenu(sysMenu);
			if (typeCodeEnum.equals(MenuTypeCodeEnum.APP)){
				List<PortalMenuVo> mdlMenuVoList = getMenu(MenuTypeCodeEnum.MDL,sysMenu.getId());
				List<PortalMenuVo> menuMenuVoList = getMenu(MenuTypeCodeEnum.MENU,sysMenu.getId());
				mdlMenuVoList.addAll(menuMenuVoList);
				portalMenuVo.setSubChildList(mdlMenuVoList);
			}
			if(typeCodeEnum.equals(MenuTypeCodeEnum.MDL)){
				portalMenuVo.setSubChildList(getMenu(MenuTypeCodeEnum.MENU,sysMenu.getId()));
			}
			if(typeCodeEnum.equals(MenuTypeCodeEnum.MENU)){
				portalMenuVo.setSubChildList(getMenu(MenuTypeCodeEnum.BTN,sysMenu.getId())); //第四级菜单，有按钮或菜单
				portalMenuVo.getSubChildList().addAll(getMenu(MenuTypeCodeEnum.MENU,sysMenu.getId()));
			}
			menuPortalMenuVoList.add(portalMenuVo);
		}
		return menuPortalMenuVoList;
	}

	private PortalMenuVo buildMenu(SysMenu sysMenu){
		PortalMenuVo portalMenuVo = new PortalMenuVo();
		portalMenuVo.setId(sysMenu.getId());
		portalMenuVo.setMenuNm(sysMenu.getMenuName());
		portalMenuVo.setIconClass(sysMenu.getIcon());
		portalMenuVo.setMenuType(sysMenu.getMenuType());
		portalMenuVo.setNodeCode(sysMenu.getNodeCode());
		if(sysMenu.getResourceId()!=null){
			SysResource sysResource = ServiceManager.sysResourceService.findOne(sysMenu.getResourceId());
			portalMenuVo.setRouterKey(sysResource.getRouterKey());
			portalMenuVo.setPermissionCode(sysResource.getPermissionCode());
		}
		return portalMenuVo;
	}

	/**
	 * 递归获取子节点
	 * @param menuList
	 * @return
	 */
	private List<SysMenu> recursionLastChild(List<SysMenu> menuList){
		List<SysMenu> sysMenus = new ArrayList<>();
		for(SysMenu sysMenu : menuList){
			List<SysMenu> childMenuList = getSysMenuRepository().findByParentId(sysMenu.getId());
			if(childMenuList.isEmpty()){
				sysMenus.add(sysMenu);
			}else{
				sysMenus.addAll(recursionLastChild(childMenuList));
			}

		}
		return sysMenus;
	}

	private Node buildAndCheckedNode(SysMenu sysMenu,List<Long> menuIdList){
		Long subCount = getSysMenuRepository().countByParentId(sysMenu.getId());

		Node node = new Node();
		node.setId(sysMenu.getId());
		node.setName(sysMenu.getMenuName());
		node.setNodeCode(sysMenu.getNodeCode());
		node.setOpen(false);
		node.setIsParent(subCount > 0);
		//若节点已分配，则直接选中
		if(menuIdList.contains(sysMenu.getId())){
			node.setChecked(true);
			return node;
		}

		//若节点没有被分配，则查看是否有子节点被分配，有子节点被分配父节点也选中
		List<Long> lastChildIdList = findLastChild(sysMenu.getId());
		lastChildIdList.retainAll(menuIdList);
		node.setChecked(lastChildIdList.size()>0);
		return node;
	}

	/**
	 * 创建树
	 * @param sysMenu
	 * @return
	 */
	private Node buildNode(SysMenu sysMenu){
		Long subCount = getSysMenuRepository().countByParentId(sysMenu.getId());
		Node node = new Node();
		node.setId(sysMenu.getId());
		node.setName(sysMenu.getMenuName());
		node.setNodeCode(sysMenu.getNodeCode());
		node.setOpen(false);
		node.setIsParent(subCount > 0);
		return node;
	}
}