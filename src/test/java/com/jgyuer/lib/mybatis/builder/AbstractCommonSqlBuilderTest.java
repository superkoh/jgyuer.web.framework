package com.jgyuer.lib.mybatis.builder;

import com.jgyuer.framework.domain.BaseDomain;
import org.apache.ibatis.type.EnumTypeHandler;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by KOH on 2017/5/13.
 * <p>
 * webFramework
 */
public class AbstractCommonSqlBuilderTest {

    @Test
    public void testInsert() {
        SqlBuilder sqlBuilder = new SqlBuilder();
        TestDomain testDomain = new TestDomain();
        testDomain.setName("koh");
        testDomain.setNickName("superkoh");
        System.out.println(sqlBuilder.insert(testDomain));
        System.out.println(sqlBuilder.updateByPrimaryKeySelective(testDomain));

    }

    static class SqlBuilder extends AbstractCommonSqlBuilder {
        private static final Map<String, Class> typeHandlerMap;
        private static final Map<String, String> jdbcTypeMap;

        static {
            typeHandlerMap = new HashMap<>();
            typeHandlerMap.put("nickName", EnumTypeHandler.class);

            jdbcTypeMap = new HashMap<>();
            jdbcTypeMap.put("nickName", "VARCHAR");
        }

        @Override
        protected String getTableName() {
            return "user";
        }

        @Override
        protected Map<String, Class> typeHandlerMap() {
            return SqlBuilder.typeHandlerMap;
        }

        @Override
        protected Map<String, String> jdbcTypeMap() {
            return SqlBuilder.jdbcTypeMap;
        }

        @Override
        protected String getPrimaryKeyName() {
            return "id";
        }
    }

    class TestDomain extends BaseDomain {
        private Long id;
        private String name;
        private String nickName;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }

}
