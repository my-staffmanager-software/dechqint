
package com.decagon.interview.stageone.model;

import java.util.List;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class UserArticle{

    public List<Datum> data;
    private String page;
    private long perPage;
    private long total;
    private long totalPages;

}
