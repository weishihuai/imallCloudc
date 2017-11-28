package com.imall.iportal.core.elasticsearch.utils;


import com.imall.iportal.core.elasticsearch.ESUtils;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;

import java.util.*;
import java.util.regex.Pattern;

/**
 * es搜索utils
 */
public class SearchUtils {
    private static final Logger logger = Loggers.getLogger(SearchUtils.class);

    public static SearchRequestBuilder simpleSearch(
            String[] indexNames
            , HashMap<String, Object[]> searchContentMap
            , int from, int offset
            , String sortField, String sortType) {
        MySearchOption.SearchLogic searchLogic = indexNames.length > 1 ? MySearchOption.SearchLogic.should : MySearchOption.SearchLogic.must;
        return simpleSearch(indexNames, searchContentMap, searchLogic, from, offset, sortField, sortType);
    }

    public static SearchRequestBuilder simpleSearch(
            String indexNames
            , HashMap<String, Object[]> searchContentMap
            , int from, int offset
            , String sortField, String sortType) {
        return simpleSearch(new String[]{indexNames}, searchContentMap, MySearchOption.SearchLogic.must, from, offset, sortField, sortType);
    }

    public static SearchRequestBuilder simpleSearch(String[] indexNames
            , HashMap<String, Object[]> searchContentMap, MySearchOption.SearchLogic searchLogic
            , int from, int offset, String sortField, String sortType) {
        if (offset <= 0) {
            return null;
        }
        try {
            BoolQueryBuilder queryBuilder = createQueryBuilder(searchContentMap, searchLogic);
            SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
                    .prepareSearch(indexNames)
                    .setTypes(indexNames)
                    .setSearchType(SearchType.DEFAULT)
                    .setQuery(queryBuilder)
                    .setFrom(from).setSize(offset)
                    .setExplain(false);
            if (sortField == null || sortField.isEmpty() || sortType == null || sortType.isEmpty()) {
                /*如果不需要排序*/
            } else {
                /*如果需要排序*/
                SortOrder sortOrder = sortType.equals("desc") ? SortOrder.DESC : SortOrder.ASC;
                searchRequestBuilder = searchRequestBuilder.addSort(sortField, sortOrder);
            }
            // 高亮
//            searchRequestBuilder = createHighlight(searchRequestBuilder, searchContentMap,"b,b");
            return searchRequestBuilder;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static SearchRequestBuilder createHighlight(SearchRequestBuilder searchRequestBuilder, HashMap<String, Object[]> searchContentMap, String highlightCSS) {
        Iterator<Map.Entry<String, Object[]>> iterator = searchContentMap.entrySet().iterator();
        /*循环每一个需要搜索的字段和值*/
        while (iterator.hasNext()) {
            Map.Entry<String, Object[]> entry = iterator.next();
            String field = entry.getKey();
            Object[] values = entry.getValue();
            /*排除非法的搜索值*/
            if (!checkValue(values)) {
                continue;
            }

            /*获得搜索类型*/
            MySearchOption mySearchOption = getSearchOption(values);
            if (mySearchOption.isHighlight()) {
                /*
                 * http://www.elasticsearch.org/guide/reference/api/search/highlighting.html
                 *
                 * fragment_size设置成1000，默认值会造成返回的数据被截断
                 * */
                HighlightBuilder highlightBuilder = new HighlightBuilder();
                highlightBuilder.field(field, 1000).preTags("<" + highlightCSS.split(",")[0] + ">").postTags("</" + highlightCSS.split(",")[1] + ">");

                searchRequestBuilder = searchRequestBuilder.highlighter(highlightBuilder);
            }
        }
        return searchRequestBuilder;
    }


    /*创建搜索条件*/
    private static BoolQueryBuilder createQueryBuilder(HashMap<String, Object[]> searchContentMap, MySearchOption.SearchLogic searchLogic) {
        try {
            if (searchContentMap == null || searchContentMap.size() == 0) {
                return null;
            }

            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            Iterator<Map.Entry<String, Object[]>> iterator = searchContentMap.entrySet().iterator();
            /*循环每一个需要搜索的字段和值*/
            while (iterator.hasNext()) {
                Map.Entry<String, Object[]> entry = iterator.next();
                String field = entry.getKey();
                Object[] values = entry.getValue();

                /*获得搜索类型*/
                MySearchOption mySearchOption = getSearchOption(values);
                QueryBuilder queryBuilder = createSingleFieldQueryBuilder(field, values, mySearchOption);
                if (queryBuilder != null) {
                    if (searchLogic == MySearchOption.SearchLogic.should) {
                        /*should关系，也就是说，在A索引里有或者在B索引里有都可以*/
                        boolQueryBuilder = boolQueryBuilder.should(queryBuilder);
                    } else {
                        /*must关系，也就是说，在A索引里有，在B索引里也必须有*/
                        boolQueryBuilder = boolQueryBuilder.must(queryBuilder);
                    }
                }
            }
            return boolQueryBuilder;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    private static QueryBuilder createSingleFieldQueryBuilder(String field, Object[] values, MySearchOption mySearchOption) {
        try {
            if (mySearchOption.getSearchType() == MySearchOption.SearchType.range) {
                /*区间搜索*/
                return createRangeQueryBuilder(field, values);
            }
            //        String[] fieldArray = field.split(",");/*暂时不处理多字段[field1,field2,......]搜索情况*/
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            for (Object valueItem : values) {
                if (valueItem instanceof MySearchOption) {
                    continue;
                }
                QueryBuilder queryBuilder = null;
                String formatValue = valueItem.toString().trim().replace("*", "");//格式化搜索数据
                if (mySearchOption.getSearchType() == MySearchOption.SearchType.term) {
                    queryBuilder = QueryBuilders.termQuery(field, formatValue).boost(mySearchOption.getBoost());
                } else if (mySearchOption.getSearchType() == MySearchOption.SearchType.querystring) {
                    if (formatValue.length() == 1) {
                        /*如果搜索长度为1的非数字的字符串，格式化为通配符搜索，暂时这样，以后有时间改成multifield搜索，就不需要通配符了*/
                        if (!Pattern.matches("[0-9]", formatValue)) {
                            formatValue = "*" + formatValue + "*";
                        }
                    }
                    QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery(formatValue).minimumShouldMatch(mySearchOption.getQueryStringPrecision());
                    queryBuilder = queryStringQueryBuilder.field(field).boost(mySearchOption.getBoost());
                }
                //默认是and
                if (MySearchOption.SearchLogic.should == mySearchOption.getSearchLogic()) {
                    boolQueryBuilder = boolQueryBuilder.should(queryBuilder);
                } else if (MySearchOption.SearchLogic.must_not == mySearchOption.getSearchLogic()) {
                    boolQueryBuilder = boolQueryBuilder.mustNot(queryBuilder);
                } else {
                    boolQueryBuilder = boolQueryBuilder.must(queryBuilder);
                }
            }
            return boolQueryBuilder;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /*范围搜索*/
    private static RangeQueryBuilder createRangeQueryBuilder(String field, Object[] values) {
        if (values.length == 1 || values[1] == null || values[1].toString().trim().isEmpty()) {
            logger.warn("[区间搜索]必须传递两个值，但是只传递了一个值，所以返回null");
            return null;
        }
        boolean timeType = false;
        if (MySearchOption.isDate(values[0])) {
            if (MySearchOption.isDate(values[1])) {
                timeType = true;
            }
        }
        String begin = "", end = "";
        if (timeType) {
            /*
             * 如果时间类型的区间搜索出现问题，有可能是数据类型导致的：
             *     （1）在监控页面（elasticsearch-head）中进行range搜索，看看什么结果，如果也搜索不出来，则：
             *     （2）请确定mapping中是date类型，格式化格式是yyyy-MM-dd HH:mm:ss
             *    （3）请确定索引里的值是类似2012-01-01 00:00:00的格式
             *    （4）如果是从数据库导出的数据，请确定数据库字段是char或者varchar类型，而不是date类型（此类型可能会有问题）
             * */
            begin = MySearchOption.formatDate(values[0]);
            end = MySearchOption.formatDate(values[1]);
        } else {
            begin = values[0].toString();
            end = values[1].toString();
        }
        return QueryBuilders.rangeQuery(field).from(begin).to(end);
    }

    /*获得搜索结果*/
    public static List<Map<String, Object>> getSearchResult(SearchResponse searchResponse) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            for (SearchHit searchHit : searchResponse.getHits()) {
                Iterator<SearchHitField> iterator = searchHit.iterator();
                HashMap<String, Object> resultMap = new HashMap<String, Object>();
                while (iterator.hasNext()) {
                    SearchHitField hitField = iterator.next();
                    resultMap.put(hitField.getName(), hitField.getValue());
                }
                Map<String, HighlightField> highlightMap = searchHit.highlightFields();
                Iterator<Map.Entry<String, HighlightField>> highlightIterator = highlightMap.entrySet().iterator();
                while (highlightIterator.hasNext()) {
                    Map.Entry<String, HighlightField> entry = highlightIterator.next();
                    Object[] contents = entry.getValue().fragments();
                    if (contents.length == 1) {
                        resultMap.put(entry.getKey(), contents[0].toString());
                    } else {
                        logger.warn("搜索结果中的高亮结果出现多数据contents.length = " + contents.length);
                    }
                }
                resultList.add(resultMap);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return resultList;
    }

    /*获得搜索选项*/
    private static MySearchOption getSearchOption(Object[] values) {
        for (Object item : values) {
            if (item instanceof MySearchOption) {
                return (MySearchOption) item;
            }
        }
        return new MySearchOption();
    }

    /*简单的值校验*/
    private static boolean checkValue(Object[] values) {
        if (values == null) {
            return false;
        } else if (values.length == 0) {
            return false;
        } else if (values[0] == null) {
            return false;
        } else if (values[0].toString().trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
