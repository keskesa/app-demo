/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/repository/support/GenericRepository.p.vm.java
 */
package com.app.demo.repository.support;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.transaction.annotation.Transactional;

import com.app.demo.dao.support.GenericDao;
import com.app.demo.dao.support.SearchParameters;
import com.app.demo.domain.Identifiable;

/**
 * The interface to manipulate entities against a repository
 * @see Identifiable
 * @see SearchParameters
 * @see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/dao.html
 */
public abstract class GenericRepository<T extends Identifiable<PK>, PK extends Serializable> {

    private Logger log;
    private GenericDao<T, PK> dao;

    // required by cglib to create a proxy around the object we are using @Transactional
    protected GenericRepository() {
        super();
    }

    public GenericRepository(GenericDao<T, PK> dao) {
        this.log = LoggerFactory.getLogger(getClass());
        this.dao = dao;
    }

    /**
     * Create a new instance. This method may be convenient when you need to instantiate an entity from components like a Spring Web Flow script.
     *
     * @return a new instance with no property set.
     */
    public abstract T getNew();

    /**
     * Creates a new instance and initializes it with some default values.
     * This method may be convenient when you need to instantiate
     * an entity from a Spring Web Flow script.
     *
     * @return a new instance initialized with default values.
     */
    public abstract T getNewWithDefaults();

    /**
     * Save or update the passed entity. 
     * Use it with conversation and extended persistence context.
     *
     * @param entity the entity to save or update.
     */
    @Transactional
    public void save(T model) {
        dao.save(model);
    }

    /**
     * Persist the passed entity.
     *
     * @param entity to be inserted.
     */
    @Transactional
    public void persist(T model) {
        dao.persist(model);
    }

    /**
     * Merge the state of the given entity into the persistence context.
     *
     * @param entity the entity to merge.
     */
    @Transactional
    public T merge(T model) {
        return dao.merge(model);
    }

    /**
     * Get an entity by its id.
     *
     * @param id the primaryKey
     * @return
     */
    @Transactional(readOnly = true)
    public T getById(PK id) {
        T entity = getNew();
        entity.setId(id);
        return get(entity);
    }

    /**
     * Delete from the repository the entity identified by the passed primary key.
     *
     * @param id the primaryKey identifying the entity to delete.
     */
    @Transactional
    public void deleteById(PK id) {
        delete(getById(id));
    }

    /**
     * Get an entity based on the passed example. If the example has a primary key set, it will be used.
     * Otherwise if the example has unique field set, it will be used to lookup the entity.
     *
     * @param example
     * @return the matching entity or null if none could be found.
     */
    @Transactional(readOnly = true)
    public T get(T model) {
        return dao.get(model);
    }

    /**
     * Delete from the repository the passed entity.
     *
     * @param entity The entity to delete.
     */
    @Transactional
    public void delete(T model) {
        if (model == null) {
            log.debug("Skipping deletion for null model!");
            return;
        }
        dao.delete(model);
    }

    /**
     * Refresh the passed entity with up to date data.
     *
     * @param entity the entity to refresh.
     */
    @Transactional(readOnly = true)
    public void refresh(T entity) {
        dao.refresh(entity);
    }

    /**
     * Find the unique entity matching the passed example.
     *
     * @param example
     * @return the unique entity found.
     * @throws org.springframework.dao.InvalidDataAccessApiUsageException if no entity or more than one entity is found.
     */
    @Transactional(readOnly = true)
    public T findUnique(T model) {
        return findUnique(model, new SearchParameters());
    }

    /**
     * Find the unique entity matching the passed example and {@link SearchParameters}.
     *
     * @param exampleOrNamedQueryParameters
     * @param searchParameters
     * @return the unique entity found.
     * @throws org.springframework.dao.InvalidDataAccessApiUsageException if no entity or more than one entity is found.
     */
    @Transactional(readOnly = true)
    public T findUnique(T model, SearchParameters sp) {
        return dao.findUnique(model, sp);
    }

    /**
     * Find the unique entity matching the passed example.
     *
     * @param example
     * @return the unique entity found or null if none could be found.
     * @throws org.springframework.dao.InvalidDataAccessApiUsageException if more than one entity is found.
     */
    @Transactional(readOnly = true)
    public T findUniqueOrNone(T model) {
        return findUniqueOrNone(model, new SearchParameters());
    }

    /**
     * Find the unique entity matching the passed example and {@link SearchParameters}.
     *
     * @param example
     * @return the unique entity found or null if none could be found.
     * @throws org.springframework.dao.InvalidDataAccessApiUsageException if more than one entity is found.
     */
    @Transactional(readOnly = true)
    public T findUniqueOrNone(T model, SearchParameters sp) {
        return dao.findUniqueOrNone(model, sp);
    }

    /**
     * Load all the entities, up to a certain limit. Implementation could for example load at most 500 entities.
     *
     * @return the list of matching entities or an empty list when no entity is found.
     */
    @Transactional(readOnly = true)
    public List<T> find() {
        return find(getNew(), new SearchParameters());
    }

    /**
     * Load all the entities matching the passed example.
     *
     * @param example
     * @return the list of matching entities or an empty list when no entity is found.
     */
    @Transactional(readOnly = true)
    public List<T> find(T model) {
        return find(model, new SearchParameters());
    }

    /**
     * Load all the entities based on the passed {@link SearchParameters}..
     *
     * @param searchParameters
     * @return the list of matching entities or an empty list when no entity is found.
     */
    @Transactional(readOnly = true)
    public List<T> find(SearchParameters sp) {
        return find(getNew(), sp);
    }

    /**
     * Load all the entities based on the passed exampleOrNamedQueryParameters and {@link SearchParameters}.
     *
     * @param exampleOrNamedQueryParameters
     * @return the list of matching entities or an empty list when no entity is found.
     */
    @Transactional(readOnly = true)
    public List<T> find(T model, SearchParameters sp) {
        return dao.find(model, sp);
    }

    /**
     * @return the number of entities that {@link #find()} would return.
     */
    @Transactional(readOnly = true)
    public int findCount() {
        return findCount(getNew(), new SearchParameters());
    }

    /**
     * @return the number of entities that {@link #find(Identifiable)} would return.
     */
    @Transactional(readOnly = true)
    public int findCount(T model) {
        return findCount(model, new SearchParameters());
    }

    /**
     * @return the number of entities that  {@link #find(SearchParameters)} would return.
     */
    @Transactional(readOnly = true)
    public int findCount(SearchParameters sp) {
        return findCount(getNew(), sp);
    }

    /**
     * @return the number of entities that {@link #find(Identifiable, SearchParameters)} would return.
     */
    @Transactional(readOnly = true)
    public int findCount(T model, SearchParameters sp) {
        return dao.findCount(model, sp);
    }

    /**
     * Return the optimistic version value, if any.
     */
    public Comparable<Object> getVersion(T model) {
        return dao.getVersion(model);
    }
}