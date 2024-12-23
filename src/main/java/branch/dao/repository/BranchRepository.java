package branch.dao.repository;

import branch.dao.data.Branch;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class BranchRepository implements PanacheRepository<Branch> {
    public List<Branch> agencyListAll(){
        return listAll();
    }
    public Branch agencyFindById(Long id){
        return find("id", id).firstResult();
    }
    public Branch agencyFindByName(String name){
        return find("name", name).firstResult();
    }
    public List<Branch> agencyFindByCity(Long id){
        return list("city_id", id).stream().toList();
    }
    public void agencySave(Branch branch){
        persist(branch);
    }
    public Long agencyDelete(long id) {
        return delete("id", id);
    }
    public void agencyUpdate(Branch branch) {
        persist(branch);
    }
}
