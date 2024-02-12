package service;

import java.util.List;
import java.util.Map;

//제너릭 인터페이스
public interface DaoService<T> {

	void close();
	List<T> selectList(Map map, String bbstype);
	T selectOne(String ... params);
	int getTotalRecordCount(Map map);
	int insert(T dto);
	int update(T dto);
	int delete(T dto);
}
