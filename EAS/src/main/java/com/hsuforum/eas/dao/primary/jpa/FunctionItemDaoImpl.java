package com.hsuforum.eas.dao.primary.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hsuforum.common.dao.jpa.BaseDaoImpl;
import com.hsuforum.eas.dao.primary.FunctionItemDao;
import com.hsuforum.eas.entity.primary.FunctionItem;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@Repository("functionItemDao")
@Slf4j
public class FunctionItemDaoImpl extends BaseDaoImpl<FunctionItem, java.lang.String> implements FunctionItemDao {

	private static final long serialVersionUID = -7835326433773204291L;
	@PersistenceContext(name = "primary")
	private EntityManager entityManager;

	/**
	 * @see com.hsuforum.common.dao.jpa.BaseDaoImpl#getEntityManager()
	 */
	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	/**
	 * @see com.hsuforum.common.dao.jpa.BaseDaoImpl#setEntityManager(jakarta.persistence.EntityManager)
	 */
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	/**
	 * @see com.hsuforum.eas.dao.primary.FunctionItemDao#findAllFetchRelation()
	 */
	@Override
	public List<FunctionItem> findAllFetchRelation() {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT obj FROM FunctionItem obj	");
		queryString.append("ORDER BY obj.id	");

		List<FunctionItem> list = this.find(queryString);

		return list;
	}

}
