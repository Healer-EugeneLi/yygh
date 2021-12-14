package com.eugeneli.yygh.cmn.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: HospConfig
 * @Description:
 * @Author EugeneLi
 * @Date: 2021/8/18
 * @Time: 21:43
 */

@Configuration
@MapperScan("com.eugeneli.yygh.cmn.mapper")
public class CmnConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
