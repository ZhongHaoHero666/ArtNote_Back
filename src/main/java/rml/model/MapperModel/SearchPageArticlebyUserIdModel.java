package rml.model.MapperModel;

/**
 * Created by BBLink on 2017/5/9.
 */
public class SearchPageArticlebyUserIdModel {
    String userId;
    int pageSize;
    int startOffert;

    public SearchPageArticlebyUserIdModel(String userId, int pageSize, int startOffert) {
        this.userId = userId;
        this.pageSize = pageSize;
        this.startOffert = startOffert;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartOffert() {
        return startOffert;
    }

    public void setStartOffert(int startOffert) {
        this.startOffert = startOffert;
    }
}
