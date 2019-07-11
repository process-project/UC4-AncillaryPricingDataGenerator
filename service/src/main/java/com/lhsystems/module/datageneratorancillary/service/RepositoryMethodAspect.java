package com.lhsystems.module.datageneratorancillary.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * The type Repository method aspect.
 */
@Component
@Aspect
public class RepositoryMethodAspect {

    /**
     * Pointcut aspect for all save methods in package where are store spring data repositories.
     */
    @Pointcut("execution(* com.lhsystems.module.datageneratorancillary.service.repository.*.save(*))")
    public void repositorySave() {
    }


    /**
     * Pointcut aspect for all exists methods in package where are store spring data repositories.
     */
    @Pointcut("execution(* com.lhsystems.module.datageneratorancillary.service.repository.*.exists(*))")
    public void repositoryExists() {
    }


    /**
     * Check if flag to save database is set, if yes, the join point is proceed, otherwise return null
     *
     * @param pjp proceeding join point
     * @return proceed if flag set, otherwise null
     * @throws Throwable if any error
     */
    @Around("repositorySave()")
    public Object stopSavingIfParamNotSet(final ProceedingJoinPoint pjp) throws Throwable {
        String saveToDatabase = System.getenv("SAVE_TO_DATABASE");

        if (Boolean.valueOf(saveToDatabase)) {
            Object[] args = pjp.getArgs();
            return pjp.proceed(args);
        }
        return null;
    }

    /**
     * Check if flag to save database is set, if yes, the join point is proceed, otherwise return false
     *
     * @param pjp proceeding join point
     * @return proceed if flag set, otherwise false
     * @throws Throwable if any error
     */
    @Around("repositoryExists()")
    public Object handleExistsIfParamNotSet(final ProceedingJoinPoint pjp) throws Throwable {
        String saveToDatabase = System.getenv("SAVE_TO_DATABASE");

        if (Boolean.valueOf(saveToDatabase)) {
            Object[] args = pjp.getArgs();
            return pjp.proceed(args);
        }
        return false;
    }

}



