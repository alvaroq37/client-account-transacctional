package branch.dao.repository;

import branch.dao.data.Department;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class DepartmentRepository implements PanacheRepository<Department> {
    public List<Department> listAllDepartment() {
        return listAll();
    }

    public void saveDepartment(Department department) {
        persist(department);
    }

    public Department findDepartmentById(Long id) {
        return find("id", id).firstResult();
    }
}
