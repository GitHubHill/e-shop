package com.hill.eshop.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JSON Array 转 int 陣列
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(int[].class)
public class Int1DArrayHandler extends BaseTypeHandler<int[]> {

    private static ObjectMapper mapper;

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, int[] ints, JdbcType jdbcType) throws SQLException {
        try {
            preparedStatement.setString(i, mapper.writeValueAsString(ints));
        } catch (JsonProcessingException e) {
            throw new SQLException(String.format("%s. %s. %s", getClass().getSimpleName(), "JsonProcessingException", e.getMessage()));
        }
    }

    @Override
    public int[] getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        try {
            return mapper.readValue(resultSet.getString(columnName), int[].class);
        } catch (IOException e) {
            throw new SQLException(String.format("%s. %s. %s", getClass().getSimpleName(), "JsonProcessingException", e.getMessage()));
        }
    }

    @Override
    public int[] getNullableResult(ResultSet resultSet, int i) throws SQLException {
        try {
            return mapper.readValue(resultSet.getString(i), int[].class);
        } catch (IOException e) {
            throw new SQLException(String.format("%s. %s. %s", getClass().getSimpleName(), "JsonProcessingException", e.getMessage()));
        }
    }

    @Override
    public int[] getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        try {
            return mapper.readValue(callableStatement.getString(i), int[].class);
        } catch (IOException e) {
            throw new SQLException(String.format("%s. %s. %s", getClass().getSimpleName(), "s", e.getMessage()));
        }
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static void setMapper(ObjectMapper mapper) {
        Int1DArrayHandler.mapper = mapper;
    }

}
