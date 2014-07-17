package org.invoices.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.invoices.service.GenericDao;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class HibernateDao<E, K extends Serializable>  implements GenericDao<E, K>{
	
	private SessionFactory sessionFactory;
	protected Class<? extends E> daoType;
	
	public HibernateDao(){
		//Java doesn’t have generics at runtime, only at compile time, 
		//so it prevents us from writing something like E.class
		daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	protected Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void add(E entity) {
		currentSession().save(entity);
	}

	public void update(E entity) {
		currentSession().saveOrUpdate(entity);
	}

	public void remove(E entity) {
		currentSession().delete(entity);
	}

	public E find(K key) {
		return (E) currentSession().get(daoType, key);
	}

	public List<E> list(int pageIndex, int pageSize) {
		return currentSession().createCriteria(daoType).list();
	}
	
	public long count(){
		return 0;
	}

}
