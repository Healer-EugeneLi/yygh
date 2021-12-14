package com.eugeneli.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eugeneli.yygh.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @InterfaceName: DictService
 * @Description:
 * @Author EugeneLi
 * @Date: 2021/8/23
 * @Time: 9:29
 */
public interface DictService extends IService<Dict> {

    /**
     * 根据数据id查询子数据列表
     * @param id
     * @return
     */
    List<Dict> findChildData(Long id);

    /**
     * 导入数据字典
     * @param file
     */
    void importDictData(MultipartFile file);


    /**
     * 导出数据字典
     * @param response
     */
    void exportDictData(HttpServletResponse response);
}
