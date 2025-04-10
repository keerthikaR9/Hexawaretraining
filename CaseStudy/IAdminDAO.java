package dao;

import java.util.List;
import exception.AdminNotFoundException;
import entity.Admin;

public interface IAdminDAO {
	Admin getAdminById(int adminId) throws AdminNotFoundException;
    Admin getAdminByUsername(String username) throws AdminNotFoundException;
    boolean registerAdmin(Admin admin);
    boolean updateAdmin(Admin admin);
    boolean deleteAdmin(int adminId);
}
