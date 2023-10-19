package labs.jdbc.executor;

import labs.jdbc.ApplicationDataSource;
import lombok.SneakyThrows;

import java.sql.Statement;

public class SongUpdate {
    private static final String SQL_UPDATE = "update song set name = 'Cold Cold Cold', duration = '3:34', album_id = '4' where id = '10'";

    @SneakyThrows
    public static int songUpdate() {
        Statement statement = ApplicationDataSource.getConnection().createStatement();
        int set = statement.executeUpdate(SQL_UPDATE);
        statement.close();
        return set;
    }
}
