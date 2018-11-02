package com.lhsystems.module.datageneratorancillary.service.repository;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data repository used for save baggage class entities.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Transactional
public interface BaggageClassRepository
extends ServiceBaseRepository<BaggageClass> {

}
