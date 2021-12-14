package com.eugeneli.yygh.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.eugeneli.yygh.cmn.mapper.DictMapper;
import com.eugeneli.yygh.model.cmn.Dict;
import com.eugeneli.yygh.vo.cmn.DictEeVo;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName: DictListener
 * @Description:
 * @Author EugeneLi
 * @Date: 2021/8/26
 * @Time: 22:05
 */
public class DictListener extends AnalysisEventListener<DictEeVo> {

    private DictMapper dictMapper;

    public DictListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    //一行一行读取数据
    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {

        //调用方法添加到数据库
        Dict dict=new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictMapper.insert(dict);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
