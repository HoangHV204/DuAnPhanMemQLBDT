package Control;

import Model.DiemSV;
import Model.SinhVien;
import com.vn.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QLDiem {
    public static List<DiemSV> ls = new ArrayList<>();
    
    
    
    public int add(DiemSV d) {
        ls.add(d);
        return 1;
    }
    
    public List<DiemSV> getAllDiemSV() {
        ArrayList<DiemSV> list = new ArrayList<>();
        String sql = "SELECT dbo.Grade.MaSV, dbo.Students.Hoten, "
                + "dbo.Grade.Tienganh, dbo.Grade.Tinhoc, dbo.Grade.GDTC"
                + "FROM dbo.Grade INNER JOIN dbo.Students ON dbo.Grade.MaSV = dbo.Students.MaSV";
        
        try(Connection conn = DBUtils.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                DiemSV dsv = new DiemSV();
                dsv.setSv(new SinhVien(rs.getString(1), rs.getString(2)));
                dsv.setTiengAnh(rs.getDouble(3));
                dsv.setTinHoc(rs.getDouble(4));
                dsv.setGdtc(rs.getDouble(5));
                list.add(dsv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public DiemSV getDiemSVByID(String maSV) {
        String sql = "SELECT dbo.Grade.MaSV, dbo.Students.Hoten, dbo.Grade.Tienganh, dbo.Grade.";
        
        try(Connection conn = DBUtils.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maSV);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                DiemSV dsv = new DiemSV();
                dsv.setSv(new SinhVien(rs.getString(1), rs.getString(2)));
                dsv.setTiengAnh(rs.getDouble(3));
                dsv.setTinHoc(rs.getDouble(4));
                dsv.setGdtc(rs.getDouble(5));
                
                return dsv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int updateDiem(DiemSV dNew) {
        for (DiemSV d : ls) {
            if (d.getSv().getMaSV().equalsIgnoreCase(dNew.getSv().getMaSV())) {
                d.setTiengAnh(dNew.getTiengAnh());
                d.setTinHoc(dNew.getTinHoc());
                d.setGdtc(dNew.getGdtc());
                return 1;
            }
        }
        return -1;
    }
    
//    public int deleteDiem(String masv) {
//        DiemSV d = getOneDiembyMaSV(masv);
//        if (d != null) {
//            ls.remove(d);
//            return 1;
//        }
//        return -1;
//    }
}
