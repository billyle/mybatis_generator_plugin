package me.libin.db.mapper;

import java.util.List;
import me.libin.db.model.TestBean2;
import me.libin.db.model.TestBean2Example;
import me.libin.db.model.TestBean2Key;
import org.apache.ibatis.annotations.Param;

public interface TestBean2Mapper {
    int countByExample(TestBean2Example example);

    int deleteByExample(TestBean2Example example);

    int deleteByPrimaryKey(TestBean2Key key);

    int insert(TestBean2 record);

    int insertSelective(TestBean2 record);

    List<TestBean2> selectByExample(TestBean2Example example);

    TestBean2 selectByPrimaryKey(TestBean2Key key);

    int updateByExampleSelective(@Param("record") TestBean2 record, @Param("example") TestBean2Example example);

    int updateByExample(@Param("record") TestBean2 record, @Param("example") TestBean2Example example);

    int updateByPrimaryKeySelective(TestBean2 record);

    int updateByPrimaryKey(TestBean2 record);
}