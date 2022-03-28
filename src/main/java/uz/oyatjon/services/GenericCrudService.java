package uz.oyatjon.services;


import uz.oyatjon.dto.GenericBaseDto;
import uz.oyatjon.dto.GenericDto;
import uz.oyatjon.entity.Auditable;
import uz.oyatjon.response.Data;
import uz.oyatjon.response.ResponseEntity;

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
