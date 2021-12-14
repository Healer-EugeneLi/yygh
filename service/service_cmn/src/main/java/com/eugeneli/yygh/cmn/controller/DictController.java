package com.eugeneli.yygh.cmn.controller;

import com.eugeneli.yygh.cmn.service.DictService;
import com.eugeneli.yygh.common.result.Result;
import com.eugeneli.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName: DictController
 * @Description:
 * @Author EugeneLi
 * @Date: 2021/8/23
 * @Time: 9:39
 */
@ApiModel("数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
@CrossOrigin
public class DictController {

    @Autowired
    private DictService dictService;

    @ApiOperation("导出数据字典")
    @GetMapping("exportDictData")
    public void exportDictData(HttpServletResponse response){

        dictService.exportDictData(response);


    }


    @ApiOperation("导入数据字典")
    @PostMapping("importDictData")
    public Result imprtDictData(MultipartFile file){

        dictService.importDictData(file);

        return Result.ok();
    }


    @ApiOperation("根据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id){
        List<Dict> data = dictService.findChildData(id);
        return Result.ok(data);
    }
}
