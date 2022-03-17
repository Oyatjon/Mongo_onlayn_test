package uz.jl.services;

import uz.jl.entity.Auditable;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public interface GenericService<M extends Auditable, K extends Serializable> {
    ResponseEntity<Data<M>> get(K id);

    ResponseEntity<Data<List<M>>> getList();
}
