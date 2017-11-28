package com.imall.commons.util;

import com.imall.commons.base.util.JsonBinder;
import com.imall.commons.base.vo.ResultVo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ygw on 2016/9/2.
 */
public class JsonBinderTest {

    @Test
    public void testToJson(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("ID0001");
        map.put("ids", list);
        ResultVo resultVo = new ResultVo(true, "操作成功", map);
        String result = JsonBinder.toJson(resultVo);
        System.out.println(result);
        String jsonStr = "{\"msg\":\"操作成功\",\"obj\":{\"ids\":[\"ID0001\"]},\"success\":true}";
        assert result.equals(jsonStr);
    }

    @Test
    public void testToBeanObject(){
        String jsonStr = "{\"msg\":\"操作成功\",\"obj\":{\"ids\":[\"ID0001\"]},\"success\":true}";
        ResultVo resultVo = JsonBinder.toBeanObject(jsonStr, ResultVo.class);
        System.out.println(resultVo.getMsg());
        assert resultVo.getMsg().equals("操作成功");
    }

    @Test
    public void testToBeanList(){
        String jsonStr = "[\"ID0001\"]";
        List<String> list = JsonBinder.toBeanList(jsonStr, String.class);
        System.out.println(list.get(0));
        assert list.get(0).equals("ID0001");
    }

    @Test
    public void testToBeanMap(){
        String jsonStr = "{\"ids\":[\"ID0001\"]}";
        Map<String, List<String>> map = JsonBinder.< Map<String, List<String>>>toBeanMap(jsonStr);
        System.out.println(map.get("ids").get(0));
        assert map.get("ids").get(0).equals("ID0001");
    }

   /* @Test
    public void testToBean(){
        String jsonStr = "{\"msg\":\"操作成功\",\"obj\":{\"ids\":[\"ID0001\"]},\"success\":true}";
        ResultVo resultVo = JsonBinder.<ResultVo>toBean(jsonStr);
        System.out.println(resultVo.getMsg());
        assert resultVo.getMsg().equals("操作成功");
    }*/
}
