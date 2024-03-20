package com.exitzero.com.thymeleaf.pagination;

import com.exitzero.com.ComDefaultVO;

/**
 * packageName    : com.pagination
 * fileName       : PaginationUtils
 * author         : 조예찬
 * date           : 2023-09-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-25        조예찬       최초 생성
 */
public class PaginationUtils {

    public static <T extends PaginationVO> PaginationInfo buildPagination(T searchVO, Integer totalCount){

        if(searchVO == null || totalCount == null) return null;

        try{
            PaginationInfo paginationInfo = new PaginationInfo(searchVO.getPage(),searchVO.getPageUnit(),searchVO.getPageSize(),totalCount);

            searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
            searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
            searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            return paginationInfo;

        } catch (Exception e){
            return null;
        }

    }

}
