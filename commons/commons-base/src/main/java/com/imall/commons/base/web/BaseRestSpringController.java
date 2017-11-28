package com.imall.commons.base.web;

import com.imall.commons.base.vo.ResultVo;

public class BaseRestSpringController {

    protected static final String CREATED_SUCCESS = "创建成功";
    protected static final String UPDATE_SUCCESS = "更新成功";
    protected static final String DELETE_SUCCESS = "删除成功";
    
    protected static final String RESULT_STRING = "result";
    protected static final String TOTAL = "total";
    protected static final String RESULT_ACTION = "redirect:/result";
    protected static final ResultVo success = new ResultVo(true);
    protected static final ResultVo failure = new ResultVo(false);

    public final ResultVo getSuccess(){
        return success;
    }

    public final ResultVo getFailure(){
        return failure;
    }
}
