package uz.oyatjon.services;

import uz.oyatjon.entity.Auditable;
import uz.oyatjon.response.Data;
import uz.oyatjon.response.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public interface GenericService<M extends Auditable, K extends Serializable> {
    ResponseEntity<Data<M>> get(K id);

    ResponseEntity<Data<List<M>>> getList();
}
