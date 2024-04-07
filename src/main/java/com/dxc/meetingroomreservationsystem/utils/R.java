package com.dxc.meetingroomreservationsystem.utils;



import com.dxc.meetingroomreservationsystem.pojo.enums.ResponseCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class R<T> implements Serializable {
    
        
        private int status;
     
        private String msg;
 
        private T data;

        public R(){
            super();
        }

        public R(int status){
            this.status = status;
        }
        public R(int status,T data){
            this.status = status;
            this.data = data;
        }
        public R(int status,String msg,T data){
            this.status = status;
            this.msg = msg;
            this.data = data;
        }
        public R(int status,String msg){
            this.status = status;
            this.msg = msg;
        }

        public boolean isSuccess(){
            return this.status == ResponseCode.SUCCESS.getCode();
        }
        public Integer getStatus(){
            return status;
        }
        public String getMsg(){
            return msg;
        }

        public static <T> R<T> createBySuccess(){
            return new R<T>(ResponseCode.SUCCESS.getCode());
        }

        public static <T> R<T> createBySuccess(T data){
            return new R<T>(ResponseCode.SUCCESS.getCode(),data);
        }

        public static <T> R<T> createBySuccess(String msg){
            return new R<T>(ResponseCode.SUCCESS.getCode(),msg);
        }

        public static <T> R<T> createBySuccess(String msg, T data){
            return new R<T>(ResponseCode.SUCCESS.getCode(),msg,data);
        }


        public static <T> R<T> createByError(){
            return new R<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
        }

        public static <T> R<T> createByError(T data){
            return new R<T>(ResponseCode.ERROR.getCode(),data);
        }

        public static <T> R<T> createByError(String errorMessage){
            return new R<T>(ResponseCode.ERROR.getCode(),errorMessage);
        }

        public static <T> R<T> createByError(Integer errorCode, String errorMessage){
            return new R<T>(errorCode,errorMessage);
        }

        public static <T> R<T> createByError(Integer code, String errorMessage,T data){
            return new R<T>(code,errorMessage,data);
        }

        public static <T> R<T> createByError(String errorMessage,T data){
            return new R<T>(ResponseCode.ERROR.getCode(),errorMessage,data);
        }

    }
