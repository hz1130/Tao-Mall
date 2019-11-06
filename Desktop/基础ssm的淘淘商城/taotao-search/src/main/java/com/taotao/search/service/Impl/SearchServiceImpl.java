package com.taotao.search.service.Impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;

/**
 * 搜索service
 * @author 
 *
 */
@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SearchDao searchDao;

	@Override
	public SearchResult search(String queryString, int page, int rows) throws Exception {
		//创建查询对象
				SolrQuery query = new SolrQuery();
				//设置查询条件 京东的查询条件不能为空，如果为空，默认返回一个推荐
				query.setQuery(queryString);
				//设置分页 设置一个默认值，不能为空
				query.setStart((page - 1) * rows);
				query.setRows(rows);
				//设置默认搜素域
				query.set("df", "item_keywords");
				//设置高亮显示
				query.setHighlight(true);
				query.addHighlightField("item_title");
				query.setHighlightSimplePre("<em style=\"color:red\">");
				query.setHighlightSimplePost("</em>");
				//执行查询
				SearchResult searchResult = searchDao.search(query);
				//计算查询结果总页数
				long recordCount = searchResult.getRecordCount();
				long pageCount = recordCount / rows;
				if (recordCount % rows > 0) { //如果10个记录数。每页显示3个，还多出一页
					pageCount++;
				}
				searchResult.setPageCount(pageCount);
				searchResult.setCurPage(page);
				
				return searchResult;
	}

}
