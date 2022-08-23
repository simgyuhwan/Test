package com.kr.board.domain.common.mapper;

import java.util.List;

public interface GenericMapper <D, E>{
    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDto(List<E> e);

    List<E> toEntity(List<D> d);

}
