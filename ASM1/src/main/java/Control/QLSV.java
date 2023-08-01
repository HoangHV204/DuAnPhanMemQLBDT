package Control;

import Model.SinhVien;
import com.vn.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QLSV {
    
    public boolean addSV(SinhVien sv) {
        String sql = "INSERT INTO Students(MaSV, Hoten, Email, SoDT, Gioitinh, Diachi, Hinh)"
                + "VALUES(?,?,?,?,?,?,?)";
        try(Connection conn = DBUtils.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sv.getMaSV());
            ps.setString(2, sv.getHoTen());
            ps.setString(3, sv.getEmail());
            ps.setString(4, sv.getSoDT());
            ps.setBoolean(5, sv.isGioiTinh());
            ps.setString(6, sv.getDiaChi());
            ps.setString(7, sv.getHinh());
            
            return ps.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
        public boolean updateSV(SinhVien sv) {
        String sql = "UPDATE Students SET Hoten = ?, Email = ?, SoDT = ?, Gioitinh = ?, Diachi = ?, Hinh = ? WHERE MaSV = ?";
        try(Connection conn = DBUtils.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(7, sv.getMaSV());
            ps.setString(1, sv.getHoTen());
            ps.setString(2, sv.getEmail());
            ps.setString(3, sv.getSoDT());
            ps.setBoolean(4, sv.isGioiTinh());
            ps.setString(5, sv.getDiaChi());
            ps.setString(6, sv.getHinh());
            
            return ps.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteSV(String maSV) {
        String sql = "DELETE FROM Students WHERE MaSV = ?";
        try(Connection conn = DBUtils.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maSV);
            
            return ps.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<SinhVien> getListStudent() {
        ArrayList<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        
        try(Connection conn = DBUtils.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                SinhVien sv = new SinhVien();
                sv.setMaSV(rs.getString(1));
                sv.setHoTen(rs.getString(2));
                sv.setEmail(rs.getString(3));
                sv.setSoDT(rs.getString(4));
                sv.setGioiTinh(rs.getBoolean(5));
                sv.setDiaChi(rs.getString(6));
                sv.setHinh(rs.getString(7));
                
                list.add(sv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public SinhVien getSinhVienByID(String maSV) {
        String sql = "SELECT * FROM Students WHERE MaSV = ?";
        
        try(Connection conn = DBUtils.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maSV);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                SinhVien sv = new SinhVien();
                sv.setMaSV(rs.getString(1));
                sv.setHoTen(rs.getString(2));
                sv.setEmail(rs.getString(3));
                sv.setSoDT(rs.getString(4));
                sv.setGioiTinh(rs.getBoolean(5));
                sv.setDiaChi(rs.getString(6));
                sv.setHinh(rs.getString(7));
                
                return sv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
}
