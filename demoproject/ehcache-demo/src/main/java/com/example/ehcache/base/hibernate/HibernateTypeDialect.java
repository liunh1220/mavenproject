package com.example.ehcache.base.hibernate;

import java.sql.Types;
import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

/**
 * 该类主要处理hibernate 长文本类型导致的问题（inner join）
 * 
 * @author Administrator
 * 
 */
public class HibernateTypeDialect extends MySQLDialect {
	
	@SuppressWarnings("null")
	public HibernateTypeDialect() {
		super();
		Type type = null;
		registerHibernateType(Types.LONGVARCHAR, type.getName());
		registerFunction("bitwise_and", new BitwiseAndSQLFunction("bitwise_and", new LongType()));
	}
	
	/**
	 * 位与函数
	 * 
	 * @author 杨梦清
	 *
	 */
	class BitwiseAndSQLFunction extends StandardSQLFunction {

		public BitwiseAndSQLFunction(String name, Type type) {
			super(name, type);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public String render(Type firstArgumentType, List list, SessionFactoryImplementor sessionFactory) {
			if (list.size() != 2) {
				throw new QueryException("bitwise_and function must be specified 2 arguments");
			}
			return list.get(0) + " & " + list.get(1);
		}
	}
}
