package cn.gc80.base.hibernate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.gc80.base.page.PageHolder;
/** 
 * HibernateDao
 */
@Repository("hibernateDao")
public class HibernateDao {
	
	@Resource
	private HibernateTemplate hibernateTemplate;

	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public Object queryObject(Class theClass, String pk)
			throws HibernateException {
		Object bean = null;
		try {
			//getHibernateTemplate().setCacheQueries(true);
			bean = getHibernateTemplate().get(theClass, pk);
		} catch (HibernateObjectRetrievalFailureException ex) {
			ex.printStackTrace();
		}
		return bean;
	}

	public Object queryObject(Class theClass, long pk)
			throws HibernateException {
		Object bean = null;
		try {
			bean = getHibernateTemplate().get(theClass, new Long(pk));
		} catch (HibernateObjectRetrievalFailureException ex) {
		}
		return bean;
	}

	public List queryObjects(final String hsql) throws HibernateException {
	   HibernateCallback callback = new HibernateCallback() {
	    public Object doInHibernate(Session session)
	      throws HibernateException {
	     Query q = session.createQuery(hsql);
	     List list=q.list();
	     return list;
	    }
	   };
	   return (List) getHibernateTemplate().execute(callback);
	}
	
	public List queryObjects(final String hsql,final Map map) throws HibernateException {
		   HibernateCallback callback = new HibernateCallback() {
		    public Object doInHibernate(Session session)
		      throws HibernateException {
		    Query q = session.createQuery(hsql);
		    Set keySet = map.keySet();
			Iterator it = keySet.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = (String) map.get(key);
				if (value != null && value.trim().length() > 0) {
					q.setString(key, value);
				}
			}
		    List list=q.list();
		    return list;
		    }
		   };
		   return (List) getHibernateTemplate().execute(callback);
		}

	public List queryObjects(String hsql, int pageNumber, int pageSize)
			throws HibernateException {
		//getHibernateTemplate().setCacheQueries(true);
		return queryObjects(hsql, pageNumber, pageSize, false);
	}
	public List queryObjects(String hsql, int pageNumber, int pageSize,Map map)
	throws HibernateException {
//getHibernateTemplate().setCacheQueries(true);
return queryObjects(hsql, pageNumber, pageSize, false,map);
}

	public List queryObjects(String hsql, int pageNumber, int pageSize,
			boolean retainLastOne)  {
		final String sql = hsql;
		final int pgNumber = pageNumber;
		final int pgSize = pageSize;
		HibernateCallback callback = new HibernateCallback() {
	    public Object doInHibernate(Session session) throws HibernateException {
	    	Query q = session.createQuery(sql);
		    if (pgSize <= 0)
				return q.list();
			else {
				int pageNumber = pgNumber;
				if (pageNumber < 1)
					pageNumber = 1;
				int start = (pageNumber - 1) * pgSize;
				q.setFirstResult(start);
				q.setMaxResults(pgSize);
				 List list=q.list();
			    
			     return list;
			}
	    }
		};
	   return (List) getHibernateTemplate().execute(callback);
	}
	
	public List queryObjects(String hsql, int pageNumber, int pageSize,
			boolean retainLastOne,final Map map)  {
		final String sql = hsql;
		final int pgNumber = pageNumber;
		final int pgSize = pageSize;
		HibernateCallback callback = new HibernateCallback() {
	    public Object doInHibernate(Session session) throws HibernateException {
	    	Query q = session.createQuery(sql);
	    	Set keySet = map.keySet();
			Iterator it = keySet.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = (String) map.get(key);
				if (value != null && value.trim().length() > 0) {
					q.setString(key, value);
				}
			}
		    if (pgSize <= 0)
				return q.list();
			else {
				int pageNumber = pgNumber;
				if (pageNumber < 1)
					pageNumber = 1;
				int start = (pageNumber - 1) * pgSize;
				q.setFirstResult(start);
				q.setMaxResults(pgSize);
				 List list=q.list();
			    
			     return list;
			}
	    }
		};
	   return (List) getHibernateTemplate().execute(callback);
	}

	public List querySqlObjects(String hsql, int pageNumber, int pageSize)
			throws HibernateException {
		final String sql = hsql;
		final int pgNumber = pageNumber;
		final int pgSize = pageSize;
		HibernateCallback callback = new HibernateCallback() {
		    public Object doInHibernate(Session session)
		      throws HibernateException {
		    	Query q = session.createSQLQuery(sql);
				if (pgSize <= 0)
					return q.list();
				else {
					int pageNumber = pgNumber;
					if (pageNumber < 1)
						pageNumber = 1;
					int start = (pageNumber - 1) * pgSize;
					// if(pageNumber>1) start--;
					q.setFirstResult(start);
					q.setMaxResults(pgSize);
					return q.list();
				}
		   }
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	public List querySqlObjects(String hsql, int pageNumber, int pageSize,final Map map)
	throws HibernateException {
	final String sql = hsql;
	final int pgNumber = pageNumber;
	final int pgSize = pageSize;
	HibernateCallback callback = new HibernateCallback() {
	    public Object doInHibernate(Session session)
	      throws HibernateException {
	    	Query q = session.createSQLQuery(sql);
	    	Set keySet = map.keySet();
			Iterator it = keySet.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = (String) map.get(key);
				if (value != null && value.trim().length() > 0) {
					q.setString(key, value);
				}
			}
			if (pgSize <= 0)
				return q.list();
			else {
				int pageNumber = pgNumber;
				if (pageNumber < 1)
					pageNumber = 1;
				int start = (pageNumber - 1) * pgSize;
				// if(pageNumber>1) start--;
				q.setFirstResult(start);
				q.setMaxResults(pgSize);
				return q.list();
			}
	   }
	};
	return (List) getHibernateTemplate().execute(callback);
}
	
	public List queryObjects(Class theClass, int pageNumber, int pageSize,
			String where, String orderBy) throws HibernateException {
		throw new UnsupportedOperationException(
				"Please use queryObjects(String hsql) instead.");
	}

	public List queryObjects(Class theClass, int pageNumber, int pageSize)
			throws HibernateException {
		//getHibernateTemplate().setCacheQueries(true);
		String sql = "from " + theClass.getName() + " as " + theClass.getName();
		return queryObjects(sql, pageNumber, pageSize, true);
	}

	public List queryObjects(Class theClass, int pageNumber, int pageSize,
			boolean retainLastOne) throws HibernateException {
		//getHibernateTemplate().setCacheQueries(true);
		String sql = "from " + theClass.getName() + " as " + theClass.getName();
		return queryObjects(sql, pageNumber, pageSize, retainLastOne);
	}

	public List queryObjects(Class theClass) throws HibernateException {
		//getHibernateTemplate().setCacheQueries(true);
		String sql = "from " + theClass.getName();
		return queryObjects(sql);
	}

	public long queryObjectCount(Class theClass) throws HibernateException {
		if (theClass == null)
			return 0;
		//getHibernateTemplate().setCacheQueries(true);
		String strHql = "select count(*) from " + theClass.getName();

		return queryObjectCount(strHql);

	}
	public long queryObjectCount(String hsql) throws HibernateException {
		//getHibernateTemplate().setCacheQueries(true);
		if (hsql == null || hsql.trim().length() == 0) {
			return 0;
		} else if (!hsql.toLowerCase().trim().startsWith("select count(")) {
			if (hsql.toLowerCase().trim().startsWith("from")) {
				hsql = "select count(*) " + hsql;
			} else {
				String example = "select count(*) from LinkagePatch as lp where lp.fileName is not null";
				throw new IllegalArgumentException(
						"Illegal hibernate sql for query object count,example: "
								+ example);
			}
		}
		final String strHql = hsql;

		Integer result = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query q = session.createQuery(strHql);
						List list = q.list();
						return Integer.parseInt(list.get(0).toString());
					}
				});

		return result.longValue();

	}
	
	public long queryObjectCount(String hsql,final Map map) throws HibernateException {
		//getHibernateTemplate().setCacheQueries(true);
		if (hsql == null || hsql.trim().length() == 0) {
			return 0;
		} else if (!hsql.toLowerCase().trim().startsWith("select count(")) {
			if (hsql.toLowerCase().trim().startsWith("from")) {
				hsql = "select count(*) " + hsql;
			} else {
				String example = "select count(*) from LinkagePatch as lp where lp.fileName is not null";
				throw new IllegalArgumentException(
						"Illegal hibernate sql for query object count,example: "
								+ example);
			}
		}
		final String strHql = hsql;

		Integer result = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query q = session.createQuery(strHql);
						Set keySet = map.keySet();
						Iterator it = keySet.iterator();
						while (it.hasNext()) {
							String key = (String) it.next();
							String value = (String) map.get(key);
							if (value != null && value.trim().length() > 0) {
								q.setString(key, value);
							}
						}
						List list = q.list();
						return Integer.parseInt(list.get(0).toString());
					}
				});

		return result.longValue();

	}

	public String updateObject(Object bean) throws HibernateException {
		if (bean != null) {
			Object obj = getHibernateTemplate().merge(bean);
			getHibernateTemplate().flush();
			return obj.toString();
		}
		return "";
	}

	public void deleteObject(Object bean) throws HibernateException {
		if (bean != null) {
			getHibernateTemplate().delete(bean);
			getHibernateTemplate().flush();
		}
		return;
	}

	public void deleteObject(String hsql,Map map) throws HibernateException {
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Query q = session.createQuery(hsql);
			Set keySet = map.keySet();
			Iterator it = keySet.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = (String) map.get(key);
				if (value != null && value.trim().length() > 0) {
					q.setString(key, value);
				}
			}
			q.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return;
	}
	
	public void deleteObject(String hsql) throws HibernateException {
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			session.createQuery(hsql).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return;
	}

	public String insertObject(Object bean) throws HibernateException {
		if (bean != null) {
			String id = getHibernateTemplate().save(bean).toString();
			getHibernateTemplate().flush();
			return id;
		}

		return "";
	}

	public PageHolder executePage(final String counthql, final String hql,
			int intPage, final int intPageSize) throws HibernateException {
		PageHolder ph = new PageHolder();
		//getHibernateTemplate().setCacheQueries(true);
		final int totalRecord;
		List listcount=this.getHibernateTemplate().find(counthql);
		if(listcount==null||listcount.isEmpty()||listcount.get(0)==null){
			 totalRecord =0;
		}else{
			totalRecord= Integer.parseInt(this.getHibernateTemplate().find(counthql).iterator().next().toString());
		}
		int totalPage1 = 0;
		if (totalRecord % intPageSize == 0) {
			totalPage1 = totalRecord / intPageSize;
		} else {
			totalPage1 = totalRecord / intPageSize + 1;
		}
		final int totalPage = totalPage1;
		if (intPage > totalPage)
			intPage = totalPage;
		if (intPage <= 0)
			intPage = 0;
		final int currentPage = intPage;
		ph.setRecordNum(totalRecord);
		ph.setPageSize(intPageSize);
		ph.setPageNumber(currentPage);
		ph.setTotalPage(totalPage);
		HibernateCallback callback = new HibernateCallback(){
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						List list = new ArrayList();
						int firstResult = 0;
						if (currentPage > 1) {
							firstResult = (currentPage - 1) * intPageSize;
						}
						if (totalRecord > 0) {
							Query query = s.createQuery(hql);
							query.setFirstResult(firstResult);
							query.setMaxResults(intPageSize);
							list = query.list();
						}
						return list;
					}
				};
				List list=(List) getHibernateTemplate().execute(callback);
		ph.setDataList(list);
		return ph;
	}
	
	public PageHolder executePage(final String counthql, final String hql,
			int intPage, final int intPageSize,final Map map) throws HibernateException {
		PageHolder ph = new PageHolder();
		//getHibernateTemplate().setCacheQueries(true);
		final int totalRecord;
		List listcount=this.queryObjects(counthql,map);
		if(listcount==null||listcount.isEmpty()||listcount.get(0)==null){
			 totalRecord =0;
		}else{
			totalRecord= Integer.parseInt(listcount.iterator().next().toString());
		}
		int totalPage1 = 0;
		if (totalRecord % intPageSize == 0) {
			totalPage1 = totalRecord / intPageSize;
		} else {
			totalPage1 = totalRecord / intPageSize + 1;
		}
		final int totalPage = totalPage1;
		if (intPage > totalPage)
			intPage = totalPage;
		if (intPage <= 0)
			intPage = 0;
		final int currentPage = intPage;
		ph.setRecordNum(totalRecord);
		ph.setPageSize(intPageSize);
		ph.setPageNumber(currentPage);
		ph.setTotalPage(totalPage);
		HibernateCallback callback = new HibernateCallback(){
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						List list = new ArrayList();
						int firstResult = 0;
						if (currentPage > 1) {
							firstResult = (currentPage - 1) * intPageSize;
						}
						if (totalRecord > 0) {
							Query query = s.createQuery(hql);
							Set keySet = map.keySet();
							Iterator it = keySet.iterator();
							while (it.hasNext()) {
								String key = (String) it.next();
								String value = (String) map.get(key);
								if (value != null && value.trim().length() > 0) {
									query.setString(key, value);
								}
							}
							query.setFirstResult(firstResult);
							query.setMaxResults(intPageSize);
							list = query.list();
						}
						return list;
					}
				};
				List list=(List) getHibernateTemplate().execute(callback);
		ph.setDataList(list);
		return ph;
	}
	
	public int executeSql(final String hsql,final Map map) throws HibernateException,
		SQLException {
	//getHibernateTemplate().setCacheQueries(true);
	int num = ((Integer) this.getHibernateTemplate().execute(
			new HibernateCallback() {
				public Object doInHibernate(Session s)
						throws HibernateException, SQLException {
					int deletedEntities = 0;
					try {
						Query query = s.createQuery(hsql);
						Set keySet = map.keySet();
						Iterator it = keySet.iterator();
						while (it.hasNext()) {
							String key = (String) it.next();
							String value = (String) map.get(key);
							if (value != null && value.trim().length() > 0) {
								query.setString(key, value);
							}
						}
						deletedEntities = query.executeUpdate();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//s.flush();
					s.close();
					return new Integer(deletedEntities);
				}
			})).intValue();
	return num;
	}

	public int executeSql(final String hsql) throws HibernateException,
			SQLException {
		//getHibernateTemplate().setCacheQueries(true);
		int num = ((Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						int deletedEntities = 0;
						try {
							deletedEntities = s.createQuery(hsql).executeUpdate();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//s.flush();
						s.close();
						return new Integer(deletedEntities);
					}
				})).intValue();
		return num;
	}
	public int executeBySql(final String sql)  {
		//getHibernateTemplate().setCacheQueries(true);
		int num = ((Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
					throws HibernateException, SQLException {
						int deletedEntities = 0;
						try {
							deletedEntities = s.createSQLQuery(sql).executeUpdate();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//s.flush();
						s.close();
						return new Integer(deletedEntities);
					}
				})).intValue();
		return num;
	}

	public int executeBatch(final Vector ve) throws HibernateException {
		int num = ((Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						int deletedEntities = 0;
						for (int i = 0; i < ve.size(); i++) {
							deletedEntities += s
									.createQuery((String) ve.get(i))
									.executeUpdate();
						}
						return new Integer(deletedEntities);
					}
				})).intValue();
		return num;
	}

	public String formCountSql(Map cond, String ClassName) {
		String hsql = "select count(*) from " + ClassName + " where 1>0";
		Set keySet = cond.keySet();
		Iterator it = keySet.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = (String) cond.get(key);
			hsql = hsql + " and " + key + "='" + value + "'";
		}
		return hsql;
	}

	public String formSql(Map cond, String ClassName) {
		String hsql = "from " + ClassName + " where 1>0";
		Set keySet = cond.keySet();
		Iterator it = keySet.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = (String) cond.get(key);
			if (value != null && value.trim().length() > 0) {
				hsql = hsql + " and " + key + " like '%" + value + "%'";
			}
		}
		return hsql;
	}

	public String formSql(Map cond, String ClassName, String orderby) {
		String hsql = "from " + ClassName + " where 1>0";
		Set keySet = cond.keySet();
		Iterator it = keySet.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = (String) cond.get(key);
			hsql = hsql + " and " + key + " like '%" + value + "%'";
		}
		if (orderby != null && !orderby.equals("")) {
			hsql = hsql + " order by " + orderby;
		}

		return hsql;
	}
}

