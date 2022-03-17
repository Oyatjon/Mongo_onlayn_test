package uz.jl.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import uz.jl.configs.ApplicationContextHolder;
import uz.jl.criteria.GenericCriteria;
import uz.jl.entity.Auditable;
import uz.jl.respository.AbstractRepository;

/**
 * THIS IS A BASE ABSTRACT CLASS THAT PROVIDES CONNECTION WITH MONGO DB
 *
 * @param <C> -> Criteria
 * @param <M> -> Collection
 */
public abstract class GenericDao<C extends GenericCriteria, M extends Auditable> extends AbstractRepository {
    protected MongoDatabase db = ApplicationContextHolder.getBean(MongoDatabase.class);
    protected MongoCollection<M> collection;

    public GenericDao(Class<M> clazz) {
        this.collection = db.getCollection(clazz.getSimpleName(), clazz);
    }
}
