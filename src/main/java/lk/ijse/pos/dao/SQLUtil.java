package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Getter
public class SQLUtil {
  public static <T>T execute(String sql, Object... args) throws SQLException {
      Connection conn = DBConnection.getInstance().getConnection();
      PreparedStatement pstm = conn.prepareStatement(sql);

      for (int i = 0; i < args.length; i++) {
          pstm.setObject(i + 1, args[i]);
      }

      if(sql.startsWith("SELECT") || sql.startsWith("select")){
          return (T)pstm.executeQuery();
      }else{
          return (T)(Boolean)(pstm.executeUpdate()>0);
      }

  }
}










