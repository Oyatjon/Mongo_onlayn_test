package uz.jl.services;

import uz.jl.mappers.GenericBaseMapper;
import uz.jl.respository.AbstractRepository;

public class AbstractService<R extends AbstractRepository, MAP extends GenericBaseMapper> {

    protected final R repository;
    protected final MAP mapper;

    public AbstractService(R repository, MAP mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
