package uz.oyatjon.respository;

import uz.oyatjon.entity.Auditable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericRepository<M extends Auditable, K extends Serializable> {
    List<M> list();

    Optional<M> get(K id);
}
