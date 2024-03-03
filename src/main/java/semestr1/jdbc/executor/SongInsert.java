package semestr1.jdbc.executor;

import semestr1.jdbc.ApplicationDataSource;
import lombok.SneakyThrows;

import java.sql.Statement;

public class SongInsert {

    private static final String SQL_INSERT = "insert into song(name, album_id, duration) values('HELP', '3', '1:00:00');";

    @SneakyThrows
    public static int songInsert() {
        Statement statement = ApplicationDataSource.getConnection().createStatement();
        int set = statement.executeUpdate(SQL_INSERT);
        statement.close();
        return set;
    }
}
