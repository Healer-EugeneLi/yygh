package com.eugeneli.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eugeneli.yygh.common.result.Result;
import com.eugeneli.yygh.common.utils.MD5;
import com.eugeneli.yygh.hosp.service.HospitalSetService;
import com.eugeneli.yygh.model.hosp.HospitalSet;
import com.eugeneli.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @ClassName: HospitalSetController
 * @Description:
 * @Author EugeneLi
 * @Date: 2021/8/18
 * @Time: 21:41
 */
@CrossOrigin(allowCredentials = "true")
@Api(tags = "医院设置")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    //1.查询所有医院设置的信息
    @ApiOperation("获取所有医院设置")
    @GetMapping("findAll")
    public Result  findAllHospitalSet(){

        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);

    }

    //2.逻辑删除医院设置信息
    @ApiOperation("逻辑删除医院设置")
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id){

        boolean flag = hospitalSetService.removeById(id);

        if(flag){
            return Result.ok();
        }else
            return Result.fail();

    }


    /**
     * 3.条件查询带分页
     * @param current 当前页
     * @param limit 每页记录数
     * @param hospitalSetQueryVo
     * @return
     */
    @ApiOperation("条件查询带分页")
    @GetMapping("/findPageHospSet/{current}/{limit}")
    public Result findPageHospSet(@PathVariable Long current,
                                  @PathVariable Long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo){

        //1.封装page 传递当前页 每页记录数
        Page<HospitalSet> page=new Page<>(current,limit);

        //2.构建条件查询
        QueryWrapper<HospitalSet> wrapper=new QueryWrapper<>();

        String hosname = hospitalSetQueryVo.getHosname();
        if(!StringUtils.isEmpty(hosname)){
            //医院名称不为空时进行模糊查询
            wrapper.like("hosname",hosname);
        }

        String hoscode = hospitalSetQueryVo.getHoscode();
        if (!StringUtils.isEmpty(hoscode)) {
            //医院编号不为空时进行等值查询
            wrapper.eq("hoscode",hoscode);
        }

        Page<HospitalSet> hospitalSetPage = hospitalSetService.page(page, wrapper);
        return Result.ok(hospitalSetPage);


    }

    /**
     * 添加医院设置
     * @param hospitalSet
     * @return
     */
    @ApiOperation("添加医院设置")
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet){

        //单独设置状态 1使用 0不能使用
        hospitalSet.setStatus(1);

        //签名密钥
        Random random=new Random();
        String encrypt = MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000));
        hospitalSet.setSignKey(encrypt);

        boolean save = hospitalSetService.save(hospitalSet);
        if(save)
            return Result.ok();
        else
            return Result.fail();

    }


    /**
     * 根据id获取医院设置
     * @param id
     * @return
     */
    @ApiOperation("根据id获取医院设置")
    @GetMapping("getHospitalSet/{id}")
    public Result getHospitalSet(@PathVariable Long id){

        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }

    /**
     * 更新医院设置
     * @param hospitalSet
     * @return
     */
    @ApiOperation("更新医院设置")
    @PostMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet){

        boolean flag = hospitalSetService.updateById(hospitalSet);
        if(flag)
            return Result.ok();
        else
            return Result.fail();

    }

    @ApiOperation("根据id批量删除医院设置")
    @DeleteMapping("batchRmove")
    public Result batchRemoveHospitalSet(@RequestBody List<Long> idList){

        boolean flag = hospitalSetService.removeByIds(idList);
        if(flag)
            return Result.ok();
        else
            return Result.fail();
    }


    @ApiOperation("医院设置锁定和解锁")
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,
                                  @PathVariable Integer status){

        HospitalSet hospitalSet = hospitalSetService.getById(id);

        hospitalSet.setStatus(status);//设置状态0 锁定 1解锁

        hospitalSetService.updateById(hospitalSet);
        return Result.ok();

    }

    //发送签名密钥
    @ApiOperation("发送签名密钥")
    @PostMapping("sendKey/{id}")
    public Result sendSignCode(@PathVariable Long id){

        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();

        //发送短信
        return Result.ok();

    }

}
