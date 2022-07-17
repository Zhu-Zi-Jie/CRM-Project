package com.zzj.crm.workbench.mapper;

import com.zzj.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sat Jul 16 16:48:47 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sat Jul 16 16:48:47 CST 2022
     */
    int insert(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sat Jul 16 16:48:47 CST 2022
     */
    int insertSelective(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sat Jul 16 16:48:47 CST 2022
     */
    Activity selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sat Jul 16 16:48:47 CST 2022
     */
    int updateByPrimaryKeySelective(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sat Jul 16 16:48:47 CST 2022
     */
    int updateByPrimaryKey(Activity record);

    //保存创建的市场活动
    int insertActivity(Activity activity);

    //根据条件分页查询市场活动的列表
    List<Activity> selectActivityByConditionForPage(Map<String, Object> map);

    //根据条件查询市场活动的总条数
    int selectCountOfActivityByCondition(Map<String, Object> map);
}