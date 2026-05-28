package com.app.threetier.mybatis.handler;

import com.app.threetier.common.enumeration.Status;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Status.class)
public class StatusHandler implements TypeHandler<Status> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Status parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Status getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "active":
                return Status.ACTIVE;
            case "inactive":
                return Status.INACTIVE;
        }
        return null;
    }

    @Override
    public Status getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "active":
                return Status.ACTIVE;
            case "inactive":
                return Status.INACTIVE;
        }
        return null;
    }

    @Override
    public Status getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "active":
                return Status.ACTIVE;
            case "inactive":
                return Status.INACTIVE;
        }
        return null;
    }
}
