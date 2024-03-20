package com.exitzero.com;

import com.exitzero.com.thymeleaf.pagination.PaginationVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComDefaultVO extends PaginationVO {
    private String searchKeyword = null;
    private String searchCondition = null;
}
