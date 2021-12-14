package com.eugeneli.yygh.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eugeneli.yygh.cmn.listener.DictListener;
import com.eugeneli.yygh.cmn.mapper.DictMapper;
import com.eugeneli.yygh.cmn.service.DictService;
import com.eugeneli.yygh.model.cmn.Dict;
import com.eugeneli.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DictServiceImpl
 * @Description:
 * @Author EugeneLi
 * @Date: 2021/8/23
 * @Time: 9:31
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {


    @Autowired DictMapper dictMapper;

    /**
     * 根据数据id查询子数据列表
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "dict",keyGenerator = "keyGenerator")
    public List<Dict> findChildData(Long id) {
        QueryWrapper<Dict> wrapper=new QueryWrapper<>();
        wrapper.eq("parent_id",id);

        List<Dict> dicts = baseMapper.selectList(wrapper);

        //对每个节点设置是否有子节点
        for(Dict dict:dicts){
            Long dictId = dict.getId();

            boolean hasChildren = this.isHasChildren(dictId);
            dict.setHasChildren(hasChildren);
        }

        return dicts;

    }

    /**
     * 导入数据字典
     *allEntries = true: 方法调用后清空所有缓存
     * @param file
     */
    @Override
    @CacheEvict(value = "dict", allEntries=true)
    public void importDictData(MultipartFile file) {

        try {
            EasyExcel.read(file.getInputStream(), DictEeVo.class,new DictListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出数据字典
     *
     * @param response
     */
    @Override
    public void exportDictData(HttpServletResponse response) {


        try {
            //设置下载信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = null;
            fileName = URLEncoder.encode("数据字典", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");

            List<Dict> dicts = dictMapper.selectList(null);
            //转换结果 dict--DictEeVo
            List<DictEeVo> dictEeVoList=new ArrayList<>(dicts.size());
            for(Dict dict:dicts){
                DictEeVo dictEeVo=new DictEeVo();
                BeanUtils.copyProperties(dict,dictEeVo,DictEeVo.class);//复制
                dictEeVoList.add(dictEeVo);
            }

            EasyExcel.write(response.getOutputStream(),DictEeVo.class).sheet("数据字典").doWrite(dictEeVoList);


        } catch (UnsupportedEncodingException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    //判断id下面是否有子节点
    public boolean isHasChildren(Long id){

        QueryWrapper<Dict> wrapper=new QueryWrapper<>();
        wrapper.eq("parent_id",id);

        Integer count = baseMapper.selectCount(wrapper);

        return count>0;
    }
}
