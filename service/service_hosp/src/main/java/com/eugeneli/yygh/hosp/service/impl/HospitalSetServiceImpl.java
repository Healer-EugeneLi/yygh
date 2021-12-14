package com.eugeneli.yygh.hosp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eugeneli.yygh.hosp.mapper.HospitalSetMapper;
import com.eugeneli.yygh.hosp.service.HospitalSetService;
import com.eugeneli.yygh.model.hosp.HospitalSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: HospitalSetServiceImpl
 * @Description:
 * @Author EugeneLi
 * @Date: 2021/8/18
 * @Time: 21:35
 */

@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {

    @Autowired
    private HospitalSetMapper hospitalSetMapper;
}
