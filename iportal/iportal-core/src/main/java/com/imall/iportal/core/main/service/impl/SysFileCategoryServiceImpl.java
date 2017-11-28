package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.global.Global;
import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.Node;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysFileCategory;
import com.imall.iportal.core.main.repository.SysFileCategoryRepository;
import com.imall.iportal.core.main.service.SysFileCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * T_PT_SYS_FILE_CATEGORY 【文件分类】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysFileCategoryServiceImpl extends AbstractBaseService<SysFileCategory, Long> implements SysFileCategoryService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysFileCategoryRepository getSysFileCategoryRepository() {
		return (SysFileCategoryRepository) baseRepository;
	}

	@Override
	public List<Node> buildFileCategoryTree(Long parentId, Boolean isIncludeRoot, Long orgId) {
		List<SysFileCategory> sysFileCategoryList = getSysFileCategoryRepository().findByParentIdAndOrgId(parentId == null ? Global.TREE_DEFAULT_ID : parentId, orgId);
		return getNodeList(sysFileCategoryList, parentId, isIncludeRoot);
	}

	private List<Node> getNodeList(List<SysFileCategory> subResourceList,Long parentId,boolean isIncludeRoot){
		List<Node> nodeList = new ArrayList<>();
		for(SysFileCategory sysFileCategory : subResourceList){
			nodeList.add(buildNode(sysFileCategory));
		}
		if (isIncludeRoot && parentId == null) {//parentId不能像别的地方一样，使用parentId.equal(Global.TREE_DEFAULT_ID),因为使用了异步刷新节点，若刷新根节点会把根节点也返回回去，造成重复
			List<Node> nodes = new ArrayList<>();
			SysFileCategory sysFileCategory = findOne(Global.TREE_DEFAULT_ID);

			Node node = buildNode(sysFileCategory);
			node.setOpen(!nodeList.isEmpty());
			node.setChildren(nodeList);
			nodes.add(node);

			return nodes;
		}
		return nodeList;
	}

	private Node buildNode(SysFileCategory sysFileCategory){
		List<SysFileCategory>  subFileCategoryList = getSysFileCategoryRepository().findByParentId(sysFileCategory.getId());

		Node node = new Node();
		node.setId(sysFileCategory.getId());
		node.setName(sysFileCategory.getCategoryName());
		node.setNodeCode(sysFileCategory.getNodeCode());
		node.setOpen(false);
		node.setIsParent(!subFileCategoryList.isEmpty());
		return node;
	}

	@Override
	@Transactional
	public void deleteFileCategory(Long id, Long orgId) {
		SysFileCategory sysFileCategory =  findOne(id);
		if(sysFileCategory == null || !sysFileCategory.getOrgId().equals(orgId)) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"文件目录"}));
		}

		List<SysFileCategory> sysFileCategoryList = getSysFileCategoryRepository().findByParentId(id);
		if(!sysFileCategoryList.isEmpty()){
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.FILE_CATEGORY_DELETE_ERROR);
			throw new BusinessException(ResGlobal.FILE_CATEGORY_DELETE_ERROR,String.format(message,new Object[]{sysFileCategory.getCategoryName()}));
		}
		delete(id);
	}

	@Transactional
	@Override
	public Long saveCategory(Long id, String categoryName, Long orgId) {
		SysFileCategory fileCategory = new SysFileCategory();
		fileCategory.setParentId(id);
		fileCategory.setCategoryName(categoryName);
		fileCategory.setOrgId(orgId);
		fileCategory.setNodeCode(ServiceManager.treeCodeGenerateBuilder.generate8Code(SysFileCategory.class, id));
		save(fileCategory);
		return fileCategory.getId();
	}

	@Transactional
	@Override
	public Long updateCategory(Long id, String categoryName, Long orgId) {
		SysFileCategory fileCategory = findOne(id);
		if(fileCategory == null || !fileCategory.getOrgId().equals(orgId)) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"文件目录"}));
		}

		fileCategory.setCategoryName(categoryName);
		save(fileCategory);
		return fileCategory.getId();
	}
}