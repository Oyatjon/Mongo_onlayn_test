package uz.jl.services;


import uz.jl.dto.GenericBaseDto;
import uz.jl.dto.GenericDto;
import uz.jl.entity.Auditable;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;

import java.io.Serializable;

/**
 * @param <M>
 * @param <CD>
 * @param <UD>
 * @param <K>
 */
public interface GenericCrudService<M extends Auditable, CD extends GenericBaseDto, UD extends GenericDto, K extends Serializable> extends GenericService<M, K> {
    ResponseEntity<Data<K>> create(CD dto);

    ResponseEntity<Data<Void>> update(UD dto);

    ResponseEntity<Data<Void>> delete(K id);
}
