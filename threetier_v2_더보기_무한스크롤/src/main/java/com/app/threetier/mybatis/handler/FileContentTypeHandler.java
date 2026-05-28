package com.app.threetier.mybatis.handler;

import com.app.threetier.common.enumeration.FileContentType;
import com.app.threetier.common.enumeration.Status;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(FileContentType.class)
public class FileContentTypeHandler implements TypeHandler<FileContentType> {

    @Override
    public void setParameter(PreparedStatement ps, int i, FileContentType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public FileContentType getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "image":
                return FileContentType.IMAGE;
            case "other":
                return FileContentType.OTHER;
        }
        return null;
    }

    @Override
    public FileContentType getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "image":
                return FileContentType.IMAGE;
            case "other":
                return FileContentType.OTHER;
        }
        return null;
    }

    @Override
    public FileContentType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "image":
                return FileContentType.IMAGE;
            case "other":
                return FileContentType.OTHER;
        }
        return null;
    }
}
