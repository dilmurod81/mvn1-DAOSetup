import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaintingDAO {
    Connection conn;
    public PaintingDAO(){
        try{
            conn = DriverManager.getConnection("jdbc:h2:./h2/db");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void dropPaintingTable(){
        try{
            PreparedStatement ps = conn.prepareStatement("drop table painting");
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void createPaintingTable(){
        try{
            PreparedStatement ps = conn.prepareStatement("create table painting(title varchar(255), year_made int)");
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void instertPainting(Painting painting){

        try{
            PreparedStatement ps = conn.prepareStatement("insert into painting (title, year_made) values (?, ?)");
            ps.setString(1, painting.title);
            ps.setInt(2, painting.year_made);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Painting> getAllPainting(){
        try {
            PreparedStatement ps = conn.prepareStatement("select * from painting");
            ResultSet rs = ps.executeQuery();
            List<Painting> allPaintings = new ArrayList<>();
            while(rs.next()){
                Painting newPainting = new Painting(rs.getString("title"), rs.getInt("year_made"));
                allPaintings.add(newPainting);
            }
            return allPaintings;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getOldestPaintingYear(){
        try{
            PreparedStatement ps = conn.prepareStatement("select min(year_made) as oldest_year from painting");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int oldestYear = rs.getInt("oldest_year");
                return oldestYear;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<Painting> getAllPaintingMadeInYear(int year){
        try {
//          we can cancat like the following way
//          PreparedStatement ps = conn.prepareStatement("select * from painting where year_made = '+year+'");
            PreparedStatement ps = conn.prepareStatement("select * from painting where year_made = ?");
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            List<Painting> allPaintings = new ArrayList<>();
            while(rs.next()){
                Painting newPainting = new Painting(rs.getString("title"), rs.getInt("year_made"));
                allPaintings.add(newPainting);
            }
            return allPaintings;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
