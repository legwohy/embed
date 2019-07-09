package com.cobra.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by xiaoming on 16/3/14.
 * 应用属性获取
 * 应用改造完成后,可以在应用部署服务器通过 curl http://localhost:8030/healthcheck.html 查看访问结果
 */
@RestController
@RequestMapping("/")
@Slf4j
public class HealthCheckController {

    /**
     * 定义响应信息
     */
    private String opsInfo;

    public HealthCheckController() {
        try (BufferedInputStream inputStream = new BufferedInputStream(HealthCheckController.class
                .getResourceAsStream("/healthcheck.html")) ){
            StringBuilder sb = new StringBuilder();
            byte[] line = new byte[2048];
            while (inputStream.read(line) != -1) {
                sb.append(new String(line));
            }
            opsInfo = sb.toString().trim();
        } catch (FileNotFoundException e) {
            opsInfo = "healthcheck.html file not exist";
            log.error(opsInfo);
        } catch (IOException e) {
            opsInfo = "healthcheck.html info read error";
            log.error(opsInfo);
        }
    }

    /**
     * 应用打包相关信息
     *
     * @return project.version
     */
    @RequestMapping({"healthcheck.html"})
    public String healthCheck() {
        return opsInfo;
    }

}