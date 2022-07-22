package com.zzj.crm.workbench.mapper;

import com.zzj.crm.workbench.domain.ClueRemark;

import java.util.List;

public interface ClueRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Fri Jul 22 16:35:41 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Fri Jul 22 16:35:41 CST 2022
     */
    int insert(ClueRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Fri Jul 22 16:35:41 CST 2022
     */
    int insertSelective(ClueRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Fri Jul 22 16:35:41 CST 2022
     */
    ClueRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Fri Jul 22 16:35:41 CST 2022
     */
    int updateByPrimaryKeySelective(ClueRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Fri Jul 22 16:35:41 CST 2022
     */
    int updateByPrimaryKey(ClueRemark record);

    //根据clueId查询线索下的所有备注
    List<ClueRemark> selectClueRemarkForDetailByClueId(String clueId);

    //根据clueId查询该线索下的所有备注信息
    List<ClueRemark> selectClueRemarkByClueId(String clueId);

    //根据clueId删除该线索下的所有备注
    int deleteClueRemarkByClueId(String clueId);
}