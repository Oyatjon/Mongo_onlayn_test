package uz.oyatjon.services;

import uz.oyatjon.mappers.GenericBaseMapper;
import uz.oyatjon.respository.AbstractRepository;

public class AbstractService<R extends AbstractRepository, MAP extends GenericBaseMapper> {

    protected final R repository;
    protected final MAP mapper;

    public AbstractService(R repository, MAP mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
