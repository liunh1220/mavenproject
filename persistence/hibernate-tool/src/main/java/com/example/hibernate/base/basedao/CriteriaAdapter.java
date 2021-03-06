package com.example.hibernate.base.basedao;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

public class CriteriaAdapter {
	

	private Session session;
	private org.hibernate.Criteria criteria;
	
	
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public CriteriaAdapter(Session session,Class clazz,Criterion... criterions){
		this.session = session;
		criteria = this.session.createCriteria(clazz);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
	}

	public org.hibernate.Criteria getCriteria() {
		return criteria;
	}

	public Session getSession() {
		return session;
	}
}
