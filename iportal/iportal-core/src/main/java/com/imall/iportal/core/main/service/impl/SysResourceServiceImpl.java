package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.global.Global;
import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.Node;
import com.imall.commons.dicts.ResourceTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysMenu;
import com.imall.iportal.core.main.entity.SysResource;
import com.imall.iportal.core.main.entity.SysResourceUrl;
import com.imall.iportal.core.main.repository.SysResourceRepository;
import com.imall.iportal.core.main.service.SysResourceService;
import com.imall.iportal.core.main.valid.SysResourceSaveValid;
import com.imall.iportal.core.main.valid.SysResourceUpdateValid;
import com.imall.iportal.core.main.vo.SysResourceVo;
import com.imall.iportal.core.main.vo.UrlAuthVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * T_PT_SYS_RESOURCE【资源】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysResourceServiceImpl extends AbstractBaseService<SysResource, Long> implements SysResourceService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysResourceRepository getSysResourceRepository() {
		return (SysResourceRepository) baseRepository;
	}

	@Override
	public Page<SysResourceVo> query(Pageable pageable,Long parentId ,String resourceName,String permissionCode,String routerKey, String isAvailable) {
		return getSysResourceRepository().query(pageable, parentId, resourceName, permissionCode, routerKey, isAvailable);
	}

	@Override
	public List<Node> buildResourceTree(Long parentId,Boolean isIncludeRoot) {
		List<SysResource> subResourceList = getSysResourceRepository().findByParentId(parentId == null ? Global.TREE_DEFAULT_ID : parentId);
		return getNodeList(subResourceList, parentId, isIncludeRoot, null);
	}

	@Override
	public List<Node> buildResourceTreeByResourceType(Long parentId, String resourceType, Boolean isIncludeRoot) {
		List<SysResource>  subResourceList = getSysResourceRepository().findByParentId(parentId);
		return getNodeList(subResourceList,parentId,isIncludeRoot,null);//TODO 参考广之旅，添加菜单时不需要根据菜单类型列资源
	}

	@Override
	public List<Node> buildResourceTreeCheckByRole(Long roleId, Long menuId) {
		SysMenu sysMenu = ServiceManager.sysMenuService.findOne(menuId);
		if(sysMenu.getResourceId()==null){
			return new ArrayList<>();
		}
		List<SysResource> resourceList = getSysResourceRepository().findByParentIdAndResourceType(sysMenu.getResourceId(), ResourceTypeCodeEnum.BTN.toCode());
		if (resourceList.isEmpty()){
			return new ArrayList<>();
		}

		//获取角色已分配的资源权限
		List<Long> resourceIdList = ServiceManager.sysRolePermissionService.findResourceIdByRoleId(roleId);

		List<Node> nodeList = new ArrayList<>();
		for(SysResource sysResource : resourceList){
			nodeList.add(buildAndCheckedNode(sysResource, resourceIdList));
		}

		if(!nodeList.isEmpty()){
			List<Node> nodes = new ArrayList<>();
			SysResource sysResource = findOne(sysMenu.getResourceId());

			Node node = buildAndCheckedNode(sysResource, resourceIdList);
			node.setOpen(true);
			node.setChildren(nodeList);
			nodes.add(node);
			return nodes;
		}
		return nodeList;
	}


	@Override
	public List<Long> findLastChild(Long id){
		List<Long> resourceIdList = new ArrayList<>();
		List<SysResource> sysResourceList = recursionLastChild(getSysResourceRepository().findByParentId(id));
		for(SysResource sysResource  : sysResourceList){
			resourceIdList.add(sysResource.getId());
		}
		return resourceIdList;
	}

	@Override
	@Transactional
	public void deleteResource(List<Long> ids) {
		for(Long id : ids){
			SysResource sysResource  = findOne(id);
			List<SysResource> sysResourceList = getSysResourceRepository().findByParentId(id);
			if(!sysResourceList.isEmpty()){
				String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.RESOURCE_DELETE_ERROR);
				throw new BusinessException(ResGlobal.RESOURCE_DELETE_ERROR,String.format(message,new Object[]{sysResource.getResourceName()}));
			}
			SysMenu sysMenu = ServiceManager.sysMenuService.findByResourceId(id);
			if(sysMenu!=null){
				String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.RESOURCE_RELATE_DELETE_ERROR);
				throw new BusinessException(ResGlobal.RESOURCE_RELATE_DELETE_ERROR,String.format(message,new Object[]{sysResource.getResourceName(),sysMenu.getMenuName()}));
			}
		}
		delete(ids);
	}

	/**
	 * 返回所有url为.json的权限
	 */
	@Override
	public List<UrlAuthVo> findAllUrlAuth() {
		List<SysResource> resourceList = getSysResourceRepository().findByIsAvailableAndResourceType();

		List<UrlAuthVo> urlAuthVoList = new ArrayList<UrlAuthVo>();
		for(SysResource resource:resourceList){
			List<SysResourceUrl> resourceUrlList = ServiceManager.sysResourceUrlService.findByResourceId(resource.getId());
			for(SysResourceUrl resourceUrl:resourceUrlList){
				if(!resourceUrl.getUrl().endsWith(".json")){
					continue;
				}

				UrlAuthVo urlAuthVo = new UrlAuthVo();
				urlAuthVo.setPermission(resource.getPermissionCode());
				urlAuthVo.setUrl(resourceUrl.getUrl());
				urlAuthVoList.add(urlAuthVo);
			}
		}
		return urlAuthVoList;
	}

	@Transactional
	@Override
	public SysResource save(SysResourceSaveValid sysResourceSaveValid) {
		//若为新增，则自动先生成树编码
		sysResourceSaveValid.setNodeCode(ServiceManager.treeCodeGenerateBuilder.generate8Code(SysResource.class, sysResourceSaveValid.getParentId()));
		SysResource sysResource = CommonUtil.copyProperties(sysResourceSaveValid, new SysResource());
		return saveSysResource(sysResource);
	}

	@Transactional
	@Override
	public SysResource update(SysResourceUpdateValid sysResourceUpdateValid) {
		//若为新增，则自动先生成树编码
		if(sysResourceUpdateValid.getId()==null){
			sysResourceUpdateValid.setNodeCode(ServiceManager.treeCodeGenerateBuilder.generate8Code(SysResource.class, sysResourceUpdateValid.getParentId()));
		}
		SysResource sysResource = CommonUtil.copyProperties(sysResourceUpdateValid, new SysResource());
		return saveSysResource(sysResource);
	}

	@Override
	public List<String> findResourceNameByAppId(Long appId) {
		return getSysResourceRepository().findResourceNameByAppId(appId);
	}

	@Override
	public List<SysResource> findByNodeCodeLike(String nodeCode) {
		return getSysResourceRepository().findByNodeCodeLike(nodeCode);
	}

	@Override
	public List<String> findByRouterTemplateJs(String routerTemplateJs) {
		return getSysResourceRepository().findByRouterTemplateJs(routerTemplateJs);
	}

	private SysResource saveSysResource(SysResource sysResource) {
		Boolean isAdd=sysResource.getId()==null;
		SysResource resource = save(sysResource);
		if(isAdd&&ResourceTypeCodeEnum.BTN.toCode().equals(sysResource.getResourceType())){
			ServiceManager.sysRolePermissionService.saveOrDeleteRolePermission(Global.DEFAULT_ADMIN_ROLE_ID,sysResource.getId(),true);
		}
		return resource;
	}

	private Node buildAndCheckedNode(SysResource sysResource,List<Long> resourceIdList){
		long childResourceCount = getSysResourceRepository().countByParentId(sysResource.getId());

		Node node = new Node();
		node.setId(sysResource.getId());
		node.setName(sysResource.getResourceName());
		node.setNodeCode(sysResource.getNodeCode());
		node.setOpen(false);
		node.setIsParent(childResourceCount>0);
		//若节点已分配，则直接选中
		if(resourceIdList.contains(sysResource.getId())){
			node.setChecked(true);
			return node;
		}

		//若节点没有被分配，则查看是否有子节点被分配，有子节点被分配父节点也选中
		List<Long> lastChildIdList = findLastChild(sysResource.getId());
		lastChildIdList.retainAll(resourceIdList);
		node.setChecked(lastChildIdList.size()>0);
		return node;
	}

	/**
	 * 递归获取子节点
	 * @param resourceList
	 * @return
	 */
	private List<SysResource> recursionLastChild(List<SysResource> resourceList){
		List<SysResource> sysResources = new ArrayList<SysResource>();
		for(SysResource sysResource : resourceList){
			List<SysResource> childResourceList = getSysResourceRepository().findByParentId(sysResource.getId());
			if(childResourceList.isEmpty()){
				sysResources.add(sysResource);
			}else{
				sysResources.addAll(recursionLastChild(childResourceList));
			}

		}
		return sysResources;
	}

	private List<Node> getNodeList(List<SysResource> subResourceList,Long parentId,boolean isIncludeRoot,String resourceType){
		List<Node> nodeList = new ArrayList<>();
		for(SysResource sysResource : subResourceList){
			nodeList.add(buildNode(sysResource,resourceType));
		}
		if (isIncludeRoot && parentId.equals(Global.TREE_DEFAULT_ID)) {
			List<Node> nodes = new ArrayList<>();
			SysResource sysResource = getSysResourceRepository().findOne(Global.TREE_DEFAULT_ID);

			Node node = buildNode(sysResource,resourceType);
			node.setOpen(!nodeList.isEmpty());
			node.setChildren(nodeList);
			nodes.add(node);

			return nodes;
		}
		return nodeList;
	}

	private Node buildNode(SysResource sysResource,String resourceType){
		List<SysResource>  subResourceList = getSysResourceRepository().findByParentIdAndResourceType(sysResource.getId(), resourceType);
		if(resourceType==null){
			subResourceList = getSysResourceRepository().findByParentId(sysResource.getId());
		}

		Node node = new Node();
		node.setId(sysResource.getId());
		node.setName(sysResource.getResourceName());
		node.setNodeCode(sysResource.getNodeCode());
		node.setOpen(false);
		node.setIsParent(!subResourceList.isEmpty());
		return node;
	}


}