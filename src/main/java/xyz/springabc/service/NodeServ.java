package xyz.springabc.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import xyz.springabc.domin.Node;
import xyz.springabc.repository.NodeRepo;
import xyz.springabc.repository.TopicRepo;

@Service
public class NodeServ {
	
	@Autowired
	private TopicRepo topicRepo;
	
	@Autowired
	private NodeRepo nodeRepo;
	
	public Node createBeforCheck(Node node){
		return nodeRepo.save(node);
	}
	
	/**
	 * 获取所有节点
	 * @return
	 */
	public List<Node> getNode(){
		return nodeRepo.findAll();
	}
	
	/**
	 * 
	 * @param page
	 * @return
	 */
	public Page<Node> getNode(int page){
		PageRequest pageRequest=new PageRequest(--page, 30);
		return nodeRepo.findAll(pageRequest);
	}
	
	/**
	 * 这里没办法刷新缓存，只好重新来了。
	 * @param page
	 * @param size
	 * @return
	 */
	@Cacheable(cacheNames="servCache",key="#page+#size")
	public Page<Node> getAllOrderByTopicCount(int page,int size){
		PageRequest pageRequest=new PageRequest(--page, size);
		return nodeRepo.findAllOrderByTopicCountDesc(pageRequest);
	}
	/**
	 * 按照分类查找节点
	 * @param sectionName
	 * @return
	 */
	public List<Node> getNodeBySection(String sectionName){
		List<Node> nodes=new ArrayList<Node>();
		nodes.addAll(nodeRepo.findBySection(sectionName));
		return nodes;
	}
	
	/**
	 * 按照分类查找节点名
	 * @param sectionName
	 * @return 一列节点的名
	 */
	public List<String> getNodeNameBySectionName(String sectionName){
		List<String> names=new ArrayList<String>();
		List<Node> nodes=nodeRepo.findBySection(sectionName);
		for(Node node:nodes){
			names.add(node.getName());
		}
		return names;
	}
	
	@Transactional
	public Node getOneNode(int id){
		Node node=nodeRepo.findOne(new Integer(id));
		node.setTopicCount(topicRepo.countByNode(node));
		nodeRepo.save(node);
		return node;
	}
	
	public Node getByname(String name){
		return nodeRepo.findByName(name);
	}
	
	public void delete(Node node){
		nodeRepo.delete(node);
	}
	
	public void deleteById(int id){
		nodeRepo.delete(new Integer(id));
	}
	
	public Node update(Node node){
		Node oldNode=nodeRepo.findOne(node.getId());
		if(oldNode!=null){
			oldNode.setName(node.getName());
			oldNode.setSection(node.getSection());
			oldNode.setDescription(node.getDescription());
			nodeRepo.save(oldNode);
		}
		return oldNode;
	}
	
	public void save(Node node){
		nodeRepo.save(node);
	}
}
