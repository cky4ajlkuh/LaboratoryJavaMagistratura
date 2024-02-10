package labs1.jdbc.executor;

import labs1.jdbc.ApplicationDataSource;
import labs1.jdbc.TableGenerator;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongSelect {

    private static final String SQL_SELECT = "" +
            "WITH s AS (SELECT album_id, MIN(duration) AS duration_m FROM song\n" +
            "WHERE duration < '5:00:00' GROUP BY album_id)\n" +
            "SELECT album.name, song.name FROM album INNER JOIN s ON album.id = s.album_id\n" +
            "INNER JOIN song ON s.album_id = song.album_id WHERE song.duration = s.duration_m";

    public static void viewAll() {
        try {
            Statement statement = ApplicationDataSource.getConnection().createStatement();
            ResultSet set = statement.executeQuery(SQL_SELECT);
            //ResultSet set = statement.executeQuery("select * from song");
            createTable(set);
            statement.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void createTable(ResultSet set) throws SQLException {
        TableGenerator tableGenerator = new TableGenerator();
        List<String> headersList = new ArrayList<>();
        List<List<String>> rowsList = new ArrayList<>();
        ResultSetMetaData data = set.getMetaData();
        int columnsNumber = data.getColumnCount();
        while (set.next()) {
            List<String> row = new ArrayList<>();
            for (int i = 1; i <= columnsNumber; i++) {
                headersList.add(data.getColumnName(i));
                row.add(set.getString(i));
            }
            rowsList.add(row);
            break;
        }
        while (set.next()) {
            List<String> row = new ArrayList<>();
            for (int i = 1; i <= columnsNumber; i++) {
                row.add(set.getString(i));
            }
            rowsList.add(row);
        }
        System.out.print(tableGenerator.generateTable(headersList, rowsList));
    }
}
