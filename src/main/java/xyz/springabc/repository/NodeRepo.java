package xyz.springabc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import xyz.springabc.domin.Node;

public interface NodeRepo extends JpaRepository<Node, Integer>{
	Node findByName(String name);
	
	/**
	 * 按照分类查找节点
	 * @param sectionName 分类名
	 * @return
	 */
	@Query(value="select n.* from node n left join section s "
			+"on n.section_id=s.id "
			+"where s.name=?1",nativeQuery=true)
	List<Node> findBySection(String sectionName);
	
	@Query("SELECT n FROM Node n "
			+"ORDER BY n.topicCount DESC")
	Page<Node> findAllOrderByTopicCountDesc(Pageable pageRequest);
}
