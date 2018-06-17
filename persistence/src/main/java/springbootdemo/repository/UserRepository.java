package springbootdemo.repository;

import org.springframework.stereotype.Repository;
import springbootdemo.model.User;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 81046 on 2018-06-16
 */
@Repository
public class UserRepository {

    /**
     * 采用内存型的存储方式-->Map
     */
    private final ConcurrentMap<Integer,User> repository = new ConcurrentHashMap<>();

    /**
     * id生成器
     */
    private final static AtomicInteger idGennerator = new AtomicInteger();

    /**
     * 保存用户对象
     */
    public boolean save(User user){
        int id = idGennerator.incrementAndGet();
        user.setId(id);
        return repository.put(id,user) == null ;
    }

    /**
     * 查询所有的用户
     * @return
     */
    public Collection<User> findAll(){
        return repository.values();
    }
}
