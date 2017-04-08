package com.greencode.petfinder.data.entity.mappers;

import java.util.List;

import rx.Observable;

/**
 * Abstract Mapper
 * I is input parameter to be converted
 * O is output
 *
 * @author Anton Kazakov
 * @date 01.04.17.
 */

public interface Mapper<I, O> {

    O transform(I i);

    List<O> transform(List<I> iList);

}
