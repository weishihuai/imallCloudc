package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.global.Global;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.Node;
import com.imall.iportal.core.main.entity.SysZone;
import com.imall.iportal.core.main.repository.SysZoneRepository;
import com.imall.iportal.core.main.service.SysZoneService;
import com.imall.iportal.core.main.vo.ZoneSubVo;
import com.imall.iportal.core.main.vo.ZoneTreeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * T_PT_SYS_ZONE【地区表】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysZoneServiceImpl extends AbstractBaseService<SysZone, Long> implements SysZoneService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysZoneRepository getSysZoneRepository() {
		return (SysZoneRepository) baseRepository;
	}

	@Override
	public List<ZoneTreeVo> findSysZoneTree() {
		List<SysZone> provinceList=findByParentId(Global.TREE_DEFAULT_ID);
		List<ZoneTreeVo> zoneTreeVos = new ArrayList<ZoneTreeVo>();
		for (SysZone province:provinceList){
			ZoneTreeVo zoneTreeVo = new ZoneTreeVo();
			zoneTreeVo.setZoneCode(province.getZoneCode());
			zoneTreeVo.setZoneName(province.getZoneName());
			/**市**/
			List<ZoneSubVo> cityList = getZoneSubList(province.getId());
			zoneTreeVo.setCityList(cityList);
			/**区**/
			for (ZoneSubVo city:cityList){
				List<ZoneSubVo> areaList=getZoneSubList(city.getId());
				city.setAreaList(areaList);
			}
			zoneTreeVos.add(zoneTreeVo);
		}
		return zoneTreeVos;
	}

	@Override
	public SysZone findByZoneCode(String nodeCode) {
		return getSysZoneRepository().findByNodeCode(nodeCode);
	}

	/**
	 * 获取子节点
	 * @param id
	 * @return
	 */
	private List<ZoneSubVo> getZoneSubList(Long id){
		List<SysZone> cityList=findByParentId(id);
		List<ZoneSubVo> cityArrayList= new ArrayList<ZoneSubVo>();
		for (SysZone sysZone:cityList){
			ZoneSubVo city=new ZoneSubVo();
			city.setZoneName(sysZone.getZoneName());
			city.setZoneCode(sysZone.getZoneCode());
			city.setId(sysZone.getId());
			cityArrayList.add(city);
		}
		return cityArrayList;
	}

	@Override
	public List<SysZone> findByParentId(Long parentId){
		return getSysZoneRepository().findByParentId(parentId);
	}

	@Override
	public List<Node> findByParentId(Long parentId, int subMaxLayer) {
		List<SysZone> list = getSysZoneRepository().findByParentId(parentId);
		return build(parentId, subMaxLayer, list, 0);
	}

	@Override
	public List<Node> findByLastId(Long id) {
		return build(new ArrayList<Node>(), id);
	}

	private List<Node> build(List<Node> list, Long id){
		if(id<=Global.TREE_DEFAULT_ID){
			return list;
		}
		SysZone zone = getSysZoneRepository().findOne(id);
		Node node = new Node();
		node.setId(zone.getId());
		node.setName(zone.getZoneName());
		node.setNodeCode(zone.getNodeCode());
		node.setPid(zone.getParentId());
		node.setOpen(false);
		node.setChecked(false);
		node.setNodeObject(zone);
		node.setIsParent(false);
		node.setChildren(new ArrayList<Node>());
		list.add(0, node);
		//处理父
		build(list, zone.getParentId());
		return list;
	}

	private List<Node> build(Long parentId, int subMaxLayer, List<SysZone> list, int currSubLayer){
		List<Node> nodes = new ArrayList<>();
		for(SysZone zone: list){
			Node node = new Node();
			node.setId(zone.getId());
			node.setName(zone.getZoneName());
			node.setNodeCode(zone.getNodeCode());
			node.setPid(parentId);
			node.setOpen(false);
			node.setChecked(false);
			node.setNodeObject(zone);
			if(currSubLayer < subMaxLayer){
				List<SysZone> subList = getSysZoneRepository().findByParentId(zone.getId());
				node.setIsParent(subList.size() > 0);
				node.setChildren(build(zone.getId(), subMaxLayer, subList, currSubLayer + 1));
			}
			else {
				node.setIsParent(false);
				node.setChildren(new ArrayList<Node>());
			}
			nodes.add(node);
		}
		return nodes;
	}

	@Override
	public Map<String, Object> findSysZoneTreeByZoneId(Long zoneId) {
		Map<String, Object> result = new HashMap<>();

		List<List<SysZone>> dataList = new ArrayList<>();
		List<Long> pathIds = new ArrayList<>();

		SysZone sysZone = this.findOne(zoneId);
		while (sysZone != null && sysZone.getId() != 1) {
			pathIds.add(sysZone.getId());

			//查询子节点
			List<SysZone> children = this.findByParentId(sysZone.getId());
			if (!children.isEmpty()) {
				dataList.add(children);
			}
			sysZone = this.findOne(sysZone.getParentId());
		}
		dataList.add(this.findByParentId(1L)); //查询所有省级地区
		result.put("pathIds", pathIds);
		result.put("dataList", dataList);

		return result;
	}
}