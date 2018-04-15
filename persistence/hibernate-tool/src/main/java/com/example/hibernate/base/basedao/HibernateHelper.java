package com.example.hibernate.base.basedao;

import java.sql.Connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class HibernateHelper {
	
	private Session session;
	private Transaction transaction;
	
	public HibernateHelper(SessionFactory sessionFactory) {
		session = sessionFactory.getCurrentSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
	}
	
	/**
	 * 开起事务
	 */
	public void beginTransaction(){
		transaction = session.beginTransaction();
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConnection(Connection connection){
		session.reconnect(connection);
		return connection;
	}
	
	/**
	 * 提交事物
	 * @throws Exception
	 */
	public void commit() throws Exception{
		TransactionSynchronizationManager.unbindResource(session.getSessionFactory());
		transaction.commit();
		session.close();
	}
	
	public void rollback() throws Exception{
		Object obj = TransactionSynchronizationManager.getResource(session.getSessionFactory());
		if(obj != null){
			TransactionSynchronizationManager.unbindResource(session.getSessionFactory());
		}
		transaction.rollback();
		close();
	}
	
	public void close(){
		Object obj = TransactionSynchronizationManager.getResource(session.getSessionFactory());
		if(obj != null){
			TransactionSynchronizationManager.unbindResource(session.getSessionFactory());
		}
		if(session.isOpen()){
			session.close();
		}
	}

}
