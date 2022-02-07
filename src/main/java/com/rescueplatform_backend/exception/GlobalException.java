package com.rescueplatform_backend.exception;

import com.rescueplatform_backend.entity.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @description: 全局异常处理
 * @author: hannah
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SQLException.class)
   public RespBean mySqlException(SQLException e){
       if (e instanceof SQLIntegrityConstraintViolationException){
           return RespBean.error("该数据有关联数据，操作失败！");
       }
       //除了上面捕获的知道sql异常，其他sql异常都报这个错误
       return RespBean.error("数据异常，操作失败!");
   }
}