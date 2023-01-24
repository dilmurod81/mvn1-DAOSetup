import javax.management.MBeanParameterInfo;
import java.util.List;

public class PaintingService {
    public PaintingDAO paintingDAO;
    public PaintingService(){
        this.paintingDAO = new PaintingDAO();
    }
    public List<Painting> getAllPaintings(){
        return paintingDAO.getAllPainting();
    }
    public List<Painting> getAllPaintingMadeInYear(int year){
        return paintingDAO.getAllPaintingMadeInYear(year);
    }
    public int getOldestPaintingYear(){
        return paintingDAO.getOldestPaintingYear();
    }
    public Painting insertPainting(Painting p){
        paintingDAO.instertPainting(p);
        return p;
    }
}
