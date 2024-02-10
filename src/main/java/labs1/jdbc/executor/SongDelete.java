package labs1.jdbc.executor;

import labs1.jdbc.ApplicationDataSource;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;

public class SongDelete {
    private static final String SQL_DELETE = "delete from song where id = '10'";

    @SneakyThrows
    public static int songDelete() {
        PreparedStatement stat = ApplicationDataSource.getConnection().prepareStatement(SQL_DELETE);
        int set = stat.executeUpdate();
        stat.close();
        return set;
    }
}
