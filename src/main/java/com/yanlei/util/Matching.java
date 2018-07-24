package com.yanlei.util;

/**
 * @Author: x
 * @Date: Created in 16:24 2018/3/16
 */
public class Matching {

    public enum  BranchDepartment{
        QY("区域","region"),
        QYS("区域数","regions"),
        HY("行业","industry"),
        HYS("行业数","industrys"),
        SS("税收","revenue"),
        SSS("税收数","revenues"),
        GZRS("员工人数","workMan"),
        GZRSS("员工人数数","workMans"),
        ZJ("资金","money"),
        ZJS("资金数","moneys");


        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        BranchDepartment(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }

    public enum DeaprtmentID{
        ZWBMSJ("政务部门数据",116),
        SHBMSJ("社会部门数据",117),
        WHBMSJ("文化部门数据",118),
        JJBMSJ("经济部门数据",119),
        QTBMSJ("其他部门数据",999),
        ZWSJ("政务数据",1),
        SHSJ("社会数据",2),
        WHSJ("文化数据",3),
        JJSJ("经济数据",4);

        private String name;
        private Integer value;


        DeaprtmentID(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public enum RESULT{
        /**
         * 成功
         */
        CODE_YES("0"),
        /**
         * 失败
         */
        CODE_NO("-1"),
        /**
         * 失败msg
         */
        MSG_YES("操作成功"),
        /**
         * 失败msg
         */
        MSG_NO("操作失败");
        private String value;

        private RESULT(String value){
            this.value=value;
        }
        public String getValue(){
            return value;
        }
    }
}
