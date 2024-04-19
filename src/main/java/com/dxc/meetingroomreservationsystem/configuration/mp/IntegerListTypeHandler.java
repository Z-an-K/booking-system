package com.dxc.meetingroomreservationsystem.configuration.mp;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.context.annotation.Configuration;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class IntegerListTypeHandler extends BaseTypeHandler<List<Integer>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Integer> parameter, JdbcType jdbcType) throws SQLException {
        String str = parameter.stream().map(Object::toString).collect(Collectors.joining(","));
        ps.setString(i, str);
    }

    public List<Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if(StringUtils.isBlank(str)) return new ArrayList<>();
        str = str.replace("\"","").replace("\'","").replace("[","").replace("]","");
        return Arrays.stream(str.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        return Arrays.stream(str.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        return Arrays.stream(str.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }
}
