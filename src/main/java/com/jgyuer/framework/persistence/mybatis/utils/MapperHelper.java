package com.jgyuer.framework.persistence.mybatis.utils;

import com.jgyuer.framework.persistence.mybatis.exception.RecordNotFoundException;
import com.jgyuer.framework.persistence.mybatis.mapper.BaseMapper;
import com.jgyuer.framework.persistence.mybatis.mapper.BaseMapperWithPK;
import com.jgyuer.framework.type.Page;
import com.jgyuer.framework.type.PageList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperHelper {
    private static final Logger logger = LoggerFactory.getLogger(MapperHelper.class);

    public static <R, RE, K, M extends BaseMapperWithPK<R, RE, K>> R selectByPrimaryKeyGuaranteed(M mapper, K id)
            throws RecordNotFoundException {
        R r = mapper.selectByPrimaryKey(id);
        if (null == r) {
            throw new RecordNotFoundException(mapper.getClass().getTypeName().replace("Mapper", ""));
        }
        try {
            Method m = r.getClass().getMethod("getIsDeleted");
            Boolean isDeleted = (Boolean) m.invoke(r);
            if (isDeleted) {
                throw new RecordNotFoundException(mapper.getClass().getTypeName().replace("Mapper", ""));
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }
        return r;
    }

    public static <R, RE, K, M extends BaseMapperWithPK<R, RE, K>> Map<K, R> selectMapByExample(M mapper, RE example,
                                                                                                String primaryKeyName) {
        List<R> list = mapper.selectByExample(example);
        if (list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K, R> map = new HashMap<>();
        Method getPK;
        try {
            getPK = list.get(0).getClass().getMethod("get" + StringUtils.capitalize(primaryKeyName));
        } catch (NoSuchMethodException ignored) {
            return Collections.emptyMap();
        }
        for (R item : list) {
            try {
                map.put((K) getPK.invoke(item), item);
            } catch (IllegalAccessException | InvocationTargetException ignored) {
            }
        }
        return map;
    }

    public static <R, RE, M extends BaseMapper<R, RE>, C, Q> PageList<R> selectPageByQuery(M mapper, RE example, C
            criteria, Q query, Page page) {
        Field[] fields = query.getClass().getDeclaredFields();
        Class<?> criteriaClass = criteria.getClass();
        if (fields.length > 0) {
            for (Field field : fields) {
                try {
                    Object value = query.getClass().getMethod("get" + StringUtils.capitalize(field.getName())).invoke
                            (query);
                    String methodPrefix = "and" + StringUtils.capitalize(field.getName());
                    if (null != value) {
                        if (value instanceof List) {
                            criteriaClass.getMethod(methodPrefix + "In", List.class).invoke(criteria, value);
                        } else if (value instanceof String) {
                            criteriaClass.getMethod(methodPrefix + "Like", String.class).invoke(criteria, "%" + value
                                    + "%");
                        } else if (value instanceof Number) {
                            if (field.getName().startsWith("min")) {
                                criteriaClass.getMethod(methodPrefix.replace("andMin", "and") +
                                        "GreaterThanOrEqualTo", value.getClass()).invoke(criteria, value);
                            } else if (field.getName().startsWith("max")) {
                                criteriaClass.getMethod(methodPrefix.replace("andMax", "and") + "LessThanOrEqualTo",
                                        value.getClass()).invoke(criteria, value);
                            } else {
                                criteriaClass.getMethod(methodPrefix + "EqualTo", value.getClass()).invoke(criteria,
                                        value);
                            }
                        } else {
                            criteriaClass.getMethod(methodPrefix + "EqualTo", value.getClass()).invoke(criteria, value);
                        }
                    }
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return selectPageByExample(mapper, example, page);
    }

    public static <R, RE, M extends BaseMapper<R, RE>> PageList<R> selectPageByExample(M mapper, RE example, Page
            page) {
        try {
            int totalCnt = mapper.countByExample(example);
            int offset = page.getPageSize() * (page.getPageNo() - 1);
            int limit = page.getPageSize();
            String orderByClause = page.getOrderBy();
            if (null == orderByClause) {
                orderByClause = (String) example.getClass().getMethod("getOrderByClause").invoke(example);
            }
            if (null == orderByClause) {
                orderByClause = "id desc";
            }
            example.getClass().getMethod("setOrderByClause", String.class).invoke(example,
                    String.format("%s limit %d,%d", orderByClause, offset, limit));
            List<R> list = mapper.selectByExample(example);
            return new PageList<>(list, page, totalCnt);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new PageList<>(Collections.emptyList(), page, 0);
        }
    }
}
