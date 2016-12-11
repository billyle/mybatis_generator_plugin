package me.libin.db.mapper;

import java.util.List;
import me.libin.db.model.TestBean;
import me.libin.db.model.TestBeanExample;
import org.apache.ibatis.annotations.Param;

public interface TestBeanMapper {
    int countByExample(TestBeanExample example);

    int deleteByExample(TestBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TestBean record);

    int insertSelective(TestBean record);

    List<TestBean> selectByExample(TestBeanExample example);

    TestBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TestBean record, @Param("example") TestBeanExample example);

    int updateByExample(@Param("record") TestBean record, @Param("example") TestBeanExample example);

    int updateByPrimaryKeySelective(TestBean record);

    int updateByPrimaryKey(TestBean record);
}